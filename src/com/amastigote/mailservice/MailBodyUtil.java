package com.amastigote.mailservice;

import java.util.Map;

public class MailBodyUtil {

    private static final String template = "<div><a href=\"$URL$\">$TITLE$<a/></div>";
    private static final String meta = "<meta charset=\"UTF-8\"><style>div{font-family: sans-serif;font-size: 14px}</style>";

    public static String generateMailHTMLBody(Map<String, String> pageMap) {
        final String[] HTMLBody = {meta};
        pageMap.forEach((title, url) ->
                HTMLBody[0] += template
                        .replace("$URL$", url)
                        .replace("$TITLE$", title));
        return HTMLBody[0];
    }
}
