package com.hrbsys.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 上传文件类
 */
@Entity
@Table(name="FILEUPLOAD")
public class UploadFile {
	@Id
	@Column(name="FILE_ID")
	private String fileId;//文件的id编号
	@Column(name="FILENAME")
	private String fileName;// 文件的名称
	@Column(name="FILETYPE")
	private String fileType;// 文件的类型
	@Column(name="FILEFULLPATH")
	private String fileFullPath;//文件的存放路径
	@Column(name="FILEUPLOADTIME")
	private String updateTime; // 文件的上传时间
	@Column(name="IMPORTDATASTATUS")
	private String importDataStatus;//文件导入的状态，文件是否以导入到数据库中

	/********************************* get和set方法 **************/
	public String getImportDataStatus()
	{
		return importDataStatus;
	}

	public void setImportDataStatus(String importDataStatus)
	{
		this.importDataStatus = importDataStatus;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileFullPath() {
		return fileFullPath;
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
