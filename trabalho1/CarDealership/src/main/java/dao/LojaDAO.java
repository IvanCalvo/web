package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Loja;


public class LojaDAO extends GenericDAO{
	
	public void insert(Loja loja) {

        String sql = "INSERT INTO lojas (id, nome, descricao, cnpj) VALUES (?,?,?,?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, loja.getId_usuario());
            statement.setString(2, loja.getNome());
            statement.setString(3, loja.getDescricao());
            statement.setString(4, loja.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Loja> getAll() {

        List<Loja> listaLojas = new ArrayList<>();

        String sql = "SELECT * from lojas";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cnpj = resultSet.getString("cnpj");
                
                Loja loja = new Loja(id, nome, descricao, cnpj);
                listaLojas.add(loja);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLojas;
    }
	
	public void delete(Loja loja) {
		//Só vai funcionar se loja não tiver mais nenhum carro.
        String sql = "DELETE FROM lojas where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, loja.getId_usuario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public void update(Loja loja) {
        String sql = "UPDATE lojas SET nome = ?, descricao = ?";
        sql += ", cnpj = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, loja.getNome());
            statement.setString(2, loja.getDescricao());
            statement.setString(3, loja.getCnpj());
            statement.setLong(4, loja.getId_usuario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
	
	public Loja get(Long id) {
        Loja loja = null;
        
        String sql = "SELECT * from lojas where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	String nome = resultSet.getString("nome");
                String descricao = resultSet.getString("descricao");
                String cnpj = resultSet.getString("cnpj");
                loja = new Loja(id, nome, descricao, cnpj);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loja;
    }
}
