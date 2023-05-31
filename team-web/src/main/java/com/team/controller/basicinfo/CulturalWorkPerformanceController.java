package com.team.controller.basicinfo;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.basic.dto.CulturalWorkPerformanceDto;
import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.CulturalWorkPerformanceVo;
import com.team.basic.entity.FilePathVo;
import com.team.basic.service.CulturalWorkPerformanceService;
import com.team.basic.service.FilePathService;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.DateUtil;
import com.team.common.util.FilePathUtil;
import com.team.form.basicinfo.*;
import com.team.vo.basicinfo.CulturalWorkPerformanceQueryVo;
import com.team.vo.basicinfo.FilePathQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 工作业绩表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@CrossOrigin
@RestController
@RequestMapping("/culturalWorkPerformanceVo")
public class CulturalWorkPerformanceController extends BaseController {

    @Resource
    private CulturalWorkPerformanceService culturalWorkPerformanceService;

    @Resource
    private FilePathService filePathService;

    @ApiOperation(value = "工作业绩添加")
    @PostMapping(value = "/addCulturalWorkPerformance")
    public BaseResult<String> addCulturalWorkPerformance(@RequestBody CulturalWorkPerformanceAddForm culturalWorkPerformanceAddForm){
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = converForm2Dto(culturalWorkPerformanceAddForm);
        culturalWorkPerformanceDto.setStartTime(DateUtil.parseDate(culturalWorkPerformanceAddForm.getStartTime(),"yyyy-MM-dd"));
        culturalWorkPerformanceDto.setEndTime(DateUtil.parseDate(culturalWorkPerformanceAddForm.getEndTime(),"yyyy-MM-dd"));
        int i = culturalWorkPerformanceService.addCulturalWorkPerformance(culturalWorkPerformanceDto);
        if (i>0) return success("添加成功");
        else return fail(-1,"添加失败");
    }

    @ApiOperation(value = "工作业绩更新")
    @PostMapping(value = "/updateCulturalWorkPerformance")
    public BaseResult<String> updateCulturalWorkPerformance(@RequestBody CulturalWorkPerformanceUpdateForm culturalWorkPerformanceUpdateForm){
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = converForm2Dto(culturalWorkPerformanceUpdateForm);
        culturalWorkPerformanceDto.setStartTime(DateUtil.parseDate(culturalWorkPerformanceUpdateForm.getStartTime(),"yyyy-MM-dd"));
        culturalWorkPerformanceDto.setEndTime(DateUtil.parseDate(culturalWorkPerformanceUpdateForm.getEndTime(),"yyyy-MM-dd"));
        int i = culturalWorkPerformanceService.updateCulturalWorkPerformance(culturalWorkPerformanceDto);
        if (i>0) return success("修改成功");
        else return fail(-1,"修改失败");
    }

    @ApiOperation(value = "工作业绩删除")
    @PostMapping(value = "/deleteCulturalWorkPerformance")
    public BaseResult<String> deleteCulturalWorkPerformance(@RequestBody CulturalWorkPerformanceDeleteForm culturalWorkPerformanceDeleteForm){
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = converForm2Dto(culturalWorkPerformanceDeleteForm);
        culturalWorkPerformanceDto.setIs_delete(1);
        int i = culturalWorkPerformanceService.updateCulturalWorkPerformance(culturalWorkPerformanceDto);
//        int i = culturalWorkPerformanceService.deleteCulturalWorkPerformance(culturalWorkPerformanceDto);
        List<FilePathDeleteForm> filePathDeleteFormList = culturalWorkPerformanceDeleteForm.getFilePathDeleteFormList();
        for (FilePathDeleteForm filePathDeleteForm : filePathDeleteFormList){
            FilePathDto filePathDto = converForm2Dto(filePathDeleteForm);
            filePathService.filePathDelete(filePathDto);
        }
        if (i>0) return success("删除成功");
        else return fail(-1,"删除失败");
    }

    @ApiOperation(value = "查询工作业绩列表")
    @PostMapping(value = "/queryCulturalWorkPerformance")
    public BaseResult<PageInfo<CulturalWorkPerformanceQueryVo>> queryCulturalWorkPerformance(@RequestBody CulturalWorkPerformanceQueryForm culturalWorkPerformanceQueryForm) {
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = converForm2Dto(culturalWorkPerformanceQueryForm);
        PageHelper.startPage(culturalWorkPerformanceQueryForm.getPageNum(),culturalWorkPerformanceQueryForm.getPageSize());
        List<CulturalWorkPerformanceVo> culturalWorkPerformanceVoList = culturalWorkPerformanceService.queryCulturalWorkPerformance(culturalWorkPerformanceDto);
        List<CulturalWorkPerformanceQueryVo> culturalWorkPerformanceQueryVoList = new ArrayList<>();
        FilePathDto filePathDto = new FilePathDto();
        for (CulturalWorkPerformanceVo culturalWorkPerformanceVo : culturalWorkPerformanceVoList){
            CulturalWorkPerformanceQueryVo culturalWorkPerformanceQueryVo = converCulturalWorkPerformanceQueryVo2Vo(culturalWorkPerformanceVo);
            culturalWorkPerformanceQueryVo.setStartTime(DateUtil.date2String(culturalWorkPerformanceVo.getStartTime(),"yyyy-MM-dd"));
            culturalWorkPerformanceQueryVo.setEndTime(DateUtil.date2String(culturalWorkPerformanceVo.getEndTime(),"yyyy-MM-dd"));
            filePathDto.setPathId(culturalWorkPerformanceVo.getSupportingMaterialsId());
            List<FilePathVo> filePathVoList = filePathService.filePathQuery(filePathDto);
            List<FilePathQueryVo> filePathQueryVoList = new ArrayList<>();
            for(FilePathVo filePathVo : filePathVoList){
                FilePathQueryVo filePathQueryVo = converFilePathQueryVo2Vo(filePathVo);
                filePathQueryVoList.add(filePathQueryVo);
            }
            culturalWorkPerformanceQueryVo.setFilePathQueryVoList(filePathQueryVoList);
            culturalWorkPerformanceQueryVoList.add(culturalWorkPerformanceQueryVo);
        }
        PageInfo<CulturalWorkPerformanceQueryVo> culturalWorkPerformanceQueryVoPageInfo = new PageInfo<>(culturalWorkPerformanceQueryVoList);
        return success(culturalWorkPerformanceQueryVoPageInfo);
    }

    @ApiOperation(value = "工作业绩列表导出")
    @PostMapping(value = "/exportExcelCulturalWorkPerformance")
    public BaseResult<String> exportExcelCulturalWorkPerformance(@RequestBody CulturalWorkPerformanceExportExcelForm culturalWorkPerformanceExportExcelForm, HttpServletResponse response){
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = converForm2Dto(culturalWorkPerformanceExportExcelForm);
        culturalWorkPerformanceService.exportExcelCulturalWorkPerformance(culturalWorkPerformanceDto,response);
        return success("导出成功");
    }

    @ApiOperation(value = "添加工作业绩证明材料")
    @PostMapping(value = "/addCulturalCommentsSuggestionsSupportingMaterials")
    public BaseResult addCulturalCommentsSuggestionsSupportingMaterials(@RequestParam("file") MultipartFile file, @RequestParam("supportingMaterialsId") String supportingMaterialsId){
        String appointPath = "file/";

        //后续添加校验

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

        String supportingMaterialsPath = FilePathUtil.addFilePath(file, suffix, appointPath);

        FilePathDto filePathDto= new FilePathDto();
        filePathDto.setFileName(file.getOriginalFilename());
        filePathDto.setType("commentsSuggestionsSupportingMaterials");
        filePathDto.setPath(supportingMaterialsPath);
        filePathDto.setPathId(supportingMaterialsId);

        FilePathVo filePathVo = filePathService.filePathUpload(filePathDto);
        if (filePathVo != null) return success("上传成功");
        else return fail(-1,"上传失败");
    }


    public static CulturalWorkPerformanceDto converForm2Dto(CulturalWorkPerformanceAddForm form) {
        if (form == null) {
            return null;
        }
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = new CulturalWorkPerformanceDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalWorkPerformanceDto);
        return culturalWorkPerformanceDto;
    }

    public static CulturalWorkPerformanceDto converForm2Dto(CulturalWorkPerformanceUpdateForm form) {
        if (form == null) {
            return null;
        }
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = new CulturalWorkPerformanceDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalWorkPerformanceDto);
        return culturalWorkPerformanceDto;
    }

    public static CulturalWorkPerformanceDto converForm2Dto(CulturalWorkPerformanceDeleteForm form) {
        if (form == null) {
            return null;
        }
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = new CulturalWorkPerformanceDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalWorkPerformanceDto);
        return culturalWorkPerformanceDto;
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

    public static CulturalWorkPerformanceDto converForm2Dto(CulturalWorkPerformanceQueryForm form) {
        if (form == null) {
            return null;
        }
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = new CulturalWorkPerformanceDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalWorkPerformanceDto);
        return culturalWorkPerformanceDto;
    }

    public static CulturalWorkPerformanceQueryVo converCulturalWorkPerformanceQueryVo2Vo(CulturalWorkPerformanceVo vo) {
        if (vo == null) {
            return null;
        }
        CulturalWorkPerformanceQueryVo culturalWorkPerformanceQueryVo = new CulturalWorkPerformanceQueryVo();
        BeanUtil.copyProperties(vo,culturalWorkPerformanceQueryVo);
        return culturalWorkPerformanceQueryVo;
    }

    public static FilePathQueryVo converFilePathQueryVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        FilePathQueryVo filePathQueryVo = new FilePathQueryVo();
        BeanUtil.copyProperties(vo,filePathQueryVo);
        return filePathQueryVo;
    }

    public static CulturalWorkPerformanceDto converForm2Dto(CulturalWorkPerformanceExportExcelForm form) {
        if (form == null) {
            return null;
        }
        CulturalWorkPerformanceDto culturalWorkPerformanceDto = new CulturalWorkPerformanceDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalWorkPerformanceDto);
        return culturalWorkPerformanceDto;
    }
}

