package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单首页列表")
public class WorkHomePageQueryForm extends BaseRequest {
    @ApiModelProperty("查询类型0全部,1超期,2待归档,3执行中,4待执行")
    private Integer queryType;
    @ApiModelProperty("页大小")
    private Integer pageSize;
    @ApiModelProperty("页数")
    private Integer pageNum;

    @Override
    public boolean verifyParam() {
        Assert.notNull(pageSize, "页大小不能为空");
        Assert.notNull(pageNum, "页数不能为空");
        Assert.notNull(queryType, "查询类型不能为空");
        return true;
    }
}
