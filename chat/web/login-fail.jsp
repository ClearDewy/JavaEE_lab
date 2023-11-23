<%--
  登录失败的页面
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>登录失败</title>
</head>
<body>
<jsp:forward page="index.jsp">
    <jsp:param name="login-fail" value="登录失败，请重新登录"/>
</jsp:forward>
</body>
</html>
