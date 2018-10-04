<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<h1>RequestDispatcher의 including vs include 지시명령</h1>

<jsp:include page="ex09_1.jsp"/> <!-- 첫 태그 앞에 슬래쉬(/)를 해줌으로써 끝태그를 달지 않아도됨!  -->

<hr>

<jsp:include page="ex09_2.jsp"></jsp:include>

</body>
</html>