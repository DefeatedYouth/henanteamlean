package com.team.work.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team.work.dto.WorkListPageResultDto;
import com.team.work.entity.WorkModelVo;
import com.team.work.dao.WorkModelMapper;
import com.team.work.service.WorkModelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 工单模板表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
@Slf4j
public class WorkModelServiceImpl extends ServiceImpl<WorkModelMapper, WorkModelVo> implements WorkModelService {

    @Override
    public Page<WorkModelVo> queryList(Integer pageNum, Integer pageSize) {
        Page<WorkModelVo> producePage = new Page<>(pageNum,pageSize);
        QueryWrapper<WorkModelVo> qw = new QueryWrapper<WorkModelVo>()
                .eq("DELETED", 0).orderByDesc("CREATED_TIME");
        Page<WorkModelVo> workModelVoPage = this.baseMapper.selectPage(producePage,qw);
        return workModelVoPage;
    }

    @Override
    public Boolean saveWorkModel(WorkModelVo entity) {
        entity.setCreatedTime(new Date());
        entity.setCreatedBy("admin");
        entity.setModelNo("MB"+ RandomUtil.randomNumbers(10));
        entity.setTeamId(1);
        entity.setStationId(1);
        int insert = this.baseMapper.insert(entity);
        log.info("saveWorkModel模板保存{}",insert);
        return true;
    }

    @Override
    public Boolean updateWorkModel(WorkModelVo entity) {
        entity.setUpdatedBy("admin");
        entity.setUpdatedTime(new Date());
        int update = this.baseMapper.updateById(entity);
        log.info("updateWorkModel模板修改{}",update);
        return true;
    }

    @Override
    public Boolean deleteWorkModel(WorkModelVo entity) {
        entity.setUpdatedBy("admin");
        entity.setUpdatedTime(new Date());
        entity.setDeleted(1);
        int update = this.baseMapper.updateById(entity);
        log.info("updateWorkModel模板删除{}",update);
        return true;
    }

    @Override
    public List<WorkModelVo> queryPushModel() {
        QueryWrapper<WorkModelVo> qw = new QueryWrapper<WorkModelVo>()
                .eq("DELETED", 0)
                .eq("PERIOD_STATUS",1);
        return this.baseMapper.selectList(qw);
    }
}
