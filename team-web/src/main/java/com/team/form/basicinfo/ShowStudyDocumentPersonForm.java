package com.team.form.basicinfo;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value="",description="查看文件学习相关人员")
public class ShowStudyDocumentPersonForm extends BaseRequest {

    @ApiModelProperty("学习文件id")
    private Integer studyDocumentId;

    @Override
    public boolean verifyParam() {
        Assert.notNull(studyDocumentId,"学习文件id不能为空");
        return true;
    }
}
