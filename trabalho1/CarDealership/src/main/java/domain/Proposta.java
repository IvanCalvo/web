package domain;

import java.time.LocalDate;

public class Proposta {
	private Long id;
	private Float valor;
	private String condPagamento;
	private LocalDate dataProposta;
	private String status;
	private Cliente cliente;
	private Carro carro;
	
	
	public Proposta(Long id) {
		this.id = id;
	}
	
	public Proposta(Cliente cliente, Carro carro) {
		this.cliente = cliente;
		this.carro = carro;
	}
	
	public Proposta(Float valor, String condPagamento, LocalDate dataProposta, Cliente cliente, Carro carro) {
		this.valor = valor;
		this.condPagamento = condPagamento;
		this.dataProposta = dataProposta;
		this.status = "ABERTO";
		this.cliente = cliente;
		this.carro = carro;
	}
	
	public Proposta ( Float valor, String condPagamento,
			LocalDate dataProposta, String status, Cliente cliente, Carro carro) {
		this.valor = valor;
		this.condPagamento = condPagamento;
		this.dataProposta = dataProposta;
		this.status = status;
		this.cliente = cliente;
		this.carro = carro;
	}
	
	public Proposta ( Long id, Float valor, String condPagamento,
			LocalDate dataProposta, String status, Cliente cliente, Carro carro) {
		this(valor, condPagamento, dataProposta, status, cliente, carro);
		this.id = id;
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
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
