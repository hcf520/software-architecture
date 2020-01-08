package com.unisoc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hcf
 * @since 2019-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

	/**
	 * 用户ID账号
	 */
	@TableField("userid")
	private String userid;

	/**
	 * 用户密码
	 */
	@TableField("userpwd")
	private String userpwd;

	/**
	 * 用户名
	 */
	@TableField("username")
	private String username;

	/**
	 * 用户电子邮箱
	 */
	@TableField("email")
	private String email;

	/**
	 * 用户手机号
	 */
	@TableField("phone")
	private String phone;

	/**
	 * 系统角色
	 */
	@TableField("roleId")
	private Integer roleId;

}
