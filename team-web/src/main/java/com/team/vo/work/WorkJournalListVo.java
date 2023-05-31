package com.team.vo.work;

import cn.hutool.core.lang.Assert;
import com.team.common.request.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author QianT
 * @date 2022/7/28$
 */
@Data
@ApiModel(value="",description="工单日志查询")
public class WorkJournalListVo{
    @ApiModelProperty("人员名称")
    private String memberName;
    @ApiModelProperty("天数据")
    private List<DayInfo> dayInfos;

    @Data
    @ApiModel(value="",description="工单日志:天数据")
    public class DayInfo{
        @ApiModelProperty("天数:20220722")
        private String dayAt;
        @ApiModelProperty("工作内容")
        private String workContent;
    }

}
