package com.team.performance.dto.monitorModel;

import lombok.Data;

import java.util.List;

/**
 * 发布班长绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class PublishMonitorPerforModelDto {

    /**
     * 流水号
     */
    private List<Integer> ids;

    /**
     * 更新人
     */
    private String updatedBy;

}
