package in.ols.rest.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import in.ols.rest.service.ISessionService;

@Component
@Order(value = 1)
public class SessionCheckFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionCheckFilter.class);

	@Autowired
	private ISessionService sessionService;

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletResponse response = (HttpServletResponse) res;
			HttpServletRequest request = (HttpServletRequest) req;
			
			String username = (String) request.getHeader("username");
			MDC.put("username", username);								

			@SuppressWarnings("unused")
			boolean validSession = false;
			if (request.getServletPath().startsWith("/swagger/")
					|| request.getServletPath().startsWith("/api-docs")) {
				chain.doFilter(req, res);
			} else {
				
				String apiToken = request.getHeader("apiToken");
				logger.debug("Got apiToken : {}", apiToken);
				if (!sessionService.isValidSession(apiToken)) {
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				} else {
					chain.doFilter(req, res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
	}
}
