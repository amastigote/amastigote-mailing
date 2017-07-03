package com.amastigote.mailservice.delivery;

import com.amastigote.mailservice.remote.RemoteContentUtil;
import com.amastigote.mailservice.util.MailBodyUtil;
import com.amastigote.mailservice.util.Page;
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
                DeliverCore.deliver(
                        RemoteContentUtil.getValidatedMails(),
                        MailBodyUtil.generateMailHTMLBody(pages));
            System.out.println("[amastigote] Deliver job completed at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
