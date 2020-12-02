package vo;

import java.sql.Date;

public class Impression
{
    private int id;
    private int from;
    private int to;
    private Date time;
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

    public void setTime(Date time)
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

    public Date getTime()
    {
        return this.time;
    }

    public String getContent()
    {
        return this.content;
    }
}
