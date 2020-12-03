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
        String sql = "select * from relation";
        return JDBCUtil.getListBySql(sql, null, null);
    }

    @Override
    public List<Relation> getRelationByFrom(int from)
    {
        String sql = "select * from relation where from = ?";
        return JDBCUtil.getListBySql(sql, (new Integer(from).toString()), "int");
    }

    @Override
    public List<Relation> getRelationByTo(int to)
    {
        String sql = "select * from relation where to = ?";
        return JDBCUtil.getListBySql(sql, (new Integer(to).toString()), "int");
    }
}