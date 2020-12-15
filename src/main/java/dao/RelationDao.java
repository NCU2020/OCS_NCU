package dao;

import vo.Relation;

import java.util.List;

public interface RelationDao
{
    public void add(Relation relation);

    public void delete(Relation relation);

    public void setAccepted(Relation relation);

    public List<Relation> findAll();

    /* 查找用户发送的好友请求 */
    public List<Relation> getRelationByFrom(int from);

    /* 查找用户收到的好友请求 */
    public List<Relation> getRelationByTo(int to);

    /* 查找好友 */
    public List<Relation> getFriends(int user);

    /* 根据接受状态查找 */
    public List<Relation> getRelationByAccepted(int user, String accepted, String userType);
}
