<%@page import="bitcamp.java110.cms.domain.Student"%>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 관리</title>
<link rel='stylesheet' href='../css/common.css'>
<style>
table, th, td{
border : 1px solid gray;
}
</style>
</head>
<body>
<header>
<h1>비트캠프</h1>
<ul>

<li><a href = '/auth/login'>로그인</a></li>
<li><a href='/student/list'>학생관리</a></li>
<li><a href='/teacher/list'>강사관리</a></li>
<li><a href='/manager/list'>매니저관리</a></li>

</ul>
</header>
<h1>학생 상세정보(MVC)</h1>

<%
Student s = (Student)request.getAttribute("student");
if(s == null) {
%>
    <p>해당 이메일의 학생이 없습니다.</p>
<%
} else {
%>


<table>
<tbody>
<tr><th>번호</th><td><%=s.getNo()%></td></tr>
<tr><th>이름</th><td><%=s.getName()%></td></tr>
<tr><th>이메일</th><td><%=s.getEmail()%></td></tr>
<tr><th>암호</th><td><%=s.getPassword()%></td></tr>
<tr><th>최종학력</th><td><%=s.getSchool()%></td></tr>
<tr><th>전화</th><td><%=s.getTel()%></td></tr>
<tr><th>재직여부</th><td><%=s.isWorking()%></td></tr>
</tbody>
</table>
<button type='button' onclick ='remove()'>삭제</button>
<script>
function remove() {
location.href='delete?no=<%=s.getNo()%>'
}
</script>
<%
}
%>

<footer>
<p>&copy;자바110기</p>
</footer>
</body>
</html>
