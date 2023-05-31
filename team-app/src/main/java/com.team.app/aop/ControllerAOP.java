package com.team.app.aop;



import com.team.common.exception.CheckException;
import com.team.common.constants.result.BaseResult;
import com.team.common.constants.result.Constant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 处理和包装异常
 */
@Aspect
@Component
@Order(-99)
public class ControllerAOP {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAOP.class);

    @Pointcut("execution(public com.team.common.constants.result.BaseResult *(..))")
    public void controllerMethod() {
    }

    @Around("controllerMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.nanoTime();
        int requestMark = (int) (startTime & 8191);//高并发时方便跟踪请求
        Signature signature = pjp.getSignature();
        BaseResult<?> result;
        try {
//            if (!signature.getDeclaringTypeName().endsWith("BaseController")) verify();//todo:是否验证
            logger.info(requestMark + " - begin -> " + signature.getDeclaringTypeName() + '.' + signature.getName()
                    + " , params: " + Arrays.toString(pjp.getArgs()));
            result = (BaseResult<?>) pjp.proceed();
        } catch (Throwable e) {
            result = handlerException(e, requestMark);
        }
        long duration = System.nanoTime() - startTime;
        if (duration > 3000000000L || result.getCode() != 1) {
            logger.error(requestMark + " - request end -> duration: " + duration + " ns, " + result);
        } else {
            logger.info(requestMark + " - request end -> duration: " + duration + " ns");
        }
        return result;
    }

    /**
     * 统一异常处理
     *
     * @param e:
     * @param requestMark:
     * @return :
     */
    private BaseResult<?> handlerException(Throwable e, int requestMark) {
        BaseResult<?> result = new BaseResult();
        // 已知异常
        if (e instanceof CheckException) {
            result.setMessage(e.getLocalizedMessage());
            result.setCode(((CheckException) e).getErrorCode());
            logger.error(requestMark + " - CheckException: {}", result);
        } else {
            result.setMessage("系统异常");
            result.setCode(Constant.RESPONSE.REP_FAIL);
            logger.error(requestMark + " - error !", e);
        }
        result.setSuccess(false);
        return result;
    }

    /**
     * 验证apiToken
     */
    @SuppressWarnings("unused")
    private void verify() {     //todo：接口权限验证
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes != null ? requestAttributes.getRequest() : null;
//        String apiToken = request != null ? request.getHeader("ApiToken") : null;//获取请求头的apiToken,并校验其有效性

    }
}
