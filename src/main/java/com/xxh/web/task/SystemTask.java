package com.xxh.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by wulongtao on 2017/6/27.
 */
@Component
public class SystemTask {
    private final Logger logger = LoggerFactory.getLogger(SystemTask.class);

    @Scheduled(cron = "0/5 * *  * * ? ")
    public void doSomething() {
        System.out.println("系统任务 ...");
    }
}
