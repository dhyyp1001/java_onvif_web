package webOnvif;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.onvif.ver10.schema.Profile;

import java.net.ConnectException;
import java.util.List;

import javax.xml.soap.SOAPException;

import de.onvif.soap.OnvifDevice;
import de.onvif.soap.devices.PtzDevices;
import service.CuttingRtsp;
import webModbus.ModbusConnection;

public class OnvifController extends HttpServlet {
    private PtzDevices ptzDevices;
    private String profileToken;
    private static final long serialVersionUID = 1L;
    private float x;
    private float y;
    private float z;

    public OnvifController() {
        super();
    }

    protected void connectPTZ(String hostIp, String user, String password) {
        try {
            OnvifDevice nvt = new OnvifDevice(hostIp, user, password);
            List<Profile> profiles = nvt.getDevices().getProfiles();
            profileToken = profiles.get(0).getToken();
            ptzDevices = nvt.getPtz();
        } catch (ConnectException e) {
            System.err.println("Could not connect to NVT.");
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        x = 0;
        y = 0;
        z = 0;
    }

    protected void moveButtonClick() {
        if (ptzDevices.isAbsoluteMoveSupported(profileToken)) {
            ptzDevices.relativeMove(profileToken, x, y, z);
            init();
        }
    }

    public void buttonClick(float x, float y) {
        this.x = x;
        this.y = y;
        moveButtonClick();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        buttonClick(Float.parseFloat(request.getParameter("x")), Float.parseFloat(request.getParameter("y")));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get the data from the request
        InputStream inputStream = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String data = reader.readLine();
        reader.close();

        // Do something with the data (e.g., process it, store it, etc.)
        CuttingRtsp cr = new CuttingRtsp();
        cr.stringCutting(data);
        connectPTZ(cr.stringCutting(data).get(2), cr.stringCutting(data).get(0), cr.stringCutting(data).get(1));
    }
}
