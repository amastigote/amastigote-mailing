package com.amastigote.mailservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MailBodyUtil {

    private static final String templateForItem =
            "<div style=\"margin: 12px\"><div><a href=\"$URL$\">$TITLE$<a/></div>$TAGS$</div>";
    private static final String templateForTag = "<span style=\"border: 1px solid rebeccapurple; font-size: 11px; border-radius: 2px; font-weight: normal; padding: 0px 3px 0px 3px;color: rebeccapurple; margin-right: 4px\">$TAG_NAME$</span>";
    private static final String meta = "<meta charset=\"UTF-8\"><style>div,table{font-family: sans-serif;font-size: 16px}</style><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"container\" style=\"border-collapse: collapse\"><tr><td style=\"background-color: rebeccapurple\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" align=\"left\"><tr><td align=\"left\" class=\"block\" style=\"padding: 6px 12px;\"><table align=\"left\" width=\"70%\"><tr><td><span style=\"line-height: 20px;font-size: 16px;color: rebeccapurple;font-weight: bold;color: #ffffff;-webkit-font-smoothing: antialiased\">Amastigote Daily</span></td></tr></table><table align=\"right\" style=\"text-align: right\" width=\"30%\"><tr><td><span style=\"font-size: 10px;line-height: 20px;font-weight: normal;color: #ffffff\">$DATE$</span></td></tr></table></table></table>";

    public static String generateMailHTMLBody(List<Page> pages) {
        final String[] HTMLBody = {meta.replace("$DATE$", new SimpleDateFormat("E MMM dd yyyy", Locale.US).format(new Date()))};
        pages.forEach(page -> {
            String itemHTML = templateForItem
                    .replace("$URL$", page.getUrl())
                    .replace("$TITLE$", page.getTitle());
            final String[] tagsHTML = {""};
            page.getTags().forEach(e ->
                    tagsHTML[0] += templateForTag.replace("$TAG_NAME$", e));
            HTMLBody[0] += itemHTML.replace("$TAGS$", tagsHTML[0]);
        });
        return HTMLBody[0];
    }
}
