package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import com.team.vo.basicinfo.StudyDocumentPersonVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "修改学习文件")
public class StudyDocumenDeleteForm extends BaseRequest {

    @ApiModelProperty("文件id")
    private Integer documentId;

    @Override
    public boolean verifyParam() {
        Assert.notNull(documentId, "学习人员List不能为空");
        return true;
    }
}



