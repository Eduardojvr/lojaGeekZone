package com.geekzone.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.geekzone.dao.VendaDAO;
import com.geekzone.dto.VendaDTO;
import com.geekzone.entity.Venda;
import com.geekzone.entity.ItemVenda;
import com.geekzone.entity.Usuario;

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
		VendaDAO dao = new VendaDAO();
		try {
			dao.registraVenda(venda, venda.getItens());
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity("Venda registrada!").build();
	}
	
	@POST
	@Path("/compras")
	@Produces({ MediaType.APPLICATION_JSON})
	public Response compras(Usuario user) {
		VendaDAO dao = new VendaDAO();
		ArrayList<VendaDTO> venda = null;
		try {
			venda = dao.minhasCompras(user);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(venda).build();
	}
}


