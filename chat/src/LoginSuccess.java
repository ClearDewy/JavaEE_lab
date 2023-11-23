import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebServlet(name = "LoginSuccess", value = "/login-success")
public class LoginSuccess extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// 设置内容类型并指定字符编码
        request.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8
        response.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8

        List<Message> messages = Mysql.selectLast100Messages();
        Collections.reverse(messages);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Chat</title></head><body>");
            out.println("<h1>当前在线人数："+LoginListener.currentPeople+"</h1>");
            out.println("<div id='chatBox'>");

            for (Message message : messages) {
                out.println("<h3>"+message.getUsername()+"</h3>");
                out.println("<p>" + message.getMessage() + "</p>");
            }

            out.println("</div>");
            out.println("<form action='./login-success' method='post'>");
            out.println("<input type='text' name='message' />");
            out.println("<input type='submit' value='Send' />");
            out.println("</form>");
            out.println("</body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// 设置内容类型并指定字符编码
        request.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8
        response.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8

        Message m=new Message();
        m.setUsername(request.getSession().getAttribute("username").toString());
        m.setMessage(request.getParameter("message"));
        Mysql.insertMessage(m.getUsername(),m.getMessage());
        doGet(request,response);
    }
}
