package servlet;

import service.UserService;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException
    {
        String Id = request.getParameter("id");

        int id = Integer.parseInt(Id);
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UserService userService = new UserService();
        User user;

        user = userService.getUserById(id);

        if (password.equals(user.getPassword()))
        {
            session.setAttribute("logState","SUCCESS");
            session.setAttribute("user", user);
        }

        else
        {
            session.setAttribute("logState", "FAIL");
        }

        respone.sendRedirect("index.jsp");
    }
}
