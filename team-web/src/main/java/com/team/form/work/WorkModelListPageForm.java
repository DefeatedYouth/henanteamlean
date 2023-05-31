package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单模型分页列表")
public class WorkModelListPageForm extends BaseRequest {
    @ApiModelProperty("页大小")
    private Integer pageSize;
    @ApiModelProperty("页数")
    private Integer pageNum;
    @Override
    public boolean verifyParam() {
        Assert.notNull(pageSize, "页大小不能为空");
        Assert.notNull(pageNum, "页数不能为空");
        return true;
    }
}
