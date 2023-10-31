<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../style.jsp"></jsp:include>
<style type="text/css">
.profileImg {
	width : 50px;
	height : 50px;
	border : 1px solid grey;
}
</style>
<meta charset="UTF-8">
<title>GUTEAM : 프로필</title>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
<input type="image" class="profileImg" alt="${vo.memberId }" src="display?fileName=${vo.memberImageName }" readonly />
<h2>${vo.memberId }님의 프로필</h2>
	<div>
		<button onclick ="popUp();">쪽지</button>
		<a href="addCash"><button>캐쉬 충전</button></a>
		<a href="../friend/list"><button>친구 목록</button></a>
	</div>
	<br>
	<div>
		<form action="delete" method="post">
		<sec:csrfInput/>
			<a href="update"><button type="button">회원 수정</button></a>
			<input type="hidden" name="memberId" id="memberId" value="${vo.memberId }">
			<input type="submit" value="회원 탈퇴">	
		</form>
		<hr>
		<div id="commentsArea">
			<button id="showMyComments">내가 쓴 댓글 보기</button>
			<div id="myComments">
			<ul id="myCommentsList"></ul></div>
		</div>
	</div>
<input type="hidden" id="alert" value="${alert }">
	
<script type="text/javascript">
	function popUp(){
		window.open('../message/list', '쪽지함', 'width=715px,height=425px,scrollbars=yes');
	};
	
	$(document).ready(function(){
		function dateFormat(date) {
	        var month = date.getMonth() + 1;
	        var day = date.getDate();
	        var hour = date.getHours();
	        var minute = date.getMinutes();	
	        var second = date.getSeconds();

	        month = month >= 10 ? month : '0' + month;
	        day = day >= 10 ? day : '0' + day;
	        hour = hour >= 10 ? hour : '0' + hour;
	        minute = minute >= 10 ? minute : '0' + minute;
	        second = second >= 10 ? second : '0' + second;

	        return date.getFullYear() + '-' + month + '-' + day + ' ' + hour + ':' + minute + ':' + second;
		}
		
		var result = $('#alert').val();
			if(result == 'success'){
				alert('회원정보가 수정되었습니다!');
			}
			else if(result == 'fail'){
				alert('요청에 실패하였습니다.');
			}
			
			$('#showMyComments').click(function(){
				//console.log("클릭확인");
				
				var memberId = $('#memberId').val();
				var url = '../boardComment/comments/'+memberId;
				var list = '';
				$.getJSON(
					url,
					function(data){
						data.sort((a,b) => b.createdDate - a.createdDate); // 해당 List데이터를 날짜 역순으로 정렬
						
						console.log(data);
					
						$(data).each(function(){
						var createdDate = new Date(this.createdDate);
						if(this.content!="삭제된 댓글입니다."){
						list += '<li class="comment_reply_item">'
							 +  '<span>'+this.nickname+'</span>'
							 +  '<span><a href="/guteam/gameBoard/detail?gameBoardId='+this.boardId+'&page=1&gameId='+this.gameId+'">'+this.content+'</a></span>'
							 +	'<span>'+dateFormat(createdDate)+'</span>'
							 +  '</li>';
						}
						});
						$('#myCommentsList').html(list);
					}
				);//end getJson
	
			});//end showMyComments
	});
</script>
</body>

