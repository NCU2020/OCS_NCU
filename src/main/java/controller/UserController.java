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
import java.sql.Date;
import java.util.List;

public class UserController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        UserService userService = new UserService();
        String method = request.getParameter("method");
        List<User> users = null;


        switch (method)
        {
            /* 根据用户名查找 */
            case "getUserByName":
            {
                String name = request.getParameter("name");

                if (name != null)
                {
                    users = userService.getUserByName(name);
                }
                break;
            }

            /* 根据id查找 */
            case "getUserById":
            {
                String ID = request.getParameter("id");

                if (ID != null)
                {
                    int id = Integer.parseInt(ID);
                    users.add(userService.getUserById(id));
                }
                break;
            }

            /* 根据性别查找 */
            case "getUserBySex":
            {
                String sex = request.getParameter("sex");
                if (sex != null)
                {
                    users = userService.getUserBySex(sex);
                }
                break;
            }

            /* 查找所有用户 */
            case "findAll":
            {
                users = userService.findAll();
                break;
            }

            /* 添加用户 */
            case "add":
            {
                String Id = request.getParameter("id");
                String name = request.getParameter("name");
                String password = request.getParameter("password");
                String Birthday = request.getParameter("birthday");
                String sex = request.getParameter("sex");
                String image = request.getParameter("image");
                String message = request.getParameter("message");
                String req = request.getParameter("request");

                if (Id!=null && name!=null && password!=null && Birthday!=null && sex!=null && image!=null && message!=null && req!=null)
                {
                    int id = Integer.parseInt(Id);
                    Date birthday = Date.valueOf(Birthday);
                    User user = new User();

                    user.setId(id);
                    user.setName(name);
                    user.setPassword(password);
                    user.setBirthday(birthday);
                    user.setSex(sex);
                    user.setImage(image);
                    user.setRequest(req);

                    userService.add(user);
                }
                break;
            }

            /* 删除用户 */
            case "delete":
            {
                String Id = request.getParameter("id");

                if (Id != null)
                {
                    int id = Integer.parseInt(Id);
                    User user = new User();
                    user.setId(id);
                    userService.delete(user);
                }
                break;
            }

            default:
                break;
        }

        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(users);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);

    }
}
