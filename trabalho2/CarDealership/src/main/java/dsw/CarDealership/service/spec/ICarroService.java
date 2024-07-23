package dsw.CarDealership.service.spec;

import java.util.List;

import dsw.CarDealership.domain.Carro;

public interface ICarroService {
	Carro buscarPorId(Long id);
	
	List<Carro> buscarTodos();
	
	void salvar(Carro carro);
	
	void excluir(Long id);
}
