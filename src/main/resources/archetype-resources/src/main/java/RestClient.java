package br.com.condessalovelace.servicorestful;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.client.RestTemplate;

/**
 * Cliente REST que utiliza o Spring Template para acessar recursos. Implementa
 * Basic Authentication com as credenciais padrão: user e password.
 * 
 * @author condessalovelace
 */
@SuppressWarnings("unchecked")
@Configuration
@EnableWebSecurity
public class RestClient<T> extends WebSecurityConfigurerAdapter {
	@Autowired
	private BasicAuthenticationEntryPointImpl authenticationEntryPoint;

	public ResponseEntity<T> getCall(String url, Class<T> classe, HttpHeaders httpHeaders) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<T> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(url, HttpMethod.GET, entity, classe);
	}

	public ResponseEntity<T> postCall(String url, T resource, HttpHeaders httpHeaders) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<T> entity = new HttpEntity<>(resource, httpHeaders);
		return (ResponseEntity<T>) restTemplate.exchange(url, HttpMethod.POST, entity, resource.getClass());
	}

	public ResponseEntity<T> putCall(String url, T resource, HttpHeaders httpHeaders) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<T> entity = new HttpEntity<>(resource, httpHeaders);
		return (ResponseEntity<T>) restTemplate.exchange(url, HttpMethod.PUT, entity, resource.getClass());
	}

	public ResponseEntity<T> patchCall(String url, T resource, HttpHeaders httpHeaders) throws IOException {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		HttpEntity<T> entity = new HttpEntity<>(resource, httpHeaders);
		return (ResponseEntity<T>) restTemplate.exchange(url, HttpMethod.PATCH, entity, resource.getClass());
	}

	public ResponseEntity<T> deleteCall(String url, Class<T> classe, HttpHeaders httpHeaders) throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<T> entity = new HttpEntity<>(httpHeaders);
		return restTemplate.exchange(url, HttpMethod.DELETE, entity, classe);
	}

	@Bean
	public CsrfTokenRepository createCsrfTokenRepository() {
		return CookieCsrfTokenRepository.withHttpOnlyFalse();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().csrf().disable().authorizeRequests().antMatchers("/css/**", "/index").permitAll()
				.anyRequest().authenticated().and().httpBasic().authenticationEntryPoint(authenticationEntryPoint);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		final String user = ConfigurationIO.getInstance().getUser();
		final String password = ConfigurationIO.getInstance().getPassword();
		auth.inMemoryAuthentication().passwordEncoder(new NoOpPasswordEncoder()).withUser(user).password(password)
				.roles("USER");
	}
}