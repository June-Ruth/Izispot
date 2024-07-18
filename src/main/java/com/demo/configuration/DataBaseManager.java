package com.demo.configuration;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public enum DataBaseManager {

    INSTANCE;

    MysqlDataSource dataSource;

    DataBaseManager() {
        Properties properties = new Properties();
        URL propertiesURL = DataBaseManager.class
                .getClassLoader()
                .getResource("db.properties");
        assert propertiesURL != null;

        File propertiesFile = new File(propertiesURL.getFile());

        try (FileInputStream fileInputStream = new FileInputStream(propertiesFile)) {
            properties.load(fileInputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(properties.getProperty("jdbc.url"));
            dataSource.setUser(properties.getProperty("jdbc.user"));
            dataSource.setPassword(properties.getProperty("jdbc.password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
