package com.team.controller.basicinfo;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.basic.dto.CulturalCommentsSuggestionsDto;
import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.CulturalCommentsSuggestionsVo;
import com.team.basic.entity.FilePathVo;
import com.team.basic.service.CulturalCommentsSuggestionsService;
import com.team.basic.service.FilePathService;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.DateUtil;
import com.team.common.util.FilePathUtil;
import com.team.form.basicinfo.*;
import com.team.vo.basicinfo.CulturalCommentsSuggestionsQueryVo;
import com.team.vo.basicinfo.FilePathQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 意见建议表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@CrossOrigin
@RestController
@RequestMapping("/culturalCommentsSuggestionsVo")
public class CulturalCommentsSuggestionsController extends BaseController {

    @Resource
    private CulturalCommentsSuggestionsService culturalCommentsSuggestionsService;

    @Resource
    private FilePathService filePathService;

    @ApiOperation(value = "意见建议添加")
    @PostMapping(value = "/addCulturalCommentsSuggestions")
    public BaseResult<String> addCulturalCommentsSuggestions(@RequestBody CulturalCommentsSuggestionsAddForm culturalCommentsSuggestionsAddForm){
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = converForm2Dto(culturalCommentsSuggestionsAddForm);
        int i = culturalCommentsSuggestionsService.addCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        if (i>0) return success("添加成功");
        else return fail(-1,"添加失败");
    }

    @ApiOperation(value = "意见建议更新")
    @PostMapping(value = "/updateCulturalCommentsSuggestions")
    public BaseResult<String> updateCulturalCommentsSuggestions(@RequestBody CulturalCommentsSuggestionsUpdateForm culturalCommentsSuggestionsUpdateForm){
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = converForm2Dto(culturalCommentsSuggestionsUpdateForm);
        int i = culturalCommentsSuggestionsService.updateCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        if (i>0) return success("修改成功");
        else return fail(-1,"修改失败");
    }

    @ApiOperation(value = "意见建议删除")
    @PostMapping(value = "/deleteCulturalCommentsSuggestions")
    public BaseResult<String> deleteCulturalCommentsSuggestions(@RequestBody CulturalCommentsSuggestionsDeleteForm culturalCommentsSuggestionsDeleteForm){
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = converForm2Dto(culturalCommentsSuggestionsDeleteForm);
        culturalCommentsSuggestionsDto.setIs_delete(1);
        int i = culturalCommentsSuggestionsService.updateCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
//        int i = culturalCommentsSuggestionsService.deleteCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        List<FilePathDeleteForm> filePathDeleteFormList = culturalCommentsSuggestionsDeleteForm.getFilePathDeleteFormList();
        for (FilePathDeleteForm filePathDeleteForm : filePathDeleteFormList){
            FilePathDto filePathDto = converForm2Dto(filePathDeleteForm);
            filePathService.filePathDelete(filePathDto);
        }
        if (i>0) return success("删除成功");
        else return fail(-1,"删除失败");
    }

    @ApiOperation(value = "查询意见建议列表")
    @PostMapping(value = "/queryCulturalCommentsSuggestions")
    public BaseResult<PageInfo<CulturalCommentsSuggestionsQueryVo>> queryCulturalCommentsSuggestions(@RequestBody CulturalCommentsSuggestionsQueryForm culturalCommentsSuggestionsQueryForm){
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = converForm2Dto(culturalCommentsSuggestionsQueryForm);
        PageHelper.startPage(culturalCommentsSuggestionsQueryForm.getPageNum(),culturalCommentsSuggestionsQueryForm.getPageSize());
        List<CulturalCommentsSuggestionsVo> culturalCommentsSuggestionsVoList = culturalCommentsSuggestionsService.queryCulturalCommentsSuggestions(culturalCommentsSuggestionsDto);
        List<CulturalCommentsSuggestionsQueryVo> culturalCommentsSuggestionsQueryVoList = new ArrayList<>();
        FilePathDto filePathDto = new FilePathDto();
        for (CulturalCommentsSuggestionsVo culturalCommentsSuggestionsVo : culturalCommentsSuggestionsVoList){
            CulturalCommentsSuggestionsQueryVo culturalCommentsSuggestionsQueryVo = converCulturalTeamDynamicsQueryVo2Vo(culturalCommentsSuggestionsVo);
            culturalCommentsSuggestionsQueryVo.setCreatedTime(DateUtil.date2String(culturalCommentsSuggestionsVo.getCreatedTime(),"yyyy-MM-dd"));
            filePathDto.setPathId(culturalCommentsSuggestionsVo.getEnclosureId());
            List<FilePathVo> filePathVoList = filePathService.filePathQuery(filePathDto);
            List<FilePathQueryVo> filePathQueryVoList = new ArrayList<>();
            for(FilePathVo filePathVo : filePathVoList){
                FilePathQueryVo filePathQueryVo = converFilePathQueryVo2Vo(filePathVo);
                filePathQueryVoList.add(filePathQueryVo);
            }
            culturalCommentsSuggestionsQueryVo.setFilePathQueryVoList(filePathQueryVoList);
            culturalCommentsSuggestionsQueryVoList.add(culturalCommentsSuggestionsQueryVo);
        }
        PageInfo<CulturalCommentsSuggestionsQueryVo> culturalCommentsSuggestionsQueryVoPageInfo = new PageInfo<>(culturalCommentsSuggestionsQueryVoList);
        return success(culturalCommentsSuggestionsQueryVoPageInfo);
    }

    @ApiOperation(value = "意见建议列表导出")
    @PostMapping(value = "/exportExcelCulturalCommentsSuggestions")
    public BaseResult<String> exportExcelCulturalCommentsSuggestions(@RequestBody CulturalCommentsSuggestionsExportExcelForm culturalCommentsSuggestionsExportExcelForm, HttpServletResponse response){
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = converForm2Dto(culturalCommentsSuggestionsExportExcelForm);
        culturalCommentsSuggestionsService.exportExcelCulturalCommentsSuggestions(culturalCommentsSuggestionsDto,response);
        return success("导出成功");
    }

    @ApiOperation(value = "添加意见建议附件")
    @PostMapping(value = "/addCulturalCommentsSuggestionsEnclosure")
    public BaseResult addCulturalCommentsSuggestionsEnclosure(@RequestParam("file") MultipartFile file, @RequestParam("enclosureId") String enclosureId){
        String appointPath = "file/";

        //后续添加校验

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

        String enclosurePath = FilePathUtil.addFilePath(file, suffix, appointPath);

        FilePathDto filePathDto= new FilePathDto();
        filePathDto.setFileName(file.getOriginalFilename());
        filePathDto.setType("commentSuggestionEnclosure");
        filePathDto.setPath(enclosurePath);
        filePathDto.setPathId(enclosureId);

        FilePathVo filePathVo = filePathService.filePathUpload(filePathDto);
        if (filePathVo != null) return success("上传成功");
        else return fail(-1,"上传失败");
    }

    @ApiOperation(value = "添加意见建议模板")
    @PostMapping(value = "/addCulturalCommentsSuggestionsEnclosureTemplate")
    public BaseResult addCulturalCommentsSuggestionsEnclosureTemplate(@RequestParam("file") MultipartFile file){
        String appointPath = "template/";

        //后续添加校验

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

        String enclosurePath = FilePathUtil.addFilePath(file, suffix, appointPath);

        FilePathDto filePathDto= new FilePathDto();
        filePathDto.setFileName(file.getOriginalFilename());
        filePathDto.setType("commentSuggestionEnclosureTemplate");
        filePathDto.setPath(enclosurePath);
        filePathDto.setPathId("template-"+ UUID.randomUUID().toString());

        FilePathVo filePathVo = filePathService.filePathUpload(filePathDto);
        if (filePathVo != null) return success("上传成功");
        else return fail(-1,"上传失败");
    }

    @ApiOperation(value = "意见建议附件模板查询")
    @RequestMapping("/queryCulturalCommentsSuggestionsEnclosureTemplate")
    public BaseResult<List<FilePathQueryVo>> queryCulturalCommentsSuggestionsEnclosureTemplate() {
        FilePathDto filePathDto = new FilePathDto();
        filePathDto.setType("commentSuggestionEnclosureTemplate");
        List<FilePathVo> filePathVoList = filePathService.filePathQuery(filePathDto);
        List<FilePathQueryVo> filePathQueryVoList = new ArrayList<>();
        for (FilePathVo filePathVo : filePathVoList){
            FilePathQueryVo filePathQueryVo = converFilePathQueryVo2Vo(filePathVo);
            filePathQueryVoList.add(filePathQueryVo);
        }
        return success(filePathQueryVoList);
    }



    public static CulturalCommentsSuggestionsDto converForm2Dto(CulturalCommentsSuggestionsAddForm form) {
        if (form == null) {
            return null;
        }
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = new CulturalCommentsSuggestionsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalCommentsSuggestionsDto);
        return culturalCommentsSuggestionsDto;
    }

    public static CulturalCommentsSuggestionsDto converForm2Dto(CulturalCommentsSuggestionsUpdateForm form) {
        if (form == null) {
            return null;
        }
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = new CulturalCommentsSuggestionsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalCommentsSuggestionsDto);
        return culturalCommentsSuggestionsDto;
    }

    public static CulturalCommentsSuggestionsDto converForm2Dto(CulturalCommentsSuggestionsDeleteForm form) {
        if (form == null) {
            return null;
        }
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = new CulturalCommentsSuggestionsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalCommentsSuggestionsDto);
        return culturalCommentsSuggestionsDto;
    }

    public static FilePathDto converForm2Dto(FilePathDeleteForm form) {
        if (form == null) {
            return null;
        }
        FilePathDto filePathDto = new FilePathDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,filePathDto);
        return filePathDto;
    }

    public static CulturalCommentsSuggestionsDto converForm2Dto(CulturalCommentsSuggestionsExportExcelForm form) {
        if (form == null) {
            return null;
        }
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = new CulturalCommentsSuggestionsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalCommentsSuggestionsDto);
        return culturalCommentsSuggestionsDto;
    }

    public static CulturalCommentsSuggestionsDto converForm2Dto(CulturalCommentsSuggestionsQueryForm form) {
        if (form == null) {
            return null;
        }
        CulturalCommentsSuggestionsDto culturalCommentsSuggestionsDto = new CulturalCommentsSuggestionsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalCommentsSuggestionsDto);
        return culturalCommentsSuggestionsDto;
    }

    public static CulturalCommentsSuggestionsQueryVo converCulturalTeamDynamicsQueryVo2Vo(CulturalCommentsSuggestionsVo vo) {
        if (vo == null) {
            return null;
        }
        CulturalCommentsSuggestionsQueryVo culturalCommentsSuggestionsQueryVo = new CulturalCommentsSuggestionsQueryVo();
        BeanUtil.copyProperties(vo,culturalCommentsSuggestionsQueryVo);
        return culturalCommentsSuggestionsQueryVo;
    }

    public static FilePathQueryVo converFilePathQueryVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        FilePathQueryVo filePathQueryVo = new FilePathQueryVo();
        BeanUtil.copyProperties(vo,filePathQueryVo);
        return filePathQueryVo;
    }
}

