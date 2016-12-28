package cloud.marker.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;

import javax.sql.DataSource;

/**
 * Created by apple on 16/12/23.
 */
public class DatabBaseConfig {

    private String url;

    private String ip;

    private String port;

    private String username;

    private String password;

    private String database;

    private DataSource dataSource;

    private QueryRunner queryRunner;

    /**私有构造方法*/
    private DatabBaseConfig(){}

    /**使用静态代码块加载数据库驱动, 只需要加载一次就够了*/
    static {
        try {
            /**加载数据库驱动*/
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**私有构造方法*/
    private DatabBaseConfig(String ip, String port, String username, String password){
        this.ip = ip;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    /**使用链式编程, 注入数据库ip地址, 端口, 用户名, 密码*/
    public static DatabBaseConfig create(String ip, String port, String username, String password) {
        if(StringUtils.isBlank(ip) || StringUtils.isBlank(username) || StringUtils.isBlank(password)) return null;
        return new DatabBaseConfig(ip, port, username, password);
    }

    /**使用的数据库, 端口默认3306*/
    public DatabBaseConfig database(String database) {
        if (StringUtils.isBlank(database)) return null;
        this.database = database;
        StringBuffer sb = new StringBuffer();
        sb.append("jdbc:mysql://").append(ip);
        sb.append(StringUtils.isBlank(port) ? ":3306" : ":"+port);
        sb.append("/").append(database).append("?useUnicode=true&characterEncoding=UTF-8");
        this.url = sb.toString();
        return this;
    }

    /**建立完整配置*/
    public DatabBaseConfig build() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        this.dataSource = dataSource;
        this.queryRunner = new QueryRunner(dataSource);
        return this;
    }

    /**获取QueryRunner查询对象*/
    public QueryRunner getQueryRunner() {
        return this.queryRunner;
    }

    /**获取数据库*/
    public String getDatabase() {
        return this.database;
    }
}
