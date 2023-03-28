package webModbus;

import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

import java.io.IOException;

public class ModbusWrite {

    int[] regWrite = new int[] { 0x0041, 0, 0, 0, 0, 0, 0x0042 };
    int[] regRead = new int[7];

    void resetReg()
    {
        setReg(201, 0);
        setReg(202, 0);
        setReg(203, 0);
        setReg(204, 0);
        setReg(205, 0);
    }

    void setReg(int addr, int val)
    {
        regWrite[addr - 200] = val;
    }

    int getReg(int addr)
    {
        return regRead[addr - 100];
    }

    void putCommand(ModbusClient mc)
    {
        if (mc.isConnected())
        {
            try {
                mc.WriteMultipleRegisters(200, regWrite);
            } catch (ModbusException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            } catch (SerialPortTimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
