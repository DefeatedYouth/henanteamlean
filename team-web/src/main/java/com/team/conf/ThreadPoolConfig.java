package com.team.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author QianT
 * @date 2022/5/16$
 */
@EnableAsync
@Configuration
@Component
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public Executor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(300);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("task-");
        // rejection-policy：当pool已经达到max size的时候，丢弃任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
