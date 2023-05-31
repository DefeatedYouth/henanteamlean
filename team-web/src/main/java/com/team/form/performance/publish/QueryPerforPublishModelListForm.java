package com.team.form.performance.publish;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.team.common.exception.CheckException;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author zhangm
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="查询班员绩效评价标准对象信息",description="查询班员绩效评价标准对象信息")
public class QueryPerforPublishModelListForm extends BaseRequest {

    /**
     * 生产者id
     */
    @ApiModelProperty(value = "用户编码", name = "productorId")
    private String productorId;

    /**
     * 年份
     */
    @ApiModelProperty(value = "年份(初始化传空)", name = "year")
    private String year;

    /**
     * 月份
     */
    @ApiModelProperty(value = "月份：01，02，03，...，12", name = "month")
    private String month;

    /**
     * 季度
     */
    @ApiModelProperty(value = "季度：1第一季度，2第二季度，3第三季度，4第四季度", name = "quarter")
    private Integer quarter;

    @Override
    public boolean verifyParam() {
        Assert.notEmpty(productorId,"用户编码不能为空");
        // 如果年份不为空，月份和季度必须选择一个
        if(StrUtil.isNotEmpty(year))
        {
            // 月份和季度必须选择一个
            if(StrUtil.isEmpty(month) && quarter == 0)
            {
                throw new CheckException("月份和季度必须选择一个",new Exception("月份和季度必须选择一个"));
            }
            // 月份和季度只能选择一个
            if(StrUtil.isNotEmpty(month) && ObjectUtil.isNotEmpty(quarter) && quarter > 0)
            {
                throw new CheckException("月份和季度只能选择一个",new Exception("月份和季度只能选择一个"));
            }
        }
        return true;
    }
}
