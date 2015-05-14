package com.hrbsys.nianji.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import com.hrbsys.base.action.ActionBase;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.nianji.service.NIANJIService;

public class NIANJIAction extends ActionBase
{
	private static final long serialVersionUID = 1L;
	private NIANJIService nianjiService;
	private String optionflag;
	private String NJ_ID;
	private String NJMC;
	private String NJDM;
	private String BZ;
	private String MS;
	// 分页相关
	private String rows; // 每页显示的记录数
	private String page;// 当前页码(第几页)
	private JSONObject result;
	// 排序相关
	private String order;
	private String sort;

	public void listNIANJI() throws Exception
	{
		HashMap<String, String> params = new HashMap<String, String>();
		int intPage = Integer.parseInt((page == null || page == "0") ? "1"
				: page);
		int number = Integer.parseInt((rows == null || rows == "0") ? "10"
				: rows);
		int start = (intPage - 1) * number;
		params.put("NJMC", this.NJMC);
		params.put("NJDM", this.NJDM);
		List<NIANJI> list = nianjiService.findNIANJIByPageApp(start, number,
				params, order, sort);// 每页的数据，放入list
		Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
		jsonMap.put("total", nianjiService.getCountNIANJI(params));
		jsonMap.put("rows", list);// rows键 存放每页记录 list
		jsonMap.put("page", intPage);
		JsonConfig config = new JsonConfig();
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
	}

	public void addNIANJI() throws Exception
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		if (null != nianjiService.findNIANJIByName(NJMC))
		{
			jsonMap.put("success", false);
			jsonMap.put("message", "年级名称已被占用！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		if (null != nianjiService.findNIANJIByCode(NJDM))
		{
			jsonMap.put("success", false);
			jsonMap.put("message", "年级代码已被占用！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}

		NIANJI t = new NIANJI();
		t.setNJ_ID(UUIDTools.getUUID());
		t.setNJMC(this.getNJMC());
		t.setNJDM(this.getNJDM());
		if (nianjiService.addNIANJI(t))
		{
			jsonMap.put("success", true);
			jsonMap.put("message", "信息新增成功！");
		} else
		{
			jsonMap.put("success", false);
			jsonMap.put("message", "信息添加失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void delNIANJI() throws Exception
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ((null != NJ_ID) && !"".equals(NJ_ID))
		{
			if (nianjiService.delNIANJI(NJ_ID))
			{
				jsonMap.put("success", true);
				jsonMap.put("message", "信息删除成功！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			} else
			{
				jsonMap.put("success", false);
				jsonMap.put("message", "信息删除失败！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			}
		}
	}

	public void updateNIANJI() throws Exception
	{
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("".equals(NJ_ID) || null == NJ_ID)
		{
			jsonMap.put("success", false);
			jsonMap.put("message", "没有ID编号！");
			new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
			return;
		}
		NIANJI nj1 = nianjiService.findNIANJIByName(NJMC);
		if (null != nj1)
		{
			if (!NJ_ID.equals(nj1.getNJ_ID()))
			{
				jsonMap.put("success", false);
				jsonMap.put("message", "年级名称已被占用！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

		NIANJI nj2 = nianjiService.findNIANJIByCode(NJDM);
		if (null != nj2)
		{
			if (!NJ_ID.equals(nj2.getNJ_ID()))
			{
				jsonMap.put("success", false);
				jsonMap.put("message", "年级代码已被占用！");
				new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
				return;
			}
		}

		NIANJI t = new NIANJI();
		t.setNJ_ID(NJ_ID);
		t.setNJMC(NJMC);
		t.setNJDM(NJDM);
		t.setBZ(BZ);
		t.setMS(MS);
		if (nianjiService.updateNIANJI(t))
		{
			jsonMap.put("success", true);
			jsonMap.put("message", "修改信息成功！");
		} else
		{
			jsonMap.put("success", false);
			jsonMap.put("message", "修改信息失败！");
		}
		new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
		return;
	}

	public void listAllNIANJI() throws Exception
	{
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("NJMC", this.NJMC);
		params.put("NJDM", this.NJDM);
		List<NIANJI> list = nianjiService.findNIANJIByPageApp(params, order,
				sort);// 每页的数据，放入list
		JsonConfig config = new JsonConfig();
		new JsonPrintTools()
				.printJSON_Array(JSONArray.fromObject(list, config));
	}

	public NIANJIService getNianjiService()
	{
		return nianjiService;
	}

	public void setNianjiService(NIANJIService nianjiService)
	{
		this.nianjiService = nianjiService;
	}

	public String getOptionflag()
	{
		return optionflag;
	}

	public void setOptionflag(String optionflag)
	{
		this.optionflag = optionflag;
	}

	public String getNJ_ID()
	{
		return NJ_ID;
	}

	public void setNJ_ID(String nJ_ID)
	{
		NJ_ID = nJ_ID;
	}

	public String getNJMC()
	{
		return NJMC;
	}

	public void setNJMC(String nJMC)
	{
		NJMC = nJMC;
	}

	public String getNJDM()
	{
		return NJDM;
	}

	public void setNJDM(String nJDM)
	{
		NJDM = nJDM;
	}

	public String getRows()
	{
		return rows;
	}

	public void setRows(String rows)
	{
		this.rows = rows;
	}

	public String getPage()
	{
		return page;
	}

	public void setPage(String page)
	{
		this.page = page;
	}

	public JSONObject getResult()
	{
		return result;
	}

	public void setResult(JSONObject result)
	{
		this.result = result;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getSort()
	{
		return sort;
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public String getBZ()
	{
		return BZ;
	}

	public void setBZ(String bZ)
	{
		BZ = bZ;
	}

	public String getMS()
	{
		return MS;
	}

	public void setMS(String mS)
	{
		MS = mS;
	}

}