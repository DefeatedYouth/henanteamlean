package com.team.basic.dao;

import com.team.basic.dto.FilePathDto;
import com.team.basic.entity.FilePathVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 文件路径表 Mapper 接口
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface FilePathMapper extends BaseMapper<FilePathVo> {

    List<FilePathVo> filePathQuery(FilePathDto filePathDto);

    Integer filePathUpload(FilePathDto filePathDto);

}
