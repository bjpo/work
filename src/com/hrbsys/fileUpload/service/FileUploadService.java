package com.hrbsys.fileUpload.service;

import java.util.HashMap;
import java.util.List;

import com.hrbsys.bean.MOBAN;
import com.hrbsys.bean.UploadFile;
import com.hrbsys.bean.XUEYUAN;

public interface FileUploadService
{

	/* 查询所有的上传文件并进行分页和排序的接口 */
	public List<UploadFile> findFileUploadByPage(int start, int number,
			HashMap<String, String> params, String order, String sort);

	/* 根据上传文件的ID进行查询的接口 */
	public UploadFile findUploadFileById(String fu_id);

	/* 删除学院的接口 */
	public boolean delUploadFile(String uf_id);

	/* 获取数据的条目数 */
	public int getCount(HashMap<String, String> params);

	/* 添加上传文件的接口 */
	public boolean addFileUpload(UploadFile fu);

	public List<UploadFile> findFileUploadByPageApp(
			HashMap<String, String> params, String order, String sort);
	/*修改文件导入的状态*/
	public boolean updataFileImportStatus(UploadFile fu);
	
	public boolean insertSql(String sql);
}
