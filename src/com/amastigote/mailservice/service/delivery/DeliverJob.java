package com.amastigote.mailservice.service.delivery;

import com.amastigote.mailservice.service.MailingTaskManager;
import com.amastigote.mailservice.service.remote.RemoteContentUtil;
import com.amastigote.mailservice.service.util.MailBodyUtil;
import com.amastigote.mailservice.service.util.MassiveMailingTaskDetail;
import com.amastigote.mailservice.service.util.Page;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;

public class DeliverJob implements Job {
    private static int lastId;

    public static int getLastId() {
        return lastId;
    }

    public static void setLastId(int lastId) {
        DeliverJob.lastId = lastId;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            System.out.println("[amastigote] Deliver job fired at " + new Date() + ".");
            List<Page> pages = RemoteContentUtil.getNewlyArchivedPages(lastId);
            if (pages.size() > 0)
                MailingTaskManager.queueTask(
                        new MassiveMailingTaskDetail()
                                .setDestinations(RemoteContentUtil.getValidatedMails())
                                .setHtmlBody(MailBodyUtil.generateMailHTMLBody(pages))
                                .setSubject("Pages Collected Today on Amastigote")
                                .setSender("Amastigote Daily")
                );
            System.out.println("[amastigote] Deliver job submitted at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
