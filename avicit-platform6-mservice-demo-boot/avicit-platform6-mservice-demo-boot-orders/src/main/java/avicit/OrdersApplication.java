package avicit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;

/**
 * VUE系统管理
 * 
 * @author liangxf
 *
 */
@SpringBootApplication(exclude = {
		RedisAutoConfiguration.class, 
		RedisRepositoriesAutoConfiguration.class,
        ActiveMQAutoConfiguration.class,
        DataSourceAutoConfiguration.class, 
        DataSourceTransactionManagerAutoConfiguration.class})
@ImportResource({"classpath*:spring-base.xml"})
public class OrdersApplication extends SpringBootServletInitializer {

    //允许在servlet容器启动时配置你的应用程序
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(OrdersApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
