import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@WebServlet(name = "LoginSuccess", value = "/login-success")
public class LoginSuccess extends HttpServlet {

    class Message{
        public String username;
        public String message;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");// 设置内容类型并指定字符编码
        request.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8
        response.setCharacterEncoding("UTF-8");// 设置字符编码为 UTF-8
        Queue<Message> chatMessages = (Queue<Message>) getServletContext().getAttribute("chatMessages");
        if (chatMessages==null){
            chatMessages=new ConcurrentLinkedQueue<Message>();
            getServletContext().setAttribute("chatMessages",chatMessages);
        }

        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Chat</title></head><body>");
            out.println("<h1>当前在线人数："+LoginListener.currentPeople+"</h1>");
            out.println("<div id='chatBox'>");

            for (Message message : chatMessages) {
                out.println("<h3>"+message.username+"</h3>");
                out.println("<p>" + message.message + "</p>"); // 使用转义来防止XSS攻击
            }

            out.println("</div>");
            out.println("<form action='chat' method='post'>");
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

        Queue<Message> chatMessages = (Queue<Message>) getServletContext().getAttribute("chatMessages");
        Message m=new Message();
        m.username=request.getSession().getAttribute("username").toString();
        m.message=request.getParameter("message");
        chatMessages.add(m);
        if (chatMessages.size()>500){
            chatMessages.poll();
        }
        getServletContext().setAttribute("chatMessages",chatMessages);
        doGet(request,response);
    }
}
