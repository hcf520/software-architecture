package com.unisoc.utils;

import com.unisoc.model.Result;

public class ResultUtil {

	public static Result success(Integer code,String msg,Object resultData) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(resultData);
		return result;
	}


	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
}
