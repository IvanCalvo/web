package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import domain.Cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (id, cpf, telefone, nome, sexo, dataDeNascimento) VALUES ( ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId_usuario());
            statement.setString(2, cliente.getCpf());
            statement.setString(3, cliente.getTelefone());
            statement.setString(4, cliente.getNome());
            statement.setString(5, cliente.getSexo());
            statement.setString(6, cliente.getDataDeNascimento().toString());
            
            System.out.println(cliente.getDataDeNascimento());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    
    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId_usuario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET cpf = ?, telefone = ?, nome = ?, sexo = ?, dataDeNascimento = ?";
        sql += "WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getNome());
            statement.setString(4, cliente.getSexo());
            statement.setString(5, cliente.getDataDeNascimento().toString());
            statement.setLong(6, cliente.getId_usuario());
            statement.executeUpdate();
            
            System.out.println(cliente.getDataDeNascimento());
            System.out.println(cliente.getDataDeNascimento().toString());

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String telefone = resultSet.getString("telefone");
                String nome = resultSet.getString("nome");
                String sexo = resultSet.getString("sexo");
                LocalDate dataDeNascimento = LocalDate.parse(resultSet.getString("dataDeNascimento"));

                cliente = new Cliente(id, cpf, telefone, nome, sexo, dataDeNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
