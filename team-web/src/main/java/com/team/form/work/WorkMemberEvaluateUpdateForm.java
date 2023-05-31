package com.team.form.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 工单人员查询
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单人员修改--人员评价页")
public class WorkMemberEvaluateUpdateForm extends BaseRequest {
    @ApiModelProperty("工单表No")
    private String workNo;
    @ApiModelProperty("人员表ID")
    private Integer id;
    @ApiModelProperty("实际开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualStartTime;
    @ApiModelProperty("实际结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date actualEndTime;
    @ApiModelProperty("工作评价0达标、1未达标、2超预期")
    private Integer workEvaluate;
    @ApiModelProperty("系统计算积分")
    private String totalIntegral;
    @ApiModelProperty("调整积分")
    private String adjustIntegral;
    @ApiModelProperty("最终积分")
    private String finalIntegral;

    @Override
    public boolean verifyParam() {
        Assert.notNull(id, "人员表ID不能为空");
        return true;
    }
}
