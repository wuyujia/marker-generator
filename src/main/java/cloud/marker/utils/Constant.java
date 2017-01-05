package cloud.marker.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WuYujia on 2016/12/29.
 */
public class Constant {

    public static Map<String, String> templates = new HashMap<>();

    static {
        templates.put("insert", "insert.ftl");
        templates.put("query", "query.ftl");
        templates.put("sql", "sql.ftl");
        templates.put("update", "update.ftl");
        templates.put("bean", "bean.ftl");
    }

    public class Template {
        public static final String INSERT = "insert";
        public static final String QUERY = "query";
        public static final String SQL = "sql";
        public static final String UPDATE = "update";
        public static final String BEAN = "bean";
        public static final String UTIL = "util";
    }

    public class FileName {
        public static final String INSERT = "Insert";
        public static final String QUERY = "Query";
        public static final String SQL = "Qql";
        public static final String UPDATE = "Update";
        public static final String BEAN = "";
        public static final String UTIL = "Util";
    }

}
