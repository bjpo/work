package com.hrbsys.middleware.action;

import com.hrbsys.middleware.bean.Result;
import com.hrbsys.middleware.constant.BaseConstant;
import com.hrbsys.middleware.dao.BaseDao;
import java.util.List;

/**
 *
 * @author Li
 */
public class TermAction
{
    private Result result;
    private BaseDao baseDao;

    public String query()
    {
        String hql="select new XUEQI(XQMC,KSRQ,JSRQ) from XUEQI";
        List list=baseDao.query(hql);
        result=new Result(BaseConstant.successStatus, BaseConstant.successMessage, list);
        return BaseConstant.reuslt;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Result getResult() {
        return result;
    }
}