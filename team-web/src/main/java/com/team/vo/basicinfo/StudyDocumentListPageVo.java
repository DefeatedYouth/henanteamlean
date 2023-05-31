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
@ApiModel(value = "",description = "学习文件列表展示")
public class StudyDocumentListPageVo {
    @ApiModelProperty("列表数据")
    private List<StudyDocumentListVo> studyDocumentLists;
    @ApiModelProperty("页数")
    private long pageNum;
    @ApiModelProperty("页大小")
    private long pageSize;
    @ApiModelProperty("总行数")
    private long total;
}
