package com.team.work.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 *
 * @author QianT
 * @date 2022/8/4$
 */
@Data
public class ExecuteWorkAndMember {
    /**
     * 成员ID
     */
    private String memberId;

    /**
     * 成员名称
     */
    private String memberName;

    /**
     * 工作日期[ "20220721", "20220722"]
     */
    private String workDays;

    /**
     * 工单编码
     */
    private String workNo;

    /**
     * 工作类型(0班组建设、1员工培训、2安全活动、3现场生产、4基建验收、5基础管理、6科技创新、7综合事务、8临时任务、9其他)
     */
    private Integer workType;

    /**
     * 工作性质(0出勤、1出差、2培训、3借调)
     */
    private Integer workNature;

}
