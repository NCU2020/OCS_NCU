/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.RelationService;
import vo.Relation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

public class RelationController extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RelationService relationService = new RelationService();
        String method;
        List<Relation> relations = null;

        method = request.getParameter("method");

        switch (method)
        {
            /* 查找一个人所有的好友 */
            case "getFriends":
            {
                String User = request.getParameter("user");
                if (User != null)
                {
                    int user = Integer.parseInt(User);
                    relations = relationService.getFriends(user);
                }
                break;
            }

            /* 查找所有 */
            case "findAll":
            {
                relations = relationService.findAll();
                break;
            }

            /* 查找发送的好友请求 */
            case "getRelationByFrom":
            {
                String From = request.getParameter("from");
                if (From != null)
                {
                    int from = Integer.parseInt(From);
                    relations = relationService.getRelationByFrom(from);
                }
                break;
            }

            /* 查找收到好友请求 */
            case "getRelationByTo":
            {
                String To = request.getParameter("to");
                if (To != null)
                {
                    int to = Integer.parseInt(To);
                    relations = relationService.getRelationByTo(to);
                }
                break;
            }

            /* 根据accepted查找 */
            case "getRelationByAccepted":
            {
                String User = request.getParameter("user");
                String accepted = request.getParameter("accepted");
                String userType = request.getParameter("userType");

                if (User!=null && accepted!=null && userType!=null)
                {
                    int user = Integer.parseInt(User);
                    relations = relationService.getRelationByAccepted(user, accepted, userType);
                }
                break;
            }

            /* 添加 */
            case "add":
            {
                String Id = request.getParameter("id");
                String From = request.getParameter("from");
                String To = request.getParameter("to");
                String Time = request.getParameter("time");
                String accepted = request.getParameter("accepted");

                if (Id!=null && From!=null && To!=null && Time!=null && accepted!=null)
                {
                    int id = Integer.parseInt(Id);
                    int from = Integer.parseInt(From);
                    int to = Integer.parseInt(To);
                    Timestamp time = Timestamp.valueOf(Time);
                    Relation relation = new Relation();

                    relation.setId(id);
                    relation.setFrom(from);
                    relation.setTo(to);
                    relation.setTime(time);
                    relation.setAccepted(accepted);

                    relationService.add(relation);
                }
                break;
            }

            /* 删除 */
            case "delete":
            {
                String Id = request.getParameter("id");

                if (Id != null)
                {
                    int id = Integer.parseInt(Id);
                    Relation relation = new Relation();

                    relation.setId(id);

                    relationService.delete(relation);
                }
                break;
            }

            case "setAccepted":
            {
                String Id = request.getParameter("id");
                String accepted = request.getParameter("accepted");

                if (Id!=null && accepted!=null)
                {
                    int id = Integer.parseInt(Id);
                    Relation relation = new Relation();

                    relation.setId(id);
                    relation.setAccepted(accepted);

                    relationService.setAccepted(relation);
                }
                break;
            }
            default:
                break;
        }

        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(relations);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
    }
}
