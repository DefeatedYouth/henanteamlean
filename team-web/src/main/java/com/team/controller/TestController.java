package com.team.controller;

import com.team.common.constants.result.BaseController;
import com.team.common.constants.result.BaseResult;
import com.team.common.util.ZmRedisUtil;
import com.team.form.TestForm;
import com.team.performance.dto.TestDto;
import com.team.performance.service.PerformanceRecordService;
import com.team.performance.service.TestService;
import com.team.vo.TestVo;
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

    @Autowired
    private PerformanceRecordService performanceRecordService;

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
    public BaseResult<TestVo> getMybatis(@RequestBody TestForm form) {
        TestDto dto = converForm2Dto(form);
        String result = testServiceImpl.queryCount(dto);
        TestVo vo = converDto2Vo(dto);
        return success(vo);
    }

    public static TestDto converForm2Dto(TestForm form) {
        if (form == null) {
            return null;
        }
        TestDto testDto = new TestDto();
        form.verifyParam();
        testDto.setId(form.getId());
        return testDto;
    }

    public static TestVo converDto2Vo(TestDto dto) {
        if (dto == null) {
            return null;
        }
        TestVo testVo = new TestVo();
        testVo.setId(dto.getId());
        return testVo;
    }

    public static void main(String[] args) {
        collect3.forEach((key, value) -> {
            System.out.println(key);
            Field[] declaredFields = value.getClass().getDeclaredFields();
            System.out.println(declaredFields);
            for (Field f: declaredFields) {
                String fUpName = f.getName().substring(0, 1).toUpperCase();
                String objGetName = "get" + fUpName + f.getName().substring(1);
                try {
                    Method method = value.getClass().getMethod(objGetName, new Class[] {});
                    Object objValue =method.invoke(value, new Object[] {});
                    System.out.println(method + ":" + objValue);
                    if (objValue instanceof Float) {

                    }
                    if (objValue instanceof Integer) {

                    }

                    if (objValue instanceof List) {

                    }

                    if (objValue instanceof Serializable) {

                    }

                    if (objValue.getClass().equals(Integer.class)) {

                    }

                    if (objValue.getClass().equals(Serializable.class)) {

                    }

                    if (objValue.getClass().equals(Float.class)) {

                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
