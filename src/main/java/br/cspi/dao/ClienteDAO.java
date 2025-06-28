package br.cspi.dao;


import br.cspi.model.Clientes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    public String inserir(Clientes cliente) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO cliente (nome, cpf, telefone, endereco, usuario_id) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getCliente_usuario_id());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir cliente");
        }

        return "Cliente inserido com sucesso";
    }

    public String alterar(Clientes cliente) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
            "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, endereco = ? WHERE id = ?"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar cliente");
        }

        return "Cliente alterado com sucesso";
    }

    public String excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM cliente WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return "Nenhum cliente excluído";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir cliente");
        }

        return "Cliente excluído com sucesso";
    }

    public ArrayList<Clientes> listar() {
        ArrayList<Clientes> clientes = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoDados();
            PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM cliente"
            );

            stmt.execute();

            while (stmt.getResultSet().next()) {
                Clientes cliente = new Clientes();
                cliente.setId(stmt.getResultSet().getInt("id"));
                cliente.setNome(stmt.getResultSet().getString("nome"));
                cliente.setCpf(stmt.getResultSet().getString("cpf"));
                cliente.setTelefone(stmt.getResultSet().getString("telefone"));
                cliente.setEndereco(stmt.getResultSet().getString("endereco"));
                cliente.setCliente_usuario_id(stmt.getResultSet().getInt("usuario_id"));

                clientes.add(cliente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao listar clientes");
        }

        return clientes;
    }
}
