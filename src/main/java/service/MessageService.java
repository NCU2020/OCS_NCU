package service;

import dao.impl.MessageDaoImpl;
import vo.Message;

import java.util.List;

public class MessageService
{
    private MessageDaoImpl message = new MessageDaoImpl();

    public List<Message> findAll()
    {
        return message.findAll();
    }

    /* 查找发送的消息 */
    public List<Message> getMessageByFrom(int from)
    {
        return message.getMessageByFrom(from);
    }

    /* 查找收到的消息 */
    public List<Message> getMessageByTo(int to)
    {
        return message.getMessageByTo(to);
    }

    /* 查找两人之间互相发送的消息并根据时间排序 */
    public List<Message> getMessageBetweenTwo(int user1, int user2)
    {
        return message.getMessageBetweenTwo(user1, user2);
    }
}
