package com.coolxer.aop;

import com.coolxer.commons.enums.ResultCodeEnum;
import com.coolxer.configuration.CustomWebConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

/**
 * 权限验证拦截器
 *
 * @author hunter
 */
@Slf4j
@Component
@Order(2)
public class AuthorityInterceptor extends AbstractInterceptor {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CustomWebConfig customWebConfig;

    /**
     * 需要放行的url集合
     */
    private List<String> releases = Arrays.asList(
            "/api/v1/system/about/info",
            "/api/v1/system/login/sign-in",
            "/api/v1/system/login/kaptcha",
            "/api/v1/system/login/encrypt/key",
            "/api/v1/system/login/sign-out"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestUri = request.getRequestURI();
        List<Boolean> booleans = releases.stream().map(requestUri::contains)
                .toList();
        if (booleans.contains(true)) {
            return true;
        }

        String referer = request.getHeader("referer");
        String host = request.getHeader("host");
        response.setCharacterEncoding(Charset.defaultCharset().toString());

        /**
         * 这做了一个后门文件，在项目目录下放一个文件allow_cross_origin(不关心内容)，有了它就允许跨域访问，否则不允许.
         * 为什么这么做?
         * 1、和前端在调试的时候就是在跨域请求，这时不能限制跨域，但部署出去的项目必须限制跨域。
         * 2、如果配置文件加开关，就有被开发打开并提交上去的可能，造成部署的项目允许跨域，要避免这种情况。
         * 3、在项目目录下放allow_cross_origin文件，makeApplication脚本打包后是不会拷贝这个文件的，git中也忽略了这个文件，开发本地对接口放一个就好了。
         * 4、不放在系统目录(类似/usr/local)是因为防止在某些客户环境需要打开，但没有权限的情况，例如建行，用过curl导入license文件.
         * */
        String filePath = System.getProperty("user.dir") + File.separator + "allow_cross_origin";
        File file = new File(filePath);
        if (!file.exists()) {
            /** 防止CSRF跨站点请求伪造（国务院项目）*/
            if (referer == null) {
                writErrorInfoToResponse(response, ResultCodeEnum.PLEASE_LOGIN);
                return false;
            } else {
                if (!referer.contains(host)) {
                    writErrorInfoToResponse(response, ResultCodeEnum.PLEASE_LOGIN);
                    return false;
                }
            }
        }

        if (requestUri.contains(customWebConfig.getNeedCheckPath())) {

            if (request.getRequestedSessionId() == null) {
                writErrorInfoToResponse(response, ResultCodeEnum.PLEASE_LOGIN);
                return false;
            } else {

                if (Boolean.FALSE.equals(stringRedisTemplate.hasKey(request.getRequestedSessionId()))) {
                    writErrorInfoToResponse(response, ResultCodeEnum.PLEASE_LOGIN);
                    return false;
                }

            }

            // 重置session超时时间，防止用户重新登录
            stringRedisTemplate.expire(request.getRequestedSessionId(), customWebConfig.getSessionTimeout());


        }
        return true;
    }


}
