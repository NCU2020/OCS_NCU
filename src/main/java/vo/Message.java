package vo;

import java.sql.Timestamp;

public class Message
{
    private int id;

    /* 消息发送人 */
    private int from;

    /* 消息接收人 */
    private int to;

    /* 消息发送时间 */
    private Timestamp time;

    /* 消息内容 */
    private String content;


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

    public void setContent(String content)
    {
        this.content = content;
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

    public String getContent()
    {
        return this.content;
    }
}
