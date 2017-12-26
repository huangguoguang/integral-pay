package com.acl.pay.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	// 角色
	private String role;

	private String ch_id; // 渠道ID
	private String ch_name; // 渠道名
	private String ce_id; // 操作中心ID
	private String ce_name; // 操作中心名
	private String settle_id; // 结算ID
	private String settle_name; // 结算名
	private String agent_id; // 代理商ID
	private String agent_name; // 代理商名称
	private String agent_code; // 代理商CODE
	private String user_id; // 用户ID
	private String user_name; // 用户名
	private String login_id; // 登录ID
	private String mobile; // 手机号
	private String password; // 密码
	private Double balance; // 余额
	private String is_use; // 是否有效

	//
	// 微信信息
	//
	private String appId; // 应用ID
	private String secret; // 密钥
	private String wxOpenId; // 交易openid
	private String payWxOpenId; // 支付openid
	private String headImage; // 微信头像
	
	private String entrance;
	
	private String yijipay_user_id;

	private String ip;//用户请求ip
	
	public String getYijipay_user_id() {
		return yijipay_user_id;
	}

	public void setYijipay_user_id(String yijipay_user_id) {
		this.yijipay_user_id = yijipay_user_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCh_id() {
		return ch_id;
	}

	public void setCh_id(String ch_id) {
		this.ch_id = ch_id;
	}

	public String getCh_name() {
		return ch_name;
	}

	public void setCh_name(String ch_name) {
		this.ch_name = ch_name;
	}

	public String getCe_id() {
		return ce_id;
	}

	public void setCe_id(String ce_id) {
		this.ce_id = ce_id;
	}

	public String getCe_name() {
		return ce_name;
	}

	public void setCe_name(String ce_name) {
		this.ce_name = ce_name;
	}

	public String getSettle_id() {
		return settle_id;
	}

	public void setSettle_id(String settle_id) {
		this.settle_id = settle_id;
	}

	public String getSettle_name() {
		return settle_name;
	}

	public void setSettle_name(String settle_name) {
		this.settle_name = settle_name;
	}

	public String getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(String agent_id) {
		this.agent_id = agent_id;
	}

	public String getAgent_name() {
		return agent_name;
	}

	public void setAgent_name(String agent_name) {
		this.agent_name = agent_name;
	}

	public String getAgent_code() {
		return agent_code;
	}

	public void setAgent_code(String agent_code) {
		this.agent_code = agent_code;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public String getIs_use() {
		return is_use;
	}

	public void setIs_use(String is_use) {
		this.is_use = is_use;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getWxOpenId() {
		return wxOpenId;
	}

	public void setWxOpenId(String wxOpenId) {
		this.wxOpenId = wxOpenId;
	}

	public String getPayWxOpenId() {
		return payWxOpenId;
	}

	public void setPayWxOpenId(String payWxOpenId) {
		this.payWxOpenId = payWxOpenId;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "UserInfo [role=" + role + ", ch_id=" + ch_id + ", ch_name=" + ch_name + ", ce_id=" + ce_id + ", ce_name="
				+ ce_name + ", settle_id=" + settle_id + ", settle_name=" + settle_name + ", agent_id=" + agent_id
				+ ", agent_name=" + agent_name + ", agent_code=" + agent_code + ", user_id=" + user_id + ", user_name="
				+ user_name + ", login_id=" + login_id + ", mobile=" + mobile + ", password=" + password + ", balance="
				+ balance + ", is_use=" + is_use + ", appId=" + appId + ", secret=" + secret + ", wxOpenId=" + wxOpenId
				+ ", payWxOpenId=" + payWxOpenId + ", headImage=" + headImage + "]";
	}

	public Map<String, Object> convertToMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", this.getUser_name());
		map.put("balance", this.getBalance());
		map.put("role", this.getRole());
		map.put("isValid", this.getIs_use());
		// 模糊手机号
		String mobile = this.getMobile();
		if (mobile.length() == 11) {
			mobile = mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length());
		} else {
			mobile = "";
		}
		map.put("mobile", mobile);
		return map;
	}

}
