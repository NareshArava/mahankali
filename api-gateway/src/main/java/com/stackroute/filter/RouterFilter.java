package com.stackroute.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import com.netflix.zuul.exception.ZuulException;

public class RouterFilter extends ZuulFilter {
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
		return "router-filter";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return FilterConstants.SIMPLE_HOST_ROUTING_FILTER_ORDER;
	}

}
