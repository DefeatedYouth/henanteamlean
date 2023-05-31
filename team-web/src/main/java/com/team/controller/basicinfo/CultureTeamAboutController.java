package com.team.controller.basicinfo;


import com.team.basic.dto.basicinfo.TeamAboutEditDto;
import com.team.basic.dto.basicinfo.TeamAboutHonorInfoDto;
import com.team.basic.dto.basicinfo.TeamAboutShowDto;
import com.team.basic.dto.basicinfo.TeamAboutStationInfoDto;
import com.team.basic.entity.CultureHonorVo;
import com.team.basic.service.CultureHonorService;
import com.team.basic.service.CultureTeamAboutService;
import com.team.common.constants.result.BaseResult;
import com.team.form.basicinfo.TeamAboutEditForm;
import com.team.vo.basicinfo.TeamAboutHonorInfoVo;
import com.team.vo.basicinfo.TeamAboutShowVo;
import com.team.vo.basicinfo.TeamAboutStationInfoVo;
import com.team.vo.basicinfo.TeamAboutStationTreeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 班组简介表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/cultureTeamAboutVo")
@Api(tags = "班组简介控制器")
@Slf4j
@CrossOrigin
public class CultureTeamAboutController {
    @Resource
    private CultureTeamAboutService cultureTeamAboutService;
    @Resource
    private CultureHonorService cultureHonorService;

    @ApiOperation("查看班组简介")
    @RequestMapping(value = "/showCultureTeamAbout", method = RequestMethod.POST)
    public BaseResult<TeamAboutShowVo> showCultureTeamAbout() {
        try {
            TeamAboutShowDto teamAboutShowDto = cultureTeamAboutService.showCultureTeamAbout();
            TeamAboutShowVo teamAboutShowVo = converShowDto2ShowVo(teamAboutShowDto);
            return BaseResult.success(teamAboutShowVo);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("维护班组简介")
    @RequestMapping(value = "/maintainCultureTeamAbout", method = RequestMethod.POST)
    public BaseResult<Boolean> maintainCultureTeamAbout(@RequestBody TeamAboutEditForm form) {
        try {
            TeamAboutEditDto teamAboutEditDto = converEditForm2EditDto(form);
            boolean isEdit = cultureTeamAboutService.maintainCultureTeamAbout(teamAboutEditDto);
            return BaseResult.success(isEdit);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("荣誉列表")
    @RequestMapping(value = "/honorList", method = RequestMethod.POST)
    public BaseResult<List<TeamAboutHonorInfoVo>> honorList() {
        try {
            List<CultureHonorVo> list = cultureHonorService.list();
            List<TeamAboutHonorInfoVo> honorList = list.stream().map(e -> {
                TeamAboutHonorInfoVo teamAboutHonorInfoVo = new TeamAboutHonorInfoVo();
                teamAboutHonorInfoVo.setHonorName(e.getHonorName());
                teamAboutHonorInfoVo.setHonorId(e.getId());
                return teamAboutHonorInfoVo;
            }).collect(Collectors.toList());
            return BaseResult.success(honorList);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("变电站列表")
    @RequestMapping(value = "/stationList", method = RequestMethod.POST)
    public BaseResult<TeamAboutStationTreeVo> stationList() {
        try {
            TeamAboutStationTreeVo provinceTree = new TeamAboutStationTreeVo();
            provinceTree.setIds("P001");
            provinceTree.setLevel("1");
            provinceTree.setName("国网河南省电力公司");

            List<TeamAboutStationTreeVo> unitTree = new ArrayList<>();
            TeamAboutStationTreeVo unitTreeOne = new TeamAboutStationTreeVo();
            unitTreeOne.setIds("U001");
            unitTreeOne.setLevel("2");
            unitTreeOne.setName("运维单位1");
            unitTree.add(unitTreeOne);

            List<TeamAboutStationTreeVo> deptTree = new ArrayList<>();
            TeamAboutStationTreeVo deptTreeOne = new TeamAboutStationTreeVo();
            deptTreeOne.setIds("D001");
            deptTreeOne.setLevel("3");
            deptTreeOne.setName("郑州运维分部");
            deptTree.add(deptTreeOne);

            List<TeamAboutStationTreeVo> voltageTree = new ArrayList<>();
            TeamAboutStationTreeVo voltageTreeOne = new TeamAboutStationTreeVo();
            voltageTreeOne.setIds("U001");
            voltageTreeOne.setLevel("4");
            voltageTreeOne.setName("交流500kV");
            voltageTree.add(voltageTreeOne);

            List<TeamAboutStationTreeVo> stationTree = new ArrayList<>();
            TeamAboutStationTreeVo stationTreeOne = new TeamAboutStationTreeVo();
            voltageTreeOne.setIds("S001");
            voltageTreeOne.setLevel("5");
            voltageTreeOne.setName("郑州500kV官渡变电站");
            TeamAboutStationTreeVo stationTreeTwo = new TeamAboutStationTreeVo();
            voltageTreeOne.setIds("S002");
            voltageTreeOne.setLevel("5");
            voltageTreeOne.setName("郑州220kV官渡变电站");
            TeamAboutStationTreeVo stationTreeThree = new TeamAboutStationTreeVo();
            voltageTreeOne.setIds("S003");
            voltageTreeOne.setLevel("5");
            voltageTreeOne.setName("郑州110kV官渡变电站");
            TeamAboutStationTreeVo stationTreeFour = new TeamAboutStationTreeVo();
            voltageTreeOne.setIds("S004");
            voltageTreeOne.setLevel("5");
            voltageTreeOne.setName("郑州35kV官渡变电站");
            stationTree.add(stationTreeOne);
            stationTree.add(stationTreeTwo);
            stationTree.add(stationTreeThree);
            stationTree.add(stationTreeFour);


            provinceTree.setChildren(unitTree);
            provinceTree.getChildren();
            unitTree.addAll(deptTree);

            deptTree.addAll(voltageTree);
            voltageTree.addAll(stationTree);
            return BaseResult.success(provinceTree);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    public static TeamAboutShowVo converShowDto2ShowVo(TeamAboutShowDto dto) {
        if (dto == null) {
            return null;
        }
        TeamAboutShowVo teamAboutShowVo = new TeamAboutShowVo();
        teamAboutShowVo.setTeamName(dto.getTeamName());
        teamAboutShowVo.setFormed(dto.getFormed());
        teamAboutShowVo.setTeamSize(dto.getTeamSize());
        List<TeamAboutHonorInfoVo> HonorInfos = dto.getTeamHonor().stream().map(e -> {
            TeamAboutHonorInfoVo teamAboutHonorInfoVo = new TeamAboutHonorInfoVo();
            teamAboutHonorInfoVo.setHonorId(e.getHonorId());
            teamAboutHonorInfoVo.setHonorName(e.getHonorName());
            return teamAboutHonorInfoVo;
        }).collect(Collectors.toList());
        teamAboutShowVo.setTeamHonor(HonorInfos);
        teamAboutShowVo.setTeamDuty(dto.getTeamDuty());
        List<TeamAboutStationInfoVo> StationInfos = dto.getTheStation().stream().map(e -> {
            TeamAboutStationInfoVo teamAboutStationInfoVo = new TeamAboutStationInfoVo();
            teamAboutStationInfoVo.setStationName(e.getStationName());
            teamAboutStationInfoVo.setStationId(e.getStationId());
            return teamAboutStationInfoVo;
        }).collect(Collectors.toList());
        teamAboutShowVo.setTheStation(StationInfos);
        teamAboutShowVo.setNumber500kV(dto.getNumber500kV());
        teamAboutShowVo.setNumber220kV(dto.getNumber220kV());
        teamAboutShowVo.setNumber110kV(dto.getNumber110kV());
        teamAboutShowVo.setNumber35kV(dto.getNumber35kV());
        return teamAboutShowVo;
    }

    public static TeamAboutEditDto converEditForm2EditDto(TeamAboutEditForm form) {
        if (form == null) {
            return null;
        }
        TeamAboutEditDto teamAboutEditDto = new TeamAboutEditDto();
        teamAboutEditDto.setFormed(form.getFormed());
        List<TeamAboutHonorInfoDto> honorInfoDtos = form.getTeamHonor().stream().map(e -> {
            TeamAboutHonorInfoDto teamAboutHonorInfoDto = new TeamAboutHonorInfoDto();
            teamAboutHonorInfoDto.setHonorName(e.getHonorName());
            teamAboutHonorInfoDto.setHonorId(e.getHonorId());
            return teamAboutHonorInfoDto;
        }).collect(Collectors.toList());
        teamAboutEditDto.setTeamHonor(honorInfoDtos);
        teamAboutEditDto.setTeamDuty(form.getTeamDuty());
        List<TeamAboutStationInfoDto> stationInfoDtos = form.getTheStation().stream().map(e -> {
            TeamAboutStationInfoDto teamAboutStationInfoDto = new TeamAboutStationInfoDto();
            teamAboutStationInfoDto.setStationId(e.getStationId());
            teamAboutStationInfoDto.setStationName(e.getStationName());
            return teamAboutStationInfoDto;
        }).collect(Collectors.toList());
        teamAboutEditDto.setTheStation(stationInfoDtos);
        return teamAboutEditDto;
    }


}

