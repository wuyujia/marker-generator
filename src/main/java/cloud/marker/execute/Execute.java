package cloud.marker.execute;

import cloud.marker.config.DatabBaseConfig;
import cloud.marker.config.QueryTableList;
import cloud.marker.sql.SqlProvider;
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

    public void execute(DatabBaseConfig config, QueryTableList tableList, String exportPath) throws SQLException, IOException, TemplateException {
        System.out.println("正在生成...请稍等...");
        // 获取执行对象
        QueryRunner queryRunner = config.getQueryRunner();
        String database = config.getDatabase();
        // 获取要生成查询工具的表
        List<String> tables = tableList.getTableList();
        for (int i = 0; i < tables.size(); i++) {
            String sql = SqlProvider.getSql(tables.get(i), database);
            List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler());
            Map<String, Object> modelContent = this.getModelContent(query);
            Map<String, Object> modelContentToHump = this.getModelContentToHump(query);


//            exportQuery(modelContent, exportPath);

            /**注意, 这个是使用驼峰*/
            exportInsert(modelContentToHump, exportPath);


//            exportSQL(modelContent, exportPath);
            System.out.println("");
        }
        System.out.println("生成完毕...");
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
            if (data_type.equalsIgnoreCase("int") || data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Integer";
            } else
            if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else
            if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else
            if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            }
            column.put("data_type", data_type);
            column.put("var", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column.put("column", field.get("column_name"));
            column_list.add(column);
            modelContent.put("column_list", column_list);
        }
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
            if (data_type.equalsIgnoreCase("int") || data_type.equalsIgnoreCase("tinyint")) {
                data_type = "Integer";
            } else
            if (data_type.equalsIgnoreCase("varchar") || data_type.equalsIgnoreCase("text")) {
                data_type = "String";
            } else
            if (data_type.equalsIgnoreCase("decimal")) {
                data_type = "BigDecimal";
            } else
            if (data_type.equalsIgnoreCase("bigint")) {
                data_type = "Long";
            }
            column.put("data_type", data_type);
            column.put("var", HumpTransferUtils.underLineToHump((String) field.get("column_name")));
            column.put("column", HumpTransferUtils.underLineToHump((String)field.get("column_name")));
            column_list.add(column);
            modelContent.put("column_list", column_list);
        }
        return modelContent;
    }

    private void exportQuery (Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
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

    private void exportSQL (Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
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

    private void exportInsert (Map<String, Object> modelContent, String exportPath) throws IOException, TemplateException {
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


}
