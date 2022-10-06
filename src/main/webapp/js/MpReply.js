/**
 * 2022.09.29. 회원 식물 댓글 모음
 */

console.log("Reply Module start");

var replyService = (function() {
	
	/*삽입*/
	function add(reply, callback) {
		console.log("reply add....");
		
		$.ajax({
			type : 'post',
			url : '/dicReply/add',
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			success : function(result, status, xhr){
				if(callback) {
					callback(result);
				}
			},
			error : function(xhr, status, er){
				if(er) {
					er(er);
				}
			}
		});
	}
	
	function getList(param, callback, error) {
		console.log("getList");
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/dicReply/list/" + bno + "/" + page + ".json", function(data){
			if(callback) {
				callback(data.replyCnt, data.list);
			}
		}).fail(function(xhr, status, err){
			if(error) {
				error();
			}
		});
	}
	
	function getOneReply(rno, callback, error) {
		console.log("getOneReply");
		
		$.get("/dicReply/" + rno + ".json", function(result){
			if(callback) {
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error) {
				error();
			}
		});
	}
	
	function updateReply(reply, callback, error){
		console.log("수정 댓글 : " + reply.mpr_seqno);
		$.ajax({
			type : 'put',
			url : "/dicReply/" + reply.mpr_seqno,
			data : JSON.stringify(reply),
			contentType : "application/json; charset=utf-8",
			
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			
			error : function(xhr, status, er) {
				if(error){
					error(er);
				}
			}
		});
		
	}
	
	function deleteReply(rno, callback, error){
		console.log("삭제 댓글 : " + rno);
		$.ajax({
			type : 'delete',
			url : "/dicReply/" + rno,
			
			success : function(result, status, xhr) {
				if(callback) {
					callback(result);
				}
			},
			
			error : function(xhr, status, er) {
				if(error){
					error(er);
				}
			}
		});
		
	}
	
	return {
		add:add,
		getList : getList,
		getOneReply : getOneReply,
		updateReply : updateReply,
		deleteReply : deleteReply
	};
})(); 

