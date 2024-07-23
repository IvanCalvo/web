package dsw.CarDealership.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import dsw.CarDealership.validation.UniqueEMAIL;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity<Long> {
	
	@NotNull(message = "{NotNull.usuario.email}")
	@UniqueEMAIL(message = "{NotUnique.usuario.email}")
	@Column(nullable = false, unique = true, length = 19)
	private String email;
	
	@NotNull(message = "{NotNull.usuario.senha}")
	@Column(nullable = false, length = 64)
	private String senha;
	
	@NotNull(message = "{NotNull.usuario.papel}")
	@Column(nullable = false, length = 19)
	private String papel;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}
}
