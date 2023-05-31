package com.team.performance.dto.monitorModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**查询班长标准绩效对象信息
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
public class QueryMonitorPerforModelRuleListDto {

    /**
     * 是否上级派发;1：是，2：否
     */
    private Integer source;

    /**

    /**
     * 班组编码
     */
    private String teamCode;

    /**
     *  生产者id
     */
    private String productorId;
}
