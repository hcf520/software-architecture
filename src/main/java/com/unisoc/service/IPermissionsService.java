package com.unisoc.service;

import com.unisoc.entity.Permissions;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
public interface IPermissionsService extends IService<Permissions> {

	List<Permissions> getUserAllPermissions(List<Permissions> list);
}
