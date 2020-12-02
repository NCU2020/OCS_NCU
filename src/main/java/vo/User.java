package vo;

import java.sql.Date;

public class User
{
    private int id;
    private String name;
    private String password;
    private Date birthday;
    private String sex;
    private String image;
    private boolean message;
    private boolean request;

    public int getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthday()
    {
        return this.birthday;
    }

    public String getSex()
    {
        return this.sex;
    }

    public String getImage()
    {
        return this.image;
    }

    public boolean isMessage()
    {
        return this.message;
    }

    public boolean isRequest()
    {
        return this.request;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public void setSex(String sex)
    {
        this.sex = sex;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public void setMessage(boolean message)
    {
        this.message = message;
    }

    public void setRequest(boolean request)
    {
        this.request = request;
    }
}
