package com.hrbsys.users.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.JUESE;
import com.hrbsys.bean.JUESE2MOKUAI;
import com.hrbsys.bean.JUESE2YONGHU;
import com.hrbsys.bean.JUESE2YONGHUZU;
import com.hrbsys.bean.KESHI;
import com.hrbsys.bean.MOKUAI;
import com.hrbsys.bean.YONGHU;
import com.hrbsys.bean.YONGHUZU;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.users.service.UserService;

public class YonghuAction extends ActionBase {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private String optionflag;

	// 用户组相关
	private String yhz_id; // 用户组 id主键
	private String yhz_yhzmc;// 用户组 名称
	private String yhz_bz;// 用户组 备注

	// 用户相关
	private String yh_yhmc;
	private String yh_yhmm;
	private String yh_id;

	// 角色相关
	private String js_jsmc;
	private String js_id;
	private String js_ms;
	private String js_bz;

	// 模块相关
	private String mk_id;
	private String mk_mkmc;
	private String mk_mkbm;
	private String mk_ms;
	private String mk_bz;
	private String mk_mkurl;
	private String mk_openstate;
	private String mk_iconcls;
	private String mk_fk_mk;
	private String mk_paixu;
	private String mk_mkym;
	private String mk_isshowinleftmenu;
	private String menuCateId;// 菜单分类ID

	// 角色2用户相关
	private String jy_id;
	private String jy_jsid;
	private String jy_jsmc;
	private String jy_yhid;
	private String jy_yhmc;
	private String jy_ms;
	private String jy_bz;

	// 角色2用户组相关
	private String jyz_id;
	private String jyz_jsid;
	private String jyz_jsmc;
	private String jyz_yhzid;
	private String jyz_yhzmc;
	private String jyz_ms;
	private String jyz_bz;

	// 角色2模块相关
	private String jm_id;
	private String jm_jsid;
	private String jm_jsmc;
	private String jm_mkid;
	private String jm_mkmc;
	private String jm_mkurl;
	private String jm_ms;
	private String jm_bz;
	private String jm_canfq;

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	/** 用户组相关action *****************************************************************************/
	// 列表用户组
	public void listYHZ() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("yhz_yhzmc", this.yhz_yhzmc);
		params.put("yhz_bz", this.yhz_bz);
		List<YONGHUZU> list = userService.findYONGHUZUByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountYONGHUZU(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghus" });// 除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}
	/**
	 * 获取所有的用户给信息（不进行分页）
	 * @throws Exception
	 */
	public void listAllYHZ() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("yhz_yhzmc", this.yhz_yhzmc);
		List<YONGHUZU> list = userService.findYONGHUZUByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
	}
	
	// 新增用户组:
	public void addYHZ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(yhz_yhzmc) || null == yhz_yhzmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写用户组名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		YONGHUZU yhz = new YONGHUZU();
		yhz.setBz(this.getYhz_bz());
		yhz.setYhzmc(this.getYhz_yhzmc());
		yhz.setYhzid(UUIDTools.getUUID());
		if (userService.addYHZ(yhz)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "信息新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "用户组信息添加失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	// 删除用户组:
	public void delYHZ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != yhz_id) && !"".equals(yhz_id)) {
			if (userService.delYHZ(yhz_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "用户组信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "用户组信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看用户组:
	public void updateYHZ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghus" });// 除去级联属性
		if ("".equals(yhz_id) || null == yhz_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有用户编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		YONGHUZU yhz = new YONGHUZU();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateyhz".equals(optionflag)) {
				if ("".equals(yhz_yhzmc) || null == yhz_yhzmc) {
					jsonMap.put("success", false);
					jsonMap.put("message", "未填写用户组名称！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				yhz.setYhzid(yhz_id);
				yhz.setBz(this.getYhz_bz());
				yhz.setYhzmc(this.getYhz_yhzmc());
				if (userService.updateYHZ(yhz)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改用户组信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "用户组信息添加失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(
				userService.findYHZ(yhz_id), config));
	}

	// 列表用户组for用户
	public void listYHZforYONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("yhz_yhzmc", this.yhz_yhzmc);
		params.put("yhz_bz", this.yhz_bz);
		List<YONGHUZU> list = userService.findYONGHUZUByPageApp(params, order,
				sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghuzu" });// 除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/** 用户相关action *****************************************************************************/
	// 列表用户
	public void listYONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1": page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10": rows);
		int start = (intPage - 1) * number;
		System.out.println("用户action中：############################:::::::::::::"+this.yh_yhmc);
		params.put("yh_yhmc", this.yh_yhmc);
		List<YONGHU> list = userService.findYONGHUByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountYONGHU(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghuzu" });// 除去级联属性
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	// 新增用户:
	public void addYONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(yh_yhmc) || null == yh_yhmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写用户名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(yhz_id) || null == yhz_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择一个用户组！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		YONGHU yh = new YONGHU();
		yh.setYhm(this.yh_yhmc);
		yh.setYhmm(this.yh_yhmm);
		yh.setYhid(UUIDTools.getUUID());
		YONGHUZU z = new YONGHUZU();
		z.setYhzid(yhz_id);
		yh.setYonghuzu(z);
		if (userService.addYONGHU(yh)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "用户新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "用户新增失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	// 删除用户:
	public void delYONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != yh_id) && !"".equals(yh_id)) {
			if (userService.delYONGHU(yh_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "用户信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "用户信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看用户
	public void updateYONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghuzu" });// 除去级联属性
		if ("".equals(yh_id) || null == yh_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有用户编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		YONGHU yh = new YONGHU();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updateyh".equals(optionflag)) {
				if ("".equals(yh_yhmc) || null == yh_yhmc) {
					jsonMap.put("success", false);
					jsonMap.put("message", "未填写用户名称！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				if ("".equals(yhz_id) || null == yhz_id) {
					jsonMap.put("success", false);
					jsonMap.put("message", "未选择用户组！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				yh.setYhid(yh_id);
				yh.setYhm(yh_yhmc);
				yh.setYhmm(yh_yhmm);
				YONGHUZU yz = new YONGHUZU();
				yz.setYhzid(yhz_id);
				yh.setYonghuzu(yz);
				if (userService.updateYONGHU(yh)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改用户信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改用户信息失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(
				userService.findYONGHU(yh_id), config));
	}

	// 列表用户for用户
	public void listYONGHUforYONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		List<YONGHU> list = userService
				.findYONGHUByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "yonghuzu" });// 除去级联属性
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	/** 角色相关action *****************************************************************************/
	// 列表角色
	public void listJUESE() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("js_jsmc", this.js_jsmc);
		params.put("js_ms", this.js_ms);
		params.put("js_bz", this.js_bz);
		List<JUESE> list = userService.findJUESEByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountJUESE(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		// new
		// JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增角色:
	public void addJUESE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(js_jsmc) || null == js_jsmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写角色名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		JUESE js = new JUESE();
		js.setJsmc(this.js_jsmc);
		js.setMs(this.js_ms);
		js.setBz(this.js_bz);
		js.setJsid(UUIDTools.getUUID());
		if (userService.addJUESE(js)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "角色新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "角色新增失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	// 删除角色:
	public void delJUESE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != js_id) && !"".equals(js_id)) {
			if (userService.delJUESE(js_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "角色信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "角色信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看角色:
	public void updateJUESE() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		if ("".equals(js_id) || null == js_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有角色编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JUESE js = new JUESE();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updatejs".equals(optionflag)) {
				if ("".equals(js_jsmc) || null == js_jsmc) {
					jsonMap.put("success", false);
					jsonMap.put("message", "未填写角色名称！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				js.setJsmc(this.js_jsmc);
				js.setMs(this.js_ms);
				js.setBz(this.js_bz);
				js.setJsid(this.js_id);
				if (userService.updateJUESE(js)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改角色信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "角色信息修改失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		// new
		// JsonPrintTools().printJSON(JSONObject.fromObject(userService.findYONGHU(yh_id),config));
		new JsonPrintTools().printJSON(JSONObject.fromObject(userService
				.findJUESE(js_id)));
	}

	// 列表角色for用户
	public void listJUESEforYONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		// int intPage = Integer.parseInt((page == null || page == "0") ? "1":
		// page);
		// int number = Integer.parseInt((rows == null || rows == "0") ? "10":
		// rows);
		// int start = (intPage - 1) * number;
		params.put("js_jsmc", this.js_jsmc);
		params.put("js_bz", this.js_bz);
		params.put("js_ms", this.js_ms);
		List<JUESE> list = userService.findJUESEByPageApp(params, order, sort);// 每页的数据，放入list
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		// new
		// JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list));
	}

	/** 角色2用户相关action *****************************************************************************/
	// 列表用户角色
	public void listJUESE2YONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("jy_yhmc", this.jy_yhmc);
		params.put("jy_jsmc", this.jy_jsmc);
		params.put("jy_bz", this.jy_bz);
		params.put("jy_ms", this.jy_ms);
		List<JUESE2YONGHU> list = userService.findJUESE2YONGHUByPageApp(start,
				number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountJUESE2YONGHU(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增用户角色
	public void addJUESE2YONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(jy_yhid) || null == jy_yhid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择用户！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jy_jsid) || null == jy_jsid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择角色！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		String[] jsidarray = this.jy_jsid.split(",");
		for (String jsidtmp : jsidarray) {
			if (null != jsidtmp && !"".equals(jsidtmp)) {
				jsidtmp = jsidtmp.trim();
				this.jy_yhid = this.jy_yhid.trim();
				JUESE2YONGHU jy = new JUESE2YONGHU();
				jy.setBz(this.jy_bz);
				jy.setJsid(jsidtmp);
				jy.setYhid(this.jy_yhid);
				jy.setMs(this.jy_ms);
				jy.setJsyhid(UUIDTools.getUUID());
				if (userService.addJUESE2YONGHU(jy)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "用户赋予角色成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "用户赋予角色失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
	}

	// 删除用户角色
	public void delJUESE2YONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.jy_id) && !"".equals(this.jy_id)) {
			if (userService.delJUESE2YONGHU(jy_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "角色用户关联信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "角色用户关联信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看用户角色
	public void updateJUESE2YONGHU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		if ("".equals(jy_id) || null == jy_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择用户角色关系！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JUESE2YONGHU jy = new JUESE2YONGHU();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updatejy".equals(optionflag)) {
				if ("".equals(jy_jsid) || null == jy_jsid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个角色！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				if ("".equals(jy_yhid) || null == jy_yhid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个用户！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				jy.setBz(this.jy_bz);
				jy.setMs(this.jy_ms);
				jy.setJsid(this.jy_jsid);
				jy.setYhid(this.jy_yhid);
				jy.setJsyhid(this.jy_id);
				if (userService.updateJUESE2YONGHU(jy)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改角色用户关系成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改角色用户关系失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(userService
				.findJUESE2YONGHU(jy_id)));
	}

	/** 模块相关action *****************************************************************************/
	// 列表模块
	public void listMOKUAI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("mk_mkmc", this.mk_mkmc);
		params.put("mk_mkbm", this.mk_mkbm);
		params.put("mk_mkbz", this.mk_bz);
		params.put("mk_ms", this.mk_ms);
		List<MOKUAI> list = userService.findMOKUAIByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountMOKUAI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		// new
		// JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,config));
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增模块:
	public void addMOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(mk_mkmc) || null == mk_mkmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写模块名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		MOKUAI mk = new MOKUAI();
		mk.setMkmc(this.mk_mkmc);
		mk.setMkbm(this.mk_mkbm);
		mk.setMs(this.mk_ms);
		mk.setBz(this.mk_bz);
		mk.setMkurl(this.mk_mkurl);
		mk.setOpenstate(this.mk_openstate);
		mk.setIconcls(this.mk_iconcls);
		mk.setPaixu(this.mk_paixu);
		mk.setFkmk(this.mk_fk_mk);
		mk.setMkym(this.mk_mkym);
		mk.setIsShowInLeftMenu(this.mk_isshowinleftmenu);
		mk.setMkid(UUIDTools.getUUID());
		mk.setMenuCateId(this.menuCateId);
		if (userService.addMOKUAI(mk)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "模块新增成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "模块新增失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	// 删除模块:
	public void delMOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.mk_id) && !"".equals(this.mk_id)) {
			if (userService.delMOKUAI(mk_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "模块信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "模块信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看模块:
	public void updateMOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		if ("".equals(mk_id) || null == mk_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "没有模块编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		MOKUAI mk = new MOKUAI();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updatemk".equals(optionflag)) {
				if ("".equals(mk_mkmc) || null == mk_mkmc) {
					jsonMap.put("success", false);
					jsonMap.put("message", "未填写模块名称！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				mk.setMkmc(this.mk_mkmc);
				mk.setMkbm(this.mk_mkbm);
				mk.setMs(this.mk_ms);
				mk.setBz(this.mk_bz);
				mk.setMkurl(this.mk_mkurl);
				mk.setOpenstate(this.mk_openstate);
				mk.setIconcls(this.mk_iconcls);
				mk.setPaixu(this.mk_paixu);
				mk.setFkmk(this.mk_fk_mk);
				mk.setMkid(this.mk_id);
				mk.setMkym(this.mk_mkym);
				mk.setIsShowInLeftMenu(this.mk_isshowinleftmenu);
				mk.setMenuCateId(this.menuCateId);
				if (userService.updateMOKUAI(mk)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改模块信息成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "模块信息修改失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		// new
		// JsonPrintTools().printJSON(JSONObject.fromObject(userService.findYONGHU(yh_id),config));
		new JsonPrintTools().printJSON(JSONObject.fromObject(userService
				.findMOKUAI(mk_id)));
	}

	// 列表模块for用户
	public void listMOKUAIforYONGHU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		// int intPage = Integer.parseInt((page == null || page == "0") ? "1":
		// page);
		// int number = Integer.parseInt((rows == null || rows == "0") ? "10":
		// rows);
		// int start = (intPage - 1) * number;
		params.put("mk_mkmc", this.mk_mkmc);
		params.put("mk_mkbm", this.mk_mkbm);
		params.put("mk_mkbz", this.mk_bz);
		params.put("mk_ms", this.mk_ms);
		List<MOKUAI> list = userService
				.findMOKUAIByPageApp(params, order, sort);// 每页的数据，放入list
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		// new
		// JsonPrintTools().printJSON_Array(JSONArray.fromObject(list,config));
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list));
	}

	/** 角色2用户组相关action *****************************************************************************/
	// 列表用户组角色
	public void listJUESE2YONGHUZU() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("jyz_yhzmc", this.jyz_yhzmc);
		params.put("jyz_jsmc", this.jyz_jsmc);
		params.put("jyz_bz", this.jyz_bz);
		params.put("jyz_ms", this.jyz_ms);
		List<JUESE2YONGHUZU> list = userService.findJUESE2YONGHUZUByPageApp(
				start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountJUESE2YONGHUZU(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增用户组角色
	public void addJUESE2YONGHUZU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(jyz_yhzid) || null == jyz_yhzid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择用户组！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jyz_jsid) || null == jyz_jsid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择角色！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		String[] jsidarray = this.jyz_jsid.split(",");
		for (String jsidtmp : jsidarray) {
			if (null != jsidtmp && !"".equals(jsidtmp)) {
				jsidtmp = jsidtmp.trim();
				JUESE2YONGHUZU jyz = new JUESE2YONGHUZU();
				jyz.setBz(this.jyz_bz);
				jyz.setJsid(jsidtmp);
				jyz.setYhzid(this.jyz_yhzid);
				jyz.setMs(this.jyz_ms);
				jyz.setJsyhzid(UUIDTools.getUUID());
				if (userService.addJUESE2YONGHUZU(jyz)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "用户组赋予角色成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "用户赋予角色失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
	}

	// 删除用户角色
	public void delJUESE2YONGHUZU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.jyz_id) && !"".equals(this.jyz_id)) {
			if (userService.delJUESE2YONGHUZU(jyz_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "角色用户组关联信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "角色用户组关联信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看用户角色
	public void updateJUESE2YONGHUZU() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"yonghuzu"});//除去级联属性
		if ("".equals(jyz_id) || null == jyz_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择用户组角色关系！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JUESE2YONGHUZU jyz = new JUESE2YONGHUZU();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updatejyz".equals(optionflag)) {
				if ("".equals(jyz_jsid) || null == jyz_jsid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个角色！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				if ("".equals(jyz_yhzid) || null == jyz_yhzid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个用户组！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				jyz.setBz(this.jyz_bz);
				jyz.setMs(this.jyz_ms);
				jyz.setJsid(this.jyz_jsid);
				jyz.setYhzid(this.jyz_yhzid);
				jyz.setJsyhzid(this.jyz_id);
				if (userService.updateJUESE2YONGHUZU(jyz)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改角色用户组关系成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改角色用户组关系失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(userService
				.findJUESE2YONGHUZU(jyz_id)));
	}

	/** 角色2模块相关action *****************************************************************************/
	// 列表模块角色
	public void listJUESE2MOKUAI() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("jm_mkmc", this.jm_mkmc);
		params.put("jm_jsmc", this.jm_jsmc);
		params.put("jm_bz", this.jm_bz);
		params.put("jm_ms", this.jm_ms);
		List<JUESE2MOKUAI> list = userService.findJUESE2MOKUAIByPageApp(start,
				number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", userService.getCountJUESE2MOKUAI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	// 新增模块角色
	public void addJUESE2MOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(jm_mkid) || null == jm_mkid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择模块！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jm_jsid) || null == jm_jsid) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未选择角色！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		String[] jsidarray = this.jm_mkid.split(",");
		for (String jmidtmp : jsidarray) {
			if (null != jmidtmp && !"".equals(jmidtmp)) {
				jmidtmp = jmidtmp.trim();
				JUESE2MOKUAI jm = new JUESE2MOKUAI();
				jm.setBz(this.jm_bz);
				jm.setJsid(this.jm_jsid);
				jm.setMkid(jmidtmp);
				jm.setMs(this.jm_ms);
				jm.setCanfq(this.jm_canfq);
				jm.setJsmkid(UUIDTools.getUUID());
				if (userService.addJUESE2MOKUAI(jm)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "模块赋予角色成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "用户赋予角色失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}

			}
		}
	}

	// 删除模块角色
	public void delJUESE2MOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != this.jm_id) && !"".equals(this.jm_id)) {
			if (userService.delJUESE2MOKUAI(jm_id)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "角色模块关联信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "角色模块关联信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改查看模块角色
	public void updateJUESE2MOKUAI() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		// JsonConfig config = new JsonConfig();
		// config.setExcludes(new String[]{"MOKUAI"});//除去级联属性
		if ("".equals(jm_id) || null == jm_id) {
			jsonMap.put("success", false);
			jsonMap.put("message", "请选择模块角色关系！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JUESE2MOKUAI jm = new JUESE2MOKUAI();
		if ((null != optionflag) && !"".equals(optionflag)) {
			if ("updatejm".equals(optionflag)) {
				if ("".equals(jm_jsid) || null == jm_jsid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个角色！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				if ("".equals(jm_mkid) || null == jm_mkid) {
					jsonMap.put("success", false);
					jsonMap.put("message", "请选择一个模块！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
				jm.setBz(this.jm_bz);
				jm.setMs(this.jm_ms);
				jm.setJsid(this.jm_jsid);
				jm.setMkid(this.jm_mkid);
				jm.setJsmkid(this.jm_id);
				jm.setCanfq(this.jm_canfq);
				if (userService.updateJUESE2MOKUAI(jm)) {
					jsonMap.put("success", true);
					jsonMap.put("message", "修改角色模块关系成功！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				} else {
					jsonMap.put("success", false);
					jsonMap.put("message", "修改角色模块关系失败！");
					new JsonPrintTools().printJSON(JSONObject
							.fromObject(jsonMap));
					return;
				}
			}
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(userService
				.findJUESE2MOKUAI(jm_id)));
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getYhz_id() {
		return yhz_id;
	}

	public void setYhz_id(String yhz_id) {
		this.yhz_id = yhz_id;
	}

	public String getOptionflag() {
		return optionflag;
	}

	public void setOptionflag(String optionflag) {
		this.optionflag = optionflag;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getYhz_yhzmc() {
		return yhz_yhzmc;
	}

	public void setYhz_yhzmc(String yhz_yhzmc) {
		this.yhz_yhzmc = yhz_yhzmc;
	}

	public String getYhz_bz() {
		return yhz_bz;
	}

	public void setYhz_bz(String yhz_bz) {
		this.yhz_bz = yhz_bz;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getYh_yhmc() {
		return yh_yhmc;
	}

	public void setYh_yhmc(String yh_yhmc) {
		this.yh_yhmc = yh_yhmc;
	}

	public String getYh_yhmm() {
		return yh_yhmm;
	}

	public void setYh_yhmm(String yh_yhmm) {
		this.yh_yhmm = yh_yhmm;
	}

	public String getYh_id() {
		return yh_id;
	}

	public void setYh_id(String yh_id) {
		this.yh_id = yh_id;
	}

	public String getJs_jsmc() {
		return js_jsmc;
	}

	public void setJs_jsmc(String js_jsmc) {
		this.js_jsmc = js_jsmc;
	}

	public String getJs_id() {
		return js_id;
	}

	public void setJs_id(String js_id) {
		this.js_id = js_id;
	}

	public String getJs_ms() {
		return js_ms;
	}

	public void setJs_ms(String js_ms) {
		this.js_ms = js_ms;
	}

	public String getJs_bz() {
		return js_bz;
	}

	public void setJs_bz(String js_bz) {
		this.js_bz = js_bz;
	}

	public String getMk_id() {
		return mk_id;
	}

	public void setMk_id(String mk_id) {
		this.mk_id = mk_id;
	}

	public String getMk_mkmc() {
		return mk_mkmc;
	}

	public void setMk_mkmc(String mk_mkmc) {
		this.mk_mkmc = mk_mkmc;
	}

	public String getMk_mkbm() {
		return mk_mkbm;
	}

	public void setMk_mkbm(String mk_mkbm) {
		this.mk_mkbm = mk_mkbm;
	}

	public String getMk_ms() {
		return mk_ms;
	}

	public void setMk_ms(String mk_ms) {
		this.mk_ms = mk_ms;
	}

	public String getMk_bz() {
		return mk_bz;
	}

	public void setMk_bz(String mk_bz) {
		this.mk_bz = mk_bz;
	}

	public String getMk_mkurl() {
		return mk_mkurl;
	}

	public void setMk_mkurl(String mk_mkurl) {
		this.mk_mkurl = mk_mkurl;
	}

	public String getMk_openstate() {
		return mk_openstate;
	}

	public void setMk_openstate(String mk_openstate) {
		this.mk_openstate = mk_openstate;
	}

	public String getMk_iconcls() {
		return mk_iconcls;
	}

	public void setMk_iconcls(String mk_iconcls) {
		this.mk_iconcls = mk_iconcls;
	}

	public String getMk_fk_mk() {
		return mk_fk_mk;
	}

	public void setMk_fk_mk(String mk_fk_mk) {
		this.mk_fk_mk = mk_fk_mk;
	}

	public String getMk_paixu() {
		return mk_paixu;
	}

	public void setMk_paixu(String mk_paixu) {
		this.mk_paixu = mk_paixu;
	}

	public String getJy_id() {
		return jy_id;
	}

	public void setJy_id(String jy_id) {
		this.jy_id = jy_id;
	}

	public String getJy_jsid() {
		return jy_jsid;
	}

	public void setJy_jsid(String jy_jsid) {
		this.jy_jsid = jy_jsid;
	}

	public String getJy_jsmc() {
		return jy_jsmc;
	}

	public void setJy_jsmc(String jy_jsmc) {
		this.jy_jsmc = jy_jsmc;
	}

	public String getJy_yhid() {
		return jy_yhid;
	}

	public void setJy_yhid(String jy_yhid) {
		this.jy_yhid = jy_yhid;
	}

	public String getJy_yhmc() {
		return jy_yhmc;
	}

	public void setJy_yhmc(String jy_yhmc) {
		this.jy_yhmc = jy_yhmc;
	}

	public String getJy_ms() {
		return jy_ms;
	}

	public void setJy_ms(String jy_ms) {
		this.jy_ms = jy_ms;
	}

	public String getJy_bz() {
		return jy_bz;
	}

	public void setJy_bz(String jy_bz) {
		this.jy_bz = jy_bz;
	}

	public String getJyz_id() {
		return jyz_id;
	}

	public void setJyz_id(String jyz_id) {
		this.jyz_id = jyz_id;
	}

	public String getJyz_jsid() {
		return jyz_jsid;
	}

	public void setJyz_jsid(String jyz_jsid) {
		this.jyz_jsid = jyz_jsid;
	}

	public String getJyz_jsmc() {
		return jyz_jsmc;
	}

	public void setJyz_jsmc(String jyz_jsmc) {
		this.jyz_jsmc = jyz_jsmc;
	}

	public String getJyz_yhzid() {
		return jyz_yhzid;
	}

	public void setJyz_yhzid(String jyz_yhzid) {
		this.jyz_yhzid = jyz_yhzid;
	}

	public String getJyz_yhzmc() {
		return jyz_yhzmc;
	}

	public void setJyz_yhzmc(String jyz_yhzmc) {
		this.jyz_yhzmc = jyz_yhzmc;
	}

	public String getJyz_ms() {
		return jyz_ms;
	}

	public void setJyz_ms(String jyz_ms) {
		this.jyz_ms = jyz_ms;
	}

	public String getJyz_bz() {
		return jyz_bz;
	}

	public void setJyz_bz(String jyz_bz) {
		this.jyz_bz = jyz_bz;
	}

	public String getJm_id() {
		return jm_id;
	}

	public void setJm_id(String jm_id) {
		this.jm_id = jm_id;
	}

	public String getJm_jsid() {
		return jm_jsid;
	}

	public void setJm_jsid(String jm_jsid) {
		this.jm_jsid = jm_jsid;
	}

	public String getJm_jsmc() {
		return jm_jsmc;
	}

	public void setJm_jsmc(String jm_jsmc) {
		this.jm_jsmc = jm_jsmc;
	}

	public String getJm_mkid() {
		return jm_mkid;
	}

	public void setJm_mkid(String jm_mkid) {
		this.jm_mkid = jm_mkid;
	}

	public String getJm_mkmc() {
		return jm_mkmc;
	}

	public void setJm_mkmc(String jm_mkmc) {
		this.jm_mkmc = jm_mkmc;
	}

	public String getJm_mkurl() {
		return jm_mkurl;
	}

	public void setJm_mkurl(String jm_mkurl) {
		this.jm_mkurl = jm_mkurl;
	}

	public String getJm_ms() {
		return jm_ms;
	}

	public void setJm_ms(String jm_ms) {
		this.jm_ms = jm_ms;
	}

	public String getJm_bz() {
		return jm_bz;
	}

	public void setJm_bz(String jm_bz) {
		this.jm_bz = jm_bz;
	}

	public String getJm_canfq() {
		return jm_canfq;
	}

	public void setJm_canfq(String jm_canfq) {
		this.jm_canfq = jm_canfq;
	}

	public String getMk_mkym() {
		return mk_mkym;
	}

	public void setMk_mkym(String mk_mkym) {
		this.mk_mkym = mk_mkym;
	}

	public String getMk_isshowinleftmenu() {
		return mk_isshowinleftmenu;
	}

	public void setMk_isshowinleftmenu(String mk_isshowinleftmenu) {
		this.mk_isshowinleftmenu = mk_isshowinleftmenu;
	}

	public String getMenuCateId() {
		return menuCateId;
	}

	public void setMenuCateId(String menuCateId) {
		this.menuCateId = menuCateId;
	}
}
