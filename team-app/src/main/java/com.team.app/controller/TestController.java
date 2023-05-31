package com.team.app.controller;

import com.team.common.exception.CheckException;
import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.ZmRedisUtil;
import com.team.performance.dto.TestDto;
import com.team.performance.service.TestService;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author QianT
 * @date 2022/7/25$
 */
@Api(tags = "test")
@RestController
@RequestMapping("/team")
public class TestController extends BaseController {

    @Autowired
    private ZmRedisUtil zmRedisUtil;

    @Autowired
    private TestService testServiceImpl;

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String selectAlarmLevel () {
        return "Hello world";
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getRedis", method = RequestMethod.POST)
    public BaseResult<String> getRedis() {
        zmRedisUtil.set("testRedis","testRedis2");
        String result = zmRedisUtil.get("testRedis");
        if (StringUtils.isNotEmpty(result)) {
            return success(result);
        } else {
            return fail();
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/getMybatis", method = RequestMethod.POST)
    public BaseResult<String> getMybatis(@RequestBody TestDto dto) {
        if (1 == 1) {
            throw new CheckException("分页信息不能为空",new Exception("分页信息不能为空"));
        }
        String result = testServiceImpl.queryCount(dto);
        return success(result);
    }
}
