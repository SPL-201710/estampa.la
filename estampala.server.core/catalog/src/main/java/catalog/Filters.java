package catalog;

import javax.servlet.Filter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import authorization.AuthorizationFilter;

public class Filters extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new AuthorizationFilter()};
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		 return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}
}
