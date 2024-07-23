package dsw.CarDealership.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import dsw.CarDealership.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Loja")
public class Loja extends Usuario{
	
	@NotNull(message = "{NotNull.loja.nome}")
	@Column(nullable = false, length = 19)
	private String nome;
	@NotNull(message = "{NotNull.loja.descricao}")
	@Column(nullable = false, length = 64)
	private String descricao;
	@NotNull(message = "{NotNull.loja.cnpj}")
	@UniqueCNPJ(message = "{NotUnique.loja.cnpj}")
	@Column(nullable = false, length = 19, unique = true)
	private String cnpj;
	@OneToMany(mappedBy ="loja", cascade = CascadeType.REMOVE)
	private List<Carro> carros;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
