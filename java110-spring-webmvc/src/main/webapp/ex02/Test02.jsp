<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Test02 페이지 컨트롤러</h1>

<!-- requestScope이 없을 경우 pagecontext - servletrequest- httpsession - servletcontext 순서로 값을 찾게된다.-->
name : ${requestScope.name}<br> 
age : ${requestScope.age}<br>

</body>
</html>