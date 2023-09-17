package br.com.condessalovelace.servicorestful;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Encoder para as credenciais enviadas via cabeçalho HTTP. Por simplicidade,
 * nenhuma codificação é feita nos valores.
 * 
 * @author condessalovelace
 *
 */
public class NoOpPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}

}
