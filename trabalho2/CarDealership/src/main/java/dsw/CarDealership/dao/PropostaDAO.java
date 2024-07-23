package dsw.CarDealership.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dsw.CarDealership.domain.Proposta;

@SuppressWarnings("unchecked")
public interface PropostaDAO extends CrudRepository<Proposta, Long>{
	Proposta findById(long id);

	List<Proposta> findAll();
	
	Proposta save(Proposta proposta);

	void deleteById(Long id);

}
