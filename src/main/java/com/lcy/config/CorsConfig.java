package com.lcy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/**
 * 类说明：跨域、拦截、请求、响应配置，用于配置 spring mvc
 *
 * @project:
 * @className: CorsConfig
 * @author:
 * @create:
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    /**
     * 全局的CORS配置，通过注册一个 CorsFilter 处理所有请求的跨域问题
     * @return
     */
   @Bean
   public FilterRegistrationBean filterRegistrationBean() {
       CorsConfiguration corsConfiguration = new CorsConfiguration();
       //1.允许任何来源
       corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
       //2.允许任何请求头
       corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
       //3.允许任何方法
       corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
       //4.允许凭证
       corsConfiguration.setAllowCredentials(true);
       //暴露响应头
       corsConfiguration.addExposedHeader("content-disposition");

       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       source.registerCorsConfiguration("/**", corsConfiguration);
       CorsFilter corsFilter = new CorsFilter(source);

       FilterRegistrationBean<CorsFilter> filterRegistrationBean=new FilterRegistrationBean<>(corsFilter);
       filterRegistrationBean.setOrder(-101);  // 小于 SpringSecurity Filter的 Order(-100) 即可,确保在spring security过滤器链前执行

       return filterRegistrationBean;
   }
}
