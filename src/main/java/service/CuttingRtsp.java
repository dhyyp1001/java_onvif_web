package service;

import java.util.ArrayList;
import java.util.List;

public class CuttingRtsp {
    public List<String> stringCutting(String rtsp) {

        List<String> rtspObj = new ArrayList<>();

        // Extract the username and password from the URL
        String[] parts1 = rtsp.split("@");
        String[] credentials = parts1[0].replace("rtsp://", "").split(":");
        String username = credentials[0];
        String password = credentials[1];

        // Extract the IP address from the URL
        String[] parts2 = parts1[1].split(":");
        String ipAddress = parts2[0];

        // Output the results
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("IP Address: " + ipAddress);
        rtspObj.add(username);
        rtspObj.add(password);
        rtspObj.add(ipAddress);
        return rtspObj;
    }
}
