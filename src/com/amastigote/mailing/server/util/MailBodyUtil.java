package com.amastigote.mailing.server.util;

import com.amastigote.mailing.ConfigurationManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MailBodyUtil {

    private static final String meta = "<html><head><meta charset=utf-8><style>div,table{font-family: sans-serif;font-size: 16px} a,a:hover{text-decoration:none}</style></head><body><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"container\" style=\"border-collapse: collapse\"><tr><td style=\"background-color: rebeccapurple\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\"><tr><td align=\"left\" class=\"block\" style=\"padding: 6px 12px;\"><table align=\"left\" width=\"70%\"><tr><td><span style=\"line-height: 20px;font-size: 16px;color: rebeccapurple;font-weight: bold;color: #ffffff;-webkit-font-smoothing: antialiased\">Amastigote Daily</span></td></tr></table><table align=\"right\" style=\"text-align: right\" width=\"30%\"><tr><td><span style=\"font-size: 10px;line-height: 20px;font-weight: normal;color: #ffffff\">$DATE$</span></td></tr></table></td></table></td></tr></table>";
    private static final String body = "<table align=\"center\"><tr><td><div style=\"margin-top: 30px; font-size: 14px\">We are glad to add your email to our list, please click the button to complete validation.</div></td></tr><tr><td style=\"text-align:center\"><div style=\"margin-top: 20px\"><a href=\"$URL$\" style=\"text-decoration: none\"><span style=\"background-color: rebeccapurple;  font-size: 14px; border-radius: 4px; font-weight: normal; padding: 6px 12px 6px 12px; color: #FFFFFF\"><strong>VALIDATE</strong></span></a></div></td></tr><tr><td><div style=\"margin-top: 40px; font-size: 12px; color: darkgrey\">If the button isn't working, visit <a href=\"$URL$\">$URL$</a> in your browser.</div></td></tr></table>";
    private static final String url = "http://$SERVER$/mail/validate?hash=";

    public static String generateMailBody(String hash) {
        String date = new SimpleDateFormat("E MMM dd yyyy", Locale.US).format(new Date());
        String url = MailBodyUtil.url.replace("$SERVER$", ConfigurationManager.getMailValidationServer()) + hash;
        String body = MailBodyUtil.body.replace("$URL$", url);
        String meta = MailBodyUtil.meta.replace("$DATE$", date);
        return meta + body + "</body></html>";
    }
}
