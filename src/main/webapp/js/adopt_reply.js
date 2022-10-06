 console.log("Reply Module start");
 
 var adoptReplyService = (function(){
	
	//ajax방식
	function add(adoptreply, callback) {
		console.log("adoptreply add..");
		
		$.ajax({
			type: 'post',
			url: '/adoptreply/add',
			data: JSON.stringify(adoptreply),
			contentType: "application/json; charset=utf-8",
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			error: function(xhr, status, er) {
				if(error){
					error(er);
				}
			}
		});
		
	}
	
	//getJSON방식
	function getList(param, callback, error){
		var bno = param.bno;
		var page = param.page || 1;
		
		$.getJSON("/adoptreply/list/" + bno + "/" + page + ".json", function(data){
			if(callback){
				callback(data.replyCnt, data.list);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function get(rno, callback, error){
		$.get("/adoptreply/" + rno + ".json", function(result){
			if(callback){
				callback(result);
			}
		}).fail(function(xhr, status, err){
			if(error){
				error();
			}
		});
	}
	
	function update(adoptreply, callback, error){
		console.log("수정 댓글:" + adoptreply.seqno);
		$.ajax({
			type: 'put', 
			url: "/adoptreply/" + adoptreply.seqno,
			data: JSON.stringify(adoptreply),
			contentType: "application/json; charset=utf-8",
			
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			
			error: function(){
				if(error){
					error(er);
				}
			}
		});
	}
	
	function remove(rno, callback, error){
		console.log("삭제댓글:" + rno);
		$.ajax({
			type: 'delete', 
			url: "/adoptreply/" + rno,
			
			success: function(result, status, xhr){
				if(callback){
					callback(result);
				}
			},
			
			error: function(xhr, status, er){
				if(error){
					error(er);
				}
			}
		});
	}

	return {
		add:add, 
		getList:getList,
		get:get,
		update:update,
		remove:remove
	};
	
})();