package dao;

import vo.Code;

import java.util.List;

public interface CodeDao
{
    void add(Code code);

    void delete(Code code);

    List<Code> findAll();
}
