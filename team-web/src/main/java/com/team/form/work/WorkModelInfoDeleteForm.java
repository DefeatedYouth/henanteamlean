package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工单模板修改
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单模板修改")
public class WorkModelInfoDeleteForm extends BaseRequest {
    @ApiModelProperty("主键id")
    private Integer id;


    @Override
    public boolean verifyParam() {
        Assert.notNull(id,"模板ID不能为空");
        return true;
    }
}
