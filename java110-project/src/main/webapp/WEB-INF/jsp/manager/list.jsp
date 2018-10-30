<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>매니저 관리</title>
<link rel='stylesheet' href='/css/common.css'> <!-- root밑의 css --> <!-- 브라우저로 출력, 웹브라우저입장에서의 경로 -->
<!-- /app/manager/list로 브라우저 url에 출력 될 경우 "../" 이 의미하는 것은 한 단계 위, app방으로의 이동! 하지만 app밑에 css가 없음! 따라서 root로 이동해야함! -->
<!-- ../을 쓰고자 한다면 root까지 올라가야 함! => ../../ 두번해줘야함 -->
<!-- javascript도 웹브라우저쪽에서의 경로 -->
<style>
table, th, td {
    border: 1px solid gray;
}
</style>
</head>
<body>

<!-- 
 RequestDispatcher rd = request.getRequestDispatcher("../header.jsp");
rd.include(request, response);
 -->
<jsp:include page="../header.jsp"></jsp:include> <!-- 웹브라우저에서 페이지소스를 보면 출력되지 않음. 서버에서 실행 -->

<h1>매니저 목록(MVC)</h1>
<p><a href='form'>추가</a></p>
<table>
<thead>
<tr>
    <th>번호</th> <th>이름</th> <th>이메일</th> <th>직위</th>
</tr>
</thead>
<tbody>

<c:forEach  items="${list}" var="m">
<tr>
    <td>${m.no}</td>
    <td><a href='detail?no=${m.no}'>${m.name}</a></td>
    <td>${m.email}</td>
    <td>${m.position}</td>
</tr>
</c:forEach>

</tbody>
</table>

<jsp:include page="../footer.jsp"/>

</body>
</html>