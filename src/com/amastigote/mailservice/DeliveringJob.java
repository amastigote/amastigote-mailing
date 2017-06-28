package com.amastigote.mailservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DeliveringJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        DeliveringCore.deliverTo("m@amastigote.com");
        System.out.println("Deliver executed.");
    }
}
