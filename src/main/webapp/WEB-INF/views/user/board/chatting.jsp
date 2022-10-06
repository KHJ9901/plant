<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	.container {
		width:600px;
		padding: 10px;
		border: 2px solid #3e3e41;
		background-color: lightgreen;
	}
	
	.talk_view {
		position: relative;
		width: 580px;
		height: 200px;
		padding: 5px;
		border: 1px dotted #3e3e41;
		background-color: honeydew;
	}
	
	#talk_input{
	   width: 90%;
	}
</style>
<script language="javascript" type="text/javascript">
	var thisUserld = "Marbong";
	
	function sendTalk() {
		var obj = document.getElemenByld("talk_input");
		if(obj) {
			
			addTalk(obj.value);
			obj.value="";
			obj.focus();
		}
	}
	
	function addTalk(content) {
		var obj = document.getElementByld("talk_view");
		if(obj) {
			var line = thisUserld + "-" + content + "<br>";
			obj.innerJSP += line;
		}
	}
</script>
</head>
<body>
	<div class="container">
		<div id="talk_view" class="talk_view"></div>
		<input type="text" name="talk_input" id="talk_input" class="talk_input">
		<input type="button" name="talk_send" id="talk_send" value="입력" OnClick="sendTalk()">
	</div>
</body>