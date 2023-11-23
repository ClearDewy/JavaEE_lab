<%@ page import="servlet.LoginListener" %><%--登录页面--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>聊天室</title>

</head>
<body>
<div class="container">
  <div class="login-wrapper">
    <div class="header">聊天室（当前人数<%=LoginListener.currentPeople%>）</div>
    <iframe src="messages.jsp" style="height: 70%;width: 100%;border: none;padding: 0" ></iframe>
    <div class="msg">
      <form action="./login-success" method="post">
        <div>
          <input type="text" name="message" maxlength="500"><input type="submit">
        </div>
      </form>
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
    width: 80%;
    height: 80%;
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
    line-height: 100px;
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
  input[type="submit"] {
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

  input[type="submit"]:hover {
    background-color: #a0cfff;
  }

  input[type="text"]
  {
    width:80%;
    outline: none;
    border: none;
    background: #e9e9eb;
    padding: 6px;
    height: 38px;
    border-radius: 30px;
    font-size: 14px;
    padding-left: 40px;
  }
  input[type="text"]::placeholder
  {
    color: #bbb;
  }



</style>
</html>


