package com.team.vo.performance;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="历史绩效发布对象信息",description="历史绩效发布对象信息")
public class HistoryPerforModelMainVo {

    /**
     * 指标名称
     */
    @ApiModelProperty(value = "指标名称", name = "quateName")
    private String quateName;

    /**
     * 明细列表
     */
    @ApiModelProperty(value = "明细列表", name = "detailList")
    private List<HistoryPerforModelVo> detailList;
}
