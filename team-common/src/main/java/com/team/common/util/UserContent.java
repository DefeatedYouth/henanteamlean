package com.team.common.util;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Date: 2022/7/29 11:12
 * @Version: 1.0
 */
public class UserContent {

    public static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("测试");
        userInfo.setUserId("u00001");
        userInfo.setTeamName("电器实验一班");
        userInfo.setTeamId("t00001");
        return userInfo;
    }

    @Data
    @NoArgsConstructor
    public static class UserInfo {
        public String userId;
        public String userName;
        public String unitCode;
        public String unitName;
        public String deptCode;
        public String deptName;
        public String teamId;
        public String teamName;
    }
}
