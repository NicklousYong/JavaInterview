package spring.schedule.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component // 注册为Spring组件
public class ScheduledTasks {


    // 格式化时间
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 1. 固定延迟执行：上一次任务结束后，延迟固定时间再执行
     * fixedDelay = 3000 表示上一次执行完后，过3秒再执行
     */
    @Scheduled(fixedDelay = 3000)
    public void taskWithFixedDelay() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("固定延迟任务执行时间：" + time);
    }

    /**
     * 2. 固定速率执行：以上一次任务开始时间为基准，固定时间后再次执行
     * fixedRate = 5000 表示每隔5秒执行一次（无论上一次是否完成）
     */
    @Scheduled(fixedRate = 5000)
    public void taskWithFixedRate() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("固定速率任务执行时间：" + time);
    }

    /**
     * 3. 初始延迟执行：首次执行前延迟指定时间，之后按固定速率执行
     * initialDelay = 2000 表示首次延迟2秒执行，之后每5秒执行一次
     */
    @Scheduled(initialDelay = 2000, fixedRate = 5000)
    public void taskWithInitialDelay() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("初始延迟任务执行时间：" + time);
    }

    /**
     * 4. Cron表达式执行：最灵活的方式，支持复杂的时间规则
     * 以下表示每天的10:30执行
     */
    @Scheduled(cron = "0 30 10 * * ?")
    public void taskWithCronExpression() {
        String time = LocalDateTime.now().format(formatter);
        System.out.println("Cron任务执行时间：" + time);
    }
}
