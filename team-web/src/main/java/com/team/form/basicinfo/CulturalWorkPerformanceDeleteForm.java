package com.team.form.basicinfo;

import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CulturalWorkPerformanceDeleteForm extends BaseRequest {

    @ApiModelProperty(value = "删除ID")
    private List<Integer> deleteIdList;

    @ApiModelProperty(value = "证明材料删除相关信息")
    private List<FilePathDeleteForm> filePathDeleteFormList;

    @Override
    public boolean verifyParam() {
        return true;
    }
}
