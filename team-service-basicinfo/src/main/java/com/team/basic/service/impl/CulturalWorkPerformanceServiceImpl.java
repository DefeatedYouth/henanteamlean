package com.team.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.team.basic.dto.CulturalWorkPerformanceDto;
import com.team.basic.entity.CulturalCommentsSuggestionsVo;
import com.team.basic.entity.CulturalWorkPerformanceVo;
import com.team.basic.dao.CulturalWorkPerformanceMapper;
import com.team.basic.service.CulturalWorkPerformanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.common.exception.CheckException;
import com.team.common.util.DateUtil;
import com.team.common.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 工作业绩表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class CulturalWorkPerformanceServiceImpl extends ServiceImpl<CulturalWorkPerformanceMapper, CulturalWorkPerformanceVo> implements CulturalWorkPerformanceService {

    @Resource
    private CulturalWorkPerformanceMapper culturalWorkPerformanceMapper;


    @Override
    public Integer addCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto) {
        culturalWorkPerformanceDto.setIs_delete(0);
        culturalWorkPerformanceDto.setSupportingMaterialsId("workPerformance-"+ UUID.randomUUID().toString());
        culturalWorkPerformanceDto.setCreatedId("1");        //后续重写获得创建人方法
        culturalWorkPerformanceDto.setCreatedBy("zp");
        culturalWorkPerformanceDto.setCreatedTime(DateUtil.getDate());
        int i = culturalWorkPerformanceMapper.addCulturalWorkPerformance(culturalWorkPerformanceDto);
        return i;
    }

    @Override
    public Integer updateCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto) {
        CulturalWorkPerformanceVo culturalWorkPerformanceVo = new CulturalWorkPerformanceVo();
        BeanUtil.copyProperties(culturalWorkPerformanceDto,culturalWorkPerformanceVo);
        culturalWorkPerformanceVo.setUpdatedTime(DateUtil.getDate());
        culturalWorkPerformanceVo.setUpdatedId("1");        //后续重写获得创建人方法
        culturalWorkPerformanceVo.setUpdatedBy("zp");
        int i = culturalWorkPerformanceMapper.updateById(culturalWorkPerformanceVo);
        return i;
    }

    @Override
    public Integer deleteCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto) {
        List<Integer> idList = culturalWorkPerformanceDto.getDeleteIdList();
        int i = culturalWorkPerformanceMapper.deleteBatchIds(idList);
        return i;
    }

    @Override
    public List<CulturalWorkPerformanceVo> queryCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto) {
        return culturalWorkPerformanceMapper.queryCulturalWorkPerformance(culturalWorkPerformanceDto);
    }

    @Override
    public void exportExcelCulturalWorkPerformance(CulturalWorkPerformanceDto culturalWorkPerformanceDto, HttpServletResponse response) {
        List<CulturalWorkPerformanceVo> culturalWorkPerformanceVoList = culturalWorkPerformanceMapper.queryCulturalWorkPerformance(culturalWorkPerformanceDto);
        List<List<String>> dataList = new ArrayList<>();
        for(CulturalWorkPerformanceVo culturalWorkPerformanceVo : culturalWorkPerformanceVoList){
            List<String> list = new ArrayList<>();
            list.add(DateUtil.date2String(culturalWorkPerformanceVo.getStartTime(),"yyyy-MM-dd"));
            list.add(DateUtil.date2String(culturalWorkPerformanceVo.getEndTime(),"yyyy-MM-dd"));
            list.add(culturalWorkPerformanceVo.getPerformanceName());
            list.add(culturalWorkPerformanceVo.getWorkContent());
            list.add(culturalWorkPerformanceVo.getPersonnelName());
            list.add(culturalWorkPerformanceVo.getRemarks());
            dataList.add(list);
        }
        List<String> titleList = Arrays.asList("序号","开始日期", "结束日期", "业绩名称", "主要工作内容或重大项目内容","参与人员","备注");
        ExcelUtil.uploadExcelAboutUser("工作业绩信息.xlsx",titleList,dataList,response);
    }
}
