package com.team.work.dto;

import lombok.Data;

import java.util.List;
import java.util.Objects;

/**
 * @author QianT
 * @date 2022/8/2$
 */
@Data
public class WorkLogDayListDto {
    private Integer memberId;
    private String memberName;
    private Integer workDay;
    private String remark;
    private String content;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkLogDayListDto that = (WorkLogDayListDto) o;
        return Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(remark);
    }
}
