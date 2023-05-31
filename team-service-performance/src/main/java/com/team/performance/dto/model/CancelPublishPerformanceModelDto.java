package com.team.performance.dto.model;

import lombok.Data;

import java.util.List;

/**
 * 取消发布专责绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class CancelPublishPerformanceModelDto {

    /**
     * 流水号
     */
    private List<Integer> ids;

    /**
     * 更新人
     */
    private String updatedBy;

}
