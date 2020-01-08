package com.unisoc.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {

	public static void main(String[] args) {
		  String keyword="test";
		  String md5=DigestUtils.md5Hex(keyword);
		  System.out.println("md5加密后："+"\n"+md5);
		  
		  String md5salt=md5PlusSalt(keyword);
		  System.out.println("加盐后："+"\n"+md5salt);
		  
		  String word= md5MinusSalt(md5salt);
		  System.out.println("解密后："+"\n"+word);
	}

	public static String md5PlusSalt(String keyword) {
		String md5 = DigestUtils.md5Hex(keyword);
		char[] cArray = md5.toCharArray();
		for (int i = 0; i < cArray.length; i++) {
			if (cArray[i] >= 48 && cArray[i] <= 57) {
				cArray[i] = (char) (105 - cArray[i]);

			}
		}
		return "".valueOf(cArray);

	}

	public static String md5MinusSalt(String md5) {
		char[] cArray = md5.toCharArray();
		for (int i = 0; i < cArray.length; i++) {
			if (cArray[i] >= 48 && cArray[i] <= 57) {
				cArray[i] = (char) (105 - cArray[i]);

			}
		}
		return "".valueOf(cArray);

	}
}
