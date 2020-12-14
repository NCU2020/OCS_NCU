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
            case "getFriends":
                String User = request.getParameter("user");
                if (User != null)
                {
                    int user = Integer.parseInt(User);
                    relations = relationService.getFriends(user);
                }
            case "findAll":
                relations = relationService.findAll();
                break;

            /* 查找发送的好友请求 */
            case "getRelationByFrom":
                String From = request.getParameter("from");
                if (From != null)
                {
                    int from = Integer.parseInt(From);
                    relations = relationService.getRelationByFrom(from);
                }
                break;

            /* 查找收到好友请求 */
            case "getRelationByTo":
                String To = request.getParameter("to");
                if (To != null)
                {
                    int to = Integer.parseInt(To);
                    relations = relationService.getRelationByTo(to);
                }
                break;

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
