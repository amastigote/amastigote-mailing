package com.amastigote.mailservice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.Map;

public class DeliveringJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Map<String, String> newlyArchivedPages = RemoteContentUtil.getNewlyArchivedPages(Main.getLastId());
            if (newlyArchivedPages.size() > 0)
                DeliveringCore.deliver(
                        RemoteContentUtil.getValidatedMails(),
                        MailBodyUtil.generateMailHTMLBody(newlyArchivedPages));
            System.out.println("[amastigote] Deliver job fired at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
