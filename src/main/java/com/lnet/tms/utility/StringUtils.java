package com.lnet.tms.utility;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str));
    }

    public static String toCamelString(String s) {
        if (isEmpty(s)) return "";

        List<String> letters = splitCamelCaseString(s);
        letters.set(0, letters.get(0).toLowerCase());

        return join(toArray(letters), "");
    }

    public static String[] toArray(List<String> list)
    {
        String[] result = new String[list.size()];
        result = list.toArray(result);

        return result;
    }

    public static String join(String[] strings, String separator) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            if (i > 0) buf.append(separator);
            buf.append(strings[i]);
        }

        return buf.toString();
    }


    public static List<String> splitCamelCaseString(String s) {
        List<String> result = new ArrayList<String>();

        if (isEmpty(s)) return result;

        StringBuilder word = new StringBuilder();
        char[] buff = s.toCharArray();
        boolean prevIsUpper = false;

        for (int i = 0; i < buff.length; i++) {
            char ch = buff[i];
            if (Character.isUpperCase(ch)) {
                if (i == 0) {
                    word.append(ch);
                } else if (!prevIsUpper) {
                    result.add(word.toString());
                    word = new StringBuilder();
                    word.append(ch);
                } else {
                    word.append(ch);
                }
                prevIsUpper = true;
            } else {
                word.append(ch);
                prevIsUpper = false;
            }
        }

        if (word != null && word.length() > 0) result.add(word.toString());

        return result;
    }


}
