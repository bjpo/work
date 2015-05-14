package com.hrbsys.tools;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

public class JsonPrintTools {
	public void printJSON(JSONObject jsonMap){
		try {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
		out = response.getWriter();
		out.print(JSONObject.fromObject(jsonMap));
        out.flush();
        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void printJSON_Array(JSONArray jsonMap){
		try {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out;
		out = response.getWriter();
		out.print(JSONArray.fromObject(jsonMap));
        out.flush();
        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
