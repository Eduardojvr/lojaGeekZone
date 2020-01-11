package com.geekzone.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.geekzone.dao.UsuarioDAO;
import com.geekzone.entity.Usuario;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;

@Path("/usuario")
public class RESTUsuario {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@POST
	@Path("/cadastro")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response cadastro(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.cadastra(usuario);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().build();
		
	}

}


