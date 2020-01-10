package com.geekzone.seguranca;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain){
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = ((HttpServletRequest) req).getSession(false);
		Object user = (session != null) ? session.getAttribute("logado") : null;
		boolean isLog = (boolean) session.getAttribute("logado");
		//System.out.println(session.getAttribute("logado"));
		HttpServletResponse response = (HttpServletResponse) res;
		String redirectURL = "";
		
		if (user != null) {
			if (isLog) {
				try {
					chain.doFilter(request, res);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} else {
				redirectURL = request.getContextPath() + "/index.html";
				try {
					response.sendRedirect(redirectURL);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			redirectURL = request.getContextPath() + "/index.html";
			try {
				response.sendRedirect(redirectURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
