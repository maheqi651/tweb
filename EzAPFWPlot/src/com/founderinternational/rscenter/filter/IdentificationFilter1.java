package com.founderinternational.rscenter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.founderinternational.rscenter.tools.Constants;

public class IdentificationFilter1 implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		    HttpServletRequest request=(HttpServletRequest)arg0;
		    if(request.getSession().getAttribute(Constants.USER)!=null)
		    {
		    	   arg2.doFilter(arg0, arg1);
		    }
		    else
		    {
		    	((HttpServletResponse)arg1).sendRedirect("login.htm");
		    }
		    
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
