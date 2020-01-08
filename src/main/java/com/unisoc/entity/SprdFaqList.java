package com.unisoc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
public class SprdFaqList implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("number")
    private String number;

    @TableField("cate1")
    private String cate1;

    @TableField("cate2")
    private String cate2;

    @TableField("cate3")
    private String cate3;

    @TableField("cate4")
    private String cate4;

    @TableField("cate5")
    private String cate5;

    @TableField("chip")
    private String chip;

    /**
     * AP版本
     */
    @TableField("version")
    private String version;

    @TableField("modemVersion")
    private String modemVersion;

    @TableField("wcnVersion")
    private String wcnVersion;

    @TableField("gnssVersion")
    private String gnssVersion;

    @TableField("androidVersion")
    private String androidVersion;

    @TableField("linuxVersion")
    private String linuxVersion;

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 关键字
     */
    @TableField("faqkey")
    private String faqkey;

    /**
     * 问题
     */
    @TableField("question")
    private String question;

    /**
     * 答案
     */
    @TableField("content")
    private String content;

    /**
     * 1. 审核通过  0， 未审核 3，驳回  2 草稿
     */
    @TableField("status")
    private String status;

    @TableField("username")
    private String username;

    @TableField("adddatetime")
    private Date adddatetime;

    @TableField("editusername")
    private String editusername;

    @TableField("editdatetime")
    private Date editdatetime;

    @TableField("approveDate")
    private Date approveDate;

    /**
     * 审批人
     */
    @TableField("owner")
    private String owner;

    @TableField("crnumber")
    private String crnumber;

    @TableField("reason")
    private String reason;

    /**
     * 1:正常 0:下架
     */
    @TableField("alive")
    private String alive;

    @TableField("aliveDate")
    private String aliveDate;

    @TableField("aliveUser")
    private String aliveUser;


}
