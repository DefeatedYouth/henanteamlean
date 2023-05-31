package com.team.performance.service.impl;

import com.team.performance.dao.TestMapper;
import com.team.performance.dto.TestDto;
import com.team.performance.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhengjiang
 * @version 1.0.0
 * @date 2022/7/19 13:43
 * @Des
 **/
@Service("testServiceImpl")
public class TestServiceImpl implements TestService
{
    @Resource
    TestMapper testMapper;

    @Override
    public String queryCount(TestDto dto) {
        String id = dto.getId();
        //   88888
        return id;
    }
}
