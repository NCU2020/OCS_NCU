package vo;

import java.sql.Timestamp;

public class Relation
{
    private int id;

    /* 请求发送人 */
    private int from;

    /* 请求接收人 */
    private int to;

    /* 请求接收时间 */
    private Timestamp time;

    /* 是否接受 */
    private String accepted;

    public void setId(int id)
    {
        this.id = id;
    }

    public void setFrom(int from)
    {
        this.from = from;
    }

    public void setTo(int to)
    {
        this.to = to;
    }

    public void setTime(Timestamp time)
    {
        this.time = time;
    }

    public void setAccepted(String accepted)
    {
        this.accepted = accepted;
    }

    public int getId()
    {
        return this.id;
    }

    public int getFrom()
    {
        return this.from;
    }

    public int getTo()
    {
        return this.to;
    }

    public Timestamp getTime()
    {
        return this.time;
    }

    public String getAccepted()
    {
        return this.accepted;
    }
}
