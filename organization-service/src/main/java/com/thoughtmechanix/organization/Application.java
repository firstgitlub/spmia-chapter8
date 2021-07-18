package com.thoughtmechanix.organization;

import com.thoughtmechanix.organization.utils.UserContextFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import javax.servlet.Filter;


@SpringBootApplication
@EnableEurekaClient   // 用户Eureka 客户端
@EnableCircuitBreaker // 用于断路器

// 用于告诉应用程序，他将绑定到 Spring Cloud Stream消息代理
// Source.class 告诉 spring cloud stream 该服务将通过在Source类上 定义的一组通道与消息代理进行通信
// 记住 通道位于消息队列上  spring cloud stream 有一个默认的 通道集 可以配置他们来与消息代理进行通信
// 有一个概念就是  消息代理就是 消息中间件
@EnableBinding(Source.class)
public class Application {
    @Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
