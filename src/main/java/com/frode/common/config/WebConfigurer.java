package com.frode.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
	@Autowired
    SchoolConfig schoolConfig;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {//"file:///E:/var/spring/uploaded_files/"  后面是转换文件url的地址
		registry.addResourceHandler("/files/**").addResourceLocations("file:///"+ schoolConfig.getUploadPath());
	}

}