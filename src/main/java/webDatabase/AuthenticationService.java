package webDatabase;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {

    Connection conn;
    private List<SiteUsers> siteUsersList;
    private List<CamInfo> camInfoList;

    public AuthenticationService() {
        siteUsersList = new ArrayList<>();
        try {
            conn = getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM siteusers");

            while (rs.next()) {
                SiteUsers siteUsers = new SiteUsers(rs.getString("username"), rs.getString("password"));
                siteUsersList.add(siteUsers);
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

        Connection con = ds.getConnection();
        return con;
    }

    public List<CamInfo> listLoad(String userId) {
        camInfoList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CamInfo WHERE Owner='"+userId+"' OR Manager='"+userId+"' OR Operator='"+userId+"'");

            while (rs.next()) {
                CamInfo camInfo = new CamInfo(
                        rs.getString("sName"),
                        rs.getString("CamIP"),
                        rs.getString("CamRtsp"),
                        rs.getString("CamHttp"),
                        rs.getString("ModIP"),
                        rs.getString("ModPort"),
                        rs.getString("CamID"),
                        rs.getString("CamPwd"),
                        rs.getString("mediaUri"),
                        rs.getString("Owner"),
                        rs.getString("Manager"),
                        rs.getString("Operator")
                        );
                camInfoList.add(camInfo);
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return camInfoList;
    }
}
