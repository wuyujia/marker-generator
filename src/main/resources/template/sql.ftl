package com.cloud.dao.sql.utils;

import java.util.Map;
import java.util.List;

/** Create By Mr.Black
 *  The more you try, The more you get!
 */
public class ${table_name}Sql {

    /**将这段代码复制到对应类的SqlProvider中就可以了*/
    public String query${table_name}sByConditions (Map<String, Object> query) {
        SQL sql = new SQL();

        /**查询结果集*/
        sql.SELECT(" * ");

        /**查询表*/
        sql.FROM(" ${full_table_name} ");

        /**查询条件*/
        for (Map.Entry<String, Object> entry : query.entrySet()) {
            sql.AND().WHERE(entry.getKey() + "#{" + entry.getKey() + "}");
        }
        /**默认排序条件*/
        // sql.ORDER_BY(" create_time DESC ");

        String sqlResult = sql.toString();
        logger.debug("通过Provider拼接好的SQL-->{}",sqlResult);
        return sqlResult;
    }


    /**将这段代码复制到对应的Dao中*/
    @SelectProvider(type = ${table_name}Dao.class, method = "query${table_name}sByConditions")
    List<${table_name}> query${table_name}sByConditions(Map<String, Object> query);
}