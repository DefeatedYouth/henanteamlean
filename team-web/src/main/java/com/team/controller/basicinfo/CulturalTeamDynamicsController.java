package com.team.controller.basicinfo;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.basic.dto.CulturalTeamDynamicsDto;
import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.CulturalTeamDynamicsVo;
import com.team.basic.entity.FilePathVo;
import com.team.basic.service.CulturalTeamDynamicsService;
import com.team.basic.service.FilePathService;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.DateUtil;
import com.team.common.util.FilePathUtil;
import com.team.form.basicinfo.*;
import com.team.vo.basicinfo.CulturalTeamDynamicsPictureAddVo;
import com.team.vo.basicinfo.CulturalTeamDynamicsQueryVo;
import com.team.vo.basicinfo.FilePathQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 班组动态表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/culturalTeamDynamicsVo")
public class CulturalTeamDynamicsController extends BaseController {

    @Resource
    private CulturalTeamDynamicsService culturalTeamDynamicsService;

    @Resource
    private FilePathService filePathService;


    @ApiOperation(value = "班组动态添加")
    @PostMapping(value = "/addCulturalTeamDynamics")
    public BaseResult<String> addCulturalTeamDynamics(@RequestBody CulturalTeamDynamicsAddForm addCulturalTeamDynamicsForm){
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = converForm2Dto(addCulturalTeamDynamicsForm);
        int i = culturalTeamDynamicsService.addCulturalTeamDynamics(culturalTeamDynamicsDto);
        if (i>0) return success("添加成功");
        else return fail(-1,"添加失败");
    }

    @ApiOperation(value = "班组动态更新")
    @PostMapping(value = "/updateCulturalTeamDynamics")
    public BaseResult<String> updateCulturalTeamDynamics(@RequestBody CulturalTeamDynamicsUpdateForm updateCulturalTeamDynamicsForm){
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = converForm2Dto(updateCulturalTeamDynamicsForm);
        int i = culturalTeamDynamicsService.updateCulturalTeamDynamics(culturalTeamDynamicsDto);
        if (i>0) return success("修改成功");
        else return fail(-1,"修改失败");
    }

    @ApiOperation(value = "班组动态删除")
    @PostMapping(value = "/deleteCulturalTeamDynamics")
    public BaseResult<String> deleteCulturalTeamDynamics(@RequestBody CulturalTeamDynamicsDeleteForm deleteCulturalTeamDynamicsForm){
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = converForm2Dto(deleteCulturalTeamDynamicsForm);
        culturalTeamDynamicsDto.setIs_delete(1);
        int i = culturalTeamDynamicsService.updateCulturalTeamDynamics(culturalTeamDynamicsDto);
//        int i = culturalTeamDynamicsService.deleteCulturalTeamDynamics(culturalTeamDynamicsDto);
        List<FilePathDeleteForm> filePathDeleteFormList = deleteCulturalTeamDynamicsForm.getFilePathDeleteFormList();
        for (FilePathDeleteForm filePathDeleteForm : filePathDeleteFormList){
            FilePathDto filePathDto = converForm2Dto(filePathDeleteForm);
            filePathService.filePathDelete(filePathDto);
        }
        if (i>0) return success("删除成功");
        else return fail(-1,"删除失败");
    }

    @ApiOperation(value = "查询班组动态列表")
    @PostMapping(value = "/queryCulturalTeamDynamics")
    public BaseResult<PageInfo<CulturalTeamDynamicsQueryVo>> queryCulturalTeamDynamics(@RequestBody CulturalTeamDynamicsQueryForm queryCulturalTeamDynamicsForm){
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = converForm2Dto(queryCulturalTeamDynamicsForm);
        PageHelper.startPage(queryCulturalTeamDynamicsForm.getPageNum(),queryCulturalTeamDynamicsForm.getPageSize());
        List<CulturalTeamDynamicsVo> culturalTeamDynamicsVoList = culturalTeamDynamicsService.queryCulturalTeamDynamics(culturalTeamDynamicsDto);
        List<CulturalTeamDynamicsQueryVo> queryCulturalTeamDynamicsVoList = new ArrayList<>();
        FilePathDto filePathDto = new FilePathDto();
        for (CulturalTeamDynamicsVo culturalTeamDynamicsVo : culturalTeamDynamicsVoList){
            CulturalTeamDynamicsQueryVo queryCulturalTeamDynamicsVo = converCulturalTeamDynamicsQueryVo2Vo(culturalTeamDynamicsVo);
            queryCulturalTeamDynamicsVo.setCreatedTime(DateUtil.date2String(culturalTeamDynamicsVo.getCreatedTime(),"yyyy-MM-dd"));
            filePathDto.setPathId(culturalTeamDynamicsVo.getPictureId());
            List<FilePathVo> filePathVoList = filePathService.filePathQuery(filePathDto);
            List<FilePathQueryVo> filePathQueryVoList = new ArrayList<>();
            for(FilePathVo filePathVo : filePathVoList){
                FilePathQueryVo filePathQueryVo = converFilePathQueryVo2Vo(filePathVo);
                filePathQueryVoList.add(filePathQueryVo);
            }
            queryCulturalTeamDynamicsVo.setFilePathQueryVoList(filePathQueryVoList);
            queryCulturalTeamDynamicsVoList.add(queryCulturalTeamDynamicsVo);
        }
        PageInfo<CulturalTeamDynamicsQueryVo> queryCulturalTeamDynamicsVoPageInfo = new PageInfo<>(queryCulturalTeamDynamicsVoList);
        return success(queryCulturalTeamDynamicsVoPageInfo);
    }

    @ApiOperation(value = "添加班组动态图片")
    @PostMapping(value = "/addCulturalTeamDynamicsPicturePath")
    public BaseResult addCulturalTeamDynamicsPicturePath(@RequestParam("file") MultipartFile file){
        String appointPath = "image/";

        if (file.getSize() > 1024 * 1024 * 10) return fail(-1,"上传文件不能大于10M");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) return fail(-1,"请选择jpg,jpeg,gif,png格式的图片");

        String picturePath = FilePathUtil.addFilePath(file, suffix, appointPath);

        FilePathDto filePathDto= new FilePathDto();
        filePathDto.setFileName(file.getOriginalFilename());
        filePathDto.setType("teamDynamicsPicture");
        filePathDto.setPath(picturePath);

        FilePathVo filePathVo = filePathService.filePathUpload(filePathDto);
        CulturalTeamDynamicsPictureAddVo culturalTeamDynamicsPictureAddVo = converCulturalTeamDynamicsPictureAddVo2Vo(filePathVo);
        return success(culturalTeamDynamicsPictureAddVo);
    }





    public static CulturalTeamDynamicsDto converForm2Dto(CulturalTeamDynamicsAddForm form) {
        if (form == null) {
            return null;
        }
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = new CulturalTeamDynamicsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalTeamDynamicsDto);
        return culturalTeamDynamicsDto;
    }

    public static CulturalTeamDynamicsDto converForm2Dto(CulturalTeamDynamicsUpdateForm form) {
        if (form == null) {
            return null;
        }
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = new CulturalTeamDynamicsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalTeamDynamicsDto);
        return culturalTeamDynamicsDto;
    }

    public static CulturalTeamDynamicsDto converForm2Dto(CulturalTeamDynamicsDeleteForm form) {
        if (form == null) {
            return null;
        }
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = new CulturalTeamDynamicsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalTeamDynamicsDto);
        return culturalTeamDynamicsDto;
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

    public static CulturalTeamDynamicsDto converForm2Dto(CulturalTeamDynamicsQueryForm form) {
        if (form == null) {
            return null;
        }
        CulturalTeamDynamicsDto culturalTeamDynamicsDto = new CulturalTeamDynamicsDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,culturalTeamDynamicsDto);
        return culturalTeamDynamicsDto;
    }


    public static CulturalTeamDynamicsQueryVo converCulturalTeamDynamicsQueryVo2Vo(CulturalTeamDynamicsVo vo) {
        if (vo == null) {
            return null;
        }
        CulturalTeamDynamicsQueryVo queryCulturalTeamDynamicsVo = new CulturalTeamDynamicsQueryVo();
        BeanUtil.copyProperties(vo,queryCulturalTeamDynamicsVo);
        return queryCulturalTeamDynamicsVo;
    }

    public static FilePathQueryVo converFilePathQueryVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        FilePathQueryVo filePathQueryVo = new FilePathQueryVo();
        BeanUtil.copyProperties(vo,filePathQueryVo);
        return filePathQueryVo;
    }

    public static CulturalTeamDynamicsPictureAddVo converCulturalTeamDynamicsPictureAddVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        CulturalTeamDynamicsPictureAddVo culturalTeamDynamicsPictureAddVo = new CulturalTeamDynamicsPictureAddVo();
        BeanUtil.copyProperties(vo,culturalTeamDynamicsPictureAddVo);
        return culturalTeamDynamicsPictureAddVo;
    }

}

