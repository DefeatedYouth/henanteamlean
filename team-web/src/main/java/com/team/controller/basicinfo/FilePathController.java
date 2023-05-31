package com.team.controller.basicinfo;


import cn.hutool.core.bean.BeanUtil;
import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.FilePathVo;
import com.team.basic.service.FilePathService;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.FilePathUtil;
import com.team.form.basicinfo.FilePathDeleteForm;
import com.team.form.basicinfo.FilePathQueryForm;
import com.team.vo.basicinfo.FilePathQueryVo;
import com.team.vo.basicinfo.FilePathUploadVo;
import io.swagger.annotations.ApiOperation;
import org.jodconverter.DocumentConverter;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 文件路径表 前端控制器
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@RestController
@RequestMapping("/filePathVo")
public class FilePathController extends BaseController {

    @Resource
    private FilePathService filePathService;

    @ApiOperation(value = "文件在线预览")
    @RequestMapping("/viewByPDF")
    public void viewByPDF(@RequestParam("filePath") String filePath, HttpServletResponse response, DocumentConverter converter) {
        //获取项目classes/static的地址
        String path = ClassUtils.getDefaultClassLoader().getResource("static/").getPath();
        String prePDF = path+"prePDF/";
        String filePaths = path+filePath;
        FilePathUtil.viewByPDF(filePaths,prePDF,response,converter);
    }

    @ApiOperation(value = "文件下载")
    @RequestMapping("/download")
    public void download(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        //获取项目classes/static的地址
        String path = ClassUtils.getDefaultClassLoader().getResource("static/").getPath();
        String filePaths = path+filePath;
        FilePathUtil.download(filePaths,response);
    }

    @ApiOperation(value = "文件删除")
    @RequestMapping("/filePathDelete")
    public BaseResult<String> filePathDelete(@RequestBody FilePathDeleteForm filePathDeleteForm) {
        FilePathDto filePathDto = converForm2Dto(filePathDeleteForm);
        int i = filePathService.filePathDelete(filePathDto);
        if (i>0) return success("删除成功");
        else return fail(-1,"删除失败");
    }


    @ApiOperation(value = "文件查询")
    @RequestMapping("/filePathQuery")
    public BaseResult<List<FilePathQueryVo>> filePathQuery(@RequestBody FilePathQueryForm filePathQueryForm) {
        FilePathDto filePathDto = converForm2Dto(filePathQueryForm);
        List<FilePathVo> filePathVoList = filePathService.filePathQuery(filePathDto);
        List<FilePathQueryVo> filePathQueryVoList = new ArrayList<>();
        for (FilePathVo filePathVo : filePathVoList){
            FilePathQueryVo filePathQueryVo = converFilePathQueryVo2Vo(filePathVo);
            filePathQueryVoList.add(filePathQueryVo);
        }
        return success(filePathQueryVoList);
    }

    @ApiOperation(value = "文件上传")
    @RequestMapping("/filePathUpload")
    public BaseResult<FilePathUploadVo> filePathUpload(@RequestParam("file") MultipartFile file) {
        String appointPath = "file/";
        //后续添加验证

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());
        String filePath = FilePathUtil.addFilePath(file,suffix,appointPath);

        FilePathDto filePathDto= new FilePathDto();
        filePathDto.setFileName(file.getOriginalFilename());
        filePathDto.setType("file");
        filePathDto.setPathId("file-"+ UUID.randomUUID().toString());
        filePathDto.setPath(filePath);

        FilePathVo filePathVo = filePathService.filePathUpload(filePathDto);
        FilePathUploadVo filePathUploadVo = converFilePathUploadVo2Vo(filePathVo);
        return success(filePathUploadVo);
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

    public static FilePathDto converForm2Dto(FilePathQueryForm form) {
        if (form == null) {
            return null;
        }
        FilePathDto filePathDto = new FilePathDto();
        form.verifyParam();
        BeanUtil.copyProperties(form,filePathDto);
        return filePathDto;
    }

    public static FilePathQueryVo converFilePathQueryVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        FilePathQueryVo filePathQueryVo = new FilePathQueryVo();
        BeanUtil.copyProperties(vo,filePathQueryVo);
        return filePathQueryVo;
    }

    public static FilePathUploadVo converFilePathUploadVo2Vo(FilePathVo vo) {
        if (vo == null) {
            return null;
        }
        FilePathUploadVo filePathUploadVo = new FilePathUploadVo();
        BeanUtil.copyProperties(vo,filePathUploadVo);
        return filePathUploadVo;
    }


}

