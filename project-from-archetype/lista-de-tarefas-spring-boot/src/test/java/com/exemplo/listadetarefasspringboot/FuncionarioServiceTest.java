package br.com.condessalovelace.servicorestful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import br.com.condessalovelace.servicorestful.Funcionario;
import br.com.condessalovelace.servicorestful.FuncionarioInfo;
import br.com.condessalovelace.servicorestful.FuncionarioService;
import br.com.condessalovelace.servicorestful.RestClient;
import br.com.condessalovelace.servicorestful.ServicoRestfulApplication;

/**
 * Classe que testa o serviço {@link FuncionarioService} cobrindo as
 * possibilidades de invocação dos seus métodos.
 * 
 * @author condessalovelace
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { RestClient.class, BasicAuthenticationEntryPointImpl.class })
public class FuncionarioServiceTest {
	@Autowired
	public RestClient<Funcionario> restClientFuncionario;
	@Autowired
	public RestClient<FuncionarioInfo> restClientFuncionarioInfo;

	@Before
	public void beforeClass() throws IOException {
		ServicoRestfulApplication.main(new String[] {});
	}

	@After
	public void afterClass() {
		ServicoRestfulApplication.shutDown();
	}

	@Test
	public void tesGetFuncionarioNotFound() throws IOException {
		try {
			restClientFuncionario.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class,
					createHeaders());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
				fail();
			}
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	@Test
	public void testGetFuncionarioBody() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<Funcionario> response = restClientFuncionario
					.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class, createHeaders());
			assertEquals(funcionario, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void testGetFuncionarioStatusCode() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<Funcionario> response = restClientFuncionario
					.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class, createHeaders());
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPostFuncionarioOk() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<Funcionario> response = restClientFuncionario
					.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class, createHeaders());
			assertEquals(funcionario, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPostFuncionarioStatusCode() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			assertEquals(HttpStatus.CREATED, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPostFuncionarioHeader() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			assertEquals("http://localhost:9090/funcionario/Giuliana", response.getHeaders().get("Location").get(0));
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPostFuncionarioJaExistente() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			response = restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario,
					createHeaders());
			assertEquals(HttpStatus.SEE_OTHER, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPutFuncionarioOk() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			funcionario.setIdade(28);
			response = restClientFuncionario.putCall("http://localhost:9090/funcionario/", funcionario,
					createHeaders());
			response = restClientFuncionario.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class,
					createHeaders());
			assertEquals(funcionario, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPutFuncionarioStatusCode() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			funcionario.setIdade(28);
			response = restClientFuncionario.putCall("http://localhost:9090/funcionario/", funcionario,
					createHeaders());
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPutFuncionarioBody() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			ResponseEntity<Funcionario> response = restClientFuncionario.postCall("http://localhost:9090/funcionario/",
					funcionario, createHeaders());
			funcionario.setIdade(28);
			response = restClientFuncionario.putCall("http://localhost:9090/funcionario/", funcionario,
					createHeaders());
			assertEquals(funcionario, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPutFuncionarioNaoExistente() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 28, "Arquiteta de software");
			restClientFuncionario.putCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
				fail();
			}
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	@Test
	public void tesPatchFuncionarioOk() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			FuncionarioInfo funcionarioInfo = new FuncionarioInfo("Giuliana", 28);
			restClientFuncionarioInfo.patchCall("http://localhost:9090/funcionario/", funcionarioInfo, createHeaders());
			ResponseEntity<Funcionario> response = restClientFuncionario
					.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class, createHeaders());
			funcionario.setIdade(28);
			assertEquals(funcionario, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPatchFuncionarioNaoExistente() throws IOException {
		try {
			FuncionarioInfo funcionarioInfo = new FuncionarioInfo("Giuliana", 28);
			restClientFuncionarioInfo.patchCall("http://localhost:9090/funcionario/", funcionarioInfo, createHeaders());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
				fail();
			}
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	@Test
	public void tesPatchFuncionarioStatusCode() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			FuncionarioInfo funcionarioInfo = new FuncionarioInfo("Giuliana", 28);
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<FuncionarioInfo> response = restClientFuncionarioInfo
					.patchCall("http://localhost:9090/funcionario/", funcionarioInfo, createHeaders());
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void tesPatchFuncionarioBody() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			FuncionarioInfo funcionarioInfo = new FuncionarioInfo("Giuliana", 28);
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<FuncionarioInfo> response = restClientFuncionarioInfo
					.patchCall("http://localhost:9090/funcionario/", funcionarioInfo, createHeaders());
			assertEquals(funcionarioInfo, response.getBody());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteFuncionarioNotFound() throws IOException {
		try {
			restClientFuncionario.deleteCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class,
					createHeaders());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
				fail();
			}
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	@Test
	public void testDeleteFuncionarioStatusCode() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			ResponseEntity<Funcionario> response = restClientFuncionario
					.deleteCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class, createHeaders());
			assertEquals(HttpStatus.OK, response.getStatusCode());
		} catch (HttpClientErrorException ex) {
			fail();
		}
	}

	@Test
	public void testDeleteFuncionarioOk() throws IOException {
		try {
			Funcionario funcionario = new Funcionario("Giuliana", 20, "Arquiteta de software");
			restClientFuncionario.postCall("http://localhost:9090/funcionario/", funcionario, createHeaders());
			restClientFuncionario.deleteCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class,
					createHeaders());
			restClientFuncionario.getCall("http://localhost:9090/funcionario/Giuliana", Funcionario.class,
					createHeaders());
		} catch (HttpClientErrorException ex) {
			if (ex.getStatusCode() != HttpStatus.NOT_FOUND) {
				fail();
			}
			assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
		}
	}

	public HttpHeaders createHeaders() throws IOException {
		HttpHeaders httpHeaders = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "merge-patch+json");
		httpHeaders.setContentType(mediaType);
		String auth = ConfigurationIO.getInstance().getUser() + ":" + ConfigurationIO.getInstance().getPassword();
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes());
		String authHeader = "Basic " + new String(encodedAuth);
		httpHeaders.set("Authorization", authHeader);
		return httpHeaders;
	}
}
