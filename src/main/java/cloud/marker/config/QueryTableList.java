package cloud.marker.config;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by apple on 16/12/23.
 */
public class QueryTableList {

    private List<String> tableList = new LinkedList<>();

    private QueryTableList(){}

    public static QueryTableList create(){
        return new QueryTableList();
    }

    public QueryTableList addTable(String table) {
        tableList.add(table);
        return this;
    }

    public List<String> getTableList(){
        return tableList;
    }
}
