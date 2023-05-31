package com.team.vo.basicinfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author
 * @date 2022/7/27$
 */
@Data
@ApiModel(value = "" ,description = "文件学习分页列表")
public class StudyDocumentStudyVo {
    @ApiModelProperty("列表数据")
    private List<StudyDocumentStudyListVo> studyDocumentStudyListVos;
    @ApiModelProperty("页数")
    private long pageNum;
    @ApiModelProperty("页大小")
    private long pageSize;
    @ApiModelProperty("总行数")
    private long total;

    @ApiModelProperty("已学习条数")
    private int alreadyCount;
    @ApiModelProperty("未学习条数")
    private int notCount;
    @ApiModelProperty("总学习条数")
    private int totalCount;
}
