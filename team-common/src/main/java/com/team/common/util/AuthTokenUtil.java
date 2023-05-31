package com.team.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;


import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xb
 * @Date: 2022/4/6 13:38
 */

public class AuthTokenUtil {
    private static Logger logger = LoggerFactory.getLogger(AuthTokenUtil.class);


    //设置过期时间
    private static final long EXPIRE_DATE = 24000 * 60 * 60;//24hour

    //token秘钥
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

    //redis token 秘钥
    private static final String TOKEN_SECRET_REDIS = "XXXXXXXXXXX";

    //token 提前判断时间
    private static final long EXPIRED_MIN = 15 * 60;//15min

    //这个key是返回给前端token里面userName 无任何作用和含义，只是为了方法的传参
    private static final String LOGIN_KEY = "login";

    private static final int REDIS_SECOND = 60 * 60;//单位是秒 60min

    /**
     * 生成token
     *
     * @param userId
     * @param userName
     * @return
     */
    public static Map<String, String> getLoginToken(String userId, String userName) {
        logger.debug("AuthTokenUtil getLoginToken start...");
        Map<String, String> result = new HashMap<>();
        try {
            byte[] bytes = userName.getBytes("UTF-8");
            //加密
            String _at = token(userId, LOGIN_KEY, EXPIRE_DATE, TOKEN_SECRET);
            String newUserName = Base64.getEncoder().encodeToString(bytes);
            String _rt = token(userId, newUserName, EXPIRE_DATE, TOKEN_SECRET_REDIS);
            result.put("_at", _at);
            ZmRedisUtil redisUtil = SpringUtil.getBean(ZmRedisUtil.class);
            redisUtil.set(_at,_rt,REDIS_SECOND);
        } catch (Exception e) {
            result.put("logout", "logout");
            logger.error("未能正确解析用户信息:{}", e.getLocalizedMessage(), e);
        }
        logger.debug("AuthTokenUtil getLoginToken end...");
        return result;
    }

    /**
     * token登出
     *
     * @param _at
     * @return
     */
    public static Map<String, String> loginTokenOut(String _at) {
        Map<String, String> result = new HashMap<>();
        try {
            ZmRedisUtil redisUtil = SpringUtil.getBean(ZmRedisUtil.class);
            redisUtil.del(_at);
        } catch (Exception e) {
            result.put("logout", "logout");
            logger.error("token删除失败:{}", e.getLocalizedMessage(), e);
        }
        return result;
    }

    /**
     * 生成token
     *
     * @param userId
     * @param userName
     * @return
     */
    private static String token(String userId, String userName, Long time, String secret) throws Exception {
        String token = "";
        //过期时间
        Date date = new Date(System.currentTimeMillis() + time);
        //秘钥及加密算法
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //设置头部信息
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //携带userId,userName信息，生成签名
        token = JWT.create()
                .withHeader(header)
                .withClaim("userId", userId)
                .withClaim("userName", userName).withExpiresAt(date)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     *
     * @param request
     * @return
     */
    public static Map<String, String> verify(HttpServletRequest request) {
        logger.debug("AuthTokenUtil verify start...");
        Map<String, String> result = new HashMap<>();
        try {
            //通过请求头获取token
            String atToken = request.getHeader("_at");
            logger.debug("AuthTokenUtil verify getHeader atToken:" + atToken);
            if (StringUtils.isBlank(atToken)) {
                //cookie里面获取token
                atToken = WebUtils.getCookie(request, "EL-ADMIN-TOEKN") == null ? null : WebUtils.getCookie(request, "EL-ADMIN-TOEKN").getValue();
            }
            if (StringUtils.isNotBlank(atToken)) {
                //token
                ZmRedisUtil redisUtil = SpringUtil.getBean(ZmRedisUtil.class);
                String rtToken = redisUtil.get(atToken);
                Long ttl = redisUtil.getExpire(atToken);
                //ttl 永久或者不存在会返回-1
                if (ttl > 0 && StringUtils.isNotBlank(rtToken)) {
                    Algorithm a1 = Algorithm.HMAC256(TOKEN_SECRET_REDIS);
                    JWTVerifier v1 = JWT.require(a1).build();
                    DecodedJWT jwt = v1.verify(rtToken);
                    String userId = jwt.getClaims().get("userId").asString();
                    String userName = new String(Base64.getDecoder().decode(jwt.getClaims().get("userName").asString()), "UTF-8");
                    result.put("userId", userId);
                    result.put("userName", userName);
                    logger.info("ttl:" + ttl + ",isTokenExpired:" + isTokenExpired(ttl));
                    if (isTokenExpired(ttl)) {
                        //快要过期
                        redisUtil.set(atToken,rtToken,REDIS_SECOND);
                    }
                } else {
                    //已经过期
                    result.put("logout", "logout");
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("logout", "logout");
            logger.error("未能正确解析用户信息:{}", e.getLocalizedMessage(), e);
        }
        return result;
    }


    public static Boolean isTokenExpired(Long redisTime) {
        if (redisTime <= EXPIRED_MIN) {
            return true;
        } else {
            return false;
        }
    }
}
