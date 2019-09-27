package ua.pforce.task.shops.connection_factory;

import com.mysql.cj.jdbc.MysqlDataSource;
import ua.pforce.task.shops.exception.ConfigException;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

import static ua.pforce.task.shops.message.LogMessages.CONFIG_FAILED;

/**
 * Singleton class, holding DataSource object;
 */
public class DataSourceFactory {

    private static final DataSourceFactory thisInstance = new DataSourceFactory();

    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());

    private final DataSource ds;

    private DataSourceFactory() {
        Properties properties = loadProperties();

        MysqlDataSource ds = new MysqlDataSource();

        ds.setUrl(properties.getProperty("db.url"));
        ds.setUser(properties.getProperty("db.user"));
        ds.setPassword(properties.getProperty("db.password"));

        this.ds = ds;
    }

    /**
     * This method should be used to obtain DataSource object for the application.
     * @return DataSource object.
     */
    public static DataSource getDataSource() {
        return thisInstance.ds;
    }

    /**
     * A helper method to load database config properties
     * @return properties pbject;
     */
    private Properties loadProperties() {
        final Properties properties = new Properties();

        try {
            properties.load(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("config.properties")));
        } catch (IOException e) {
            LOGGER.info(CONFIG_FAILED.getMsg() + e);
            throw new ConfigException(e);
        }

        return properties;
    }
}
