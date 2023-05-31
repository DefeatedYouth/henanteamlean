package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/30$
 */
@Data
public class WorkMemberListQueryDto {
    private String workNo;

    private Integer leaderFlag;

    private Integer id;

    private String memberId;

    private String memberName;

    private Date actualStartTime;

    private Date actualEndTime;
    //是否达标
    private Integer workEvaluate;
    //"出勤日期json [ \"2022-07-21\", \"2022-07-22\"]"
    private String workDays;
    //系统计算积分
    private String totalIntegral;
    //调整积分
    private String finalIntegral;
    //最终积分
    private String adjustIntegral;

}
