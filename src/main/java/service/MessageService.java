/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package service;

import dao.impl.MessageDaoImpl;
import vo.Message;

import java.util.List;

public class MessageService
{
    private MessageDaoImpl messageDao = new MessageDaoImpl();

    public void add(Message message)
    {
        messageDao.add(message);
    }

    public void delete(Message message)
    {
        messageDao.delete(message);
    }

    public void setRead(Message message)
    {
        messageDao.setRead(message);
    }

    public List<Message> findAll()
    {
        return messageDao.findAll();
    }

    /* 查找发送的消息 */
    public List<Message> getMessageByFrom(int from)
    {
        return messageDao.getMessageByFrom(from);
    }

    /* 查找收到的消息 */
    public List<Message> getMessageByTo(int to)
    {
        return messageDao.getMessageByTo(to);
    }

    /* 查找两人之间互相发送的消息并根据时间排序 */
    public List<Message> getMessageBetweenTwo(int user1, int user2)
    {
        return messageDao.getMessageBetweenTwo(user1, user2);
    }
}
