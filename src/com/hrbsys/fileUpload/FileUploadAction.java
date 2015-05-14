package com.hrbsys.fileUpload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.tools.JsonPrintTools;
import com.opensymphony.xwork2.ActionSupport;

/****************************** 处理文件上传的 Action类 *****************************************/
public class FileUploadAction extends ActionSupport {
	private static final int BUFFER_SIZE = 16 * 1024;
	// 文件标题
	private String title;
	// 上传文件域对象
	private File upload;
	// 上传文件名
	private String uploadFileName;
	// 上传文件类型
	private String uploadContentType;
	// 保存文件的目录路径 ( 通过依赖注入 )
	private String savePath;
	// 设置存储的盘符
	private String drive;
	// 显示读取文件的名字
	private String showFileName;
	// 显示读取文件的类型
	private String showFileType;

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

	/************************************************ 上传调用的Action方法 ************************/
	public String fileUpload() throws Exception {
		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		String dstPath = drive + File.separator + this.getSavePath()
				+ File.separator + this.getUploadFileName();
		File dstFile = new File(dstPath);
		copy(this.upload, dstFile);
		return "success";
	}

	/*************************************** 将上传文件中的文件显示到前台 ***************************/
	public void showFileList() {
		// 设置默认的路径
		File myFile = new File(drive + File.separator + this.getSavePath());// 操作路径,可以有外部参数决定的
		print(myFile);
	}

	/************************************** 将文件夹中的文件遍历出来 ***********************/
	public void print(File file) {
		List list = new ArrayList();
		JsonConfig config = new JsonConfig();
		if (file != null) {
			// 如果是目录 if
			if (file.isDirectory()) {
				// 列出全部的文件
				File[] allFile = file.listFiles();
				for (int i = 0; i < allFile.length; i++) {
					Map<String, Object> jsonMap = new HashMap<String, Object>();
					// 读取文件的名字
					showFileName = allFile[i].getName();
					// 读取文件的类型
					showFileType = showFileName.substring(
							showFileName.lastIndexOf('.'),
							showFileName.length());
					// 向jsonMap中添加数据
					jsonMap.put("showFileName", showFileName);
					jsonMap.put("showFileType", showFileType);
					list.add(jsonMap);
					// 因为给的路径有可能是目录，所以，继续判断
					// print(allFile[i]);
				}
			}
		}
		// 转换json
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/****************************** get和set方法 ******************************************/
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
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
}
