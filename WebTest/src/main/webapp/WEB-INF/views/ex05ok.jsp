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
	<!-- ex05ok.jsp -->
	<h1>결과 <small>단일값</small></h1>
	
	<div class="message" title="데이터">
		${data}
	</div>
	


	<h1>결과 <small>복합값</small></h1>
	<div class="message" title="result">
		${result}
	</div>
	

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>