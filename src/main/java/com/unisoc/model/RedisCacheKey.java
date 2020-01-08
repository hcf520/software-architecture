package com.unisoc.model;

public class RedisCacheKey {

	private static final String USER_TOKEN = "USER:TOKEN:";
	private static final String USER_PERMISSIONS = "USER:PERMISSIONS:";
	private static final String USER_INFO = "USER:INFO:";

	/**
	 * 用户TOKEN地址
	 * @param userKey
	 * @return
	 */
	public static String getUserToken(String userKey) {
		return USER_TOKEN + userKey;
	}

	/**
	 * 用户权限地址
	 * @param userKey
	 * @return
	 */
	public static String getUserPermissions(String userKey) {
		return USER_PERMISSIONS + userKey;
	}

	/**
	 * 用户个人信息地址
	 * @param userKey
	 * @return
	 */
	public static String getUserInfo(String userKey) {
		return USER_INFO + userKey;
	}

}
