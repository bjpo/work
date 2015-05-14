package com.hrbsys.luzhiwen.action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URLEncoder;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 指纹录入--插件下载
 * 
 * @author admin
 * 
 */
public class FileDownloadAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	// 基本设置类型
	private String contentType;
	private String contentDisposition;

	// 输入流
	private InputStream inputStream;
	// 下载路径
	private String inputPath;
	// 默认执行的方法
	public String execute() throws Exception {
			// 读取要下载文件的路径
			inputStream = new FileInputStream(inputPath);
              return SUCCESS;
	}

	/**************** get和set方法 *****************/

	public String getContentType() {
		return contentType;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public InputStream getInputStream() throws Exception{
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) throws Exception {
		this.inputStream=inputStream;
	}

}
