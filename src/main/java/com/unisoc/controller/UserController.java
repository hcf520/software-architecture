package com.unisoc.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Producer;
import com.unisoc.config.security.UserLoginToken;
import com.unisoc.entity.Permissions;
import com.unisoc.entity.User;
import com.unisoc.model.Result;
import com.unisoc.service.IUserService;
import com.unisoc.utils.GlobalConst;
import com.unisoc.utils.RedisUtil;
import com.unisoc.utils.ResultUtil;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author hcf
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private Producer producer;

	@Autowired
	private IUserService iUserService;

	@Autowired
	private RedisUtil redisUtil;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public Result<Map<String, Object>> login(User user, Integer vercode, HttpServletRequest request) {
		HttpSession session = request.getSession();
		log.info("用户会话唯一ID："+session.getId());
		Integer sessionVercode = (Integer) session.getAttribute(GlobalConst.VALI_SESSION_KEY.getKey());
		if (sessionVercode!=null&&(vercode.intValue() == sessionVercode.intValue())) {
			User userResult = iUserService.userLogin(user);
			if (userResult != null && !userResult.equals("")) {
				Map<String, String> mapToken = new HashMap<String, String>();
				mapToken.put("token", iUserService.getToken(user,session.getId()));
				List<Permissions> permissionsList = iUserService.getUserPermissions(userResult.getRoleId());
				boolean userTokenFlag = iUserService.saveUserToken(user.getUserid(),mapToken.get("token"));
				boolean userInfoFlag = iUserService.saveUserInfo(user.getUserid(), userResult);
				boolean userPermissionsFlag = iUserService.saveUserPermissions(user.getUserid(), permissionsList);
				log.info("缓存状态："+userTokenFlag);
				return userTokenFlag&&userPermissionsFlag&&userInfoFlag?ResultUtil.success(200, "登录成功！", mapToken):ResultUtil.error(500, "登录失败！");
			} else {
				return ResultUtil.error(500, "登录失败！");
			}
		}
		return ResultUtil.error(500, "登录失败！");
	}

	@PostMapping("/register")
	public Result<Map<String, Object>> register(User user) {
		return iUserService.userRegister(user) ? ResultUtil.success(200, "注册成功！", user.getUsername())
				: ResultUtil.error(500, "注册失败！");
	}

	@RequestMapping("/number.jpg")
	public void number(HttpServletResponse response, HttpSession httpSession) throws IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		// 个位数字相加
		String s1 = text.substring(0, 1);
		String s2 = text.substring(1, 2);
		int count = Integer.valueOf(s1).intValue() + Integer.valueOf(s2).intValue();// 放入session参数
		httpSession.setAttribute(GlobalConst.VALI_SESSION_KEY.getKey(), count);
		// 生成图片验证码
		BufferedImage image = producer.createImage(s1 + "+" + s2 + "=?");
		// 保存 redis key 自己设置
		// stringRedisTemplate.opsForValue().set("",String.valueOf(count));
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}

	@GetMapping("/test01")
	public Object test01(HttpSession httpSession) {
		List list = new ArrayList<>();
		list.add(httpSession.getAttribute(GlobalConst.USER_SESSION_KEY.getKey()));
		list.add(redisUtil.get("userSession"));
		return list;
	}
}
