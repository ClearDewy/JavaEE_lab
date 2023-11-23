<%--
  登录检查
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ page import="bean.Mysql" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% request.setCharacterEncoding("UTF-8");
        Object username = request.getParameter("username");
        Object password = request.getParameter("password");
        int user_id;
        if (username==null||password==null||(user_id=Mysql.login(username.toString(),password.toString()))!=-1){
//        登录失败
    %>
        <jsp:forward page="index.jsp">
            <jsp:param name="login-fail" value="登录失败，请重新登录"/>
        </jsp:forward>
    <%
        }else {
            Cookie uCookie = null,pCookie=null;
            if (request.getCookies()!=null){
                for (Cookie cookie :request.getCookies()){
                    if ("username".equals(cookie.getName())) {
                        uCookie=cookie;
                    } else if ("password".equals(cookie.getName())) {
                        pCookie=cookie;
                    }
                }
                if (uCookie==null){
                    response.addCookie(new Cookie("username", URLEncoder.encode(username.toString(), StandardCharsets.UTF_8)));
                }else {
                    uCookie.setValue(URLEncoder.encode(username.toString(), StandardCharsets.UTF_8));
                }
                if (pCookie==null){
                    response.addCookie(new Cookie("password", URLEncoder.encode(password.toString(), StandardCharsets.UTF_8)));
                }else {
                    pCookie.setValue(URLEncoder.encode(password.toString(), StandardCharsets.UTF_8));
                }
            }
            session.setAttribute("user_id",user_id);
            response.sendRedirect(request.getContextPath()+"/shop.jsp");
        }
    %>

</body>
</html>
