package servlet;

import service.UserService;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class Logon extends HttpServlet
{
    private boolean ID(int id, List<User> users)
    {
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getId() == id)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String email = req.getParameter("logon-email");
        String name = req.getParameter("logon-name");
        String password = req.getParameter("logon-pwd");
        String sex = req.getParameter("logon-sex");
        String birthday = req.getParameter("logon-birthday");
        HttpSession session = req.getSession();

        UserService userService = new UserService();
        List<User> users = userService.findAll();
        int id;

        do
        {
            id = (int)(Math.random()*1000000000);
        }
        while (ID(id, users));
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        if (sex.equals("male"))
        {
            sex = "M";
        }
        else
        {
            sex = "F";
        }
        user.setSex(sex);
        user.setBirthday(Date.valueOf(birthday));
        userService.add(user);

        session.setAttribute("id", id);
        resp.sendRedirect("logon.jsp");
    }
}
