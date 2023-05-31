package com.team.basic.service;

import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.FilePathVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文件路径表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface FilePathService extends IService<FilePathVo> {

    Integer filePathDelete(FilePathDto filePathDto);

    List<FilePathVo> filePathQuery(FilePathDto filePathDto);

    FilePathVo filePathUpload(FilePathDto filePathDto);

}
