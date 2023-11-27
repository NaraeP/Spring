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
	<!-- add.jsp -->
	<h1>게시판 <small>글쓰기</small></h1>
	
	<form method="POST" action="/spring/board/addok.do">
		
		<div>글쓰기 폼..</div>
		<div>
			<input type="submit" value="글쓰기">
		</div>
	
	</form>
	 
	

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>