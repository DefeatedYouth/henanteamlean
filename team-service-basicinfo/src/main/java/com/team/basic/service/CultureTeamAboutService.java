package com.team.basic.service;

import com.team.basic.dto.basicinfo.TeamAboutEditDto;
import com.team.basic.dto.basicinfo.TeamAboutShowDto;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team.basic.entity.CultureTeamAboutVo;

/**
 * <p>
 * 班组简介表 服务类
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
public interface CultureTeamAboutService extends IService<CultureTeamAboutVo> {

    /**
     * 班组简介信息展示
     * @return
     */
    TeamAboutShowDto showCultureTeamAbout();

    /**
     * 维护班组简介信息
     * @param teamAboutEditDto
     * @return
     */
    boolean maintainCultureTeamAbout(TeamAboutEditDto teamAboutEditDto);
}
