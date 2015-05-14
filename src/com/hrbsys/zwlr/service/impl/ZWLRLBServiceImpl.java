package com.hrbsys.zwlr.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.ZWLRLB;
import com.hrbsys.bean.ZWLRMD;
import com.hrbsys.zwlr.service.ZWLRLBService;

public class ZWLRLBServiceImpl implements ZWLRLBService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addZWLRLB(ZWLRLB y) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// y.setDJRQ(df.format(new Date()).toString());
		// y.setXGRQ(df.format(new Date()).toString());
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delZWLRLB(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			ZWLRLB yhz = findZWLRLB(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// 删除指纹录入列表时，连带删除指纹录入名单
	public boolean delZWLRLBAndZWLRMD(String arrayId) {
		String[] ids = arrayId.split(",");
		for (int i = 0; i < ids.length; i++) {
			List list = findZWLRLBMD(ids[i]);
			baseDao.deleteList(list);
			if (ids.length == (i + 1)) {
				return true;
			}
		}
		return false;
	}

	// 获取属于修改名单中的学生
	public List<ZWLRMD> updateInXs(String zwlrmd_id) {
		// 查询属于这个列表中的学生
		String sql = "select * from zwlrmd where zwlrmd_id='" + zwlrmd_id + "'";
		List queryList = baseDao.executeSQL(sql);
		List list = new ArrayList();
		for (int i = 0; i < queryList.size(); i++) {
			ZWLRMD zwlrmd = new ZWLRMD();
			Object[] obj = (Object[]) queryList.get(i);
			zwlrmd.setZWLRMD_ID((String) obj[0]);
			zwlrmd.setXH((String) obj[1]);
			zwlrmd.setZSXM((String) obj[2]);
			zwlrmd.setXYMC((String) obj[3]);
			zwlrmd.setZYMC((String) obj[4]);
			zwlrmd.setNJMC((String) obj[5]);
			zwlrmd.setBJMC((String) obj[6]);
			zwlrmd.setXB((String) obj[7]);
			zwlrmd.setNJ_ID((String) obj[8]);
			zwlrmd.setBJ_ID((String) obj[9]);
			list.add(zwlrmd);
		}
		return list;
	}

	// 获取不属于修改名单中的学生，并没有进行分配的的学生
	public List updateNotInXs() {
		// 查询出不在这个列表中的学生
		String sql = "select * from xsxx where xh not in(select xh from zwlrmd)";
		List querylist = baseDao.executeSQL(sql);
		List list = new ArrayList();
		// 遍历查询结果的集合
		for (int i = 0; i < querylist.size(); i++) {
			Object[] obj = (Object[]) querylist.get(i);
			ZWLRMD zwlrmd = new ZWLRMD();

			zwlrmd.setXH((String) obj[1]);
			zwlrmd.setZSXM((String) obj[2]);
			zwlrmd.setXB((String) obj[4]);
			zwlrmd.setNJ_ID((String) obj[57]);
			zwlrmd.setBJ_ID((String) obj[58]);
			zwlrmd.setXYMC((String) obj[70]);
			zwlrmd.setZYMC((String) obj[71]);
			zwlrmd.setNJMC((String) obj[72]);
			zwlrmd.setBJMC((String) obj[73]);
			list.add(zwlrmd);
		}
		return list;
	}

	// update
	@Override
	public boolean updateZWLRLB(ZWLRLB t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ZWLRLB tmpt = baseDao.findAll(
				"from ZWLRLB where ZWLR_ID='" + t.getZWLR_ID() + "'",
				ZWLRLB.class).get(0);
		tmpt.setLBMC(t.getLBMC());
		tmpt.setSFLR(t.getSFLR());
		tmpt.setZWLRSJ(t.getZWLRSJ());
		tmpt.setLBCJSJ(t.getLBCJSJ());
		tmpt.setLASTMODIFYTIME(t.getLASTMODIFYTIME());
		// tmpt.setXGRQ(df.format(new Date()).toString());
		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public ZWLRLB findZWLRLB(String t_id) {
		return baseDao.findAll("from ZWLRLB where ZWLR_ID='" + t_id + "'",
				ZWLRLB.class).get(0);
	}

	// 指纹录入列表删除标题时，根据zwlrmd_id查询出所有数据
	public List findZWLRLBMD(String t_id) {
		String hql = "from ZWLRMD where ZWLRMD_ID='" + t_id + "'";
		if (baseDao.findAll(hql, ZWLRMD.class) != null) {
			return baseDao.findAll(hql, ZWLRMD.class);
		}
		return null;
	}

	@Override
	public ZWLRLB findZWLRLBId(String t_listTitle) {
		List<ZWLRLB> list = baseDao.findAll("from ZWLRLB where LBMC='"
				+ t_listTitle.trim() + "'", ZWLRLB.class);
		// 判断查询出来的数据的条数是否大于0
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// findByPage
	@Override
	public List<ZWLRLB> findZWLRLBByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from ZWLRLB where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("LBMC") && !"".equals(params.get("LBMC"))) {
			param = "and LBMC like '%" + params.get("LBMC").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("SFLR") && !"".equals(params.get("SFLR"))) {
			param = "and SFLR like '%" + params.get("SFLR").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZWLRSJ") && !"".equals(params.get("ZWLRSJ"))) {
			param = "and ZWLRSJ like '%" + params.get("ZWLRSJ").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("LBCJSJ") && !"".equals(params.get("LBCJSJ"))) {
			param = "and LBCJSJ like '%" + params.get("LBCJSJ").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		List<ZWLRLB> list = baseDao.findByPage(hql, ZWLRLB.class, start,
				number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountZWLRLB(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from ZWLRLB where 1=1 ";
		if (null != params.get("LBMC") && !"".equals(params.get("LBMC"))) {
			hql += "and LBMC like '%" + params.get("LBMC").toString() + "%'";
		}
		if (null != params.get("SFLR") && !"".equals(params.get("SFLR"))) {
			hql += "and SFLR like '%" + params.get("SFLR").toString() + "%'";
		}
		if (null != params.get("ZWLRSJ") && !"".equals(params.get("ZWLRSJ"))) {
			hql += "and ZWLRSJ like '%" + params.get("ZWLRSJ").toString()
					+ "%'";
		}
		if (null != params.get("LBCJSJ") && !"".equals(params.get("LBCJSJ"))) {
			hql += "and LBCJSJ like '%" + params.get("LBCJSJ").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	/**
	 * 获取当前名单中的全部人数
	 */
	public Map<String,Integer> getCountZWLRMD(HashMap<String, String> params) {
		Map<String,Integer> map=new HashMap<String, Integer>();
		//查询出名单中的总人数
		String hql = "SELECT COUNT(*) from ZWLRMD where 1=1 ";
		//查询出名单中以录入指纹的人数
		String hql1 = "SELECT COUNT(*) from Xsxx where 1=1 ";
		// 判断传送过来的参数是否为空
		if (null != params.get("ZWLRMD_ID")
				&& !"".equals(params.get("ZWLRMD_ID"))) {
			hql += "and ZWLRMD_ID like '%" + params.get("ZWLRMD_ID").toString()
					+ "%'";
			hql1+="and XH in (select XH from ZWLRMD where ZWLRMD_ID='"+ params.get("ZWLRMD_ID").toString()+"') and ZHIWEN_ID1 is not null and ZHIWEN_ID2 is not null";
		}
		//统计出指纹名单中以录入的人数
		int zwlrrsCount=Integer.parseInt(baseDao.findAll(hql1, java.lang.Long.class).get(0).toString());
		// 统计出名单中总人数
		int totalCount = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		map.put("zwlrrsCount", zwlrrsCount);
		map.put("totalCount", totalCount);
		return map;
	}

	// findbypagelike
	@Override
	public List<ZWLRLB> findZWLRLBByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from ZWLRLB where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("LBMC") && !"".equals(params.get("LBMC"))) {
			param = "and LBMC like '%" + params.get("LBMC").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("SFLR") && !"".equals(params.get("SFLR"))) {
			param = "and SFLR like '%" + params.get("SFLR").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZWLRSJ") && !"".equals(params.get("ZWLRSJ"))) {
			param = "and ZWLRSJ like '%" + params.get("ZWLRSJ").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("LBCJSJ") && !"".equals(params.get("LBCJSJ"))) {
			param = "and LBCJSJ like '%" + params.get("LBCJSJ").toString()
					+ "%'";
			params2.add(param);
		}
		List<ZWLRLB> list = baseDao.findAll(hql, ZWLRLB.class);
		return list;
	}

	/**
	 * 查询出指纹录入名单中的数据，显示到前台
	 */
	// public List<ZWLRMD> findZWLRLBMD(int start, int number,
	// HashMap<String, String> params, String order, String sort) {
	// String hql = "from zwlrmd where 1=1 ";
	// String param = "";
	// ArrayList<String> params2 = new ArrayList<String>();
	// if (null != params.get("ZWLR_ID") && !"".equals(params.get("ZWLR_ID"))) {
	// param = "and zwlrmd_id=(select zwlr_id from zwlrlb)";
	// params2.add(param);
	// }
	// List<ZWLRMD> list = baseDao.findByPage(hql, ZWLRMD.class, start,
	// number, params2, order, sort);
	// return list;
	// }

	/**
	 * 指纹录入列表接口方法的实现
	 */
	// 向指纹录入名单中加入学生信息
	@Override
	public boolean addZWLRMD(ZWLRMD md) {
		return baseDao.save(md);
	}

	// 删除指纹录入名单中的学生信息
	@Override
	public boolean delZWLRMD(String y) {
		String[] arrayXH = y.split(",");
		for (String xh : arrayXH) {
			ZWLRMD zwlrmd = findZwlrMdStu(xh);
			if (zwlrmd != null) {
				baseDao.delete(zwlrmd);
				return true;
			}
		}
		return false;
	}

	// 更新指纹录入名单中的学生信息
	@Override
	public boolean updateZWLRMD(ZWLRMD md) {
		return baseDao.update(md);
	}

	// 查询要修改的数据在zwlrmd表中有没有
	public ZWLRMD findZwlrMdStu(String xh) {
		String hql = "from ZWLRMD where XH='" + xh + "'";
		List<ZWLRMD> list = baseDao.findAll(hql, ZWLRMD.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	// 指纹录入功能 查询回来两条数据
	public List findTwoData(String id, int pageNum) {
		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from xsxx where xh in ("
				+ "select xh from zwlrmd where zwlrmd_id='"
				+ id
				+ "') and zhiwen_id1 is null and zhiwen_id2 is  null) A )"
				+ " WHERE RN <=" + pageNum;
		// 执行sql语句进行查询数据
		List querylist = baseDao.executeSQL(sql);
		List list = new ArrayList();
		// 遍历查询结果的集合
		for (int i = 0; i < querylist.size(); i++) {
			Object[] obj = (Object[]) querylist.get(i);
			// Xsxx xsxx=new Xsxx();
			// xsxx.setXsId((String)obj[0]);
			// xsxx.setXh((String) obj[1]);
			// xsxx.setZsxm((String) obj[2]);
			// xsxx.setXYMC((String) obj[70]);
			// xsxx.setZYMC((String) obj[71]);
			// xsxx.setNJMC((String) obj[72]);
			// xsxx.setBJMC((String) obj[73]);
			// xsxx.setXb((String) obj[4]);
			// xsxx.setNjId((String) obj[57]);
			// xsxx.setBjId((String) obj[58]);
			//
			ZWLRMD zwlrmd = new ZWLRMD();
			zwlrmd.setXH((String) obj[1]);
			zwlrmd.setZSXM((String) obj[2]);
			zwlrmd.setXYMC((String) obj[70]);
			zwlrmd.setZYMC((String) obj[71]);
			zwlrmd.setNJMC((String) obj[72]);
			zwlrmd.setBJMC((String) obj[73]);
			zwlrmd.setXB((String) obj[4]);
			zwlrmd.setNJ_ID((String) obj[57]);
			zwlrmd.setBJ_ID((String) obj[58]);
			list.add(zwlrmd);
		}
		return list;
	}

	// 下一条数据
	public List nextData(String ZWLR_ID, int start, int end) {
		// String sql =
		// "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM zwlrmd where zwlrmd_id='"
		// + ZWLR_ID + "') A ) WHERE RN BETWEEN " + start + " AND " + end;

		String sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (select * from xsxx where xh in ("
				+ "select xh from zwlrmd where zwlrmd_id='"
				+ ZWLR_ID
				//+ "') and zhiwen_id1 is not null and zhiwen_id2 is not null) A )"
                                                                        + "') and zhiwen_id1 is null and zhiwen_id2 is null) A )"
				+ " WHERE RN BETWEEN " + start + " AND " + end;

		List querylist = baseDao.executeSQL(sql);
		List list = new ArrayList();
		// 遍历查询结果的集合
		for (int i = 0; i < querylist.size(); i++) {
			Object[] obj = (Object[]) querylist.get(i);
			ZWLRMD zwlrmd = new ZWLRMD();
			zwlrmd.setZWLRMD_ID((String) obj[0]);
			zwlrmd.setXH((String) obj[1]);
			zwlrmd.setZSXM((String) obj[2]);
			zwlrmd.setXYMC((String) obj[3]);
			zwlrmd.setZYMC((String) obj[4]);
			zwlrmd.setNJMC((String) obj[5]);
			zwlrmd.setBJMC((String) obj[6]);
			zwlrmd.setXB((String) obj[7]);
			zwlrmd.setNJ_ID((String) obj[8]);
			zwlrmd.setBJ_ID((String) obj[9]);
			list.add(zwlrmd);
		}
		return list;
	}

	// 查询指纹录入名单中，没有录入指纹的学生信息
	public List<Xsxx> notFP(String zwlr_id) {
		String hql = "from Xsxx where XH in (select XH from ZWLRMD where ZWLRMD_ID='"
				+ zwlr_id + "') and ZHIWEN_ID1 is null and ZHIWEN_ID2 is  null";

		List<Xsxx> list = baseDao.findAll(hql, Xsxx.class);
		return list;
	}

}