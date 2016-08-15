package com.founderinternational.rscenter.tools;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONBuilder;

import org.springframework.web.servlet.view.AbstractView;

public class CovertToJson extends AbstractView {
		 

		@Override
		protected void renderMergedOutputModel(Map<String, Object> arg0,
				HttpServletRequest arg1, HttpServletResponse arg2)
				throws Exception {
			// arg2.setContentType("text/html; charset=utf-8");
             PrintWriter out = arg2.getWriter();
			 out.print(new String(URLDecoder.decode(JSONObject.fromObject(arg0).toString(),"UTF-8")));
		}
}
