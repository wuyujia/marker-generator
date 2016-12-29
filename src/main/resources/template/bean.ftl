package com.cloud.dao.sql.utils;

import java.math.BigDecimal;

/**
* Create By Mr.Black
* The more you try, The more you get!
*/
@SuppressWarnings("all")
public class ${tableName} {

<#list columnList as column>
private ${column.dataType} ${column.columnName};

</#list>

<#list columnList as column>
public ${column.dataType} get${column.columnNameCap} () {
return ${column.columnName};
}

public ${tableName} set${column.columnNameCap} (${column.dataType} ${column.columnName}) {
this.${column.columnName} = ${column.columnName};
return this;
}

</#list>
}