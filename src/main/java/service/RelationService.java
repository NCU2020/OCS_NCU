/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package service;

import dao.impl.RelationDaoImpl;
import vo.Relation;

import java.util.List;

public class RelationService
{
    private RelationDaoImpl relationDao = new RelationDaoImpl();

    public void add(Relation relation)
    {
        relationDao.add(relation);
    }

    public void delete(Relation relation)
    {
        relationDao.delete(relation);
    }

    public List<Relation> findAll()
    {
        return relationDao.findAll();
    }

    /* 查找发送的好友请求 */
    public List<Relation> getRelationByFrom(int from)
    {
        return relationDao.getRelationByFrom(from);
    }

    /* 查找收到的好友请求 */
    public List<Relation> getRelationByTo(int to)
    {
        return relationDao.getRelationByTo(to);
    }

    /* 查找已添加的好友 */
    public List<Relation> getFriends(int user)
    {
        return relationDao.getFriends(user);
    }
}
