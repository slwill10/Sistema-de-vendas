/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class VendasDAO {
    
     Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    // Cadastrar venda
    public void cadastrarVenda(Vendas obj){
        try {
            
            String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, observacoes) values (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());
            
            stmt.execute();
            stmt.close();
                    
            JOptionPane.showMessageDialog(null, "Venda registrado com sucesso!");
            
        } catch (Exception e) {            
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }
            
    //Retorna o id da última venda
    public int retornaUltimaVenda(){
        try {
            
            int idvenda = 0;
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
             Vendas p = new Vendas();
             
             p.setId(rs.getInt("id"));
             idvenda = p.getId();
            }
            
            return idvenda;     
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}
