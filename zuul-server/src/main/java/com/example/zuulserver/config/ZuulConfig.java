package com.example.zuulserver.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


/**
 * 跨域可以使用nginx上处理
 */
@Configuration
public class ZuulConfig {

    @Bean
    public CorsFilter corsFilter(){
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);//支持cookie跨域
        //设置原始域,http://www.a.com
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        //方法, post 还是 get
        configuration.setAllowedMethods(Arrays.asList("*"));
        //时间, 多长时间不检查
        configuration.setMaxAge(3000L);

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        CorsFilter filter = new CorsFilter(source);
        return filter;
    }


}
