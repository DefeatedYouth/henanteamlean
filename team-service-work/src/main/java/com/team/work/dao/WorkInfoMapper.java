package com.team.work.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.work.dto.ExecuteWorkAndMember;
import com.team.work.dto.WorkHomePageDto;
import com.team.work.dto.WorkListPageDto;
import com.team.work.dto.WorkListPageResultDto;
import com.team.work.entity.WorkInfoVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 工单表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkInfoMapper extends BaseMapper<WorkInfoVo> {

    Page<WorkListPageResultDto> queryWorkList(Page<WorkListPageResultDto> producePage, @Param("dto") WorkListPageDto dto);

    Page<WorkInfoVo> queryHomeList(Page<WorkInfoVo> producePage, WorkHomePageDto dto);

    List<ExecuteWorkAndMember> queryExecuteWorkAndMember();

}
