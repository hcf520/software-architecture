package com.unisoc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hcf
 * @since 2019-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_permissions")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "permissionsId", type = IdType.AUTO)
    private Integer permissionsId;

    /**
     * 权限名称
     */
    @TableField("permissions_name")
    private String permissionsName;

    /**
     * 权限控制区域
     */
    @TableField("control_area")
    private String controlArea;

    /**
     * 资源路径
     */
    @TableField("URI")
    private String uri;
    
    /**
     * 所属父级权限
     */
    @TableField("parent_permissionId")
    private Integer parentPermissionid;

    /**
     * 0：正常；1：失效
     */
    @TableField("status")
    private Integer status;

    /**
     * 权限描述
     */
    @TableField("description")
    private String description;


}
