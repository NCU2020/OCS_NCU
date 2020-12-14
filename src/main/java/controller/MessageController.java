/************************
     Author: 丁宇翔
     Date: 2020.12.13
 ************************/
package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import service.MessageService;
import vo.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MessageController extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MessageService messageService = new MessageService();
        String method;
        List<Message> messages = null;

        method = request.getParameter("method");

        switch (method)
        {
            /* 查找两人之间互相发送的消息 */
            case "getMessageBetweenTwo":
                String User1 = request.getParameter("user1");
                String User2 = request.getParameter("user2");

                if (User1 != null && User2 != null)
                {
                    int user1 = Integer.parseInt(User1);
                    int user2 = Integer.parseInt(User2);

                    messages = messageService.getMessageBetweenTwo(user1, user2);
                }
                break;

            /* 查找发送的消息 */
            case "getMessageByFrom":
                String From = request.getParameter("from");

                if (From != null)
                {
                    int from = Integer.parseInt(From);

                    messages = messageService.getMessageByFrom(from);
                }
                break;

            /* 查找收到的消息 */
            case "getMessageByTo":
                String To = request.getParameter("to");

                if (To != null)
                {
                    int to = Integer.parseInt(To);

                    messages = messageService.getMessageByTo(to);
                }
                break;
            /* 查找所有消息 */
            case "findAll":
                messages = messageService.findAll();
                break;

            default:
                break;
        }

        response.setCharacterEncoding("UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(messages);
        PrintWriter out = response.getWriter();
        out.write(jsonStr);
    }
}