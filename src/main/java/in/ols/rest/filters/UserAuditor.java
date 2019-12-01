package in.ols.rest.filters;

import org.slf4j.MDC;
import org.springframework.data.domain.AuditorAware;

public class UserAuditor implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return MDC.get("username");
	}
}
