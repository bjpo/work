package com.hrbsys.users.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.JUESE2MOKUAI;
import com.hrbsys.bean.JUESE2YONGHU;
import com.hrbsys.bean.JUESE2YONGHUZU;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.users.service.UserService;

public class UserServiceImpl implements UserService {
	//单例
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// 用户组相关方法
	@Override
	public boolean addYHZ(YONGHUZU y) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		y.setDjrq(df.format(new Date()).toString());
		y.setXgrq(df.format(new Date()).toString());
		return baseDao.save(y);
	}

	@Override
	public boolean delYHZ(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			YONGHUZU yhz = findYHZ(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean updateYHZ(YONGHUZU yhz) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		YONGHUZU tmpy = baseDao.findAll(
				"from YONGHUZU where yhzid='" + yhz.getYhzid() + "'",
				YONGHUZU.class).get(0);
		tmpy.setBz(yhz.getBz());
		tmpy.setYhzmc(yhz.getYhzmc());
		tmpy.setXgrq(df.format(new Date()).toString());
		return baseDao.update(tmpy);
	}

	@Override
	public YONGHUZU findYHZ(String yhz_id) {
		return baseDao.findAll("from YONGHUZU where yhzid='" + yhz_id + "'",
				YONGHUZU.class).get(0);
	}

	@Override
	public List<YONGHUZU> findYONGHUZUByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from YONGHUZU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("yhz_yhzmc")
				&& !"".equals(params.get("yhz_yhzmc"))) {
			String param = "and YHZMC like '%"
					+ params.get("yhz_yhzmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("yhz_bz") && !"".equals(params.get("yhz_bz"))) {
			String param = "and BZ like '%" + params.get("yhz_bz").toString()
					+ "%'";
			params2.add(param);
		}
		List<YONGHUZU> list = baseDao.findByPage(hql, YONGHUZU.class, start,
				number, params2, order, sort);
		return list;
	}

	@Override
	public int getCountYONGHUZU(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from YONGHUZU where 1=1";
		if (null != params.get("yhz_yhzmc")
				&& !"".equals(params.get("yhz_yhzmc"))) {
			hql += "and YHZMC like '%" + params.get("yhz_yhzmc").toString()
					+ "%'";
		}
		if (null != params.get("yhz_bz") && !"".equals(params.get("yhz_bz"))) {
			hql += "and BZ like '%" + params.get("yhz_bz").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<YONGHUZU> findYONGHUZUByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from YONGHUZU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("yhz_yhzmc")
				&& !"".equals(params.get("yhz_yhzmc"))) {
			String param = "and YHZMC like '%"
					+ params.get("yhz_yhzmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("yhz_bz") && !"".equals(params.get("yhz_bz"))) {
			String param = "and BZ like '%" + params.get("yhz_bz").toString()
					+ "%'";
			params2.add(param);
		}
		List<YONGHUZU> list = baseDao.findAll(hql, YONGHUZU.class);
		return list;
	}

	// 用户相关方法
	@Override
	public boolean addYONGHU(YONGHU y) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		y.setDjrq(df.format(new Date()).toString());
		y.setXgrq(df.format(new Date()).toString());
		return baseDao.save(y);
	}

	@Override
	public boolean delYONGHU(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				YONGHU tmp = findYONGHU(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateYONGHU(YONGHU yh) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		YONGHU tmpy = baseDao.findAll(
				"from YONGHU where yhid='" + yh.getYhid() + "'", YONGHU.class)
				.get(0);
		tmpy.setYhm(yh.getYhm());
		tmpy.setYhmm(yh.getYhmm());
		tmpy.setXgrq(df.format(new Date()).toString());
		YONGHUZU yz = findYHZ(yh.getYonghuzu().getYhzid());
		tmpy.setYonghuzu(yz);
		return baseDao.update(tmpy);
	}

	// 修改用户登录的时间
	public boolean updateYONGHULoginTime(YONGHU yh) {
		YONGHU tmpy = baseDao.findAll(
				"from YONGHU where yhid='" + yh.getYhid() + "'", YONGHU.class)
				.get(0);
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		tmpy.setLastLoginTime(yh.getLoginTime());
		tmpy.setLoginTime(sdf.format(new Date()));
		return baseDao.update(tmpy);
	}

	@Override
	public YONGHU findYONGHU(String yh_id) {
		YONGHU readyh = baseDao.findAll(
				"from YONGHU where yhid='" + yh_id + "'", YONGHU.class).get(0);
		readyh.setJson_yhzid(readyh.getYonghuzu().getYhzid());
		readyh.setJson_yhzmc(readyh.getYonghuzu().getYhzmc());
		return readyh;
	}

	// 根据用户名进行查询用户
	public YONGHU findYONGHUName(String yh_name) {
		return baseDao.findAll("from YONGHU where yhm='" + yh_name + "'",
				YONGHU.class).get(0);
	}

	@Override
	public List<YONGHU> findYONGHUByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from YONGHU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("yh_yhmc") && !"".equals(params.get("yh_yhmc"))) {
			String param = "and YHM like '%" + params.get("yh_yhmc").toString()
					+ "%'";
			System.out.println("userServiceImpl中param：：：：：：：：：：：：：：：：："+param);
			params2.add(param);
		}
		List<YONGHU> list = baseDao.findByPage(hql, YONGHU.class, start,
				number, params2, order, sort);
		return list;
	}

	@Override
	public int getCountYONGHU(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from YONGHU where 1=1";
		if (null != params.get("yh_yhmc") && !"".equals(params.get("yh_yhmc"))) {
			hql += "and YHM like '%" + params.get("yh_yhmc").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<YONGHU> findYONGHUByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from YONGHU where 1=1";
		List<YONGHU> list = baseDao.findAll(hql, YONGHU.class);
		return list;
	}

	// 角色相关方法：
	@Override
	public boolean addJUESE(JUESE js) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		js.setDjrq(df.format(new Date()).toString());
		js.setXgrq(df.format(new Date()).toString());
		return baseDao.save(js);
	}

	@Override
	public boolean delJUESE(String js_id) {
		String[] ids = js_id.split(",");
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				JUESE tmp = findJUESE(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateJUESE(JUESE js) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		JUESE tmpjs = baseDao.findAll(
				"from JUESE where jsid='" + js.getJsid() + "'", JUESE.class)
				.get(0);
		tmpjs.setBz(js.getBz());
		tmpjs.setMs(js.getMs());
		tmpjs.setJsmc(js.getJsmc());
		tmpjs.setXgrq(df.format(new Date()).toString());
		return baseDao.update(tmpjs);

	}

	@Override
	public JUESE findJUESE(String js_id) {
		return baseDao.findAll("from JUESE where jsid='" + js_id + "'",
				JUESE.class).get(0);
	}

	@Override
	public List<JUESE> findJUESEByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("js_jsmc") && !"".equals(params.get("js_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("js_jsmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("js_bz") && !"".equals(params.get("js_bz"))) {
			String param = "and BZ like '%" + params.get("js_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("js_ms") && !"".equals(params.get("js_ms"))) {
			String param = "and MS like '%" + params.get("js_ms").toString()
					+ "%'";
			params2.add(param);
		}
		List<JUESE> list = baseDao.findByPage(hql, JUESE.class, start, number,
				params2, order, sort);
		return list;

	}

	@Override
	public int getCountJUESE(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JUESE where 1=1";
		if (null != params.get("js_jsmc") && !"".equals(params.get("js_jsmc"))) {
			hql += "and JSMC like '%" + params.get("js_jsmc").toString() + "%'";
		}
		if (null != params.get("js_bz") && !"".equals(params.get("js_bz"))) {
			hql += "and BZ like '%" + params.get("js_bz").toString() + "%'";
		}
		if (null != params.get("js_ms") && !"".equals(params.get("js_ms"))) {
			hql += "and MS like '%" + params.get("js_ms").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;

	}

	@Override
	public List<JUESE> findJUESEByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from JUESE where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("js_jsmc") && !"".equals(params.get("js_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("js_jsmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("js_bz") && !"".equals(params.get("js_bz"))) {
			String param = "and BZ like '%" + params.get("js_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("js_ms") && !"".equals(params.get("js_ms"))) {
			String param = "and MS like '%" + params.get("js_MS").toString()
					+ "%'";
			params2.add(param);
		}
		List<JUESE> list = baseDao.findAll(hql, JUESE.class);
		return list;
	}

	// 模块相关
	@Override
	public boolean addMOKUAI(MOKUAI mk) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		mk.setDjrq(df.format(new Date()).toString());
		mk.setXgrq(df.format(new Date()).toString());
		if (null != mk.getFkmk() && !"".equals(mk.getFkmk())) {
			mk.setFkmkmc(this.findMOKUAI(mk.getFkmk()).getMkmc());
		}
		return baseDao.save(mk);
	}

	@Override
	public boolean delMOKUAI(String mk_id) {
		String[] ids = mk_id.split(",");
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				MOKUAI tmp = findMOKUAI(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateMOKUAI(MOKUAI mk) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		MOKUAI tmpmk = baseDao.findAll(
				"from MOKUAI where mkid='" + mk.getMkid() + "'", MOKUAI.class)
				.get(0);
		tmpmk.setMkmc(mk.getMkmc());
		tmpmk.setMkbm(mk.getMkbm());
		tmpmk.setBz(mk.getBz());
		tmpmk.setMs(mk.getMs());
		tmpmk.setMkurl(mk.getMkurl());
		tmpmk.setOpenstate(mk.getOpenstate());
		tmpmk.setXgrq(df.format(new Date()).toString());
		tmpmk.setFkmk(mk.getFkmk());
		if (null != mk.getFkmk() && !"".equals(mk.getFkmk())) {
			tmpmk.setFkmkmc(this.findMOKUAI(mk.getFkmk()).getMkmc());
		} else {
			tmpmk.setFkmkmc(null);
		}
		tmpmk.setIconcls(mk.getIconcls());
		tmpmk.setPaixu(mk.getPaixu());
		tmpmk.setMkym(mk.getMkym());
		tmpmk.setIsShowInLeftMenu(mk.getIsShowInLeftMenu());
		tmpmk.setMenuCateId(mk.getMenuCateId());
		return baseDao.update(tmpmk);
	}

	@Override
	public MOKUAI findMOKUAI(String mk_id) {
		return baseDao.findAll("from MOKUAI where mkid='" + mk_id + "'",
				MOKUAI.class).get(0);
	}

	@Override
	public List<MOKUAI> findMOKUAIByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from MOKUAI where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("mk_mkmc") && !"".equals(params.get("mk_mkmc"))) {
			String param = "and mkmc like '%"
					+ params.get("mk_mkmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("mk_mkbz") && !"".equals(params.get("mk_mkbz"))) {
			String param = "and BZ like '%" + params.get("mk_mkbz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("mk_ms") && !"".equals(params.get("mk_ms"))) {
			String param = "and MS like '%" + params.get("mk_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("mk_mkbm") && !"".equals(params.get("mk_mkbm"))) {
			String param = "and mkbm like '%"
					+ params.get("mk_mkbm").toString() + "%'";
			params2.add(param);
		}
		List<MOKUAI> list = baseDao.findByPage(hql, MOKUAI.class, start,
				number, params2, order, sort);
		return list;

	}

	@Override
	public int getCountMOKUAI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from MOKUAI where 1=1";
		if (null != params.get("mk_mkmc") && !"".equals(params.get("mk_mkmc"))) {
			hql += "and MKMC like '%" + params.get("mk_mkmc").toString() + "%'";
		}
		if (null != params.get("mk_bz") && !"".equals(params.get("mk_bz"))) {
			hql += "and BZ like '%" + params.get("mk_bz").toString() + "%'";
		}
		if (null != params.get("mk_ms") && !"".equals(params.get("mk_ms"))) {
			hql += "and MS like '%" + params.get("mk_ms").toString() + "%'";
		}
		if (null != params.get("mk_mkbm") && !"".equals(params.get("mk_mkbm"))) {
			hql += "and mkbm like '%" + params.get("mk_mkbm").toString() + "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;

	}

	@Override
	public List<MOKUAI> findMOKUAIByPageApp(HashMap<String, String> params,
			String order, String sort) {
		String hql = "from MOKUAI where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("mk_mkmc") && !"".equals(params.get("mk_mkmc"))) {
			String param = "and mkmc like '%"
					+ params.get("mk_mkmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("mk_bz") && !"".equals(params.get("mk_bz"))) {
			String param = "and BZ like '%" + params.get("mk_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("mk_ms") && !"".equals(params.get("mk_ms"))) {
			String param = "and MS like '%" + params.get("mk_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("mk_mkbm") && !"".equals(params.get("mk_mkbm"))) {
			String param = "and mkbm like '%"
					+ params.get("mk_mkbm").toString() + "%'";
			params2.add(param);
		}
		List<MOKUAI> list = baseDao.findAll(hql, MOKUAI.class);
		return list;
	}

	// 角色2用户相关
	@Override
	public boolean addJUESE2YONGHU(JUESE2YONGHU jy) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		jy.setDjrq(df.format(new Date()).toString());
		jy.setXgrq(df.format(new Date()).toString());
		jy.setYhmc(this.findYONGHU(jy.getYhid()).getYhm()); // 加入用户名
		jy.setJsmc(this.findJUESE(jy.getJsid()).getJsmc());// 角色名称
		return baseDao.save(jy);
	}

	@Override
	public boolean delJUESE2YONGHU(String jy_id) {
		String[] ids = jy_id.split(",");
		System.out.println("用户角色ID数组：" + jy_id);
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				System.out.println("分解后的iD:" + id);
				JUESE2YONGHU tmp = findJUESE2YONGHU(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateJUESE2YONGHU(JUESE2YONGHU jy) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		JUESE2YONGHU tmpjy = baseDao.findAll(
				"from JUESE2YONGHU where jsyhid='" + jy.getJsyhid() + "'",
				JUESE2YONGHU.class).get(0);
		tmpjy.setJsid(jy.getJsid());
		tmpjy.setYhid(jy.getYhid());
		tmpjy.setMs(jy.getMs());
		tmpjy.setBz(jy.getBz());
		tmpjy.setYhmc(this.findYONGHU(jy.getYhid()).getYhm()); // 设置用户名
		tmpjy.setJsmc(this.findJUESE(jy.getJsid()).getJsmc());// 设置角色名称
		tmpjy.setXgrq(df.format(new Date()).toString());
		return baseDao.update(tmpjy);
	}

	@Override
	public JUESE2YONGHU findJUESE2YONGHU(String jy_id) {
		return baseDao.findAll(
				"from JUESE2YONGHU where jsyhid='" + jy_id + "'",
				JUESE2YONGHU.class).get(0);
	}

	@Override
	public List<JUESE2YONGHU> findJUESE2YONGHUByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE2YONGHU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jy_bz") && !"".equals(params.get("jy_bz"))) {
			String param = "and bz like '%" + params.get("jy_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jy_ms") && !"".equals(params.get("jy_ms"))) {
			String param = "and MS like '%" + params.get("jy_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jy_yhmc") && !"".equals(params.get("jy_yhmc"))) {
			String param = "and YHMC like '%"
					+ params.get("jy_yhmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jy_jsmc") && !"".equals(params.get("jy_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jy_jsmc").toString() + "%'";
			params2.add(param);
		}

		System.out
				.println(params.get("jy_jsmc")
						+ "::::::::::::::::::::::::::::::::::asdfffffffffffffffffffffffffffff");
		List<JUESE2YONGHU> list = baseDao.findByPage(hql, JUESE2YONGHU.class,
				start, number, params2, order, sort);
		return list;
	}

	@Override
	public int getCountJUESE2YONGHU(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JUESE2YONGHU where 1=1";
		if (null != params.get("jy_bz") && !"".equals(params.get("jy_bz"))) {
			String param = "and bz like '%" + params.get("jy_bz").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jy_ms") && !"".equals(params.get("jy_ms"))) {
			String param = "and MS like '%" + params.get("jy_ms").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jy_yhmc") && !"".equals(params.get("jy_yhmc"))) {
			String param = "and YHMC like '%"
					+ params.get("jy_yhmc").toString() + "%'";
			hql += param;
		}
		if (null != params.get("jy_jsmc") && !"".equals(params.get("jy_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jy_jsmc").toString() + "%'";
			hql += param;
		}
		System.out.println("统计总数HQL：" + hql);
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<JUESE2YONGHU> findJUESE2YONGHUByPageApp(
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE2YONGHU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jy_bz") && !"".equals(params.get("jy_bz"))) {
			String param = "and bz like '%" + params.get("jy_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jy_ms") && !"".equals(params.get("jy_ms"))) {
			String param = "and MS like '%" + params.get("jy_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jy_yhmc") && !"".equals(params.get("jy_yhmc"))) {
			String param = "and YHMC like '%"
					+ params.get("jy_yhmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jy_jsmc") && !"".equals(params.get("jy_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jy_jsmc").toString() + "%'";
			params2.add(param);
		}
		List<JUESE2YONGHU> list = baseDao.findAll(hql, JUESE2YONGHU.class);
		return list;
	}

	// 角色用户组相关
	@Override
	public boolean addJUESE2YONGHUZU(JUESE2YONGHUZU jyz) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		jyz.setDjrq(df.format(new Date()).toString());
		jyz.setXgrq(df.format(new Date()).toString());
		jyz.setYhzmc(this.findYHZ(jyz.getYhzid()).getYhzmc());// 加入用户组名称
		jyz.setJsmc(this.findJUESE(jyz.getJsid()).getJsmc());// 角色名称
		return baseDao.save(jyz);
	}

	@Override
	public boolean delJUESE2YONGHUZU(String jyz_id) {
		String[] ids = jyz_id.split(",");
		System.out.println("用户组角色ID数组：" + jyz_id);
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				System.out.println("分解后的iD:" + id);
				JUESE2YONGHUZU tmp = findJUESE2YONGHUZU(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateJUESE2YONGHUZU(JUESE2YONGHUZU jyz) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		JUESE2YONGHUZU tmpjyz = baseDao.findAll(
				"from JUESE2YONGHUZU where jsyhzid='" + jyz.getJsyhzid() + "'",
				JUESE2YONGHUZU.class).get(0);
		tmpjyz.setJsid(jyz.getJsid());
		tmpjyz.setYhzid(jyz.getYhzid());
		tmpjyz.setMs(jyz.getMs());
		tmpjyz.setBz(jyz.getBz());
		tmpjyz.setYhzmc(this.findYHZ(jyz.getYhzid()).getYhzmc()); // 设置用户组名
		tmpjyz.setJsmc(this.findJUESE(jyz.getJsid()).getJsmc());// 设置角色名称
		tmpjyz.setXgrq(df.format(new Date()).toString());
		return baseDao.update(tmpjyz);
	}

	@Override
	public JUESE2YONGHUZU findJUESE2YONGHUZU(String jyz_id) {
		return baseDao.findAll(
				"from JUESE2YONGHUZU where jsyhzid='" + jyz_id + "'",
				JUESE2YONGHUZU.class).get(0);
	}

	@Override
	public List<JUESE2YONGHUZU> findJUESE2YONGHUZUByPageApp(int start,
			int number, HashMap<String, String> params, String order,
			String sort) {
		String hql = "from JUESE2YONGHUZU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jyz_bz") && !"".equals(params.get("jyz_bz"))) {
			String param = "and bz like '%" + params.get("jyz_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_ms") && !"".equals(params.get("jyz_ms"))) {
			String param = "and MS like '%" + params.get("jyz_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_yhzmc")
				&& !"".equals(params.get("jyz_yhzmc"))) {
			String param = "and YHZMC like '%"
					+ params.get("jyz_yhzmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_jsmc")
				&& !"".equals(params.get("jyz_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jyz_jsmc").toString() + "%'";
			params2.add(param);
		}

		System.out
				.println(params.get("jyz_jsmc")
						+ "::::::::::::::::::::::::::::::::::asdfffffffffffffffffffffffffffff");
		List<JUESE2YONGHUZU> list = baseDao.findByPage(hql,
				JUESE2YONGHUZU.class, start, number, params2, order, sort);
		return list;
	}

	@Override
	public int getCountJUESE2YONGHUZU(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JUESE2YONGHUZU where 1=1";
		if (null != params.get("jyz_bz") && !"".equals(params.get("jyz_bz"))) {
			String param = " and bz like '%" + params.get("jyz_bz").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jyz_ms") && !"".equals(params.get("jyz_ms"))) {
			String param = " and MS like '%" + params.get("jyz_ms").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jyz_yhzmc")
				&& !"".equals(params.get("jyz_yhzmc"))) {
			String param = " and YHZMC like '%"
					+ params.get("jyz_yhzmc").toString() + "%'";
			hql += param;
		}
		if (null != params.get("jyz_jsmc")
				&& !"".equals(params.get("jyz_jsmc"))) {
			String param = " and jsmc like '%"
					+ params.get("jyz_jsmc").toString() + "%'";
			hql += param;
		}
		System.out.println("统计总数HQL：" + hql);
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<JUESE2YONGHUZU> findJUESE2YONGHUZUByPageApp(
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE2YONGHUZU where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jyz_bz") && !"".equals(params.get("jyz_bz"))) {
			String param = "and bz like '%" + params.get("jyz_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_ms") && !"".equals(params.get("jyz_ms"))) {
			String param = "and MS like '%" + params.get("jyz_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_yhzmc")
				&& !"".equals(params.get("jyz_yhzmc"))) {
			String param = "and YHZMC like '%"
					+ params.get("jy_yhzmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jyz_jsmc")
				&& !"".equals(params.get("jyz_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jyz_jsmc").toString() + "%'";
			params2.add(param);
		}
		List<JUESE2YONGHUZU> list = baseDao.findAll(hql, JUESE2YONGHUZU.class);
		return list;
	}

	// 角色2模块相关
	@Override
	public boolean addJUESE2MOKUAI(JUESE2MOKUAI jm) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		jm.setDjrq(df.format(new Date()).toString());
		jm.setXgrq(df.format(new Date()).toString());
		jm.setMkmc(this.findMOKUAI(jm.getMkid()).getMkmc());// 加入模块名称
		jm.setMkurl(this.findMOKUAI(jm.getMkid()).getMkurl());// 模块url
		jm.setJsmc(this.findJUESE(jm.getJsid()).getJsmc());// 角色名称
		return baseDao.save(jm);
	}

	@Override
	public boolean delJUESE2MOKUAI(String jm_id) {
		String[] ids = jm_id.split(",");
		for (String id : ids) {
			if (null != id || !"".equals(id)) {
				JUESE2MOKUAI tmp = findJUESE2MOKUAI(id);
				if (!baseDao.delete(tmp)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean updateJUESE2MOKUAI(JUESE2MOKUAI jm) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		JUESE2MOKUAI tmpjm = baseDao.findAll(
				"from JUESE2MOKUAI where jsmkid='" + jm.getJsmkid() + "'",
				JUESE2MOKUAI.class).get(0);
		tmpjm.setJsid(jm.getJsid());
		tmpjm.setMkid(jm.getMkid());
		tmpjm.setMs(jm.getMs());
		tmpjm.setBz(jm.getBz());
		tmpjm.setCanfq(jm.getCanfq());
		tmpjm.setMkmc(this.findMOKUAI(jm.getMkid()).getMkmc()); // 设置模块名称
		tmpjm.setMkurl(this.findMOKUAI(jm.getMkid()).getMkurl()); // 设置模块URL
		tmpjm.setJsmc(this.findJUESE(jm.getJsid()).getJsmc());// 设置角色名称
		tmpjm.setXgrq(df.format(new Date()).toString());
		return baseDao.update(tmpjm);
	}

	@Override
	public JUESE2MOKUAI findJUESE2MOKUAI(String jm_id) {
		return baseDao.findAll(
				"from JUESE2MOKUAI where jsmkid='" + jm_id + "'",
				JUESE2MOKUAI.class).get(0);
	}

	@Override
	public List<JUESE2MOKUAI> findJUESE2MOKUAIByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE2MOKUAI where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jm_bz") && !"".equals(params.get("jm_bz"))) {
			String param = "and bz like '%" + params.get("jm_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jm_ms") && !"".equals(params.get("jm_ms"))) {
			String param = "and MS like '%" + params.get("jm_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jm_mkmc") && !"".equals(params.get("jm_mkmc"))) {
			String param = "and MKMC like '%"
					+ params.get("jm_mkmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jm_jsmc") && !"".equals(params.get("jm_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jm_jsmc").toString() + "%'";
			params2.add(param);
		}
		List<JUESE2MOKUAI> list = baseDao.findByPage(hql, JUESE2MOKUAI.class,
				start, number, params2, order, sort);
		return list;
	}

	@Override
	public int getCountJUESE2MOKUAI(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from JUESE2MOKUAI where 1=1";
		if (null != params.get("jm_bz") && !"".equals(params.get("jm_bz"))) {
			String param = " and bz like '%" + params.get("jm_bz").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jm_ms") && !"".equals(params.get("jm_ms"))) {
			String param = " and MS like '%" + params.get("jm_ms").toString()
					+ "%'";
			hql += param;
		}
		if (null != params.get("jm_mkmc") && !"".equals(params.get("jm_mkmc"))) {
			String param = " and MKMC like '%"
					+ params.get("jm_mkmc").toString() + "%'";
			hql += param;
		}
		if (null != params.get("jm_jsmc") && !"".equals(params.get("jm_jsmc"))) {
			String param = " and jsmc like '%"
					+ params.get("jm_jsmc").toString() + "%'";
			hql += param;
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	@Override
	public List<JUESE2MOKUAI> findJUESE2MOKUAIByPageApp(
			HashMap<String, String> params, String order, String sort) {
		String hql = "from JUESE2MOKUAI where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("jm_bz") && !"".equals(params.get("jm_bz"))) {
			String param = "and bz like '%" + params.get("jm_bz").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jm_ms") && !"".equals(params.get("jm_ms"))) {
			String param = "and MS like '%" + params.get("jm_ms").toString()
					+ "%'";
			params2.add(param);
		}
		if (null != params.get("jm_mkmc") && !"".equals(params.get("jm_mkmc"))) {
			String param = "and MKMC like '%"
					+ params.get("jm_mkmc").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("jm_jsmc") && !"".equals(params.get("jm_jsmc"))) {
			String param = "and jsmc like '%"
					+ params.get("jm_jsmc").toString() + "%'";
			params2.add(param);
		}
		List<JUESE2MOKUAI> list = baseDao.findAll(hql, JUESE2MOKUAI.class);
		return list;
	}
}
