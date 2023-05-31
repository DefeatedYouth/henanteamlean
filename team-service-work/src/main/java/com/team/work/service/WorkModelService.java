package com.team.work.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.work.entity.WorkModelVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工单模板表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkModelService extends IService<WorkModelVo> {

    /**
     * 查模型列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<WorkModelVo> queryList(Integer pageNum, Integer pageSize);

    /**
     * 保存工单模型
     * @param entity
     * @return
     */
    Boolean saveWorkModel(WorkModelVo entity);

    /**
     * 修改工单模型
     * @param entity
     * @return
     */
    Boolean updateWorkModel(WorkModelVo entity);

    /**
     * 删除工单模型
     * @param entity
     * @return
     */
    Boolean deleteWorkModel(WorkModelVo entity);

    /**
     * 查询可推送模板
     * @return
     */
    List<WorkModelVo> queryPushModel();
}
