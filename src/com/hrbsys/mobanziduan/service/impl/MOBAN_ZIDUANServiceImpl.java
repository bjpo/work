package com.hrbsys.mobanziduan.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.MOBAN_ZIDUAN;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.mobanziduan.service.MOBAN_ZIDUANService;
import com.hrbsys.tools.TeamPrint;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.users.service.impl.UserServiceImpl;

public class MOBAN_ZIDUANServiceImpl implements MOBAN_ZIDUANService {
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	// add
	@Override
	public boolean addMOBAN_ZIDUAN(MOBAN_ZIDUAN y) {
		return baseDao.save(y);
	}

	// delete
	@Override
	public boolean delMOBAN_ZIDUAN(String y) {
		String[] ids = y.split(",");
		for (String id : ids) {
			MOBAN_ZIDUAN yhz = findMOBAN_ZIDUAN(id);
			if (!baseDao.delete(yhz)) {
				return false;
			}
		}
		return true;
	}

	// update
	@Override
	public boolean updateMOBAN_ZIDUAN(MOBAN_ZIDUAN t) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		MOBAN_ZIDUAN tmpt = baseDao.findAll(
				"from MOBAN_ZIDUAN where ZIDUAN_ID='" + t.getZIDUAN_ID() + "'",
				MOBAN_ZIDUAN.class).get(0);
		tmpt.setLIE(t.getLIE());
		tmpt.setZIDUAN_ID(t.getZIDUAN_ID());
		tmpt.setMB_ID(t.getMB_ID());
		tmpt.setZIDUANMC(t.getZIDUANMC());
		return baseDao.update(tmpt);
	}

	// findById
	@Override
	public MOBAN_ZIDUAN findMOBAN_ZIDUAN(String t_id) {
		return baseDao.findAll(
				"from MOBAN_ZIDUAN where ZIDUAN_ID='" + t_id + "'",
				MOBAN_ZIDUAN.class).get(0);
	}

	// findByPage
	@Override
	public List<MOBAN_ZIDUAN> findMOBAN_ZIDUANByPageApp(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		String hql = "from MOBAN_ZIDUAN where 1=1 ";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("LIE") && !"".equals(params.get("LIE"))) {
			param = "and LIE like '%" + params.get("LIE").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZIDUAN_ID")
				&& !"".equals(params.get("ZIDUAN_ID"))) {
			param = "and ZIDUAN_ID like '%"
					+ params.get("ZIDUAN_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("MB_ID") && !"".equals(params.get("MB_ID"))) {
			param = "and MB_ID like '%" + params.get("MB_ID").toString() + "%'";
			params2.add(param);
			param = "";
		}
		if (null != params.get("ZIDUANMC")
				&& !"".equals(params.get("ZIDUANMC"))) {
			param = "and ZIDUANMC like '%" + params.get("ZIDUANMC").toString()
					+ "%'";
			params2.add(param);
			param = "";
		}
		List<MOBAN_ZIDUAN> list = baseDao.findByPage(hql, MOBAN_ZIDUAN.class,
				start, number, params2, order, sort);
		return list;
	}

	// getCount
	@Override
	public int getCountMOBAN_ZIDUAN(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from MOBAN_ZIDUAN where 1=1 ";
		if (null != params.get("LIE") && !"".equals(params.get("LIE"))) {
			hql += "and LIE like '%" + params.get("LIE").toString() + "%'";
		}
		if (null != params.get("ZIDUAN_ID")
				&& !"".equals(params.get("ZIDUAN_ID"))) {
			hql += "and ZIDUAN_ID like '%" + params.get("ZIDUAN_ID").toString()
					+ "%'";
		}
		if (null != params.get("MB_ID") && !"".equals(params.get("MB_ID"))) {
			hql += "and MB_ID like '%" + params.get("MB_ID").toString() + "%'";
		}
		if (null != params.get("ZIDUANMC")
				&& !"".equals(params.get("ZIDUANMC"))) {
			hql += "and ZIDUANMC like '%" + params.get("ZIDUANMC").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	// findbypagelike
	@Override
	public List<MOBAN_ZIDUAN> findMOBAN_ZIDUANByPageApp(
			HashMap<String, String> params, String order, String sort) {
		String hql = "from MOBAN_ZIDUAN where 1=1";
		String param = "";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("LIE") && !"".equals(params.get("LIE"))) {
			param = "and LIE like '%" + params.get("LIE").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZIDUAN_ID")
				&& !"".equals(params.get("ZIDUAN_ID"))) {
			param = "and ZIDUAN_ID like '%"
					+ params.get("ZIDUAN_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("MB_ID") && !"".equals(params.get("MB_ID"))) {
			param = "and MB_ID like '%" + params.get("MB_ID").toString() + "%'";
			params2.add(param);
		}
		if (null != params.get("ZIDUANMC")
				&& !"".equals(params.get("ZIDUANMC"))) {
			param = "and ZIDUANMC like '%" + params.get("ZIDUANMC").toString()
					+ "%'";
			params2.add(param);
		}
		List<MOBAN_ZIDUAN> list = baseDao.findAll(hql, MOBAN_ZIDUAN.class);
		return list;
	}

	/** 根据模板的ID号在MOBAN_ZIDUAN表查询出所有的字段 */
	public List<MOBAN_ZIDUAN> getMOBAN_ZIDUANList(String sql) {
		return baseDao.executeSQL(sql);
	}

	@Override
	public boolean importExccelToDB(String drive, String savePath, String mbid,
			String excelFileName) {
		try {
			Workbook wb = null;
			Sheet sheet = null;
			Row row = null;
			File myFile = null;
			myFile = new File(drive + File.separator + savePath
					+ File.separator
					+ URLDecoder.decode(excelFileName, "UTF-8"));
			// 调用book方法进行判断excel文件的版本
			wb = book(myFile, excelFileName);
			// 获取第一个Shheet页
			sheet = wb.getSheetAt(0);
			// 判断第一个Shheet页是还是空的，如果为空返回
			if (sheet == null) {
				TeamPrint.println(MOBAN_ZIDUANServiceImpl.class,
						"importExccelToDB", "语句", "sheet为空，不进行导入");
				return false;
			}
			String sql = "select b.mb_id,a.ziduan_id,b.mb_name,b.biaoming,a.ziduanmc,a.lie,b.ZHUJIAN,b.ISCREATEUSER,b.inserType,b.user_column from moban_ziduan a ,moban b where a.mb_id=b.mb_id and a.mb_id='"
					+ mbid + "'";
			TeamPrint.println(MOBAN_ZIDUANServiceImpl.class,
					"importExccelToDB", "SQL语句", sql);
			// 根据模板ID进行查询字段，将其放入到集合中
			List list = getMOBAN_ZIDUANList(sql);
			HashMap<String, String> map = new HashMap<String, String>();

			// 循环模板中的字段
			for (int i = 0; i < list.size(); i++) {
				Object[] mohan_ziduans = (Object[]) list.get(i);
				String sql_mb_id = (null == mohan_ziduans[0]) ? ""
						: mohan_ziduans[0].toString();// 模板ID
				String sql_ziduan_id = (null == mohan_ziduans[1]) ? ""
						: mohan_ziduans[1].toString();// 模板字段ID
				String sql_mbmc = (null == mohan_ziduans[2]) ? ""
						: mohan_ziduans[2].toString();// 模板名称
				String sql_biaoming = (null == mohan_ziduans[3]) ? ""
						: mohan_ziduans[3].toString();// 表名
				String sql_ziduanmc = (null == mohan_ziduans[4]) ? ""
						: mohan_ziduans[4].toString();// 字段名称
				String sql_lie = (null == mohan_ziduans[5]) ? ""
						: mohan_ziduans[5].toString();// 列
				String sql_zhujian = (null == mohan_ziduans[6]) ? ""
						: mohan_ziduans[6].toString();// 主键
				String sql_ISCREATEUSER = (null == mohan_ziduans[7]) ? ""
						: mohan_ziduans[7].toString();// 是否创建相应的用户
				String sql_insertType = (null == mohan_ziduans[8]) ? ""
						: mohan_ziduans[8].toString();// 创建用户的类型
				String sql_user_column = (null == mohan_ziduans[9]) ? ""
						: mohan_ziduans[9].toString();// 创建用户的类型
				System.out.println("sql_ISCREATEUSERsql_ISCREATEUSER---"+sql_ISCREATEUSER);
				System.out.println("sql_insertTypesql_insertType--"+sql_insertType);
				if (i == 0) {// 第一行赋值即可
					map.put("dr_biaoming", sql_biaoming);// 表名
					map.put("dr_zhujian", sql_zhujian);// 主键名称
					map.put("ISCREATEUSER", sql_ISCREATEUSER);// 是否创建用户
					map.put("insertType", sql_insertType);// 创建用户的类型
					map.put("user_name", sql_user_column);// 用户名对应的列
					map.put("user", sql_ziduanmc);
				}
				map.put(sql_lie, sql_ziduanmc);
			}
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////
			sheet = wb.getSheetAt(0);
			Row row_title = sheet.getRow(0);// 获取第一行
			int rownum_title = row_title.getPhysicalNumberOfCells();
			HashMap<Integer, String> mapDui = new HashMap<Integer, String>(); // 列数对应的字段值
			for (int tmpi = 0; tmpi < rownum_title; tmpi++) {
				String cellval = row_title.getCell(tmpi).getStringCellValue();
				if (null != map.get(cellval)) {
					mapDui.put(tmpi, map.get(cellval));
				}
			}
			String sql3 = ")";
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				int colnum = row.getPhysicalNumberOfCells();
				String tmpsql1 = "insert into " + map.get("dr_biaoming") + "("+ map.get("dr_zhujian");
				String tmpsql2 = ") values('" + UUIDTools.getUUID() + "'";
				for (int a = 0; a < colnum + 1; a++) {
					if (null != mapDui.get(a)) { // 如果这一列有对应关系
						tmpsql1 += "," + mapDui.get(a);
						if (row.getCell(a)!=null) {
							//将读取的数据类型改为String 
							row.getCell(a).setCellType(Cell.CELL_TYPE_STRING);
							tmpsql2 += ",'" + row.getCell(a) + "'";
						}
						// 判断是否创建相应的用户 0：创建用户 1：不创建用户
						if (map.get("ISCREATEUSER").equals("0")) {
							// 判断创建用户的类型
							if (mapDui.get(a).equalsIgnoreCase(map.get("user"))) {
								YONGHU yh = new YONGHU();
								yh.setYhid(UUIDTools.getUUID());
								yh.setYhm(String.valueOf(row.getCell(a)));
								yh.setYhmm(String.valueOf(row.getCell(a)));
								YONGHUZU z = new YONGHUZU();
								z.setYhzid(map.get("insertType"));
								yh.setYonghuzu(z);
								// 进行保存
								baseDao.save(yh);
							}
						}
					}
				}
				String tmpsql = tmpsql1 + tmpsql2 + sql3;
				TeamPrint.println(MOBAN_ZIDUANServiceImpl.class,
						"importExccelToDB", "SQL语句", tmpsql);
				baseDao.executeDoSQL(tmpsql);
			}
			// ///////////////////////////////////////////////////////////////////////////////////////////////////////
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 判断excel文件的版本
	public static Workbook book(File file, String excelFileName) {
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		// 读取外部的文件
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		/* 进行版本的判断 */
		if (excelFileName.endsWith(".xls")) {
			// 这excel文件是2003的情况下
			// 创建文件系统
			POIFSFileSystem pfs = null;
			try {
				pfs = new POIFSFileSystem(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 读取excel文件分析Excel文件中的数据
			try {
				wb = new HSSFWorkbook(pfs);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			OPCPackage op = null;
			try {
				op = OPCPackage.open(is);
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 这是文件版是excel2007+的情况下
			try {
				wb = new XSSFWorkbook(op);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			// 关闭io流
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wb;
	}

	public static void main(String[] args) {
		try {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("dr_biaoming", "XSXX");
			map.put("dr_zhujian", "XS_ID");
			map.put("titA", "XH");
			map.put("titB", "ZSXM");
			map.put("titC", "XB");
			map.put("titD", "SFZHM");
			Workbook wb = null;
			Sheet sheet = null;
			Row row = null;
			File myFile = null;
			myFile = new File("D:\\tmp\\demo1.xlsx");
			wb = book(myFile, "demo1.xlsx");
			sheet = wb.getSheetAt(0);
			Row row_title = sheet.getRow(0);// 获取第一行
			int rownum_title = row_title.getPhysicalNumberOfCells();
			HashMap<Integer, String> mapDui = new HashMap<Integer, String>(); // 列数对应的字段值
			for (int tmpi = 0; tmpi < rownum_title; tmpi++) {
				String cellval = row_title.getCell(tmpi).getStringCellValue();
				if (null != map.get(cellval)) {
					System.out.println(cellval + " --> " + map.get(cellval));
					mapDui.put(tmpi, map.get(cellval));
				}
			}
			String sql3 = ")";
			for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
				row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				int colnum = row.getPhysicalNumberOfCells();
				String tmpsql1 = "insert into " + map.get("dr_biaoming") + "("
						+ map.get("dr_zhujian");
				String tmpsql2 = ") values('" + UUIDTools.getUUID() + "'";
				for (int a = 0; a < colnum + 1; a++) {
					if (null != mapDui.get(a)) { // 如果这一列有对应关系
						tmpsql1 += "," + mapDui.get(a);
						tmpsql2 += ",'" + row.getCell(a) + "'";
					}
				}
				String tmpsql = tmpsql1 + tmpsql2 + sql3;
				System.out.println(tmpsql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}