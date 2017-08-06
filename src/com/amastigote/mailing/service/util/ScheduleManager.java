package com.amastigote.mailing.service.util;

import com.amastigote.mailing.ConfigurationManager;
import com.amastigote.mailing.service.delivery.DeliverJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class ScheduleManager {

    public static void startUp() throws SchedulerException, ClassNotFoundException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        System.out.println("[sch-manager] Scheduler is up");

        JobDetail jobDetail = newJob(DeliverJob.class)
                .withIdentity("Mail Delivering Job")
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("Mail Delivering Trigger")
                .withSchedule(
                        CronScheduleBuilder.cronSchedule(
                                "0 " + ConfigurationManager.getTriggerMin() + " " + ConfigurationManager.getTriggerHour() + " * * ?"
                        ).withMisfireHandlingInstructionIgnoreMisfires())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
