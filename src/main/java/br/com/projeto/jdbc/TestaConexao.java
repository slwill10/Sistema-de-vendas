/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author Willian
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        try {
            new ConnectionFactory().getConnection();
            JOptionPane.showConfirmDialog(null, "Conectado com sucesso!!!");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "OPS aconteceu um erro:"  + e);
        }
    }
    
}