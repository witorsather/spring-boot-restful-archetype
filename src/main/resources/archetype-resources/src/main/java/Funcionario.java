package br.com.condessalovelace.servicorestful;

/**
 * Representa um funcionário como recurso provido por um serviço.
 * 
 * @author condessalovelace
 *
 */
public class Funcionario {
	private String nome;
	private Integer idade;
	private String cargo;

	public Funcionario() {

	}

	public Funcionario(String nome, Integer idade, String cargo) {
		this.nome = nome;
		this.idade = idade;
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public boolean equals(Object obj) {
		return nome.equals(((Funcionario) obj).getNome());
	}
}
