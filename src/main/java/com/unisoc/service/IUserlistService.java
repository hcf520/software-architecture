package com.unisoc.service;

import com.unisoc.entity.Userlist;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hcf
 * @since 2019-12-19
 */
public interface IUserlistService extends IService<Userlist> {
	void valiTransactional();
}
