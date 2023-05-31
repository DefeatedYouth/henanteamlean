package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Date: 2022/7/27 14:53
 * @Version: 1.0
 */
@Data
@ApiModel(value = "",description = "学习文件展示")
public class StudyDocumentStudyListForm extends BaseRequest {
    @ApiModelProperty("文件名称")
    private String documentName;
    @ApiModelProperty("标签")
    private String label;
    @ApiModelProperty("是否学习")
    private Integer isStudy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @ApiModelProperty("起始日期")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone = "GMT+8")
    @ApiModelProperty("结束日期")
    private Date endTime;

    @ApiModelProperty("页数")
    private int pageNum;
    @ApiModelProperty("行数")
    private int pageRows;

    @Override
    public boolean verifyParam() {
        Assert.notNull(pageRows, "页大小不能为空");
        Assert.notNull(pageNum, "页数不能为空");
        return true;
    }

}
