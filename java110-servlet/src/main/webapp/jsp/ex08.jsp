<%@ page import="java.io.File,java.io.InputStream" %>
<%@ page language="java" 
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.net.*,java.util.*"
    import="java.lang.ref.*"
    trimDirectiveWhitespaces="true"
    buffer="1kb"
    autoFlush="false"
    %>  <!-- import 여러개 OK,page import 위치 상관X, whitespace를 없애라! -->
<%@ page import="java.lang.reflect.Method" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP</title>
</head>
<body>
<h1>지시 명령문 (Directive Element) - include</h1>
<pre>
 - 외부 파일의 내용을 JSP에 포함할 때 사용한다.
    &lt;%@ include file="포함할 파일"%>
 - RequestDispatcher의 include가 아니다.
 - 즉, include 지시명령은 실행을 위임하는 것이 아니라,
        파일의 내용을 포함하는 것이다.
 - 동작원리
    - 일단 지정한 파일의 내용을 JSP에 포함한다.
    - 그런 후 서블릿 클래스를 만든다.
</pre>
    <%@ include file="ex08_1.txt"%>
    
    <hr> <!-- 가로줄 쫙! -->
    
    <%@ include file="ex08_2.txt"%>
</body>
</html>