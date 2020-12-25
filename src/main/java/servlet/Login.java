package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String Id = request.getParameter("id");

        int id = Integer.parseInt(Id);
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        User user;

        user = userService.getUserById(id);

        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (password.equals(user.getPassword()))
        {
            user.setPassword("");
            session.setAttribute("logState","SUCCESS");
            session.setAttribute("user", user);
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(user);
            out.write(jsonStr);
        }

        else
        {
            session.setAttribute("logState", "FAIL");
            out.write("FAIL");
        }
    }
}
