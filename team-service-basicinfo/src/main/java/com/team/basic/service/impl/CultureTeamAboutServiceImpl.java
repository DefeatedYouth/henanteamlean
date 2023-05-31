package com.team.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.team.basic.dao.CultureTeamAboutMapper;
import com.team.basic.dto.basicinfo.TeamAboutEditDto;
import com.team.basic.dto.basicinfo.TeamAboutHonorInfoDto;
import com.team.basic.dto.basicinfo.TeamAboutShowDto;
import com.team.basic.dto.basicinfo.TeamAboutStationInfoDto;
import com.team.basic.entity.CultureHonorVo;
import com.team.basic.entity.CultureRelTeamaboutHonorVo;
import com.team.basic.entity.CultureRelTeamaboutStationVo;
import com.team.basic.entity.CultureTeamAboutVo;
import com.team.basic.service.CultureHonorService;
import com.team.basic.service.CultureRelTeamaboutHonorService;
import com.team.basic.service.CultureRelTeamaboutStationService;
import com.team.basic.service.CultureTeamAboutService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.common.util.UserContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 班组简介表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class CultureTeamAboutServiceImpl extends ServiceImpl<CultureTeamAboutMapper, CultureTeamAboutVo> implements CultureTeamAboutService {
    @Resource
    private CultureTeamAboutService cultureTeamAboutService;
    @Resource
    private CultureRelTeamaboutStationService cultureRelTeamaboutStationService;
    @Resource
    private CultureRelTeamaboutHonorService cultureRelTeamaboutHonorService;
    @Resource
    private CultureHonorService cultureHonorService;

    /**
     * 班组简介展示
     *
     * @return
     */
    @Override
    public TeamAboutShowDto showCultureTeamAbout() {
        UserContent.UserInfo userInfo = UserContent.getUserInfo();
        CultureTeamAboutVo teamAboutInfo = cultureTeamAboutService.getOne(new LambdaQueryWrapper<CultureTeamAboutVo>()
                .eq(CultureTeamAboutVo::getTeamId, userInfo.getTeamId()).eq(CultureTeamAboutVo::getIsDeleted,0));
        List<CultureRelTeamaboutStationVo> Stations = cultureRelTeamaboutStationService.list(new LambdaQueryWrapper<CultureRelTeamaboutStationVo>()
                .eq(CultureRelTeamaboutStationVo::getTeamAboutId, teamAboutInfo.getId()));
        List<CultureRelTeamaboutHonorVo> HonorVos = cultureRelTeamaboutHonorService.list(new LambdaQueryWrapper<CultureRelTeamaboutHonorVo>()
                .eq(CultureRelTeamaboutHonorVo::getTeamAboutId, teamAboutInfo.getId()));
        List<Integer> integers = new ArrayList<>();
        for (CultureRelTeamaboutHonorVo honorVo : HonorVos) {
            integers.add(honorVo.getHonorId());
        }
        List<CultureHonorVo> cultureHonorVos = cultureHonorService.list(new LambdaQueryWrapper<CultureHonorVo>().in(CultureHonorVo::getId, integers));

        TeamAboutShowDto result = new TeamAboutShowDto();
        result.setTeamName(teamAboutInfo.getTeamName());
        result.setFormed(teamAboutInfo.getTeamFormed());
        result.setTeamDuty(teamAboutInfo.getTeamDuties());
        //班组人数
        result.setTeamSize(8);

        List<TeamAboutHonorInfoDto> HonorInfos = cultureHonorVos.stream().map(e -> {
            TeamAboutHonorInfoDto teamAboutHonorInfoDto = new TeamAboutHonorInfoDto();
            teamAboutHonorInfoDto.setHonorId(e.getId());
            teamAboutHonorInfoDto.setHonorName(e.getHonorName());
            return teamAboutHonorInfoDto;
        }).collect(Collectors.toList());
        result.setTeamHonor(HonorInfos);
        List<TeamAboutStationInfoDto> stationInfoDtos = Stations.stream().map(e -> {
            TeamAboutStationInfoDto teamAboutStationInfoDto = new TeamAboutStationInfoDto();
            teamAboutStationInfoDto.setStationName(e.getStationName());
            teamAboutStationInfoDto.setStationId(e.getStationId());
            return teamAboutStationInfoDto;
        }).collect(Collectors.toList());
        result.setTheStation(stationInfoDtos);

        int number500 = 0;
        int number110 = 0;
        int number220 = 0;
        int number35 = 0;

        for (TeamAboutStationInfoDto stationInfoDto : stationInfoDtos) {
            String s = stationInfoDto.getStationName();
            if (s.contains("500kV")) {
                number500 += 1;
            } else if (s.contains("220kV")) {
                number220 += 1;
            } else if (s.contains("110kV")) {
                number110 += 1;
            } else if (s.contains("35kV")) {
                number35 += 1;
            }
        }
        result.setNumber35kV(number35);
        result.setNumber110kV(number110);
        result.setNumber220kV(number220);
        result.setNumber500kV(number500);
        return result;
    }

    /**
     *维护班组简介信息
     * @param teamAboutEditDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean maintainCultureTeamAbout(TeamAboutEditDto teamAboutEditDto) {
        UserContent.UserInfo userInfo = UserContent.getUserInfo();
        CultureTeamAboutVo temp = cultureTeamAboutService.getOne(new LambdaQueryWrapper<CultureTeamAboutVo>().eq(CultureTeamAboutVo::getTeamId, userInfo.getTeamId()));
        if(temp != null){
            Integer id = temp.getId();
            LambdaUpdateWrapper<CultureTeamAboutVo> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(CultureTeamAboutVo::getTeamId,userInfo.getTeamId())
                    .set(CultureTeamAboutVo::getTeamName,userInfo.getTeamName())
                    .set(CultureTeamAboutVo::getTeamDuties,teamAboutEditDto.getTeamDuty())
                    .set(CultureTeamAboutVo::getTeamFormed,teamAboutEditDto.getFormed())
                    .set(CultureTeamAboutVo::getTeamPeoNum,8)
                    .set(CultureTeamAboutVo::getProducerId,userInfo.getUserId())
                    .set(CultureTeamAboutVo::getCreatedBy,userInfo.getUserName())
                    .set(CultureTeamAboutVo::getCreatedTime,new Date())
                    .eq(CultureTeamAboutVo::getId,id);
            boolean updateTeamAbout = cultureTeamAboutService.update(wrapper);
            //删除关联数据
            boolean removeStation = cultureRelTeamaboutStationService.remove(new LambdaUpdateWrapper<CultureRelTeamaboutStationVo>()
                    .eq(CultureRelTeamaboutStationVo::getTeamAboutId, id));
            boolean removeHonor = cultureRelTeamaboutHonorService.remove(new LambdaUpdateWrapper<CultureRelTeamaboutHonorVo>()
                    .eq(CultureRelTeamaboutHonorVo::getTeamAboutId, id));
            //添加关联数据
            List<CultureRelTeamaboutStationVo> stationInfos = teamAboutEditDto.getTheStation().stream().map(e -> {
                CultureRelTeamaboutStationVo cultureRelTeamaboutStationVo = new CultureRelTeamaboutStationVo();
                cultureRelTeamaboutStationVo.setStationId(e.getStationId());
                cultureRelTeamaboutStationVo.setStationName(e.getStationName());
                cultureRelTeamaboutStationVo.setTeamAboutId(id);

                cultureRelTeamaboutStationVo.setCreatedBy(userInfo.getUserName());
                cultureRelTeamaboutStationVo.setCreatedTime(new Date());
                cultureRelTeamaboutStationVo.setProducerId(userInfo.getUserId());

                return cultureRelTeamaboutStationVo;
            }).collect(Collectors.toList());

            boolean isInsertStation = cultureRelTeamaboutStationService.saveBatch(stationInfos);

            List<CultureRelTeamaboutHonorVo> honorInfos = teamAboutEditDto.getTeamHonor().stream().map(e -> {
                CultureRelTeamaboutHonorVo cultureRelTeamaboutHonorVo = new CultureRelTeamaboutHonorVo();
                cultureRelTeamaboutHonorVo.setHonorId(e.getHonorId());
                cultureRelTeamaboutHonorVo.setTeamAboutId(id);

                cultureRelTeamaboutHonorVo.setCreatedBy(userInfo.getUserName());
                cultureRelTeamaboutHonorVo.setCreatedTime(new Date());
                cultureRelTeamaboutHonorVo.setProducerId(userInfo.getUserId());
                return cultureRelTeamaboutHonorVo;
            }).collect(Collectors.toList());

            boolean isInsertHonor = cultureRelTeamaboutHonorService.saveBatch(honorInfos);

            return updateTeamAbout&&isInsertHonor&&isInsertStation&&removeStation&&removeHonor;
        } else {
            CultureTeamAboutVo cultureTeamAboutVo = new CultureTeamAboutVo();
            cultureTeamAboutVo.setTeamPeoNum(8);
            cultureTeamAboutVo.setTeamId(userInfo.getTeamId());
            cultureTeamAboutVo.setTeamName(userInfo.getTeamName());
            cultureTeamAboutVo.setTeamDuties(teamAboutEditDto.getTeamDuty());
            cultureTeamAboutVo.setTeamFormed(teamAboutEditDto.getFormed());
            cultureTeamAboutVo.setCreatedBy(userInfo.getUserName());
            cultureTeamAboutVo.setCreatedTime(new Date());
            cultureTeamAboutVo.setProducerId(userInfo.getUserId());
            boolean isInsertTeamAbout = cultureTeamAboutService.save(cultureTeamAboutVo);
            Integer id = cultureTeamAboutVo.getId();

            List<CultureRelTeamaboutStationVo> stationInfos = teamAboutEditDto.getTheStation().stream().map(e -> {
                CultureRelTeamaboutStationVo cultureRelTeamaboutStationVo = new CultureRelTeamaboutStationVo();
                cultureRelTeamaboutStationVo.setStationId(e.getStationId());
                cultureRelTeamaboutStationVo.setStationName(e.getStationName());
                cultureRelTeamaboutStationVo.setTeamAboutId(id);

                cultureRelTeamaboutStationVo.setCreatedBy(userInfo.getUserName());
                cultureRelTeamaboutStationVo.setCreatedTime(new Date());
                cultureRelTeamaboutStationVo.setProducerId(userInfo.getUserId());
                return cultureRelTeamaboutStationVo;
            }).collect(Collectors.toList());

            boolean isInsertStation = cultureRelTeamaboutStationService.saveBatch(stationInfos);

            List<CultureRelTeamaboutHonorVo> honorInfos = teamAboutEditDto.getTeamHonor().stream().map(e -> {
                CultureRelTeamaboutHonorVo cultureRelTeamaboutHonorVo = new CultureRelTeamaboutHonorVo();
                cultureRelTeamaboutHonorVo.setHonorId(e.getHonorId());
                cultureRelTeamaboutHonorVo.setTeamAboutId(id);

                cultureRelTeamaboutHonorVo.setCreatedBy(userInfo.getUserName());
                cultureRelTeamaboutHonorVo.setCreatedTime(new Date());
                cultureRelTeamaboutHonorVo.setProducerId(userInfo.getUserId());
                return cultureRelTeamaboutHonorVo;
            }).collect(Collectors.toList());

            boolean isInsertHonor = cultureRelTeamaboutHonorService.saveBatch(honorInfos);

            return isInsertTeamAbout&&isInsertHonor&&isInsertStation;
        }
    }
}
