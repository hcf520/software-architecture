package com.unisoc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.unisoc.entity.SprdFaqList;
import com.unisoc.service.ISprdFaqListService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hcf
 * @since 2019-12-19
 */
@RestController
@RequestMapping("/sprd-faq-list")
public class SprdFaqListController {

	@Autowired
	private ISprdFaqListService iSprdFaqListService;

	@GetMapping(value = "/findAll")
	public List findAll() {
		return iSprdFaqListService.list();
	}

	@GetMapping("/findById")
	public SprdFaqList findById(int id) {
		return iSprdFaqListService.getById(id);
	}

	@GetMapping("/updateQuestionById")
	public boolean updateQuestionById(int id, String question) {
		UpdateWrapper<SprdFaqList> updateWrapper = new UpdateWrapper<SprdFaqList>();
		updateWrapper.eq("id", id).set("question", question);
		return iSprdFaqListService.update(updateWrapper);
	}

	@GetMapping(value = "/findPageAll")
	public IPage findPageAll(int pageNum, int pageSize) {
		IPage<SprdFaqList> page = new Page<SprdFaqList>(pageNum, pageSize);
		QueryWrapper<SprdFaqList> wrapper = new QueryWrapper<SprdFaqList>();
		wrapper.eq("cate2", 9);
		return iSprdFaqListService.page(page,wrapper);
	}
	
	@GetMapping(value = "/valiTransactional")
	public void valiTransactional() {
			iSprdFaqListService.valiTransactional();
	}

}
