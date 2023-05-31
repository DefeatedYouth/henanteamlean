package com.team.performance.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 班组成员对象
 * </p>
 *
 * @author qiant
 * @since 2022-07-26
 */
@Data
public class TeamMemberInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 流水号
     */
    private Integer id;

    /**
     * 被考核班员编码
     */
    private String teamPeoCode;

    /**
     * 被考核班员名称
     */
    private String teamPeoName;

}
