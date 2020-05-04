package pri117.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() throws SQLException {
        var ds = new SimpleDriverDataSource();
        ds.setDriver(new com.mysql.jdbc.Driver());
        ds.setUrl("jdbc:mysql://localhost:3306/lab01?serverTimezone=UTC");
        ds.setUsername("root");
        ds.setPassword("12232");
        return ds;
    }
}
