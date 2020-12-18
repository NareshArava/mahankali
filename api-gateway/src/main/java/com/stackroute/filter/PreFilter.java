package com.stackroute.filter;


import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException 
	{
		HttpServletRequest httpRequest = (HttpServletRequest) RequestContext.getCurrentContext();
		
		System.out.println(httpRequest.getMethod());
		System.out.println(httpRequest.getRequestURL());
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre-filter";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.PRE_DECORATION_FILTER_ORDER;
	}

}
