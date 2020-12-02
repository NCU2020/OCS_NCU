package dao;

import vo.Message;

import java.sql.Timestamp;
import java.util.List;

public interface MessageDao
{
    public void add(Message message);

    public void delete(Message message);

    public List<Message> findAll();

    public List<Message> getMessageByFrom(int from);

    public List<Message> getMessageByTo(int to);

    public  List<Message> getMessageByTime(Timestamp time);

    /* 根据消息内容查找 */
    public List<Message> getMessageByContent(String content);

    /* 根据消息发送人、接收人查找 */
    public List<Message> getMessageByFromTO(int from, int to);

    /* 根据消息发送人、接收人和消息内容查找 */
    public List<Message> getMessageByFromTOContent(int from, int to, String content);
}
