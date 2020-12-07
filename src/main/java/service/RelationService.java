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
    private RelationDaoImpl relation = new RelationDaoImpl();

    public List<Relation> findAll()
    {
        return relation.findAll();
    }

    /* 查找发送的好友请求 */
    public List<Relation> getRelationByFrom(int from)
    {
        return relation.getRelationByFrom(from);
    }

    /* 查找收到的好友请求 */
    public List<Relation> getRelationByTo(int to)
    {
        return relation.getRelationByTo(to);
    }
}
