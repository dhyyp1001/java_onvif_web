package webModbus;

import de.re.easymodbus.modbusclient.ModbusClient;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModbusButtonFunc extends HttpServlet {
    ModbusWrite mw = new ModbusWrite();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        mw.resetReg();
        mw.setReg(201, ModbusConnection.sprayGroup);
        mw.setReg(202, ModbusConnection.startSpraying);
        mw.setReg(203, ModbusConnection.selectedPump);
        mw.setReg(204, ModbusConnection.nSprayTimes);
        mw.putCommand(ModbusConnection.mc);
        System.out.println("# success write.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        mw.resetReg();
        mw.setReg(202, ModbusConnection.stopEmergency);
        mw.putCommand(ModbusConnection.mc);
        System.out.println("# success stop.");
    }
}
