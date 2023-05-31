package com.team.controller.basicinfo;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.team.basic.entity.SysHolidaysVo;
import com.team.basic.service.SysHolidaysService;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.DateUtil;
import com.team.conf.UserContent;
import com.team.form.basicinfo.HolidaysCreateForm;
import com.team.form.basicinfo.HolidaysDeleteForm;
import com.team.form.basicinfo.HolidaysListForm;
import com.team.form.basicinfo.HolidaysUpdateForm;
import com.team.vo.basicinfo.HolidaysListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 节假日表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/sysHolidaysVo")
@Api(tags = "节假日管理")
@Slf4j
@CrossOrigin
public class SysHolidaysController {

    @Resource
    private SysHolidaysService sysHolidaysService;

    @ApiOperation("添加节假日")
    @RequestMapping(value = "/addHolidays", method = RequestMethod.POST)
    public BaseResult<Boolean> addHolidays(@RequestBody HolidaysCreateForm form) {
        try {
            UserContent.UserInfo userInfo = UserContent.getUserInfo();
            String userName = userInfo.getUserName();
            String userId = userInfo.getUserId();

            Date startTime = form.getStartTime();
            Date endTime = form.getEndTime();
            long startDateTime = startTime.getTime();
            long endDateTime = endTime.getTime();
            int days = (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24)) + 1;

            StringBuilder rests = new StringBuilder();
            for (String date : form.getRest()) {
                String trim = date.trim();
                rests.append(trim).append(";");
            }
            Date date = new Date();

            SysHolidaysVo sysHolidaysVo = converForm2Vo(form);
            sysHolidaysVo.setHolidaysDays(days);
            sysHolidaysVo.setDaysOffDate(rests.toString());
            Date startDate = form.getStartTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int year = calendar.get(Calendar.YEAR);
            sysHolidaysVo.setYear(year);

            sysHolidaysVo.setProducerId(userId);
            sysHolidaysVo.setCreatedTime(date);
            sysHolidaysVo.setCreatedBy(userName);

            return BaseResult.success(sysHolidaysService.save(sysHolidaysVo));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("修改节假日")
    @RequestMapping(value = "/updateHolidays", method = RequestMethod.POST)
    public BaseResult updateHolidays(@RequestBody HolidaysUpdateForm form) {
        try {
            UserContent.UserInfo userInfo = UserContent.getUserInfo();
            String userName = userInfo.getUserName();
            String userId = userInfo.getUserId();

            Date startTime = form.getStartTime();
            Date endTime = form.getEndTime();
            long startDateTime = startTime.getTime();
            long endDateTime = endTime.getTime();
            int days = (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24)) + 1;

            StringBuilder rests = new StringBuilder();
            for (String date : form.getRest()) {
                String trim = date.trim();
                rests.append(trim).append(";");
            }
            Date startDate = form.getStartTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int year = calendar.get(Calendar.YEAR);

            Date date = new Date();
            SysHolidaysVo sysHolidaysVo = converForm2Vo(form);
            sysHolidaysVo.setHolidaysDays(days);
            sysHolidaysVo.setDaysOffDate(rests.toString());
            sysHolidaysVo.setYear(year);
            sysHolidaysVo.setUpdatedTime(date);
            sysHolidaysVo.setUpdatedBy(userName);
            sysHolidaysVo.setUpdatedId(userId);

            return BaseResult.success(sysHolidaysService.updateById(sysHolidaysVo));
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("删除节假日")
    @RequestMapping(value = "/deleteHolidays", method = RequestMethod.POST)
    public BaseResult<Boolean> deleteHolidays(@RequestBody HolidaysDeleteForm form) {
        try {
            UserContent.UserInfo userInfo = UserContent.getUserInfo();
            String userName = userInfo.getUserName();
            String userId = userInfo.getUserId();
            Date date = new Date();
            boolean update = false;
            for (Integer holidaysId : form.getHolidaysIds()) {
                LambdaUpdateWrapper<SysHolidaysVo> wrapper = new LambdaUpdateWrapper<>();
                wrapper.set(SysHolidaysVo::getIsDeleted, 1)
                        .set(SysHolidaysVo::getUpdatedTime, date)
                        .set(SysHolidaysVo::getUpdatedBy, userName)
                        .set(SysHolidaysVo::getUpdatedId, userId)
                        .eq(SysHolidaysVo::getId, holidaysId);
                update = sysHolidaysService.update(wrapper);
            }
            return BaseResult.success(update);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("节假日列表展示")
    @RequestMapping(value = "/HolidaysList", method = RequestMethod.POST)
    public BaseResult<List<HolidaysListVo>> HolidaysList(@RequestBody HolidaysListForm form) {
        try {

            LambdaQueryWrapper<SysHolidaysVo> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SysHolidaysVo::getIsDeleted, 0)
                    .eq(SysHolidaysVo::getYear, Integer.parseInt(form.getYear()));
            List<SysHolidaysVo> list = sysHolidaysService.list(wrapper);

            List<HolidaysListVo> holidaysListVos = new ArrayList<>();
            for (SysHolidaysVo record : list) {
                HolidaysListVo holidaysListVo = converVo2Vo(record);
                if (!StringUtils.isEmpty(record.getDaysOffDate())) {
                    String[] split = record.getDaysOffDate().split(";");
                    List<String> strings = new ArrayList<>();
                    for (String s : split) {
                        String s1 = DateUtil.dateToWeek(s);
                        StringBuilder rests = new StringBuilder();
                        rests.append(s).append("(").append(s1).append(")");
                        strings.add(rests.toString());
                    }
                    holidaysListVo.setRestDays(strings);
                } else {
                    List<String> strings = new ArrayList<>();
                    strings.add("无调休");
                    holidaysListVo.setRestDays(strings);
                }
                holidaysListVos.add(holidaysListVo);
            }
            return BaseResult.success(holidaysListVos);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }


    public static SysHolidaysVo converForm2Vo(HolidaysCreateForm form) {
        if (form == null) {
            return null;
        }
        SysHolidaysVo sysHolidaysVo = new SysHolidaysVo();
        sysHolidaysVo.setHolidaysName(form.getHolidaysName());
        sysHolidaysVo.setStartTime(form.getStartTime());
        sysHolidaysVo.setEndTime(form.getEndTime());
        return sysHolidaysVo;
    }

    public static SysHolidaysVo converForm2Vo(HolidaysUpdateForm form) {
        if (form == null) {
            return null;
        }
        SysHolidaysVo sysHolidaysVo = new SysHolidaysVo();
        sysHolidaysVo.setHolidaysName(form.getHolidaysName());
        sysHolidaysVo.setStartTime(form.getStartTime());
        sysHolidaysVo.setEndTime(form.getEndTime());
        sysHolidaysVo.setId(form.getHolidaysId());
        return sysHolidaysVo;
    }

    public static HolidaysListVo converVo2Vo(SysHolidaysVo vo) {
        if (vo == null) {
            return null;
        }
        HolidaysListVo holidaysListVo = new HolidaysListVo();
        holidaysListVo.setHolidaysId(vo.getId());
        holidaysListVo.setHolidaysName(vo.getHolidaysName());
        holidaysListVo.setStartTime(vo.getStartTime());
        holidaysListVo.setEndTime(vo.getEndTime());
        holidaysListVo.setDaysOfHolidays(vo.getHolidaysDays());
        return holidaysListVo;
    }

}

