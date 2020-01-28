package com.geekzone.dao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.geekzone.entity.Categoria;
import com.geekzone.entity.ItemVenda;
import com.geekzone.entity.Usuario;
import com.geekzone.entity.Venda;
import com.geekzone.dao.ConnectionManager;
import com.geekzone.dto.VendaDTO;

public class VendaDAO {
	public VendaDAO() {

	}

	public void registraVenda(Venda venda, ArrayList <ItemVenda> todosItens) throws Exception {
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
		sql.append(" valorFrete, ");
		sql.append(" status, ");
		sql.append(" codRastreio ");
		sql.append(" ) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
		
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
			pstmt.setString(11, "Não enviado");
			pstmt.setString(12, null);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();  
			rs.next();
			int id = rs.getInt(1);
			rs.close();
 
			for(ItemVenda item: todosItens) {
				registraVenda(Integer.toString(id), item);
			}

		} finally {
			if (pstmt != null)
				pstmt.close();
			db.close();
		}

	}
	
	public void registraVenda(String id, ItemVenda item) throws Exception {
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
	
	public ArrayList<VendaDTO> minhasCompras(Usuario user) throws Exception {
		Connection db = ConnectionManager.getDBConnection();
		ArrayList<VendaDTO> arrayList = new ArrayList<VendaDTO>();

		PreparedStatement pstmt = null;
		ResultSet result = null;

		try {
			pstmt = db.prepareStatement("select v.idVenda, v.cpfComprador, v.cepComprador, v.emailComprador, v.valorFrete, v.status, v.codRastreio,\n" + 
					"i.idProduto, i.qtd, i.precoUnidade, p.nomeProduto, p.linkImg1\n" + 
					" from venda v\n" + 
					"	inner join item i\n" + 
					"		on i.idVenda=v.idVenda\n" + 
					"	inner join produto p\n" + 
					"		on p.idProduto=i.idProduto\n" + 
					"where v.cpfComprador='"+user.getCpf()+"';");
			result = pstmt.executeQuery();

			while (result.next()) {
				VendaDTO venda = new VendaDTO();
				venda.setCpfComprador(result.getString("cpfComprador"));				
				venda.setEmailComprador(result.getString("emailComprador"));
				venda.setValorUnidade(result.getString("precoUnidade"));
				venda.setQtdComprada(result.getString("qtd"));
				venda.setIdProduto(result.getString("idProduto"));
				venda.setNomeProduto(result.getString("nomeProduto"));
				venda.setCepComprador(result.getString("cepComprador"));
				venda.setIdVenda(result.getString("idVenda"));
				venda.setValorFrete(result.getString("valorFrete"));
				venda.setStatus(result.getString("status"));
				venda.setCodRastreio(result.getString("codRastreio"));
				venda.setLinkImg1(result.getString("linkImg1"));
				arrayList.add(venda);
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
