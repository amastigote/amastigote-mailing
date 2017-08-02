package com.amastigote.mailing.service.delivery;

import com.amastigote.mailing.service.MailingTaskManager;
import com.amastigote.mailing.service.remote.RemoteContentUtil;
import com.amastigote.mailing.service.util.MailBodyUtil;
import com.amastigote.mailing.service.util.MassiveMailingTaskDetail;
import com.amastigote.mailing.service.util.PageDetail;
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
            System.out.println("[deliver-job] Deliver job fired at " + new Date() + ".");
            List<PageDetail> pageDetails = RemoteContentUtil.getNewlyArchivedPages(lastId);
            if (pageDetails.size() > 0)
                MailingTaskManager.queueTask(
                        new MassiveMailingTaskDetail()
                                .setDestinations(RemoteContentUtil.getValidatedMails())
                                .setHtmlBody(MailBodyUtil.generateMailBody(pageDetails))
                                .setSubject("Pages Collected Today on Amastigote")
                                .setSender("Amastigote Daily")
                );
            System.out.println("[deliver-job] Deliver job submitted at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
