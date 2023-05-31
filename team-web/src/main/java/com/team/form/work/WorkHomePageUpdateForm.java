package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@ApiModel(value="",description="工单首页更新按钮")
public class WorkHomePageUpdateForm extends BaseRequest {
    @ApiModelProperty("工单ID")
    private Integer id;
    @ApiModelProperty("请求状态2开工、3完成、4归档、5取消")
    private Integer workStatus;
    @ApiModelProperty("工作评价0达标、1未达标、2超预期")
    private Integer workEvaluate;
    @ApiModelProperty("实际开始时间yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date actualEndTime;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "工单ID不能为空");
        return true;
    }
}
