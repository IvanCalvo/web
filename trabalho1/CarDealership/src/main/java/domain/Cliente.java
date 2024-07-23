package domain;

import java.time.LocalDate;

public class Cliente {
	private String nome;
	private Long id_usuario;
	private String cpf;
	private String telefone;
	private String sexo;
	private LocalDate dataDeNascimento;
	
	public Cliente(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public Cliente(String cpf, String telefone, String nome, 
			String sexo, LocalDate dataDeNascimento) {
		this.cpf = cpf;
		this.telefone = telefone;
		this.nome = nome;
		this.sexo = sexo;
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public Cliente(Long id_usuario, String cpf, String telefone, String nome,
			String sexo, LocalDate dataDeNascimento) {
		this(cpf,  telefone, nome, sexo, dataDeNascimento);
		this.id_usuario = id_usuario;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
}
