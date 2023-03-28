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
    ModbusClient mc = new ModbusClient();
    int[] holdingRegisters;

    //write mapping
    int startSpraying = 0x55;//85 살포시작
    int stopEmergency = 0xcc;//204 긴급정지

    //read mapping
    public static String season = "-";
    public static String sprayStatus = "-";
    public static int[] tanks = new int[2];

    //readRegisters mapping
    int tankALevelReg = holdingRegisters[2];
    int tankBLevelReg = holdingRegisters[3];
    int seasonReg = holdingRegisters[16];
    int sprayStatusReg = holdingRegisters[18];


    public ModbusConnection() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            Thread.sleep(2000);
            holdingRegisters = mc.ReadHoldingRegisters(0, 20);
            tanks = new int[]{tankALevelReg, tankBLevelReg};
            sprayStatusCheck(sprayStatusReg);
            seasonCheck(seasonReg);
            System.out.println("# tankGet success.");

            //연결중요
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
            System.out.println("# modbus connection success, ip is : " + mc.getipAddress());
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
                sprayStatus = "동절기";
                break;
            case 13:
                sprayStatus = "하절기";
                break;
        }
    }
}
