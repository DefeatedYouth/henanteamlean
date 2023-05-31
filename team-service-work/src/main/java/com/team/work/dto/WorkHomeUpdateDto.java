package com.team.work.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author QianT
 * @date 2022/8/3$
 */
@Data
public class WorkHomeUpdateDto {
    private Integer id;
    private Integer workStatus;
    private Integer workEvaluate;
    private Date actualStartTime;
    private Date actualEndTime;
}
