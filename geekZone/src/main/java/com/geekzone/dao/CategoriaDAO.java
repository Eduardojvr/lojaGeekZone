package com.geekzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.geekzone.entity.Categoria;



public class CategoriaDAO {
	
	public CategoriaDAO() {
		
	}
	
	public ArrayList<Categoria> todasCategorias() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		ArrayList<Categoria> arrayList = new ArrayList<Categoria>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM categoria");
			result = pstmt.executeQuery();

			while (result.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(result.getString("id"));
				categoria.setNomeCategoria(result.getString("nomeCategoria"));
				arrayList.add(categoria);
			}

		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}
		return arrayList;
	}
	

}
