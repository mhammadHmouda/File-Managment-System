package filedemo.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection  {

    private static DBConnection MySql = null;

    private String LocalDataBaseURL = "jdbc:mysql://localhost:3306/files";
    private String user = "root";
    private String password = "hamood12344321hhz#";

    Connection connectionDataBase = null;
    public Connection getConnection()
    {

        if(MySql==null)
        {
            MySql =  new DBConnection();

            try {
                connectionDataBase = DriverManager.getConnection(LocalDataBaseURL, user, password);
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }

        return connectionDataBase;
    }


}
