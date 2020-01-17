package com.geekzone.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.geekzone.entity.Item;
import com.geekzone.entity.Usuario;
import com.geekzone.entity.Venda;
import com.geekzone.dao.ConnectionManager;

public class VendaDAO {
	public VendaDAO() {

	}

	public void registraVenda(Venda venda, ArrayList <Item> todosItens) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO venda ");
		sql.append(" ( ");
		sql.append(" cpfComprador, ");
		sql.append(" cepComprador, ");		
		sql.append(" emailComprador, ");
		sql.append(" cidade, ");
		sql.append(" bairro, ");
		sql.append(" estado, ");
		sql.append(" numero, ");
		sql.append(" complemento, ");
		sql.append(" telefone, ");
		sql.append(" valorFrete ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?);");
		
		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, venda.getCpfComprador());
			pstmt.setString(2, venda.getCepComprador());
			pstmt.setString(3, venda.getEmailComprador());
			pstmt.setString(4, venda.getCidade());
			pstmt.setString(5, venda.getBairro());
			pstmt.setString(6, venda.getEstado());
			pstmt.setString(7, venda.getNumero());
			pstmt.setString(8, venda.getComplemento());
			pstmt.setString(9, venda.getTelefone());
			pstmt.setString(10, venda.getValorFrete());
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();  
			rs.next();
			int id = rs.getInt(1);
			rs.close();
 
			for(Item item: todosItens) {
				registraVenda(Integer.toString(id), item);
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

	}
	
	public void registraVenda(String id, Item item) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO item ");
		sql.append(" ( ");
		sql.append(" idVenda, ");
		sql.append(" idProduto, ");		
		sql.append(" qtd, ");
		sql.append(" precoUnidade ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?);"); 
	
		try {
				pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, id);
				pstmt.setString(2, item.getIdProduto());
				pstmt.setString(3, item.getQtd());
				pstmt.setString(4, item.getPrecoUnidade());
				pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

	}


}
