package com.team.work.service;

import com.team.work.entity.WorkInfoVo;
import com.team.work.entity.WorkRecordVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工单操作记录表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkRecordService extends IService<WorkRecordVo> {

    /**
     * 保存工单记录
     * @param workInfo
     * @param aNew
     * @param s
     * @return
     */
    Boolean saveRecord(WorkInfoVo workInfo, Integer operate, String record);
}
