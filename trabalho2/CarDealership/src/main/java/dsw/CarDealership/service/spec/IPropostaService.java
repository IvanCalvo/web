package dsw.CarDealership.service.spec;

import java.util.List;

import dsw.CarDealership.domain.Proposta;

public interface IPropostaService {
	Proposta buscarPorId(Long id);
	
	List<Proposta> buscarTodos();
	
	void salvar(Proposta proposta);
	
	void excluir(Long id);
}
