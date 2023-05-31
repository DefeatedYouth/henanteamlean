package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import com.team.vo.basicinfo.TeamAboutHonorInfoVo;
import com.team.vo.basicinfo.TeamAboutStationInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "班组简介维护")
public class TeamAboutEditForm extends BaseRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("成立时间")
    private Date formed;
    @ApiModelProperty("班级荣誉id")
    private List<TeamAboutHonorInfoVo> teamHonor;
    @ApiModelProperty("班组职责")
    private String teamDuty;
    @ApiModelProperty("所辖站id")
    private List<TeamAboutStationInfoVo> theStation;



    @Override
    public boolean verifyParam() {
        Assert.notNull(formed,"成立时间不能为空");
        Assert.notEmpty(teamDuty,"班组职责不能为空");
        return true;
    }
}
