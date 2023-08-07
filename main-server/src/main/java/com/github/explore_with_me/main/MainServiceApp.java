package com.github.explore_with_me.main;

import com.github.explore_with_me.stats.client.StatsClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class MainServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(MainServiceApp.class, args);
    }
    //исправление замечания
    @Bean
    StatsClient statClient() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        return new StatsClient(builder);
    }
}
