package dsw.CarDealership.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import dsw.CarDealership.validation.UniqueCPF;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario{
	
	@NotNull(message = "{NotNull.cliente.nome}")
	@Column(nullable = false, length = 19)
	private String nome;
	@NotNull(message = "{NotNull.cliente.cpf}")
	@UniqueCPF(message = "{NotUnique.cliente.cpf}")
	@Column(nullable = false, length = 19)
	private String cpf;
	@NotNull(message = "{NotNull.cliente.telefone}")
	@Column(nullable = false, length = 19)
	private String telefone;
	@NotNull(message = "{NotNull.cliente.sexo}")
	@Column(nullable = false, length = 19)
	private String sexo;
	@NotNull(message = "{NotNull.cliente.dataDeNascimento}")
	@Column(nullable = false, length = 30)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataDeNascimento;
	@OneToMany(mappedBy ="cliente", cascade = CascadeType.REMOVE)
	private List<Proposta> proposta;

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
