package com.cloud.dao.sql.utils;

import org.apache.ibatis.jdbc.SQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Create By Mr.Black
* The more you try, The more you get!
*/
@SuppressWarnings("all")
public class ${tableName}Update {

    private Logger logger = LoggerFactory.getLogger(${tableName}Update.class);

    public String updateByIdSelective (${tableName} ${var}) {
        SQL sql = new SQL();
        sql.UPDATE("${fullTableName}");

        <#list columnList as column>
        <#if column.pri ??>
        <#else >
        if (${var}.get${column.columnNameCap}() != null) {
            sql.SET("${column.fullColumnName} = ${column.columnName}");
        }
        </#if>
        </#list>

        sql.WHERE("${primaryColumn} = ${columnName}");

        String sqlResult = sql.toString();
        logger.debug("通过Provider拼接好的SQL-->{}", sqlResult);
        return sqlResult;
    }
}
