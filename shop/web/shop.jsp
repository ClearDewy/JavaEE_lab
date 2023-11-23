<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ page import="bean.ShopCarItem" %>
<%@ page import="bean.Mysql" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品列表</title>

</head>
<body>
<div class="container">
    <div class="login-wrapper">
        <div class="header">商品列表</div>
        <form action="shopping.jsp" method="post" class="shopForm">
            <ul class="list">
                <%
                    for(ShopCarItem item: Mysql.getAllShop((Integer) session.getAttribute("user_id"))){
                %>
                <li><img class="pho" src="<%=item.getCommodityInfo().getImg()%>" alt="">
                    <p class="name"><br><%=item.getCommodityInfo().getName()%></p>
                    <br>
                    <div style="display: flex;justify-content: space-between;align-items: center">
                        <span class="money">¥<%=item.getCommodityInfo().getPrice()%></span>
                        <span>数量：</span>
                        <input type="number" min="0" name="<%=item.getCommodityInfo().getId()%>" class="quantity-input" value="<%=item.getCount()%>"> <!-- 数量输入框 -->
                    </div>
                    </li>
                <%
                    }
                %>
            </ul>
            <input style="right: 0" type="submit">
        </form>
    </div>
</div>

</body>
<style>
    * {
        margin: 0;
        padding: 0;
        list-style: none;
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
    form input[type="submit"] {
        position: absolute;
        border: none;
        padding: 5px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        border-radius: 5px;
        right: 10px;
        bottom: 10px;
    }

    input[type="submit"]:hover {
        background-color: #a0cfff;
    }

    input[type="text"] {
        width: 80%;
        outline: none;
        border: none;
        background: #e9e9eb;
        padding: 6px;
        height: 38px;
        border-radius: 30px;
        font-size: 14px;
        padding-left: 40px;
    }

    input[type="text"]::placeholder {
        color: #bbb;
    }

    .list {
        width: 100%;
        margin: auto;
        clear: both;
        max-height: 80%;
        overflow-y: auto;
    }

    li {
        width: 238px;
        height: 370px;
        float: left;
        padding: 10px;
    }

    .pho {
        width: 240px;
        height: 195px;
        text-align: center;
    }

    .name {
        font-family: 微软雅黑;
        font-size: 15px;
        height: 100px;
    }

    .money {
        color: red;
        font-size: 15px;
        display: inline-block;
        margin-right: 80px;
    }

    .quantity-input {
        width: 50px;
        padding: 5px;
    }
    .shopForm{
        width: 100%;
        height: 85%;
        position: relative
    }

</style>
</html>


