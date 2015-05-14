package com.hrbsys.login.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hrbsys.base.dao.BaseDao;
import com.hrbsys.bean.Attributes;
import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.JUESE2MOKUAI;
import com.hrbsys.bean.JUESE2YONGHU;
import com.hrbsys.bean.JUESE2YONGHUZU;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.MenuTree;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.login.service.LoginService;

public class LoginServiceImpl implements LoginService {
	private BaseDao baseDao;	
	
	@Override
	public YONGHU getYONGHUbyYHMCandYHMM(String uname, String upassword) {
		String hql = "from YONGHU where 1=1";
		if ((null != uname) && !"".equals(uname)) {
			hql+=" and YHM ='"+uname.toString().trim()+"'";
		}
		if ((null != upassword) && !"".equals(upassword)) {
			hql+=" and YHMM ='"+upassword.toString().trim()+"'";
		}else{
			hql+=" and YHMM =''";
		}
		List<YONGHU> listyh=baseDao.findAll(hql,YONGHU.class);
		if(null==listyh||listyh.size()<1){
			return null;
		};
		return listyh.get(0);
	}
	
	@Override
	public List<JUESE> getJUESEbyYONGHU(YONGHU y) {
		ArrayList<JUESE> alljs=new ArrayList<JUESE>();
		if(null!=y.getYhid()&&!"".equals(y.getYhid())){
			String hql="from JUESE2YONGHU WHERE 1=1 and yhid='"+y.getYhid()+"'";	
			List<JUESE2YONGHU>allj2y= baseDao.findAll(hql, JUESE2YONGHU.class);
			for(JUESE2YONGHU j2y:allj2y){
				String hql2="from JUESE WHERE 1=1 AND jsid='"+j2y.getJsid()+"'";
				alljs.addAll(baseDao.findAll(hql2,JUESE.class));
			}
		}
		return alljs;
	}
	
	@Override
	public List<YONGHUZU> getYONGHUZUforYONGHU(YONGHU y) {
		List<YONGHUZU> listyhz=new ArrayList<YONGHUZU>();
		if(null!=y.getYhid()&&!"".equals(y.getYhid())){
			String hql="from YONGHUZU WHERE 1=1 and yhzid='"+y.getYonghuzu().getYhzid()+"'";	
			listyhz.addAll(baseDao.findAll(hql, YONGHUZU.class));
		}
		return listyhz;
	}
	
	@Override
	public List<JUESE> getJUESEbyYONGHUZU(YONGHUZU yz) {
		List<JUESE> all=new ArrayList<JUESE>();
		if(null!=yz.getYhzid()&&!"".equals(yz.getYhzid())){//如果用户组ID不为空
			String hql="from JUESE2YONGHUZU WHERE 1=1 and yhzid='"+yz.getYhzid()+"'";	
			List<JUESE2YONGHUZU> alljuese2yonghuzu= baseDao.findAll(hql, JUESE2YONGHUZU.class); //查找出用户组对应的所有角色
			for(JUESE2YONGHUZU jyz:alljuese2yonghuzu){
				String hql2="from JUESE WHERE 1=1 AND jsid='"+jyz.getJsid()+"'";
				all.addAll(baseDao.findAll(hql2,JUESE.class));  //通过角色用户组关联关系查找出所有的角色
			}
		}
		return all;
	}
	

	@Override
	public List<JUESE> getJUESEbyYONGHUZU(List<YONGHUZU> allyz) {
		ArrayList<JUESE> listjuese=new ArrayList<JUESE>();
		for(YONGHUZU yz:allyz){
			if(null!=yz.getYhzid()&&!"".equals(yz.getYhzid())){
					listjuese.addAll(this.getJUESEbyYONGHUZU(yz));
			}
		}
		return listjuese;
	}	
	
	@Override
	public List<MOKUAI> getMOKUAIbyJUESE(JUESE js) {
		List<MOKUAI> listmokuai=new ArrayList<MOKUAI>();
		if(null!=js.getJsid()&&!"".equals(js.getJsid())){
			String hql="from JUESE2MOKUAI WHERE 1=1 and jsid='"+js.getJsid()+"'";	
			List<JUESE2MOKUAI> listj2m=baseDao.findAll(hql, JUESE2MOKUAI.class);
			for(JUESE2MOKUAI j2m:listj2m){
				if(baseDao.findAll("from MOKUAI where mkid='"+j2m.getMkid()+"'", MOKUAI.class).size()>0){
					listmokuai.add(baseDao.findAll("from MOKUAI where mkid='"+j2m.getMkid()+"'", MOKUAI.class).get(0));
				}
			}
		}
		return listmokuai;
	}
	
	@Override
	public List<MOKUAI> getMOKUAIbyJUESE(List<JUESE> alljuese) {
		List<MOKUAI> listmokuai=new ArrayList<MOKUAI>();
		for(JUESE js:alljuese){
			if(null!=js.getJsid()&&!"".equals(js.getJsid())){
				listmokuai.addAll(this.getMOKUAIbyJUESE(js));
			}			
		}
		return listmokuai;
	}
	
	

	public BaseDao getBaseDao() {
		return baseDao;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public Map<String,MenuTree> getAllMenuTree(List<MOKUAI> allmk,String fid) {
		Map<String,MenuTree> tree=new LinkedHashMap<String,MenuTree>();
		Map<String,MenuTree> tree2=new LinkedHashMap<String,MenuTree>();

		if(null!=allmk&&allmk.size()>0){
			List<MenuTree> alltree=this.tranMOUAItoMemuTreeGrade(allmk); //获取对象树
			for(MenuTree mt:alltree){
				if(null==mt.getFid()||"".equals(mt.getFid())){
					tree.put(mt.getId(),mt);
					tree2.put(mt.getId(),mt);
				}else{
					if(tree.containsKey(mt.getFid())){
						MenuTree mtf=tree.get(mt.getFid());
						List<MenuTree> allchild=mtf.getChildren();
						allchild.add(mt);
						mtf.setChildren(allchild);
						tree.put(mt.getId(),mt);
					}else{
						tree.put(mt.getId(),mt);
						tree2.put(mt.getId(),mt);
					}
				}
			}
		}
//		  for (String key :tree2.keySet()) {
//			   System.out.println("LoginServiceImpl中打印的getAllMenuTree打印key= "+ key + " --> " + tree2.get(key).getText()+" --> "+ tree2.get(key).getPaixu());
//		  }
		return tree2;
	}
	
	public List<MenuTree> tranMOUAItoMemuTreeGrade(List<MOKUAI> allmk){
		List<MenuTree> alltree=new ArrayList<MenuTree>();
		for(MOKUAI m:allmk){
			if("yes".equals(m.getIsShowInLeftMenu())){
				MenuTree mt=new MenuTree();
				mt.setId(m.getMkid());
				mt.setText(m.getMkmc());
				mt.setFid(m.getFkmk());
				mt.setIconCls(m.getIconcls());
				mt.setState(m.getOpenstate());
				mt.setPaixu(m.getPaixu());
				Attributes att=new Attributes();
				att.setUrl(m.getMkym());
				att.setPrice(m.getPaixu());
				mt.setAttributes(att);
				alltree.add(mt);
//				System.out.println("LoginServiceImpl中打印的tranMOUAItoMemuTreeGrade::"+mt.getText()+"-->"+mt.getPaixu());
			}
		}
////		List<MenuTree>all=ComparatorList.sort(alltree); //排序 
//		//排序
//		Collections.sort(alltree, new Comparator() {
//			@Override
//			public int compare(Object o1, Object o2) {
//				MenuTree m1=(MenuTree)o1;
//				MenuTree m2=(MenuTree)o2;
//				return new Double(m1.getPaixu()).compareTo(new Double(m2.getPaixu()));
//			}
//		});	
		return alltree;
	}
	public Set<MenuTree> tranMapToList(Map<String,MenuTree> allmap){
		Set<MenuTree> alltree=new LinkedHashSet<MenuTree>();
		Iterator<String> i=allmap.keySet().iterator();
//		System.out.println("LoginServiceImpl中打印的tranMapToList:: SET的长度"+alltree.size()+" --> "+allmap.size());
		while(i.hasNext()){
			String tmpi=i.next();
			alltree.add(allmap.get(tmpi));
//			System.out.println("LoginServiceImpl中打印的tranMapToList:: SET的长度"+tmpi+" --> "+alltree.size()+" --> "+allmap.size()+"-->"+allmap.get(tmpi).getText()+"-->"+alltree.size());
		}
//		System.out.println("LoginServiceImpl中打印的tranMapToList:::::::"+alltree.size());
//		Iterator<MenuTree> it=alltree.iterator();
//		while(it.hasNext()){
//			MenuTree mtmp=it.next();
////			System.out.println("LoginServiceImpl中打印的tranMapToList::"+mtmp.getText()+" --> "+mtmp.getPaixu());
//		}
		return alltree;
	}
}