<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="/css/mgLogin.css">
	<link rel="stylesheet" href="/css/mgW3.css">
	<link rel="stylesheet" href="/css/mgHome.css">
	<link rel="stylesheet" href="/css/memBoard.css">
	<link rel="stylesheet" href="/css/mgDicReg.css">
</head>

<script>
function init(){
	var stat = document.getElementsByName("stat");
	if(stat[0].value != 'null')  {
		/* alert(stat[0].value); */
		document.getElementById("box").style.visibility = "visible";
	}
}
</script>
