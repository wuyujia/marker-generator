package cloud.marker;

import cloud.marker.config.DatabBaseConfig;
import cloud.marker.config.QueryTableList;
import cloud.marker.execute.Execute;
import cloud.marker.utils.FreeMarkerUtils;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by apple on 16/12/23.
 */
public class MainApp {

    public static void main(String[] args) throws IOException, TemplateException, ClassNotFoundException, SQLException {
        String username = "root";
        String password = "root";
        String ip = "127.0.0.1";
        String port = null; // 配置里面默认3306
        DatabBaseConfig config = DatabBaseConfig.create(ip, port, username, password).database("ecmall").build();

        QueryTableList tableList = QueryTableList.create()
                .addTable("cloud_order_buy_log")
                .addTable("cloud_goods_stock_log")
                .addTable("cloud_order_refund_log")
                .addTable("cloud_order_return_log")
                .addTable("cloud_trade_flow")
                .addTable("cloud_transaction_flow");

        String absolutePath = FreeMarkerUtils.getAbsolutePath("src/main/java/com/cloud/dao/sql/utils/");

        new Execute().execute(config, tableList, absolutePath);

    }
}
