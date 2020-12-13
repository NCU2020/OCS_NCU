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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ImpressionController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ImpressionService impressionService = new ImpressionService();
        String method = request.getParameter("method");
        List<Impression> impressions = null;

        if (method.equals("findAll"))
        {
            impressions = impressionService.findAll();
        }
        /* 查找发送的好友印象 */
        else if (method.equals("getImpressionByFrom"))
        {
            String From = request.getParameter("from");
            if (From != null)
            {
                int from = Integer.parseInt(From);
                impressions = impressionService.getImpressionByFrom(from);
            }
        }
        /*  */
        else if (method.equals("getImpressionByTo"))
        {
            String To = request.getParameter("to");
            if (To != null)
            {
                int to = Integer.parseInt(To);
                impressions = impressionService.getImpressionByTo(to);
            }
        }

        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(impressions);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
    }
}
