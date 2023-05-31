package com.team.work.dto;

import lombok.Data;

/**
 * @author QianT
 * @date 2022/7/30$
 */
@Data
public class WorkMemberChangeUpdateDto {
    private String workNo;
    private Integer id;
    private String memberId;
    private String memberName;
    private String workDays;
}
