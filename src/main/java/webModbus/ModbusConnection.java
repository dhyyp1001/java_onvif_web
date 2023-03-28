package webModbus;

import de.re.easymodbus.datatypes.Parity;
import de.re.easymodbus.datatypes.StopBits;
import de.re.easymodbus.modbusclient.ModbusClient;
import service.CuttingRtsp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ModbusConnection extends HttpServlet {
    final static int PORT = 502;
    static ModbusClient mc = new ModbusClient();
    int[] holdingRegisters = new int[20];

    //write mapping
    static int startSpraying = 0x55;//85 살포시작 --202
    static int stopEmergency = 0xcc;//204 긴급정지 --202
    static int sprayGroup = 0x31;//49 그룹살포 --201
    static int selectedPump = 1;// 1:A pump , 2 : B pump --203
    static int nSprayTimes = 2; // --204

    //read mapping
    public static String season = "-";
    public static String sprayStatus = "-";
    public static int[] tanks = new int[2];

    //readRegisters mapping
    int tankALevelReg;
    int tankBLevelReg;
    int seasonReg;
    int sprayStatusReg;

    public ModbusConnection() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(2000);
            holdingRegisters = mc.ReadHoldingRegisters(0, 20);
            tankALevelReg = holdingRegisters[2];
            tankBLevelReg = holdingRegisters[3];
            tanks = new int[]{tankALevelReg, tankBLevelReg};

            sprayStatusReg = holdingRegisters[18];
            sprayStatusCheck(sprayStatusReg);

            seasonReg = holdingRegisters[16];
            seasonCheck(seasonReg);
            System.out.println("# Get info success.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            mc.setConnectionTimeout(99999);
            mc.setStopBits(StopBits.One);
            mc.setParity(Parity.None);
            mc.setUnitIdentifier((short) 7);

            // Get the data from the request
            InputStream inputStream = request.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String data = reader.readLine();
            reader.close();

            mc.Connect(data, PORT);
            if (mc.isConnected()) {
                System.out.println("# modbus connection success, ip is : " + mc.getipAddress());
            } else {
                System.out.println("# modbus connection fail.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void sprayStatusCheck(int sprayStatusReg) {
        switch (sprayStatusReg) {
            case 1:
                sprayStatus = "동작 대기";
                break;
            case 8:
                sprayStatus = "분사 중";
                break;
            case 10:
                sprayStatus = "정지 중";
                break;
        }
    }

    void seasonCheck(int seasonReg) {
        switch (seasonReg) {
            case 10:
                season = "동절기 오류 (세척수)";
                break;
            case 11:
                season = "하절기 오류 (제설용액)";
                break;
            case 12:
                season = "동절기";
                break;
            case 13:
                season = "하절기";
                break;
        }
    }
}
