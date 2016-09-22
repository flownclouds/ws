package com.saltyfish.configuration;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by weck on 16/9/7.
 * <p>
 * 时钟任务
 */

@Component
public class ScheduleTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 600000)
    public void reportCurrentTime() {
        System.out.println("当前时间:" + dateFormat.format(new Date()) + ","
                + "时间戳：" + System.currentTimeMillis());
    }
}
