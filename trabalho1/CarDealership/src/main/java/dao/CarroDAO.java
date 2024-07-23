package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import domain.Carro;
import domain.Loja;


public class CarroDAO extends GenericDAO{
	
	public void insert(Carro carro) {
		String sql = "INSERT INTO carro (cnpj_loja, id_loja, placa, modelo, chassi, ano, quilometragem,"
				+ "descricao, valor, fotos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, carro.getloja().getCnpj());
            statement.setLong(2, carro.getloja().getId_usuario());
            statement.setString(3, carro.getPlaca());
            statement.setString(4, carro.getModelo());
            statement.setString(5, carro.getChassi());
            statement.setInt(6, carro.getAno());
            statement.setInt(7, carro.getQuilometragem());
            statement.setString(8, carro.getDescricao());
            statement.setFloat(9, carro.getValor());
            statement.setString(10, carro.getFotos());
            statement.executeUpdate();
            statement.close();
            conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Carro> getAll(){
		
		List<Carro> listaCarros = new ArrayList<>();
		
		String sql = "SELECT c.ID, cnpj_loja, placa, modelo, chassi, ano, quilometragem, c.descricao, c.valor, fotos, id_loja, nome, l.descricao from carro c Left Join lojas l on c.id_loja = l.id LEFT JOIN Proposta p ON c.id = p.carro_id WHERE p.statusCompra IS NULL OR p.statusCompra!= \"ACEITO\" GROUP BY c.ID ORDER BY c.id";
					
		try {
			Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
            	Long id = resultSet.getLong("id");
            	String cnpj_loja = resultSet.getString("cnpj_loja");
            	String placa = resultSet.getString("placa");
            	String modelo = resultSet.getString("modelo");
            	String chassi = resultSet.getString("chassi");
            	int ano = resultSet.getInt("ano");
            	int quilometragem = resultSet.getInt("quilometragem");
            	String descricao = resultSet.getString("descricao");
            	float valor = resultSet.getFloat("valor");
            	String fotos = resultSet.getString("fotos");
            	
            	Long id_loja = resultSet.getLong("id_loja");
            	String nome = resultSet.getString("nome");
            	String descricao_loja = resultSet.getString("l.descricao");
            	
            	Loja loja = new Loja(id_loja, nome, descricao_loja, cnpj_loja);
            	Carro carro = new Carro(id, loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
            	listaCarros.add(carro);
            }
            resultSet.close();
            statement.close();
            conn.close();
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }
		return listaCarros;
	}
	
	public List<Carro> getAll(Long identificador){
			
			List<Carro> listaCarros = new ArrayList<>();
			
			String sql = "SELECT * from carro c, lojas l where c.id_loja = l.id order by c.id_loja";
						
			try {
				Connection conn = this.getConnection();
	            Statement statement = conn.createStatement();
	            
	            ResultSet resultSet = statement.executeQuery(sql);
	            while (resultSet.next()){
	            	Long id = resultSet.getLong("id");
	            	String cnpj_loja = resultSet.getString("cnpj_loja");
	            	String placa = resultSet.getString("placa");
	            	String modelo = resultSet.getString("modelo");
	            	String chassi = resultSet.getString("chassi");
	            	int ano = resultSet.getInt("ano");
	            	int quilometragem = resultSet.getInt("quilometragem");
	            	String descricao = resultSet.getString("descricao");
	            	float valor = resultSet.getFloat("valor");
	            	String fotos = resultSet.getString("fotos");
	            	
	            	Long id_loja = resultSet.getLong("id_loja");
	            	String nome = resultSet.getString("nome");
	            	String descricao_loja = resultSet.getString("l.descricao");
	            	
	            	Loja loja = new Loja(id_loja, nome, descricao_loja, cnpj_loja);
	            	Carro carro = new Carro(id, loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
	            	if( identificador == id_loja) {
		            	listaCarros.add(carro);
	            	}
	            }
	            resultSet.close();
	            statement.close();
	            conn.close();
			} catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
			return listaCarros;
		}
	
	public void delete(Carro carro) {
        String sql = "DELETE FROM carro where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, carro.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void update(Carro carro) {
        String sql = "UPDATE carro SET cnpj_loja = ?, id_loja = ?, placa = ?, modelo = ?, chassi = ?, ano = ?";
        sql += ", quilometragem = ?, descricao = ?, valor = ?, fotos = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, carro.getloja().getCnpj());
            statement.setLong(2, carro.getloja().getId_usuario());
            statement.setString(3, carro.getPlaca());
            statement.setString(4, carro.getModelo());
            statement.setString(5, carro.getChassi());
            statement.setInt(6, carro.getAno());
            statement.setInt(7, carro.getQuilometragem());
            statement.setString(8, carro.getDescricao());
            statement.setFloat(9, carro.getValor());
            statement.setString(10, carro.getFotos());
            statement.setLong(11, carro.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Carro get(Long id) {
        Carro carro = null;

        String sql = "SELECT * from carro c, lojas l where c.id = ? and c.id_loja = l.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	
            	String placa = resultSet.getString("placa");
            	String modelo = resultSet.getString("modelo");
            	String chassi = resultSet.getString("chassi");
            	int ano = resultSet.getInt("ano");
            	int quilometragem = resultSet.getInt("quilometragem");
            	String descricao = resultSet.getString("descricao");
            	float valor = resultSet.getFloat("valor");
            	String fotos = resultSet.getString("fotos");
            	
            	Long id_loja = resultSet.getLong("id_loja");
            	Loja loja = new LojaDAO().get(id_loja);
           
            	
            	carro = new Carro(id, loja, placa, modelo, chassi, ano, quilometragem, descricao, valor, fotos);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return carro;
    }
}
