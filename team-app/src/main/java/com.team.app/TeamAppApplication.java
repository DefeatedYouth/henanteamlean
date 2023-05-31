package com.team.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication(scanBasePackages = {"com.team"})
@EnableScheduling
@EnableAsync
//@MapperScan(basePackages ="com.team.**.dao")
public class TeamAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamAppApplication.class, args);
    }
}
