package com.team.basic.dao;

import com.team.basic.dto.CulturalCommentsSuggestionsDto;
import com.team.basic.entity.CulturalCommentsSuggestionsVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 意见建议表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CulturalCommentsSuggestionsMapper extends BaseMapper<CulturalCommentsSuggestionsVo> {

    List<CulturalCommentsSuggestionsVo> queryCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

    Integer addCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto);

}
