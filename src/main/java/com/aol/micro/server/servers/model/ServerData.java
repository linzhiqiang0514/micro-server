package com.aol.micro.server.servers.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.aol.micro.server.auto.discovery.RestResource;
import com.aol.micro.server.module.Module;
import com.aol.micro.server.rest.swagger.SwaggerInitializer;
import com.aol.micro.server.utility.UsefulStaticMethods;
import com.google.common.collect.ImmutableList;

@Getter
public class ServerData {

	private final int port;
	private final ImmutableList<FilterData> filterDataList;
	private final ImmutableList<ServletData> servletDataList;
	private final ImmutableList<RestResource> resources;
	private final AnnotationConfigWebApplicationContext rootContext;
	private final String baseUrlPattern;
	private final Module module;

	public ServerData(int port, List<FilterData> filterDataList, List<ServletData> servletDataList, List<RestResource> resources, AnnotationConfigWebApplicationContext rootContext,
			String baseUrlPattern, Module module) {

		this.port = port;
		this.module = module;
		this.filterDataList = ImmutableList.copyOf(UsefulStaticMethods.either(filterDataList, new ArrayList<FilterData>()));
		this.servletDataList = ImmutableList.copyOf(UsefulStaticMethods.either(servletDataList, new ArrayList<ServletData>()));
		this.resources = ImmutableList.copyOf(resources);
		this.rootContext = rootContext;
		this.baseUrlPattern = baseUrlPattern;
	}

	public void init() {
		rootContext.getBean(SwaggerInitializer.class).setServerData(this);
	}
}