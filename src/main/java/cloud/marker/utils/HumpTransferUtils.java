package cloud.marker.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apple on 16/12/23.
 */
public class HumpTransferUtils {

    public static String underLineToHump (String underLineStr) {
        if (underLineStr == null || underLineStr.isEmpty()) return "";
        String prefix = "";
        if (underLineStr.charAt(0) == '_') {
            prefix = "_";
            underLineStr = underLineStr.substring(1, underLineStr.length());
        }
        String regex = "_[0-9a-z]";
        Matcher matcher = Pattern.compile(regex).matcher(underLineStr);
        while (matcher.find()) {
            String group = matcher.group();
            underLineStr = underLineStr.replace(group, group.substring(1).toUpperCase());
        }
        return underLineStr;
    }

    public static String underLineToHumpIncludeCap(String underLineStr) {
        if (underLineStr == null || underLineStr.isEmpty()) return "";
        String prefix = "";
        if (underLineStr.charAt(0) == '_') {
            prefix = "_";
//            underLineStr = underLineStr.substring(1, underLineStr.length());
        } else {
            underLineStr = "_" + underLineStr;
        }
        String regex = "_[0-9a-z]";
        Matcher matcher = Pattern.compile(regex).matcher(underLineStr);
        while (matcher.find()) {
            String group = matcher.group();
            underLineStr = underLineStr.replace(group, group.substring(1).toUpperCase());
        }
        return underLineStr;
    }

    public static void main(String[] args) {
        System.out.println(underLineToHumpIncludeCap("cloud_order_buy"));
    }
}
