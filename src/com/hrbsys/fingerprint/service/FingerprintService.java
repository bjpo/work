package com.hrbsys.fingerprint.service;

import com.hrbsys.basicinfo.student.action.Base64;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.UPDATEVERSION;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.bean.ZWLRMD;
import com.hrbsys.fingerprint.dao.FingerprintDao;
import com.hrbsys.tools.BaseChangeTool;
import com.hrbsys.tools.UUIDTools;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Li
 */
public class FingerprintService
{
    private FingerprintDao fingerprintDao;

    public Map queryFingerprintList(Object object, String sort, String order, int page, int rows) throws IllegalArgumentException, IllegalAccessException
    {
        Map<String, Object> map = new HashMap<>();
        QueryParametersProcessingResult result = QueryParametersProcessor.process(object, sort, order);
        String str = "from ZWLRLB where 1=1 ";
        String hql = str + result.getCondition() + result.getSequence();
        map.put("rows", fingerprintDao.query(hql, result.getConditionValue(), page, rows));
        str = "select count(*) from ZWLRLB where 1=1 ";
        hql = str + result.getCondition();
        map.put("total", fingerprintDao.total(hql, result.getConditionValue()));
        return map;
    }

    public List querySquad()
    {
        String hql = "select new BANJI(BJ_ID,BJMC) from BANJI order by ZYMC,NJMC";
        return fingerprintDao.findAll(hql, BANJI.class);
    }

    public List queryStudent(BANJI squad)
    {
        String hql = "select new Xsxx(xh,zsxm) from Xsxx where bjId = ? and xh not in (select XH from ZWLRMD where BJ_ID= ?) order by xh asc";
        return fingerprintDao.findAll(hql, Xsxx.class, new Object[]{squad.getBJ_ID(), squad.getBJ_ID()});
    }

    public List queryFingerprintRoster(ZWLRMD fingerprintRoster)
    {
        String hql = "from ZWLRMD where ZWLRMD_ID = ? order by XH asc";
        return fingerprintDao.findAll(hql, ZWLRMD.class, new Object[]{fingerprintRoster.getZWLRMD_ID()});
    }

    public Map saveOrUpdateFingerprintList(String operationType, ZWLRLB fingerprintList, String[] studentNumbers)
    {
        Map<String, Object> map = new HashMap<>();
        String hql = "from ZWLRLB where LBMC = ?";
        List<ZWLRLB> list = fingerprintDao.findAll(hql, ZWLRLB.class, new Object[]{fingerprintList.getLBMC()});
        if (list.isEmpty() || list.get(0).getZWLR_ID().equals(fingerprintList.getZWLR_ID())) 
        {
            switch (operationType)
            {
                case "save":
                {
                    fingerprintList.setZWLR_ID(UUIDTools.getUUID());
                    fingerprintList.setSFLR("否");
                    fingerprintList.setLBCJSJ(BaseChangeTool.getCurrentTime());
                    fingerprintList.setLASTMODIFYTIME(BaseChangeTool.getCurrentTime());
                    String sql = "insert into zwlrmd (id,zwlrmd_id,xh,zsxm,xb,bj_id,bjmc,nj_id,njmc,zymc,xymc) "
                            + "select ?,?,xh,zsxm,xb,bj_id,bjmc,nj_id,njmc,zymc,xymc from xsxx where xh = ?";
                    fingerprintDao.saveFingerprintList(sql, fingerprintList, studentNumbers);
                    map.put("success", true);
                    map.put("message", "操作成功");
                    break;
                }
                case "update":
                {
                    String sql1 = "update zwlrlb set lbmc = ? where zwlr_id = ?";
                    String sql2 = "delete from zwlrmd where zwlrmd_id = ?";
                    String sql3 = "insert into zwlrmd (id,zwlrmd_id,xh,zsxm,xb,bj_id,bjmc,nj_id,njmc,zymc,xymc) "
                            + "select ?,?,xh,zsxm,xb,bj_id,bjmc,nj_id,njmc,zymc,xymc from xsxx where xh = ?";
                    fingerprintDao.updateFingerprintList(sql1, sql2, sql3, fingerprintList, studentNumbers);
                    map.put("success", true);
                    map.put("message", "操作成功");
                    break;
                }
            }
        } else
        {
            map.put("success", false);
            map.put("message", "列表名称已存在");
        }
        return map;
    }

    public Map queryUnregisteredStudent(ZWLRLB fingerprintList)
    {
        Map<String, Object> map = new HashMap<>();
        String hql = "select count(*) from ZWLRMD where ZWLRMD_ID = ?";
        int amount = fingerprintDao.total(hql, new Object[]{fingerprintList.getZWLR_ID()});
        map.put("amount", amount);
        hql = "from Xsxx where zhiwenId1 is null and zhiwenId2 is null and xh in (select XH from ZWLRMD where ZWLRMD_ID = ?) order by xh asc";
        List list = fingerprintDao.findAll(hql, Xsxx.class, new Object[]{fingerprintList.getZWLR_ID()});
        map.put("unregisteredStudent", list);
        map.put("registeredAmount", amount - list.size());
        return map;
    }

    public Map saveFingerprintRegister(Map<String, String> fileConfig, Xsxx studentInfo, String fingerprintId1, String fingerprintId2) throws IOException
    {
        Map<String, Object> map = new HashMap<>();
        String name = fileConfig.get("path") + studentInfo.getXh() + fileConfig.get("flag1") + fileConfig.get("suffix");
        byte[] data = Base64.decode(fingerprintId1);
        saveFingerprintRegisterFile(name, data);
        name = fileConfig.get("path") + studentInfo.getXh() + fileConfig.get("flag2") + fileConfig.get("suffix");
        data = Base64.decode(fingerprintId2);
        saveFingerprintRegisterFile(name, data);
        String hql = "from UPDATEVERSION where NAME like ? and TYPE = ?";
        List<UPDATEVERSION> updateversionList = fingerprintDao.findAll(hql, UPDATEVERSION.class, new Object[]{studentInfo.getXh() + "%", fileConfig.get("type")});
        if (updateversionList.isEmpty())
        {
            UPDATEVERSION updateversion = new UPDATEVERSION();
            updateversion.setID(UUIDTools.getUUID());
            updateversion.setNAME(studentInfo.getXh() + fileConfig.get("flag1"));
            updateversion.setOPT("Add");
            updateversion.setTYPE(fileConfig.get("type"));
            updateversion.setUPDATETIME(BaseChangeTool.getCurrentTime());
            updateversionList.add(updateversion);
            updateversion = new UPDATEVERSION();
            updateversion.setID(UUIDTools.getUUID());
            updateversion.setNAME(studentInfo.getXh() + fileConfig.get("flag2"));
            updateversion.setOPT("Add");
            updateversion.setTYPE(fileConfig.get("type"));
            updateversion.setUPDATETIME(BaseChangeTool.getCurrentTime());
            updateversionList.add(updateversion);
        } else
        {
            updateversionList.get(0).setOPT("Add");
            updateversionList.get(0).setUPDATETIME(BaseChangeTool.getCurrentTime());
            updateversionList.get(1).setOPT("Add");
            updateversionList.get(1).setUPDATETIME(BaseChangeTool.getCurrentTime());
        }
        hql = "from Xsxx where xh = ?";
        List<Xsxx> studentInfoList = fingerprintDao.findAll(hql, Xsxx.class, new Object[]{studentInfo.getXh()});
        studentInfoList.get(0).setZhiwenId1("有");
        studentInfoList.get(0).setZhiwenId2("有");
        fingerprintDao.saveFingerprintRegister(updateversionList, studentInfoList);
        return map;
    }

    private void saveFingerprintRegisterFile(String name, byte[] data) throws FileNotFoundException, IOException
    {
        try (FileOutputStream stream = new FileOutputStream(name))
        {
            stream.write(data, 0, data.length);
        }
    }

    public Map deleteFingerprintRegister(Map<String, String> fileConfig, String[] studentNumbers)
    {
        Map<String, Object> map = new HashMap<>();
        for (String studentNumber : studentNumbers)
        {
            deleteFingerprintRegisterFile(fileConfig.get("path") + studentNumber + fileConfig.get("flag1") + fileConfig.get("suffix"));
            deleteFingerprintRegisterFile(fileConfig.get("path") + studentNumber + fileConfig.get("flag2") + fileConfig.get("suffix"));
        }
        String str = "";
        for (int i = 0; i < studentNumbers.length; i++)
        {
            str += "?,";
        }
        String hql = "from UPDATEVERSION where NAME in (" + str.substring(0, str.length() - 1) + ")";
        List<UPDATEVERSION> list=fingerprintDao.findAll(hql, UPDATEVERSION.class, studentNumbers);
        if (list.isEmpty())
        {
            for (String studentNumber : studentNumbers)
            {
                UPDATEVERSION updateversion = new UPDATEVERSION();
                updateversion.setID(UUIDTools.getUUID());
                updateversion.setNAME(studentNumber);
                updateversion.setOPT("Del");
                updateversion.setTYPE(fileConfig.get("type"));
                updateversion.setUPDATETIME(BaseChangeTool.getCurrentTime());
                list.add(updateversion);
            }
        }else
        {
            List<String> studentNumbersNotInUpdateversion = new ArrayList<>();
            for (String studentNumber : studentNumbers)
            {
                boolean notInUpdateversion = true;
                for (UPDATEVERSION u : list)
                {
                    if (studentNumber.equals(u.getNAME()))
                    {
                        u.setUPDATETIME(BaseChangeTool.getCurrentTime());
                        notInUpdateversion = false;
                        break;
                    }
                }
                if (notInUpdateversion)
                {
                    studentNumbersNotInUpdateversion.add(studentNumber);
                }
            }
            for (String studentNumber : studentNumbersNotInUpdateversion)
            {
                UPDATEVERSION updateversion = new UPDATEVERSION();
                updateversion.setID(UUIDTools.getUUID());
                updateversion.setNAME(studentNumber);
                updateversion.setOPT("Del");
                updateversion.setTYPE(fileConfig.get("type"));
                updateversion.setUPDATETIME(BaseChangeTool.getCurrentTime());
                list.add(updateversion);
            }
        }
        String sql = "update xsxx set ZHIWEN_ID1= ? , ZHIWEN_ID2 = ? where xh in (" + str.substring(0, str.length() - 1) + ")";
        fingerprintDao.deleteFingerprintRegister(sql, "", "", studentNumbers, list);
        return map;
    }

    private void deleteFingerprintRegisterFile(String name)
    {
        File file = new File(name);
        if (file.exists())
        {
            file.delete();
        }
    }

    public Map deleteFingerprintList(String[] fingerprintListIds)
    {
        Map<String, Object> map = new HashMap<>();
        String str = "";
        for (int i = 0; i < fingerprintListIds.length; i++)
        {
            str += "?,";
        }
        String sql1 = "delete from zwlrlb where zwlr_id in (" + str.substring(0, str.length() - 1) + ")";
        String sql2 = "delete from zwlrmd where zwlrmd_id in (" + str.substring(0, str.length() - 1) + ")";
        fingerprintDao.deleteFingerprintList(sql1, sql2, fingerprintListIds);
        return map;
    }

    public void setFingerprintDao(FingerprintDao fingerprintDao) {
        this.fingerprintDao = fingerprintDao;
    }
}