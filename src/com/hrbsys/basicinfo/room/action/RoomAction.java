package com.hrbsys.basicinfo.room.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.room.service.RoomService;
import com.hrbsys.bean.FANGJIAN;
import com.hrbsys.bean.JXL;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;

public class RoomAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	private RoomService roomService;

	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	private String fjId;
	private String fjdm;
	private String fjmc;
	private String fjdz;
	private String fjh;
	private String louceng;
	private String isdmt;
	private String renshu;
	private String jxlId;
	private String jxlMc;
	private String jxllh;

	private String jxlmc;
	private String jxldm;

	/** 房间action *****************************************************************************/
	// 列表
	public void listFJ() {
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		int start = (intPage - 1) * number;
		params.put("fjmc", this.fjmc);
		params.put("fjdm", this.fjdm);
		params.put("fjdz", this.fjdz);
		params.put("fjh", this.fjh);
		params.put("louceng", this.louceng);
		params.put("renshu", this.renshu);
		params.put("isdmt", this.isdmt);
		params.put("jxlId", this.jxlId);
		params.put("jxlMc", this.jxlMc);
		params.put("jxllh", this.jxllh);

		List<FANGJIAN> list = roomService.findRoomByPageApp(start, number, params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map

		jsonMap.put("total", roomService.getCountRoom(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);

		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
	}

	public void listJXLforFJ() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("jxlmc", jxlmc);
		params.put("jxldm", jxldm);
		List<JXL> list = roomService.findJXLByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	// 新增教学楼:
	public void addFJ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(fjmc) || null == fjmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(fjdz) || null == fjdz) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写楼号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(fjh) || null == fjh) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写位置");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(louceng) || null == louceng) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写楼层");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(isdmt) || null == isdmt) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写是否有多媒体");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(renshu) || null == renshu) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写可容纳人数");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		if ("".equals(jxlId) || null == jxlId) {
			jsonMap.put("success", false);
			jsonMap.put("message", "该房间所属教学楼信息不全");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		JXL jxl = roomService.findJXLById(jxlId);
		if((null == jxl) || ("".equals(jxl.getJxldm()))){
			jsonMap.put("success", false);
			jsonMap.put("message", "教学楼信息错误");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		fjdm = jxl.getJxldm()+louceng+fjh;
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("fjmc", fjmc);
		params.put("fjdm", fjdm);
		params.put("fjdz", fjdz);
		params.put("fjh", fjh);
		List<FANGJIAN> list = roomService.findRoomByPageApp(params, order, sort);
		if (0 != list.size()) {
			jsonMap.put("success", false);
			jsonMap.put("message", "信息已存在！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		FANGJIAN fj = new FANGJIAN();
		fj.setFjId(UUIDTools.getUUID());
		fj.setFjdm(fjdm);
		fj.setFjmc(fjmc);
		fj.setFjdz(fjdz);
		fj.setFjh(fjh);
		fj.setLouceng(Integer.parseInt(louceng));
		fj.setIsdmt(isdmt);
		fj.setRenshu(Integer.parseInt(renshu));
		fj.setJxlId(jxl.getJxlId());
		fj.setJxlMc(jxl.getJxlmc());
		fj.setJxllh(jxl.getJxllh());

		if (roomService.addRoom(fj)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "添加增成功！");

		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "添加增失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	// 删除教学楼:
	public void delFJ() throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != fjId) && !"".equals(fjId)) {
			if (roomService.delRoom(fjId)) {
				jsonMap.put("success", true);
				jsonMap.put("message", "删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else {
				jsonMap.put("success", false);
				jsonMap.put("message", "删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	// 修改教学楼:
	public void updateFJ() throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		if ("".equals(fjId) || null == fjId) {
			jsonMap.put("success", false);
			jsonMap.put("message", "房间ID为空！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(fjmc) || null == fjmc) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写房间名称！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(fjdz) || null == fjdz) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写房间地址！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(fjh) || null == fjh) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写房间号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(louceng) || null == louceng) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写房间所在楼层！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(isdmt) || null == isdmt) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写该房间内是否有多媒体！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(renshu) || null == renshu) {
			jsonMap.put("success", false);
			jsonMap.put("message", "未填写房间可容纳人数！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if ("".equals(jxlId) || null == jxlId) {
			jsonMap.put("success", false);
			jsonMap.put("message", "房间所属教学楼信息不全！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		JXL jxl = roomService.findJXLById(jxlId);
		if ((null == jxl) || "".equals(jxl.getJxldm())) {
			jsonMap.put("success", false);
			jsonMap.put("message", "教学楼信息错误！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		fjdm = jxl.getJxldm()+louceng+fjh;
		FANGJIAN fj = new FANGJIAN();
		fj.setFjId(fjId);
		fj.setFjdm(fjdm);
		fj.setFjmc(fjmc);
		fj.setFjdz(fjdz);
		fj.setFjh(fjh);
		fj.setLouceng(Integer.parseInt(louceng));
		fj.setIsdmt(isdmt);
		fj.setRenshu(Integer.parseInt(renshu));
		fj.setJxlId(jxl.getJxlId());
		fj.setJxllh(jxl.getJxllh());
		fj.setJxlMc(jxl.getJxlmc());

		if (roomService.updateRoom(fj)) {
			jsonMap.put("success", true);
			jsonMap.put("message", "修改信息成功！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		} else {
			jsonMap.put("success", false);
			jsonMap.put("message", "修改信息失败！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
	}

	public void listAllFJ() throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		List<FANGJIAN> list = roomService.findRoomByPageApp(params, order, sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list, config));
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
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

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
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

	public String getFjId() {
		return fjId;
	}

	public void setFjId(String fjId) {
		this.fjId = fjId;
	}

	public String getFjdm() {
		return fjdm;
	}

	public void setFjdm(String fjdm) {
		this.fjdm = fjdm;
	}

	public String getFjmc() {
		return fjmc;
	}

	public void setFjmc(String fjmc) {
		this.fjmc = fjmc;
	}

	public String getFjdz() {
		return fjdz;
	}

	public void setFjdz(String fjdz) {
		this.fjdz = fjdz;
	}

	public String getFjh() {
		return fjh;
	}

	public void setFjh(String fjh) {
		this.fjh = fjh;
	}

	public String getLouceng() {
		return louceng;
	}

	public void setLouceng(String louceng) {
		this.louceng = louceng;
	}

	public String getIsdmt() {
		return isdmt;
	}

	public void setIsdmt(String isdmt) {
		this.isdmt = isdmt;
	}

	public String getRenshu() {
		return renshu;
	}

	public void setRenshu(String renshu) {
		this.renshu = renshu;
	}

	public String getJxlId() {
		return jxlId;
	}

	public void setJxlId(String jxlId) {
		this.jxlId = jxlId;
	}

	public String getJxlMc() {
		return jxlMc;
	}

	public void setJxlMc(String jxlMc) {
		this.jxlMc = jxlMc;
	}

	public String getJxllh() {
		return jxllh;
	}

	public void setJxllh(String jxllh) {
		this.jxllh = jxllh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJxlmc() {
		return jxlmc;
	}

	public void setJxlmc(String jxlmc) {
		this.jxlmc = jxlmc;
	}

	public String getJxldm() {
		return jxldm;
	}

	public void setJxldm(String jxldm) {
		this.jxldm = jxldm;
	}
}
