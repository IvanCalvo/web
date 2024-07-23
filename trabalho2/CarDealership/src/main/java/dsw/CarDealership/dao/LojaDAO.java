package dsw.CarDealership.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import dsw.CarDealership.domain.Loja;

@SuppressWarnings("unchecked")
public interface LojaDAO extends CrudRepository<Loja, Long>{
	
	Loja findById(long id);
	
	List<Loja> findAll();
	
	Loja save(Loja l);
	
	void deleteById(Long id);
	
	@Query("SELECT l FROM Loja l WHERE l.cnpj = :cnpj")
	Loja findByCNPJ(@Param ("cnpj") String cnpj);
	
	@Query("SELECT l FROM Loja l WHERE l.email = :email")
	Loja findByemail(@Param("email") String email);
	
}

