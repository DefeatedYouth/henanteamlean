package com.team.common.constants.performance;

import io.swagger.annotations.ApiModelProperty;

// 绩效模块-常量
public interface PerforConstants {

    // 是否含有班组：1不包含，2包含
    interface IS_HAV_TEAM {
        Integer NO = 1;
        Integer YES = 2;
    }

    // 发布状态：1未发布，2发布中，3已发布
    interface PUBLISH_STATE {
        Integer NO_PUBLISH = 1;
        Integer ING_PUBLISTE = 2;
        Integer AL_PUBLISTED = 3;
    }

    // 绩效方案来源;1：工区专责，2：班长
    interface PERFOR_SOURCE {
        Integer SOURCE_LEADER = 1;
        Integer SOURCE_MONITOR = 2;
    }

    // 周期：1月，2季度
    interface ASSESSMENT_CYCLE {
        Integer MONTH = 1;
        Integer QUARTER = 2;
    }

    // 绩效评价状态;1:未发布（默认）;2:预发布；3:已发布
    interface EVA_PUBLISH_STATE {
        Integer NO_PUBLISH = 1;
        Integer ING_PUBLISTE = 2;
        Integer AL_PUBLISTED = 3;
    }
}
