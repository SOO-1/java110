<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='/css/common.css'>
</head>
<body>

<jsp:include page="header.jsp"/>

<%
String message = (String)request.getAttribute("message");
Exception error = (Exception)request.getAttribute("error");
%>
<h1>JSP:<%=message%></h1>
<p><%=error.getMessage()%></p>
<pre>
<%
error.printStackTrace(new PrintWriter(out));
%>
</pre>

<jsp:include page="footer.jsp"/>

</body>
</html>