package br.com.condessalovelace.servicorestful;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Serviço capaz de criar, atualizar, deletar e recuperar funcionários. A
 * persistência dos dados é feita em memória.
 * 
 * @author condessalovelace
 *
 */
@RequestMapping("/funcionario")
@RestController
public class FuncionarioService {
	private List<Funcionario> funcionarios = new ArrayList<>();

	@GetMapping
	@RequestMapping("/{nome}")
	public ResponseEntity<Funcionario> buscarFuncionario(@PathVariable(value = "nome") String nome) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getNome().equals(nome))
				return ResponseEntity.ok(funcionario);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Funcionario> criarFuncionario(@RequestBody Funcionario funcionario) {
		Boolean criado = criar(funcionario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nome}")
				.buildAndExpand(funcionario.getNome()).toUri();
		if (criado) {
			return ResponseEntity.created(location).build();
		} else {
			return ResponseEntity.status(HttpStatus.SEE_OTHER).location(location).build();
		}
	}

	@PutMapping
	public ResponseEntity<Funcionario> atualizarFuncionario(@RequestBody Funcionario funcionario) {
		if (atualizarCompleto(funcionario))
			return ResponseEntity.ok(funcionario);
		else
			return ResponseEntity.notFound().build();
	}

	@PatchMapping
	public ResponseEntity<FuncionarioInfo> atualizarFuncionario(@RequestBody FuncionarioInfo funcionarioInfo) {
		if (atualizarParcial(funcionarioInfo))
			return ResponseEntity.ok(funcionarioInfo);
		else
			return ResponseEntity.notFound().build();
	}

	@DeleteMapping(value = "/{nome}")
	public ResponseEntity<Funcionario> removerFuncionario(@PathVariable(value = "nome") String nome) {
		Funcionario funcionario = remover(nome);
		if (funcionario == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().build();
	}

	private Boolean criar(Funcionario funcionario) {
		if (!funcionarios.contains(funcionario))
			return funcionarios.add(funcionario);
		else
			return false;
	}

	private Boolean atualizarCompleto(Funcionario funcionarioCompleto) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getNome().equals(funcionarioCompleto.getNome())) {
				BeanUtils.copyProperties(funcionarioCompleto, funcionario);
				return true;
			}
		}
		return false;
	}

	private Funcionario remover(String nome) {
		Iterator<Funcionario> it = funcionarios.iterator();
		while (it.hasNext()) {
			Funcionario funcionario = it.next();
			if (funcionario.getNome().equals(nome)) {
				it.remove();
				return funcionario;
			}
		}
		return null;
	}

	private Boolean atualizarParcial(FuncionarioInfo funcionarioInfo) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getNome().equals(funcionarioInfo.getNome())) {
				BeanUtils.copyProperties(funcionarioInfo, funcionario, getNullProperties(funcionarioInfo));
				return true;
			}
		}
		return false;
	}

	private String[] getNullProperties(FuncionarioInfo funcionarioInfo) {
		List<String> nullPropertiesList = new ArrayList<>();
		String[] nullPropertiesArray = new String[] {};
		try {
			for (Field field : funcionarioInfo.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object attribute = field.get(funcionarioInfo);
				if (attribute == null) {
					nullPropertiesList.add(field.getName());
				}
			}
		} catch (Exception e) {
			// DO NOTHING!
		}
		return nullPropertiesList.toArray(nullPropertiesArray);
	}
}
