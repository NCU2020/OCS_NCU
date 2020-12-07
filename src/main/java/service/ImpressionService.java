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
    private ImpressionDaoImpl impression = new ImpressionDaoImpl();

    public List<Impression> findAll()
    {
        return impression.findAll();
    }

    /* 查找发送的好友印象 */
    public List<Impression> getImpressionByFrom(int from)
    {
        return impression.getImpressionByFrom(from);
    }

    /* 查找收到的好友印象 */
    public List<Impression> getImpressionByTo(int to)
    {
        return impression.getImpressionByTo(to);
    }
}
