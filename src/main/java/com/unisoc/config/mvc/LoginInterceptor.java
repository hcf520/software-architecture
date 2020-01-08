package com.unisoc.config.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.unisoc.entity.User;
import com.unisoc.model.RedisCacheKey;
import com.unisoc.utils.GlobalConst;
import com.unisoc.utils.RedisUtil;

public class LoginInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 如果不是映射到方法直接通过
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}

		String token = request.getHeader("token");// 从 http 请求头中取出 token
		String sessionID = request.getSession().getId();// 得到session唯一ID
		// 如果无Token信息将跳登录界面
		if (token == null) {
			// throw new RuntimeException("无token，请重新登录");
			response.sendRedirect("/");
			logger.info("请先登录");
			return false;
		}
		// 获取 token中的 userid
		String userId;
		try {
			userId = JWT.decode(token).getAudience().get(0);
		} catch (JWTDecodeException j) {
			throw new RuntimeException("401");
		}
		String redisToken = (String) redisUtil.get(RedisCacheKey.getUserToken(userId));// 通过解析出的userid查出对应Token
		// 验证 token一致性
		String tokenSessionID = JWT.decode(redisToken).getId();
		if ((redisToken.equals(token)) && (tokenSessionID.equals(sessionID))) {
			boolean userTokenFlag = redisUtil.expire(RedisCacheKey.getUserToken(userId), 3600);
			boolean userPermissionsFlag = redisUtil.expire(RedisCacheKey.getUserPermissions(userId), 3600);
			boolean userInfoFlag = redisUtil.expire(RedisCacheKey.getUserInfo(userId), 3600);
			if (!userTokenFlag && !userPermissionsFlag && !userInfoFlag) {//session会话超时
				response.sendRedirect("/");
				logger.info("请先登录");
				return false;
			}
		} else {
			throw new RuntimeException("会话错误");
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("postHandle...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("afterCompletion...");
	}

}
