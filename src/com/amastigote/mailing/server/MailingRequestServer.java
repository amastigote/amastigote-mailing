package com.amastigote.mailing.server;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class MailingRequestServer {

    public static void startUp(int port) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8081), 50);
        httpServer.createContext("/mail", new MailingRequestHandler());
        httpServer.start();
        System.out.println("[amastigote] Mailing request server is up");
    }
}
