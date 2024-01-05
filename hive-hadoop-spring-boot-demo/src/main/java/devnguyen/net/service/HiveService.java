package devnguyen.net.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class HiveService {

    @Value("${hive.datasource.url}")
    private String hiveUrl;

    @Value("${hive.datasource.username}")
    private String hiveUser;

    @Value("${hive.datasource.password}")
    private String hivePassword;

    public <T> List<T> queryHive(String sql, List<Object> values, Function<ResultSet, T> ormFn) {
        List<T> items = new ArrayList<>();
        try {
            // Load Hive JDBC Driver
            Class.forName("org.apache.hive.jdbc.HiveDriver");

            // Create connection
            Connection connection = DriverManager.getConnection(hiveUrl, hiveUser, hivePassword);

            // Create statement
            var statement = connection.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++) {
                statement.setObject(i + 1, values.get(i));
            }


            // Execute HiveQL query
            String query = "SELECT * FROM your_table LIMIT 10";
            ResultSet resultSet = statement.executeQuery();
            // orm
            var metaData = resultSet.getMetaData();

            // Process the result set
            while (resultSet.next()) {
                // Access data by column name
                String column1 = resultSet.getString("column1");
                String column2 = resultSet.getString("column2");

                // Process data as needed
                System.out.println("Column1: " + column1 + ", Column2: " + column2);
                items.add(ormFn.apply(resultSet));
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }
}
