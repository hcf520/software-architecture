package com.unisoc.service.impl;

import com.unisoc.entity.SprdFaqList;
import com.unisoc.mapper.SprdFaqListMapper;
import com.unisoc.service.ISprdFaqListService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hcf
 * @since 2019-12-19
 */
@Service
public class SprdFaqListServiceImpl extends ServiceImpl<SprdFaqListMapper, SprdFaqList> implements ISprdFaqListService {

	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void valiTransactional() {
		UpdateWrapper<SprdFaqList> updateWrapper = new UpdateWrapper<SprdFaqList>();
		updateWrapper.eq("id", 163).set("question", "验证事务110");
		this.update(updateWrapper);
		//int i = 1 / 0;
		throw new RuntimeException("数据库更新操作异常");
		//this.removeById(163);
		
	}

}
