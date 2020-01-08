package com.unisoc.service.impl;

import com.unisoc.entity.Permissions;
import com.unisoc.entity.Role;
import com.unisoc.entity.User;
import com.unisoc.mapper.UserMapper;
import com.unisoc.model.RedisCacheKey;
import com.unisoc.service.IPermissionsService;
import com.unisoc.service.IRoleService;
import com.unisoc.service.IUserService;
import com.unisoc.utils.PasswordUtil;
import com.unisoc.utils.RedisUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Autowired
	private IRoleService iRoleService;

	@Autowired
	private IPermissionsService iPermissionsService;

	@Autowired
	private RedisUtil redisUtil;

	@Transactional
	@Override
	public boolean userRegister(User user) {
		if (null == user.getUserpwd() || user.getUserpwd().trim().equals("")) {
			return false;
		} else {
			// String md5PlusSaltPWD = Md5Utils.md5PlusSalt(user.getUserpwd());
			String userSaltPWD = PasswordUtil.generate(user.getUserpwd());
			user.setUserpwd(userSaltPWD);
			user.setRoleId(2);
			return this.save(user);
		}
	}

	@Override
	public User userLogin(User user) {
		if (user != null && !user.equals("")) {
			// String md5PlusSaltPWD = Md5Utils.md5PlusSalt(user.getUserpwd());
			// QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			// Map<SFunction<User, ?>, Object> params = new HashMap<>();
			// params.put(User::getUserid, user.getUserid());
			// params.put(User::getUserpwd, md5PlusSaltPWD);
			// queryWrapper.lambda().allEq(params);
			// return this.getOne(queryWrapper, true);
			QueryWrapper<User> queryWrapper = new QueryWrapper<>();
			queryWrapper.lambda().eq(User::getUserid, user.getUserid());
			User resultUser = this.getOne(queryWrapper, true);
			if (resultUser != null && !user.equals("")) {
				boolean pwdValiFlag = PasswordUtil.verify(user.getUserpwd(), resultUser.getUserpwd());
				return pwdValiFlag ? resultUser : null;
			}
		}
		return null;
	}

	/**
	 * 生成TOKEN
	 */
	public String getToken(User user, String sessionID) {
		String token = "";
		// 过期时间，单位毫秒
		// long EXPIRE_TIME = 7*24*3600*1000;
		// 生成过期时间
		// Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
		token = JWT.create()
				.withAudience(user.getUserid())
				.withIssuedAt(new Date())
				.withJWTId(sessionID)
				.sign(Algorithm.HMAC256(user.getUserpwd()));
		return token;
	}

	@Override
	public List<Permissions> getUserPermissions(int roleId) {
		Role role = iRoleService.getAllRoleAndPermissions(roleId);
		List<Permissions> permissionsList = role.getPermissionsList();
		return iPermissionsService.getUserAllPermissions(permissionsList);
	}

	@Override
	public boolean saveUserToken(String userKey, String token) {
		return redisUtil.set(RedisCacheKey.getUserToken(userKey), token, 3600);
	}

	@Override
	public boolean saveUserPermissions(String userKey, List<Permissions> permissionsList) {
		return redisUtil.set(RedisCacheKey.getUserPermissions(userKey), permissionsList, 3600);
	}

	@Override
	public boolean saveUserInfo(String userKey, User user) {
		return redisUtil.set(RedisCacheKey.getUserInfo(userKey), user, 3600);
	}
}
