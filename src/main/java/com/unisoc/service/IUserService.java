package com.unisoc.service;

import com.unisoc.entity.Permissions;
import com.unisoc.entity.User;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
public interface IUserService extends IService<User> {
	static final String userTokenCache = "USER:TOKEN";

	boolean userRegister(User user);

	User userLogin(User user);

	String getToken(User user,String sessionID);

	/**
	 * 根据当前用户角色ID得出用户所有权限列表
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permissions> getUserPermissions(int roleId);

	/**
	 * 保存用户TOKEN到Redis
	 * @param token
	 * @return
	 */
	boolean saveUserToken(String userKey,String token);
	
	/**
	 * 缓存用户权限列表
	 * @param userKey
	 * @param permissionsList
	 * @return
	 */
	boolean saveUserPermissions(String userKey,List<Permissions> permissionsList);
	
	/**
	 * 缓存用户个人信息
	 * @param userKey
	 * @param user
	 * @return
	 */
	public boolean saveUserInfo(String userKey, User user);
	
	
}
