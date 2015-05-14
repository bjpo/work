package com.hrbsys.fileUpload.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.MOBAN;
import com.hrbsys.bean.UploadFile;
import com.hrbsys.bean.XUEYUAN;

/**
 * 上传文件实现类
 * 
 * @author Administrator
 * 
 */
public class FileUploadImpl implements FileUploadService {

	// 引入操作数据库的工具类
	private BaseDao baseDao;

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/***
	 * 将数据库中的上传文件表中的数据全部读取出来并进行分页与排序
	 */
	@Override
	public List<UploadFile> findFileUploadByPage(int start, int number,
			HashMap<String, String> params, String order, String sort) {
		// 查询hql语句
		String hql = "from UploadFile where 1=1";

		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("fileName")
				&& !"".equals(params.get("fileName"))) {
			String param = " and FILENAME like '%" + params.get("fileName")
					+ "%'";
			params2.add(param);
		}
		List<UploadFile> list = baseDao.findByPage(hql, UploadFile.class,
				start, number, params2, order, sort);
		return list;
	}

	@Override
	public UploadFile findUploadFileById(String fu_id) {
		return baseDao.findAll("from UploadFile where fileId='" + fu_id + "'",
				UploadFile.class).get(0);
	}

	/**
	 * 向数据库中添加上传文件
	 */
	@Override
	public boolean addFileUpload(UploadFile fu) {
		return baseDao.save(fu);
	}

	/**
	 * 删除上传文件
	 */
	@Override
	public boolean delUploadFile(String uf_id) {
		// 截取前台传来的文件名
		String[] ids = uf_id.split(",");
		// 进行遍历文件的名字，进行删除
		for (String id : ids) {
			UploadFile uf = findUploadFileById(id);
			if (!baseDao.delete(uf)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取数据的总条数
	 */
	@Override
	public int getCount(HashMap<String, String> params) {
		String hql = "SELECT COUNT(*) from UploadFile where 1=1";
		if (null != params.get("fileName")
				&& !"".equals(params.get("fileName"))) {
			hql += "and FILENAME like '%" + params.get("fileName").toString()
					+ "%'";
		}
		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)
				.get(0).toString());
		return count;
	}

	public List<UploadFile> findFileUploadByPageApp(
			HashMap<String, String> params, String order, String sort) {
		String hql = "from UploadFile where 1=1";
		ArrayList<String> params2 = new ArrayList<String>();
		if (null != params.get("fileName")
				&& !"".equals(params.get("fileName"))) {
			String param = "and FILENAME like '%"
					+ params.get("fileName").toString() + "%'";
			params2.add(param);
		}
		List<UploadFile> list = baseDao.findAll(hql, UploadFile.class);
		return list;
	}

	/******************** 将excel文件导入的状态存储到数据库中 *********************/
	public boolean updataFileImportStatus(UploadFile fu) {
		String hql = " from UploadFile where fileId='" + fu.getFileId() + "'";
		UploadFile uf = (UploadFile) baseDao.findAll(hql, UploadFile.class)
				.get(0);
		uf.setImportDataStatus(fu.getImportDataStatus());
		return baseDao.update(uf);
	}
	/**查询出字段表中的所有与mb_id相对应的字段*/
	@Override
	public boolean insertSql(String sql) {
		return baseDao.executeDoSQL(sql);
	}
}
