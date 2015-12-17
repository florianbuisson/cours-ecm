package fr.cmm.tags;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class Functions {

    public static String text(String a) {
        a = StringEscapeUtils.escapeXml10(a);
        return StringUtils.replace(a, "\n", "<br>");
    }
}
