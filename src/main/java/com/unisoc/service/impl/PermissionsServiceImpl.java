package com.unisoc.service.impl;

import com.unisoc.entity.Permissions;
import com.unisoc.mapper.PermissionsMapper;
import com.unisoc.service.IPermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements IPermissionsService {

	@Autowired
	private PermissionsMapper permissionsMapper;

	@Override
	public List<Permissions> getUserAllPermissions(List<Permissions> list) {
		if (list != null && list.size() > 0) {
			return permissionsMapper.queryUserAllPermissions(list);
		}
		return null;
	}

}
