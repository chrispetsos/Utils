package io.swagger.api.util;

import java.io.IOException;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

public class Bootstrap extends HttpServlet {
	private String clientAppBasePath = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		BeanConfig beanConfig = new BeanConfig();
		//beanConfig.setSchemes(new String[]{"http"});
		String contextPath = config.getServletContext().getContextPath();
		String basePathWithoutContext = config.getInitParameter("basePathWithoutContext");
		if(contextPath == null)
		{
			beanConfig.setBasePath(basePathWithoutContext);        	
		}
		else
		{
			beanConfig.setBasePath(contextPath + basePathWithoutContext);        	
		}

		clientAppBasePath = beanConfig.getBasePath(); 

		beanConfig.setTitle(config.getInitParameter("title"));
		beanConfig.setVersion(config.getInitParameter("version"));
		beanConfig.setScan(true);

	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		// Set response content type
		response.setContentType(MediaType.APPLICATION_JSON);

		// New location to be redirected
		//String site = new String("http://www.photofuntoos.com");

		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", clientAppBasePath + "/swagger.json");    
	}
}