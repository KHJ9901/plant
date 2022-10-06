console.log("Member Module start");

var memberService = (function(){
	
	
function insertmember(member, callback, error) {
		console.log("member add....");
	$.ajax({
		type : 'post',
		url : '/member/add',
		data : JSON.stringify(member),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
	});
}

function memdel(member, callback, error) {
		console.log("member add....");
	$.ajax({
		type : 'post',
		url : '/member/memdel',
		data : JSON.stringify(member),
		contentType : "application/json; charset=utf-8",
		success : function(result, status, xhr){
			if(callback){
				callback(result);
			}
		},
		error : function(xhr, status, er){
			if(error){
				error(er);
			}
		}
	});
}
	

function 
 idDoubleCheck(member, callback, error) {
	console.log("member idcheck....");
	console.log("js : "+member.id);
	$.get("/member/" + member.id + ".json", function(result){
		if(callback){
			callback(result);
		}
	}).fail(function(xhr,status, err){
		if(error){
			error();
		}
	});
}
function EmailDoubleCheck(member, callback, error) {
	console.log("member idcheck....");
	$.get("/member/" + email + ".json", function(result){
		if(callback){
			callback(result);
		}
	}).fail(function(xhr,status, err){
		if(error){
			error();
		}
	});
}
function NicknameDoubleCheck(member, callback, error) {
	console.log("member idcheck....");
	$.get("/member/" + nickname + ".json", function(result){
		if(callback){
			callback(result);
		}
	}).fail(function(xhr,status, err){
		if(error){
			error();
		}
	});
}
	
return {
	insertmember : insertmember,
	idDoubleCheck : idDoubleCheck,
	EmailDoubleCheck : EmailDoubleCheck,
	NicknameDoubleCheck : NicknameDoubleCheck,
	memdel : memdel
}



})();