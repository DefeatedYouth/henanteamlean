package com.team.work.service.impl;

import cn.hutool.core.date.DateUtil;
import com.team.work.entity.WorkInfoVo;
import com.team.work.entity.WorkRecordVo;
import com.team.work.dao.WorkRecordMapper;
import com.team.work.service.WorkRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 工单操作记录表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
@Slf4j
public class WorkRecordServiceImpl extends ServiceImpl<WorkRecordMapper, WorkRecordVo> implements WorkRecordService {

    @Override
    public Boolean saveRecord(WorkInfoVo workInfo, Integer operate, String content) {
        WorkRecordVo recordVo = new WorkRecordVo();
        recordVo.setWorkNo(workInfo.getWorkNo());
        recordVo.setWorkStatus(workInfo.getWorkStatus());
        recordVo.setOperateType(operate);
        //fixme
        recordVo.setCreatedBy("admin");
        recordVo.setCreatedTime(new Date());
        recordVo.setOperateContent(content);
        try {
            this.baseMapper.insert(recordVo);
            return true;
        }catch (Exception e){
            log.error("saveRecord记录保存异常"+e);
            return false;
        }
    }
}
