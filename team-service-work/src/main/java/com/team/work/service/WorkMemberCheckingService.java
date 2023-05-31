package com.team.work.service;

import com.team.work.dto.*;
import com.team.work.entity.WorkInfoVo;
import com.team.work.entity.WorkMemberCheckingVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工单员工考勤表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface WorkMemberCheckingService extends IService<WorkMemberCheckingVo> {

    /**
     * 查询日历详情
     * @param month
     * @param teamId
     * @param memberId
     * @return
     */
    WorkMemberCheckingCountDetailDto queryCountDetail(String month, Integer teamId, Integer memberId);

    /**
     * 查询日历列表
     * @param month
     * @param teamId
     * @return
     */
    List<WorkCalendarDto> queryCalendar(String month, Integer teamId);

    /**
     * 查询日志列表
     * @param converLogQueryForm2Dto
     * @return
     */
    List<WorkLogResultDto> queryWorkLogList(WorkLogQueryDto converLogQueryForm2Dto);

    /**
     * 创建假期
     * @param converCreateForm
     * @return
     */
    Boolean createVacate(WorkVacateCreateDto converCreateForm);

    /**
     * 调整员工考勤
     * @param workDays
     * @param workNo
     * @param memberId
     * @param days
     * @param memberName
     * @return
     */
    Boolean changeMemberCheckingByWorkDays(WorkInfoVo workNo, String memberId, String days, String memberName);

    /**
     * 查询缺勤信息
     * @param today
     * @return
     */
    List<WorkMemberCheckingVo> queryAbsenceList(String today);
}
