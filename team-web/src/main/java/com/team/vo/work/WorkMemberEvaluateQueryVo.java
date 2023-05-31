package com.team.vo.work;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="",description="工单人员信息--拆分评价页面")
public class WorkMemberEvaluateQueryVo {
    @ApiModelProperty("工单编码")
    private String workNo;
    @ApiModelProperty("人员表ID")
    private Integer id;
    @ApiModelProperty("成员ID")
    private String memberId;
    @ApiModelProperty("成员名称")
    private String memberName;
    @ApiModelProperty("是否负责人(0不是、1是)")
    private Integer leaderFlag;
    @ApiModelProperty("实际开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date actualEndTime;
    @ApiModelProperty("系统计算积分")
    private String totalIntegral;
    @ApiModelProperty("调整积分")
    private String adjustIntegral;
    @ApiModelProperty("最终积分")
    private String finalIntegral;
    @ApiModelProperty("工作评价0达标、1未达标、2超预期")
    private Integer workEvaluate;
}
