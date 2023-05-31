package com.team.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.team.basic.dto.CulturalCommentsSuggestionsDto;
import com.team.basic.entity.CulturalCommentsSuggestionsVo;
import com.team.basic.dao.CulturalCommentsSuggestionsMapper;
import com.team.basic.service.CulturalCommentsSuggestionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.common.util.DateUtil;
import com.team.common.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * <p>
 * 意见建议表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class CulturalCommentsSuggestionsServiceImpl extends ServiceImpl<CulturalCommentsSuggestionsMapper, CulturalCommentsSuggestionsVo> implements CulturalCommentsSuggestionsService {

    @Resource
    private CulturalCommentsSuggestionsMapper culturalCommentsSuggestionsMapper;

    @Override
    public Integer addCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto) {
        culturalCommentsSuggestionsDto.setIs_delete(0);
        culturalCommentsSuggestionsDto.setEnclosureId("commentSuggestion-"+ UUID.randomUUID().toString());
        culturalCommentsSuggestionsDto.setSeconderId(StringUtils.join(culturalCommentsSuggestionsDto.getSecondersId(),","));
        culturalCommentsSuggestionsDto.setSeconderName(StringUtils.join(culturalCommentsSuggestionsDto.getSecondersName(),","));
        culturalCommentsSuggestionsDto.setCreatedId("1");        //后续重写获得创建人方法
        culturalCommentsSuggestionsDto.setCreatedBy("zp");
        culturalCommentsSuggestionsDto.setCreatedTime(DateUtil.getDate());
        int i = culturalCommentsSuggestionsMapper.addCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        return i;
    }

    @Override
    public Integer updateCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto) {
        CulturalCommentsSuggestionsVo culturalCommentsSuggestionsVo = new CulturalCommentsSuggestionsVo();
        BeanUtil.copyProperties(culturalCommentsSuggestionsDto,culturalCommentsSuggestionsVo);
        culturalCommentsSuggestionsVo.setSeconderId(StringUtils.join(culturalCommentsSuggestionsDto.getSecondersId(),","));
        culturalCommentsSuggestionsVo.setSeconderName(StringUtils.join(culturalCommentsSuggestionsDto.getSecondersName(),","));
        culturalCommentsSuggestionsVo.setUpdatedTime(DateUtil.getDate());
        culturalCommentsSuggestionsVo.setUpdatedId("1");        //后续重写获得创建人方法
        culturalCommentsSuggestionsVo.setUpdatedBy("zp");
        int i = culturalCommentsSuggestionsMapper.updateById(culturalCommentsSuggestionsVo);
        return i;
    }

    @Override
    public Integer deleteCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto) {
        List<Integer> idList = culturalCommentsSuggestionsDto.getDeleteIdList();
        int i = culturalCommentsSuggestionsMapper.deleteBatchIds(idList);
        return i;
    }

    @Override
    public List<CulturalCommentsSuggestionsVo> queryCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto) {
        return culturalCommentsSuggestionsMapper.queryCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
    }

    @Override
    public void exportExcelCulturalCommentsSuggestions(CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto, HttpServletResponse response) {
        List<CulturalCommentsSuggestionsVo> culturalCommentsSuggestionsVoList = culturalCommentsSuggestionsMapper.queryCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        List<List<String>> dataList = new ArrayList<>();
        for(CulturalCommentsSuggestionsVo culturalCommentsSuggestionsVo : culturalCommentsSuggestionsVoList){
            List<String> list = new ArrayList<>();
            list.add(culturalCommentsSuggestionsVo.getProposalType());
            list.add(culturalCommentsSuggestionsVo.getCreatedBy());
            list.add(culturalCommentsSuggestionsVo.getSeconderName());
            list.add(culturalCommentsSuggestionsVo.getProposedTheme());
            list.add(DateUtil.date2String(culturalCommentsSuggestionsVo.getCreatedTime(),"yyyy-MM-dd"));
            list.add(culturalCommentsSuggestionsVo.getAdopted().toString());
            list.add(culturalCommentsSuggestionsVo.getReason());
            list.add(culturalCommentsSuggestionsVo.getDepartment());
            dataList.add(list);
        }

        List<String> titleList = Arrays.asList("序号","提议类型", "提出人", "附议人", "提议主题","提出日期", "是否采纳", "采纳/不采纳原因", "创建人");
        ExcelUtil.uploadExcelAboutUser("意见建议信息.xlsx",titleList,dataList,response);
    }
}
