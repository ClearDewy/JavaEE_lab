package servlet;

import bean.Message;
import bean.Mysql;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "servlet.LoginSuccess", value = "/login-success")
public class LoginSuccess extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// 设置内容类型并指定字符编码
        request.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8
        response.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8

        Message m=new Message();
        m.setUsername(request.getSession().getAttribute("username").toString());
        m.setMessage(request.getParameter("message"));
        Mysql.insertMessage(m.getUsername(),m.getMessage());
        request.getRequestDispatcher("/login-success.jsp").forward(request,response);
    }
}
