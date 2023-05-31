package com.team.basic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 个人风采
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_culture_personal_style")
public class CulturePersonalStyleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 员工ID
     */
    @TableField("EMPLOYEE_ID")
    private Integer employeeId;

    /**
     * 员工姓名
     */
    @TableField("EMPLOYEE_NAME")
    private String employeeName;

    /**
     * 员工岗位
     */
    @TableField("EMPLOYEE_POST")
    private String employeePost;

    /**
     * 员工简介
     */
    @TableField("EMPLOYEE_INTRODUCTION")
    private String employeeIntroduction;

    /**
     * 头像文件路径
     */
    @TableField("HEAD_SHOT_PATH")
    private String headShotPath;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField("CREATED_TIME")
    private Date createdTime;

    /**
     * 更新人
     */
    @TableField("UPDATED_BY")
    private String updatedBy;

    /**
     * 是否删除
     */
    @TableField("IS_DELETE")
    private Integer isDelete;


}
