package com.unisoc.mapper;

import com.unisoc.entity.Role;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
public interface RoleMapper extends BaseMapper<Role> {

	Role queryAllRoleAndPermissions(Integer roleId);
}
