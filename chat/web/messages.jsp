<%@ page import="bean.Mysql" %>
<%@ page import="bean.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>

<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>聊天室</title>
<%--    每三秒刷新一次--%>
    <meta http-equiv="refresh" content="3">
    <script>
        // 自动滚动到最下面
        window.onload = function() {
            window.scrollTo(0, document.body.scrollHeight);
        }
    </script>
</head>

<body>
<!--chatbox-->
<div class="chatBox">

    <%
        List<Message> messages = Mysql.selectLast100Messages();
        Collections.reverse(messages);
        String myUsername=session.getAttribute("username").toString();
        for (Message message : messages) {
            if (myUsername.equals(message.getUsername())){
    %>
    <div class="message my_message">
        <p><%=message.getMessage()%><br><span><%=message.getUsername()%></span> </p>
    </div>
    <%
            }else {
    %>
    <div class="message frnd_message">
        <p><%=message.getMessage()%><br><span><%=message.getUsername()%></span> </p>
    </div>
    <%
            }
        }
    %>

</div>
</body>
<style>

    .chatBox
    {
        position: relative;
        width: 92%;
        padding: 50px;
        overflow-y: auto;
    }
    .message
    {
        position: relative;
        display: flex;
        width: 100%;
        margin: 5px 0;
    }
    .message p
    {
        position: relative;
        right: 0;
        text-align: right;
        max-width: 65%;
        padding: 12px;
        background: #dcf8c6;
        border-radius: 10px;
        font-size: 0.9em;
    }
    .message p::before
    {
        content: '';
        position: absolute;
        top: 0;
        right: -12px;
        width: 20px;
        height: 20px;
        background: linear-gradient(135deg,#dcf8c6 0%,#dcf8c6 50%,transparent 50%,transparent);
    }
    .message p span
    {
        display: block;
        margin-top: 5px;
        font-size: 0.85em;
        opacity: 0.5;
    }
    .my_message
    {
        justify-content: flex-end;
    }
    .frnd_message
    {
        justify-content: flex-start;
    }
    .frnd_message p
    {
        background: #e9e9eb;
        text-align: left;
    }
    .message.frnd_message p::before
    {
        content: '';
        position: absolute;
        top: 0;
        left: -12px;
        width: 20px;
        height: 20px;
        background: linear-gradient(225deg,#e9e9eb 0%,#e9e9eb 50%,transparent 50%,transparent);
    }

</style>
</html>
