package dao;

import vo.Impression;

import java.util.List;

public interface ImpressionDao
{
    public void add(Impression impression);

    public void delete(Impression impression);

    public List<Impression> findAll();

    /* 查找用户发送的好友印象 */
    public List<Impression> getImpressionByFrom(int from);

    /* 查找用户收到的好友印象 */
    public List<Impression> getImpressionByTo(int to);

}
