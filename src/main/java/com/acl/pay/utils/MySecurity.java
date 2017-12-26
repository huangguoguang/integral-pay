package com.acl.pay.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MySecurity {
	private final static String encoding = "UTF-8";

	private final static String MOBILE_KEY = "ANCHOL2016";

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param password
	 * @return
	 */
	public static String encryptAES(String content, String password) {
		byte[] encryptResult = encrypt(content, password);
		String encryptResultStr = parseByte2HexStr(encryptResult);
		// BASE64位加密
		encryptResultStr = ebotongEncrypto(encryptResultStr);
		return encryptResultStr;
	}

	/**
	 * AES解密
	 * 
	 * @param encryptResultStr
	 * @param password
	 * @return
	 */
	public static String decrypt(String encryptResultStr, String password) {
		// BASE64位解密
		String decrpt = ebotongDecrypto(encryptResultStr);
		byte[] decryptFrom = parseHexStr2Byte(decrpt);
		byte[] decryptResult = decrypt(decryptFrom, password);
		return new String(decryptResult);
	}

	/**
	 * 加密字符串
	 */
	private static String ebotongEncrypto(String str) {
		BASE64Encoder base64encoder = new BASE64Encoder();
		String result = str;
		if (str != null && str.length() > 0) {
			try {
				byte[] encodeByte = str.getBytes(encoding);
				result = base64encoder.encode(encodeByte);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// base64加密超过一定长度会自动换行 需要去除换行符
		return result.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
	}

	/**
	 * 解密字符串
	 */
	private static String ebotongDecrypto(String str) {
		BASE64Decoder base64decoder = new BASE64Decoder();
		try {
			byte[] encodeByte = base64decoder.decodeBuffer(str);
			return new String(encodeByte);
		} catch (IOException e) {
			e.printStackTrace();
			return str;
		}
	}

	/**
	 * 加密
	 * 
	 * @param content
	 *          需要加密的内容
	 * @param password
	 *          加密密码
	 * @return
	 */
	private static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *          待解密内容
	 * @param password
	 *          解密密钥
	 * @return
	 */
	private static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			// 防止linux下 随机生成key
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(password.getBytes());
			kgen.init(128, secureRandom);
			// kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	public static Object justDecrypt(String data, String password) {
		if (data == null || data.trim().isEmpty()) {
			return data;
		}
		return decrypt(data.toString(), password);
	}

	public static String encoderMobile(String mobile) {
		return encryptAES(mobile, MOBILE_KEY);
	}

	public static String decoderMobile(String mobile) {
		return decrypt(mobile, MOBILE_KEY);
	}

	public static String decoderMoblieAddMark(String data) {
		if (StringUtils.isBlank(data)) {
			return data;
		}
		String mobile = decoderMobile(data);
		return mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
	}

	public static void main(String[] args) {
//		System.out.println(encryptAES(decrypt("RURGN0Q1NTI2QTYzNTgzQkNGMjFFQTcxNUI0NDQ4NjY=", "ANCHOL2016")+"x", "ANCHOL2016"));
		System.out.println(decrypt("MTYzQTgzREIyMzkzNjhGQzU1RjVEN0I5RDcxQkNFQTI=", "ANCHOL2016"));
////		// 
		System.out.println(encryptAES("18612344499", "ANCHOL2016"));
		

//		String key[] = {"13297978246", "13297978246x", "15159122205","18830767507","18045279191"};
//		for (int i = 0; i < key.length; i++) {
//			System.out.println(key[i] + "    " + encryptAES(key[i], "ANCHOL2016"));
//		}
//		
//		String keys[] = {"N0RDMDVGQTJBNzM3RTg2RTZGNUVBQUI2NDZFMTlFNjY=","OUQxNDlENkU2REQzMUY5RjNGMkQ5QzA3MUE4NDI0Qjk=","QTIxNEJCNjlGRjNDMTY2QTI1NThDRTc4QkI4QUI4RDc=","RUNFNzU4NkFCNDQ0REQwRjRCNzQyODdBQ0E5OEMzNDM="};
//		for (int i = 0; i < keys.length; i++) {
//			System.out.println(decrypt(keys[i], "ANCHOL2016"));
//		}
		
	}

}