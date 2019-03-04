package com.insoft.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.tuckey.web.filters.urlrewrite.UrlRewriteFilter;


@SpringBootApplication
public class SpringBootWebApplicationZK {

    public static void main(String[] args){
        SpringApplication.run(SpringBootWebApplicationZK.class, args);
    }

    @Bean
    public UrlRewriteFilter getUrlRewriteFilter() {
        UrlRewriteFilter urlRewriteFilter = new UrlRewriteFilter();
        return urlRewriteFilter;
    }

}