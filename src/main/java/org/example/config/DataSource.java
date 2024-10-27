package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

@UtilityClass
public class DataSource {

    private static final String DB_URL = "jdbc:h2:~/SpaceTravel";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(DB_URL);
        config.setDriverClassName("org.h2.Driver");
        ds = new HikariDataSource(config);
        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations("db/migration")
                .load();
        flyway.migrate();
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
