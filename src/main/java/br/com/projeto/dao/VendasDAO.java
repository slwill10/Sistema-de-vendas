/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    public void cadastrarVenda(Vendas obj) {
        try {

            String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda, observacoes) values (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
        }
    }

    //Retorna o id da última venda
    public int retornaUltimaVenda() {
        try {

            int idvenda = 0;
            String sql = "select max(id) id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));
                idvenda = p.getId();
            }

            return idvenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Método que filtra vendas por datas
    public List<Vendas> listarvendasPorPeriodo(LocalDate data_inicio, LocalDate data_fim) {
        List<Vendas> lista = new ArrayList<>();
        try {
            String sql = "SELECT v.id, date_format(data_venda, '%d/%m/%Y') as data_formatada, c.nome, v.total_venda, v.observacoes FROM tb_vendas AS v "
                    + "INNER JOIN tb_clientes AS c ON (v.cliente_id = c.id) WHERE v.data_venda BETWEEN ? AND ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio.toString());
            stmt.setString(2, data_fim.toString());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();

                obj.setId(rs.getInt("id"));
                obj.setData_venda(rs.getString("data_formatada"));
                c.setNome(rs.getString("nome"));
                obj.setTotal_venda(rs.getDouble("total_venda"));
                obj.setObs(rs.getString("observacoes"));
                obj.setCliente(c);

                lista.add(obj);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return lista;
    }

}
