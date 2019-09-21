package com.example.zuulserver.filter;

import com.example.common.util.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限的校验,买家和卖家的
 *
 * 为了解耦把run()方法, 拆分出了两个类,
 * 分别是buyer和seller filter ,两个过滤...代码会好看很多...
 *
 */
@Component
public class AuthFilter extends ZuulFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 权限的校验,买家和卖家的
     * /order/create 只能买家(cookie 中有openid)
     * /order/finish  只能卖家(cookie中有token, redis中有值)
     * /product/list  都可以访问
     */
    @Override
    public Object run() throws ZuulException {
        //这里注释掉了.
        /*RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if ("/order/create".equals(request.getRequestURI())) {
            Cookie cookie = CookieUtil.getCookie(request, "openid");
            if (cookie == null || StringUtils.isBlank(cookie.getValue())) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }

        if ("/order/finish".equals(request.getRequestURI())) {
            //常量没有弄过来....
            Cookie cookie = CookieUtil.getCookie(request, "token");
            String value = cookie.getValue();
            Object user = stringRedisTemplate.opsForHash().get("user", value);
            if (cookie == null || StringUtils.isBlank(value) || (user == null)) {
                requestContext.setSendZuulResponse(false);
                requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            }
        }*/

        return null;
    }
}
