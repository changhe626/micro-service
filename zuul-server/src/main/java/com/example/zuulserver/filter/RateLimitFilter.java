package com.example.zuulserver.filter;


import com.example.zuulserver.exception.RateLimitException;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;


/**
 * 限流, 使用令牌桶算法
 */
@Component
public class RateLimitFilter extends ZuulFilter {

    //guava的实现
    private final static RateLimiter rateLimiter = RateLimiter.create(100);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("RateLimitFilter");
        //没有拿到令牌
        if(!rateLimiter.tryAcquire()){
            /*RequestContext requestContext = RequestContext.getCurrentContext();
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);*/
            throw new RateLimitException();
        }
        return null;
    }
}
