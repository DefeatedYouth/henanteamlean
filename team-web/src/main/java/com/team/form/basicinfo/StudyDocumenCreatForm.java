package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import com.team.vo.basicinfo.StudyDocumentPersonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "添加学习文件")
public class StudyDocumenCreatForm extends BaseRequest {

    @ApiModelProperty("文件名称")
    private String documentName;
    @ApiModelProperty("标签")
    private String label;
    @ApiModelProperty("学习人员")
    private List<StudyDocumentPersonVo> studyPerson;
    @ApiModelProperty("学习要求")
    private String requirement;
    @ApiModelProperty("已打卡")
    private String alreadyStudy;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("发布日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    private Date releaseDate;
    @ApiModelProperty("附件")
    private String accessory;
    @ApiModelProperty("备注")
    private String remark;


    @Override
    public boolean verifyParam() {
        Assert.notEmpty(documentName, "名称不能为空");
        Assert.notEmpty(label, "标签不能为空");
        Assert.notEmpty(requirement, "学习要求不能为空");
        Assert.notEmpty(remark, "备注不能为空");
        return true;
    }
}



