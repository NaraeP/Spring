<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Code</title>
<link rel="stylesheet" href="http://pinnpublic.dothome.co.kr/cdn/example-min.css">
<style>

</style>
</head>
<body> 
	<!-- add.jsp -->
	<h1>Code <small>add</small></h1>
	
	<form method="POST", action="/code/addok.do">
		<table class="vertical">
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" class="full" required></td>
			</tr>
			<tr>
				<th>코드</th>
				<td><textarea name="code" class="full" required></textarea></td>
			</tr>
			<tr>
				<th>언어</th>
				<td>
					<select name="language">
						<option value="java">Java</option>
						<option value="sql">SQL</option>
						<option value="html">HTML</option>
						<option value="css">CSS</option>
						<option value="js">JavaScript</option>
					</select>
				</td>
			</tr>
		</table>
		<div>
			<button type="submit" class="add">추가하기</button>
			<button type="button" class="back" onclick="location.href='/code/list.do';">돌아가기</button>
		</div>
	</form>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	<script>
	
	</script>
</body>
</html>