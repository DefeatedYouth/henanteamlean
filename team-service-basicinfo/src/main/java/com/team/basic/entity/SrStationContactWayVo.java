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
 * 所辖站联系方式表
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_sr_station_contact_way")
public class SrStationContactWayVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
      @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 变电站名称
     */
    @TableField("STATION_NAME")
    private String stationName;

    /**
     * 运维班组
     */
    @TableField("OPS_TEAM")
    private String opsTeam;

    /**
     * 运维班组座机
     */
    @TableField("OPS_TELEPHONE")
    private String opsTelephone;

    /**
     * 联系人
     */
    @TableField("CONTACT_PERSON")
    private String contactPerson;

    /**
     * 更新时间
     */
    @TableField("UPDATED_TIME")
    private Date updatedTime;

    /**
     * 联系方式
     */
    @TableField("CONTACT_WAY")
    private String contactWay;

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
