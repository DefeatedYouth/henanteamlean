package com.team.vo.work;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单日志报表查询")
public class WorkCalendarLogReportListVo {
    @ApiModelProperty("班组名称")
    private String teamName;
    @ApiModelProperty("人员ID")
    private Integer memberId;
    @ApiModelProperty("人员名称")
    private String memberName;
    @ApiModelProperty("ERP编号")
    private String erpNo;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("天数据")
    private List<DayInfo> dayInfos;

    @Data
    @ApiModel(value="",description="工单日志:天数据")
    public static class DayInfo{
        @ApiModelProperty("天数:20220722")
        private String dayAt;
        @ApiModelProperty("工作内容")
        private String content;
    }

}
