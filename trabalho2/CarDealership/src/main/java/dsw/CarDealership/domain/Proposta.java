package dsw.CarDealership.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("serial")
@Entity
@Table(name = "Proposta")
public class Proposta extends AbstractEntity<Long>{
	@NotNull(message = "{NotNull.proposta.valor}")
	@Column(columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal valor;
	@NotNull(message = "{NotNull.proposta.condPagamento}")
	@Column(nullable = false, length = 19)
	private String condPagamento;
	@NotNull(message = "{NotNull.proposta.dataProposta}")
	@Column(nullable = false, length = 30)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataProposta;
	@NotNull(message = "{NotNull.proposta.status}")
	@Column(nullable = false, length = 19)
	private String status;
	@NotNull(message = "{NotNull.proposta.cliente}")
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@NotNull(message = "{NotNull.proposta.carro}")
	@ManyToOne
	@JoinColumn(name = "carro_id")
	private Carro carro;
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getCondPagamento() {
		return condPagamento;
	}
	public void setCondPagamento(String condPagamento) {
		this.condPagamento = condPagamento;
	}
	public LocalDate getdataProposta() {
		return dataProposta;
	}
	public void setdataProposta(LocalDate dataProposta) {
		this.dataProposta = dataProposta;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}
}
