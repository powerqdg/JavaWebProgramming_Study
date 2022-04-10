package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter  implements Filter{
	FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("CharacterEncodingFilter init() 실행");
		this.config = config;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		System.out.println("CharacterEncodingFilter doFilter() 실행");
		// 서블릿을 실행한 전 수행할 작업 여기에
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);
		
		// 서블릿을 실행한 후 수행할 작업 여기에
		
	}
	
	@Override
	public void destroy() {
		System.out.println("CharacterEncodingFilter destroy() 실행");
	}
}
