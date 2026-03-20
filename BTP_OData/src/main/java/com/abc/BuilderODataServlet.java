package com.abc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.core.servlet.ODataServlet;
import org.springframework.context.ApplicationContext;

import com.abc.util.SpringUtils;




@WebServlet(urlPatterns = { "/jatin.svc/*" }

, initParams = {
		@WebInitParam(name = "javax.ws.rs.Application", value = "org.apache.olingo.odata2.core.rest.app.ODataApplication"),
		@WebInitParam(
			    name = "org.apache.olingo.odata2.service.factory",
			    value = "com.abc.annotatioprocessor.MyODataServiceFactory"
			)
		}

)

public class BuilderODataServlet extends ODataServlet {

	private static final long serialVersionUID = 1L;

	private String oDataServiceFactoryBeanName = "com.abc.annotation.processor.MyODataServiceFactory";

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ApplicationContext applicationContext = SpringUtils.getApplicationContext();
		
		if (applicationContext == null) {
			System.out.println("Spring context is NULL");
		    resp.sendError(500, "Spring context not initialized");
			return;
		}
		
		ODataServiceFactory oDataServiceFactory = (ODataServiceFactory)applicationContext.getBean(this.oDataServiceFactoryBeanName);
		req.setAttribute("org.apache.olingo.odata2.service.factory.instance", oDataServiceFactory);
		super.service(req, resp);
	}

	public String getoDataServiceFactoryBeanName() {
		return oDataServiceFactoryBeanName;
	}

	public void setoDataServiceFactoryBeanName(String oDataServiceFactoryBeanName) {
		this.oDataServiceFactoryBeanName = oDataServiceFactoryBeanName;
	}
}
