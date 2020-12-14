/************************
     Author: 丁宇翔
     Date: 2020.12.3
 ************************/
package dao.impl;

import dao.ImpressionDao;
import dao.JDBCUtil;
import vo.Impression;

import java.util.List;

public class ImpressionDaoImpl implements ImpressionDao
{
    @Override
    public void add(Impression impression)
    {
        String sql = "INSERT INTO `impression` values(?, ?, ?, ?, ?)";
        JDBCUtil.executeSql(sql, "int", String.valueOf(impression.getId()), "int", String.valueOf(impression.getFrom()), "int", String.valueOf(impression.getTo()), "Date", impression.getTime().toString(), "String", impression.getContent());
    }

    @Override
    public void delete(Impression impression)
    {
        String sql = "DELETE FROM `impression` WHERE `id`= ?";
        JDBCUtil.executeSql(sql, "int", String.valueOf(impression.getId()));
    }

    @Override
    public List<Impression> findAll()
    {
        String sql = "select * from `impression`";
        return JDBCUtil.getListBySql(sql, "Impression");
    }

    /* 根据发送人查找 */
    @Override
    public List<Impression> getImpressionByFrom(int from)
    {
        String sql = "select * from `impression` where `from` = ?";
        return JDBCUtil.getListBySql(sql, "int", Integer.toString(from), "Impression");
    }

    /* 根据接收人查找 */
    @Override
    public List<Impression> getImpressionByTo(int to)
    {
        String sql = "SELECT * FROM `impression` WHERE `to` = ?";
        return JDBCUtil.getListBySql(sql, "int", Integer.toString(to), "Impression");
    }
}
