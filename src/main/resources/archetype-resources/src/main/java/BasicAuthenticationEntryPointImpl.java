package br.com.condessalovelace.servicorestful;

import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class BasicAuthenticationEntryPointImpl extends BasicAuthenticationEntryPoint {
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("servicorestful");
		super.afterPropertiesSet();
	}
}
