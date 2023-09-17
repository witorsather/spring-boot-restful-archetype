package br.com.condessalovelace.servicorestful;

/**
 * Essa classe representa um {@link Funcionario} mas o seu propósito é
 * encapsular apenas uma parte das informações do mesmo, para fins de
 * atualização parcial do recurso.
 * 
 * @author condessalovelace
 *
 */
public class FuncionarioInfo {
	private String nome;
	private Integer idade;
	private String cargo;

	public FuncionarioInfo() {

	}

	public FuncionarioInfo(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
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
		Boolean nomesIguais = (nome == null && ((FuncionarioInfo) obj).getNome() == null)
				|| nome.equals(((FuncionarioInfo) obj).getNome());
		Boolean idadesIguais = (idade == null && ((FuncionarioInfo) obj).getIdade() == null)
				|| idade.equals(((FuncionarioInfo) obj).getIdade());
		Boolean cargosIguais = (cargo == null && ((FuncionarioInfo) obj).getCargo() == null)
				|| cargo.equals(((FuncionarioInfo) obj).getCargo());
		return nomesIguais && idadesIguais && cargosIguais;
	}
}
