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
    public ModbusConnection() {
        super();
    }

    ModbusClient mc = new ModbusClient();
    public static int[] holdingRegisters = new int[20];

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        try {
            Thread.sleep(2000);
            holdingRegisters = mc.ReadHoldingRegisters(0, 20);
            System.out.println("# tank get success. #");

            //연결중요
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
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

            mc.Connect(data, 502);
            System.out.println("# modbus con success, ip is : " + mc.getipAddress() + " #");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
