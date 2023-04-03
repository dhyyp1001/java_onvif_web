package webDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {

    private List<SiteUsers> siteUsersList;

    public AuthenticationService() {
        // initialize the list of users from the database
        siteUsersList = new ArrayList<>();
        // connect to the database
        Connection conn = getConnection();
        // execute a query to retrieve all users
        String query = "SELECT * FROM siteusers";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // create a User object from each row in the result set
                String username = rs.getString("username");
                String password = rs.getString("password");
                SiteUsers siteUsers = new SiteUsers(username, password);
                siteUsersList.add(siteUsers);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

    // helper method to get a database connection
    private Connection getConnection() {
        String url = "jdbc:sqlserver://;serverName=112.175.14.46;databaseName=daesang";
        String user = "daesang";
        String password = "Rudqhxpzm1";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
