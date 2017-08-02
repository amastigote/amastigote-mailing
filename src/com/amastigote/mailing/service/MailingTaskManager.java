package com.amastigote.mailing.service;

import com.amastigote.mailing.service.delivery.DeliverCore;
import com.amastigote.mailing.service.util.MassiveMailingTaskDetail;
import com.amastigote.mailing.service.util.SingleMailingTaskDetail;
import org.apache.commons.mail.EmailException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MailingTaskManager {
    private static AtomicBoolean serviceUp = new AtomicBoolean(false);
    private static BlockingQueue<SingleMailingTaskDetail> blockingQueue = new LinkedBlockingQueue<>(500);

    public static void queueTask(MassiveMailingTaskDetail massiveMailingTaskDetail) throws InterruptedException {
        String htmlBody = massiveMailingTaskDetail.getHtmlBody();
        String sender = massiveMailingTaskDetail.getSender();
        String subject = massiveMailingTaskDetail.getSubject();
        for (String e : massiveMailingTaskDetail.getDestinations()) {
            blockingQueue.put(
                    new SingleMailingTaskDetail()
                            .setDestination(e)
                            .setHtmlBody(htmlBody)
                            .setSender(sender)
                            .setSubject(subject));
        }
    }

    public static boolean queueTask(SingleMailingTaskDetail singleMailingTaskDetail) {
        return blockingQueue.offer(singleMailingTaskDetail);
    }

    public synchronized static void startUp() throws InterruptedException {
        if (serviceUp.compareAndSet(false, true)) {
            new Thread(() -> {
                try {
                    //noinspection InfiniteLoopStatement
                    while (true) {
                        SingleMailingTaskDetail singleMailingTaskDetail = blockingQueue.take();
                        DeliverCore.deliver(singleMailingTaskDetail);

                        /*
                         * Mail delivery should be lowered to a fixed rate
                         */
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException | EmailException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println("[mail-tsk-manager] Mailing service is up");
        } else
            System.out.println("[mail-tsk-manager] Mailing service is already up, ignore");
    }
}
