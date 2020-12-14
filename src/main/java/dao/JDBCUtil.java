/************************
     Author: 丁宇翔
     Date: 2020.12.2
************************/
package dao;

import vo.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil
{
    private static String DB_URL = "jdbc:mysql://localhost/ocs?useSSL=FALSE&serverTimezone=Asia/Shanghai";
    private static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static String DB_USER = "root";
    private static String DB_PWD = "qwe[]123";
    private static Connection connection = null;

    /* 连接数据库 */
    public static Connection getConnection()
    {
        try
        {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("ClassNotFoundException");
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            System.out.println("连接数据库异常");
            e.printStackTrace();
        }
        return connection;
    }

    /* 关闭连接 */
    public static void closeConnection(Connection connection)
    {
        if (connection != null)
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static <T>List<T> getListBySql(String sql, String... args)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            /* 如果有查询的参数 */
            if (args.length > 1)
            {
                for (int i = 1; i <= (args.length - 1)/2; i++)
                {
                    String type = args[2 * i - 2];
                    String value = args[2 * i - 1];

                    /* 查询类型为String */
                    if (type.equals("String"))
                    {
                        preparedStatement.setString(i, value);
                    }
                    /* 查询类型为Int */
                    else if (type.equals("int"))
                    {
                        preparedStatement.setInt(i, Integer.parseInt(value));
                    }
                    /* 查询类型为Date */
                    else if (type.equals("Date"))
                    {
                        preparedStatement.setDate(i, Date.valueOf(value));
                    }
                }
                resultSet = preparedStatement.executeQuery();
            }
            /* 如果没有查询参数 */
            else
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }

            if (args[args.length - 1].equals("User"))
            {
                List list = new ArrayList<User>();

                while (resultSet.next())
                {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setBirthday(resultSet.getDate("birthday"));
                    user.setSex(resultSet.getString("sex"));
                    user.setImage(resultSet.getString("image"));
                    user.setMessage(resultSet.getString("message"));
                    user.setRequest(resultSet.getString("request"));
                    list.add(user);
                }
                return (List<T>) list;
            }
            else if (args[args.length - 1].equals("Message"))
            {
                List<Message> list = new ArrayList<Message>();

                while (resultSet.next())
                {
                    Message message = new Message();
                    message.setId(resultSet.getInt("id"));
                    message.setFrom(resultSet.getInt("from"));
                    message.setTo(resultSet.getInt("to"));
                    message.setTime(resultSet.getTimestamp("time"));
                    message.setContent(resultSet.getString("content"));
                    message.setContent(resultSet.getString("read"));
                    list.add(message);
                }
                return (List<T>) list;
            }

            else if (args[args.length - 1].equals("Relation"))
            {
                List<Relation> list = new ArrayList<Relation>();

                while (resultSet.next())
                {
                    Relation relation = new Relation();
                    relation.setId(resultSet.getInt("id"));
                    relation.setFrom(resultSet.getInt("from"));
                    relation.setTo(resultSet.getInt("to"));
                    relation.setTime(resultSet.getTimestamp("time"));
                    relation.setAccepted(resultSet.getString("accepted"));
                    list.add(relation);
                }
                return (List<T>) list;
            }
            else if (args[args.length - 1].equals("Impression"))
            {
                List<Impression> list = new ArrayList<Impression>();

                while (resultSet.next())
                {
                    Impression impression = new Impression();
                    impression.setId(resultSet.getInt("id"));
                    impression.setFrom(resultSet.getInt("from"));
                    impression.setTo(resultSet.getInt("to"));
                    impression.setTime(resultSet.getDate("time"));
                    impression.setContent(resultSet.getString("content"));
                    list.add(impression);
                }
                return (List<T>) list;
            }// else if

        }// try
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCUtil.closeConnection(connection);
        }
        return null;
    }
}
