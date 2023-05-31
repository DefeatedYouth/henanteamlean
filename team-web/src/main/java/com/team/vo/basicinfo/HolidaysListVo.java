package com.team.vo.basicinfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "",description = "假期列表展示")
public class HolidaysListVo {

    @ApiModelProperty(value = "节假日id")
    private Integer holidaysId;

    @ApiModelProperty(value = "节假日开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "节假日结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "节假日名称")
    private String holidaysName;

    @ApiModelProperty(value = "调休时间")
    private List<String> restDays;

    @ApiModelProperty(value = "节假日天数")
    private Integer DaysOfHolidays;

}
