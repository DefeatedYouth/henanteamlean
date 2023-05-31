package com.team.basic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.team.basic.dao.FilePathMapper;
import com.team.basic.dto.CulturalTeamDynamicsDto;
import com.team.basic.entity.CulturalTeamDynamicsVo;
import com.team.basic.dao.CulturalTeamDynamicsMapper;
import com.team.basic.entity.FilePathVo;
import com.team.basic.service.CulturalTeamDynamicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 班组动态表 服务实现类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Service
public class CulturalTeamDynamicsServiceImpl extends ServiceImpl<CulturalTeamDynamicsMapper, CulturalTeamDynamicsVo> implements CulturalTeamDynamicsService {

    @Resource
    private CulturalTeamDynamicsMapper culturalTeamDynamicsMapper;

    @Resource
    private FilePathMapper filePathMapper;

    @Override
    public Integer addCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto) {
        culturalTeamDynamicsDto.setIs_delete(0);
        culturalTeamDynamicsDto.setPictureId("teamDynamics-" + UUID.randomUUID().toString());
        culturalTeamDynamicsDto.setCreatedId("1");        //后续重写获得创建人方法
        culturalTeamDynamicsDto.setCreatedBy("zp");
        culturalTeamDynamicsDto.setCreatedTime(DateUtil.getDate());
        int i = culturalTeamDynamicsMapper.addCulturalTeamDynamics(culturalTeamDynamicsDto);

        List<Integer> fileIdList = culturalTeamDynamicsDto.getFileIdList();
        if(fileIdList != null && !fileIdList.isEmpty()){
            FilePathVo filePathVo = new FilePathVo();
            for(Integer fileId : fileIdList){
                filePathVo.setId(fileId);
                filePathVo.setPathId(culturalTeamDynamicsDto.getPictureId());
                filePathMapper.updateById(filePathVo);
            }
        }
        return i;
    }

    @Override
    public Integer updateCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto) {
        CulturalTeamDynamicsVo culturalTeamDynamicsVo = new CulturalTeamDynamicsVo();
        BeanUtil.copyProperties(culturalTeamDynamicsDto,culturalTeamDynamicsVo);
        int i = culturalTeamDynamicsMapper.updateById(culturalTeamDynamicsVo);
        return i;
    }

    @Override
    public Integer deleteCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto) {
        List<Integer> idList = culturalTeamDynamicsDto.getDeleteIdList();
        int i = culturalTeamDynamicsMapper.deleteBatchIds(idList);
        return i;
    }

    @Override
    public List<CulturalTeamDynamicsVo> queryCulturalTeamDynamics(CulturalTeamDynamicsDto culturalTeamDynamicsDto) {
        return culturalTeamDynamicsMapper.queryCulturalTeamDynamics(culturalTeamDynamicsDto);
    }
}
