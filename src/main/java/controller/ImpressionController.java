/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.ImpressionService;
import vo.Impression;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

public class ImpressionController extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();

        /* 判断是否登录 */
        if (session.getAttribute("user") != null)
        {
            ImpressionService impressionService = new ImpressionService();
            String method = request.getParameter("method");
            List<Impression> impressions = null;

            switch (method)
            {
                case "findAll":
                {
                    impressions = impressionService.findAll();
                    break;
                }

                /* 查找发送的好友印象 */
                case "getImpressionByFrom":
                {
                    String From = request.getParameter("from");
                    if (From != null)
                    {
                        int from = Integer.parseInt(From);
                        impressions = impressionService.getImpressionByFrom(from);
                    }
                    break;
                }

                /* 查找收到的好友印象 */
                case "getImpressionByTo":
                {
                    String To = request.getParameter("to");
                    if (To != null)
                    {
                        int to = Integer.parseInt(To);
                        impressions = impressionService.getImpressionByTo(to);
                    }
                    break;
                }

                /* 插入记录 */
                case "add":
                {
                    String From = request.getParameter("from");
                    String To = request.getParameter("to");
                    String Time = request.getParameter("time");
                    String content = request.getParameter("content");
                    if (From != null && To != null && Time != null && content != null)
                    {
                        int id = 0;
                        int from = Integer.parseInt(From);
                        int to = Integer.parseInt(To);
                        Date time = Date.valueOf(Time);
                        Impression impression = new Impression();
                        impression.setId(id);
                        impression.setFrom(from);
                        impression.setTo(to);
                        impression.setTime(time);
                        impression.setContent(content);
                        impressionService.add(impression);
                    }
                    break;
                }

                /* 删除记录 */
                case "delete":
                {
                    String Id = request.getParameter("id");

                    if (Id != null)
                    {
                        int id = Integer.parseInt(Id);
                        Impression impression = new Impression();
                        impression.setId(id);
                        impressionService.delete(impression);
                    }
                    break;
                }
                default:
                    break;
            } // switch

            response.setCharacterEncoding("UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(impressions);
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
