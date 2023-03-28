var serverUrlString = "http://192.168.0.2:8080/java_onvif_web/OnvifController";
var ModbusButtonFuncUrlString = "http://192.168.0.2:8080/java_onvif_web/ModbusButtonFunc";
var startBtn = document.getElementById('start_btn');
var stopBtn = document.getElementById('stop_btn');
var status = document.getElementById('modbus_status_id').innerText;


$(document).ready(function() {
	$('#ajaxConPostLButton').click(function() {
		AjaxConPostLB();
	})
	$('#ajaxConPostRButton').click(function() {
		AjaxConPostRB();
	})
	$('#ajaxConPostUButton').click(function() {
		AjaxConPostUB();
	})
	$('#ajaxConPostDButton').click(function() {
		AjaxConPostDB();
	})

    if(status === '동작 대기'){
        stopBtn.style.display = 'none';
        startBtn.style.display = 'block';
    }else if(status ==='분사 중'){
        stopBtn.style.display = 'block';
        startBtn.style.display = 'none';
    }else if(status ==='정지 중'){
        stopBtn.style.display = 'block';
        startBtn.style.display = 'none';
        stopBtn.style.backgroundColor = 'gray';
        stopBtn.style.pointerEvents = 'none';
    }


	$('#start_btn').click(function() {
	    AjaxStartBtn();
	})
	$('#stop_btn').click(function() {
	    AjaxStopBtn();
	})
})
function AjaxConPostLB() {
	var url = serverUrlString;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
			x: -0.05,
			y: 0
		},
	})
}
function AjaxConPostRB() {
	var url = serverUrlString;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
			x: +0.05,
			y: 0
		},
	})
}
function AjaxConPostUB() {
	var url = serverUrlString;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
			x: 0,
			y: -0.1
		},
	})
}
function AjaxConPostDB() {
	var url = serverUrlString;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
			x: 0,
			y: +0.1
		},
	})
}
function AjaxStartBtn() {
	var url = ModbusButtonFuncUrlString;
	$.ajax({
		type: "GET",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
		},
	})
}
function AjaxStopBtn() {
	var url = ModbusButtonFuncUrlString;
	$.ajax({
		type: "POST",
		url: url,
		dataType: "jsonp",
		jsonpCallback: "myCallback",
		data: {
		},
	})
}