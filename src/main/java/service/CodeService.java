package service;

import dao.impl.CodeDaoImpl;
import vo.Code;

import java.util.List;

public class CodeService
{
    private CodeDaoImpl codeDao = new CodeDaoImpl();

    public void delete(Code code)
    {
        codeDao.delete(code);
    }

    public void add(Code code)
    {
        codeDao.add(code);
    }

    public List<Code> findAll()
    {
        return codeDao.findAll();
    }
}
