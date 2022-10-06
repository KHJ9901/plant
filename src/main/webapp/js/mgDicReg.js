function inputCate() {
	console.log("식물 카테고리 선택항");
	var sel = document.forms["memRegForm"]["selDomain"].value;
	console.log("선택 옵션값 : " + sel)
	document.forms["mpRegForm"]["plantcate"].value = sel;
	
	if(sel != ""){
		document.forms["mpRegForm"]["plantcate"].readOnly = true;
		document.forms["mpRegForm"]["plantcate"].style.backgroundColor ='lightyellow';
	} else {
		document.forms["mpRegForm"]["plantcate"].readOnly = false;
		document.forms["mpRegForm"]["plantcate"].focus();		// 직접입력 선택시 텍스트 창 자동 선택
		document.forms["mpRegForm"]["plantcate"].style.backgroundColor ='lightblue';
		}
}