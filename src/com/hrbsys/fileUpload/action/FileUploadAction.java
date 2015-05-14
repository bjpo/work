package com.hrbsys.fileUpload.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.UploadFile;
import com.hrbsys.fileUpload.service.FileUploadService;
import com.hrbsys.mobanziduan.service.MOBAN_ZIDUANService;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
public class FileUploadAction extends ActionBase {
	private MOBAN_ZIDUANService mbzdService;
	/***************************************************** 上传文件接口 *************/
	private FileUploadService fileUploadService;
	protected File excel;// 导入Excel文件
	private static final int BUFFER_SIZE = 16 * 1024;
	private JSONObject result;
	private String uploadFileName;// 上传文件名
	private File upload;// 上传文件域对象
	private String savePath;// 保存文件的目录路径 ( 通过依赖注入 )
	private String drive;// 设置存储的盘符
	private String showFileName;// 显示读取文件的名字
	private String showFileType;// 显示读取文件的类型
	private String delFileName;// 删除时用的名字（前台传过来的）
	private String fileName;// 文件的名称
	private String fileType;// 文件的类型
	private String fileId;// 文件的编号
	private String fileFullPath;// 上传文件的全路径
	/************************************** 向数据库中插入Excel文件中的数据 ******************/
	private String comBoBoxId;// 下拉列表框中传过来的ID值
	private String importFileId;// 要导入文件的ID
	private String importFileName;// 要导入文件的名字
	private String updateTime;// 上传时间
	private String order;// 排序相关
	private String sort;
	private String rows;// 每页显示的记录数
	private String page;// 当前页码(第几页)
	private String importDataStatus;// 文件是否导入到数据库 0代表没有导入到数据库 1代表已经导入到数据库中了
	private String MB_ID;// 模板的id
	private String MB_NAME;// 模板的名字
	private String BIAOMING;// 要将数据导入的表的名字
	private String toMoBanId;// 前台传过来的模板ID
	/************************************************ 上传调用的Action方法 ************************/
	public void fileUpload() throws Exception {
		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		String dstPath = drive + File.separator + this.getSavePath()+ File.separator + this.getUploadFileName();
		File dstFile = new File(dstPath);
		// 定义map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (this.getUploadFileName() == null) {
			jsonMap.put("success", false);
			jsonMap.put("message", "添加上传文件失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		} else {
			// 设置上传文件的时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 将数据放入到对象中去
			UploadFile uf = new UploadFile();
			uf.setFileId(UUIDTools.getUUID());
			uf.setFileFullPath(dstPath);
			uf.setFileName(this.getUploadFileName());
			uf.setFileType(this.getUploadFileName().substring(this.getUploadFileName().lastIndexOf('.'),this.getUploadFileName().length()));
			uf.setUpdateTime(String.valueOf(df.format(new Date())));
			uf.setImportDataStatus(String.valueOf(0));// 文件中的数据是否已经导入，将其置为0
			// 判断是否上传成功，是否成功给予提示信息
			if (fileUploadService.addFileUpload(uf)) {
				// 将上传文件拷贝到指写目录下
				copy(this.upload, dstFile);
				jsonMap.put("success", true);
				jsonMap.put("message", "文件上传成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "文件上传失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	/*************************************** 将上传文件中的文件显示到前台 ***************************/
	public void showFileList() {
		// 存放数据对象的集合
		HashMap<String, String> params = new HashMap<String, String>();
		// 进行分页
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("fileName", this.fileName);

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		List<UploadFile> list = fileUploadService.findFileUploadByPage(start,
				number, params, order, sort);
		// 统计出总的页数
		jsonMap.put("total", fileUploadService.getCount(params));
		// rows键 存放每页记录 list
		jsonMap.put("rows", list);
		// 当前页
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		// 转换json
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));

	}

	/******************* 删除上传文件 ************************************/
	public void delUploadFile() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		if ((null != fileId) && !"".equals(fileId)) {
			if (fileUploadService.delUploadFile(fileId)) {
				/**
				 * 数据库中的文件删除成功之后，在将存储在本地的文件删除
				 */
				// 截取前台传来的文件名
				String[] names = this.getDelFileName().split(",");
				// 进行遍历文件的名字，进行删除
				for (String name : names) {
					// 拼接的路径加上文件的名字,URLDecoder对前台传过来的值进行解析
					File myFile = new File(drive + File.separator+ this.getSavePath() + File.separator+ URLDecoder.decode(name, "UTF-8"));
					// 判断文件存不存在
					if (myFile.exists()) {
						// 文件如果存在就删除
						myFile.delete();
					}
				}
				jsonMap.put("success", true);
				jsonMap.put("message", "文件删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "文件删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}

	}

	/********************************
	 * 通过Excel文件向数据库中导入数据
	 * 
	 * @throws InvalidFormatException
	 * @throws UnsupportedEncodingException
	 ***********************/
	public void importDataToDB() throws InvalidFormatException,UnsupportedEncodingException {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		if(null==this.getToMoBanId()||"".equals(this.getToMoBanId())){
			log.info("模板ID为空...");
			return ;
		}
		if(null==this.getImportFileName()||"".equals(this.getImportFileName())){
			log.info("要导入的文件为空...");
			log.info(":::"+this.getImportFileName());
			return ;
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boolean issuccess=mbzdService.importExccelToDB(drive, savePath, this.getToMoBanId(),this.getImportFileName());
		if(issuccess){
			log.info("导入成功...");
			jsonMap.put("success", true);
			jsonMap.put("message", "文件导入成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		}else{
			log.info("导入失败...");
			jsonMap.put("success", false);
			jsonMap.put("message", "文件导入失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		}
	}



	// 自己封装的一个把源文件对象复制成目标文件对象
	private static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/****************************** get和set方法 ******************************************/

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public String getDelFileName() {
		return delFileName;
	}

	public void setDelFileName(String delFileName) {
		this.delFileName = delFileName;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getShowFileName() {
		return showFileName;
	}

	public void setShowFileName(String showFileName) {
		this.showFileName = showFileName;
	}

	public String getShowFileType() {
		return showFileType;
	}

	public void setShowFileType(String showFileType) {
		this.showFileType = showFileType;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public FileUploadService getFileUploadService() {
		return fileUploadService;
	}

	public void setFileUploadService(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	public String getComBoBoxId() {
		return comBoBoxId;
	}

	public void setComBoBoxId(String comBoBoxId) {
		this.comBoBoxId = comBoBoxId;
	}

	public String getImportFileId() {
		return importFileId;
	}

	public void setImportFileId(String importFileId) {
		this.importFileId = importFileId;
	}

	public String getImportFileName() {
		return importFileName;
	}

	public void setImportFileName(String importFileName) {
		this.importFileName = importFileName;
	}

	// 封装要上传的文件
	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getImportDataStatus() {
		return importDataStatus;
	}

	public void setImportDataStatus(String importDataStatus) {
		this.importDataStatus = importDataStatus;
	}

	public String getMB_ID() {
		return MB_ID;
	}

	public void setMB_ID(String mB_ID) {
		MB_ID = mB_ID;
	}

	public String getMB_NAME() {
		return MB_NAME;
	}

	public void setMB_NAME(String mB_NAME) {
		MB_NAME = mB_NAME;
	}

	public String getBIAOMING() {
		return BIAOMING;
	}

	public void setBIAOMING(String bIAOMING) {
		BIAOMING = bIAOMING;
	}

	public String getToMoBanId() {
		return toMoBanId;
	}

	public void setToMoBanId(String toMoBanId) {
		this.toMoBanId = toMoBanId;
	}

	public MOBAN_ZIDUANService getMbzdService() {
		return mbzdService;
	}
	public void setMbzdService(MOBAN_ZIDUANService mbzdService) {
		this.mbzdService = mbzdService;
	}


}
