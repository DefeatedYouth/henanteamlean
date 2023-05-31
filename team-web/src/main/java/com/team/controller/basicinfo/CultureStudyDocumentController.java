package com.team.controller.basicinfo;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.basic.dto.basicinfo.*;
import com.team.basic.entity.CultureStudyDocumentVo;
import com.team.basic.service.CultureRelStudyPersonService;
import com.team.basic.service.CultureStudyDocumentService;
import com.team.common.constants.result.BaseResult;
import com.team.conf.UserContent;
import com.team.form.basicinfo.*;
import com.team.vo.basicinfo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 学习文件表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/cultureStudyDocumentVo")
@Api(tags = "文件学习控制器")
public class CultureStudyDocumentController {
    @Resource
    private CultureStudyDocumentService cultureStudyDocumentService;

    @Resource
    private CultureRelStudyPersonService cultureRelStudyPersonService;

    @ApiOperation("文件学习派发列表")
    @RequestMapping(value = "/studyDocumentList", method = RequestMethod.POST)
    public BaseResult<StudyDocumentListPageVo> studyDocumentList(@RequestBody StudyDocumentListForm form) {
        try {
           /* Page<CultureStudyDocumentVo> page = new Page<>(form.getPageNum(),form.getPageRows());
            LambdaQueryWrapper<CultureStudyDocumentVo> wrapper = new LambdaQueryWrapper<>();
            if(!StringUtils.isEmpty(form.getDocumentName())){
                wrapper.eq(CultureStudyDocumentVo::getDocumentName,form.getDocumentName());
            }
            if (!StringUtils.isEmpty(form.getLabel())){
                wrapper.eq(CultureStudyDocumentVo::getLabel,form.getLabel());
            }
            if (form.getState() != null){
                wrapper.eq(CultureStudyDocumentVo::getState,form.getState());
            }
            if (form.getStartTime() != null){
                wrapper.ge(CultureStudyDocumentVo::getReleaseDate,form.getStartTime());
            }
            if (form.getEndTime() != null){
                wrapper.le(CultureStudyDocumentVo::getReleaseDate,form.getEndTime());
            }
            wrapper.eq(CultureStudyDocumentVo::getIsDelete,0);
            Page<CultureStudyDocumentVo> cultureStudyDocumentVoPage = cultureStudyDocumentService.page(page, wrapper);
            ArrayList<StudyDocumentListVo> studyDocumentListVos = new ArrayList<>();
            for (CultureStudyDocumentVo record : cultureStudyDocumentVoPage.getRecords()) {
                StudyDocumentListVo studyDocumentListVo = converVo2Vo(record);
                studyDocumentListVos.add(studyDocumentListVo);
            }
            StudyDocumentListPageVo studyDocumentListPageVo = new StudyDocumentListPageVo();
            studyDocumentListPageVo.setStudyDocumentLists(studyDocumentListVos);
            studyDocumentListPageVo.setPageNum(cultureStudyDocumentVoPage.getCurrent());
            studyDocumentListPageVo.setPageSize(cultureStudyDocumentVoPage.getSize());
            studyDocumentListPageVo.setTotal(cultureStudyDocumentVoPage.getTotal());*/
            return BaseResult.success(/*studyDocumentListPageVo*/null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("查看文件学习派发相关人员")
    @RequestMapping(value = "/showStudyDocumentPerson", method = RequestMethod.POST)
    public BaseResult<List<StudyDocumentPersonVo>> showStudyDocumentPerson(@RequestBody ShowStudyDocumentPersonForm form) {
        try {
            /*Integer studyDocumentId = form.getStudyDocumentId();
            List<CultureRelStudyPersonVo> list = cultureRelStudyPersonService.list(new LambdaQueryWrapper<CultureRelStudyPersonVo>()
                    .eq(CultureRelStudyPersonVo::getDocumentId, studyDocumentId));
            List<StudyDocumentPersonVo> studyDocumentPersonVos = new ArrayList<>();
            for (CultureRelStudyPersonVo cultureRelStudyPersonVo : list) {
                StudyDocumentPersonVo studyDocumentPersonVo = converVo2Vo(cultureRelStudyPersonVo);
                studyDocumentPersonVos.add(studyDocumentPersonVo);
            }*/
            return BaseResult.success(/*studyDocumentPersonVos*/null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("添加文件学习派发相关人员")
    @RequestMapping(value = "/addStudyDocumentPerson", method = RequestMethod.POST)
    public BaseResult<Boolean> addStudyDocumentPerson(@RequestBody List<StudyDocumentPersonForm> form) {
        try {
           /* List<CultureRelStudyPersonVo> cultureRelStudyPersonVos = new ArrayList<>();
            for (StudyDocumentPersonForm studyDocumentPersonForm : form) {
                CultureRelStudyPersonVo cultureRelStudyPersonVo = new CultureRelStudyPersonVo();
                cultureRelStudyPersonVo.setPersonId(studyDocumentPersonForm.getPersonId());
                cultureRelStudyPersonVo.setPersonName(studyDocumentPersonForm.getPersonName());
                cultureRelStudyPersonVos.add(cultureRelStudyPersonVo);
            }
            boolean b = cultureRelStudyPersonService.saveBatch(cultureRelStudyPersonVos);*/
            return BaseResult.success(/*b*/null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }


    @ApiOperation("添加文件学习派发")
    @RequestMapping(value = "/addStudyDocument", method = RequestMethod.POST)
    public BaseResult<Boolean> addStudyDocument(@RequestBody StudyDocumenCreatForm form) {
        try {
            /*CultureStudyDocumentVo cultureStudyDocumentVo = converForm2Vo(form);

            boolean saveStudyDocument = cultureStudyDocumentService.save(cultureStudyDocumentVo);

            ArrayList<CultureRelStudyPersonVo> cultureRelStudyPersonVos = new ArrayList<>();
            for (StudyDocumentPersonVo studyPerson : form.getStudyPerson()) {
                CultureRelStudyPersonVo cultureRelStudyPersonVo = new CultureRelStudyPersonVo();
                cultureRelStudyPersonVo.setPersonId(studyPerson.getPersonId());
                cultureRelStudyPersonVo.setPersonName(studyPerson.getPersonName());
                cultureRelStudyPersonVos.add(cultureRelStudyPersonVo);
            }
            boolean saveStudyPerson = cultureRelStudyPersonService.saveBatch(cultureRelStudyPersonVos);*/

            return BaseResult.success(/*saveStudyPerson&&saveStudyDocument*/false);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("修改文件学习派发")
    @RequestMapping(value = "/updateStudyDocument", method = RequestMethod.POST)
    public BaseResult<Boolean> updateStudyDocument(@RequestBody StudyDocumenUpdateForm form) {
        try {
         /*   StudyDocumenUpdateDto studyDocumenUpdateDto = converForm2Dto(form);
            boolean b = cultureStudyDocumentService.updateStudyDocument(studyDocumenUpdateDto);*/
            return BaseResult.success(false);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("删除文件学习派发")
    @RequestMapping(value = "/deleteStudyDocument", method = RequestMethod.POST)
    public BaseResult<Boolean> deleteStudyDocument(@RequestBody StudyDocumenDeleteForm form) {
        try {
           /* boolean b = cultureStudyDocumentService.deleteStudyDocument(form.getDocumentId());*/
            return BaseResult.success(false);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("文件学习派发-发布")
    @RequestMapping(value = "/ReleaseStudyDocument", method = RequestMethod.POST)
    public BaseResult<Boolean> ReleaseStudyDocument(@RequestBody StudyDocumenDeleteForm form) {
        try {
            /*boolean b = cultureStudyDocumentService.ReleaseStudyDocument(form.getDocumentId());*/
            return BaseResult.success(false);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("文件学习派发-取消发布")
    @RequestMapping(value = "/cancelReleaseStudyDocument", method = RequestMethod.POST)
    public BaseResult<Boolean> cancelReleaseStudyDocument(@RequestBody StudyDocumenDeleteForm form) {
        try {
            /*boolean b = cultureStudyDocumentService.cancelReleaseStudyDocument(form.getDocumentId());*/
            return BaseResult.success(false);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("文件学习-班长展示")
    @RequestMapping(value = "/studyShowMonitor", method = RequestMethod.POST)
    public BaseResult<List<StudyDocumentMonitorShowVo>> studyShowMonitor() {
        try {
            /*UserContent.UserInfo userInfo = UserContent.getUserInfo();
            *//*String clazzId = userInfo.getClazzId();*//*
            String userId = userInfo.getUserId();
            List<StudyDocumentShowDto> studyDocumentShowDtos = cultureStudyDocumentService.studyShowMonitor(userId);

            List<StudyDocumentMonitorShowVo> studyDocumentMonitorShowVos = new ArrayList<>();
            for (StudyDocumentShowDto studyDocumentShowDto : studyDocumentShowDtos) {
                StudyDocumentMonitorShowVo studyDocumentMonitorShowVo = converDto2Vo(studyDocumentShowDto);
                studyDocumentMonitorShowVos.add(studyDocumentMonitorShowVo);
            }*/
            return BaseResult.success(/*studyDocumentMonitorShowVos*/null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }

    @ApiOperation("文件学习-组员展示")
    @RequestMapping(value = "/studyShowMember", method = RequestMethod.POST)
    public BaseResult<List<StudyDocumentMemberShowVo>> studyShowMember() {
        try {
           /* UserContent.UserInfo userInfo = UserContent.getUserInfo();
            String userId = userInfo.getUserId();
            List<StudyDocumentShowDto> studyDocumentShowDtos = cultureStudyDocumentService.studyShowMember(userId);
            List<StudyDocumentMemberShowVo> studyDocumentMemberShowVos = new ArrayList<>();
            for (StudyDocumentShowDto studyDocumentShowDto : studyDocumentShowDtos) {
                StudyDocumentMemberShowVo studyDocumentMemberShowVo = converDto2vo(studyDocumentShowDto);
                studyDocumentMemberShowVos.add(studyDocumentMemberShowVo);
            }*/
            return BaseResult.success(/*studyDocumentMemberShowVos*/null);
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }


    @ApiOperation("文件学习-学习列表")
    @RequestMapping(value = "/studyList", method = RequestMethod.POST)
    public BaseResult<StudyDocumentStudyVo> studyList(@RequestBody StudyDocumentStudyListForm form) {
        try {
           /* UserContent.UserInfo userInfo = UserContent.getUserInfo();
            String userId = userInfo.getUserId();
            StudyDocumentStudyListAfferentDto studyDocumentStudyListAfferentDto = converForm2Dto(form);
            studyDocumentStudyListAfferentDto.setUserId(userId);
            StudyDocumentStudyDto studyDocumentStudyDto = cultureStudyDocumentService.studyList(studyDocumentStudyListAfferentDto);
            StudyDocumentStudyVo studyDocumentStudyVo = converDto2Vo(studyDocumentStudyDto);
            return BaseResult.success(studyDocumentStudyVo);*/
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return BaseResult.fail();
        }
    }





   /* public static CultureStudyDocumentVo converForm2Vo(StudyDocumenCreatForm form) {
        if (form == null) {
            return null;
        }
        CultureStudyDocumentVo cultureStudyDocumentVo = new CultureStudyDocumentVo();
        cultureStudyDocumentVo.setDocumentName(form.getDocumentName());
        cultureStudyDocumentVo.setLabel(form.getLabel());
        cultureStudyDocumentVo.setStudyRequire(form.getRequirement());
        cultureStudyDocumentVo.setRemarks(form.getRemark());
        return cultureStudyDocumentVo;
    }

    public static StudyDocumentListVo converVo2Vo(CultureStudyDocumentVo vo) {
        if (vo == null) {
            return null;
        }
        StudyDocumentListVo studyDocumentListVo = new StudyDocumentListVo();
        studyDocumentListVo.setDocumentId(vo.getId());
        studyDocumentListVo.setDocumentName(vo.getDocumentName());
        studyDocumentListVo.setLabel(vo.getLabel());
        studyDocumentListVo.setAlreadyStudy(vo.getAlreadyStudy());
        studyDocumentListVo.setState(vo.getState());
        studyDocumentListVo.setReleaseDate(vo.getReleaseDate());
        studyDocumentListVo.setRequirement(vo.getStudyRequire());
        studyDocumentListVo.setRemark(vo.getRemarks());
        studyDocumentListVo.setAccessory(vo.getAttachmentPathList());
        return studyDocumentListVo;
    }

    public static StudyDocumentPersonVo converVo2Vo(CultureRelStudyPersonVo vo) {
        if (vo == null) {
            return null;
        }
        StudyDocumentPersonVo studyDocumentPersonVo = new StudyDocumentPersonVo();
        studyDocumentPersonVo.setPersonId(vo.getPersonId());
        studyDocumentPersonVo.setPersonName(vo.getPersonName());
        return studyDocumentPersonVo;
    }

    public static StudyDocumenUpdateDto converForm2Dto(StudyDocumenUpdateForm form) {
        if (form == null) {
            return null;
        }
        StudyDocumenUpdateDto studyDocumenUpdateDto = new StudyDocumenUpdateDto();
        studyDocumenUpdateDto.setDocumentName(form.getDocumentName());
        studyDocumenUpdateDto.setLabel(form.getLabel());
        List<Map<String, String>> collect = form.getStudyPerson().stream().map(e -> {
            Map<String, String> map = new HashMap<>();
            map.put(e.getPersonId(), e.getPersonName());
            return map;
        }).collect(Collectors.toList());
        studyDocumenUpdateDto.setStudyPerson(collect);
        studyDocumenUpdateDto.setRequirement(form.getRequirement());
        studyDocumenUpdateDto.setRemark(form.getRemark());
        studyDocumenUpdateDto.setDocumentId(form.getDocumentId());
        return studyDocumenUpdateDto;
    }

    public static StudyDocumentMemberShowVo converDto2vo(StudyDocumentShowDto dto) {
        if (dto == null) {
            return null;
        }
        StudyDocumentMemberShowVo studyDocumentMemberShowVo = new StudyDocumentMemberShowVo();
        studyDocumentMemberShowVo.setDocumentId(dto.getDocumentId());
        studyDocumentMemberShowVo.setDocumentName(dto.getDocumentName());
        studyDocumentMemberShowVo.setIsStudy(dto.getIsStudy());
        studyDocumentMemberShowVo.setReleaseDate(dto.getReleaseDate());
        return studyDocumentMemberShowVo;
    }

    public static StudyDocumentMonitorShowVo converDto2Vo(StudyDocumentShowDto dto) {
        if (dto == null) {
            return null;
        }
        StudyDocumentMonitorShowVo studyDocumentMonitorShowVo = new StudyDocumentMonitorShowVo();
        studyDocumentMonitorShowVo.setDocumentId(dto.getDocumentId());
        studyDocumentMonitorShowVo.setDocumentName(dto.getDocumentName());
        studyDocumentMonitorShowVo.setAlreadyStudy(dto.getAlreadyStudy());
        studyDocumentMonitorShowVo.setReleaseDate(dto.getReleaseDate());
        return studyDocumentMonitorShowVo;
    }

    public static StudyDocumentStudyListAfferentDto converForm2Dto(StudyDocumentStudyListForm form) {
        if (form == null) {
            return null;
        }
        StudyDocumentStudyListAfferentDto studyDocumentStudyListAfferentDto = new StudyDocumentStudyListAfferentDto();
        studyDocumentStudyListAfferentDto.setDocumentName(form.getDocumentName());
        studyDocumentStudyListAfferentDto.setLabel(form.getLabel());
        studyDocumentStudyListAfferentDto.setIsStudy(form.getIsStudy());
        studyDocumentStudyListAfferentDto.setStartTime(form.getStartTime());
        studyDocumentStudyListAfferentDto.setEndTime(form.getEndTime());
        studyDocumentStudyListAfferentDto.setPageNum(form.getPageNum());
        studyDocumentStudyListAfferentDto.setPageRows(form.getPageRows());
        return studyDocumentStudyListAfferentDto;
    }

    public static StudyDocumentStudyVo converDto2Vo(StudyDocumentStudyDto dto) {
        if (dto == null) {
            return null;
        }
        StudyDocumentStudyVo studyDocumentStudyVo = new StudyDocumentStudyVo();
        List<StudyDocumentStudyListVo> studyDocumentStudyListVos = new ArrayList<>();
        for (StudyDocumentStudyListDto studyDocumentStudyListVo : dto.getStudyDocumentStudyListVos()) {
            StudyDocumentStudyListVo studyDocumentStudyListVo1 = converDto2Dto(studyDocumentStudyListVo);
            studyDocumentStudyListVos.add(studyDocumentStudyListVo1);
        }
        studyDocumentStudyVo.setStudyDocumentStudyListVos(studyDocumentStudyListVos);
        studyDocumentStudyVo.setPageNum(dto.getPageNum());
        studyDocumentStudyVo.setPageSize(dto.getPageSize());
        studyDocumentStudyVo.setTotal(dto.getTotal());
        studyDocumentStudyVo.setAlreadyCount(dto.getAlreadyCount());
        studyDocumentStudyVo.setNotCount(dto.getNotCount());
        studyDocumentStudyVo.setTotalCount(dto.getTotalCount());
        return studyDocumentStudyVo;
    }

    public static StudyDocumentStudyListVo converDto2Dto(StudyDocumentStudyListDto dto) {
        if (dto == null) {
            return null;
        }
        StudyDocumentStudyListVo studyDocumentStudyListVo = new StudyDocumentStudyListVo();
        studyDocumentStudyListVo.setDocumentId(dto.getDocumentId());
        studyDocumentStudyListVo.setDocumentName(dto.getDocumentName());
        studyDocumentStudyListVo.setLabel(dto.getLabel());
        studyDocumentStudyListVo.setRequirement(dto.getRequirement());
        studyDocumentStudyListVo.setIsStudy(dto.getIsStudy());
        studyDocumentStudyListVo.setState(dto.getState());
        studyDocumentStudyListVo.setReleaseDate(dto.getReleaseDate());
        studyDocumentStudyListVo.setAccessory(dto.getAccessory());
        return studyDocumentStudyListVo;
    }*/


}

