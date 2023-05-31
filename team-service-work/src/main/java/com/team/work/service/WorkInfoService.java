package com.team.work.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.work.dto.*;
import com.team.work.entity.WorkInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team.work.entity.WorkModelVo;

import java.util.List;

/**
 * <p>
 * 工单表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkInfoService extends IService<WorkInfoVo> {

    /**
     * 查询列表分页
     * @param dto
     * @return
     */
    Page<WorkListPageResultDto> queryWorkList(WorkListPageDto dto);

    /**
     * 创建工单
     * @param dto
     * @return
     */
    String createWork(WorkInfoCreateDto dto);

    /**
     * 删除工单
     * @param id
     * @return
     */
    Boolean deleteWork(Integer id);

    /**
     * 取消工单
     * @param id
     * @return
     */
    Boolean cancelWork(Integer id);

    /**
     * 推送工单
     * @param id
     * @return
     */
    Boolean pushWork(Integer id);

    /**
     * 归档工单
     * @param id
     * @return
     */
    Boolean archiveWork(Integer id);

    /**
     * 更新工单
     * @param dto
     * @return
     */
    Boolean updateWork(WorkUpdateDto dto);

    /**
     * 查询工单是否归档
     * @param collect
     * @return
     */
    List<String> checkHadArchive(List<String> collect);

    /**
     * 首页列表
     * @param dto
     * @return
     */
    Page<WorkInfoVo> queryHomeList(WorkHomePageDto dto);

    /**
     * 首页进度控制
     * @param dto
     * @return
     */
    Boolean homeUpdate(WorkHomeUpdateDto dto);

    /**
     * 根据工单号查工单
     */
    WorkInfoVo queryWorkByNo(String workNo);

    /**
     * 查询执行中工单与人员
     * @return
     */
    List<ExecuteWorkAndMember> queryExecuteWorkAndMember();

    /**
     * 根据模板创建工单
     * @param pushModel
     * @return
     */
    Boolean createWorkByModel(List<WorkModelVo> pushModel);
}
