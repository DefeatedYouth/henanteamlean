package com.team.basic.service;

import com.team.basic.dto.basicinfo.StudyDocumenUpdateDto;
import com.team.basic.dto.basicinfo.StudyDocumentShowDto;
import com.team.basic.dto.basicinfo.StudyDocumentStudyDto;
import com.team.basic.dto.basicinfo.StudyDocumentStudyListAfferentDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team.basic.entity.CultureStudyDocumentVo;

import java.util.List;

/**
 * <p>
 * 学习文件表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CultureStudyDocumentService extends IService<CultureStudyDocumentVo> {

  /*  boolean updateStudyDocument(StudyDocumenUpdateDto studyDocumenUpdateDto);

    boolean deleteStudyDocument(Integer documentId);

    boolean ReleaseStudyDocument(Integer documentId);

    boolean cancelReleaseStudyDocument(Integer documentId);

    List<StudyDocumentShowDto> studyShowMonitor(String userId);

    List<StudyDocumentShowDto> studyShowMember(String userId);

    StudyDocumentStudyDto studyList(StudyDocumentStudyListAfferentDto studyDocumentStudyListAfferentDto);*/
}
