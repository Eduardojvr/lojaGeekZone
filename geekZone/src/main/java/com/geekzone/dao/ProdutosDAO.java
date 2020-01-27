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
			pstmt = db.prepareStatement("SELECT * FROM produto order by idProduto desc;");
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
				 produto.setLinkImg1(result.getString("linkImg1")); 
				 produto.setLinkImg2(result.getString("linkImg2")); 
				 produto.setLinkImg3(result.getString("linkImg3")); 
				 produto.setPeso(result.getString("peso")); 
				 produto.setAltura(result.getString("altura")); 
				 produto.setLargura(result.getString("largura")); 
				 produto.setComprimento(result.getString("comprimento")); 
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
