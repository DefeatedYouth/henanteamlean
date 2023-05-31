package com.team.basic.service;

import com.team.basic.dto.CulturalCommentsSuggestionsDto;
import com.team.basic.entity.CulturalCommentsSuggestionsVo;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 意见建议表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalCommentsSuggestionsService extends IService<CulturalCommentsSuggestionsVo> {

    Integer addCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

    Integer updateCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

    Integer deleteCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

    List<CulturalCommentsSuggestionsVo> queryCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

    void exportExcelCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto, HttpServletResponse response);

}
