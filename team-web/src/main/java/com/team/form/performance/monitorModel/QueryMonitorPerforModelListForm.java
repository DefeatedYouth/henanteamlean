package com.team.form.performance.monitorModel;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="分页查询班长绩效对象信息",description="分页查询班长绩效对象信息")
public class QueryMonitorPerforModelListForm extends BaseRequest {

    /**
     * 是否上级派发;1：是，2：否
     */
    @ApiModelProperty(value = "是否上级派发;1：是，2：否", name = "resource")
    private Integer source;

    /**
     * 指标名称
     */
    @ApiModelProperty(value = "指标名称", name = "quateName")
    private String quateName;

    /**
     * 考核周期;1：月，2：季度
     */
    @ApiModelProperty(value = "考核周期;1：月，2：季度", name = "assessmentCycle")
    private Integer assessmentCycle;

    /**
     * 发布状态：1未发布，2发布中,3发布
     */
    @ApiModelProperty(value = "发布状态：1未发布，2发布中,3发布", name = "publishState")
    private Integer publishState;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数", name = "pageNum")
    private Integer pageNum;

    /**
     * 每页大小
     */
    @ApiModelProperty(value = "每页大小", name = "pageSize")
    private Integer pageSize;

    /**
     * 生产者id
     */
    @ApiModelProperty(value = "用户编码", name = "productorId")
    private String productorId;

    @Override
    public boolean verifyParam() {
        Assert.notNull(pageNum, "当前页数不能为空");
        Assert.notNull(pageSize, "每页大小不能为空");
        Assert.notEmpty(productorId,"用户编码不能为空");
        return true;
    }
}
