package domain;

public class Carro {
	private Long id;
	private Loja loja; 
	private String placa;
	private String modelo; 
	private String chassi; 
	private Integer ano;
	private Integer quilometragem;  
	private String descricao;
	private Float valor;  
	private String fotos;
	
	public Carro(Long id) {
		this.id = id;  
	}
	
	public Carro(Loja loja, String placa, String modelo, String chassi, Integer ano,
				Integer quilometragem, String descricao, Float valor, String fotos) {
		this.loja = loja;
		this.placa = placa;
		this.modelo = modelo;
		this.chassi = chassi;
		this.ano = ano;
		this.quilometragem = quilometragem;
		this.descricao = descricao;
		this.valor = valor;
		this.fotos = fotos;
	}
	
	public Carro(Long id, Loja loja, String placa, String modelo, String chassi, Integer ano,
			Integer quilometragem, String descricao, Float valor, String fotos) {
		this(loja,  placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public Loja getloja() {
		return loja;
	}

	public void setloja(Loja loja) {
		this.loja = loja;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public String getFotos() {
		return fotos;
	}

	public void setFotos(String fotos) {
		this.fotos = fotos;
	}
}