package com.team.vo.work;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单首页分页列表")
public class WorkHomePageQueryListVo {
    @ApiModelProperty("工单ID")
    private Integer id;
    @ApiModelProperty("工单内容")
    private String workContent;
    @ApiModelProperty("工单状态1待执行、2执行中、3已完成、4已归档、5已取消、6超期")
    private Integer workStatus;
    @ApiModelProperty("计划开始时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date planStartTime;
    @ApiModelProperty("计划结束时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date planEndTime;
    @ApiModelProperty("实际开始时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date actualEndTime;
}
