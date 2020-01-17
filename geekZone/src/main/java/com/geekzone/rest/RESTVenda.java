package com.geekzone.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.geekzone.dao.VendaDAO;
import com.geekzone.entity.Venda;
import com.geekzone.entity.Item;


import javax.ws.rs.Produces;

@Path("/venda")
public class RESTVenda {
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	@POST
	@Path("/registraVenda")
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	public Response registraVenda(Venda venda) {
//		System.out.println("Bairro: "+venda.getBairro());
//		System.out.println("Itens: "+venda.getItens().get(0).getIdProduto());
		System.out.println("Frete: "+venda.getValorFrete());

		
		VendaDAO dao = new VendaDAO();
		try {
			dao.registraVenda(venda, venda.getItens());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity("Venda registrada!").build();
	}
}


