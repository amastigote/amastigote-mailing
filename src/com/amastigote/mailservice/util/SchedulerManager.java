package com.amastigote.mailservice.util;

import com.amastigote.mailservice.Main;
import com.amastigote.mailservice.delivery.DeliveringJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class SchedulerManager {
    private static Scheduler scheduler;

    public static void start() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail = newJob(DeliveringJob.class)
                .withIdentity("Mail Delivering Job")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("Mail Delivering Trigger")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule("0 " + Main.getTriggerMin() + " " + Main.getTriggerHour() + " * * ?")
                                .withMisfireHandlingInstructionIgnoreMisfires())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public static void shutdown() throws SchedulerException {
        if (scheduler != null)
            scheduler.shutdown();
    }
}
