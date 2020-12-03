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
        String sql = "select * from message";
        return JDBCUtil.getListBySql(sql, null, null);
    }

    /* 根据发送人查找消息 */
    @Override
    public List<Message> getMessageByFrom(int from)
    {
        String sql = "select * from message where from = ?";
        return JDBCUtil.getListBySql(sql, (new Integer(from)).toString(), "int");
    }

    /* 根据接收人查找消息 */
    @Override
    public List<Message> getMessageByTo(int to)
    {
        String sql = "select * from message where to = ?";
        return JDBCUtil.getListBySql(sql, (new Integer(to)).toString(), "int");
    }

    /* 根据发送时间查找消息 */
    @Override
    public List<Message> getMessageByTime(Timestamp time)
    {
        String sql = "select * from message where time = ?";
        return JDBCUtil.getListBySql(sql, time.toString(), "Timestamp");
    }

    /* 根据内容查找消息 */
    @Override
    public List<Message> getMessageByContent(String content)
    {
        String sql = "select * from message where from = ?";
        return JDBCUtil.getListBySql(sql, content, "String");
    }

    /* 根据发送人和接受人查找消息 */
    @Override
    public List<Message> getMessageByFromTO(int from, int to)
    {
        //TODO
        return null;
    }

    /* 根据发送人和接受人查找消息 */
    @Override
    public List<Message> getMessageByFromTOContent(int from, int to, String content)
    {
        //TODO
        return null;
    }
    // TODO: 修改JDBCUtil.getListBySql()，使其能接受变长参数
    //       添加查找两人间互相发送的消息（按时间排序）；
}
