package com.saltyfish.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by weck on 16/9/7.
 * <p>
 * 时钟任务
 */

@Component
public class ScheduleTask {

    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {
        System.out.println("当前时间:" + new Date(System.currentTimeMillis()) + ","
                + "时间戳：" + System.currentTimeMillis());
    }
}
