/************************
     Author: 丁宇翔
     Date: 2020.12.2
************************/
package dao;

import vo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUtil
{
    private static String DB_URL = "jdbc:sqlite:a.db";
    private static String DB_DRIVER = "org.sqlite.JDBC";
    private static Connection connection = null;

    /* 连接数据库 */
    public static Connection getConnection()
    {
        try
        {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
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
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try
        {
            connection = JDBCUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            /* 如果有查询的参数 */
            if (args.length > 0)
            {
                for (int i = 1; i < args.length; i++)
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
                        preparedStatement.setInt(i, Integer.getInteger(value).intValue());
                    }
                    /* 查询类型为Date */
                    else if (type.equals("Date"))
                    {
                        preparedStatement.setDate(i, Date.valueOf(value));
                    }
                }
                resultSet = preparedStatement.executeQuery(sql);
            }
            /* 如果没有查询参数 */
            else
            {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);
            }

            while (resultSet.next())
            {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setSex(resultSet.getString("sex"));
                user.setImage(resultSet.getString("image"));
                user.setMessage(resultSet.getBoolean("message"));
                user.setRequest(resultSet.getBoolean("request"));
                list.add(user);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            JDBCUtil.closeConnection(connection);
        }
        return (List<T>) list;
    }
}
