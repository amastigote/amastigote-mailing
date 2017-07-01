package com.amastigote.mailservice.delivery;

import com.amastigote.mailservice.Main;
import com.amastigote.mailservice.remote.RemoteContentUtil;
import com.amastigote.mailservice.util.MailBodyUtil;
import com.amastigote.mailservice.util.Page;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.List;

public class DeliveringJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            List<Page> pages = RemoteContentUtil.getNewlyArchivedPages(Main.getLastId());
            if (pages.size() > 0)
                DeliveringCore.deliver(
                        RemoteContentUtil.getValidatedMails(),
                        MailBodyUtil.generateMailHTMLBody(pages));
            System.out.println("[amastigote] Deliver job fired at " + new Date() + ".");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
