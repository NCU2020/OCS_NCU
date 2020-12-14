/************************
     Author: 丁宇翔
     Date: 2020.12.7
 ************************/
package service;

import dao.impl.ImpressionDaoImpl;
import vo.Impression;

import java.util.List;

public class ImpressionService
{
    private ImpressionDaoImpl impressionDao = new ImpressionDaoImpl();

    public void add(Impression impression)
    {
        impressionDao.add(impression);
    }

    public void delete(Impression impression)
    {
        impressionDao.delete(impression);
    }

    public List<Impression> findAll()
    {
        return impressionDao.findAll();
    }

    /* 查找发送的好友印象 */
    public List<Impression> getImpressionByFrom(int from)
    {
        return impressionDao.getImpressionByFrom(from);
    }

    /* 查找收到的好友印象 */
    public List<Impression> getImpressionByTo(int to)
    {
        return impressionDao.getImpressionByTo(to);
    }
}
