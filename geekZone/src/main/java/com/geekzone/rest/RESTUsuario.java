package com.geekzone.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
			return Response.serverError().entity("Erro ao cadastrar!").build();	
		}
		return Response.ok().entity("Usuário cadastrado com sucesso!").build();	
	}

	@POST
	@Path("/login")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response login(Usuario usuario) {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			Usuario login = dao.login(usuario);

			if (login != null) {
				request.getSession().setAttribute("logado", true);
				request.getSession().setAttribute("nomeCompleto", login.getNomeCompleto());
				request.getSession().setAttribute("cpf", login.getCpf());
				request.getSession().setAttribute("nascimento", login.getNascimento());
				request.getSession().setAttribute("sexo", login.getSexo());
				request.getSession().setAttribute("cep", login.getCep());
				request.getSession().setAttribute("cidade", login.getBairro());
				request.getSession().setAttribute("bairro", login.getBairro());
				request.getSession().setAttribute("estado", login.getEstado());
				request.getSession().setAttribute("numero", login.getNumero());
				request.getSession().setAttribute("complemento", login.getComplemento());
				request.getSession().setAttribute("telefone", login.getTelefone());
				request.getSession().setAttribute("celular", login.getCelular());
				request.getSession().setAttribute("email", login.getEmail());
			} else {
				return Response.serverError().entity("Erro ao logar!").build();	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.ok().entity("Usuário logado com sucesso!").build();	
	}
	
	@POST
	@Path("/logout")
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout() {
		HttpSession session = request.getSession();
		String firstRedirectUrl = request.getContextPath()+"/index.html";
		try {
			if (session != null) {
				request.getSession().setAttribute("logado", false);
				session.invalidate();
				response.sendRedirect(firstRedirectUrl);
				return Response.ok().status(Status.OK).build();
			} else {
				return Response.status(Status.FORBIDDEN).build();
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.FORBIDDEN).entity("Erro ao realizar logout!").build();
		}

	}
	
	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public  Usuario getUsuario() {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = null;
		try {
			if(request.getSession().getAttribute("email") != null) {
				usuario = dao.getUsuario(request.getSession().getAttribute("email").toString());				
			}
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}


