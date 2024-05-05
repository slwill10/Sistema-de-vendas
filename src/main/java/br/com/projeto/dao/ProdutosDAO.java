/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class ProdutosDAO {

    Connection con;

    public ProdutosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    // Método cadastras produtos
    public void cadastrar(Produtos obj) {
        try {

            String sql = "Insert into tb_produtos(descricao, preco, qtd_estoque, for_id)values(?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso! ");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    // Método listar todos os produtos
    public List<Produtos> listarProdutos() {
        List<Produtos> lista = new ArrayList<>();
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p\n"
                    + "inner join tb_fornecedores as f on (p.for_id = f.id);";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));

                f.setNome(rs.getString("nome"));

                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return lista;
    }

    // Método alterar
    public void alterarProduto(Produtos obj) {
        try {

            String sql = "update tb_produtos set descricao=?, preco=?, qtd_estoque=?, for_id=? where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
    }

    // Método excluir produto
    public void excluirProduto(Produtos obj) {
        try {

            String sql = "delete from tb_produtos WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);

        }
    }

    // Método listar por nome
    public List<Produtos> listarprodutoPorNome(String nome) {
        try {

            List<Produtos> lista = new ArrayList<>();

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome"
                    + "                    from tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);
                lista.add(obj);
            }

            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return null;
        }
    }

    // Método consultaProduto por nome
    public Produtos consultarPorNome(String nome) {
        try {

            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome"
                    + "   from tb_produtos as p inner join tb_fornecedores as f on (p.for_id = f.id) where p.descricao = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
                f.setNome(rs.getString("f.nome"));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return null;
        }
    }

    // Método buscaProduto por Codigo
    public Produtos buscaPorCodigo(int id) {
        try {
            String sql = "select * from tb_produtos where id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setDescricao(rs.getString("descricao"));
                obj.setPreco(rs.getDouble("preco"));
                obj.setQtd_estoque(rs.getInt("qtd_estoque"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
            return null;
        }
    }

}
