package com.geekzone.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.geekzone.entity.Usuario;

public class UsuarioDAO {
	
	public UsuarioDAO(){
		
	}
	
	public void cadastra(Usuario usuario) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		PreparedStatement pstmt = null;

		StringBuilder sql = new StringBuilder();

		String senha = usuario.getSenha();
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0,senha.length());
		String criptografia = new BigInteger(1,m.digest()).toString(16);

		sql.append("INSERT INTO cliente ");
		sql.append(" ( ");
		sql.append(" nomeCompleto, ");
		sql.append(" cpf, ");
		sql.append(" nascimento, ");		
		sql.append(" sexo, ");
		sql.append(" cep, ");
		sql.append(" cidade, ");
		sql.append(" bairro, ");
		sql.append(" estado, ");
		sql.append(" numero, ");
		sql.append(" complemento, ");
		sql.append(" telefone, ");
		sql.append(" celular, ");
		sql.append(" email, ");
		sql.append(" senha ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		
		try {
			pstmt = db.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, usuario.getNomeCompleto());
			pstmt.setString(2, usuario.getCpf());
			pstmt.setString(3, usuario.getNascimento());
			pstmt.setString(4, usuario.getSexo());
			pstmt.setString(5, usuario.getCep());
			pstmt.setString(6, usuario.getCidade());
			pstmt.setString(7, usuario.getBairro());
			pstmt.setString(8, usuario.getEstado());
			pstmt.setString(9, usuario.getNumero());
			pstmt.setString(10, usuario.getComplemento());
			pstmt.setString(11, usuario.getTelefone());
			pstmt.setString(12, usuario.getCelular());
			pstmt.setString(13, usuario.getEmail());
			pstmt.setString(14, criptografia);

			pstmt.executeUpdate();

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
	
	}
	

}
