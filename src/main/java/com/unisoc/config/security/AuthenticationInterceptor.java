package com.unisoc.config.security;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.unisoc.entity.Permissions;
import com.unisoc.entity.User;
import com.unisoc.model.RedisCacheKey;
import com.unisoc.service.IUserService;
import com.unisoc.utils.RedisUtil;

public class AuthenticationInterceptor implements HandlerInterceptor {

	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object object) throws Exception {
		String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出
																// token
		// 如果不是映射到方法直接通过
		if (!(object instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) object;
		Method method = handlerMethod.getMethod();
		// 检查是否有passtoken注释，有则跳过认证
		// if (method.isAnnotationPresent(PassToken.class)) {
		// PassToken passToken = method.getAnnotation(PassToken.class);
		// if (passToken.required()) {
		// return true;
		// }
		// }
		// 检查有没有需要用户权限的注解
		if (method.isAnnotationPresent(TokenPermissions.class)) {
			TokenPermissions tokenPermissions = method.getAnnotation(TokenPermissions.class);
			if (tokenPermissions.value() != null && !tokenPermissions.value().equals("")) {
				// 获取 token中的 userid
				String userId;
				try {
					userId = JWT.decode(token).getAudience().get(0);
				} catch (JWTDecodeException j) {
					throw new RuntimeException("401");
				}
				
				List<Permissions> permissionsList = (List<Permissions>) redisUtil.get(RedisCacheKey.getUserPermissions(userId));
				int size = permissionsList.size();
				for (int i = 0; i < size; i++) {
					//如有合适权限，则通过
					if(tokenPermissions.value().equals(permissionsList.get(i).getControlArea())){
						return true;
					}
				}
				return false;
			} else {
				throw new RuntimeException("注解异常");
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
	}
}
