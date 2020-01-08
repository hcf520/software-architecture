package com.unisoc.utils;

public enum GlobalConst {

	USER_SESSION_KEY("usersession", "用户SESSION的KEY"),
	VALI_SESSION_KEY("valicode","用户登录验证码SESSION的KEY");
	private String key;

	private String text;

	private GlobalConst(String key, String text) {
		this.key = key;
		this.text = text;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
