import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8"); //设置内容类型并指定字符编码

        Cookie[] cookies=request.getCookies();
        String username="";
        String password="";
        String remember="";

        if(cookies!=null){
            //遍历查找用户名 密码
            for (Cookie i:cookies){
                if ("username".equals(i.getName())){
                    //找到并解决编码问题
                    username= URLDecoder.decode(i.getValue(), StandardCharsets.UTF_8);
                }else if("password".equals(i.getName())) {
                    password = i.getValue();
                }
            }
            //如果用户名 密码都存在
            if(!username.isEmpty()&&!password.isEmpty()){
                remember="checked";
            }
        }

        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>登陆界面</title>");
        out.println("</head><body>");
        if (request.getAttribute("errorMessage")!=null){
            out.println("<h1>"+request.getAttribute("errorMessage").toString()+"</h1>");
        }
        out.println("<form action='./login' method='post'>");
        out.println("姓名: <input type='text' name='username' value='" + username + "'><br>");
        out.println("学号: <input type='password' name='password' value='" + password + "'><br>");
        out.println("<input type='checkbox' name='remember' " + remember + "><label for='remember'>记住密码</label><br>");
        out.println("<input type='submit' value='提交'><input type='reset' value='重置'><br>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        boolean isValidUser=Mysql.find(username,password);
        if (isValidUser){
            Cookie usernameCookie = null, passwordCookie = null;
            Cookie[] cookies = request.getCookies();
            if(cookies!=null){
                for(Cookie i:cookies){
                    if("username".equals(i.getName())){
                        usernameCookie=i;
                    }
                    if("password".equals(i.getName())){
                        passwordCookie=i;
                    }
                }
            }
            if("on".equals(remember)){
                if(usernameCookie==null){
                    usernameCookie=new Cookie("username", URLEncoder.encode(username,StandardCharsets.UTF_8));
                } else{
                    usernameCookie.setValue(URLEncoder.encode(username,StandardCharsets.UTF_8));  //更新
                }
                if(passwordCookie==null){
                    passwordCookie=new Cookie("password", URLEncoder.encode(password,StandardCharsets.UTF_8));
                } else{
                    passwordCookie.setValue(URLEncoder.encode(password,StandardCharsets.UTF_8));  //更新
                }

                usernameCookie.setMaxAge(24*30*60*60); //设置记忆时间
                passwordCookie.setMaxAge(24*30*60*60);

                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            } else {

            }
            //Filter 验证设置
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath()+"/login-success");
        }


    }
}
