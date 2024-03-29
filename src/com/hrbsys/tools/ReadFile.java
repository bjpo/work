package com.hrbsys.tools;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/*生成模块相关文件*/

public class ReadFile {
	
	public void createDir(String path){
		File dir=new File(path);
		  if(!dir.exists()){
			  dir.mkdirs();
		  }
	}
	
	public String createJSDir(String fileOutPath2,String biaoming){
		  String path=fileOutPath2+"\\WebRoot\\js\\"+biaoming.toLowerCase()+"\\";
		  createDir(path);
		  return path;
	}
	
	public String createJSPDir(String fileOutPath2,String biaoming){
		  String path=fileOutPath2+"\\WebRoot\\"+biaoming.toLowerCase()+"\\";
		  createDir(path);
		  return path;
	}
	public String createActionDir(String fileOutPath2,String biaoming,String pkg){
		  String pkgtmp=pkg.replace(".", "\\");
		  String path=fileOutPath2+"\\src\\"+pkgtmp+"\\action\\";
		  createDir(path);
		  return path;
	}
	public String createServiceDir(String fileOutPath2,String biaoming,String pkg){
		  String pkgtmp=pkg.replace(".", "\\");
		  String path=fileOutPath2+"\\src\\"+pkgtmp+"\\service\\";
		  createDir(path);
		  return path;
	}
	public String createServiceImplDir(String fileOutPath2,String biaoming,String pkg){
		  String pkgtmp=pkg.replace(".", "\\");
		  String path=fileOutPath2+"\\src\\"+pkgtmp+"\\service\\impl\\";
		  createDir(path);
		  return path;
	}
	public String createBeanDir(String fileOutPath2,String biaoming,String pkg){
		  String pkgtmp=pkg.replace(".", "\\");
		  String path=fileOutPath2+"\\src\\com\\hrbsys\\bean\\";
		  createDir(path);
		  return path;
	}
/////////////////////////////////////////////////////////////实体类对象配置及输出
    public void outPutEntryBean(){
	  String str = new String();//写入文本字符串
	  String biaoming="";
	  String pkg="";
	  String primarykey="";
	  String fileOutPath="";
	  String fileOutPath2="";
	  try {
		  Properties prop = new Properties(); 
	      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
	      prop.load(in); 
          biaoming = prop.getProperty("tablename").trim();
          pkg = prop.getProperty("package").trim(); 
          primarykey = prop.getProperty("primarykey").trim();
//          fileOutPath = prop.getProperty("fileOutPath").trim();
          fileOutPath2= prop.getProperty("fileOutPath").trim();
          fileOutPath=createBeanDir(fileOutPath2, biaoming, pkg)+"\\"+biaoming.toUpperCase()+".java";
          //设置属性集合剔除掉这几个主要属性
          Set keySet=prop.keySet();
          keySet.remove("tablename");//剔除表名
          keySet.remove("package");//剔除包名
          keySet.remove("primarykey");//剔除主键
          keySet.remove("fileOutPath");//剔除输出路径
		  File f = new File(fileOutPath);
		  if(f.exists()){
		  }else{
			  f.createNewFile();//不存在则创建
		  }
		  BufferedReader input = new BufferedReader(new FileReader(f));
		  str="package com.hrbsys.bean;" +"\r\n"+
		  		"import java.io.Serializable;" +"\r\n"+
		  		"import javax.persistence.Column;" +"\r\n"+
		  		"import javax.persistence.Entity;" +"\r\n"+
		  		"import javax.persistence.Id;" +"\r\n"+
		  		"import javax.persistence.Table;"+"\r\n"+
		  		"@Entity" +"\r\n"+
		  		"@Table(name=\""+biaoming.toUpperCase()+"\")" +"\r\n"+
		  		"public class "+biaoming.toUpperCase()+"  implements Serializable {" +"\r\n"+
		  		"@Id" +"\r\n"+
		  		"@Column(name=\""+primarykey.toUpperCase()+"\")" +"\r\n"+
		  	    "private String "+primarykey.toUpperCase()+";"+"\r\n"+"\r\n";
		  str+="public String get"+primarykey.toUpperCase()+"() {" +"\r\n"+
			  		" return "+primarykey.toUpperCase()+"; " +"\r\n"+
			  		"}" +"\r\n"+
			  		"public void set"+primarykey.toUpperCase()+"(String "+primarykey.toUpperCase()+") { " +"\r\n"+
			  		"this."+primarykey.toUpperCase()+" = "+primarykey.toUpperCase()+";" +"\r\n"+
			  		"}"+"\r\n";
		  Iterator itkey=keySet.iterator();
		  //生成字段
		  while(itkey.hasNext()){
			  String tmpkey=itkey.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
			  str+="@Column(name=\""+tmpkey.toUpperCase().trim()+"\")" +"\r\n"+
			  	   "private "+tmpvalue+" "+tmpkey.toUpperCase()+";"+"\r\n"+
			  	   "\r\n";
			  str+="public "+tmpvalue+" get"+tmpkey.toUpperCase()+"() {" +"\r\n"+
				  		" return "+tmpkey.toUpperCase()+"; " +"\r\n"+
				  		"}" +"\r\n"+
				  		"public void set"+tmpkey.toUpperCase()+"("+tmpvalue+" "+tmpkey.toUpperCase()+") { " +"\r\n"+
				  		"this."+tmpkey.toUpperCase()+" = "+tmpkey.toUpperCase()+";" +"\r\n"+
				  		"}"+"\r\n";
		  }
		  str+="\r\n" +"}";
		  BufferedWriter output = new BufferedWriter(new FileWriter(f));   
		  output.write(str);   
		  output.close();  
		  System.out.println("实体类： "+fileOutPath+"  【生成完毕！】 ,请将其配置到applicationContext.xml文件中："+"<value>com.hrbsys.bean."+biaoming.toUpperCase()+"</value>");
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
   }

//输出service
    public void outPutEntryServiceInterface(){
  	  String str = new String();//写入文本字符串
  	  String biaoming="";
  	  String pkg="";
  	  String primarykey="";
  	  String fileOutPath="";
  	  String fileOutPath2="";
  	  try {
  		  Properties prop = new Properties(); 
  	      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
  	      prop.load(in); 
            biaoming = prop.getProperty("tablename").trim();
            pkg = prop.getProperty("package").trim(); 
            primarykey = prop.getProperty("primarykey").trim();
            fileOutPath2 = prop.getProperty("fileOutPath").trim();
            fileOutPath=createServiceDir(fileOutPath2, biaoming, pkg)+"\\"+biaoming.toUpperCase()+"Service.java";
            
  		  File f = new File(fileOutPath);
  		  if(f.exists()){
  		  }else{
  			  f.createNewFile();//不存在则创建
  		  }
  		  BufferedReader input = new BufferedReader(new FileReader(f));
  		  str="package "+pkg+".service;" +"\r\n"+
  		  		"import java.util.HashMap;"+"\r\n" +
  		  		"import java.util.List;"+"\r\n";
  		  str+="public interface "+biaoming.toUpperCase()+"Service {" +"\r\n"+
  		  		"public boolean add"+biaoming.toUpperCase()+"("+biaoming.toUpperCase()+" y); " +"\r\n"+
  		  		"public boolean del"+biaoming.toUpperCase()+"(String y);" +"\r\n"+
  		  		"public boolean update"+biaoming.toUpperCase()+"("+biaoming.toUpperCase()+" yhz);" +"\r\n"+
  		  		"public "+biaoming.toUpperCase()+" find"+biaoming.toUpperCase()+"(String yhz_id);" +"\r\n"+
  		  		"public List<"+biaoming.toUpperCase()+"> find"+biaoming.toUpperCase()+"ByPageApp(int start, int number,HashMap<String,String> params,String order,String sort);" +"\r\n"+
  		  		"public int getCount"+biaoming.toUpperCase()+"(HashMap<String,String> params);" +"\r\n"+
  		  		"List<"+biaoming.toUpperCase()+"> find"+biaoming.toUpperCase()+"ByPageApp(HashMap<String, String> params,String order, String sort);"+"\r\n";
  		  
  		  str+="\r\n" +
  		  		"}";
  		  BufferedWriter output = new BufferedWriter(new FileWriter(f));
  		  output.write(str);
  		  output.close();
  		  
  		  System.out.println("服务类接口： "+fileOutPath+"  【生成完毕！】");
  	  } catch (Exception e) {
  		  e.printStackTrace();  
  	  }
    }

  /////////////////////////////////////////////服务实现类对象配置及输出
    public void outPutServiceImpl(){
	  String str = new String();//写入文本字符串
	  String biaoming="";
	  String pkg="";
	  String primarykey="";
	  String fileOutPath="";
	  String fileOutPath2="";
	  try {
		  Properties prop = new Properties(); 
	      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
	      prop.load(in); 
          biaoming = prop.getProperty("tablename").trim();
          pkg = prop.getProperty("package").trim(); 
          primarykey = prop.getProperty("primarykey").trim();
          fileOutPath2 = prop.getProperty("fileOutPath").trim();
          fileOutPath=createServiceImplDir(fileOutPath2, biaoming, pkg)+"\\"+biaoming.toUpperCase()+"ServiceImpl.java";
          //设置属性集合剔除掉这几个主要属性
          Set keySet=prop.keySet();
          keySet.remove("tablename");//剔除表名
          keySet.remove("package");//剔除包名
          keySet.remove("primarykey");//剔除主键
          keySet.remove("fileOutPath");//剔除输出路径
		  File f = new File(fileOutPath);
		  if(f.exists()){
		  }else{
			  f.createNewFile();//不存在则创建
		  }
		  str="package "+pkg+".service.impl;" +"\r\n"+
		  		"import java.text.SimpleDateFormat;" +"\r\n"+
		  		"import java.util.ArrayList;" +"\r\n"+
		  		"import java.util.Date;" +"\r\n"+
		  		"import java.util.HashMap;" +"\r\n"+
		  		"import java.util.List;" +"\r\n"+
		  		"import com.hrbsys.base.dao.BaseDao;"+"\r\n";
		  str+="public class "+biaoming.toUpperCase()+"ServiceImpl implements "+biaoming.toUpperCase()+"Service {" +"\r\n"+
		  		"	private BaseDao baseDao;	" +"\r\n"+
		  		"	public BaseDao getBaseDao() {" +"\r\n"+
		  		"   	return baseDao;" +"\r\n"+
		  		"	}" +"\r\n"+
		  		"	public void setBaseDao(BaseDao baseDao) {" +"\r\n"+
		  		"   	this.baseDao = baseDao;" +"\r\n"+
		  		"	}"+"\r\n"+"\r\n";
		  //新增
		  str+="    //add" +"\r\n"+
			   "	@Override" +"\r\n"+
		  	   "    public boolean add"+biaoming.toUpperCase()+"("+biaoming.toUpperCase()+" y) {" +"\r\n"+
		  	   "       SimpleDateFormat df = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");" +"\r\n"+
		  	   "       y.setDJRQ(df.format(new Date()).toString());" +"\r\n"+
		  	   "       y.setXGRQ(df.format(new Date()).toString());" +"\r\n"+
		  	   "       return baseDao.save(y);" +"\r\n"+
		  	   "    }"+"\r\n"+"\r\n";
		  //删除
		  str+="    //delete" +"\r\n"+
			   "	@Override " +"\r\n"+
		  	   " 	public boolean del"+biaoming.toUpperCase()+"(String y) {" +"\r\n"+
		  	   "		String[] ids = y.split(\",\");" +"\r\n"+
		  	   "		for (String id : ids) {"+biaoming.toUpperCase()+" yhz=find"+biaoming.toUpperCase()+"(id);" +"\r\n"+
		  	   "			if (!baseDao.delete(yhz)) {" +"\r\n"+
		  	   "				return false;" +"\r\n"+
		  	   "			}" +"\r\n"+
		  	   "		}" +"\r\n"+
		  	   "		return true;" +"\r\n"+
		  	   "	}"+"\r\n"+"\r\n";
		  //修改
		  str+="    //update" +"\r\n"+
			   "	@Override " +"\r\n"+
			   "	public boolean update"+biaoming.toUpperCase()+"("+biaoming.toUpperCase()+" t) {" +"\r\n"+
			   "		SimpleDateFormat df = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");" +"\r\n"+
			   "		"+biaoming.toUpperCase()+" tmpt = baseDao.findAll(\"from "+biaoming.toUpperCase()+" where "+primarykey.toUpperCase()+"='\" + t.get"+primarykey.toUpperCase()+"() + \"'\","+biaoming.toUpperCase()+".class).get(0);"+"\r\n";

		  Iterator itkeyset=keySet.iterator();
		  //生成字段
		  while(itkeyset.hasNext()){
			  String tmpkey=itkeyset.next().toString().trim();
		  str+="   	    tmpt.set"+tmpkey.toUpperCase()+"(t.get"+tmpkey.toUpperCase()+"());"+"\r\n";
		  }
		  str+="		tmpt.setXGRQ(df.format(new Date()).toString());" +"\r\n"+
			   "		return baseDao.update(tmpt);" +"\r\n"+
			   "	}"+"\r\n"+"\r\n";
		  //查询byID
		  str+="    //findById" +"\r\n"+
			   "	@Override" +"\r\n"+
		  	   "	public "+biaoming.toUpperCase()+" find"+biaoming.toUpperCase()+"(String t_id) {" +"\r\n"+
		  	   "		return baseDao.findAll(\"from "+biaoming.toUpperCase()+" where "+primarykey.toUpperCase()+"='\" + t_id + \"'\","+biaoming.toUpperCase()+".class).get(0);" +"\r\n"+
		  	   "	}"+"\r\n"+"\r\n";
		  
		  //查询
		  str+="    //findByPage" +"\r\n"+
			   "	@Override" +"\r\n"+
		  	   "	public List<"+biaoming.toUpperCase()+"> find"+biaoming.toUpperCase()+"ByPageApp(int start, int number,HashMap<String, String> params, String order, String sort) {" +"\r\n"+
		  	   "		String hql = \"from "+biaoming.toUpperCase()+" where 1=1 \";" +"\r\n"+
		  	   "		String param = \"\";" +"\r\n"+
		  	   "		ArrayList<String> params2 = new ArrayList<String>();" +"\r\n";
		  	   
		  Iterator itkeyforsel=keySet.iterator();
		  //生成字段
		  while(itkeyforsel.hasNext()){
			  String tmpkey=itkeyforsel.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
			  if("String".equals(tmpvalue)){
		  str+="		if (null != params.get(\""+tmpkey.toUpperCase()+"\") && !\"\".equals(params.get(\""+tmpkey.toUpperCase()+"\"))) {" +"\r\n"+
			   "			param = \"and "+tmpkey.toUpperCase()+" like '%\" + params.get(\""+tmpkey.toUpperCase()+"\").toString()" +"\r\n"+
	  	       "				+ \"%'\";" +"\r\n"+
	  	       "			params2.add(param);" +"\r\n"+
	  	       "			param=\"\";"+"\r\n"+
	  	       "		}" +"\r\n";
			  }
		  }
		  
		  str+="		List<"+biaoming.toUpperCase()+"> list = baseDao.findByPage(hql, "+biaoming.toUpperCase()+".class, start," +"\r\n"+
		  	   "		number, params2, order, sort);" +"\r\n"+
		  	   "		return list;" +"\r\n"+
		  	   "	}"+"\r\n"+"\r\n";
		  

		  str+="    //getCount" +"\r\n"+
		       "	@Override"+"\r\n"+
			   "	public int getCount"+biaoming.toUpperCase()+"(HashMap<String, String> params) {"+"\r\n"+
			   "		String hql = \"SELECT COUNT(*) from "+biaoming.toUpperCase()+" where 1=1 \";"+"\r\n";
		  Iterator it=keySet.iterator();
		  while(it.hasNext()){
			  String tmpkey=it.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
			  if("String".equals(tmpvalue)){
		  str+=   "		if (null != params.get(\""+tmpkey.toUpperCase()+"\")"+"\r\n"+
				  "				&& !\"\".equals(params.get(\""+tmpkey.toUpperCase()+"\"))) {"+"\r\n"+
				  "			hql += \"and "+tmpkey.toUpperCase()+" like '%\" + params.get(\""+tmpkey.toUpperCase()+"\").toString()"+"\r\n"+
				  "					+ \"%'\";"+"\r\n"+
				  "		}"+"\r\n";
			  }
		  }	   
		  str+="		int count = Integer.parseInt(baseDao.findAll(hql, java.lang.Long.class)"+"\r\n"+
			   "				.get(0).toString());"+"\r\n"+
			   "		return count;"+"\r\n"+
			   "	}"+"\r\n"+"\r\n";
		  //模糊分页查询
		  str+="    //findbypagelike"+"\r\n"+
			   "	@Override"+"\r\n"+
			   "	public List<"+biaoming.toUpperCase()+"> find"+biaoming.toUpperCase()+"ByPageApp(HashMap<String, String> params, String order, String sort) {"+"\r\n"+
			   "		String hql = \"from "+biaoming.toUpperCase()+" where 1=1\";"+"\r\n"+
			   "		String param=\"\";"+"\r\n"+
			   "		ArrayList<String> params2 = new ArrayList<String>();"+"\r\n";
		  Iterator it2=keySet.iterator();
		  while(it2.hasNext()){
			  String tmpkey=it2.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
			  if("String".equals(tmpvalue)){
		  str+="		if (null != params.get(\""+tmpkey.toUpperCase()+"\")"+"\r\n"+
			   "				&& !\"\".equals(params.get(\""+tmpkey.toUpperCase()+"\"))) {"+"\r\n"+
			   "			param = \"and "+tmpkey.toUpperCase()+" like '%\""+"\r\n"+
			   "					+ params.get(\""+tmpkey.toUpperCase()+"\").toString() + \"%'\";"+"\r\n"+
			   "			params2.add(param);"+"\r\n"+
			   "		}"+"\r\n";   
			  }
		  }
		  str+=  "		List<"+biaoming.toUpperCase()+"> list = baseDao.findAll(hql,"+biaoming.toUpperCase()+".class);"+"\r\n"+
				 "		return list;"+"\r\n"+
				 "	}"+"\r\n"+"\r\n";
		  
		  str+="}";
		  BufferedWriter output = new BufferedWriter(new FileWriter(f));   
		  output.write(str);   
		  output.close();  
		  System.out.println("服务实现类： "+fileOutPath+"  【生成完毕！】");
	  } catch (Exception e) {
		  e.printStackTrace();  
	  }
   }

    ////////////////////////////////////////////////////////////////Action类对象配置及输出
      public void outPutAction(){
  	  String str = new String();//写入文本字符串
  	  String biaoming="";
  	  String pkg="";
  	  String primarykey="";
  	  String fileOutPath="";
  	  String fileOutPath2="";
  	  try {
  		  Properties prop = new Properties(); 
  	      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
  	      prop.load(in); 
            biaoming = prop.getProperty("tablename").trim();
            pkg = prop.getProperty("package").trim(); 
            primarykey = prop.getProperty("primarykey").trim();
            fileOutPath2 = prop.getProperty("fileOutPath").trim();
            fileOutPath=createActionDir(fileOutPath2, biaoming, pkg)+"\\"+biaoming.toUpperCase()+"Action.java";
            //设置属性集合剔除掉这几个主要属性
            Set keySet=prop.keySet();
            keySet.remove("tablename");//剔除表名
            keySet.remove("package");//剔除包名
            keySet.remove("primarykey");//剔除主键
            keySet.remove("fileOutPath");//剔除输出路径
  		  File f = new File(fileOutPath);
  		  if(f.exists()){
  		  }else{
  			  f.createNewFile();//不存在则创建
  		  }
//  		  BufferedReader input = new BufferedReader(new FileReader(f));
  		    str="package "+pkg+".action;"+"\r\n"+
  				"import java.util.HashMap;"+"\r\n"+
  				"import java.util.List;"+"\r\n"+
  				"import java.util.Map;"+"\r\n"+
  				"import net.sf.json.JSONArray;"+"\r\n"+
  				"import net.sf.json.JSONObject;"+"\r\n"+
  				"import net.sf.json.JsonConfig;"+"\r\n"+
  				"import com.hrbsys.base.action.ActionBase;"+"\r\n"+
  				"import com.hrbsys.bean."+biaoming.toUpperCase()+";"+"\r\n"+
  				"import com.hrbsys.tools.JsonPrintTools;"+"\r\n"+
  				"import com.hrbsys.tools.UUIDTools;"+"\r\n"+
  				"import "+pkg+".service."+biaoming.toUpperCase()+"Service;"+"\r\n"+
  				"public class "+biaoming.toUpperCase()+"Action extends ActionBase {"+"\r\n"+
  				"	private static final long serialVersionUID = 1L;"+"\r\n"+
  				"	private "+biaoming.toUpperCase()+"Service "+biaoming.toLowerCase()+"Service;"+"\r\n"+
  				"	private String optionflag;"+"\r\n"+
  				"	private String "+primarykey.toUpperCase()+"; "+"\r\n";
  		    	


  			

  		    
  		    Iterator itkeys_private=keySet.iterator();
  		    while(itkeys_private.hasNext()){
			  String tmpkey=itkeys_private.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
			  str+="	private "+tmpvalue+" "+tmpkey.toUpperCase()+";"+"\r\n";
  		      str+="";
  		    }
  		    str+="	// 分页相关"+"\r\n"+
  		    	 "	private String rows; // 每页显示的记录数"+"\r\n"+
  		    	 "	private String page;// 当前页码(第几页)"+"\r\n"+
  		    	 "	private JSONObject result;"+"\r\n"+
  		    	 "	// 排序相关"+"\r\n"+
  		    	 "	private String order;"+"\r\n"+
  		    	 "	private String sort;"+"\r\n";
  		    str+="\r\n";
 
  		    //列表
  		    str+="	public void list"+biaoming.toUpperCase()+"() throws Exception {"+"\r\n"+
  		    	 "		HashMap<String, String> params = new HashMap<String, String>();"+"\r\n"+
  		    	 "		int intPage = Integer.parseInt((page == null || page == \"0\") ? \"1\": page);"+"\r\n"+
  		    	 "		int number = Integer.parseInt((rows == null || rows == \"0\") ? \"10\": rows);"+"\r\n"+
  		    	 "		int start = (intPage - 1) * number;"+"\r\n";
  		    System.out.println("请将,列表：list"+biaoming.toUpperCase()+"  Action请求配置到系统的模块中，并将其赋予角色“开发者权限！”");		
  		    Iterator itkeys_listt=keySet.iterator();
  		    while(itkeys_listt.hasNext()){
			  String tmpkey=itkeys_listt.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
		    str+="		params.put(\""+tmpkey.toUpperCase()+"\", this."+tmpkey.toUpperCase()+");"+"\r\n";
  		    }
  		    str+="		List<"+biaoming.toUpperCase()+"> list = "+biaoming.toLowerCase()+"Service.find"+biaoming.toUpperCase()+"ByPageApp(start, number,params, order, sort);// 每页的数据，放入list"+"\r\n"+
  		    	 "		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map"+"\r\n"+
  		    	 "		jsonMap.put(\"total\", "+biaoming.toLowerCase()+"Service.getCount"+biaoming.toUpperCase()+"(params));"+"\r\n"+
  		    	 "		jsonMap.put(\"rows\", list);// rows键 存放每页记录 list"+"\r\n"+
  		    	 "		jsonMap.put(\"page\", intPage);"+"\r\n"+
  		    	 "		JsonConfig config = new JsonConfig();"+"\r\n"+
  		    	 "//		config.setExcludes(new String[]{\"yonghus\"});//除去级联属性"+"\r\n"+
  		    	 "		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));"+"\r\n"+
  		    	 "	}"+"\r\n"+"\r\n";
  		    
  		    //新增
  		    str+="	public void add"+biaoming.toUpperCase()+"() throws Exception {"+"\r\n"+
  		    		"		Map<String, Object> jsonMap = new HashMap<String, Object>();"+"\r\n"+
  		    		"		"+biaoming.toUpperCase()+" t = new "+biaoming.toUpperCase()+"();"+"\r\n";
  		    str+="		t.set"+primarykey.toUpperCase()+"(UUIDTools.getUUID());"+"\r\n";
  		    
  		    System.out.println("请将,新增：add"+biaoming.toUpperCase()+"  Action请求配置到系统的模块中，并将其赋予角色“开发者权限！”");
  		    
  		    Iterator itkeys_add_setpri=keySet.iterator();
  		    while(itkeys_add_setpri.hasNext()){
			  String tmpkey=itkeys_add_setpri.next().toString().trim();
			  String tmpvalue=prop.getProperty(tmpkey);
		    str+="		t.set"+tmpkey.toUpperCase()+"(this.get"+tmpkey.toUpperCase()+"());"+"\r\n";
  		    }
  		    
  		    		
    		   str+="		if ("+biaoming.toLowerCase()+"Service.add"+biaoming.toUpperCase()+"(t)) {"+"\r\n"+
  		    		"			jsonMap.put(\"success\", true);"+"\r\n"+
  		    		"			jsonMap.put(\"message\", \"信息新增成功！\");"+"\r\n"+
  		    		"			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
  		    		"			return;"+"\r\n"+
  		    		"		} else {"+"\r\n"+
  		    		"			jsonMap.put(\"success\", false);"+"\r\n"+
  		    		"			jsonMap.put(\"message\", \"信息添加失败！\");"+"\r\n"+
  		    		"			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
  		    		"			return;"+"\r\n"+
  		    		"		}"+"\r\n"+
  		    		"	}"+"\r\n"+"\r\n";
  		    
  		    //删除
    		 str+="	public void del"+biaoming.toUpperCase()+"() throws Exception {"+"\r\n"+
    				 "		Map<String, Object> jsonMap = new HashMap<String, Object>();"+"\r\n"+
    				 "		if ((null != "+primarykey.toUpperCase()+") && !\"\".equals("+primarykey.toUpperCase()+")) {"+"\r\n"+
    				 "			if ("+biaoming.toLowerCase()+"Service.del"+biaoming.toUpperCase()+"("+primarykey.toUpperCase()+")) {"+"\r\n"+
    				 "				jsonMap.put(\"success\", true);"+"\r\n"+
    				 "				jsonMap.put(\"message\", \"信息删除成功！\");"+"\r\n"+
    				 "				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
    				 "			} else {"+"\r\n"+
    				 "				jsonMap.put(\"success\", false);"+"\r\n"+
    				 "				jsonMap.put(\"message\", \"信息删除失败！\");"+"\r\n"+
    				 "				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
    				 "			}"+"\r\n"+
    				 "		}"+"\r\n"+
    				 "	}"+"\r\n"+"\r\n";
    		 System.out.println("请将,删除：del"+biaoming.toUpperCase()+"  Action请求配置到系统的模块中，并将其赋予角色“开发者权限！”");
    		 //修改
  		   str+="	public void update"+biaoming.toUpperCase()+"() throws Exception {"+"\r\n"+
  				 "		Map<String, Object> jsonMap = new HashMap<String, Object>();"+"\r\n"+
  				"		JsonConfig config = new JsonConfig();"+"\r\n"+
  				"//		config.setExcludes(new String[]{\"yonghus\"});//除去级联属性"+"\r\n"+
  				"		if (\"\".equals("+primarykey.toUpperCase()+") || null == "+primarykey.toUpperCase()+") {"+"\r\n"+
  				"			jsonMap.put(\"success\", false);"+"\r\n"+
  				"			jsonMap.put(\"message\", \"没有ID编号！\");"+"\r\n"+
  				"			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
  				"			return;"+"\r\n"+
  				"		}"+"\r\n"+
  				"		"+biaoming.toUpperCase()+" t = new "+biaoming.toUpperCase()+"();"+"\r\n"+
  				"		if ((null != optionflag) && !\"\".equals(optionflag)) {"+"\r\n"+
  				"			if (\"update"+biaoming.toUpperCase()+"\".equals(optionflag)) {"+"\r\n"+
  				"				t.set"+primarykey.toUpperCase()+"("+primarykey.toUpperCase()+");"+"\r\n";
  		   System.out.println("请将,修改：update"+biaoming.toUpperCase()+"  Action请求配置到系统的模块中，并将其赋予角色“开发者权限！”");
	 		    Iterator itkeys_update_setpri=keySet.iterator();
	 		    while(itkeys_update_setpri.hasNext()){
				  String tmpkey=itkeys_update_setpri.next().toString().trim();
				  String tmpvalue=prop.getProperty(tmpkey);
		   str+="				t.set"+tmpkey.toUpperCase()+"(this.get"+tmpkey.toUpperCase()+"());"+"\r\n";
	 		    }
  		   str+="				if ("+biaoming.toLowerCase()+"Service.update"+biaoming.toUpperCase()+"(t)) {"+"\r\n"+
  				"					jsonMap.put(\"success\", true);"+"\r\n"+
  				"					jsonMap.put(\"message\", \"修改信息成功！\");"+"\r\n"+
  				"					new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
  				"					return;"+"\r\n"+
  				"				} else {"+"\r\n"+
  				"					jsonMap.put(\"success\", false);"+"\r\n"+
  				"					jsonMap.put(\"message\", \"修改信息失败！\");"+"\r\n"+
  				"					new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));"+"\r\n"+
  				"					return;"+"\r\n"+
  				"				}"+"\r\n"+
  				"			}"+"\r\n"+
  				"		}"+"\r\n"+
  				"		new JsonPrintTools().printJSON(JSONObject.fromObject("+biaoming.toLowerCase()+"Service.find"+biaoming.toUpperCase()+"("+primarykey.toUpperCase()+"),config));"+"\r\n"+
  				"	}"+"\r\n"+"\r\n";
    		 
    		 
    		 
    		 
    	str+="	public void listAll"+biaoming.toUpperCase()+"() throws Exception {"+"\r\n"+
    			"		HashMap<String, String> params = new HashMap<String, String>();"+"\r\n";
    	System.out.println("请将,查找全部：listAll"+biaoming.toUpperCase()+"  Action请求配置到系统的模块中，并将其赋予角色“开发者权限！”");
		    Iterator itkeys_listall=keySet.iterator();
		    while(itkeys_listall.hasNext()){
		  String tmpkey=itkeys_listall.next().toString().trim();
		  String tmpvalue=prop.getProperty(tmpkey);
	       str+="		params.put(\""+tmpkey.toUpperCase()+"\", this."+tmpkey.toUpperCase()+");"+"\r\n";
		    }
		   str+="		List<"+biaoming.toUpperCase()+"> list = "+biaoming.toLowerCase()+"Service.find"+biaoming.toUpperCase()+"ByPageApp(params, order, sort);// 每页的数据，放入list"+"\r\n"+
    			"		JsonConfig config = new JsonConfig();"+"\r\n"+
    			"//		config.setExcludes(new String[]{\"yonghuzu\"});//除去级联属性"+"\r\n"+
    			"		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));"+"\r\n"+
    			"	}"+"\r\n"+"\r\n"; 
		   
		    Iterator itkeys_setget=keySet.iterator();
		    while(itkeys_setget.hasNext()){
		    	String tmpkey=itkeys_setget.next().toString().trim();
		    	String tmpvalue=prop.getProperty(tmpkey);
		    str+="		public "+tmpvalue+" get"+tmpkey.toUpperCase()+"() {"+"\r\n" +
		    	 "			return "+tmpkey.toUpperCase()+";"+"\r\n" +
		    	 "		}"+"\r\n";
		    str+="		public void set"+tmpkey.toUpperCase()+"("+tmpvalue+" "+tmpkey.toUpperCase()+") {"+"\r\n" +
		    	 "			this."+tmpkey.toUpperCase()+" = "+tmpkey.toUpperCase()+";" +"\r\n"+
		    	 "		}"+"\r\n";
		    str+="\r\n";
		    }
		   

		    
//		    "+biaoming.toLowerCase()+"Service;"
		    str+="		public "+biaoming.toUpperCase()+"Service get"+biaoming.toUpperCase()+"() {"+"\r\n" +
			     "			return "+biaoming.toLowerCase()+"Service;"+"\r\n" +
			     "		}"+"\r\n"+"\r\n";
			str+="		public void set"+biaoming.toLowerCase()+"Service("+biaoming.toUpperCase()+"Service "+biaoming.toLowerCase()+"Service) {"+"\r\n" +
			     "			this."+biaoming.toLowerCase()+"Service = "+biaoming.toLowerCase()+"Service;"+"\r\n" +
			     "		}"+"\r\n"+"\r\n";
			
		   
	  		str+="		public String get"+primarykey.toUpperCase()+"() {"+"\r\n" +
	  	  		 "			return "+primarykey.toUpperCase()+";"+"\r\n" +
	  	  		 "		}"+"\r\n";
	  	  	str+="		public void set"+primarykey.toUpperCase()+"(String "+primarykey.toUpperCase()+") {"+"\r\n" +
	  	  		 "			this."+primarykey.toUpperCase()+" = "+primarykey.toUpperCase()+";"+"\r\n" +
	  	  		 "		}"+"\r\n";
	  	  	  
	  	  	str+="		public String getOptionflag() {"+"\r\n" +
	  	  		 "			return optionflag;"+"\r\n" +
	  	  		 "		}" +"\r\n"+"\r\n"+
	  	  		 "		public void setOptionflag(String optionflag) {"+"\r\n" +
	  	  		 "			this.optionflag = optionflag;	" +"\r\n"+
	  	  		 "		}"+"\r\n";
			str+="		public String getRows() {"+"\r\n"+
					"			return rows;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public void setRows(String rows) {"+"\r\n"+
					"			this.rows = rows;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public String getPage() {"+"\r\n"+
					"			return page;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public void setPage(String page) {"+"\r\n"+
					"			this.page = page;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public JSONObject getResult() {"+"\r\n"+
					"			return result;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public void setResult(JSONObject result) {"+"\r\n"+
					"			this.result = result;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public String getOrder() {"+"\r\n"+
					"			return order;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public void setOrder(String order) {"+"\r\n"+
					"			this.order = order;"+"\r\n"+
					"		}"+"\r\n"+
					"		public String getSort() {"+"\r\n"+
					"			return sort;"+"\r\n"+
					"		}"+"\r\n"+"\r\n"+
					"		public void setSort(String sort) {"+"\r\n"+
					"			this.sort = sort;"+"\r\n"+
					"		}"+"\r\n";
  		  str+="}";
  		  BufferedWriter output = new BufferedWriter(new FileWriter(f));   
  		  output.write(str);   
  		  output.close();  
  		  System.out.println("Action类： "+fileOutPath+"  【生成完毕！】");
  	  } catch (Exception e) {
  		  e.printStackTrace();
  	  }
     }
    
//////////////////////////////////////////////////////////////////生成模块的Struts配置文件
      public void outPutStrutsConfigFile(){
  	  String str = new String();//写入文本字符串
  	  String biaoming="";
  	  String pkg="";
  	  String primarykey="";
  	  String fileOutPath="";
  	  String fileOutPath2="";
  	  try {
  		  Properties prop = new Properties(); 
  	      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
  	      prop.load(in); 
            biaoming = prop.getProperty("tablename").trim();
            pkg = prop.getProperty("package").trim(); 
            primarykey = prop.getProperty("primarykey").trim();
            fileOutPath2 = prop.getProperty("fileOutPath").trim();
            createDir(fileOutPath2+"\\src\\");
            fileOutPath=fileOutPath2+"\\src\\"+biaoming.toLowerCase()+".xml";
            //设置属性集合剔除掉这几个主要属性
            Set keySet=prop.keySet();
            keySet.remove("tablename");//剔除表名
            keySet.remove("package");//剔除包名
            keySet.remove("primarykey");//剔除主键
            keySet.remove("fileOutPath");//剔除输出路径
  		  File f = new File(fileOutPath);
  		  if(f.exists()){
  		  }else{
  			  f.createNewFile();//不存在则创建
  		  }
  		  
  		  
  		  str+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\r\n"+
  				"<!DOCTYPE struts PUBLIC \"-//Apache Software Foundation//DTD Struts Configuration 2.3//EN\" "+"\r\n"+
  				"\"http://struts.apache.org/dtds/struts-2.3.dtd\">"+"\r\n"+
  				"<struts>"+"\r\n"+
  				"	<package name=\""+biaoming.toLowerCase()+"Package\" extends=\"login-jsondefault\">"+"\r\n"+
  				"		<!--列表-->"+"\r\n"+
  				"		<action name=\"list"+biaoming.toUpperCase()+"\" class=\""+biaoming.toLowerCase()+"action\" method=\"list"+biaoming.toUpperCase()+"\">"+"\r\n"+
  				"		</action>"+"\r\n"+
  				"		<!--列表不分页-->"+"\r\n"+
  				"		<action name=\"listAll"+biaoming.toUpperCase()+"\" class=\""+biaoming.toLowerCase()+"action\" method=\"listAll"+biaoming.toUpperCase()+"\">"+"\r\n"+
  				"		</action>"+"\r\n"+
  				"		<!-- 新增 -->"+"\r\n"+
  				"		<action name=\"add"+biaoming.toUpperCase()+"\" class=\""+biaoming.toLowerCase()+"action\" method=\"add"+biaoming.toUpperCase()+"\">"+"\r\n"+
  				"			<result name=\"success\" type=\"json\"></result>"+"\r\n"+
  				"		</action>"+"\r\n"+
  				"		<!-- 修改 -->"+"\r\n"+
  				"		<action name=\"update"+biaoming.toUpperCase()+"\" class=\""+biaoming.toLowerCase()+"action\" method=\"update"+biaoming.toUpperCase()+"\">"+"\r\n"+
  				"			<result name=\"success\" type=\"json\"></result>	"+"\r\n"+
  				"		</action>"+"\r\n"+
  				"		<!-- 删除 -->"+"\r\n"+
  				"		<action name=\"del"+biaoming.toUpperCase()+"\" class=\""+biaoming.toLowerCase()+"action\" method=\"del"+biaoming.toUpperCase()+"\">"+"\r\n"+
  				"			<result name=\"success\" type=\"json\"></result>"+"\r\n"+
  				"		</action>"+"\r\n"+
  				"	</package>"+"\r\n"+
  				"</struts>"+"\r\n"+"\r\n";

  		  BufferedWriter output = new BufferedWriter(new FileWriter(f));   
  		  output.write(str);   
  		  output.close();  
  		  System.out.println("模块struts配置文件： "+fileOutPath+"  【生成完毕！】"+"请将其配置到struts.xml文件中："+"<include file=\""+biaoming.toLowerCase()+".xml\"></include>");
  	  } catch (Exception e) {
  		  e.printStackTrace();
  	  }
     }      
    
//////////////////////////////////////////////////////////////////生成模块的spring配置文件
public void outPutSpringConfigFile(){
String str = new String();//写入文本字符串
String biaoming="";
String pkg="";
String primarykey="";
String fileOutPath="";
String fileOutPath2="";
try {
Properties prop = new Properties(); 
InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
prop.load(in); 
biaoming = prop.getProperty("tablename").trim();
pkg = prop.getProperty("package").trim(); 
primarykey = prop.getProperty("primarykey").trim();
fileOutPath2 = prop.getProperty("fileOutPath").trim();
createDir(fileOutPath2+"\\src\\config\\");
fileOutPath=fileOutPath2+"\\src\\config\\applicationContext-"+biaoming.toLowerCase()+".xml";
//设置属性集合剔除掉这几个主要属性
Set keySet=prop.keySet();
keySet.remove("tablename");//剔除表名
keySet.remove("package");//剔除包名
keySet.remove("primarykey");//剔除主键
keySet.remove("fileOutPath");//剔除输出路径
File f = new File(fileOutPath);
if(f.exists()){
}else{
f.createNewFile();//不存在则创建
}
str+="<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+"\r\n"+
		"<beans xmlns=\"http://www.springframework.org/schema/beans\""+"\r\n"+
		"	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:p=\"http://www.springframework.org/schema/p\""+"\r\n"+
		"	xmlns:tx=\"http://www.springframework.org/schema/tx\" xmlns:context=\"http://www.springframework.org/schema/context\""+"\r\n"+
		"	xsi:schemaLocation=\"http://www.springframework.org/schema/beans"+"\r\n"+
		"http://www.springframework.org/schema/beans/spring-beans-3.0.xsd"+"\r\n"+
		"http://www.springframework.org/schema/tx"+"\r\n"+
		"http://www.springframework.org/schema/tx/spring-tx-3.0.xsd"+"\r\n"+
		"http://www.springframework.org/schema/aop"+"\r\n"+
		"http://www.springframework.org/schema/aop/spring-aop-3.0.xsd"+"\r\n"+
		"http://www.springframework.org/schema/context"+"\r\n"+
		"http://www.springframework.org/schema/context/spring-context-3.0.xsd\">"+"\r\n"+
		"	<!-- "+biaoming.toLowerCase()+"service -->"+"\r\n"+
		"	<bean id=\""+biaoming.toLowerCase()+"Service\" class=\""+pkg+".service.impl."+biaoming.toUpperCase()+"ServiceImpl\""+"\r\n"+
		"		scope=\"singleton\">"+"\r\n"+
		"		<property name=\"baseDao\">"+"\r\n"+
		"			<ref bean=\"baseDao\" />"+"\r\n"+
		"		</property>"+"\r\n"+
		"	</bean>	"+"\r\n"+
		"	<!-- "+biaoming.toLowerCase()+"action -->"+"\r\n"+
		"	<bean id=\""+biaoming.toLowerCase()+"action\" class=\""+pkg+".action."+biaoming.toUpperCase()+"Action\""+"\r\n"+
		"		scope=\"prototype\">"+"\r\n"+
		"		<property name=\""+biaoming.toLowerCase()+"Service\">"+"\r\n"+
		"			<ref local=\""+biaoming.toLowerCase()+"Service\" />"+"\r\n"+
		"		</property>"+"\r\n"+
		"	</bean>	"+"\r\n"+
		"</beans>"+"\r\n"+"\r\n";

BufferedWriter output = new BufferedWriter(new FileWriter(f));   
output.write(str);   
output.close();  
System.out.println("模块spring配置文件： "+fileOutPath+"  【生成完毕！】");
} catch (Exception e) {
e.printStackTrace();
}
}      
   
    
    
////////////////////////////////////////////////////////////////生成JSP页面
public void outPutJSPFile(){
  String str = new String();//写入文本字符串
  String biaoming="";
  String pkg="";
  String primarykey="";
  String fileOutPath="";
  String fileOutPath2="";
  try {
	  Properties prop = new Properties(); 
      InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
      prop.load(in); 
      biaoming = prop.getProperty("tablename").trim();
      pkg = prop.getProperty("package").trim(); 
      primarykey = prop.getProperty("primarykey").trim();
      fileOutPath2 = prop.getProperty("fileOutPath").trim();
      fileOutPath=createJSPDir(fileOutPath2, biaoming)+"\\listall_"+biaoming.toLowerCase()+".jsp";
      //设置属性集合剔除掉这几个主要属性
      Set keySet=prop.keySet();
      keySet.remove("tablename");//剔除表名
      keySet.remove("package");//剔除包名
      keySet.remove("primarykey");//剔除主键
      keySet.remove("fileOutPath");//剔除输出路径
	  File f = new File(fileOutPath);
	  if(f.exists()){
	  }else{
		  f.createNewFile();//不存在则创建
	  }
	  str="<%@ page language=\"java\" import=\"java.util.*\" pageEncoding=\"UTF-8\"%>"+"\r\n"+
			  "<%"+"\r\n"+
			  "	String path = request.getContextPath();"+"\r\n"+
			  "	String basePath = request.getScheme() + \"://\""+"\r\n"+
			  "			+ request.getServerName() + \":\" + request.getServerPort()"+"\r\n"+
			  "			+ path + \"/\";"+"\r\n"+
			  "%>"+"\r\n"+
			  "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"+"\r\n"+
			  "<html>"+"\r\n"+
			  "<head>"+"\r\n"+
			  "<base href=\"<%=basePath%>\">"+"\r\n"+
			  "<title>列表</title>"+"\r\n"+
			  "<meta http-equiv=\"pragma\" content=\"no-cache\">"+"\r\n"+
			  "<meta http-equiv=\"cache-control\" content=\"no-cache\">"+"\r\n"+
			  "<meta http-equiv=\"expires\" content=\"0\">"+"\r\n"+
			  "<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">"+"\r\n"+
			  "<meta http-equiv=\"description\" content=\"This is my page\">"+"\r\n"+
			  "<style type=\"text/css\">"+"\r\n"+
			  ".td {"+"\r\n"+
			  "	border: solid #99BBE8;"+"\r\n"+
			  "	border-width: 0px 1px 1px 0px;"+"\r\n"+
			  "}"+"\r\n"+
			  ".table {"+"\r\n"+
			  "	border: solid #99BBE8;"+"\r\n"+
			  "	border-width: 1px 0px 0px 1px;"+"\r\n"+
			  "}"+"\r\n"+
			  "</style>"+"\r\n"+
			  "<jsp:include page=\"../common/include.jsp\"></jsp:include>"+"\r\n"+
			  "</head>"+"\r\n"+
			  "<body>"+"\r\n"+
			  "	<script type=\"text/javascript\" src=\"<%=basePath%>js/"+biaoming.toLowerCase()+"/"+biaoming.toLowerCase()+".js\"></script>"+"\r\n"+
			  "	<table id=\"datagrid\" toolbar=\"#DIV_toolbar\"></table>"+"\r\n"+
			  "	<div id=\"DIV_toolbar\" style=\"padding:3px;padding-top:0px;  margin-left: 5px;\">"+"\r\n"+  
			  "		<a onclick=\"add"+biaoming.toUpperCase()+"_dialog();\" plain=\"true\" class=\"easyui-linkbutton\" iconCls=\"icon-add\">新增信息</a>"+"\r\n"+  
			  "		<a onclick=\"showEnterDialog();\" plain=\"true\" class=\"easyui-linkbutton\" iconCls=\"icon-remove\">删除信息</a>"+"\r\n"+
			  "		<br/>"+"\r\n";
	    Iterator itkeys_listall=keySet.iterator();
	    int ziduani=1;
	    while(itkeys_listall.hasNext()){
	    	String tmpkey=itkeys_listall.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	str+="	    <span>字段"+ziduani+":</span>"+"\r\n"+  
	    		 "	    <input id=\""+tmpkey.toUpperCase()+"_serchbar\" style=\"line-height:26px;border:1px solid #ccc\" name=\""+tmpkey.toUpperCase()+"\" />"+"\r\n";
	    	ziduani=ziduani+1;
	    }	  

	    str+="	    <a class=\"easyui-linkbutton\" plain=\"true\" onclick=\"doSearch();\"><b>搜索</b></a>  "+"\r\n"+
			  "	</div>  "+"\r\n"+
			  "	<!-- 新增对话框  BEGIN-->"+"\r\n"+
			  "	<div id=\"add"+biaoming.toUpperCase()+"_dlg\" class=\"easyui-dialog\""+"\r\n"+
			  "		style=\"width:400px;height:220px;padding:10px 20px\" closed=\"true\""+"\r\n"+
			  "		buttons=\"#add"+biaoming.toUpperCase()+"_dlg-buttons\" modal=\"true\">"+"\r\n"+
			  "		<form id=\"add"+biaoming.toUpperCase()+"_form\" method=\"post\">"+"\r\n";
	    
	    
	    Iterator itkeys_add=keySet.iterator();
	    ziduani=1;
	    while(itkeys_add.hasNext()){
	    	String tmpkey=itkeys_add.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	
	    	str+= "			<div>"+"\r\n"+
	  			  "				<label >字段"+ziduani+":</label>"+"\r\n"+ 
	  			  "				<input class=\"easyui-validatebox\" type=\"text\" id=\""+tmpkey.toUpperCase()+"\" name=\""+tmpkey.toUpperCase()+"\" data-options=\"required:true\" />"+"\r\n"+
	  			  "			</div>"+"\r\n";
	    	ziduani++;
	    }		    
	    
	    str+="		</form>"+"\r\n"+
			  "	</div>"+"\r\n"+
			  "	<div id=\"add"+biaoming.toUpperCase()+"_dlg-buttons\">"+"\r\n"+
			  "		<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\" onclick=\"add"+biaoming.toUpperCase()+"();\" iconCls=\"icon-ok\">保存</a>"+"\r\n"+
			  "	</div>"+"\r\n"+
			  "	<!-- 新增对话框  END-->"+"\r\n"+
			  "		<!-- 查看/修改对话框  BEGIN-->"+"\r\n"+
			  "	<div id=\"update"+biaoming.toUpperCase()+"_dlg\" class=\"easyui-dialog\""+"\r\n"+
			  "		style=\"width:400px;height:220px;padding:10px 20px\" closed=\"true\""+"\r\n"+
			  "		buttons=\"#update_"+biaoming.toUpperCase()+"_dlg-buttons\" modal=\"true\">"+"\r\n"+
			  "		<form id=\"update"+biaoming.toUpperCase()+"_form\" method=\"post\">"+"\r\n";
	    
			

	    
	    
	    Iterator itkeys_update=keySet.iterator();
	    ziduani=1;
	    while(itkeys_update.hasNext()){
	    	String tmpkey=itkeys_update.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	
	    	str+= "			<div>"+"\r\n"+
	  			  "				<label >字段"+ziduani+":</label> "+"\r\n"+
				  "				<input type=\"text\" id=\"update_"+tmpkey.toUpperCase()+"\" name=\""+tmpkey.toUpperCase()+"\"/> "+"\r\n"+
				  "			</div>"+"\r\n";
	    	ziduani++;
	    }	
	    str+=
				  "				<input type=\"hidden\" id=\"update_"+primarykey.toUpperCase()+"\" name=\""+primarykey.toUpperCase()+"\" />"+"\r\n"+
				  "				<input type=\"hidden\" id=\"update_optionflag\" name=\"optionflag\"/>	"+"\r\n";
	    	  
			  
	    str+="		</form>"+"\r\n"+
			  "	</div>"+"\r\n"+
			  "	<div id=\"update_"+biaoming.toUpperCase()+"_dlg-buttons\">"+"\r\n"+
			  "		<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\""+"\r\n"+
			  "			onclick=\"update"+biaoming.toUpperCase()+"();\" iconCls=\"icon-ok\">修改信息</a>"+"\r\n"+
			  "	</div>"+"\r\n"+
			  "	<!-- 查看/修改对话框  END-->"+"\r\n"+
			  "</body>"+"\r\n"+
			  "</html>"+"\r\n"+"\r\n";
	  BufferedWriter output = new BufferedWriter(new FileWriter(f));   
	  output.write(str);   
	  output.close();  
	  System.out.println("JSP页面： "+fileOutPath+"  【生成完毕！】");
  } catch (Exception e) {
	  e.printStackTrace();
  }
}    
    
    
    

////////////////////////////////////////////////////////////////生成javascript
public void outPutJavaScriptFile(){
String str = new String();//写入文本字符串
String biaoming="";
String pkg="";
String primarykey="";
String fileOutPath="";
String fileOutPath2="";
try {
Properties prop = new Properties(); 
InputStream in = Object.class.getResourceAsStream("/bean.properties"); 
prop.load(in); 
biaoming = prop.getProperty("tablename").trim();
pkg = prop.getProperty("package").trim(); 
primarykey = prop.getProperty("primarykey").trim();
fileOutPath2 = prop.getProperty("fileOutPath").trim();
fileOutPath=createJSDir(fileOutPath2, biaoming)+"\\"+biaoming.toLowerCase()+".js";
//设置属性集合剔除掉这几个主要属性
Set keySet=prop.keySet();
keySet.remove("tablename");//剔除表名
keySet.remove("package");//剔除包名
keySet.remove("primarykey");//剔除主键
keySet.remove("fileOutPath");//剔除输出路径
File f = new File(fileOutPath);
if(f.exists()){
}else{
f.createNewFile();//不存在则创建
}
str="//模糊查询"+"\r\n"+
		"function doSearch() {"+"\r\n"+
		"	$('#datagrid').datagrid('load', {"+"\r\n";
	    Iterator itkeys_selbylike=keySet.iterator();
	    
	    while(itkeys_selbylike.hasNext()){
	    	String tmpkey=itkeys_selbylike.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	str+="		"+tmpkey.toUpperCase()+" : $('#"+tmpkey.toUpperCase()+"_serchbar').val(),"+"\r\n";
	    }	

		str+="		optionflag : 'selbylike'"+"\r\n"+
		"	});"+"\r\n"+
		"}"+"\r\n"+
		"// 打开新增信息对话框"+"\r\n"+
		"function add"+biaoming.toUpperCase()+"_dialog() {"+"\r\n"+
		"	$('#add"+biaoming.toUpperCase()+"_dlg').dialog('open').dialog('setTitle', '新增信息'); // 弹出窗口"+"\r\n"+
		"	$.parser.parse('#add"+biaoming.toUpperCase()+"_dlg'); // 需要重新渲染对话框，否则easyui不起作用"+"\r\n"+
		"}"+"\r\n"+
		"// 新增信息"+"\r\n"+
		"function add"+biaoming.toUpperCase()+"() {"+"\r\n"+
		"	$('#add"+biaoming.toUpperCase()+"_form').form('submit', {"+"\r\n"+
		"		url : 'add"+biaoming.toUpperCase()+".action',"+"\r\n"+
		"		onSubmit : function() {"+"\r\n"+
		"		},"+"\r\n"+
		"		success : function(data) {"+"\r\n"+
		"			if (\"false\" == data) {"+"\r\n"+
		"				$.messager.alert('提示', '新增信息失败！');"+"\r\n"+
		"			} else {"+"\r\n"+
		"				var obj2 = eval(\"(\" + data + \")\");"+"\r\n"+
		"				$.messager.alert('提示', obj2.message);"+"\r\n"+
		"				$('#add"+biaoming.toUpperCase()+"_form').form('clear');"+"\r\n"+
		"				$('#add"+biaoming.toUpperCase()+"_dlg').dialog('close');"+"\r\n"+
		"			}"+"\r\n"+
		"			$('#datagrid').datagrid('clearSelections');"+"\r\n"+
		"			$('#datagrid').datagrid('reload');"+"\r\n"+
		"		}"+"\r\n"+
		"	});"+"\r\n"+
		"}"+"\r\n"+
		"// 打开修改对话框"+"\r\n"+
		"function update"+biaoming.toUpperCase()+"_dialog() {"+"\r\n"+
		"	var row = $('#datagrid').datagrid('getSelected');"+"\r\n"+
		"	var NowTime = new Date().toLocaleTimeString();"+"\r\n"+
		"	if (typeof(row) == \"undefined\") { "+"\r\n"+
		"		return ;"+"\r\n"+
		"	}"+"\r\n"+
		"	if (!row && typeof(row)!=\"undefined\" && row!=0){   "+"\r\n"+
		"		   return ;"+"\r\n"+
		"	}"+"\r\n"+
		"	if (row."+primarykey.toUpperCase()+") {"+"\r\n"+
		"		$.ajax({"+"\r\n"+
		"			url : \"update"+biaoming.toUpperCase()+".action?nowtime=\" + NowTime + \"&"+primarykey.toUpperCase()+"=\""+"\r\n"+
		"					+ row."+primarykey.toUpperCase()+","+"\r\n"+
		"			context : document.body,"+"\r\n"+
		"			success : function(data) {"+"\r\n"+
		"				var yhzobj = eval(\"(\" + data + \")\");"+"\r\n"+
		"				$('#update"+biaoming.toUpperCase()+"_dlg').dialog('open').dialog('setTitle',"+"\r\n"+
		"						'查看/修改信息'); // 弹出窗口"+"\r\n"+
		"				$.parser.parse('#update"+biaoming.toUpperCase()+"_dlg'); // 需要重新渲染对话框，否则easyui不起作用"+"\r\n";

	    Iterator itkeys_update=keySet.iterator();
	    while(itkeys_update.hasNext()){
	    	String tmpkey=itkeys_update.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	str+="				$('#update_"+tmpkey.toUpperCase()+"').val(yhzobj."+tmpkey.toUpperCase()+");"+"\r\n";
	    }	
		
	    str+="				$('#update_"+primarykey.toUpperCase()+"').val(yhzobj."+primarykey.toUpperCase()+");"+"\r\n";
	    str+="				$('#update_optionflag').val('update"+biaoming.toUpperCase()+"');"+"\r\n";
	    
		str+="			}"+"\r\n"+
		"		});"+"\r\n"+
		"	}"+"\r\n"+
		"}"+"\r\n"+
		"// 修改信息"+"\r\n"+
		"function update"+biaoming.toUpperCase()+"() {"+"\r\n"+
		"	$('#update"+biaoming.toUpperCase()+"_form').form('submit', {"+"\r\n"+
		"		url : 'update"+biaoming.toUpperCase()+".action',"+"\r\n"+
		"		onSubmit : function() {"+"\r\n"+
		"		},"+"\r\n"+
		"		success : function(data) {"+"\r\n"+
		"			if (\"false\" == data) {"+"\r\n"+
		"				$.messager.alert('提示', \"修改信息失败！\");"+"\r\n"+
		"			} else {"+"\r\n"+
		"				var obj2 = eval(\"(\" + data + \")\");"+"\r\n"+
		"				$.messager.alert('提示', obj2.message);"+"\r\n"+
		"				$('#update"+biaoming.toUpperCase()+"_form').form('clear');"+"\r\n"+
		"				$('#update"+biaoming.toUpperCase()+"_dlg').dialog('close');"+"\r\n"+
		"			}"+"\r\n"+
		"		}"+"\r\n"+
		"	});"+"\r\n"+
		"	$('#datagrid').datagrid('clearSelections');"+"\r\n"+
		"	$('#datagrid').datagrid('reload');"+"\r\n";
		

	    Iterator itkeys_update_end=keySet.iterator();
	    while(itkeys_update_end.hasNext()){
	    	String tmpkey=itkeys_update_end.next().toString().trim();
	    	String tmpvalue=prop.getProperty(tmpkey);
	    	str+="	$('#update_"+tmpkey.toUpperCase()+"').val(\"\");"+"\r\n";
	    }
		
		
		
		
   str+="	$('#update_"+primarykey.toUpperCase()+"').val(\"\");"+"\r\n"+
		"	$('#update_optionflag').val(\"\");"+"\r\n"+
		"}"+"\r\n"+
		"// 删除信息方法 弹出删除对话框："+"\r\n"+
		"function showEnterDialog() {"+"\r\n"+
		"	$.messager.confirm(\"删除信息\", \" 确认要删除选中的信息吗？\", function(r) {"+"\r\n"+
		"		if (r) {"+"\r\n"+
		"			var NowTime = new Date().toLocaleTimeString();"+"\r\n"+
		"			var row = $('#datagrid').datagrid('getSelections');"+"\r\n"+
		"			if (row < 1) {"+"\r\n"+
		"				$.messager.alert('提示', '请选择要删除的记录');"+"\r\n"+
		"				return;"+"\r\n"+
		"			}"+"\r\n"+
		"			var tmpyhzid = \"\";"+"\r\n"+
		"			for ( var tmpi = 0; tmpi < row.length; tmpi++) {"+"\r\n"+
		"				tmpyhzid = tmpyhzid + row[tmpi]."+primarykey.toUpperCase()+" + \",\";"+"\r\n"+
		"			}"+"\r\n"+
		"			$.ajax({"+"\r\n"+
		"				url : \"del"+biaoming.toUpperCase()+".action?nowtime=\" + NowTime + \"&"+primarykey.toUpperCase()+"=\""+"\r\n"+
		"						+ tmpyhzid,"+"\r\n"+
		"				context : document.body,"+"\r\n"+
		"				success : function(data) {"+"\r\n"+
		"					var yhzobj = eval(\"(\" + data + \")\");"+"\r\n"+
		"					$.messager.alert('提示', yhzobj.message);"+"\r\n"+
		"					$('#datagrid').datagrid('clearSelections');"+"\r\n"+
		"					$('#datagrid').datagrid('reload');"+"\r\n"+
		"				}"+"\r\n"+
		"			});"+"\r\n"+
		"		}"+"\r\n"+
		"	});"+"\r\n"+
		"	return false;"+"\r\n"+
		"}"+"\r\n"+
		"// 初始化方法"+"\r\n"+
		"$(function() {"+"\r\n"+
		"	$('#datagrid')"+"\r\n"+
		"			.datagrid("+"\r\n"+
		"					{"+"\r\n"+
		"						url : 'list"+biaoming.toUpperCase()+".action',"+"\r\n"+
		"						title : '所有',"+"\r\n"+
		"						iconCls : 'icon-cls',"+"\r\n"+
		"						pageSize : 10,"+"\r\n"+
		"						pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],"+"\r\n"+
		"						fitColumns : false,"+"\r\n"+
		"						nowrap : false,"+"\r\n"+
		"						border : false,"+"\r\n"+
		"						idField : '"+primarykey.toUpperCase()+"',"+"\r\n"+
		"						columns : [ ["+"\r\n";
		
		
		
   
   
   Iterator itkeys_list=keySet.iterator();
   int ziduani=1;
   while(itkeys_list.hasNext()){
   	String tmpkey=itkeys_list.next().toString().trim();
   	String tmpvalue=prop.getProperty(tmpkey);
   	
   	str+= "								{"+"\r\n"+
   			"									title : '<b>字段"+ziduani+"</b>',"+"\r\n"+
   			"									field : '"+tmpkey.toUpperCase()+"',"+"\r\n"+
   			"									sortable : true,"+"\r\n"+
   			"									width : 100"+"\r\n"+
   			"								}," +
   			"";
   	ziduani++;
   }
   str+=""+"\r\n"+
		"								{"+"\r\n"+
		"									title : '<b>操作</b>',"+"\r\n"+
		"									field : '"+primarykey.toUpperCase()+"',"+"\r\n"+
		"									width : 100,"+"\r\n"+
		"									formatter : function(value, rowData,"+"\r\n"+
		"											rowIndex) {"+"\r\n"+
		"										return \"<a style='cursor:hand;' onclick='update"+biaoming.toUpperCase()+"_dialog();'><img src='js/jquery-easyui/themes/icons/pencil.png'  alt='修改' style='border:0px;'/></a>\";"+"\r\n"+
		"									}"+"\r\n"+
		"								} ] ],"+"\r\n"+
		"						singleSelect : false,// 是否单选"+"\r\n"+
		"						pagination : true,// 分页控件"+"\r\n"+
		"						rownumbers : true,// 行号"+"\r\n"+
		"						frozenColumns : [ [ {"+"\r\n"+
		"							field : 'ck',"+"\r\n"+
		"							checkbox : true"+"\r\n"+
		"						} ] ]"+"\r\n"+
		"					});"+"\r\n"+
		"	$('#datagrid').datagrid('getPager').pagination({"+"\r\n"+
		"		pageSize : 10,// 每页显示的记录条数，默认为10"+"\r\n"+
		"		pageList : [ 10, 20, 30, 40, 50, 60, 70, 80 ],// 可以设置每页记录条数的列表"+"\r\n"+
		"		beforePageText : '第',// 页数文本框前显示的汉字"+"\r\n"+
		"		afterPageText : '页    共 {pages} 页',"+"\r\n"+
		"		displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录'"+"\r\n"+
		"	});"+"\r\n"+
		"});"+"\r\n"+"\r\n";
BufferedWriter output = new BufferedWriter(new FileWriter(f));   
output.write(str);   
output.close();  
System.out.println("JavaScript：     "+fileOutPath+" 【生成完毕！】");
} catch (Exception e) {
e.printStackTrace();
}
} 


    
    
	public static void main(String args[]){ 
		ReadFile rf=new ReadFile();
		rf.outPutJavaScriptFile();
		rf.outPutJSPFile();
		rf.outPutStrutsConfigFile();
		System.out.println("-------------------------------------------------------------------------------------------------");
		rf.outPutAction();
		System.out.println("-------------------------------------------------------------------------------------------------");
		rf.outPutSpringConfigFile();
		rf.outPutEntryServiceInterface();
		rf.outPutServiceImpl();
		rf.outPutEntryBean();
		System.out.println("已全部配置完毕，请将Action在系统中配置到模块中，并将模块赋权给“开发者角色”！");
	}
}
