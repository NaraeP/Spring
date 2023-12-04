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
	<!-- mylogin.jsp -->
	<%@ include file="/WEB-INF/views/inc/header.jsp" %>
	
	<h2>Login Page</h2>
	
	<!-- 
		
		*** 주의 ***
		1. method="POST" 이어야만 한다.
		2. action="/컨텍스트명/login" 이어야만 한다.
		(우린 껍데기 교체 작업일 뿐, 실제로 서버에 넘어가서 인증처리 하는것들은 되어있기 때문에 해당 경로를 이용해야만 한다.)
		3. 아이디: name="username" 이어야만 한다.
		4. 패스워드: name="password" 이어야만 한다.
	
	 -->
	 <form method="POST" action="/spring/login">
		<div>
			<input type="text" name="username" placeholder="ID" required>
		</div>	 
		<div>
			<input type="password" name="password" placeholder="Password" required>
		</div>
		<div>
			<button class="in">로그인</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	 </form>
	 

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	</script>
</body>
</html>