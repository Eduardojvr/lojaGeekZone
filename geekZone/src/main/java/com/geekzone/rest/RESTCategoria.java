package com.geekzone.rest;

import java.util.ArrayList;

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
import javax.ws.rs.core.Response.ResponseBuilder;

import com.geekzone.dao.CategoriaDAO;
import com.geekzone.dao.UsuarioDAO;
import com.geekzone.entity.Usuario;
import com.geekzone.entity.Categoria;


@Path("/categoria")
public class RESTCategoria {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/todasCategorias")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response cadastro(Usuario usuario) {
		CategoriaDAO dao = new CategoriaDAO();
		ArrayList <Categoria> array = null;
		try {
			array = dao.todasCategorias();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(array).build();
		
	}

}
