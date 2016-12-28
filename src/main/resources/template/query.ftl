package com.cloud.dao.sql.utils;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;

/** Create By Mr.Black
 *  The more you try, The more you get!
 */
public class ${table_name}Query {

    private Map<String, Object> param = new HashMap<String, Object>();

    /**私有构造方法*/
    private ${table_name}Query(){}

    public static ${table_name}Query build() {
        return new ${table_name}Query();
    }

    <#list column_list as column>
    public ${table_name}Query ${column.method}Equal(${column.data_type} ${column.var}) {
        param.put("${column.column} = ", ${column.var});
        return this;
    }

    public ${table_name}Query ${column.method}LargeThan(${column.data_type} ${column.var}) {
        param.put("${column.column} > ", ${column.var});
        return this;
    }

    public ${table_name}Query ${column.method}LargeThanOrEqual(${column.data_type} ${column.var}) {
        param.put("${column.column} >= ", ${column.var});
        return this;
    }

    public ${table_name}Query ${column.method}BelowThan(${column.data_type} ${column.var}) {
        param.put("${column.column} < ", ${column.var});
        return this;
    }

    public ${table_name}Query ${column.method}BelowThanOrEqual(${column.data_type} ${column.var}) {
        param.put("${column.column} <= ", ${column.var});
        return this;
    }
    </#list>

    public Map<String, Object> query(){
        return this.param;
    }
}