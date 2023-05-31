package com.team.performance.dto.monitorModel;

import lombok.Data;

import java.util.Date;

/**
 * 删除班长绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class DeleteMonitorPerforModelDto {

    /**
     * 流水号
     */
    private Integer id;

    /**
     * 更新人
     */
    private String updatedBy;

}
