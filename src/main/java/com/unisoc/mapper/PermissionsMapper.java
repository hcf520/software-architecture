package com.unisoc.mapper;

import com.unisoc.entity.Permissions;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
public interface PermissionsMapper extends BaseMapper<Permissions> {

	List<Permissions> queryUserAllPermissions(List<Permissions> list);
}
