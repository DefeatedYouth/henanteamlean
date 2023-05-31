package com.team.performance.dto.monitorModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 取消发布班长绩效对象信息
 * @author zhangm
 * @date 2022/7/27$
 */
@Data
public class CancelPublishMonitorPerforModelDto {

    /**
     * 流水号
     */
    private List<Integer> ids;

    /**
     *  修改人名称
     */
    private String updatedBy;

}
