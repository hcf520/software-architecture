package com.unisoc.service.impl;

import com.unisoc.entity.Userlist;
import com.unisoc.mapper.UserlistMapper;
import com.unisoc.service.IUserlistService;
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
public class UserlistServiceImpl extends ServiceImpl<UserlistMapper, Userlist> implements IUserlistService {

	@Override
	@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
	public void valiTransactional() {
		UpdateWrapper<Userlist> updateWrapper = new UpdateWrapper<Userlist>();
		updateWrapper.eq("id", 1).set("mail", "846656938@qq.com");
		this.update(updateWrapper);
		//int i = 1 / 0;
		//throw new RuntimeException("数据库更新操作异常");
		//this.removeById(2);
		
	}

}
