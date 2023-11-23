package servlet;

import bean.Mysql;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Register", value = "/register")
public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println();

        if (Mysql.register(request.getParameter("username"),request.getParameter("password"))){
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("register-fail","注册失败，请重新注册");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }
}
