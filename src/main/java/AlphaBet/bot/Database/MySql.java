package AlphaBet.bot.Database;

import java.sql.*;
import java.time.LocalDateTime;

/* ======================================================================================
 ====================================================================================== */
public class MySql {
    private static Connection connection;
    private final String host;
    private final String port;
    private final String user;
    private final String password;
    private final String database;
    /* ======================================================================================
    ====================================================================================== */
    public MySql(String host, String port, String user, String password, String database) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.database = database;
    }

    public static Connection getConn() {
        return connection;
    }
    /* ======================================================================================
     ====================================================================================== */
    public MySql connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"
                    + this.host + ":" + this.port + "/"
                    + this.database, this.user, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this;
    }
    /* ======================================================================================
    ====================================================================================== */
    public MySql initialize() {
        connect();
        return this;
    }
    /* ======================================================================================
        This method only return single row
    ====================================================================================== */
    public String getData(String table, String where, String value,String returnField) {
        try {
            PreparedStatement q = connection.prepareStatement
                    (String.format("SELECT * FROM %s WHERE %s = %s", table, where, value));
            ResultSet rs = q.executeQuery();
            if (rs.next())
                return rs.getString(returnField);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /* --------------------------------------------------------------------------------------
                    This method to insert data
    -------------------------------------------------------------------------------------- */
    public void insertbljoin(LocalDateTime joinDate, String memberID, String inviterID) {
        try {
            PreparedStatement q = connection.prepareStatement
                    (String.format("INSERT INTO tbljoin (joinDate, memberID, inviterID) VALUES ('%s', '%s', '%s')", joinDate, memberID, inviterID));
            q.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /* --------------------------------------------------------------------------------------
                    This method to insert data
    -------------------------------------------------------------------------------------- */
    public void insertblleave(LocalDateTime leaveDate, String memberID) {
        try {
            PreparedStatement q = connection.prepareStatement
                    (String.format("INSERT INTO tblleave (leaveDate, memberID) VALUES ('%s', '%s')", leaveDate, memberID));
            q.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
