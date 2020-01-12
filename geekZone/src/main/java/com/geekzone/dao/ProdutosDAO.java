package com.geekzone.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.geekzone.entity.Categoria;
import com.geekzone.entity.Produto;

public class ProdutosDAO {
	public ProdutosDAO() {
	}

	public ArrayList<Produto> todosProdutos() throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		ArrayList<Produto> arrayList = new ArrayList<Produto>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("SELECT * FROM produto");
			result = pstmt.executeQuery();

			while (result.next()) {
				Produto produto = new Produto();
				 produto.setIdProduto(result.getString("idProduto")); 
				 produto.setIdCategoria(result.getString("idCategoria")); 
				 produto.setIdTipoProduto(result.getString("idTipoProduto")); 
				 produto.setNomeProduto(result.getString("nomeProduto")); 
				 produto.setQtd(result.getString("qtd")); 
				 produto.setPreco(result.getString("preco")); 
				 produto.setDescricao(result.getString("descricao")); 
				 produto.setLinkImg(result.getString("linkImg")); 
				 arrayList.add(produto);
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
