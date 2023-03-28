<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="webModbus.ModbusConnection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" http-equiv="refresh" content="5">
<style>
body{
	background-color: black;
}
.text {
    color: white;
    font-size: 20px;
}

.my_btn {
	background-color: gray;
	color: white;
	border: none;
	width: 100px;
	height: 100px;
	font-size: 60px;
	margin: 2px;
	margin-bottom: 5px;
	text-align: center;
	border-radius: 12px;
}

.my_btn:active {
	background-color: rgb(144, 196, 210);
}

.modbus_btn {
    font-size: 45px;
    width: 250px;
    height: 90px;
}

.space {
    color: white;
	border: none;
	width: 100px;
	height: 100px;
	font-size: 60px;
	margin: 2px;
	margin-bottom: 5px;
	text-align: center;
	border-radius: 12px;
    background-color: black;
}

#arrow_btns {
    margin-left: 100px;
    float: left;
}

.modbus_status {
    font-size: 50px;
    color: white;
    text-align: center;
}

#modbus_part {
    margin-left: 150px;
    margin-top: 80px;
    float: left;
}

.all_comp {
    margin-top:70px;
}

#start_btn {
    background-color: rgb(66, 170, 66);
    display: block;
}

#stop_btn {
    background-color: rgb(193, 17, 17);
    display: none;
}

.tanks-custom {
    width: 350px;
	height: 50px;
    accent-color: rgb(103, 153, 196);
    margin-bottom: 10px;
            }

#tanks {
    margin-left: 80px;
}

#season {
    float: left;
    text-align: center;
}

</style>
</head>
<body>
<div class="all_comp">
    <div id="top_comp">
        <div id="tanks">
            <div class="text">Tank A Level : <span class="text"><%=ModbusConnection.tanks[0]%></span></div>
            <progress value=<%=ModbusConnection.tanks[0]%> max="100" class="tanks-custom" id="tank-a"></progress>
            <div class="text">Tank B Level : <span class="text"><%=ModbusConnection.tanks[1]%></span></div>
            <progress value=<%=ModbusConnection.tanks[1]%> max="100" class="tanks-custom" id="tank-b"></progress>
        </div>
        <div class="text" id="season"><%=ModbusConnection.season%></div>
    </div>
    <div id="arrow_btns">
        <input type="button" class="space" value="">
        <input type="button" class="my_btn" id="ajaxConPostUButton" value="▲">
        <input type="button" class="space" value="">
        <br>
        <input type="button" class="my_btn" id="ajaxConPostLButton" value="◀">
        <input type="button" class="my_btn" id="ajaxConPostDButton" value="▼">
        <input type="button" class="my_btn" id="ajaxConPostRButton" value="▶">
    </div>
    <div id="modbus_part">
        <div class="modbus_status"><%=ModbusConnection.sprayStatus%></div>
        <input type="button" id="start_btn" class="my_btn modbus_btn" value="START">
        <input type="button" id="stop_btn" class="my_btn modbus_btn" value="STOP">
    </div>
</div>

<script type="text/javascript" src="ajax/jquery-3.6.3.min.js"></script>
<script type="text/javascript" src="ajax/ajaxCommunication.js"></script>
</body>
</html>
 