/*数据库为ORACLE 10G*/
/***********************************************************************系统需要的基础表*/
/*用户表：*/
drop table yonghu;
create table yonghu(
	YH_ID VARCHAR2(36) primary key,
	YHZ_ID VARCHAR2(36),
	YHM VARCHAR2(50),
	YHMM VARCHAR2(50),
	FK_YHZL VARCHAR2(36),
	FK_YHZLLX VARCHAR2(36),
	djrq varchar2(500),
	xgrq varchar2(500)
);
drop table yhz;
/*用户组*/
create table yhz(
	YHZ_ID VARCHAR2(36) primary key,
	YHZMC VARCHAR2(50),
	BZ VARCHAR2(500),
	djrq varchar2(500),
	xgrq varchar2(500)
);

drop table yonghu_juese;
/*用户与角色关系表*/
create table yonghu_juese(
	YHJS_ID VARCHAR2(36) primary key ,
	yh_id varchar2(36),
	js_id varchar2(36),
	ms varchar2(500),
	bz VARCHAR2(500),
	djrq varchar2(500),
	xgrq varchar2(500),
	yhmc varchar2(500),
	jsmc varchar2(500)
);

drop table juese;
/*角色*/
create table juese(
	js_id varchar2(36) primary key ,
	jsmc varchar2(100),
	bz varchar2(500),
	ms varchar2(500),
	djrq varchar2(500),
	xgrq varchar2(500)
);

drop table yonghuzu_juese;
/*用户组角色关系表*/
create table yonghuzu_juese(
	yhzjs_id varchar2(36)primary key,
	yhzid varchar2(36),
	jsid varchar2(36),
	bz varchar2(500),
	ms varchar2(500),
	djrq varchar2(500),
	xgrq varchar2(500),
	yhzmc varchar2(500),
	jsmc varchar2(500)
);
drop table juesemokuai;
/*角色模块关系表*/
create table juesemokuai(
	JSMK_ID VARCHAR2(36) primary key,
	JS_ID VARCHAR2(36),
	MK_ID VARCHAR2(36),
	BZ VARCHAR2(500),
	MS VARCHAR2(500),
	CANFQ VARCHAR2(36),
	djrq varchar2(500),
	xgrq varchar2(500),
	jsmc varchar2(500),
	mkmc varchar2(500),
	mkurl varchar2(500)
);

drop table mokuai;
/*模块表*/
create table mokuai(
	MK_ID VARCHAR2(36) PRIMARY KEY,
	MKMC VARCHAR2(100),
	MKBM VARCHAR2(100),
	MS VARCHAR2(500),
	BZ VARCHAR2(500),
	MKURL VARCHAR2(500),
	mkYEMIAN VARCHAR2(500),
	ISSHOWINLEFTMENU VARCHAR2(36),
	DJRQ VARCHAR2(500),
	XGRQ VARCHAR2(500),
	OPENSTATE VARCHAR2(50),
	iconcls varchar2(500),
	paixu varchar2(100) default('100001'),
	FK_MK VARCHAR2(36),
	fk_mkmc varchar2(500)
);

/**考勤系统相关基础表************************************************************************************/
/*学期信息表*/
DROP TABLE XUEQI;
CREATE TABLE XUEQI(
	XQ_ID VARCHAR2(36) PRIMARY KEY,/*学期ID*/
	XQMC VARCHAR2(100),/*学期名称*/
	KSRQ VARCHAR2(100),/*学期开始日期*/
	JSRQ VARCHAR2(100),/*学期结束日期*/
	bz  varchar2(500),/*备注*/
	ms varchar2(500)
);
/*学院基本信息表*/
drop table XUEYUAN;
create table XUEYUAN(
  xy_id varchar2(36) PRIMARY KEY,/*学院ID*/
  xydm varchar2(50),
  xymc varchar2(100),/*学院名称*/
  xydz varchar2(500),/*学院地址*/
  yzbm varchar2(100),/*邮政编码*/
  xxxx varchar2(4000), /*详细信息*/
  bz  varchar2(500),/*备注*/
  ms varchar2(500),/*描述*/
  fxy_id varchar2(36),/*上级院系ID,指向自身表xy_id*/
  fxymc varchar2(100),/*上级院系名称*/
  TMP1 VARCHAR2(500),/*备用字段*/
  TMP2 VARCHAR2(500),/*备用字段*/
  TMP3 VARCHAR2(500),/*备用字段*/
  TMP4 VARCHAR2(500),/*备用字段*/
  TMP5 VARCHAR2(500),/*备用字段*/
  TMP6 VARCHAR2(500)/*备用字段*/
);
/*专业基本信息表*/
DROP TABLE ZHUANYE;
CREATE TABLE ZHUANYE(
	ZY_ID VARCHAR2(36) PRIMARY KEY,/*专业ID*/
	ZYDM VARCHAR2(100),/*专业代码*/
	ZYMC VARCHAR2(100),/*专业名称*/
	XYID VARCHAR2(36),/*所在院系ID*/
	XYMC VARCHAR2(100),/*所在院系名称*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*年级信息表*/
drop table NIANJI;
CREATE TABLE NIANJI(
	NJ_ID VARCHAR2(36) PRIMARY KEY,/*年级主键*/
	NJMC VARCHAR2(100),/*年级名称*/
	NJDM VARCHAR2(100),/*年级代码*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*班级信息表*/
DROP TABLE BANJI;
CREATE TABLE BANJI(
	BJ_id varchar2(36) primary key,/*班级主键*/
	BJMC varchar2(100),/*班级名称*/
	BJHM VARCHAR2(100),/*班号*/
	BZK VARCHAR2(100),/*本科、专科、研究生等*/
	NJ_ID VARCHAR2(36),/*年级主键*/
	NJMC VARCHAR2(100),/*年级名称*/
	NJDM VARCHAR2(100),/*年级代码*/
	ZY_ID VARCHAR2(36),/*专业主键*/
	ZYMC VARCHAR2(100),/*专业名称*/
	ZYDM VARCHAR2(100),/*专业代码*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/	
);

/*教学楼信息表*/
drop table JXL;
CREATE TABLE JXL(
	JXL_ID VARCHAR2(36) PRIMARY KEY,/*教学楼ID*/
	JXLMC VARCHAR2(100),/*教学楼名称*/
	JXLDM VARCHAR2(36),/*教学楼代码*/
	JXLLH VARCHAR2(36),/*教学楼楼号*/
	JXLWZ VARCHAR2(500),/*教学楼位置*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*房间信息表*/
DROP TABLE FANGJIAN;
CREATE TABLE FANGJIAN(
	FJ_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	FJDM VARCHAR2(50),/*房间代码*/
	FJMC VARCHAR2(100),/*房间名称*/
	FJDZ VARCHAR2(500),/*房间地址*/
	FJH VARCHAR2(36),/*房间号*/
	LOUCENG INT,/*所在楼层*/
	ISDMT VARCHAR2(36),/*是否多媒体*/
	RENSHU INT,/*可容纳人数*/
	JXL_ID VARCHAR2(36),/*所在教学楼ID*/
	JXL_MC VARCHAR2(100),/*所在教学楼名称*/
	JXLLH VARCHAR2(36),/*所在教学楼楼号*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*教室信息表*/
DROP TABLE JIAOSHI;
CREATE TABLE JIAOSHI(
	JS_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	JSMC VARCHAR2(100),/*教室名称*/
	FJ_ID VARCHAR2(36),/*房间ID*/
	FJMC VARCHAR2(100),/*房间名称*/
	FJDM VARCHAR2(50),/*房间代码*/
	ISDMT VARCHAR2(36),/*是否多媒体*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*课程信息类别表*/
drop table KECHENGXXLB;
CREATE TABLE KECHENGXXLB(
	KECHENGXXLB_ID VARCHAR2(36) PRIMARY KEY ,/*主键*/
	KECHENGXXLBMC VARCHAR2(100),/*课程信息类别名称*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*课程信息表*/
DROP TABLE KECHENGXX;
CREATE TABLE KECHENGXX(
	KECHENGXX_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	KECHENGDM VARCHAR2(50),/*课程信息代码*/
	KECHENGMC VARCHAR2(100),/*课程信息名称*/
	XSFP_LLJX INT,/*学时分配_理论教学*/
	XSFP_SYSJ INT,/*学时分配_实验实践*/
	KSXF INT,/*开设学分*/
	YXXF INT,/*应选学分*/
	KSXQ VARCHAR2(100),/*开设学期*/
	KHFS VARCHAR2(100),/*考核方式*/
	ZY_ID VARCHAR2(36),/*所在专业ID*/
	ZYMC VARCHAR2(100),/*所在专业名称*/
	KECHENGXXLB_ID VARCHAR2(36),/*课程信息类别ID*/
	KECHENGXXLBMC VARCHAR2(100),/*课程信息类别名称*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);

/*课时信息表*/
drop table KESHI;
CREATE TABLE KESHI(
	KS_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	KSMC VARCHAR2(100),/*课时名称*/
	KSSJ VARCHAR2(100),/*开始时间*/
	JSSJ VARCHAR2(100),/*结束时间*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*课程表表*/
DROP TABLE KECHENGB;
CREATE TABLE KECHENGB(
	KCB_ID VARCHAR2(36),/*课程表ID*/
	KCB_FXS_ID VARCHAR2(36) PRIMARY KEY,/*课程表分课时ID*/
	KCXX_ID VARCHAR2(36),/*课程信息ID*/
	KCXXMC VARCHAR2(100),/*课程信息名称*/
	LAOSHI_ID VARCHAR2(36),/*老师ID*/
	LAOSHIMC VARCHAR2(100),/*老师名称*/
	JS_ID VARCHAR2(36),/*教室ID*/
	JSMC VARCHAR2(100),/*教室名称*/
	KS_ID VARCHAR2(36),/*课时信息ID*/
	KSMC VARCHAR2(100),/*课时信息名称*/
	KCBLB VARCHAR2(36),/*课程表类别：专业课、选修课*/
	XINGQI VARCHAR2(100),/*星期*/
	XINGQIXH VARCHAR2(100),/*星期对应的序号*/
	KSZHOU VARCHAR2(100),/*开始周*/
	JSZHOU VARCHAR2(100),/*结束周*/
	djrq varchar2(100);/*等级日期*/
	xgrq varchar2(100);/*修改日期*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*上课班级表：如果是专业课，则上课学生列表到这个关系表中去读取*/
drop table kcb_bj;
create table kcb_bj(
	kcbbj_id varchar2(36) primary key,/*主键*/
	kcb_id varchar2(36),/*课程表ID*/
	bj_id varchar2(36)/*学生ID*/
);

/*课程上课学生信息表*/
drop table skxsxx;
create table skxsxx(
	kcxs_id varchar2(36) primary key,/*主键*/
	kcb_id varchar2(36),/*课程表ID*/
	kcb_fxs_id varchar2(36),/*课程表_分学时ID*/
	kcb_kcxxmc varchar2(100),/*课程信息名称*/
	xs_id varchar2(36),/*学生ID*/
	xsxm varchar2(100),/*学生姓名*/
	xuehao varchar2(100)/*学号*/
);

/*串课信息表*/
drop table ckxx;
create table ckxx(
  ckxx_id varchar2(36) PRIMARY KEY,/*主键*/
  KCB_ID VARCHAR2(36),/*课程表ID*/
  KCB_KCMC VARCHAR2(100),/*课程名称*/
  KS_ID VARCHAR2(36),/*课时ID*/
  KSMC VARCHAR2(100),/*课时名称*/
  XINGQI VARCHAR2(100),/*星期*/
  ZHOU VARCHAR2(100),/*周*/
  LAOSHI_ID VARCHAR2(36),/*老师ID*/
  LAOSHI_XM VARCHAR2(100),/*老师姓名*/
  LAOSHI_GH VARCHAR2(100),/*老师工号*/
  JS_ID VARCHAR2(36),/*教室ID*/
  JSMC varchar2(100),/*教室名称*/
  KCB_ID_CD VARCHAR2(36),/*串到的课程表ID*/
  KCB_KCMC_CD VARCHAR2(100),/*串到的课程名称*/
  KS_ID_CD VARCHAR2(36),/*串到的课时ID*/
  KSMC_CD VARCHAR2(100),/*串到的课时名称*/
  XINGQI_CD VARCHAR2(100),/*串到的星期*/
  ZHOU_CD VARCHAR2(100),/*串到的周*/
  LAOSHI_ID_CD VARCHAR2(36),/*串到的老师ID*/
  LAOSHI_XM_CD VARCHAR2(100),/*串到的老师姓名*/
  LAOSHI_GH_CD VARCHAR2(100),/*串到的老师工号*/
  JS_ID_CD VARCHAR2(36),/*串到的教室ID*/
  JSMC_CD varchar2(100),/*串到的教室名称*/
  BZ VARCHAR2(500),/*备注*/
  MS VARCHAR2(500),/*描述*/
  TMP1 VARCHAR2(500),/*备用字段*/
  TMP2 VARCHAR2(500),/*备用字段*/
  TMP3 VARCHAR2(500),/*备用字段*/
  TMP4 VARCHAR2(500),/*备用字段*/
  TMP5 VARCHAR2(500),/*备用字段*/
  TMP6 VARCHAR2(500)/*备用字段*/
);


/*教工类别表*/
DROP TABLE JIAOGONGLB;
create table JIAOGONGLB(
	JIAOGONGLB_ID VARCHAR2(36) PRIMARY KEY,
	JIAOGONGLBMC VARCHAR2(100),
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*教职员工信息表*/
DROP TABLE JIAOGONG;
create table JIAOGONG(
	JG_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	JGGH VARCHAR2(100),/*教工工号*/
	JGMC VARCHAR2(100),/*教工名称*/
	ZSXM VARCHAR2(100),/*真实姓名*/
	XB VARCHAR2(36),/*性别*/
	CSNY VARCHAR2(100),/*出生年月*/
	MZ VARCHAR2(100),/*民族*/
	BYYX VARCHAR2(200),/*毕业院校*/
	XUELI VARCHAR2(36),/*学历*/
	SHENGAO VARCHAR2(36),/*身高*/
	TIZHONG VARCHAR2(36),/*体重*/
	JTZZ VARCHAR2(500),/*家庭住址*/
	SFZHM VARCHAR2(200),/*身份证号码*/
	ZW_ID VARCHAR2(36),/*指纹ID*/
	ZP_ID VARCHAR2(36),/*照片ID*/
	YH_ID VARCHAR2(36),/*用户ID*/
	JGLB_ID VARCHAR2(36),/*教工类别ID*/
	JGLBMC VARCHAR2(100),/*教工类别名称*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*学生信息表*/
DROP TABLE XSXX;
create table XSXX(
	XS_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	XH VARCHAR2(100),/*学号*/
	ZSXM VARCHAR2(100),/*真实姓名*/
	CYM VARCHAR2(100),/*曾用名*/
	XB VARCHAR2(36),/*性别*/
	SFZHM VARCHAR2(100),/*身份证号码*/
	CSNY VARCHAR2(100),/*出生年月*/
	MZ VARCHAR2(100),/*民族*/
	RTSJ VARCHAR2(200),/*入团时间*/
	CSD VARCHAR2(200),/*出生地*/
	HJLB VARCHAR2(100),/*户籍类别*/
	SUSHEHAO VARCHAR2(200),/*宿舍号*/
	SSDH VARCHAR2(50),/*宿舍电话*/
	GATDM VARCHAR2(100),/*港澳台代码*/
	LXR VARCHAR2(200),/*联系人*/
	YZBM VARCHAR2(100),/*邮政编码*/
	DAHH VARCHAR2(100),/*档案行号*/
	DAYH VARCHAR2(100),/*档案页号*/
	DZYX VARCHAR2(200),/*电子邮箱*/
	LXDZ VARCHAR2(500),/*联系地址*/
	LXDH VARCHAR2(50),/*联系电话*/
	ZSJJ VARCHAR2(100),/*招生季节*/
	ZKZH VARCHAR2(100),/*准考证号*/
	KSH VARCHAR2(100),/*考生号*/
	KSLB VARCHAR2(100),/*考生类别*/
	JKZK VARCHAR2(100),/*健康状况*/
	KSTZ VARCHAR2(100),/*考生特征*/
	RXWHCD VARCHAR2(100),/*入学文化程度*/
	SYSF VARCHAR2(100),/*生源省份*/
	BYLB VARCHAR2(100),/*毕业类别*/
	SYDQ VARCHAR2(100),/*生源地区*/
	KLDM VARCHAR2(100),/*科类代码*/
	RXCJ VARCHAR2(100),/*入学成绩*/
	XXTJ VARCHAR2(100),/*获知学校信息途径*/
	TC VARCHAR2(100),/*特长*/
	RXSJ VARCHAR2(100),/*入学时间*/
	ZY_ID_LQ VARCHAR2(100),/*录取专业*/
	XZ VARCHAR2(100),/*学制*/
	RXNF VARCHAR2(100),/*入学年份*/
	RXFS VARCHAR2(100),/*入学方式*/
	PYLB VARCHAR2(100),/*培养类别*/
	PYDX VARCHAR2(100),/*培养对象*/
	PYCC VARCHAR2(100),/*培养层次*/
	BXFS VARCHAR2(100),/*办学方式*/
	XXNX VARCHAR2(100),/*学习年限*/
	QTBXXS VARCHAR2(100),/*其他办学形式*/
	ZXWYYZ VARCHAR2(100),/*主修外语语种*/
	ZXWYJB VARCHAR2(100),/*主修外语级别*/
	BXLX VARCHAR2(100),/*办学类型*/
	BYZX VARCHAR2(100),/*毕业中学*/
	SG VARCHAR2(100),/*身高*/
	TZ VARCHAR2(100),/*体重*/
	JSJNLDJ VARCHAR2(100),/*主修计算机能力等级*/
	JTZZ VARCHAR2(100),/*家庭住址*/
	YH_ID VARCHAR2(36),/*用户ID*/
	XY_ID VARCHAR2(36),/*所在学院*/
	XYMC varchar2(100),/*学院名称*/
	ZYMC VARCHAR2(100),/*专业名称*/
	NJMC VARCHAR2(100),/*年级名称*/
	BJMC VARCHAR2(100),/*班级名称*/
	ZY_ID VARCHAR2(36),/*所在专业*/
	NJ_ID VARCHAR2(36),/*所在年级*/
	BJ_ID VARCHAR2(36),/*所在班级*/
	ZHIWEN_ID1 VARCHAR2(36),/*指纹表ID1*/
	ZHIWEN_ID2 VARCHAR2(36),/*指纹表ID2*/
	ZHAOPIAN_ID VARCHAR2(36),/*照片*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500),/*描述*/
	TMP1 VARCHAR2(500),/*备用字段*/
	TMP2 VARCHAR2(500),/*备用字段*/
	TMP3 VARCHAR2(500),/*备用字段*/
	TMP4 VARCHAR2(500),/*备用字段*/
	TMP5 VARCHAR2(500),/*备用字段*/
	TMP6 VARCHAR2(500)/*备用字段*/
);
/*指纹信息表*/
drop table ZWXX;
CREATE TABLE ZWXX(
	ZWXX_ID VARCHAR2(36) PRIMARY KEY ,/*主键*/
	XSXX_ID VARCHAR2(36),/*学生信息ID*/
	XSXX_XH VARCHAR2(100),/*学生学号*/
	ZWXXLJ1 VARCHAR2(500),/*指纹1路径*/
	ZWXXLJ2 VARCHAR2(500)/*指纹2路径*/
);
/*照片信息表*/
DROP TABLE ZPXX;
CREATE TABLE ZPXX(
	ZPXX_ID VARCHAR2(36),/*主键*/
	XSXX_ID VARCHAR2(36),/*学生信息ID*/
	XSXX_XH VARCHAR2(100),/*学生学号*/
	ZPLJ VARCHAR2(500)/*照片路径*/
);

/*请假信息表*/
create table qjxx(
	qjxx_id varchar2(36)  primary key,/*主键*/
	XS_ID VARCHAR2(36),/*学生信息表ID*/
	XSXM VARCHAR2(100),/*学生姓名*/
	XSXH VARCHAR2(100),/*学号*/
	QJKSSJ VARCHAR2(100),/*请假开始时间*/
	QJJSJS VARCHAR2(100),/*请假结束时间*/
	DJRQ varchar2(100),/*登记日期*/
	xgrq varchar2(100),/*修改日期*/
	bz varchar2(500),/*备注*/
	ms varchar2(500),/*描述*/
	JG_ID VARCHAR2(36),/*填写学生请假信息的老师教工ID*/
	JGXM VARCHAR2(100),/*填写学生请假信息的老师教工姓名*/
	JGGH VARCHAR2(100)/*填写学生请假信息的老师教工工号*/
);
/*设备信息表*/
drop table sbxx;
create table sbxx(
	sbxx_id varchar2(36),/*设备信息ID*/
	sbmc varchar2(100),/*设备名称*/
	js_id varchar2(36),/*所在教室*/
	jsmc varchar2(100),/*教室名称*/
	ms varchar2(500),/*描述*/
	bz varchar2(500)/*备注*/
);
/*设备交互表*/
create table SBJH(
	SBJH_ID VARCHAR2(36),
	ZW1LJ VARCHAR2(500),
	ZW2LJ VARCHAR2(500),
	ZPLJ VARCHAR2(500),
	CZLX VARCHAR2(100),
	CZRQ VARCHAR2(100)
);

/**需做分区******************************************************************************/
/*考勤信息中间表学生信息表:定时跑的中间表*/
DROP TABLE KQJL_XSJL;
create table KQJL_XSJL(
	KQXSXX_ID VARCHAR2(36),/*主键*/
	JS_ID VARCHAR2(36),/*教室主键*/
	JSMC VARCHAR2(100),/*教室名称*/
	KQSB_ID VARCHAR2(36),/*考勤设备主键*/
	KQSBMC VARCHAR2(100),/*考勤设备名称*/
	SKSJ date,/*上课日期时间*/
	XINGQI VARCHAR2(100),/*星期*/
	ZHOU VARCHAR2(100),/*周*/
	KESHI_ID VARCHAR2(36),/*课时*/
	KSMC VARCHAR2(100),/*课时名称*/
	LS_ID VARCHAR2(36),/*教师ID*/
	LSXM VARCHAR2(100),/*教师姓名*/
	LSGH VARCHAR2(100),/*教师工号*/
	KCB_ID VARCHAR2(36),/*课程表ID*/
	KCB_FKS_ID VARCHAR2(36),/*课程表中分课时ID*/
	KCMC VARCHAR2(100),/*课程名称*/
	KCLB VARCHAR2(100),/*课程类别*/
	XS_ID VARCHAR2(36),/*学生ID*/
	XSXM VARCHAR2(100),/*学生姓名*/
	XSXH VARCHAR2(100),/*学生学号*/
	CQQK VARCHAR2(100),/*出勤情况*/
	skdksj DATE,/*上课打卡时间*/
	xkdksj DATE,/*下课打卡时间*/
	BZ VARCHAR2(500),
	MS VARCHAR2(500)
)
PARTITION   BY   RANGE   (SKSJ)
(
    PARTITION   p201405   VALUES   LESS   THAN   (to_date('2014-06-01', 'yyyy-mm-dd')),
    PARTITION   p201406   VALUES   LESS   THAN   (to_date('2014-07-01', 'yyyy-mm-dd')),
    PARTITION   p201407   VALUES   LESS   THAN   (to_date('2014-08-01', 'yyyy-mm-dd')),
    PARTITION   p201408   VALUES   LESS   THAN   (to_date('2014-09-01', 'yyyy-mm-dd')),
    PARTITION   p201409   VALUES   LESS   THAN   (to_date('2014-10-01', 'yyyy-mm-dd')),
    PARTITION   p201410   VALUES   LESS   THAN   (to_date('2014-11-01', 'yyyy-mm-dd')),
    PARTITION   p201411   VALUES   LESS   THAN   (to_date('2014-12-01', 'yyyy-mm-dd')),
    PARTITION   p201412   VALUES   LESS   THAN   (to_date('2015-01-01', 'yyyy-mm-dd')),
    PARTITION   p201501   VALUES   LESS   THAN   (to_date('2015-02-01', 'yyyy-mm-dd')),
    PARTITION   p201502   VALUES   LESS   THAN   (to_date('2015-03-01', 'yyyy-mm-dd')),
    PARTITION   p201503   VALUES   LESS   THAN   (to_date('2015-04-01', 'yyyy-mm-dd')),
    PARTITION   p201504   VALUES   LESS   THAN   (to_date('2015-05-01', 'yyyy-mm-dd')),
    PARTITION   p201505   VALUES   LESS   THAN   (to_date('2015-06-01', 'yyyy-mm-dd')),
    PARTITION   p201506   VALUES   LESS   THAN   (to_date('2015-07-01', 'yyyy-mm-dd')),
    PARTITION   p201507   VALUES   LESS   THAN   (to_date('2015-08-01', 'yyyy-mm-dd')),
    PARTITION   p201508   VALUES   LESS   THAN   (to_date('2015-09-01', 'yyyy-mm-dd')),
    PARTITION   p201509   VALUES   LESS   THAN   (to_date('2015-10-01', 'yyyy-mm-dd')),
    PARTITION   p201510   VALUES   LESS   THAN   (to_date('2015-11-01', 'yyyy-mm-dd')),
    PARTITION   p201511   VALUES   LESS   THAN   (to_date('2015-12-01', 'yyyy-mm-dd')),
    PARTITION   p201512   VALUES   LESS   THAN   (to_date('2016-01-01', 'yyyy-mm-dd')),
    PARTITION   p201601   VALUES   LESS   THAN   (to_date('2016-02-01', 'yyyy-mm-dd')),
    PARTITION   p201602   VALUES   LESS   THAN   (to_date('2016-03-01', 'yyyy-mm-dd')),
    PARTITION   p201603   VALUES   LESS   THAN   (to_date('2016-04-01', 'yyyy-mm-dd')),
    PARTITION   p201604   VALUES   LESS   THAN   (to_date('2016-05-01', 'yyyy-mm-dd')),
    PARTITION   p201605   VALUES   LESS   THAN   (to_date('2016-06-01', 'yyyy-mm-dd')),
    PARTITION   p201606   VALUES   LESS   THAN   (to_date('2016-07-01', 'yyyy-mm-dd')),
    PARTITION   p201607   VALUES   LESS   THAN   (to_date('2016-08-01', 'yyyy-mm-dd')),
    PARTITION   p201608   VALUES   LESS   THAN   (to_date('2016-09-01', 'yyyy-mm-dd')),
    PARTITION   p201609   VALUES   LESS   THAN   (to_date('2016-10-01', 'yyyy-mm-dd')),
    PARTITION   p201610   VALUES   LESS   THAN   (to_date('2016-11-01', 'yyyy-mm-dd')),
    PARTITION   p201611   VALUES   LESS   THAN   (to_date('2016-12-01', 'yyyy-mm-dd')),
    PARTITION   p201612   VALUES   LESS   THAN   (to_date('2017-01-01', 'yyyy-mm-dd')),
    PARTITION   p201701   VALUES   LESS   THAN   (to_date('2017-02-01', 'yyyy-mm-dd')),
    PARTITION   p201702   VALUES   LESS   THAN   (to_date('2017-03-01', 'yyyy-mm-dd')),
    PARTITION   p201703   VALUES   LESS   THAN   (to_date('2017-04-01', 'yyyy-mm-dd')),
    PARTITION   p201704   VALUES   LESS   THAN   (to_date('2017-05-01', 'yyyy-mm-dd')),
    PARTITION   p201705   VALUES   LESS   THAN   (to_date('2017-06-01', 'yyyy-mm-dd')),
    PARTITION   p201706   VALUES   LESS   THAN   (to_date('2017-07-01', 'yyyy-mm-dd')),
    PARTITION   p201707   VALUES   LESS   THAN   (to_date('2017-08-01', 'yyyy-mm-dd')),
    PARTITION   p201708   VALUES   LESS   THAN   (to_date('2017-09-01', 'yyyy-mm-dd')),
    PARTITION   p201709   VALUES   LESS   THAN   (to_date('2017-10-01', 'yyyy-mm-dd')),
    PARTITION   p201710   VALUES   LESS   THAN   (to_date('2017-11-01', 'yyyy-mm-dd')),
    PARTITION   p201711   VALUES   LESS   THAN   (to_date('2017-12-01', 'yyyy-mm-dd')),
    PARTITION   p201712   VALUES   LESS   THAN   (to_date('2018-01-01', 'yyyy-mm-dd')),
    PARTITION   p201801   VALUES   LESS   THAN   (to_date('2018-02-01', 'yyyy-mm-dd')),
    PARTITION   p201802   VALUES   LESS   THAN   (to_date('2018-03-01', 'yyyy-mm-dd')),
    PARTITION   p201803   VALUES   LESS   THAN   (to_date('2018-04-01', 'yyyy-mm-dd')),
    PARTITION   p201804   VALUES   LESS   THAN   (to_date('2018-05-01', 'yyyy-mm-dd')),
    PARTITION   p201805   VALUES   LESS   THAN   (to_date('2018-06-01', 'yyyy-mm-dd')),
    PARTITION   p201806   VALUES   LESS   THAN   (to_date('2018-07-01', 'yyyy-mm-dd')),
    PARTITION   p201807   VALUES   LESS   THAN   (to_date('2018-08-01', 'yyyy-mm-dd')),
    PARTITION   p201808   VALUES   LESS   THAN   (to_date('2018-09-01', 'yyyy-mm-dd')),
    PARTITION   p201809   VALUES   LESS   THAN   (to_date('2018-10-01', 'yyyy-mm-dd')),
    PARTITION   p201810   VALUES   LESS   THAN   (to_date('2018-11-01', 'yyyy-mm-dd')),
    PARTITION   p201811   VALUES   LESS   THAN   (to_date('2018-12-01', 'yyyy-mm-dd')),
    PARTITION   p201812   VALUES   LESS   THAN   (to_date('2019-01-01', 'yyyy-mm-dd')),
    PARTITION   p201901   VALUES   LESS   THAN   (to_date('2019-02-01', 'yyyy-mm-dd')),
    PARTITION   p201902   VALUES   LESS   THAN   (to_date('2019-03-01', 'yyyy-mm-dd')),
    PARTITION   p201903   VALUES   LESS   THAN   (to_date('2019-04-01', 'yyyy-mm-dd')),
    PARTITION   p201904   VALUES   LESS   THAN   (to_date('2019-05-01', 'yyyy-mm-dd')),
    PARTITION   p201905   VALUES   LESS   THAN   (to_date('2019-06-01', 'yyyy-mm-dd')),
    PARTITION   p201906   VALUES   LESS   THAN   (to_date('2019-07-01', 'yyyy-mm-dd')),
    PARTITION   p201907   VALUES   LESS   THAN   (to_date('2019-08-01', 'yyyy-mm-dd')),
    PARTITION   p201908   VALUES   LESS   THAN   (to_date('2019-09-01', 'yyyy-mm-dd')),
    PARTITION   p201909   VALUES   LESS   THAN   (to_date('2019-10-01', 'yyyy-mm-dd')),
    PARTITION   p201910   VALUES   LESS   THAN   (to_date('2019-11-01', 'yyyy-mm-dd')),
    PARTITION   p201911   VALUES   LESS   THAN   (to_date('2019-12-01', 'yyyy-mm-dd')),
    PARTITION   p201912   VALUES   LESS   THAN   (to_date('2020-01-01', 'yyyy-mm-dd'))
);


/*考勤信息 班级信息*/
DROP TABLE KQXX_BJXX;
create table KQXX_BJXX(
	KQXX_BJXX_ID VARCHAR2(36) PRIMARY KEY,/*主键*/
	JS_ID VARCHAR2(36),/*教室主键*/
	JSMC VARCHAR2(100),/*教室名称*/
	SKSJ VARCHAR2(200),/*上课日期时间*/
	XINGQI VARCHAR2(100),/*星期*/
	ZHOU VARCHAR2(100),/*周*/
	KESHI_ID VARCHAR2(36),/*课时*/
	KSMC VARCHAR2(100),/*课时名称*/
	LS_ID VARCHAR2(36),/*教师ID*/
	LSXM VARCHAR2(100),/*教师姓名*/
	LSGH VARCHAR2(100),/*教师工号*/
	KCB_ID VARCHAR2(36),/*课程表ID*/
	KCB_FKS_ID VARCHAR2(36),/*课程表中分课时ID*/
	KCMC VARCHAR2(100),/*课程名称*/
	KCLB VARCHAR2(100),/*课程类别*/
	YSKRS INT,/*应上课人数*/
	SJSKRS INT,/*实际上课人数*/
	ZCCXRS INT ,/*正常出席人数*/
	QXRS INT,/*缺席人数*/
	CDRS INT,/*迟到人数*/
	ZTRS INT,/*早退人数*/
	CQL FLOAT,/*出勤率*/
	QXL FLOAT,/*缺席率*/
	CDL FLOAT,/*迟到率*/
	ZTL FLOAT,/*早退率*/
	BZ VARCHAR2(500),/*备注*/
	MS VARCHAR2(500)/*描述*/
);
/**需做分区******************************************************************************/
/*考勤信息表*/
CREATE TABLE kqxx
(
    KQXX_ID varchar2(36) primary key,
    SHEBEI_ID varchar2(36),
    SBMC varchar2(100),
    DJSJ DATE,
    XUEHAO varchar2(100)
)
PARTITION   BY   RANGE   (DJSJ)
(
    PARTITION   p201405   VALUES   LESS   THAN   (to_date('2014-06-01', 'yyyy-mm-dd')),
    PARTITION   p201406   VALUES   LESS   THAN   (to_date('2014-07-01', 'yyyy-mm-dd')),
    PARTITION   p201407   VALUES   LESS   THAN   (to_date('2014-08-01', 'yyyy-mm-dd')),
    PARTITION   p201408   VALUES   LESS   THAN   (to_date('2014-09-01', 'yyyy-mm-dd')),
    PARTITION   p201409   VALUES   LESS   THAN   (to_date('2014-10-01', 'yyyy-mm-dd')),
    PARTITION   p201410   VALUES   LESS   THAN   (to_date('2014-11-01', 'yyyy-mm-dd')),
    PARTITION   p201411   VALUES   LESS   THAN   (to_date('2014-12-01', 'yyyy-mm-dd')),
    PARTITION   p201412   VALUES   LESS   THAN   (to_date('2015-01-01', 'yyyy-mm-dd')),
    PARTITION   p201501   VALUES   LESS   THAN   (to_date('2015-02-01', 'yyyy-mm-dd')),
    PARTITION   p201502   VALUES   LESS   THAN   (to_date('2015-03-01', 'yyyy-mm-dd')),
    PARTITION   p201503   VALUES   LESS   THAN   (to_date('2015-04-01', 'yyyy-mm-dd')),
    PARTITION   p201504   VALUES   LESS   THAN   (to_date('2015-05-01', 'yyyy-mm-dd')),
    PARTITION   p201505   VALUES   LESS   THAN   (to_date('2015-06-01', 'yyyy-mm-dd')),
    PARTITION   p201506   VALUES   LESS   THAN   (to_date('2015-07-01', 'yyyy-mm-dd')),
    PARTITION   p201507   VALUES   LESS   THAN   (to_date('2015-08-01', 'yyyy-mm-dd')),
    PARTITION   p201508   VALUES   LESS   THAN   (to_date('2015-09-01', 'yyyy-mm-dd')),
    PARTITION   p201509   VALUES   LESS   THAN   (to_date('2015-10-01', 'yyyy-mm-dd')),
    PARTITION   p201510   VALUES   LESS   THAN   (to_date('2015-11-01', 'yyyy-mm-dd')),
    PARTITION   p201511   VALUES   LESS   THAN   (to_date('2015-12-01', 'yyyy-mm-dd')),
    PARTITION   p201512   VALUES   LESS   THAN   (to_date('2016-01-01', 'yyyy-mm-dd')),
    PARTITION   p201601   VALUES   LESS   THAN   (to_date('2016-02-01', 'yyyy-mm-dd')),
    PARTITION   p201602   VALUES   LESS   THAN   (to_date('2016-03-01', 'yyyy-mm-dd')),
    PARTITION   p201603   VALUES   LESS   THAN   (to_date('2016-04-01', 'yyyy-mm-dd')),
    PARTITION   p201604   VALUES   LESS   THAN   (to_date('2016-05-01', 'yyyy-mm-dd')),
    PARTITION   p201605   VALUES   LESS   THAN   (to_date('2016-06-01', 'yyyy-mm-dd')),
    PARTITION   p201606   VALUES   LESS   THAN   (to_date('2016-07-01', 'yyyy-mm-dd')),
    PARTITION   p201607   VALUES   LESS   THAN   (to_date('2016-08-01', 'yyyy-mm-dd')),
    PARTITION   p201608   VALUES   LESS   THAN   (to_date('2016-09-01', 'yyyy-mm-dd')),
    PARTITION   p201609   VALUES   LESS   THAN   (to_date('2016-10-01', 'yyyy-mm-dd')),
    PARTITION   p201610   VALUES   LESS   THAN   (to_date('2016-11-01', 'yyyy-mm-dd')),
    PARTITION   p201611   VALUES   LESS   THAN   (to_date('2016-12-01', 'yyyy-mm-dd')),
    PARTITION   p201612   VALUES   LESS   THAN   (to_date('2017-01-01', 'yyyy-mm-dd')),
    PARTITION   p201701   VALUES   LESS   THAN   (to_date('2017-02-01', 'yyyy-mm-dd')),
    PARTITION   p201702   VALUES   LESS   THAN   (to_date('2017-03-01', 'yyyy-mm-dd')),
    PARTITION   p201703   VALUES   LESS   THAN   (to_date('2017-04-01', 'yyyy-mm-dd')),
    PARTITION   p201704   VALUES   LESS   THAN   (to_date('2017-05-01', 'yyyy-mm-dd')),
    PARTITION   p201705   VALUES   LESS   THAN   (to_date('2017-06-01', 'yyyy-mm-dd')),
    PARTITION   p201706   VALUES   LESS   THAN   (to_date('2017-07-01', 'yyyy-mm-dd')),
    PARTITION   p201707   VALUES   LESS   THAN   (to_date('2017-08-01', 'yyyy-mm-dd')),
    PARTITION   p201708   VALUES   LESS   THAN   (to_date('2017-09-01', 'yyyy-mm-dd')),
    PARTITION   p201709   VALUES   LESS   THAN   (to_date('2017-10-01', 'yyyy-mm-dd')),
    PARTITION   p201710   VALUES   LESS   THAN   (to_date('2017-11-01', 'yyyy-mm-dd')),
    PARTITION   p201711   VALUES   LESS   THAN   (to_date('2017-12-01', 'yyyy-mm-dd')),
    PARTITION   p201712   VALUES   LESS   THAN   (to_date('2018-01-01', 'yyyy-mm-dd')),
    PARTITION   p201801   VALUES   LESS   THAN   (to_date('2018-02-01', 'yyyy-mm-dd')),
    PARTITION   p201802   VALUES   LESS   THAN   (to_date('2018-03-01', 'yyyy-mm-dd')),
    PARTITION   p201803   VALUES   LESS   THAN   (to_date('2018-04-01', 'yyyy-mm-dd')),
    PARTITION   p201804   VALUES   LESS   THAN   (to_date('2018-05-01', 'yyyy-mm-dd')),
    PARTITION   p201805   VALUES   LESS   THAN   (to_date('2018-06-01', 'yyyy-mm-dd')),
    PARTITION   p201806   VALUES   LESS   THAN   (to_date('2018-07-01', 'yyyy-mm-dd')),
    PARTITION   p201807   VALUES   LESS   THAN   (to_date('2018-08-01', 'yyyy-mm-dd')),
    PARTITION   p201808   VALUES   LESS   THAN   (to_date('2018-09-01', 'yyyy-mm-dd')),
    PARTITION   p201809   VALUES   LESS   THAN   (to_date('2018-10-01', 'yyyy-mm-dd')),
    PARTITION   p201810   VALUES   LESS   THAN   (to_date('2018-11-01', 'yyyy-mm-dd')),
    PARTITION   p201811   VALUES   LESS   THAN   (to_date('2018-12-01', 'yyyy-mm-dd')),
    PARTITION   p201812   VALUES   LESS   THAN   (to_date('2019-01-01', 'yyyy-mm-dd')),
    PARTITION   p201901   VALUES   LESS   THAN   (to_date('2019-02-01', 'yyyy-mm-dd')),
    PARTITION   p201902   VALUES   LESS   THAN   (to_date('2019-03-01', 'yyyy-mm-dd')),
    PARTITION   p201903   VALUES   LESS   THAN   (to_date('2019-04-01', 'yyyy-mm-dd')),
    PARTITION   p201904   VALUES   LESS   THAN   (to_date('2019-05-01', 'yyyy-mm-dd')),
    PARTITION   p201905   VALUES   LESS   THAN   (to_date('2019-06-01', 'yyyy-mm-dd')),
    PARTITION   p201906   VALUES   LESS   THAN   (to_date('2019-07-01', 'yyyy-mm-dd')),
    PARTITION   p201907   VALUES   LESS   THAN   (to_date('2019-08-01', 'yyyy-mm-dd')),
    PARTITION   p201908   VALUES   LESS   THAN   (to_date('2019-09-01', 'yyyy-mm-dd')),
    PARTITION   p201909   VALUES   LESS   THAN   (to_date('2019-10-01', 'yyyy-mm-dd')),
    PARTITION   p201910   VALUES   LESS   THAN   (to_date('2019-11-01', 'yyyy-mm-dd')),
    PARTITION   p201911   VALUES   LESS   THAN   (to_date('2019-12-01', 'yyyy-mm-dd')),
    PARTITION   p201912   VALUES   LESS   THAN   (to_date('2020-01-01', 'yyyy-mm-dd'))
);

/*计算考勤信息存放按课时的学号信息*/
create table tmp_xuehao(
	xuehao varchar2(100)
);

/*学生考勤信息*/
DROP TABLE KQXX_XSCQ;
create table KQXX_XSCQ(
	KQXX_XSCQ_ID VARCHAR2(36),/*主键*/
	JS_ID VARCHAR2(36),/*教室主键*/
	JSMC VARCHAR2(100),/*教室名称*/
	SKSJ date,/*上课日期时间*/
	XINGQI VARCHAR2(100),/*星期*/
	ZHOU VARCHAR2(100),/*周*/
	KESHI_ID VARCHAR2(36),/*课时*/
	KSMC VARCHAR2(100),/*课时名称*/
	LS_ID VARCHAR2(36),/*教师ID*/
	LSXM VARCHAR2(100),/*教师姓名*/
	LSGH VARCHAR2(100),/*教师工号*/
	KCB_ID VARCHAR2(36),/*课程表ID*/
	KCB_FKS_ID VARCHAR2(36),/*课程表中分课时ID*/
	KCMC VARCHAR2(100),/*课程名称*/
	KCLB VARCHAR2(100),/*课程类别*/
	XS_ID VARCHAR2(36),/*学生ID*/
	XSXM VARCHAR2(100),/*学生姓名*/
	XSXH VARCHAR2(100),/*学生学号*/
	CQQK VARCHAR2(100),/*出勤情况*/
	XY_ID VARCHAR2(36),/*所在学院*/
	ZY_ID VARCHAR2(36),/*所在专业*/
	NJ_ID VARCHAR2(36),/*所在年级*/
	BJ_ID VARCHAR2(36),/*所在班级*/
	BZ VARCHAR2(500),
	MS VARCHAR2(500)
)
PARTITION   BY   RANGE   (SKSJ)
(
    PARTITION   p201405   VALUES   LESS   THAN   (to_date('2014-06-01', 'yyyy-mm-dd')),
    PARTITION   p201406   VALUES   LESS   THAN   (to_date('2014-07-01', 'yyyy-mm-dd')),
    PARTITION   p201407   VALUES   LESS   THAN   (to_date('2014-08-01', 'yyyy-mm-dd')),
    PARTITION   p201408   VALUES   LESS   THAN   (to_date('2014-09-01', 'yyyy-mm-dd')),
    PARTITION   p201409   VALUES   LESS   THAN   (to_date('2014-10-01', 'yyyy-mm-dd')),
    PARTITION   p201410   VALUES   LESS   THAN   (to_date('2014-11-01', 'yyyy-mm-dd')),
    PARTITION   p201411   VALUES   LESS   THAN   (to_date('2014-12-01', 'yyyy-mm-dd')),
    PARTITION   p201412   VALUES   LESS   THAN   (to_date('2015-01-01', 'yyyy-mm-dd')),
    PARTITION   p201501   VALUES   LESS   THAN   (to_date('2015-02-01', 'yyyy-mm-dd')),
    PARTITION   p201502   VALUES   LESS   THAN   (to_date('2015-03-01', 'yyyy-mm-dd')),
    PARTITION   p201503   VALUES   LESS   THAN   (to_date('2015-04-01', 'yyyy-mm-dd')),
    PARTITION   p201504   VALUES   LESS   THAN   (to_date('2015-05-01', 'yyyy-mm-dd')),
    PARTITION   p201505   VALUES   LESS   THAN   (to_date('2015-06-01', 'yyyy-mm-dd')),
    PARTITION   p201506   VALUES   LESS   THAN   (to_date('2015-07-01', 'yyyy-mm-dd')),
    PARTITION   p201507   VALUES   LESS   THAN   (to_date('2015-08-01', 'yyyy-mm-dd')),
    PARTITION   p201508   VALUES   LESS   THAN   (to_date('2015-09-01', 'yyyy-mm-dd')),
    PARTITION   p201509   VALUES   LESS   THAN   (to_date('2015-10-01', 'yyyy-mm-dd')),
    PARTITION   p201510   VALUES   LESS   THAN   (to_date('2015-11-01', 'yyyy-mm-dd')),
    PARTITION   p201511   VALUES   LESS   THAN   (to_date('2015-12-01', 'yyyy-mm-dd')),
    PARTITION   p201512   VALUES   LESS   THAN   (to_date('2016-01-01', 'yyyy-mm-dd')),
    PARTITION   p201601   VALUES   LESS   THAN   (to_date('2016-02-01', 'yyyy-mm-dd')),
    PARTITION   p201602   VALUES   LESS   THAN   (to_date('2016-03-01', 'yyyy-mm-dd')),
    PARTITION   p201603   VALUES   LESS   THAN   (to_date('2016-04-01', 'yyyy-mm-dd')),
    PARTITION   p201604   VALUES   LESS   THAN   (to_date('2016-05-01', 'yyyy-mm-dd')),
    PARTITION   p201605   VALUES   LESS   THAN   (to_date('2016-06-01', 'yyyy-mm-dd')),
    PARTITION   p201606   VALUES   LESS   THAN   (to_date('2016-07-01', 'yyyy-mm-dd')),
    PARTITION   p201607   VALUES   LESS   THAN   (to_date('2016-08-01', 'yyyy-mm-dd')),
    PARTITION   p201608   VALUES   LESS   THAN   (to_date('2016-09-01', 'yyyy-mm-dd')),
    PARTITION   p201609   VALUES   LESS   THAN   (to_date('2016-10-01', 'yyyy-mm-dd')),
    PARTITION   p201610   VALUES   LESS   THAN   (to_date('2016-11-01', 'yyyy-mm-dd')),
    PARTITION   p201611   VALUES   LESS   THAN   (to_date('2016-12-01', 'yyyy-mm-dd')),
    PARTITION   p201612   VALUES   LESS   THAN   (to_date('2017-01-01', 'yyyy-mm-dd')),
    PARTITION   p201701   VALUES   LESS   THAN   (to_date('2017-02-01', 'yyyy-mm-dd')),
    PARTITION   p201702   VALUES   LESS   THAN   (to_date('2017-03-01', 'yyyy-mm-dd')),
    PARTITION   p201703   VALUES   LESS   THAN   (to_date('2017-04-01', 'yyyy-mm-dd')),
    PARTITION   p201704   VALUES   LESS   THAN   (to_date('2017-05-01', 'yyyy-mm-dd')),
    PARTITION   p201705   VALUES   LESS   THAN   (to_date('2017-06-01', 'yyyy-mm-dd')),
    PARTITION   p201706   VALUES   LESS   THAN   (to_date('2017-07-01', 'yyyy-mm-dd')),
    PARTITION   p201707   VALUES   LESS   THAN   (to_date('2017-08-01', 'yyyy-mm-dd')),
    PARTITION   p201708   VALUES   LESS   THAN   (to_date('2017-09-01', 'yyyy-mm-dd')),
    PARTITION   p201709   VALUES   LESS   THAN   (to_date('2017-10-01', 'yyyy-mm-dd')),
    PARTITION   p201710   VALUES   LESS   THAN   (to_date('2017-11-01', 'yyyy-mm-dd')),
    PARTITION   p201711   VALUES   LESS   THAN   (to_date('2017-12-01', 'yyyy-mm-dd')),
    PARTITION   p201712   VALUES   LESS   THAN   (to_date('2018-01-01', 'yyyy-mm-dd')),
    PARTITION   p201801   VALUES   LESS   THAN   (to_date('2018-02-01', 'yyyy-mm-dd')),
    PARTITION   p201802   VALUES   LESS   THAN   (to_date('2018-03-01', 'yyyy-mm-dd')),
    PARTITION   p201803   VALUES   LESS   THAN   (to_date('2018-04-01', 'yyyy-mm-dd')),
    PARTITION   p201804   VALUES   LESS   THAN   (to_date('2018-05-01', 'yyyy-mm-dd')),
    PARTITION   p201805   VALUES   LESS   THAN   (to_date('2018-06-01', 'yyyy-mm-dd')),
    PARTITION   p201806   VALUES   LESS   THAN   (to_date('2018-07-01', 'yyyy-mm-dd')),
    PARTITION   p201807   VALUES   LESS   THAN   (to_date('2018-08-01', 'yyyy-mm-dd')),
    PARTITION   p201808   VALUES   LESS   THAN   (to_date('2018-09-01', 'yyyy-mm-dd')),
    PARTITION   p201809   VALUES   LESS   THAN   (to_date('2018-10-01', 'yyyy-mm-dd')),
    PARTITION   p201810   VALUES   LESS   THAN   (to_date('2018-11-01', 'yyyy-mm-dd')),
    PARTITION   p201811   VALUES   LESS   THAN   (to_date('2018-12-01', 'yyyy-mm-dd')),
    PARTITION   p201812   VALUES   LESS   THAN   (to_date('2019-01-01', 'yyyy-mm-dd')),
    PARTITION   p201901   VALUES   LESS   THAN   (to_date('2019-02-01', 'yyyy-mm-dd')),
    PARTITION   p201902   VALUES   LESS   THAN   (to_date('2019-03-01', 'yyyy-mm-dd')),
    PARTITION   p201903   VALUES   LESS   THAN   (to_date('2019-04-01', 'yyyy-mm-dd')),
    PARTITION   p201904   VALUES   LESS   THAN   (to_date('2019-05-01', 'yyyy-mm-dd')),
    PARTITION   p201905   VALUES   LESS   THAN   (to_date('2019-06-01', 'yyyy-mm-dd')),
    PARTITION   p201906   VALUES   LESS   THAN   (to_date('2019-07-01', 'yyyy-mm-dd')),
    PARTITION   p201907   VALUES   LESS   THAN   (to_date('2019-08-01', 'yyyy-mm-dd')),
    PARTITION   p201908   VALUES   LESS   THAN   (to_date('2019-09-01', 'yyyy-mm-dd')),
    PARTITION   p201909   VALUES   LESS   THAN   (to_date('2019-10-01', 'yyyy-mm-dd')),
    PARTITION   p201910   VALUES   LESS   THAN   (to_date('2019-11-01', 'yyyy-mm-dd')),
    PARTITION   p201911   VALUES   LESS   THAN   (to_date('2019-12-01', 'yyyy-mm-dd')),
    PARTITION   p201912   VALUES   LESS   THAN   (to_date('2020-01-01', 'yyyy-mm-dd'))
);

/*KQXX_DAY考勤信息实时记录表*/
drop table KQXX_DAY;
CREATE TABLE KQXX_DAY
(
    KQXX_ID varchar2(36) primary key,
    SHEBEI_ID varchar2(36),
    SBMC varchar2(100),
    DJSJ DATE,
    XUEHAO varchar2(100)
);


/*文件上传实时记录表：王久诚 2014-05-22*/
drop table FILEUPLOAD;
CREATE TABLE FILEUPLOAD(
	FILE_ID VARCHAR2(30) NOT NULL ENABLE, 
	FILENAME VARCHAR2(50) NOT NULL ENABLE, 
	FILETYPE VARCHAR2(20), 
	FILEFULLPATH VARCHAR2(150) NOT NULL ENABLE, 
	FILEUPLOADTIME DATE
 );
 
/*KQXX_CZRZ考勤信息操作日志表*/
drop table KQXX_CZRZ;
CREATE TABLE KQXX_CZRZ
(
    KQXXCZRZ_ID varchar2(36) primary key,/*主键*/
    CZMC varchar2(100),/*操作名称*/
    CZSJ DATE,/*操作时间*/
    BUZHOU VARCHAR2(100),
    SFCG VARCHAR2(50),/*操作是否成功*/
    JGBM VARCHAR2(50),/*操作结果编码*/
    JGMS VARCHAR2(1000)/*操作结果描述*/
);

/*邮件发送*/
drop table YOUJIANLB;
CREATE TABLE YOUJIANLB
(
    YOUJIANLB_ID varchar2(36) primary key,/*主键*/
    YXMC varchar2(100),/*收件人邮箱*/
    XINGMING varchar2(100),/*收件人姓名*/
    ms VARCHAR2(500),/*描述*/
    bz VARCHAR2(500)/*备注*/
);


/*考勤信息阶段计算表：加入串课与请假管理*/
DROP TABLE KQXX_JD;
create table KQXX_JD(
	KQXX_JD_ID VARCHAR2(36),/*主键*/
	JS_ID VARCHAR2(36),/*教室主键*/
	JSMC VARCHAR2(100),/*教室名称*/
	SKSJ date,/*上课日期时间*/
	XINGQI VARCHAR2(100),/*星期*/
	ZHOU VARCHAR2(100),/*周*/
	KESHI_ID VARCHAR2(36),/*课时*/
	KSMC VARCHAR2(100),/*课时名称*/
	LS_ID VARCHAR2(36),/*教师ID*/
	LSXM VARCHAR2(100),/*教师姓名*/
	LSGH VARCHAR2(100),/*教师工号*/
	KCB_ID VARCHAR2(36),/*课程表ID*/
	KCB_FKS_ID VARCHAR2(36),/*课程表中分课时ID*/
	KCMC VARCHAR2(100),/*课程名称*/
	KCLB VARCHAR2(100),/*课程类别*/
	XS_ID VARCHAR2(36),/*学生ID*/
	XSXM VARCHAR2(100),/*学生姓名*/
	XSXH VARCHAR2(100),/*学生学号*/
	CQQK VARCHAR2(100),/*出勤情况*/
	XY_ID VARCHAR2(36),/*所在学院*/
	XYMC VARCHAR2(100),/*学院名称*/
	ZY_ID VARCHAR2(36),/*所在专业*/
	ZYMC VARCHAR2(100),/*专业名称*/
	NJ_ID VARCHAR2(36),/*所在年级*/
	NJMC VARCHAR2(100),/*年级名称*/
	BJ_ID VARCHAR2(36),/*所在班级*/
	BJMC VARCHAR2(100),/*班级名称*/
	QINGJIA VARCHAR2(36),/*是否请假*/
	QJXX_ID VARCHAR2(36),/*请假ID*/
	CHUANKE VARCHAR2(36),/*是否串课*/
	CKXX_ID VARCHAR2(36),/*串课信息ID*/
	JISUANSJ date,/*该次计算的时间*/
	BZ VARCHAR2(500),
	MS VARCHAR2(500)
)
PARTITION   BY   RANGE   (JISUANSJ)
(
    PARTITION   p201405   VALUES   LESS   THAN   (to_date('2014-06-01', 'yyyy-mm-dd')),
    PARTITION   p201406   VALUES   LESS   THAN   (to_date('2014-07-01', 'yyyy-mm-dd')),
    PARTITION   p201407   VALUES   LESS   THAN   (to_date('2014-08-01', 'yyyy-mm-dd')),
    PARTITION   p201408   VALUES   LESS   THAN   (to_date('2014-09-01', 'yyyy-mm-dd')),
    PARTITION   p201409   VALUES   LESS   THAN   (to_date('2014-10-01', 'yyyy-mm-dd')),
    PARTITION   p201410   VALUES   LESS   THAN   (to_date('2014-11-01', 'yyyy-mm-dd')),
    PARTITION   p201411   VALUES   LESS   THAN   (to_date('2014-12-01', 'yyyy-mm-dd')),
    PARTITION   p201412   VALUES   LESS   THAN   (to_date('2015-01-01', 'yyyy-mm-dd')),
    PARTITION   p201501   VALUES   LESS   THAN   (to_date('2015-02-01', 'yyyy-mm-dd')),
    PARTITION   p201502   VALUES   LESS   THAN   (to_date('2015-03-01', 'yyyy-mm-dd')),
    PARTITION   p201503   VALUES   LESS   THAN   (to_date('2015-04-01', 'yyyy-mm-dd')),
    PARTITION   p201504   VALUES   LESS   THAN   (to_date('2015-05-01', 'yyyy-mm-dd')),
    PARTITION   p201505   VALUES   LESS   THAN   (to_date('2015-06-01', 'yyyy-mm-dd')),
    PARTITION   p201506   VALUES   LESS   THAN   (to_date('2015-07-01', 'yyyy-mm-dd')),
    PARTITION   p201507   VALUES   LESS   THAN   (to_date('2015-08-01', 'yyyy-mm-dd')),
    PARTITION   p201508   VALUES   LESS   THAN   (to_date('2015-09-01', 'yyyy-mm-dd')),
    PARTITION   p201509   VALUES   LESS   THAN   (to_date('2015-10-01', 'yyyy-mm-dd')),
    PARTITION   p201510   VALUES   LESS   THAN   (to_date('2015-11-01', 'yyyy-mm-dd')),
    PARTITION   p201511   VALUES   LESS   THAN   (to_date('2015-12-01', 'yyyy-mm-dd')),
    PARTITION   p201512   VALUES   LESS   THAN   (to_date('2016-01-01', 'yyyy-mm-dd')),
    PARTITION   p201601   VALUES   LESS   THAN   (to_date('2016-02-01', 'yyyy-mm-dd')),
    PARTITION   p201602   VALUES   LESS   THAN   (to_date('2016-03-01', 'yyyy-mm-dd')),
    PARTITION   p201603   VALUES   LESS   THAN   (to_date('2016-04-01', 'yyyy-mm-dd')),
    PARTITION   p201604   VALUES   LESS   THAN   (to_date('2016-05-01', 'yyyy-mm-dd')),
    PARTITION   p201605   VALUES   LESS   THAN   (to_date('2016-06-01', 'yyyy-mm-dd')),
    PARTITION   p201606   VALUES   LESS   THAN   (to_date('2016-07-01', 'yyyy-mm-dd')),
    PARTITION   p201607   VALUES   LESS   THAN   (to_date('2016-08-01', 'yyyy-mm-dd')),
    PARTITION   p201608   VALUES   LESS   THAN   (to_date('2016-09-01', 'yyyy-mm-dd')),
    PARTITION   p201609   VALUES   LESS   THAN   (to_date('2016-10-01', 'yyyy-mm-dd')),
    PARTITION   p201610   VALUES   LESS   THAN   (to_date('2016-11-01', 'yyyy-mm-dd')),
    PARTITION   p201611   VALUES   LESS   THAN   (to_date('2016-12-01', 'yyyy-mm-dd')),
    PARTITION   p201612   VALUES   LESS   THAN   (to_date('2017-01-01', 'yyyy-mm-dd')),
    PARTITION   p201701   VALUES   LESS   THAN   (to_date('2017-02-01', 'yyyy-mm-dd')),
    PARTITION   p201702   VALUES   LESS   THAN   (to_date('2017-03-01', 'yyyy-mm-dd')),
    PARTITION   p201703   VALUES   LESS   THAN   (to_date('2017-04-01', 'yyyy-mm-dd')),
    PARTITION   p201704   VALUES   LESS   THAN   (to_date('2017-05-01', 'yyyy-mm-dd')),
    PARTITION   p201705   VALUES   LESS   THAN   (to_date('2017-06-01', 'yyyy-mm-dd')),
    PARTITION   p201706   VALUES   LESS   THAN   (to_date('2017-07-01', 'yyyy-mm-dd')),
    PARTITION   p201707   VALUES   LESS   THAN   (to_date('2017-08-01', 'yyyy-mm-dd')),
    PARTITION   p201708   VALUES   LESS   THAN   (to_date('2017-09-01', 'yyyy-mm-dd')),
    PARTITION   p201709   VALUES   LESS   THAN   (to_date('2017-10-01', 'yyyy-mm-dd')),
    PARTITION   p201710   VALUES   LESS   THAN   (to_date('2017-11-01', 'yyyy-mm-dd')),
    PARTITION   p201711   VALUES   LESS   THAN   (to_date('2017-12-01', 'yyyy-mm-dd')),
    PARTITION   p201712   VALUES   LESS   THAN   (to_date('2018-01-01', 'yyyy-mm-dd')),
    PARTITION   p201801   VALUES   LESS   THAN   (to_date('2018-02-01', 'yyyy-mm-dd')),
    PARTITION   p201802   VALUES   LESS   THAN   (to_date('2018-03-01', 'yyyy-mm-dd')),
    PARTITION   p201803   VALUES   LESS   THAN   (to_date('2018-04-01', 'yyyy-mm-dd')),
    PARTITION   p201804   VALUES   LESS   THAN   (to_date('2018-05-01', 'yyyy-mm-dd')),
    PARTITION   p201805   VALUES   LESS   THAN   (to_date('2018-06-01', 'yyyy-mm-dd')),
    PARTITION   p201806   VALUES   LESS   THAN   (to_date('2018-07-01', 'yyyy-mm-dd')),
    PARTITION   p201807   VALUES   LESS   THAN   (to_date('2018-08-01', 'yyyy-mm-dd')),
    PARTITION   p201808   VALUES   LESS   THAN   (to_date('2018-09-01', 'yyyy-mm-dd')),
    PARTITION   p201809   VALUES   LESS   THAN   (to_date('2018-10-01', 'yyyy-mm-dd')),
    PARTITION   p201810   VALUES   LESS   THAN   (to_date('2018-11-01', 'yyyy-mm-dd')),
    PARTITION   p201811   VALUES   LESS   THAN   (to_date('2018-12-01', 'yyyy-mm-dd')),
    PARTITION   p201812   VALUES   LESS   THAN   (to_date('2019-01-01', 'yyyy-mm-dd')),
    PARTITION   p201901   VALUES   LESS   THAN   (to_date('2019-02-01', 'yyyy-mm-dd')),
    PARTITION   p201902   VALUES   LESS   THAN   (to_date('2019-03-01', 'yyyy-mm-dd')),
    PARTITION   p201903   VALUES   LESS   THAN   (to_date('2019-04-01', 'yyyy-mm-dd')),
    PARTITION   p201904   VALUES   LESS   THAN   (to_date('2019-05-01', 'yyyy-mm-dd')),
    PARTITION   p201905   VALUES   LESS   THAN   (to_date('2019-06-01', 'yyyy-mm-dd')),
    PARTITION   p201906   VALUES   LESS   THAN   (to_date('2019-07-01', 'yyyy-mm-dd')),
    PARTITION   p201907   VALUES   LESS   THAN   (to_date('2019-08-01', 'yyyy-mm-dd')),
    PARTITION   p201908   VALUES   LESS   THAN   (to_date('2019-09-01', 'yyyy-mm-dd')),
    PARTITION   p201909   VALUES   LESS   THAN   (to_date('2019-10-01', 'yyyy-mm-dd')),
    PARTITION   p201910   VALUES   LESS   THAN   (to_date('2019-11-01', 'yyyy-mm-dd')),
    PARTITION   p201911   VALUES   LESS   THAN   (to_date('2019-12-01', 'yyyy-mm-dd')),
    PARTITION   p201912   VALUES   LESS   THAN   (to_date('2020-01-01', 'yyyy-mm-dd'))
);

--指纹录入表
create table zwlrlb(
zwlr_id varchar2(36) primary key , --指纹录入列表ID
lbmc varchar2(200),--需要录入指纹名单列表的名称
lbcjsj varchar2(200),--列表建立时间
zwlrsj varchar2(200),--列表中人员的指纹录入时间
sflr varchar2(100)--是否已经录入
);
drop table zwlrlbgx;
--指纹录入表与实体用户对应关系表
create table zwlrlbgx(
zwlrgx_id varchar2(36) primary key,--主键
zwlr_id varchar2(36),--对应指纹表ID，
zwlr_mc varchar2(200),--指纹录入表的名称
paixu int,--排序
zwlrbiao varchar2(200),--对应的录入哪个实体类表的人的指纹。 :比如录入学生指纹：XSXX
zwlrbiao_id varchar2(36),--录入指纹的表的ID:比如录入学生指纹：XSXX_ID
zwlrbiao_mc varchar2(50),--录入指纹的表的姓名。 :比如录入学生指纹：对应学生姓名
zwlrbiao_hm varchar2(100),--录入指纹的表的编号。:比如录入学生指纹：对应学生学号
zwlrbiao_tmp1 varchar2(500),--扩展需要字段.:比如录入学生指纹：对应学生所在学院
zwlrbiao_tmp2 varchar2(500),--扩展需要字段.:比如录入学生指纹：对应学生所在专业
zwlrbiao_tmp3 varchar2(500),--扩展需要字段.:比如录入学生指纹：对应学生所在年级
zwlrbiao_tmp4 varchar2(500),--扩展需要字段.:比如录入学生指纹：对应学生所在班级
zwlrbiao_tmp5 varchar2(500),--扩展需要字段.
zwlrbiao_tmp6 varchar2(500),--扩展需要字段.
zwlrbiao_tmp7 varchar2(500),--扩展需要字段.
zwlrbiao_tmp8 varchar2(500),--扩展需要字段.
zwlrbiao_tmp9 varchar2(500)--扩展需要字段.
);