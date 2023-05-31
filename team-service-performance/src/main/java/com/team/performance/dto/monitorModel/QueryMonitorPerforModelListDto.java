package com.team.performance.dto.monitorModel;

import lombok.Data;

/**分页查询班长绩效对象信息
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
public class QueryMonitorPerforModelListDto {

    /**
     * 是否上级派发;1：是，2：否
     */
    private Integer source;

    /**
     * 指标名称
     */
    private String quateName;

    /**
     * 考核周期;1：月，2：季度
     */
    private Integer assessmentCycle;

    /**
     * 发布状态：1未发布，2发布中,3发布
     */
    private Integer publishState;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 每页大小
     */
    private Integer pageSize;

    /**
     * 班组编码
     */
    private String teamCode;

    /**
     * 生产者id
     */
    private String productorId;

}
