package com.unisoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unisoc.config.security.TokenPermissions;
import com.unisoc.entity.Userlist;
import com.unisoc.service.IUserlistService;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hcf
 * @since 2019-12-19
 */
@RestController
@RequestMapping("/userlist")
public class UserlistController {

	@Autowired
	private IUserlistService iUserlistService;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@GetMapping(value = "/findAll")
	public List findAll() {
		return iUserlistService.list();
	}

	@TokenPermissions("threeModel1")
	@GetMapping("/findById")
	public Userlist findById(int id) {
		return iUserlistService.getById(id);
	}

	@GetMapping(value = "/findRedisById")
	public Userlist findRedisById(int id) {
//		redisTemplate.opsForValue().set("shiro:two", "第二个值");
		String key = (String) redisTemplate.opsForValue().get("two");
		redisTemplate.opsForValue().set("obj-"+id, iUserlistService.getById(id));	
		System.out.println(redisTemplate.randomKey());
		return (Userlist) redisTemplate.opsForValue().get("obj-"+id);
		
	}
}
