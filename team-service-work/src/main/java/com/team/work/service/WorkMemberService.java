package com.team.work.service;

import com.team.work.dto.WorkEvaluateUpdateDto;
import com.team.work.dto.WorkMemberChangeUpdateDto;
import com.team.work.dto.WorkMemberListQueryDto;
import com.team.work.entity.WorkMemberCheckingVo;
import com.team.work.entity.WorkMemberVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工单人员表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkMemberService extends IService<WorkMemberVo> {
    /**
     * 查询人员变动列表
     * @param workNo
     * @return
     */
    List<WorkMemberListQueryDto> queryMemberListByWorkSn(String workNo);

    /**
     * 更新人员变动信息
     * @param dto
     * @return
     */
    Boolean updateChange(WorkMemberChangeUpdateDto dto);

    /**
     * 更新人员评分
     * @param dto
     * @return
     */
    Boolean updateEvaluate(WorkEvaluateUpdateDto dto);

    /**
     * 修改工单人员出勤信息
     * @param hadWorkVo
     * @return
     */
    Boolean updateMemberInfoByWorkIds(List<WorkMemberCheckingVo> hadWorkVo);

    /**
     * 工单完成计算积分,调整人员实际开始和实际结束时间
     * @param workInfoVo
     */
    Boolean calculateIntegralByFinish(Integer workInfoVo);

}
