/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtosl;
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
    public void cadastrar(Produtosl obj) {
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
    public List<Produtosl> listarProdutos() {
        List<Produtosl> lista = new ArrayList<>();
        try {
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p\n"
                    + "inner join tb_fornecedores as f on (p.for_id = f.id);";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtosl obj = new Produtosl();
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

}
