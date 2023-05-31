package com.team.performance.dto.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 发布专责绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class BatchChoosePerformanceModelTeamDto {

    /**
     * 绩效方案流水号
     */
    private Integer modelId;

    /**
     * 班组编码(,隔开)
     */
    private String teamCodes;

    /**
     * 班组名称(,隔开)
     */
    private String teamNames;

    /**
     * 更新人
     */
    private String updatedBy;

}
