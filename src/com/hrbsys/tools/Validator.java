package com.hrbsys.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	/** 整数 */
	private static final String v_Integer = "^-?[1-9]\\d*$";

	public static boolean checkInteger(String value) {
		return matchRegex(v_Integer, value);
	}

	/** 正整数 */
	private static final String v_pInteger = "^[1-9]\\d*$";

	public static boolean checkPInteger(String value) {
		return matchRegex(v_pInteger, value);
	}

	/** 负整数 */
	private static final String v_nInteger = "^-[1-9]\\d*$";

	public static boolean checkNInteger(String value) {
		return matchRegex(v_nInteger, value);
	}

	/** 数字 */
	private static final String v_Number = "^([+-]?)\\d*\\.?\\d+$";

	public static boolean checkNumber(String value) {
		return matchRegex(v_Number, value);
	}

	/** 正数 */
	private static final String v_pNumber = "^[1-9]\\d*|0$";

	public static boolean checkPNumber(String value) {
		return matchRegex(v_pNumber, value);
	}

	/** 负数 */
	private static final String v_nNumber = "^-[1-9]\\d*|0$";

	public static boolean checkNNumber(String value) {
		return matchRegex(v_nNumber, value);
	}

	/** 浮点数 */
	private static final String v_Float = "^([+-]?)\\d*\\.\\d+$";

	public static boolean checkFloat(String value) {
		return matchRegex(v_Float, value);
	}

	/** 正浮点数 */
	private static final String v_pFloat = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

	public static boolean checkPFloat(String value) {
		return matchRegex(v_pFloat, value);
	}

	/** 负浮点数 */
	private static final String v_nFloat = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

	public static boolean checkNFloat(String value) {
		return matchRegex(v_nFloat, value);
	}

	/** 非负浮点数（正浮点数 + 0） */
	private static final String v_upFloat = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

	public static boolean checkUPFloat(String value) {
		return matchRegex(v_upFloat, value);
	}

	/** 非正浮点数（负浮点数 + 0） */
	private static final String v_unFloat = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";

	public static boolean checkUNFloat(String value) {
		return matchRegex(v_unFloat, value);
	}

	/** 邮件 */
	private static final String v_Email = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

	public static boolean checkEmail(String value) {
		return matchRegex(v_Email, value);
	}

	/** 仅中文 */
	private static final String v_Chinese = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

	public static boolean checkChinese(String value) {
		return matchRegex(v_Chinese, value);
	}

	/** 邮编 */
	private static final String v_ZipCode = "^\\d{6}$";

	public static boolean checkZipCode(String value) {
		return matchRegex(v_ZipCode, value);
	}

	/** 手机 */
	private static final String v_Mobile = "^(13|15)[0-9]{9}$";

	public static boolean checkMobile(String value) {
		return matchRegex(v_Mobile, value);
	}

	/** 日期 */
	private static final String v_Date = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";

	public static boolean checkDate(String value) {
		return matchRegex(v_Date, value);
	}
	
	private static final String v_Date1 = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	public static boolean checkDate1(String value) {
		return matchRegex(v_Date1, value);
	}

	/** 电话号码的函数(包括验证国内区号,国际区号,分机号) */
	private static final String v_Tel = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";

	public static boolean checkTel(String value) {
		return matchRegex(v_Tel, value);
	}

	/** 身份证 */
	private static final String v_Idcard = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

	public static boolean checkIdCard(String value) {
		return matchRegex(v_Idcard, value);
	}
	
	public static boolean checkEthnic(String value){
		String strArray[] = {"汉族","壮族","满族","回族","苗族","维吾尔族","土家族","彝族","蒙古族","藏族",
				"布依族","侗族","瑶族","朝鲜族","白族","哈尼族","哈萨克族","黎族","傣族","畲族","傈僳族",
				"仡佬族","东乡族","高山族","拉祜族","水族","佤族","纳西族","羌族","土族","仫佬族","锡伯族",
				"柯尔克孜族","达斡尔族","景颇族","毛南族","撒拉族","塔吉克族","阿昌族","普米族","鄂温克族","怒族",
				"京族","基诺族","德昂族","保安族","俄罗斯族","裕固族","乌兹别克族","门巴族","鄂伦春族","独龙族",
				"塔塔尔族","赫哲族","珞巴族","布朗族"};
		for(int i = 0 ; i < strArray.length; i++)
		{
			if(value.equals(strArray[i])){
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkProvice(String value){
		String strArray[] = {"北京","天津","重庆","上海","河北","山西","辽宁","吉林","黑龙江","江苏","浙江",
				"安徽","福建","江西","山东","河南","湖北","湖南","广东","海南","四川","贵州","云南","陕西","甘肃",
				"青海","台湾","内蒙古自治区","广西壮族自治区","宁夏回族自治区","新疆维吾尔自治区","西藏自治区","香港","澳门"};
		for(int i = 0 ; i < strArray.length; i++)
		{
			if(value.equals(strArray[i])){
				return true;
			}
		}
		return false;
	}
	public static boolean checkGraduating(String value){
		String strArray[] = {"全日制","自考","函授","成人高考","网络教育","电大","夜校"};
		for(int i = 0 ; i < strArray.length; i++)
		{
			if(value.equals(strArray[i])){
				return true;
			}
		}
		return false;
	}

	private static boolean matchRegex(String regex, String value) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.matches();
	}
}
