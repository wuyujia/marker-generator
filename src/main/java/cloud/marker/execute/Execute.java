package cloud.marker.execute;

import cloud.marker.config.DatabBaseConfig;
import cloud.marker.config.QueryTableList;
import cloud.marker.sql.SqlProvider;
import cloud.marker.utils.Constant;
import cloud.marker.utils.FreeMarkerUtils;
import cloud.marker.utils.HumpTransferUtils;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by apple on 16/12/23.
 */
@SuppressWarnings("all")
public class Execute {

    public void execute(DatabBaseConfig config, QueryTableList tableList, String exportPath, String... templates) throws SQLException, IOException, TemplateException {
        // 获取执行对象
        QueryRunner queryRunner = config.getQueryRunner();
        String database = config.getDatabase();
        // 获取要生成查询工具的表
        List<String> tables = tableList.getTableList();
        for (int i = 0; i < tables.size(); i++) {
            String sql = SqlProvider.getSql(tables.get(i), database);
            List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler());
            Map<String, Object> modelContent = this.getModelContent(query);
            for (String template : templates) {
                if (template.equals(Constant.Template.INSERT)) {
                    Map<String, Object> modelContentToHump = this.getModelContentToHump(query);
                    /**注意, 这个是使用驼峰*/
                    exportInsert(modelContentToHump, exportPath);
                } else if (template.equals(Constant.Template.QUERY)) {
                    exportQuery(modelContent, exportPath);
                } else if (template.equals(Constant.Template.SQL)) {
                    exportSQL(modelContent, exportPath);
                } else if (template.equals(Constant.Template.UPDATE)) {
                    Map<String, Object> modelContentForUpdate = this.getModelContentForUpdate(query);
                    exportUpdate(modelContentForUpdate, exportPath, Constant.templates.get(Constant.Template.UPDATE), Constant.FileName.UPDATE);
                } else if (template.equals(Constant.Template.UTIL)) {
                    Map<String, Object> modelContentToHump = this.getModelContentToHump(query);
                    /**注意, 这个是使用驼峰*/
                    exportUtil(modelContentToHump, exportPath);
                }
                Map<String, Object> modelContentForBean = this.getModelContentForBean(query);
                exportUpdate(modelContentForBean, exportPath, Constant.templates.get(Constant.Template.BEAN), Constant.FileName.BEAN);
            }
        }
        System.out.println("生成成功");
    }

    private Map<String, Object> getModelContent(List<Map<String, Object>> tableFields) {
        /** 创建返回结果集, 包含内容为
         *  {
         *      table_name:
         *          column_list:[
         *                          {
         *                              method: 字段名
         *                              data_type:数据类型 需要判断转换
         *                              var: 变量名称
         *                              column: 数据库列名
         *                          },
         *                          {
         *                              method: 字段名
         *                              data_type:数据类型 需要判断转换
         *                              var: 变量名称
         *                              column: 数据库列名
         *                          }
         *                  ]
         *  }
         */
        Map<String, Object> modelContent = new LinkedHashMap<>();
        // 新建字段集合
        ArrayList<Map<String, Object>> column_list = new ArrayList<>();

        // 遍历字段集合
        for (int j = 0; j < tableFields.size(); j++) {
            System.out.print(".");
            Map<String, Object> field = tableFields.get(j);
            modelContent.put("table_name", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("table_name")));
            modelContent.put("full_table_name", (String) field.get("table_name"));

            Map<String, Object> column = new HashMap<>();
            column.put("method", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            // 对数据类型进行转换
            String data_type = (String) field.get("data_type");
            if (data_type.equalsIgnoreCase("int")) {
                data_type = "Integer";
            } else if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            } else if (data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Byte";
            }
            column.put("data_type", data_type);
            column.put("var", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column.put("column", field.get("column_name"));
            column_list.add(column);
            modelContent.put("column_list", column_list);
        }
        return modelContent;
    }

    private Map<String, Object> getModelContentForUpdate(List<Map<String, Object>> tableFields) {

        Map<String, Object> modelContent = new LinkedHashMap<>();
        // 新建字段集合
        ArrayList<Map<String, Object>> columnList = new ArrayList<>();

        // 遍历字段集合
        for (int j = 0; j < tableFields.size(); j++) {
            Map<String, Object> field = tableFields.get(j);
            // 添加包括首字母转成驼峰的表名
            if (Objects.isNull(modelContent.get("tableName"))) {
                modelContent.put("tableName", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("table_name")));
            }
            // 添加未转成驼峰的全名
            if (Objects.isNull(modelContent.get("fullTableName"))) {
                modelContent.put("fullTableName", (String) field.get("table_name"));
            }
            if (Objects.isNull(modelContent.get("var"))) {
                modelContent.put("var", HumpTransferUtils.underLineToHump((String) field.get("table_name")));
            }

            Map<String, Object> column = new HashMap<>();
            if ("PRI".equals(field.get("COLUMN_KEY"))) {
                column.put("pri", field.get("column_key"));
                modelContent.put("primaryColumn", field.get("column_name"));
                modelContent.put("columnName", "#{" + HumpTransferUtils.underLineToHump((String) field.get("column_name")) + "}");
                continue;
            }
            // 添加驼峰转换的表名
            column.put("columnName", "#{" + HumpTransferUtils.underLineToHump((String) field.get("column_name")) + "}");
            column.put("columnNameCap", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("column_name")));
            // 添加未转换的列名
            column.put("fullColumnName", field.get("column_name"));
            // 对数据类型进行转换
            String data_type = (String) field.get("data_type");
            if (data_type.equalsIgnoreCase("int")) {
                data_type = "Integer";
            } else if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            } else if (data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Byte";
            }
            // 添加数据类型
            column.put("dataType", data_type);
            columnList.add(column);
        }
        modelContent.put("columnList", columnList);
        return modelContent;
    }

    private Map<String, Object> getModelContentForBean(List<Map<String, Object>> tableFields) {

        Map<String, Object> modelContent = new LinkedHashMap<>();
        // 新建字段集合
        ArrayList<Map<String, Object>> columnList = new ArrayList<>();

        // 遍历字段集合
        for (int j = 0; j < tableFields.size(); j++) {
            Map<String, Object> field = tableFields.get(j);
            // 添加包括首字母转成驼峰的表名
            if (Objects.isNull(modelContent.get("tableName"))) {
                modelContent.put("tableName", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("table_name")));
            }
            // 添加未转成驼峰的全名
            if (Objects.isNull(modelContent.get("fullTableName"))) {
                modelContent.put("fullTableName", (String) field.get("table_name"));
            }
            if (Objects.isNull(modelContent.get("var"))) {
                modelContent.put("var", HumpTransferUtils.underLineToHump((String) field.get("table_name")));
            }

            Map<String, Object> column = new HashMap<>();
//            if ("PRI".equals(field.get("COLUMN_KEY"))) {
//                column.put("pri", field.get("column_key"));
//                modelContent.put("primaryColumn", field.get("column_name"));
//                modelContent.put("columnName", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
//                continue;
//            }
            // 添加驼峰转换的表名
            column.put("columnName", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column.put("columnNameCap", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("column_name")));
            // 添加未转换的列名
            column.put("fullColumnName", field.get("column_name"));
            // 对数据类型进行转换
            String data_type = (String) field.get("data_type");
            if (data_type.equalsIgnoreCase("int")) {
                data_type = "Integer";
            } else if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            } else if (data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Byte";
            }
            // 添加数据类型
            column.put("dataType", data_type);
            columnList.add(column);
        }
        modelContent.put("columnList", columnList);
        return modelContent;
    }

    private Map<String, Object> getModelContentToHump(List<Map<String, Object>> tableFields) {
        /** 创建返回结果集, 包含内容为
         *  {
         *      table_name:
         *          column_list:[
         *                          {
         *                              method: 字段名
         *                              data_type:数据类型 需要判断转换
         *                              var: 变量名称
         *                              column: 数据库列名
         *                          },
         *                          {
         *                              method: 字段名
         *                              data_type:数据类型 需要判断转换
         *                              var: 变量名称
         *                              column: 数据库列名
         *                          }
         *                  ]
         *  }
         */
        Map<String, Object> modelContent = new LinkedHashMap<>();
        // 新建字段集合
        ArrayList<Map<String, Object>> column_list = new ArrayList<>();

        // 遍历字段集合
        for (int j = 0; j < tableFields.size(); j++) {
            System.out.print(".");
            Map<String, Object> field = tableFields.get(j);
            modelContent.put("table_name", HumpTransferUtils.underLineToHumpIncludeCap((String) field.get("table_name")));
            modelContent.put("full_table_name", (String) field.get("table_name"));

            Map<String, Object> column = new HashMap<>();
            column.put("method", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            // 对数据类型进行转换
            String data_type = (String) field.get("data_type");
            if (data_type.equalsIgnoreCase("int")) {
                data_type = "Integer";
            } else if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            } else if (data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Byte";
            }
            column.put("data_type", data_type);
            column.put("var", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column.put("column", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column_list.add(column);
            modelContent.put("column_list", column_list);
        }
        return modelContent;
    }

    private void exportQuery(Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
        Template template = FreeMarkerUtils.createConfiguration("/template").getTemplate("query.ftl");
        File path = new File(exportPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File outFile = new File(path, (String) modelContent.get("table_name") + "Query.java");
        FileWriter out = new FileWriter(outFile);
        template.process(modelContent, out);
        out.close();
    }

    private void exportSQL(Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
        Template template = FreeMarkerUtils.createConfiguration("/template").getTemplate("sql.ftl");
        File path = new File(exportPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File outFile = new File(path, (String) modelContent.get("table_name") + "Sql.java");
        FileWriter out = new FileWriter(outFile);
        template.process(modelContent, out);
        out.close();
    }

    private void exportInsert(Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
        Template template = FreeMarkerUtils.createConfiguration("/template").getTemplate("insert.ftl");
        File path = new File(exportPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File outFile = new File(path, (String) modelContent.get("table_name") + "Insert.java");
        FileWriter out = new FileWriter(outFile);
        template.process(modelContent, out);
        out.close();
    }

    private void exportUtil(Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
        Template template = FreeMarkerUtils.createConfiguration("/template").getTemplate("util.ftl");
        File path = new File(exportPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File outFile = new File(path, (String) modelContent.get("table_name") + "Util.java");
        FileWriter out = new FileWriter(outFile);
        template.process(modelContent, out);
        out.close();
    }

    private void exportUpdate(Map<String, Object> modelContent, String exportPath, String templateName, String fileName) throws IOException, TemplateException {
        Template template = FreeMarkerUtils.createConfiguration("/template").getTemplate(templateName);
        File path = new File(exportPath);
        if (!path.exists()) {
            path.mkdirs();
        }
        File outFile = new File(path, (String) modelContent.get("tableName") + fileName + ".java");
        FileWriter out = new FileWriter(outFile);
        template.process(modelContent, out);
        out.close();
    }

}
