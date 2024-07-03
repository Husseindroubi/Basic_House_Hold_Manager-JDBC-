package household.data.modelling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static Connection connection;

    public static Connection getInstance() throws  SQLException {

        if (connection == null || connection.isClosed() || connection.isReadOnly()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Class forName
                connection = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/householdmanager", "root", "");
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return connection;
    }
}
