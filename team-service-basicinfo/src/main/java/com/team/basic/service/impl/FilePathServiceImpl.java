package com.team.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.FilePathVo;
import com.team.basic.dao.FilePathMapper;
import com.team.basic.service.FilePathService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * <p>
 * 文件路径表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class FilePathServiceImpl extends ServiceImpl<FilePathMapper, FilePathVo> implements FilePathService {

    @Resource
    private FilePathMapper filePathMapper;

    @Override
    public Integer filePathDelete(FilePathDto filePathDto) {
        int i = filePathMapper.deleteById(filePathDto.getId());
        //获取项目classes/static的地址
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        new File(path + filePathDto.getPath()).delete();
        return i;
    }

    @Override
    public List<FilePathVo> filePathQuery(FilePathDto filePathDto) {
        return filePathMapper.filePathQuery(filePathDto);
    }

    @Override
    public FilePathVo filePathUpload(FilePathDto filePathDto) {
        filePathDto.setIs_delete(0);
        filePathMapper.filePathUpload(filePathDto);
        List<FilePathVo> filePathVoList = filePathMapper.filePathQuery(filePathDto);
        return filePathVoList.get(0);
    }
}
