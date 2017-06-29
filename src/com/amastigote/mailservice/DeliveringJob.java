package com.amastigote.mailservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class DeliveringJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            DeliveringCore.deliver(
                    RemoteContentUtil.getValidatedMails(),
                    MailBodyUtil.generateMailHTMLBody(RemoteContentUtil.getNewlyArchivedPages(0)));
            System.out.println("Deliver job fired at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
