package com.unisoc.service;

import com.unisoc.entity.Role;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
public interface IRoleService extends IService<Role> {

	Role getAllRoleAndPermissions(Integer roleId);
}
