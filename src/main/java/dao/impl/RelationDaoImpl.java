/************************
     Author: 丁宇翔
     Date: 2020.12.2
 ************************/
package dao.impl;

import dao.JDBCUtil;
import dao.RelationDao;
import vo.Relation;

import java.util.List;

public class RelationDaoImpl implements RelationDao
{
    @Override
    public void add(Relation relation)
    {

    }

    @Override
    public void delete(Relation relation)
    {

    }

    @Override
    public List<Relation> findAll()
    {
        String sql = "select * from `relation`";
        return JDBCUtil.getListBySql(sql, "Relation");
    }

    @Override
    public List<Relation> getRelationByFrom(int from)
    {
        String sql = "select * from `relation` where `from` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(from), "Relation");
    }

    @Override
    public List<Relation> getRelationByTo(int to)
    {
        String sql = "select * from `relation` where `to` = ?";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(to), "Relation");
    }

    @Override
    public List<Relation> getFriends(int user)
    {
        String sql = "select * from `relation` where (`from` = ? or `to` = ?) and `accepted` = ACCEPTED";
        return JDBCUtil.getListBySql(sql, "int", String.valueOf(user), "int", String.valueOf(user), "Relation");
    }
}
