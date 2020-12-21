package dao.impl;

import dao.CodeDao;
import dao.JDBCUtil;
import vo.Code;

import java.util.List;

public class CodeDaoImpl implements CodeDao
{
    @Override
    public void add(Code code)
    {

    }

    @Override
    public void delete(Code code)
    {
        String sql = "DELETE FROM `code` WHERE code = ?";
        JDBCUtil.executeSql(sql, "String", code.getCode());
    }

    @Override
    public List<Code> findAll()
    {
        String sql = "SELECT * FROM `code`";
        return JDBCUtil.getListBySql(sql, "Code");
    }
}
