var serverUrlString = "http://192.168.0.2:8080/java_onvif_web/OnvifController";
var modbusUrlString = "http://192.168.0.2:8080/java_onvif_web/ModbusConnection";
var startBtn = document.getElementById('start_btn');
var stopBtn = document.getElementById('stop_btn');



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
	$('#start_btn').click(function() {
			startBtn.style.display = 'none';
			stopBtn.style.display = 'block';
	})
	$('#stop_btn').click(function() {
			stopBtn.style.display = 'none';
			startBtn.style.display = 'block';
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