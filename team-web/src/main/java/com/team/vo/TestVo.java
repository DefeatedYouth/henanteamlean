package com.team.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/27$
 */
@Data
@ApiModel
public class TestVo {
    @ApiModelProperty(value = "ID", name = "id")
    private String id;
}
