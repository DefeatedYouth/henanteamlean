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
 * 管理人员与班组关系表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sr_admin_team")
public class SrAdminTeamVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 岗位
     */
    @TableField("POST")
    private String post;

    /**
     * 管理员名字
     */
    @TableField("ADMIN_NAME")
    private String adminName;

    /**
     * 所辖班组名称
     */
    @TableField("THE_TEAM_NAME")
    private String theTeamName;

    /**
     * 创建人
     */
    @TableField("CREATED_BY")
    private String createdBy;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;

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
