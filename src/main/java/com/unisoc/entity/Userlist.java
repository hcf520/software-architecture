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
 * @since 2019-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_userlist")
public class Userlist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("Account")
    private String Account;

    /**
     * 工号
     */
    @TableField("SN")
    private String sn;

    @TableField("EnglishName")
    private String EnglishName;

    @TableField("ChineseName")
    private String ChineseName;

    /**
     * 座位号
     */
    @TableField("SeatNumber")
    private String SeatNumber;

    /**
     * 公司代码
     */
    @TableField("CompID")
    private String CompID;

    @TableField("Fname")
    private String Fname;

    @TableField("Lname")
    private String Lname;

    /**
     * 1级部门代码
     */
    @TableField("Dep1")
    private String Dep1;

    /**
     * 1级部门中文代码
     */
    @TableField("DepID1")
    private String DepID1;

    /**
     * 1级部门负责人工号
     */
    @TableField("DepHead1")
    private String DepHead1;

    /**
     * 2级部门代码
     */
    @TableField("Dep2")
    private String Dep2;

    /**
     * 2级部门中文代码
     */
    @TableField("DepID2")
    private String DepID2;

    /**
     * 2级部门负责人工号
     */
    @TableField("DepHead2")
    private String DepHead2;

    /**
     * 3级部门代码
     */
    @TableField("Dep3")
    private String Dep3;

    /**
     * 3级部门中文代码
     */
    @TableField("DepID3")
    private String DepID3;

    /**
     * 3级部门负责人工号
     */
    @TableField("DepHead3")
    private String DepHead3;

    /**
     * 4级部门代码
     */
    @TableField("Dep4")
    private String Dep4;

    /**
     * 4级部门中文名称
     */
    @TableField("DepID4")
    private String DepID4;

    /**
     * 4级部门负责人工号
     */
    @TableField("DepHead4")
    private String DepHead4;

    /**
     * 职位代码
     */
    @TableField("JobID")
    private String JobID;

    /**
     * 职位英文名
     */
    @TableField("JobName")
    private String JobName;

    /**
     * 职位中文名
     */
    @TableField("JobCName")
    private String JobCName;

    /**
     * 主管
     */
    @TableField("Reportto")
    private String Reportto;

    /**
     * 主管的主管
     */
    @TableField("Reportto2")
    private String Reportto2;

    /**
     * 分机号
     */
    @TableField("ext")
    private String ext;

    /**
     * 手机号
     */
    @TableField("Cellphone")
    private String Cellphone;

    /**
     * 游标
     */
    @TableField("stamp")
    private String stamp;

    /**
     * 地区
     */
    @TableField("Location")
    private String Location;

    /**
     * 入职日期
     */
    @TableField("StartDate")
    private String StartDate;

    /**
     * 试用结束日期
     */
    @TableField("ProbationConfDate")
    private String ProbationConfDate;

    /**
     * HRBP工号
     */
    @TableField("BP")
    private String bp;

    /**
     * 员工类型代码：NORM
     */
    @TableField("EmpType")
    private String EmpType;

    /**
     * 职级代码
     */
    @TableField("JobgradeID")
    private String JobgradeID;

    /**
     * 显示名称
     */
    @TableField("DisplayName")
    private String DisplayName;

    /**
     * 试用期（月）
     */
    @TableField("Probterm")
    private String Probterm;

    /**
     * 员工类型代码
     */
    @TableField("Expr1")
    private String Expr1;

    /**
     * 状态代码
     */
    @TableField("Status")
    private String Status;

    /**
     * 地域类型：A2-Expatriate
     */
    @TableField("EmpCategory")
    private String EmpCategory;

    /**
     * 户口承诺书：1-有；0-无
     */
    @TableField("Registered")
    private String Registered;

    /**
     * 相片
     */
    @TableField("Photo")
    private String Photo;

    /**
     * 离职日期
     */
    @TableField("Leadate")
    private String Leadate;

    /**
     * SiteHR
     */
    @TableField("SiteHR")
    private String SiteHR;

    /**
     * 实际工作地点
     */
    @TableField("ActualWA")
    private String ActualWA;

    /**
     * 排班代码
     */
    @TableField("Workschedules")
    private String Workschedules;

    /**
     * 证件号码
     */
    @TableField("identification")
    private String identification;

    /**
     * 公司邮箱
     */
    @TableField("MAIL")
    private String mail;

    /**
     * 姓名(Prefered Name)
     */
    @TableField("PName")
    private String PName;


}
