package cloud.marker.utils;

import cloud.marker.MainApp;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mr.Black on 16/12/23.
 */
public class FreeMarkerUtils {

    private FreeMarkerUtils(){}

    /**创建新的FreeMarker配置文件*/
    public static Configuration createConfiguration(String markerPathPrefix) {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        configuration.setClassForTemplateLoading(FreeMarkerUtils.class, markerPathPrefix);
        return configuration;
    }

    /**获取相对于src/main/java的绝对路径*/
    public static String getAbsolutePath(String path){
        String resource = MainApp.class.getResource("/").toString();
        String basePath = resource.substring(resource.indexOf("/"),resource.lastIndexOf("target"));
        if (StringUtils.isBlank(path)) path = "";
        Matcher matcher = Pattern.compile("/*").matcher(path);
        if (matcher.find()) {
            String group = matcher.group();
            path = path.replace(group, "");
        }
        return basePath + path;
    }

    /**获取模板*/
    public static Template getTamplate(Configuration configuration, String templateName) throws IOException {
        return configuration.getTemplate(templateName);
    }
}
