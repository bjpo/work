package com.hrbsys.tongji.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.hrbsys.base.action.ActionBase;
import com.hrbsys.basicinfo.banji.service.BANJIService;
import com.hrbsys.bean.BANJI;
import com.hrbsys.bean.CONDITIONS;
import com.hrbsys.bean.KESHI;
import com.hrbsys.bean.KQXX_XSCQ;
import com.hrbsys.bean.NIANJI;
import com.hrbsys.bean.TONGJI;
import com.hrbsys.bean.XUEQI;
import com.hrbsys.bean.XUEYUAN;
import com.hrbsys.bean.Xsxx;
import com.hrbsys.bean.XueShengKQ;
import com.hrbsys.bean.ZHUANYE;
import com.hrbsys.kqxxbjxx.service.KQXX_BJXXService;
import com.hrbsys.kqxxxscq.service.KQXX_XSCQService;
import com.hrbsys.nianji.service.NIANJIService;
import com.hrbsys.realtime.service.RealTimeService;
import com.hrbsys.task.service.TaskService;
import com.hrbsys.tongji.service.TONGJIService;
import com.hrbsys.tools.BaseChangeTool;
import com.hrbsys.tools.JsonPrintTools;
import com.hrbsys.tools.UUIDTools;
import com.hrbsys.xueqi.service.XUEQIService;
import com.hrbsys.xuesheng.service.XsxxService;
import com.hrbsys.xueyuan.service.XueYuanService;
import com.hrbsys.zhuanye.service.ZhuanYeService;

public class TONGJIAction extends ActionBase {

    private static final long serialVersionUID = 1L;
    private TONGJIService tongjiService;
    private RealTimeService realTimeService;
    private KQXX_BJXXService kqxxbjxxService;
    private KQXX_XSCQService kcxxxscqService;
    private XUEQIService xueqiService;
    private XueYuanService xueyuanService;
    private ZhuanYeService zhuanyeService;
    private NIANJIService nianjiService;
    private BANJIService banjiService;
    private XsxxService xsxxService;
    private TaskService taskService;
    private String optionflag;
    private String TONGJI_ID;
    private String XS_CD;
    private String XS_ID;
    private String XS_XM;
    private String QXRS;
    private String NJ_ID;
    private String CDRSBL;
    private String XS_XH;
    private String ZCCXRS;
    private String JS_JSMC;
    private String KSRQ;// 开始日期
    private String XS_QX;
    private String ZTRS;
    private String NJMC;
    private String YSKRS;
    private String JG_JGGH;
    private String XS_ZT;
    private String BJMC;
    private String BJ_ID;
    private String JG_JGMC;
    private String KCXX_KCMC;
    private String SKRQ;
    private String XINGQI;
    private String KCXX_ID;
    private String ZYMC;
    private String ZCCXBL;
    private String XY_ID;
    private String CQQK;
    private String ZTRSBL;
    private String ZHOU;
    private String KS_ID;
    private String XYMC;
    private String CDRS;
    private String XUEQI_ID;
    private String XS_ZCCX;
    private String JSRQ;// 结束日期
    private String QXRSBL;
    private String JS_ID;
    private String JG_ID;
    private String ZY_ID;
    private String riqi;
    private String queryTitle;// 统计查询条件标题
    private String CondId;// 统计查询条件id
    private String xq_name;// 学期名称
    private String xy_name;// 学院名称
    private String zy_name;// 专业名称
    private String nj_name;// 年级名称
    private String bj_name;// 班级名称
    private String con_id;//配置查询条件id
    // 分页相关
    private String rows; // 每页显示的记录数
    private String page;// 当前页码(第几页)
    private JSONObject result;
    // 排序相关
    private String order;
    private String sort;

    // 统计全校 第一个统计
    public void listQUANXIAO1() throws Exception {
        System.out
                .println("全校查询......................................................");
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        /**
         * 分析： 1、统计全校信息分两种：实时和往天 2、实时查询为：不输入开始日期、结束日期 3、实时查询为：选择课程、选择教室、选择教工、选择课时 4、查询getKQQK() 5、展示到页面 6、往天查询：输入开始日期、结束日期（不输入则为开始日期） 7、调用kqxxservice查询结果。 8、展示到页面。
		 * *
         */
        dayin();
        // 如果开始日期不为空
        if ((null != this.getKSRQ() && !"".equals(this.getKSRQ()))
                || (null != this.getZHOU() && !"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this.getXINGQI()))
                || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID()))) {
            log.info("进入到的是往天查询页面.......................debug信息....");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
			// //调用往天查询方法，查询统计信息。
            // String xueqi_ksrq = "";// 设置学期从哪天开始
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            HashMap<String, Integer> mapkqxxbj = kqxxbjxxService.getBJKQXX(
                    this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                    this.getKCXX_ID(), xq, this.getXINGQI(), this.getZHOU(),
                    this.getKSRQ(), this.getJSRQ());
            TONGJI tj = new TONGJI();
            tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
            tj.setQXRS(mapkqxxbj.get("qxrs").toString());
            tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
            tj.setCDRS(mapkqxxbj.get("cdrs").toString());
            tj.setZTRS(mapkqxxbj.get("ztrs").toString());
            List<TONGJI> ls = new ArrayList<TONGJI>();
            ls.add(tj);
            jsonMap.clear();
            jsonMap.put("total", ls.size());
            jsonMap.put("rows", ls);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        }
        // 如果没有查询天，则为当天，当天考勤信息为实时调用。
        HashMap<String, Integer> mapkq = realTimeService.getKQQK(
                this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                this.getKCXX_ID(), null);
        TONGJI tj = new TONGJI();
        tj.setYSKRS((mapkq.get("yingchuqin")).toString());
        tj.setQXRS((mapkq.get("queqin")).toString());
        tj.setZCCXRS((mapkq.get("zhengchang")).toString());
        tj.setCDRS((mapkq.get("chidao")).toString());
        tj.setZTRS((mapkq.get("zaotui")).toString());
        listtj.add(tj);
        log.info("进入到的是当天查询页面.......................info信息....");
        jsonMap.clear();
        /**
         * ************************************ 校长前台首页下的全校出勤情况百分比数据 开始 ***********************************************
         */
        // 转换百分比
        NumberFormat num = NumberFormat.getPercentInstance();
        num.setMaximumIntegerDigits(3); // 设置数的整数部分所允许的最大位数
        num.setMaximumFractionDigits(2); // 设置数的小数部分所允许的最大位数
        // 计算出勤的百分比
        if (mapkq.get("yingchuqin") != null && mapkq.get("yingchuqin") != 0) {
            String qxPercen = num.format(mapkq.get("queqin")
                    / mapkq.get("yingchuqin"));// 缺席百分比
            String cdPercen = num.format(mapkq.get("chidao")
                    / mapkq.get("yingchuqin"));// 迟到百分比
            String ztPercen = num.format(mapkq.get("zaotui")
                    / mapkq.get("yingchuqin"));// 早退百分比
            String zcPercen = num.format(mapkq.get("zhengchang")
                    / mapkq.get("yingchuqin"));// 正常出席百分比
            jsonMap.put("qxPercen", qxPercen);
            jsonMap.put("cdPercen", cdPercen);
            jsonMap.put("ztPercen", ztPercen);
            jsonMap.put("zcPercen", zcPercen);
        } else {
            // 在没有数据的情况
            jsonMap.put("qxPercen", "0%");
            jsonMap.put("cdPercen", "0%");
            jsonMap.put("ztPercen", "0%");
            jsonMap.put("zcPercen", "0%");
        }
        /**
         * ************************************* 校长前台首页下的全校出勤情况百分比数据 结束 **********************************************
         */
        jsonMap.put("total", listtj.size());
        jsonMap.put("rows", listtj);// rows键 存放每页记录 list
        jsonMap.put("page", intPage);
        new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
    }

    // 统计全校 第二个统计，按照学院统计
    public void listQUANXIAO2() throws Exception {
        /**
         * 算法分析： 统计全校出勤情况时：分开按学院统计： 1、统计全校信息分两种：实时和往天 2、实时查询为：不输入开始日期、结束日期 3、实时查询为：选择课程、选择教室、选择教工、选择课时 4、查询getKQQK() 5、展示到页面 6、往天查询：输入开始日期、结束日期（不输入则为开始日期） 7、调用kqxxservice查询结果。 8、展示到页面。
		 *
         */
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();

        List<XUEYUAN> all_xueyuan = xueyuanService.findXUEYUANByPage(start,
                number, new HashMap(), order, sort);
        log.info(this.getKSRQ());
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            log.info("分学院：进入到的是往天查询页面.......................info信息....");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            all_tongji.clear(); // 清空原有数据
            for (XUEYUAN xy : all_xueyuan) { // 循环每一个学院，统计上课信息
                HashMap<String, Integer> mapkqxxbj = kqxxbjxxService.getBJKQXX(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), xq, this.getXINGQI(),
                        this.getZHOU(), this.getKSRQ(), this.getJSRQ(),
                        xy.getXyid());
                TONGJI tj = new TONGJI();
                tj.setXY_ID(xy.getXyid());
                tj.setXYMC(xy.getXymc());
                tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                all_tongji.add(tj); // 新增一个学院的统计信息
            }
            jsonMap.clear();
            jsonMap.put("total", xueyuanService.getCountXUEYUAN(new HashMap()));
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            all_tongji.clear();// 清空原有数据
            for (XUEYUAN xy : all_xueyuan) { // 循环每一个学院，统计上课信息
                // 如果没有查询天，则为当天，当天考勤信息为实时调用。
                String tmpandParam = " and a.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID in (select e.ZY_ID from ZHUANYE e where e.XYID='"
                        + xy.getXyid() + "'))";
                HashMap<String, Integer> mapkq = realTimeService.getKQQK(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), null, tmpandParam, null, null, null);
                TONGJI tj = new TONGJI();
                tj.setXY_ID(xy.getXyid());
                tj.setXYMC(xy.getXymc());
                tj.setYSKRS((mapkq.get("yingchuqin")).toString());
                tj.setQXRS((mapkq.get("queqin")).toString());
                tj.setZCCXRS((mapkq.get("zhengchang")).toString());
                tj.setCDRS((mapkq.get("chidao")).toString());
                tj.setZTRS((mapkq.get("zaotui")).toString());
                listtj.add(tj);
            }
            log.info("分学院：进入到的是当天查询页面.......................info信息....");
            jsonMap.clear();
            jsonMap.put("total", xueyuanService.getCountXUEYUAN(new HashMap()));
            jsonMap.put("rows", listtj);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
        }
    }

    // 学院方法：传入学院ID,分专业统计
    //学院统计学院统计学院统计学院统计学院统计学院统计学院统计学院统计
    public void listXUEYUANTONGJI() throws Exception {
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();
        if (null == this.getXY_ID() || "".equals(this.getXY_ID())) {
            log.info("统计学院下专业时，学院ID为空！");
            return;
        }
        XUEYUAN x = xueyuanService.findXueYuanById(this.getXY_ID());
        if (null == x || "".equals(x)) { // 如果有这个学院
            return;
        }
        List<ZHUANYE> all_zhuanye;
        if (null != this.getZY_ID() && !"".equals(this.getZY_ID())) {
            all_zhuanye = new ArrayList<ZHUANYE>();
            ZHUANYE z = zhuanyeService.findZHUANYE(this.getZY_ID());
            if (null != z && !"".equals(z)) {
                all_zhuanye.add(z);
            }
            log.info("ifelse111中的：：：：" + z.getZYMC());
        } else {

            all_zhuanye = zhuanyeService.findZHUANYEByXueYuan(this.getXY_ID());
            // 当根据学院去查询时，没有记录时显示“无记录”
            if (all_zhuanye.size() <= 0) {
                // 当没有数据时显示“无记录”
                jsonOutPutNULL();
            }
            log.info("ifelse222中的：：：：" + all_zhuanye.size());
        }
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            log.info("分专业：往天考勤计算");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            all_tongji.clear(); // 清空原有数据
            for (ZHUANYE zy : all_zhuanye) { // 循环每一个学院，统计上课信息
                log.info(kcxxxscqService);
                log.info(zy);
                HashMap<String, Integer> mapkqxxbj = kcxxxscqService.getBJKQXX(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), xq, this.getXINGQI(),
                        this.getZHOU(), this.getKSRQ(), this.getJSRQ(),
                        zy.getXYID(), zy.getZY_ID(), this.getNJ_ID(),
                        this.getBJ_ID());
                TONGJI tj = new TONGJI();
                tj.setXY_ID(zy.getXYID());
                tj.setXYMC(zy.getXYMC());
                tj.setZY_ID(zy.getZY_ID());
                tj.setZYMC(zy.getZYMC());
                if (null != mapkqxxbj) {
                    tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                    tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                    tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                    tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                    tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                    log.info(zy.getZYMC() + "::::::应出席人数："
                            + mapkqxxbj.get("ycxrs").toString());
                    log.info(zy.getZYMC() + "::::::缺席人数：："
                            + mapkqxxbj.get("qxrs").toString());
                    log.info(zy.getZYMC() + "::::::正常人数: ："
                            + mapkqxxbj.get("zcrs").toString());
                    log.info(zy.getZYMC() + "::::::迟到人数：："
                            + mapkqxxbj.get("cdrs").toString());
                    log.info(zy.getZYMC() + "::::::早退人数：："
                            + mapkqxxbj.get("ztrs").toString());
                }
                all_tongji.add(tj); // 新增一个学院的统计信息
            }
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            log.info("分专业：进入当天实时查询");
            all_tongji.clear();// 清空原有数据
            for (ZHUANYE zy : all_zhuanye) { // 循环每一个学院，统计上课信息
                // 如果没有查询天，则为当天，当天考勤信息为实时调用。
                String tmpandParam = " and a.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID='"
                        + zy.getZY_ID() + "')";
                HashMap<String, Integer> mapkq = realTimeService.getKQQK(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), null, tmpandParam, this.getNJ_ID(),
                        this.getBJ_ID(), this.getZY_ID());
                TONGJI tj = new TONGJI();
                tj.setZY_ID(zy.getZY_ID());
                tj.setZYMC(zy.getZYMC());
                tj.setXY_ID(zy.getXYID());
                tj.setXYMC(zy.getXYMC()); 
//                tj.setYSKRS((mapkq.get("yingchuqin")).toString());
//                tj.setQXRS((mapkq.get("queqin")).toString());
//                tj.setZCCXRS((mapkq.get("zhengchang")).toString());
//                tj.setCDRS((mapkq.get("chidao")).toString());
//                tj.setZTRS((mapkq.get("zaotui")).toString());
                //测试测试测试测试
                tj.setYSKRS("36");
                tj.setQXRS("6");
                tj.setZCCXRS("30");
                tj.setCDRS("25");
                tj.setZTRS("29");
                listtj.add(tj);
            }
            jsonMap.clear();
            jsonMap.put("total", listtj.size());
            jsonMap.put("rows", listtj);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
        }
    }

    // 学院方法：传入学院ID,分专业,年级统计
    public void listZHUANYE_NIANJITONGJI() throws Exception {
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();
        if (null == this.getZY_ID() || "".equals(this.getZY_ID())) {
            log.info("统计学院下专业按年级统计时，专业ID为空！");
            return;
        }
        ZHUANYE z = zhuanyeService.findZHUANYE(this.getZY_ID());
        if (null == z || "".equals(z)) { // 如果有这个学院
            return;
        }
        HashMap<String, String> mapnj = new HashMap<String, String>();
        mapnj.put("NJ_ID", this.getNJ_ID());
        List<NIANJI> all_nainji = nianjiService.findNIANJIByPageApp(mapnj,
                order, sort);
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            log.info("分专业：进入到的是往天查询页面.......................info信息....");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            all_tongji.clear(); // 清空原有数据
            for (NIANJI nj : all_nainji) { // 循环每一个学院，统计上课信息
                HashMap<String, Integer> mapkqxxbj = kcxxxscqService.getBJKQXX(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), xq, this.getXINGQI(),
                        this.getZHOU(), this.getKSRQ(), this.getJSRQ(),
                        z.getXYID(), z.getZY_ID(), nj.getNJ_ID(),
                        this.getBJ_ID());
                TONGJI tj = new TONGJI();
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setNJ_ID(nj.getNJ_ID());
                tj.setNJMC(nj.getNJMC());
                tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                all_tongji.add(tj); // 新增一个学院的统计信息
            }
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            all_tongji.clear();// 清空原有数据
            for (NIANJI nj : all_nainji) { // 循环每一个学院，统计上课信息
                // 如果没有查询天，则为当天，当天考勤信息为实时调用。
                String tmpandParam = " and a.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID='"
                        + z.getZY_ID() + "')";
                HashMap<String, Integer> mapkq = realTimeService.getKQQK(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), null, tmpandParam, nj.getNJ_ID(),
                        null, null);
                TONGJI tj = new TONGJI();
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setNJ_ID(nj.getNJ_ID());
                tj.setNJMC(nj.getNJMC());
                tj.setYSKRS((mapkq.get("yingchuqin")).toString());
                tj.setQXRS((mapkq.get("queqin")).toString());
                tj.setZCCXRS((mapkq.get("zhengchang")).toString());
                tj.setCDRS((mapkq.get("chidao")).toString());
                tj.setZTRS((mapkq.get("zaotui")).toString());
                listtj.add(tj);
            }
            log.info("分专业：进入到的是当天查询页面.......................info信息....");
            jsonMap.clear();
            jsonMap.put("total", listtj.size());
            jsonMap.put("rows", listtj);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
        }
    }

    // 学院方法：传入学院ID,分专业,年级统计
    public void listZHUANYE_BANJITONGJI() throws Exception {
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();
        if (null == this.getZY_ID() || "".equals(this.getZY_ID())) {
            log.info("统计学院下专业按年级统计时，专业ID为空！");
            return;
        }
        ZHUANYE z = zhuanyeService.findZHUANYE(this.getZY_ID());
        if (null == z || "".equals(z)) { // 如果有这个学院
            return;
        }
        List<BANJI> all_banji = banjiService.findXSByZYandNJandBJ(
                this.getZY_ID(), this.getNJ_ID(), this.getBJ_ID());
        log.info(all_banji.size() + "：班级集合的size（）；");
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            log.info("分专业：进入到的是往天查询页面.......................info信息....");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            all_tongji.clear(); // 清空原有数据
            for (BANJI bj : all_banji) { // 循环每一个学院，统计上课信息
                HashMap<String, Integer> mapkqxxbj = kcxxxscqService
                        .getBJKQXX(this.getJS_ID(), this.getJG_ID(),
                                this.getKS_ID(), this.getKCXX_ID(), xq,
                                this.getXINGQI(), this.getZHOU(),
                                this.getKSRQ(), this.getJSRQ(), z.getXYID(),
                                z.getZY_ID(), bj.getNJ_ID(), bj.getBJ_ID());
                TONGJI tj = new TONGJI();
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setNJ_ID(bj.getNJ_ID());
                tj.setNJMC(bj.getNJMC());
                tj.setBJ_ID(bj.getBJ_ID());
                tj.setBJMC(bj.getBJMC());
                log.info(bj.getBJ_ID()
                        + ":###############################################BJ_ID:"
                        + tj.getBJ_ID());
                log.info(bj.getBJMC()
                        + ":###############################################BJMC:"
                        + tj.getBJMC());
                tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                all_tongji.add(tj); // 新增一个学院的统计信息
            }
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            all_tongji.clear();// 清空原有数据
            for (BANJI bj : all_banji) { // 循环每一个学院，统计上课信息
                // 如果没有查询天，则为当天，当天考勤信息为实时调用。
                String tmpandParam = " and a.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID='"
                        + z.getZY_ID() + "')";
                HashMap<String, Integer> mapkq = realTimeService.getKQQK(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), null, tmpandParam, this.getNJ_ID(),
                        bj.getBJ_ID(), null);
                TONGJI tj = new TONGJI();
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setNJ_ID(bj.getNJ_ID());
                tj.setNJMC(bj.getNJMC());
                tj.setBJ_ID(bj.getBJ_ID());
                tj.setBJMC(bj.getBJMC());
                log.info(bj.getBJ_ID()
                        + ":###############################################BJ_ID:"
                        + tj.getBJ_ID());
                log.info(bj.getBJMC()
                        + ":###############################################BJMC:"
                        + tj.getBJMC());
                tj.setYSKRS((mapkq.get("yingchuqin")).toString());
                tj.setQXRS((mapkq.get("queqin")).toString());
                tj.setZCCXRS((mapkq.get("zhengchang")).toString());
                tj.setCDRS((mapkq.get("chidao")).toString());
                tj.setZTRS((mapkq.get("zaotui")).toString());
                listtj.add(tj);
            }
            log.info("分专业：进入到的是当天查询页面....111...................info信息....");
            jsonMap.clear();
            jsonMap.put("total", listtj.size());
            jsonMap.put("rows", listtj);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
        }
    }

    // 学院方法：传入学院ID,分专业,年级、学生统计
    public void listZHUANYE_BANJIXUESHENGTONGJI() throws Exception {
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();
        if (null == this.getZY_ID() || "".equals(this.getZY_ID())) {
            log.info("统计学院下专业按年级统计时，专业ID为空！");
            return;
        }
        ZHUANYE z = zhuanyeService.findZHUANYE(this.getZY_ID());
        if (null == z || "".equals(z)) { // 如果有这个学院
            return;
        }

        if (null == this.getBJ_ID() || "".equals(this.getBJ_ID())) {
            log.info("班级为空，不统计考勤");
            return;
        }
        // 找到班级下所有学生：
        List<Xsxx> all_xs = xsxxService.findBANJIByZYandNJ(this.getZY_ID(),
                this.getNJ_ID(), this.getBJ_ID());
        log.info("查询出来的学生总数量是：########################################"
                + all_xs.size());
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            log.info("分专业：进入到的是往天查询页面.......................info信息....");
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            all_tongji.clear(); // 清空原有数据
            for (Xsxx xs : all_xs) { // 循环每一个学院，统计上课信息
                HashMap<String, Integer> mapkqxxbj = kcxxxscqService
                        .getBJKQXX_XSKQ_COUNT(this.getJS_ID(), this.getJG_ID(),
                                this.getKS_ID(), this.getKCXX_ID(), xq,
                                this.getXINGQI(), this.getZHOU(),
                                this.getKSRQ(), this.getJSRQ(), z.getXYID(),
                                z.getZY_ID(), xs.getNjId(), xs.getBjId(),
                                xs.getXsId());
                TONGJI tj = new TONGJI();
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setNJ_ID(xs.getNjId());
                tj.setNJMC(xs.getNJMC());
                tj.setBJ_ID(xs.getBjId());
                tj.setBJMC(xs.getBJMC());
                tj.setXS_ID(xs.getXsId());
                tj.setXS_XM(xs.getZsxm());
                tj.setXS_XH(xs.getXh());
                tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                all_tongji.add(tj); // 新增一个学院的统计信息
            }
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            all_tongji.clear();// 清空原有数据
            for (Xsxx xs : all_xs) { // 循环每一个学院，统计上课信息
                // 如果没有查询天，则为当天，当天考勤信息为实时调用。
                String tmpandParam = " and a.KCXX_ID in(select b.KECHENGXX_ID from KECHENGXX b where b.ZY_ID='"
                        + z.getZY_ID() + "')";
                HashMap<String, Integer> mapkq = realTimeService
                        .getKQQK_XUESHENG_TONGJI(this.getJS_ID(),
                                this.getJG_ID(), this.getKS_ID(),
                                this.getKCXX_ID(), null, tmpandParam, null,
                                null, xs.getXsId());
                TONGJI tj = new TONGJI();
                tj.setZY_ID(z.getZY_ID());
                tj.setZYMC(z.getZYMC());
                tj.setXY_ID(z.getXYID());
                tj.setXYMC(z.getXYMC());
                tj.setNJ_ID(xs.getNjId());
                tj.setBJMC(xs.getBJMC());
                tj.setNJMC(xs.getNJMC());
                tj.setBJ_ID(xs.getBjId());
                tj.setXS_ID(xs.getXsId());
                tj.setXS_XM(xs.getZsxm());
                tj.setXS_XH(xs.getXh());
                tj.setQXRS((mapkq.get("queqin")).toString());
                tj.setZCCXRS((mapkq.get("zhengchang")).toString());
                tj.setCDRS((mapkq.get("chidao")).toString());
                tj.setZTRS((mapkq.get("zaotui")).toString());
                all_tongji.add(tj);
            }
            log.info("分专业：进入到的是当天查询页面.......................info信息....列表的长度是##########################："
                    + all_tongji.size());
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
        }
    }

    // 学院方法：传入学院ID,分专业,年级、学生、按照学生出勤情况进行统计
    public void listZHUANYE_BANJIXUESHENGBYCQQKTONGJI() throws Exception {
        int intPage = Integer.parseInt((page == null || page == "0") ? "1"
                : page);
        int number = Integer.parseInt((rows == null || rows == "0") ? "10"
                : rows);
        int start = (intPage - 1) * number;
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();// 定义map
        JsonConfig config = new JsonConfig();
        dayin();
        if (null == this.getXS_ID() || "".equals(this.getXS_ID())) {
            log.info("统计学院下专业按年级统计时，学生ID为空！");
            return;
        }
        Xsxx xs = xsxxService.findStudentByXSId(this.getXS_ID());
        // 如果开始日期不为空
        if (((null != this.getKSRQ()) && (!"".equals(this.getKSRQ())))
                || ((null != this.getZHOU())
                && (!"".equals(this.getZHOU()))
                || (null != this.getXINGQI() && !"".equals(this
                        .getXINGQI())) || (null != this.getXUEQI_ID() && !""
                .equals(this.getXUEQI_ID())))) {
            // 判断结束日期是否为空：如果为空则定义为开始日期，不为空则继续
            if (!(null != this.getJSRQ() && !"".equals(this.getJSRQ()))) {
                this.setJSRQ(this.getKSRQ());
            }
            XUEQI xq = null;
            if (null != this.getXUEQI_ID() && !"".equals(this.getXUEQI_ID())) {
                xq = xueqiService.findXUEQI(this.getXUEQI_ID());
            } else {
                xq = xueqiService.getCurrentXUEQI();
            }
            if (null != this.getCQQK() && !"".equals(this.getCQQK())) {
                log.info("########################::::::::::::::::::"
                        + this.getCQQK());
                this.CQQK = BaseChangeTool.getKQQKbyChar(this.getCQQK());
                log.info("转换完毕：222：：：：：：：：：：：：：：：：：：：：：：：：：：：：：：：" + this.CQQK);
            }
            List<KQXX_XSCQ> allcq = kcxxxscqService.getBJKQXX_XSKQ_FEN(
                    this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                    this.getKCXX_ID(), xq, this.getXINGQI(), this.getZHOU(),
                    this.getKSRQ(), this.getJSRQ(), this.getXS_ID(),
                    this.getCQQK());
            // 转成统计信息
            List<TONGJI> all_tongji = new ArrayList<TONGJI>();
            for (KQXX_XSCQ kq : allcq) {
                TONGJI j = new TONGJI();
                j.setBJ_ID(kq.getBJ_ID());
                j.setBJMC(xs.getBJMC());
                j.setCQQK(kq.getCQQK());
                j.setJG_ID(kq.getLS_ID());
                j.setJG_JGGH(kq.getLSGH());
                j.setJG_JGMC(kq.getJSMC());
                j.setNJ_ID(kq.getNJ_ID());
                j.setNJMC(xs.getNJMC());
                j.setXY_ID(xs.getXyId());
                j.setXYMC(xs.getXYMC());
                j.setZY_ID(xs.getZyId());
                j.setZYMC(xs.getZYMC());
                j.setXS_ID(xs.getXsId());
                j.setXS_XM(xs.getZsxm());
                j.setXS_XH(xs.getXh());
                j.setKCXX_ID(kq.getKCB_ID());
                j.setKCXX_KCMC(kq.getKCMC());
                j.setJS_ID(kq.getJS_ID());
                j.setJS_JSMC(kq.getJSMC());
                all_tongji.add(j);
            }
            jsonMap.clear();
            jsonMap.put("total", all_tongji.size());
            jsonMap.put("rows", all_tongji);// rows键 存放每页记录 list
            jsonMap.put("page", intPage);
            new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap,
                    config));
            return;
        } else {
            log.info(this.CQQK);
            if (null != this.getCQQK() && !"".equals(this.getCQQK())) {
                log.info("########################::::::::::::::::::"
                        + this.getCQQK());
                this.CQQK = BaseChangeTool.getKQQKbyChar(this.getCQQK());
                log.info("转换完毕：222：：：：：：：：：：：：：：：：：：：：：：：：：：：：：：：" + this.CQQK);
            }
            log.info("########################::::::::::::::::::"
                    + this.getCQQK());
            List<XueShengKQ> all = realTimeService.getKQQK_XS(this.getJS_ID(),
                    this.getJG_ID(), this.getKS_ID(), this.getKCXX_ID(), null,
                    xs.getXsId(), this.getCQQK(), xs.getXh());
            log.info(all.size());
            for (XueShengKQ xk : all) { // 循环课程列表，计算考勤信息
                TONGJI j = new TONGJI();
                j.setXS_ID(xs.getXsId());
                j.setXS_XM(xs.getZsxm());
                j.setCQQK(xk.getCqqk());
                j.setXS_XH(xs.getXh());
                j.setXS_ID(xs.getXsId());
                j.setKCXX_KCMC(xk.getKcxxmc());
                // 课程表中的相关信息
                j.setJS_ID(xk.getJs_id());
                j.setJS_JSMC(xk.getJsmc());
                j.setJG_ID(xk.getLs_id());
                j.setJG_JGMC(xk.getLsmc());
                j.setJG_JGGH(xk.getLsgh());
                j.setKCXX_ID(xk.getKcb_id());
                j.setKCXX_KCMC(xk.getKcb_kcxxmc());
                log.info("TONGJI:kcxxmc::::" + j.getKCXX_KCMC());
                log.info("TONGJI:XUESHENGKQ::::" + xk.getKcxxmc());
                j.setKCXX_ID(xk.getKcbid());
                // 班级名称、年级名称、专业名称：
                j.setBJ_ID(xs.getBjId());
                j.setBJMC(xs.getBJMC());
                j.setNJ_ID(xs.getNjId());
                j.setNJMC(xs.getNJMC());
                j.setZY_ID(xs.getZyId());
                j.setZYMC(xs.getZYMC());
                j.setXY_ID(xs.getXyId());
                j.setXYMC(xs.getXYMC());
                // 上课日期等
                listtj.add(j);
            }
        }
        jsonMap.clear();
        jsonMap.put("total", listtj.size());
        jsonMap.put("rows", listtj);// rows键 存放每页记录 list
        jsonMap.put("page", intPage);
        new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
    }

    /*
     * 如果传递的参数有为空的，则没有信息
     */
    public void jsonOutPutNULL() {
        List<TONGJI> listtj = new ArrayList<TONGJI>();
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        JsonConfig config = new JsonConfig();
        jsonMap.clear();
        TONGJI tj = new TONGJI();
        tj.setXYMC("无记录");
        tj.setZY_ID("null");
        tj.setZYMC("无记录");
        tj.setYSKRS("无记录");
        tj.setQXRS("无记录");
        tj.setZCCXRS("无记录");
        tj.setCDRS("无记录");
        tj.setZTRS("无记录");
        tj.setJS_JSMC("无记录");
        listtj.add(tj);
        jsonMap.put("total", listtj.size());
        jsonMap.put("rows", listtj);// rows键 存放每页记录 list
        jsonMap.put("page", 1);
        new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap, config));
    }

    // 手工执行班级的计算当天信息的方法：
    public void doBanjiExecute() throws Exception {
        log.info("进入到手工班级执行方法中....begin");
        taskService.doSQLbyDay_JIAOSHI(this.getRiqi());
        log.info("进入到手工班级执行方法中....end");
    }

    // 手工执行按学生的计算当天信息的方法：
    public void doXueshengExecute() throws Exception {
        log.info("进入到手工学生执行方法中....begin");
        taskService.doSQLbyDay_XUESHENG(this.getRiqi());
        log.info("进入到手工学生执行方法中....end");
    }

    /**
     * 校长前台统计条件配置页面 保存查询条件
     *
     * @return
     * @throws UnsupportedEncodingException
     */
    public void conditionsConfigurationSave()
            throws UnsupportedEncodingException {
        Map<String, String> jsonMap = new HashMap<String, String>();
        CONDITIONS con = new CONDITIONS();
        con.setId(UUIDTools.getUUID());// 主键id
        if (this.BJ_ID != null && !"".equals(this.BJ_ID)) {
            con.setBj_id(this.BJ_ID);
        }
        if (this.NJ_ID != null && !"".equals(this.NJ_ID)) {
            con.setNj_id(this.NJ_ID);
        }
        if (this.ZY_ID != null && !"".equals(this.ZY_ID)) {
            con.setZy_id(this.ZY_ID);
        }
        if (this.XY_ID != null && !"".equals(this.XY_ID)) {
            con.setXy_id(this.XY_ID);
        }
        con.setQueryTitle(URLDecoder.decode(this.queryTitle, "UTF-8"));
        con.setKsrq(this.KSRQ);
        con.setJsrq(this.JSRQ);
        // 进行保存
        if (tongjiService.addCondition(con)) {
            jsonMap.put("message", "保存成功!");
        } else {
            jsonMap.put("message", "保存失败!");
        }
        new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
    }

    /**
     * 校长前台统计条件配置页面 删除查询条件
     *
     * @return
     */
    public void conditionsConfigurationDel() {
        Map<String, String> jsonMap = new HashMap<String, String>();
        if (this.CondId != null && !"".equals(this.CondId)) {
            // 获取要删除数据的id
            String[] str = CondId.split(",");
            boolean flag = false;
            // 遍历数据的id,对数据进行删除
            for (int i = 0; i < str.length; i++) {
                //删除配置的查询条件
                tongjiService.delCondition(str[i]);
                //判断当前i与str长度是否相等，如果相等证明以删除完成
                if (i == str.length - 1) {
                    flag = true;
                }
            }
            //判断是否删除成功
            if (flag) {
                jsonMap.put("message", "删除成功!");
            } else {
                jsonMap.put("message", "删除失败!");
            }
        }
        new JsonPrintTools().printJSON(JSONObject.fromObject(jsonMap));
    }

    /**
     * 获取全部查询配置条件
     */
    public void listConditionsConfiguration() {
        // 用户存放条件参数
        HashMap<String, String> params = new HashMap<String, String>();
        // 获取查询数据的集合
        List<CONDITIONS> list = tongjiService.findAllConditionsConfiguration(
                params, order, sort);// 每页的数据，放入list
        // 向页面返回json数据
        new JsonPrintTools().printJSON_Array(JSONArray.fromObject(list));
    }

    /**
     * 此数据用于校长前台饼图中 传入学院ID,专业ID,年级ID,班级ID,开始日期,结束日期
     *
     * @return
     */
    public void getDataGraph() throws Exception {
        System.out
                .println("getDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraphgetDataGraph");
        // 用户存放条件参数
        HashMap<String, String> params = new HashMap<String, String>();
        CONDITIONS con = new CONDITIONS();
        params.put("id", this.con_id);
        con = tongjiService.findAllConditionsConfiguration(params, order, sort).get(0);
        System.out.println("配置条件id");
        System.out.println("配置条件id");
        this.XY_ID = con.getXy_id();
        this.ZY_ID = con.getZy_id();
        this.NJ_ID = con.getNj_id();
        this.BJ_ID = con.getBj_id();
        this.KSRQ = con.getKsrq();
        this.JSRQ = con.getJsrq();
        List<TONGJI> all_tongji = new ArrayList<TONGJI>();
        // 判断学院ID是否为空
        if (con.getXy_id() != null && !"".equals(con.getXy_id())) {
            // 判断专业ID是否为空
            if (con.getZy_id() != null && !"".equals(con.getZy_id())) {
                System.out.println("111111111111111111");
                // 判断年级ID是否为空
                if (con.getNj_id() != null && !"".equals(con.getNj_id())) {
                    System.out.println("222222222222222222222222");
                    // 判断班级是否为空
                    if (con.getBj_id() != null && !"".equals(con.getBj_id())) {
                        System.out.println("3333333333333333333");
                        listZHUANYE_BANJITONGJI();
                    } else {
                        System.out.println("4444444444444444");
                        XUEQI xq = new XUEQI();
                        xq.setKSRQ(this.KSRQ);
                        xq.setJSRQ(this.JSRQ);
                        // 年级ID为空时进行年级查询统计
                        HashMap<String, Integer> mapkqxxbj = kcxxxscqService.getBJKQXX(
                                this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                                this.getKCXX_ID(), xq, this.getXINGQI(),
                                this.getZHOU(), this.getKSRQ(), this.getJSRQ(),
                                this.XY_ID, this.ZY_ID, this.NJ_ID,
                                this.getBJ_ID());
                        TONGJI tj = new TONGJI();
                        tj.setXY_ID(this.XY_ID);
                        tj.setBJ_ID(this.BJ_ID);
                        tj.setNJ_ID(this.NJ_ID);
                        tj.setZY_ID(this.ZY_ID);
                        tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                        tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                        tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                        tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                        tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                        new JsonPrintTools().printJSON(JSONObject.fromObject(tj));
                    }
                } else {
                    System.out.println("55555555555555");
                    XUEQI xq = new XUEQI();
                    xq.setKSRQ(this.KSRQ);
                    xq.setJSRQ(this.JSRQ);
                    // 年级ID为空时进行专业查询统计
                    HashMap<String, Integer> mapkqxxbj = kcxxxscqService.getBJKQXX(
                            this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                            this.getKCXX_ID(), xq, this.getXINGQI(), this.getZHOU(),
                            this.getKSRQ(), this.getJSRQ(), this.XY_ID, this.ZY_ID, this.getNJ_ID(),
                            this.getBJ_ID());
                    TONGJI tj = new TONGJI();
                    tj.setXY_ID(this.XY_ID);
                    tj.setZY_ID(this.ZY_ID);
                    if (null != mapkqxxbj) {
                        tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
                        tj.setQXRS(mapkqxxbj.get("qxrs").toString());
                        tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
                        tj.setCDRS(mapkqxxbj.get("cdrs").toString());
                        tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                    }
                }
            } else {
                System.out.println("6666666666666");
                // 专业ID为空按学院进行查询统计
                XUEQI xq = new XUEQI();
                xq.setKSRQ(this.KSRQ);
                xq.setJSRQ(this.JSRQ);
                HashMap<String, Integer> mapkqxxbj = kqxxbjxxService.getBJKQXX(
                        this.getJS_ID(), this.getJG_ID(), this.getKS_ID(),
                        this.getKCXX_ID(), xq, this.getXINGQI(),
                        this.getZHOU(), this.getKSRQ(), this.getJSRQ(),
                        this.XY_ID);
                TONGJI tj = new TONGJI();
                tj.setXY_ID(con.getXy_id());
                tj.setYSKRS("36");
                tj.setQXRS("10");
                tj.setZCCXRS("26");
                tj.setCDRS("3");
                tj.setZTRS("9");
//                tj.setYSKRS(mapkqxxbj.get("ycxrs").toString());
//                tj.setQXRS(mapkqxxbj.get("qxrs").toString());
//                tj.setZCCXRS(mapkqxxbj.get("zcrs").toString());
//                tj.setCDRS(mapkqxxbj.get("cdrs").toString());
//                tj.setZTRS(mapkqxxbj.get("ztrs").toString());
                new JsonPrintTools().printJSON(JSONObject.fromObject(tj));
            }
        }
    }

    public String getXS_CD() {
        return XS_CD;
    }

    public void setXS_CD(String XS_CD) {
        this.XS_CD = XS_CD;
    }

    public String getXS_ID() {
        return XS_ID;
    }

    public void setXS_ID(String XS_ID) {
        this.XS_ID = XS_ID;
    }

    public String getXS_XM() {
        return XS_XM;
    }

    public void setXS_XM(String XS_XM) {
        this.XS_XM = XS_XM;
    }

    public String getQXRS() {
        return QXRS;
    }

    public void setQXRS(String QXRS) {
        this.QXRS = QXRS;
    }

    public String getNJ_ID() {
        return NJ_ID;
    }

    public void setNJ_ID(String NJ_ID) {
        this.NJ_ID = NJ_ID;
    }

    public String getCDRSBL() {
        return CDRSBL;
    }

    public void setCDRSBL(String CDRSBL) {
        this.CDRSBL = CDRSBL;
    }

    public String getXS_XH() {
        return XS_XH;
    }

    public void setXS_XH(String XS_XH) {
        this.XS_XH = XS_XH;
    }

    public String getZCCXRS() {
        return ZCCXRS;
    }

    public void setZCCXRS(String ZCCXRS) {
        this.ZCCXRS = ZCCXRS;
    }

    public String getJS_JSMC() {
        return JS_JSMC;
    }

    public void setJS_JSMC(String JS_JSMC) {
        this.JS_JSMC = JS_JSMC;
    }

    public String getKSRQ() {
        return KSRQ;
    }

    public void setKSRQ(String KSRQ) {
        this.KSRQ = KSRQ;
    }

    public String getXS_QX() {
        return XS_QX;
    }

    public void setXS_QX(String XS_QX) {
        this.XS_QX = XS_QX;
    }

    public String getZTRS() {
        return ZTRS;
    }

    public void setZTRS(String ZTRS) {
        this.ZTRS = ZTRS;
    }

    public String getNJMC() {
        return NJMC;
    }

    public void setNJMC(String NJMC) {
        this.NJMC = NJMC;
    }

    public String getYSKRS() {
        return YSKRS;
    }

    public void setYSKRS(String YSKRS) {
        this.YSKRS = YSKRS;
    }

    public String getJG_JGGH() {
        return JG_JGGH;
    }

    public void setJG_JGGH(String JG_JGGH) {
        this.JG_JGGH = JG_JGGH;
    }

    public String getXS_ZT() {
        return XS_ZT;
    }

    public void setXS_ZT(String XS_ZT) {
        this.XS_ZT = XS_ZT;
    }

    public String getBJMC() {
        return BJMC;
    }

    public void setBJMC(String BJMC) {
        this.BJMC = BJMC;
    }

    public String getBJ_ID() {
        return BJ_ID;
    }

    public void setBJ_ID(String BJ_ID) {
        this.BJ_ID = BJ_ID;
    }

    public String getJG_JGMC() {
        return JG_JGMC;
    }

    public void setJG_JGMC(String JG_JGMC) {
        this.JG_JGMC = JG_JGMC;
    }

    public String getKCXX_KCMC() {
        return KCXX_KCMC;
    }

    public void setKCXX_KCMC(String KCXX_KCMC) {
        this.KCXX_KCMC = KCXX_KCMC;
    }

    public String getSKRQ() {
        return SKRQ;
    }

    public void setSKRQ(String SKRQ) {
        this.SKRQ = SKRQ;
    }

    public String getXINGQI() {
        return XINGQI;
    }

    public void setXINGQI(String XINGQI) {
        // this.XINGQI = XINGQI;
        this.XINGQI = BaseChangeTool.getXingQibyXingQiXH(XINGQI);
    }

    public String getKCXX_ID() {
        return KCXX_ID;
    }

    public void setKCXX_ID(String KCXX_ID) {
        this.KCXX_ID = KCXX_ID;
    }

    public String getZYMC() {
        return ZYMC;
    }

    public void setZYMC(String ZYMC) {
        this.ZYMC = ZYMC;
    }

    public String getZCCXBL() {
        return ZCCXBL;
    }

    public void setZCCXBL(String ZCCXBL) {
        this.ZCCXBL = ZCCXBL;
    }

    public String getXY_ID() {
        return XY_ID;
    }

    public void setXY_ID(String XY_ID) {
        this.XY_ID = XY_ID;
    }

    public String getCQQK() {
        return CQQK;
    }

    public void setCQQK(String CQQK) {
        this.CQQK = CQQK;
    }

    public String getZTRSBL() {
        return ZTRSBL;
    }

    public void setZTRSBL(String ZTRSBL) {
        this.ZTRSBL = ZTRSBL;
    }

    public String getZHOU() {
        return ZHOU;
    }

    public void setZHOU(String ZHOU) {
        // this.ZHOU = ZHOU;
        this.ZHOU = BaseChangeTool.changeZhouIDtoZHOU(ZHOU);
    }

    public String getKS_ID() {
        return KS_ID;
    }

    public void setKS_ID(String KS_ID) {
        this.KS_ID = KS_ID;
    }

    public String getXYMC() {
        return XYMC;
    }

    public void setXYMC(String XYMC) {
        this.XYMC = XYMC;
    }

    public String getCDRS() {
        return CDRS;
    }

    public void setCDRS(String CDRS) {
        this.CDRS = CDRS;
    }

    public String getXUEQI_ID() {
        return XUEQI_ID;
    }

    public void setXUEQI_ID(String XUEQI_ID) {
        this.XUEQI_ID = XUEQI_ID;
    }

    public String getXS_ZCCX() {
        return XS_ZCCX;
    }

    public void setXS_ZCCX(String XS_ZCCX) {
        this.XS_ZCCX = XS_ZCCX;
    }

    public String getJSRQ() {
        return JSRQ;
    }

    public void setJSRQ(String JSRQ) {
        this.JSRQ = JSRQ;
    }

    public String getQXRSBL() {
        return QXRSBL;
    }

    public void setQXRSBL(String QXRSBL) {
        this.QXRSBL = QXRSBL;
    }

    public String getJS_ID() {
        return JS_ID;
    }

    public void setJS_ID(String JS_ID) {
        this.JS_ID = JS_ID;
    }

    public String getJG_ID() {
        return JG_ID;
    }

    public void setJG_ID(String JG_ID) {
        this.JG_ID = JG_ID;
    }

    public String getZY_ID() {
        return ZY_ID;
    }

    public void setZY_ID(String ZY_ID) {
        this.ZY_ID = ZY_ID;
    }

    public TONGJIService getTONGJI() {
        return tongjiService;
    }

    public void settongjiService(TONGJIService tongjiService) {
        this.tongjiService = tongjiService;
    }

    public String getTONGJI_ID() {
        return TONGJI_ID;
    }

    public void setTONGJI_ID(String TONGJI_ID) {
        this.TONGJI_ID = TONGJI_ID;
    }

    public String getOptionflag() {
        return optionflag;
    }

    public void setOptionflag(String optionflag) {
        this.optionflag = optionflag;
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

    public RealTimeService getRealTimeService() {
        return realTimeService;
    }

    public void setRealTimeService(RealTimeService realTimeService) {
        this.realTimeService = realTimeService;
    }

    public KQXX_BJXXService getKqxxbjxxService() {
        return kqxxbjxxService;
    }

    public void setKqxxbjxxService(KQXX_BJXXService kqxxbjxxService) {
        this.kqxxbjxxService = kqxxbjxxService;
    }

    public XUEQIService getXueqiService() {
        return xueqiService;
    }

    public void setXueqiService(XUEQIService xueqiService) {
        this.xueqiService = xueqiService;
    }

    public XueYuanService getXueyuanService() {
        return xueyuanService;
    }

    public void setXueyuanService(XueYuanService xueyuanService) {
        this.xueyuanService = xueyuanService;
    }

    public ZhuanYeService getZhuanyeService() {
        return zhuanyeService;
    }

    public void setZhuanyeService(ZhuanYeService zhuanyeService) {
        this.zhuanyeService = zhuanyeService;
    }

    public NIANJIService getNianjiService() {
        return nianjiService;
    }

    public void setNianjiService(NIANJIService nianjiService) {
        this.nianjiService = nianjiService;
    }

    public BANJIService getBanjiService() {
        return banjiService;
    }

    public void setBanjiService(BANJIService banjiService) {
        this.banjiService = banjiService;
    }

    public KQXX_XSCQService getKcxxxscqService() {
        return kcxxxscqService;
    }

    public void setKcxxxscqService(KQXX_XSCQService kcxxxscqService) {
        this.kcxxxscqService = kcxxxscqService;
    }

    public XsxxService getXsxxService() {
        return xsxxService;
    }

    public void setXsxxService(XsxxService xsxxService) {
        this.xsxxService = xsxxService;
    }

    public TaskService getTaskService() {
        return taskService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getRiqi() {
        return riqi;
    }

    public void setRiqi(String riqi) {
        this.riqi = riqi;
    }

    public String getQueryTitle() {
        return queryTitle;
    }

    public void setQueryTitle(String queryTitle) {
        this.queryTitle = queryTitle;
    }

    public String getCondId() {
        return CondId;
    }

    public void setCondId(String condId) {
        CondId = condId;
    }

    public String getXq_name() {
        return xq_name;
    }

    public void setXq_name(String xq_name) {
        this.xq_name = xq_name;
    }

    public String getXy_name() {
        return xy_name;
    }

    public void setXy_name(String xy_name) {
        this.xy_name = xy_name;
    }

    public String getZy_name() {
        return zy_name;
    }

    public void setZy_name(String zy_name) {
        this.zy_name = zy_name;
    }

    public String getNj_name() {
        return nj_name;
    }

    public void setNj_name(String nj_name) {
        this.nj_name = nj_name;
    }

    public String getBj_name() {
        return bj_name;
    }

    public void setBj_name(String bj_name) {
        this.bj_name = bj_name;
    }

    public String getCon_id() {
        return con_id;
    }

    public void setCon_id(String con_id) {
        this.con_id = con_id;
    }

    // 后台打印输出传递的参数：
    public void dayin() {
        log.info("传入参数打印开始：----------------------------------------------------begin");
        log.info("教室ID是：" + this.getJS_ID());
        log.info("老师ID是：" + this.getJG_ID());
        log.info("课时ID是：" + this.getKS_ID());
        log.info("课程信息ID是：" + this.getKCXX_ID());
        log.info("学期ID是：" + this.getXUEQI_ID());
        log.info("星期ID是：" + this.getXINGQI());
        log.info("周ID是：" + this.getZHOU());
        log.info("开始日期是：" + this.getKSRQ());
        log.info("结束日期是：" + this.getJSRQ());
        log.info("专业ID是：" + this.getZY_ID());
        log.info("年级ID是：" + this.getNJ_ID());
        log.info("班级ID是：" + this.getBJ_ID());
        log.info("传入参数打印结束：------------------------------------------------------end");
    }
}
