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

    public List<Message> getMessageByContent(String content);
}
