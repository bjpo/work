package com.hrbsys.kqxxxscq.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.kqxxxscq.service.KQXX_XSCQService;
import com.hrbsys.tools.BaseChangeTool;

public class KQXX_XSCQServiceImpl implements KQXX_XSCQService {

    private BaseDao baseDao;

    public BaseDao getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    //add
    @Override
    public boolean addKQXX_XSCQ(KQXX_XSCQ y) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//       y.setDJRQ(df.format(new Date()).toString());
//       y.setXGRQ(df.format(new Date()).toString());
        return baseDao.save(y);
    }

    //delete
    @Override
    public boolean delKQXX_XSCQ(String y) {
        String[] ids = y.split(",");
        for (String id : ids) {
            KQXX_XSCQ yhz = findKQXX_XSCQ(id);
            if (!baseDao.delete(yhz)) {
                return false;
            }
        }
        return true;
    }

    //update
    @Override
    public boolean updateKQXX_XSCQ(KQXX_XSCQ t) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        KQXX_XSCQ tmpt = baseDao.findAll("from KQXX_XSCQ where KQXX_XSCQ_ID='" + t.getKQXX_XSCQ_ID() + "'", KQXX_XSCQ.class).get(0);
        tmpt.setKCMC(t.getKCMC());
        tmpt.setNJ_ID(t.getNJ_ID());
        tmpt.setKCB_ID(t.getKCB_ID());
        tmpt.setLS_ID(t.getLS_ID());
        tmpt.setZHOU(t.getZHOU());
        tmpt.setKESHI_ID(t.getKESHI_ID());
        tmpt.setXSXM(t.getXSXM());
        tmpt.setMS(t.getMS());
        tmpt.setXSXH(t.getXSXH());
        tmpt.setXY_ID(t.getXY_ID());
        tmpt.setSKSJ(t.getSKSJ());
        tmpt.setBZ(t.getBZ());
        tmpt.setLSXM(t.getLSXM());
        tmpt.setXS_ID(t.getXS_ID());
        tmpt.setXINGQI(t.getXINGQI());
        tmpt.setBJ_ID(t.getBJ_ID());
        tmpt.setKCLB(t.getKCLB());
        tmpt.setJSMC(t.getJSMC());
        tmpt.setZY_ID(t.getZY_ID());
        tmpt.setKCB_FKS_ID(t.getKCB_FKS_ID());
        tmpt.setCQQK(t.getCQQK());
        tmpt.setKSMC(t.getKSMC());
        tmpt.setLSGH(t.getLSGH());
        tmpt.setJS_ID(t.getJS_ID());
//		tmpt.setXGRQ(df.format(new Date()).toString());
        return baseDao.update(tmpt);
    }

    //findById
    @Override
    public KQXX_XSCQ findKQXX_XSCQ(String t_id) {
        return baseDao.findAll("from KQXX_XSCQ where KQXX_XSCQ_ID='" + t_id + "'", KQXX_XSCQ.class).get(0);
    }

    //findByPage
    @Override
    public List<KQXX_XSCQ> findKQXX_XSCQByPageApp(int start, int number, HashMap<String, String> params, String order, String sort) {
        String hql = "from KQXX_XSCQ where 1=1 ";
        String param = "";
        ArrayList<String> params2 = new ArrayList<String>();
        if (null != params.get("KCMC") && !"".equals(params.get("KCMC"))) {
            param = "and KCMC like '%" + params.get("KCMC").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("NJ_ID") && !"".equals(params.get("NJ_ID"))) {
            param = "and NJ_ID like '%" + params.get("NJ_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("KCB_ID") && !"".equals(params.get("KCB_ID"))) {
            param = "and KCB_ID like '%" + params.get("KCB_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("LS_ID") && !"".equals(params.get("LS_ID"))) {
            param = "and LS_ID like '%" + params.get("LS_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("ZHOU") && !"".equals(params.get("ZHOU"))) {
            param = "and ZHOU like '%" + params.get("ZHOU").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("KESHI_ID") && !"".equals(params.get("KESHI_ID"))) {
            param = "and KESHI_ID like '%" + params.get("KESHI_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("XSXM") && !"".equals(params.get("XSXM"))) {
            param = "and XSXM like '%" + params.get("XSXM").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("MS") && !"".equals(params.get("MS"))) {
            param = "and MS like '%" + params.get("MS").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("XSXH") && !"".equals(params.get("XSXH"))) {
            param = "and XSXH like '%" + params.get("XSXH").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("XY_ID") && !"".equals(params.get("XY_ID"))) {
            param = "and XY_ID like '%" + params.get("XY_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("BZ") && !"".equals(params.get("BZ"))) {
            param = "and BZ like '%" + params.get("BZ").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("LSXM") && !"".equals(params.get("LSXM"))) {
            param = "and LSXM like '%" + params.get("LSXM").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("XS_ID") && !"".equals(params.get("XS_ID"))) {
            param = "and XS_ID like '%" + params.get("XS_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("XINGQI") && !"".equals(params.get("XINGQI"))) {
            param = "and XINGQI like '%" + params.get("XINGQI").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("BJ_ID") && !"".equals(params.get("BJ_ID"))) {
            param = "and BJ_ID like '%" + params.get("BJ_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("KCLB") && !"".equals(params.get("KCLB"))) {
            param = "and KCLB like '%" + params.get("KCLB").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("JSMC") && !"".equals(params.get("JSMC"))) {
            param = "and JSMC like '%" + params.get("JSMC").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("ZY_ID") && !"".equals(params.get("ZY_ID"))) {
            param = "and ZY_ID like '%" + params.get("ZY_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("KCB_FKS_ID") && !"".equals(params.get("KCB_FKS_ID"))) {
            param = "and KCB_FKS_ID like '%" + params.get("KCB_FKS_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("CQQK") && !"".equals(params.get("CQQK"))) {
            param = "and CQQK like '%" + params.get("CQQK").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("KSMC") && !"".equals(params.get("KSMC"))) {
            param = "and KSMC like '%" + params.get("KSMC").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("LSGH") && !"".equals(params.get("LSGH"))) {
            param = "and LSGH like '%" + params.get("LSGH").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        if (null != params.get("JS_ID") && !"".equals(params.get("JS_ID"))) {
            param = "and JS_ID like '%" + params.get("JS_ID").toString()
                    + "%'";
            params2.add(param);
            param = "";
        }
        List<KQXX_XSCQ> list = baseDao.findByPage(hql, KQXX_XSCQ.class, start,
                number, params2, order, sort);
        return list;
    }

    //getCount
    @Override
    public int getCountKQXX_XSCQ(HashMap<String, String> params) {
        String hql = "SELECT COUNT(*) from KQXX_XSCQ where 1=1 ";
        if (null != params.get("KCMC")
                && !"".equals(params.get("KCMC"))) {
            hql += "and KCMC like '%" + params.get("KCMC").toString()
                    + "%'";
        }
        if (null != params.get("NJ_ID")
                && !"".equals(params.get("NJ_ID"))) {
            hql += "and NJ_ID like '%" + params.get("NJ_ID").toString()
                    + "%'";
        }
        if (null != params.get("KCB_ID")
                && !"".equals(params.get("KCB_ID"))) {
            hql += "and KCB_ID like '%" + params.get("KCB_ID").toString()
                    + "%'";
        }
        if (null != params.get("LS_ID")
                && !"".equals(params.get("LS_ID"))) {
            hql += "and LS_ID like '%" + params.get("LS_ID").toString()
                    + "%'";
        }
        if (null != params.get("ZHOU")
                && !"".equals(params.get("ZHOU"))) {
            hql += "and ZHOU like '%" + params.get("ZHOU").toString()
                    + "%'";
        }
        if (null != params.get("KESHI_ID")
                && !"".equals(params.get("KESHI_ID"))) {
            hql += "and KESHI_ID like '%" + params.get("KESHI_ID").toString()
                    + "%'";
        }
        if (null != params.get("XSXM")
                && !"".equals(params.get("XSXM"))) {
            hql += "and XSXM like '%" + params.get("XSXM").toString()
                    + "%'";
        }
        if (null != params.get("MS")
                && !"".equals(params.get("MS"))) {
            hql += "and MS like '%" + params.get("MS").toString()
                    + "%'";
        }
        if (null != params.get("XSXH")
                && !"".equals(params.get("XSXH"))) {
            hql += "and XSXH like '%" + params.get("XSXH").toString()
                    + "%'";
        }
        if (null != params.get("XY_ID")
                && !"".equals(params.get("XY_ID"))) {
            hql += "and XY_ID like '%" + params.get("XY_ID").toString()
                    + "%'";
        }
        if (null != params.get("BZ")
                && !"".equals(params.get("BZ"))) {
            hql += "and BZ like '%" + params.get("BZ").toString()
                    + "%'";
        }
        if (null != params.get("LSXM")
                && !"".equals(params.get("LSXM"))) {
            hql += "and LSXM like '%" + params.get("LSXM").toString()
                    + "%'";
        }
        if (null != params.get("XS_ID")
                && !"".equals(params.get("XS_ID"))) {
            hql += "and XS_ID like '%" + params.get("XS_ID").toString()
                    + "%'";
        }
        if (null != params.get("XINGQI")
                && !"".equals(params.get("XINGQI"))) {
            hql += "and XINGQI like '%" + params.get("XINGQI").toString()
                    + "%'";
        }
        if (null != params.get("BJ_ID")
                && !"".equals(params.get("BJ_ID"))) {
            hql += "and BJ_ID like '%" + params.get("BJ_ID").toString()
                    + "%'";
        }
        if (null != params.get("KCLB")
                && !"".equals(params.get("KCLB"))) {
            hql += "and KCLB like '%" + params.get("KCLB").toString()
                    + "%'";
        }
        if (null != params.get("JSMC")
                && !"".equals(params.get("JSMC"))) {
            hql += "and JSMC like '%" + params.get("JSMC").toString()
                    + "%'";
        }
        if (null != params.get("ZY_ID")
                && !"".equals(params.get("ZY_ID"))) {
            hql += "and ZY_ID like '%" + params.get("ZY_ID").toString()
                    + "%'";
        }
        if (null != params.get("KCB_FKS_ID")
                && !"".equals(params.get("KCB_FKS_ID"))) {
            hql += "and KCB_FKS_ID like '%" + params.get("KCB_FKS_ID").toString()
                    + "%'";
        }
        if (null != params.get("CQQK")
                && !"".equals(params.get("CQQK"))) {
            hql += "and CQQK like '%" + params.get("CQQK").toString()
                    + "%'";
        }
        if (null != params.get("KSMC")
                && !"".equals(params.get("KSMC"))) {
            hql += "and KSMC like '%" + params.get("KSMC").toString()
                    + "%'";
        }
        if (null != params.get("LSGH")
                && !"".equals(params.get("LSGH"))) {
            hql += "and LSGH like '%" + params.get("LSGH").toString()
                    + "%'";
        }
        if (null != params.get("JS_ID")
                && !"".equals(params.get("JS_ID"))) {
            hql += "and JS_ID like '%" + params.get("JS_ID").toString()
                    + "%'";
        }
        int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
                .get(0).toString());
        return count;
    }

    //findbypagelike
    @Override
    public List<KQXX_XSCQ> findKQXX_XSCQByPageApp(HashMap<String, String> params, String order, String sort) {
        String hql = "from KQXX_XSCQ where 1=1";
        String param = "";
        ArrayList<String> params2 = new ArrayList<String>();
        if (null != params.get("KCMC")
                && !"".equals(params.get("KCMC"))) {
            param = "and KCMC like '%"
                    + params.get("KCMC").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("NJ_ID")
                && !"".equals(params.get("NJ_ID"))) {
            param = "and NJ_ID like '%"
                    + params.get("NJ_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("KCB_ID")
                && !"".equals(params.get("KCB_ID"))) {
            param = "and KCB_ID like '%"
                    + params.get("KCB_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("LS_ID")
                && !"".equals(params.get("LS_ID"))) {
            param = "and LS_ID like '%"
                    + params.get("LS_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("ZHOU")
                && !"".equals(params.get("ZHOU"))) {
            param = "and ZHOU like '%"
                    + params.get("ZHOU").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("KESHI_ID")
                && !"".equals(params.get("KESHI_ID"))) {
            param = "and KESHI_ID like '%"
                    + params.get("KESHI_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("XSXM")
                && !"".equals(params.get("XSXM"))) {
            param = "and XSXM like '%"
                    + params.get("XSXM").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("MS")
                && !"".equals(params.get("MS"))) {
            param = "and MS like '%"
                    + params.get("MS").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("XSXH")
                && !"".equals(params.get("XSXH"))) {
            param = "and XSXH like '%"
                    + params.get("XSXH").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("XY_ID")
                && !"".equals(params.get("XY_ID"))) {
            param = "and XY_ID like '%"
                    + params.get("XY_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("BZ")
                && !"".equals(params.get("BZ"))) {
            param = "and BZ like '%"
                    + params.get("BZ").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("LSXM")
                && !"".equals(params.get("LSXM"))) {
            param = "and LSXM like '%"
                    + params.get("LSXM").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("XS_ID")
                && !"".equals(params.get("XS_ID"))) {
            param = "and XS_ID like '%"
                    + params.get("XS_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("XINGQI")
                && !"".equals(params.get("XINGQI"))) {
            param = "and XINGQI like '%"
                    + params.get("XINGQI").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("BJ_ID")
                && !"".equals(params.get("BJ_ID"))) {
            param = "and BJ_ID like '%"
                    + params.get("BJ_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("KCLB")
                && !"".equals(params.get("KCLB"))) {
            param = "and KCLB like '%"
                    + params.get("KCLB").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("JSMC")
                && !"".equals(params.get("JSMC"))) {
            param = "and JSMC like '%"
                    + params.get("JSMC").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("ZY_ID")
                && !"".equals(params.get("ZY_ID"))) {
            param = "and ZY_ID like '%"
                    + params.get("ZY_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("KCB_FKS_ID")
                && !"".equals(params.get("KCB_FKS_ID"))) {
            param = "and KCB_FKS_ID like '%"
                    + params.get("KCB_FKS_ID").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("CQQK")
                && !"".equals(params.get("CQQK"))) {
            param = "and CQQK like '%"
                    + params.get("CQQK").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("KSMC")
                && !"".equals(params.get("KSMC"))) {
            param = "and KSMC like '%"
                    + params.get("KSMC").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("LSGH")
                && !"".equals(params.get("LSGH"))) {
            param = "and LSGH like '%"
                    + params.get("LSGH").toString() + "%'";
            params2.add(param);
        }
        if (null != params.get("JS_ID")
                && !"".equals(params.get("JS_ID"))) {
            param = "and JS_ID like '%"
                    + params.get("JS_ID").toString() + "%'";
            params2.add(param);
        }
        List<KQXX_XSCQ> list = baseDao.findAll(hql, KQXX_XSCQ.class);
        return list;
    }

//按年级统计：
    @Override
    public HashMap<String, Integer> getBJKQXX(String jsid, String jgid, String ksid, String kcbid, XUEQI xq, String xingqi, String zhou, String ksrq, String jsrq, String xy_id, String zy_id, String nj_id) {
        return getBJKQXX(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq, xy_id, zy_id, nj_id, null);
    }

//按班级统计：
    /**
     * 算法分析：
     *
     *
     */
    @Override
    public HashMap<String, Integer> getBJKQXX(String jsid, String jgid, String ksid, String kcbid, XUEQI xq, String xingqi, String zhou, String ksrq, String jsrq, String xy_id, String zy_id, String nj_id, String bj_id) {
        return getBJKQXX_XSKQ_COUNT(jsid, jgid, ksid, kcbid, xq, xingqi, zhou, ksrq, jsrq, xy_id, zy_id, nj_id, bj_id, null);
    }

    @Override
    public HashMap<String, Integer> getBJKQXX_XSKQ_COUNT(String jsid, String jgid, String ksid, String kcbid, XUEQI xq, String xingqi, String zhou, String ksrq, String jsrq, String xy_id, String zy_id, String nj_id, String bj_id, String xsid) {

        if (null == xy_id || "".equals(xy_id)) {
            System.out.println("KQXX_XSCQServiceImpl中打印::" + "学院ID为空，返回null...");
            return null;
        }
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int countYCXRS = 0;
        int countQUEXIRS = 0;
        int countZHENGCHANGRS = 0;
        int countCHIDAORS = 0;
        int countZAOTUIRS = 0;
        String hql1 = "SELECT CQQK,COUNT(CQQK) FROM KQXX_XSCQ WHERE 1=1";
        String hql2 = " GROUP BY CQQK";

        if (null != zy_id && !"".equals(zy_id)) { //增加专业ID
            hql1 += " and ZY_ID='" + zy_id + "'";
        }
        if (null != nj_id && !"".equals(nj_id)) { //增加年级ID
            hql1 += " and NJ_ID='" + nj_id + "'";
        }
        if (null != bj_id && !"".equals(bj_id)) { //增加班级ID
            hql1 += " and BJ_ID='" + bj_id + "'";
        }

        if (null != xsid && !"".equals(xsid)) { //增加学生ID
            hql1 += " and XS_ID='" + xsid + "'";
        }

        if (null != jsid && !"".equals(jsid) && !"".equals(jsid)) { //增加教室ID
            hql1 += " and JS_ID=" + jsid;
        }
        if (null != jgid && !"".equals(jgid)) {//增加老师ID
            hql1 += " and LS_ID=" + jgid;
        }
        if (null != ksid && !"".equals(ksid)) {//增加课时id
            hql1 += " and KESHI_ID=" + ksid;
        }
        if (null != kcbid && !"".equals(kcbid)) {//增加课程表ID
            hql1 += " and KCB_ID=" + kcbid;
        }
        if (null != ksrq && !"".equals(ksrq)) {//增加开始日期
            hql1 += " and SKSJ>=to_date('" + ksrq + "','yyyy-mm-dd')";
        }
        if (null != jsrq && !"".equals(jsrq)) {//增加结束日期
            hql1 += " and SKSJ<=to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
        if (null != xq && !"".equals(xq)) {
            hql1 += "and  SKSJ between to_date('" + xq.getKSRQ().trim() + "','yyyy-mm-dd') and to_date('" + xq.getJSRQ().trim() + "','yyyy-mm-dd')";
        }
        if (null != zhou && !"".equals(zhou)) {//周不为空
            //如果星期不为空，则计算星期中课时
            List<String> riqi = BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
            ////////////////////////////////////////////////////////////////////
            for (int i = 0; i < riqi.size(); i++) {
                String riqitmp = riqi.get(i);
                String tmphql = hql1;
                if (null != riqitmp && !"".equals(riqitmp)) {//增加课程表ID
                    tmphql += " and SKSJ>=to_date('" + riqitmp + "','yyyy-mm-dd') and SKSJ<=to_date('" + riqitmp + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
                }
                tmphql += hql2;
                System.out.println("KQXX_XSCQServiceImpl中打印::" + "分学院：周不为空时，拼凑的hql：：" + tmphql);
                List all = baseDao.executeSQL(tmphql);
                for (int alli = 0; alli < all.size(); alli++) {
                    Object[] tmpobj = (Object[]) all.get(alli);
                    String tmpkey = (null == tmpobj[0]) ? "" : tmpobj[0].toString();
                    String tmpvalue = (null == tmpobj[1]) ? "0" : tmpobj[1].toString();
                    Integer tmpnum = Integer.parseInt(tmpvalue);
                    countYCXRS += tmpnum; //总数不管怎样都增加
                    if ("迟到早退".equals(tmpkey)) {
                        countCHIDAORS += tmpnum; //迟到的人数增加
                        countZAOTUIRS += tmpnum; //早退的人数增加
                    }
                    if ("早退".equals(tmpkey)) {
                        countZAOTUIRS += tmpnum;	//早退人数增加
                    }
                    if ("迟到".equals(tmpkey)) {
                        countCHIDAORS += tmpnum;//迟到人数增加
                    }
                    if ("缺席".equals(tmpkey)) {
                        countQUEXIRS += tmpnum;//缺席人数增加
                    }
                    if ("正常".equals(tmpkey)) {
                        countZHENGCHANGRS += tmpnum;//正常人数增加
                    }
                }
            }
        } else { //如果周为空
            String tmphql = hql1;
            if (null != xingqi && !"".equals(xingqi)) {
                tmphql += " and XINGQI='" + xingqi + "'";
            }
            tmphql = tmphql + hql2;
            System.out.println("KQXX_XSCQServiceImpl中打印::" + "分学院：周是空时，拼凑的hql：：" + tmphql);
            List all = baseDao.executeSQL(tmphql);
            for (int alli = 0; alli < all.size(); alli++) {
                Object[] tmpobj = (Object[]) all.get(alli);
                String tmpkey = (null == tmpobj[0]) ? "" : tmpobj[0].toString();
                String tmpvalue = (null == tmpobj[1]) ? "0" : tmpobj[1].toString();
                Integer tmpnum = Integer.parseInt(tmpvalue);
                countYCXRS += tmpnum; //总数不管怎样都增加
                if ("迟到早退".equals(tmpkey)) {
                    countCHIDAORS += tmpnum; //迟到的人数增加
                    countZAOTUIRS += tmpnum; //早退的人数增加
                }
                if ("早退".equals(tmpkey)) {
                    countZAOTUIRS += tmpnum;	//早退人数增加
                }
                if ("迟到".equals(tmpkey)) {
                    countCHIDAORS += tmpnum;//迟到人数增加
                }
                if ("缺席".equals(tmpkey)) {
                    countQUEXIRS += tmpnum;//缺席人数增加
                }
                if ("正常".equals(tmpkey)) {
                    countZHENGCHANGRS += tmpnum;//正常人数增加
                }
            }
        }
        map.put("ycxrs", countYCXRS);
        map.put("qxrs", countQUEXIRS);
        map.put("zcrs", countZHENGCHANGRS);
        map.put("cdrs", countCHIDAORS);
        map.put("ztrs", countZAOTUIRS);
        return map;
    }

    @Override
    public List<KQXX_XSCQ> getBJKQXX_XSKQ_FEN(String jsid, String jgid, String ksid, String kcbid, XUEQI xq, String xingqi, String zhou, String ksrq, String jsrq, String xsid, String kqqk) {
        String hql = "FROM KQXX_XSCQ WHERE 1=1 ";
        String hql2 = " GROUP BY CQQK";
        if (null != jsid && !"".equals(jsid) && !"".equals(jsid)) { //增加教室ID
            hql += " and JS_ID=" + "'" + jsid + "'";
        }
        if (null != jgid && !"".equals(jgid)) {//增加老师ID
            hql += " and LS_ID=" + "'" + jgid + "'";
        }
        if (null != ksid && !"".equals(ksid)) {//增加课时id
            hql += " and KESHI_ID=" + "'" + ksid + "'";
        }
        if (null != kcbid && !"".equals(kcbid)) {//增加课程表ID
            hql += " and KCB_ID=" + "'" + kcbid + "'";
        }
        if (null != ksrq && !"".equals(ksrq)) {//增加开始日期
            hql += " and SKSJ>=to_date('" + ksrq + "','yyyy-mm-dd')";
        }
        if (null != jsrq && !"".equals(jsrq)) {//增加结束日期
            hql += " and SKSJ<=to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
        if (null != xsid && !"".equals(xsid)) {//学生学号
            hql += " AND XS_ID='" + xsid + "'";
        }
        if (null != kqqk && !"".equals(kqqk)) {//学生学号
            hql += " AND CQQK='" + kqqk + "'";
        }
        if (null != xq && !"".equals(xq)) {
            hql += "and  SKSJ between to_date('" + xq.getKSRQ().trim() + "','yyyy-mm-dd') and to_date('" + xq.getJSRQ().trim() + "','yyyy-mm-dd')";
        }
        if (null != zhou && !"".equals(zhou)) {//周不为空
            //如果星期不为空，则计算星期中课时
            List<String> riqi = BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////
            for (int i = 0; i < riqi.size(); i++) {
                String riqitmp = riqi.get(i);
                if (null != riqitmp && !"".equals(riqitmp)) {//增加课程表ID
                    hql += " and SKSJ>=to_date('" + riqitmp + "','yyyy-mm-dd') and SKSJ<=to_date('" + riqitmp + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
                }
                System.out.println("KQXX_XSCQServiceImpl中打印::" + "分学院：周不为空时，拼凑的hql：：" + hql);
                return baseDao.findAll(hql, KQXX_XSCQ.class);
            }
        }
        //如果周为空
        if (null != xingqi && !"".equals(xingqi)) {
            hql += " and XINGQI='" + xingqi + "'";
        }
        System.out.println("KQXX_XSCQServiceImpl中打印::" + "分学院：周是空时，拼凑的hql：：" + hql);
        return baseDao.findAll(hql, KQXX_XSCQ.class);
    }

    @Override
    public List<KQXX_XSCQ> findBANJIByZYandNJ(String zy_id, String nj_id, String bj_id) {
        String hql = "from KQXX_XSCQ where 1=1";
        if (null != zy_id && !"".equals(zy_id)) {
            hql += " and ZY_ID='" + zy_id + "'";
        }
        if (null != nj_id && !"".equals(nj_id)) {
            hql += " and NJ_ID='" + nj_id + "'";
        }
        List<KQXX_XSCQ> list = baseDao.findAll(hql, KQXX_XSCQ.class);
        return list;
    }
    /*
     * 按教师/教室查询出勤情况
     * */

    @Override
    public List<KQXX_XSCQ> findKQXXbyJGdetail(String jsid, String jgid, String ksid, String kcbid, XUEQI xq, String xingqi, String zhou, String ksrq, String jsrq) {
        String hql = "FROM KQXX_XSCQ WHERE 1=1 ";
        if (null != jsid && !"".equals(jsid) && !"".equals(jsid)) { //增加教室ID
            hql += " and JS_ID='" + jsid + "'";
        }
        if (null != jgid && !"".equals(jgid)) {//增加老师ID
            hql += " and LS_ID='" + jgid + "'";
        }
        if (null != ksid && !"".equals(ksid)) {//增加课时id
            hql += " and KESHI_ID='" + ksid + "'";
        }
        if (null != kcbid && !"".equals(kcbid)) {//增加课程表ID
            hql += " and KCB_FKS_ID='" + kcbid + "'";
        }
        if (null != ksrq && !"".equals(ksrq)) {//增加开始日期
            hql += " and SKSJ>=to_date('" + ksrq + "','yyyy-mm-dd')";
        }
        if (null != jsrq && !"".equals(jsrq)) {//增加结束日期
            hql += " and SKSJ<=to_date('" + jsrq + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }
        if (null != xq && !"".equals(xq)) {
            hql += "and  SKSJ between to_date('" + xq.getKSRQ().trim() + "','yyyy-mm-dd') and to_date('" + xq.getJSRQ().trim() + "','yyyy-mm-dd')";
        }
        if (null != zhou && !"".equals(zhou)) {//周不为空
            //如果星期不为空，则计算星期中课时
            List<String> riqi = BaseChangeTool.getRIQIhouZHOUXINGQI(xq.getKSRQ(), xingqi, BaseChangeTool.getZHOUtoInt(zhou));
				//根据时间查考勤信息表数//////////////////////////////////////////////
            /////////////////////////////////////////////////////////////////////
            for (int i = 0; i < riqi.size(); i++) {
                String riqitmp = riqi.get(i);
                if (null != riqitmp && !"".equals(riqitmp)) {//增加课程表ID
                    hql += " and SKSJ>=to_date('" + riqitmp + "','yyyy-mm-dd') and SKSJ<=to_date('" + riqitmp + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
                }
                System.out.println("KQXX_XSCQServiceImpl中打印::" + "按教师/教室统计：：：" + hql);
                return baseDao.findAll(hql, KQXX_XSCQ.class);
            }
        }
        if (null != xingqi && !"".equals(xingqi)) {
            hql += " and XINGQI='" + xingqi + "'";
        }
        System.out.println("KQXX_XSCQServiceImpl中打印::" + "按教师/教室统计：：：" + hql);
        return baseDao.findAll(hql, KQXX_XSCQ.class);
    }
}
