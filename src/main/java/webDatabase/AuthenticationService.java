package webDatabase;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {

    private List<SiteUsers> siteUsersList;

    public AuthenticationService() {
        siteUsersList = new ArrayList<>();
        try {
            Connection conn = getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM siteusers");

            while (rs.next()) {
                SiteUsers siteUsers = new SiteUsers(rs.getString("username"), rs.getString("password"));
                siteUsersList.add(siteUsers);
                System.out.println(rs.getString("username")+"  "+rs.getString("password"));
            }

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean authenticate(String username, String password) {
        for (SiteUsers siteUsers : siteUsersList) {
            if (siteUsers.getUsername().equals(username) && siteUsers.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private Connection getConnection() throws SQLServerException {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setURL("jdbc:sqlserver://;serverName=112.175.14.46;databaseName=daesang");
        ds.setUser("daesang");
        ds.setPassword("Rudqhxpzm1");
        Connection con = ds.getConnection();
        return con;
    }
}
