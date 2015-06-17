package com.hrbsys.middleware.action;

import com.hrbsys.middleware.bean.Result;
import com.hrbsys.middleware.constant.BaseConstant;
import com.hrbsys.middleware.dao.BaseDao;
import java.util.List;

/**
 *
 * @author Li
 */
public class StudentAction
{
    private Result result;
    private BaseDao baseDao;
    private String name;
    private String squadName;

    public String query()
    {
        if (name != null)
        {
            String hql = "select new Student(number) from Student where name = ?";
            List list=baseDao.query(hql, new Object[]{name});
            result=new Result(BaseConstant.successStatus, BaseConstant.successMessage, list);
        }
        if (squadName != null)
        {
            String hql = "select new Student(number) from Student where squadName = ?";
            List list=baseDao.query(hql, new Object[]{squadName});
            result=new Result(BaseConstant.successStatus, BaseConstant.successMessage, list);
        }
        return BaseConstant.reuslt;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public Result getResult() {
        return result;
    }
}