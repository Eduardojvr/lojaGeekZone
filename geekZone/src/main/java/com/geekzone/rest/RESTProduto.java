package com.geekzone.rest;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.geekzone.dao.CategoriaDAO;
import com.geekzone.dao.ProdutosDAO;
import com.geekzone.dto.CarouselDTO;
import com.geekzone.entity.Categoria;
import com.geekzone.entity.Produto;
import com.geekzone.entity.Usuario;

@Path("/produto")
public class RESTProduto {
	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/todosProdutos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response todosProdutos() {
		ProdutosDAO dao = new ProdutosDAO();
		ArrayList <Produto> array = null;
		try {
			array = dao.todosProdutos();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(array).build();

	}

	@GET
	@Path("/carousel")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response carousel() {
		ProdutosDAO dao = new ProdutosDAO();
		ArrayList <CarouselDTO> array = null;
		try {
			array = dao.carousel();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity(array).build();

	}
}
