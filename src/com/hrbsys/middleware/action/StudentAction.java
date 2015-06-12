package com.hrbsys.middleware.action;

import com.hrbsys.middleware.bean.BaseBean;
import com.hrbsys.middleware.constant.BaseConstant;
import com.hrbsys.middleware.dao.BaseDao;
import java.util.List;


/**
 *
 * @author Li
 */
public class StudentAction
{
    private BaseBean baseBean;
    private BaseDao baseDao;
    private String name;

    public String queryStudent()
    {
        String hql = "select new Student(number) from Student where name = ?";
        List list = baseDao.query(hql, new Object[]{name});
        baseBean=new BaseBean();
        baseBean.setStatus(BaseConstant.successStatus);
        baseBean.setMessage(BaseConstant.successMessage);
        baseBean.setData(list);
        return BaseConstant.reuslt;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseBean getBaseBean() {
        return baseBean;
    }
}