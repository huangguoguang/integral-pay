package com.acl.pay.service;

import com.acl.pay.entity.UserInfo;

import java.util.Map;

public interface UserInfoService {
	
	/**
	 * 刷新用户信息 IN REDIS
	 * @param cacheUser
	 */
	public void refreshUserInfo(UserInfo cacheUser);

	/**
	 * 查询用户信息 IN MONGODB
	 * @param mobile
	 */
	public UserInfo queryUserInfoInMongodb(String mobile) throws Exception;

	/**
	 * 用户默认收货地址
	 * @param userId
	 * @return
	 */
	Map<String, Object> getDefaultAddr(String userId);

	/**
	 * title
	 * @param userInfo
	 * @return
	 * @throws Exception
	 */
	String getTitle(UserInfo userInfo) throws Exception;


	/**
	 * 根据token获取用户登录信息
	 * @param accessToken
	 * @return
	 */
	String userInfoByAccessToken(String accessToken);
}
