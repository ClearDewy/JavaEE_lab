<%@ page import="java.nio.charset.StandardCharsets" %>
<%@ page import="java.net.URLDecoder" %><%--登录页面--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>登录</title>

</head>
<body>
<div class="container">
  <div class="login-wrapper">
    <div class="header">Login</div>
    <div class="form-wrapper">
      <%
        request.setCharacterEncoding("UTF-8");
        String username="",password="";
        if (request.getCookies()!=null){
          for (Cookie cookie :request.getCookies()){
            if ("username".equals(cookie.getName())) {
              username = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            } else if ("password".equals(cookie.getName())) {
              password = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            }
          }
        }

      %>

      <form action="login.jsp" method="post">
        <input type="text" name="username" placeholder="username" class="input-item" value="<%=username%>">
        <input type="password" name="password" placeholder="password" class="input-item" value="<%=password%>">
        <input type="checkbox" id="remember" name="remember">
        <label for="remember">Remember me</label>
        <input type="reset" value="Reset" style="margin-left: 100px">
        <% if (request.getParameter("login-fail")!=null){%>
        <p style="color: #F56C6C"><%=request.getParameter("login-fail")%></p>
        <% }%>
        <input type="submit" value="Login" class="btn"/>
      </form>
    </div>
    <div class="msg">
      Don't have account?
      <a href="register.jsp">Sign up</a>
    </div>
  </div>
</div>

</body>
<style>
  * {
    margin: 0;
    padding: 0;
  }
  html {
    height: 100%;
  }
  body {
    height: 100%;
  }
  .container {
    height: 100%;
    background-image: linear-gradient(to right, #fbc2eb, #a6c1ee);
  }
  .login-wrapper {
    background-color: #fff;
    width: 358px;
    height: 588px;
    border-radius: 15px;
    padding: 0 50px;
    position: relative;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
  }
  .header {
    font-size: 38px;
    font-weight: bold;
    text-align: center;
    line-height: 200px;
  }
  .input-item {
    display: block;
    width: 100%;
    margin-bottom: 20px;
    border: 0;
    padding: 10px;
    border-bottom: 1px solid rgb(128, 125, 125);
    font-size: 15px;
    outline: none;
  }
  .input-item::placeholder {
    text-transform: uppercase;
  }
  .btn {
    text-align: center;
    padding: 10px;
    width: 100%;
    margin-top: 40px;
    background-image: linear-gradient(to right, #a6c1ee, #fbc2eb);
    color: #fff;
  }
  .msg {
    text-align: center;
    line-height: 88px;
  }
  a {
    text-decoration-line: none;
    color: #abc1ee;
  }
  /* 样式化复选框 */
  input[type="checkbox"] {
    margin-right: 5px;
    cursor: pointer;
  }

  /* 样式化标签 */
  label {
    cursor: pointer;
  }

  /* 样式化重置按钮 */
  input[type="reset"] {
    border: none;
    padding: 5px 10px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 5px;
  }

  input[type="reset"]:hover {
    background-color: #d32f2f;
  }
</style>
</html>


