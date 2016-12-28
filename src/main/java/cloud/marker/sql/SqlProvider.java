package cloud.marker.sql;

/**
 * Created by apple on 16/12/23.
 */
public class SqlProvider {

    public static String getSql(String table, String database) {
        StringBuffer sb = new StringBuffer();
        sb.append(" select * from information_schema.COLUMNS where table_name = '");
        sb.append(table);
        sb.append("' and table_schema = '");
        sb.append(database);
        sb.append("' ");
        return sb.toString();
    }
}
