<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Example</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body> 
	<!-- test.jsp -->
	<h1>WebSocket <small>연결 테스트</small></h1>	

	<div>
		<button type="button" class="in" id="btnConnect">연결하기</button>
		<button type="button" class="out" id="btnDisconnect">종료하기</button>
	</div>
	
	<hr>
	
	<div>
		<input type="text" class="long" id="msg">
		<button type="button" id="btnMsg">보내기</button>
	</div>
	
	<hr>
	
	<div class="message full"></div>
	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
		//클라이언트 <-> 서버
		const url = 'ws://localhost:8090/socket/testserver.do';
		
		//웹 소켓 참조 변수
		let ws;
		
		//연결버튼을 누르면,
		//1. 소켓 생성
		//2. (소켓으로) 서버 접속(연결)
		//3. 통신
		//4. 서버 접속 해제(종료)
		$('#btnConnect').click(function() {

			//1. 소켓 생성
			//2. 서버 접속(연결)
			//소켓 생성 뿐만아니라 동시에 서버 접속까지 된다.
			ws = new WebSocket(url);
			
			//내입장에서는 상대방과 언제 연결이 되는지 예측할 수 없다.
			//따라서 언제발생하는지 모르니까 소켓 이벤트 > 콜백 형태로 알 수 있다.
			
			//소켓 이벤트 > 콜백 형태
			ws.onopen = function(evt) {
				//서버측에서 소켓  연결을 받아들이고 연결이 되는 순간 발생!
				log('서버와 연결되었습니다.');
				
			}
			
			//실제 연결이 끊긴 순간 발생
			ws.onclose = function(evt) {
				log('서버와 연결이 종료되었습니다.');
			}
			
			//서버가 나에게 메시지를 보낼 때 발생
			ws.onmessage = function(evt) {
				log(evt.data);
			}
			
			//에러발생 시 호출되는 이벤트
			ws.onerror = function(evt) {
				log('에러가 발생했습니다.' + evt);
			}
			
		});

		
		$('#btnDisconnect').click(function() {
			
			//연결 끊기 시도
			ws.close();
			log('연결을 종료합니다.');
			
		});
		
		function log(msg) {
			$('.message').prepend(`
					
				<div>[\${new Date().toLocaleTimeString()}] \${msg}</div>		
			`);
			
		}
		
		//버튼을 클릭하면 소켓으로 전달하기
		$('#btnMsg').click(function(){
			
			//연결된 서버에게 메시지 전송하기
			/* ws.send('전달할 메시지'); */
			ws.send($('#msg').val());
			log('메시지를 전송했습니다.');
			
			$('#msg').val('');
			
		});
		
	</script>
</body>
</html>