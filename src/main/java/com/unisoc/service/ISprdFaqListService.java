package com.unisoc.service;

import com.unisoc.entity.SprdFaqList;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hcf
 * @since 2019-12-19
 */
public interface ISprdFaqListService extends IService<SprdFaqList> {
	
	/**
	 * 事务验证
	 */
	public void valiTransactional();

}
