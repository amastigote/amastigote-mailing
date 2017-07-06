package com.amastigote.mailservice.service.util;

import com.amastigote.mailservice.Configuration;
import com.amastigote.mailservice.service.delivery.DeliverJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ScheduleManager {
    private static Scheduler scheduler;

    public static void start() throws SchedulerException {
        scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        JobDetail jobDetail = newJob(DeliverJob.class)
                .withIdentity("Mail Delivering Job")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("Mail Delivering Trigger")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(
                                "0 " + Configuration.getTriggerMin() + " " + Configuration.getTriggerHour() + " * * ?"
                        ).withMisfireHandlingInstructionIgnoreMisfires())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    public static void shutdown() throws SchedulerException {
        if (scheduler != null)
            scheduler.shutdown();
    }
}
