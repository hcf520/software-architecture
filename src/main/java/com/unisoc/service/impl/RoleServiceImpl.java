package com.unisoc.service.impl;

import com.unisoc.entity.Role;
import com.unisoc.mapper.RoleMapper;
import com.unisoc.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public Role getAllRoleAndPermissions(Integer roleId) {

		return roleMapper.queryAllRoleAndPermissions(roleId);
	}

}
