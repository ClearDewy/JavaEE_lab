<%@ page import="java.util.Map" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="bean.Mysql" %><%--
  处理商品选购
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        int user_id= (int) session.getAttribute("user_id");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            Mysql.updateShopCar(user_id,Integer.parseInt(paramName),Integer.parseInt(paramValues[0]));
        }
        response.sendRedirect(request.getContextPath()+"/shoplist.jsp");
    %>
</body>
</html>
