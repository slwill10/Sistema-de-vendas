/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

}
