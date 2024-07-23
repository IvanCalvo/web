package dsw.CarDealership;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dsw.CarDealership.dao.CarroDAO;
import dsw.CarDealership.dao.ClienteDAO;
import dsw.CarDealership.dao.LojaDAO;
import dsw.CarDealership.dao.PropostaDAO;
import dsw.CarDealership.dao.UsuarioDAO;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.domain.Cliente;
import dsw.CarDealership.domain.Loja;
import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.domain.Usuario;

@SpringBootApplication
public class CarDealershipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarDealershipApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CarroDAO carroDAO, LojaDAO lojaDAO, PropostaDAO propostaDAO, ClienteDAO clienteDAO, UsuarioDAO usuarioDAO, BCryptPasswordEncoder encoder) {
		return (args) -> {
			
			//Usuarios
			
			Usuario u2 = new Usuario();
			u2.setEmail("admin");
			u2.setPapel("ADMIN");
			u2.setSenha(encoder.encode("admin"));
			usuarioDAO.save(u2);
			
			//Lojas
			Loja l1 = new Loja();
			l1.setEmail("loja1");
			l1.setPapel("LOJA");
			l1.setSenha(encoder.encode("loja1"));
			l1.setNome("loja1");
			l1.setDescricao("Nada a dizer");
			l1.setCnpj("1234589");
			lojaDAO.save(l1);
			
			Loja l2 = new Loja();
			l2.setEmail("loja2");
			l2.setPapel("LOJA");
			l2.setSenha(encoder.encode("loja2"));
			l2.setNome("loja2");
			l2.setDescricao("Nada a dizer");
			l2.setCnpj("1234");
			lojaDAO.save(l2);
			
			Loja l3 = new Loja();
			l3.setEmail("loja3");
			l3.setPapel("LOJA");
			l3.setSenha(encoder.encode("loja3"));
			l3.setNome("loja3");
			l3.setDescricao("Nada a dizer");
			l3.setCnpj("1234588");
			lojaDAO.save(l3);
			
			Loja l4 = new Loja();
			l4.setEmail("loja4");
			l4.setPapel("LOJA");
			l4.setSenha(encoder.encode("loja4"));
			l4.setNome("loja4");
			l4.setDescricao("Nada a dizer");
			l4.setCnpj("1234588");
			lojaDAO.save(l3);
			
			//Carros
			Carro c1 = new Carro();
			c1.setLoja(l1);
			c1.setPlaca("1234asda");
			c1.setModelo("Modelo 1");
			c1.setChassi("asdasd");
			c1.setAno(2000);
			c1.setQuilometragem(200);
			c1.setDescricao("algo a dizer?");
			c1.setValor(BigDecimal.valueOf(200000));
			c1.setFotos("caminho");
			carroDAO.save(c1);
			
			Carro c2 = new Carro();
			c2.setLoja(l2);
			c2.setPlaca("1234asda");
			c2.setModelo("Modelo 2");
			c2.setChassi("asdasd");
			c2.setAno(2000);
			c2.setQuilometragem(200);
			c2.setDescricao("algo a dizer?");
			c2.setValor(BigDecimal.valueOf(200000));
			c2.setFotos("caminho");
			carroDAO.save(c2);
			
			Carro c3 = new Carro();
			c3.setLoja(l3);
			c3.setPlaca("1234asda");
			c3.setModelo("Modelo 3");
			c3.setChassi("asdddddddd");
			c3.setAno(2355);
			c3.setQuilometragem(200);
			c3.setDescricao("algo a dizer?");
			c3.setValor(BigDecimal.valueOf(20));
			c3.setFotos("caminho");
			carroDAO.save(c3);
			
			//Clientes
			Cliente cl1 = new Cliente();
			cl1.setEmail("cliente");
			cl1.setPapel("CLIENTE");
			cl1.setSenha(encoder.encode("cliente"));
			cl1.setCpf("124564564");
			cl1.setDataDeNascimento(LocalDate.parse("2000-02-02"));
			cl1.setNome("Roberval");
			cl1.setSexo("M");
			cl1.setTelefone("123-5888");
			clienteDAO.save(cl1);
			
			Cliente cl2 = new Cliente();
			cl2.setEmail("cliente2");
			cl2.setPapel("CLIENTE");
			cl2.setSenha(encoder.encode("cliente2"));
			cl2.setCpf("1245645");
			cl2.setDataDeNascimento(LocalDate.parse("2000-02-02"));
			cl2.setNome("Robert");
			cl2.setSexo("M");
			cl2.setTelefone("123-5998");
			clienteDAO.save(cl2);
			
			Cliente cl3 = new Cliente();
			cl2.setEmail("cliente3");
			cl2.setPapel("CLIENTE");
			cl2.setSenha(encoder.encode("cliente3"));
			cl2.setCpf("1245");
			cl2.setDataDeNascimento(LocalDate.parse("2000-02-02"));
			cl2.setNome("William");
			cl2.setSexo("M");
			cl2.setTelefone("123-5778");
			clienteDAO.save(cl2);
			
			//Propostas
			Proposta p1 = new Proposta();
			p1.setCarro(c1);
			p1.setCliente(cl1);
			p1.setCondPagamento("Dinheiro");
			p1.setdataProposta(LocalDate.parse("2000-02-02"));
			p1.setStatus("ACEITO");
			p1.setValor(BigDecimal.valueOf(2000));
			propostaDAO.save(p1);
			
			Proposta p2 = new Proposta();
			p2.setCarro(c2);
			p2.setCliente(cl1);
			p2.setCondPagamento("Dinheiro");
			p2.setdataProposta(LocalDate.parse("2000-02-02"));
			p2.setStatus("ACEITO");
			p2.setValor(BigDecimal.valueOf(2000));
			propostaDAO.save(p2);
	};
	
	}
	
}
