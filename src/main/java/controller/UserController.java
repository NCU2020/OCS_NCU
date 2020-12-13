/************************
     Author: 丁宇翔
     Date: 2020.12.13
 ************************/
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.UserService;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserService userService = new UserService();
        String method = request.getParameter("method");
        List<User> users = null;

        /* 根据用户名查找 */
        if (method.equals("getUserByName"))
        {

            String name = request.getParameter("name");
            if (name != null)
            {
                users = userService.getUserByName(name);
            }
        }
        /* 根据id查找 */
        else if (method.equals("getUserById"))
        {
            String ID = request.getParameter("id");

            if (!ID.equals(null))
            {
                int id = Integer.parseInt(ID);
                users.add(userService.getUserById(id));
            }
        }
        /* 根据性别查找 */
        else if (method.equals("getUserBySex"))
        {
            String sex= request.getParameter("sex");
            if (!sex.equals(null))
            {
                users = userService.getUserBySex(sex);
            }
        }
        /* 查找全部 */
        else if (method.equals("findAll"))
        {
            users = userService.findAll();
        }

        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(users);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);

    }
}
