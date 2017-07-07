package com.amastigote.mailing.server;

import com.amastigote.mailing.server.util.MailBodyUtil;
import com.amastigote.mailing.server.util.RequestBodyUtil;
import com.amastigote.mailing.service.MailingTaskManager;
import com.amastigote.mailing.service.util.SingleMailingTaskDetail;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

public class MailingRequestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Map<String, String> reqParams = RequestBodyUtil.parse(httpExchange.getRequestBody());
        System.out.println("\n------Mailing Req.-------");
        reqParams.forEach((k, v) -> System.out.println(k + " -> " + v));
        System.out.println("-------------------------\n");
        if (MailingTaskManager.queueTask(new SingleMailingTaskDetail()
                .setDestination(reqParams.get("mail"))
                .setHtmlBody(MailBodyUtil.generateMailBody(reqParams.get("hash")))
                .setSender("Amastigote Daily")
                .setSubject("Confirm your subscription to Amastigote Daily")))
            httpExchange.sendResponseHeaders(740, 0); // Task submitted
        else
            httpExchange.sendResponseHeaders(840, 0); // Server busy
        httpExchange.close();
    }
}
