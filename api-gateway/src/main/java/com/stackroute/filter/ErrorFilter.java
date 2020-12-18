package com.stackroute.filter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class ErrorFilter extends ZuulFilter {
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "error-filter";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.SEND_ERROR_FILTER_ORDER;
	}

}
