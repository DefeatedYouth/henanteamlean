package com.team.basic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.team.basic.dao.CultureRelStudyPersonMapper;
import com.team.basic.dao.CultureStudyDocumentMapper;
import com.team.basic.dto.basicinfo.*;
import com.team.basic.entity.CultureStudyDocumentVo;
import com.team.basic.service.CultureStudyDocumentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 学习文件表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class CultureStudyDocumentServiceImpl extends ServiceImpl<CultureStudyDocumentMapper, CultureStudyDocumentVo> implements CultureStudyDocumentService {
    @Resource
    private CultureStudyDocumentMapper cultureStudyDocumentMapper;
    @Resource
    private CultureRelStudyPersonMapper cultureRelStudyPersonMapper;

    /*@Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateStudyDocument(StudyDocumenUpdateDto studyDocumenUpdateDto) {
        boolean b = cultureStudyDocumentMapper.updateStudyDocument(studyDocumenUpdateDto);
        Integer documentId = studyDocumenUpdateDto.getDocumentId();
        boolean b1 = cultureRelStudyPersonMapper.deleteStudyPerson(documentId);
        for (Map<String, String> studyPerson : studyDocumenUpdateDto.getStudyPerson()) {
            studyPerson.entrySet().forEach(e -> {
                String key = e.getKey();
                String value = e.getValue();
                Integer documentId1 = studyDocumenUpdateDto.getDocumentId();
                boolean b3 = cultureRelStudyPersonMapper.insertStudyPerson(key, value, documentId1);
            });
        }
        return b && b1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteStudyDocument(Integer documentId) {
        boolean b = cultureStudyDocumentMapper.deleteStudyDocumentById(documentId);
        boolean b1 = cultureRelStudyPersonMapper.deleteStudyPersonById(documentId);
        return b && b1;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean ReleaseStudyDocument(Integer documentId) {
        Date date = new Date();
        boolean isRelease = cultureStudyDocumentMapper.ReleaseStudyDocument(documentId, date);
        return isRelease;
    }

    @Override
    public boolean cancelReleaseStudyDocument(Integer documentId) {
        boolean isCancelRelease = cultureStudyDocumentMapper.cancelReleaseStudyDocument(documentId);

        return isCancelRelease;
    }*/

    /*@Override
    public List<StudyDocumentShowDto> studyShowMonitor(String userId) {
        List<StudyDocumentShowDto> studyDocumentShowDtos = cultureStudyDocumentMapper.studyShowMonitor(userId);
        List<StudyDocumentShowDto> collect = studyDocumentShowDtos.stream().limit(4).collect(Collectors.toList());
        return collect;
    }

    @Override
    public List<StudyDocumentShowDto> studyShowMember(String userId) {
        List<StudyDocumentShowDto> studyDocumentShowDtos = cultureStudyDocumentMapper.studyShowMember(userId);
        List<StudyDocumentShowDto> collect = studyDocumentShowDtos.stream().limit(4).collect(Collectors.toList());
        return collect;
    }

    @Override
    public StudyDocumentStudyDto studyList(StudyDocumentStudyListAfferentDto studyDocumentStudyListAfferentDto) {
        PageHelper.startPage(studyDocumentStudyListAfferentDto.getPageNum(), studyDocumentStudyListAfferentDto.getPageRows());
        List<StudyDocumentStudyListDto> studyDocumentStudyListDtos = cultureStudyDocumentMapper.studyList(studyDocumentStudyListAfferentDto);
        PageInfo<StudyDocumentStudyListDto> info = new PageInfo<>(studyDocumentStudyListDtos);

        Integer totalCount = cultureRelStudyPersonMapper.selectCount(new LambdaQueryWrapper<CultureRelStudyPersonVo>()
                .eq(CultureRelStudyPersonVo::getPersonId, studyDocumentStudyListAfferentDto.getUserId()));
        Integer alreadyCount = cultureRelStudyPersonMapper.selectCount(new LambdaQueryWrapper<CultureRelStudyPersonVo>()
                .eq(CultureRelStudyPersonVo::getPersonId, studyDocumentStudyListAfferentDto.getUserId())
                .eq(CultureRelStudyPersonVo::getIsStudy, 1));
        Integer notCount = totalCount - alreadyCount;

        StudyDocumentStudyDto result = new StudyDocumentStudyDto();

        *//*info.getList().forEach(e ->{
            String accessory = e.getAccessory();
            String fileName = accessory.substring(accessory.lastIndexOf("/") + 1);
            fileNames = fileNames + "," + fileName;
        });*//*

        result.setStudyDocumentStudyListVos(info.getList());
        result.setPageNum(info.getPageNum());
        result.setPageSize(info.getSize());
        result.setTotal(info.getTotal());
        result.setAlreadyCount(alreadyCount);
        result.setTotalCount(totalCount);
        result.setNotCount(notCount);
        return result;
    }*/
}
