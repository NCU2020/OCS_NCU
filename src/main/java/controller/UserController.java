/************************
     Author: 丁宇翔
     Date: 2020.12.13
 ************************/
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.RelationService;
import service.UserService;
import vo.Relation;
import vo.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class UserController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null)
        {
            UserService userService = new UserService();
            String method = request.getParameter("method");
            List<User> users = new ArrayList<>();

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
                        User user = userService.getUserById(id);
                        System.out.println(id);
                        System.out.println(user.getId());
                        user.setPassword("");
                        users.add(user);
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

                /* 查找好友 */
                case "getFriends":
                {
                    String User = request.getParameter("user");
                    if (User != null)
                    {
                        int user = Integer.parseInt(User);
                        RelationService relationService = new RelationService();
                        List<Relation> relations;
                        relations = relationService.getFriends(user);
                        List<User> userList = new ArrayList<>();

                        for (int i = 0; i < relations.size(); i++)
                        {
                            int friendID;
                            if (relations.get(i).getFrom() == user)
                            {
                                friendID = relations.get(i).getTo();
                            }
                            else
                            {
                                friendID = relations.get(i).getFrom();
                            }

                            User userI = userService.getUserById(friendID);
                            userI.setPassword("");
                            userList.add(userI);
                        }
                        users = userList;
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

                    if (Id != null && name != null && password != null && Birthday != null && sex != null && image != null && message != null && req != null)
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
        } // if

        else
        {
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.write("无权访问");
        }
    }
}
