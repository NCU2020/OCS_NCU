/************************
     Author: 丁宇翔
     Date: 2020.12.3
 ************************/
package dao.impl;

import dao.JDBCUtil;
import dao.MessageDao;
import vo.Message;

import java.sql.Timestamp;
import java.util.List;

public class MessageDaoImpl implements MessageDao
{
    @Override
    public void add(Message message)
    {

    }

    @Override
    public void delete(Message message)
    {

    }

    @Override
    public List<Message> findAll()
    {
        String sql = "select * from `message`";
        return JDBCUtil.getListBySql(sql, "Message");
    }

    /* 根据发送人查找消息 */
    @Override
    public List<Message> getMessageByFrom(int from)
    {
        String sql = "select * from `message` where `from` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(from), "Message");
    }

    /* 根据接收人查找消息 */
    @Override
    public List<Message> getMessageByTo(int to)
    {
        String sql = "select * from `message` where `to` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(to), "Message");
    }

    /* 根据发送时间查找消息 */
    @Override
    public List<Message> getMessageByTime(Timestamp time)
    {
        String sql = "select * from `message` where `time` = ?";
        return JDBCUtil.getListBySql(sql, "Timestamp", time.toString(), "Message");
    }

    /* 根据内容查找消息 */
    @Override
    public List<Message> getMessageByContent(String content)
    {
        String sql = "select * from `message` where `content` = ?";
        return JDBCUtil.getListBySql(sql, "String", content, "Message");
    }

    /* 根据发送人和接受人查找消息 */
    @Override
    public List<Message> getMessageByFromTO(int from, int to)
    {
        String sql = "select * from `message` where `from` = ? and `to` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(from), "int", String.valueOf(to), "Message");
    }

    /* 根据发送人和接受人查找消息 */
    @Override
    public List<Message> getMessageByFromTOContent(int from, int to, String content)
    {
        String sql = "select * from `message` where `from` = ? and `to` = ? and `content` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(from), "int", String.valueOf(to), "String", content, "Message");
    }

    @Override
    public List<Message> getMessageBetweenTwo(int user1, int user2)
    {
        String sql = "select * from `message` where (`from` = ? and `to` = ?) or (`from` = ? and `to` = ?)";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(user1), "int", String.valueOf(user2), "int", String.valueOf(user2), "int", String.valueOf(user1), "Message");
    }
}
