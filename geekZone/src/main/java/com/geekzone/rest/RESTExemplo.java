package com.geekzone.rest;

import java.util.ArrayList;
import com.mercadopago.*;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.MercadoPago;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/exemplo")
public class RESTExemplo {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/getExemplo")
	@Produces({ MediaType.APPLICATION_JSON })
	public String get() {

		// Configura credenciais
		try {
			MercadoPago.SDK.setAccessToken("TEST-137829318645213-010816-6ea499d1be4d48f3a621fb90ac034fa2-147152781");
		} catch (MPConfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Cria um objeto de preferência
		Preference preference = new Preference();
		Payer payer = new Payer();
		payer.setEmail("eduardojvr10@gmail.com");
		// Cria um item na preferência
		Item item = new Item();
		item.setTitle("Meu produto")
		    .setQuantity(1)
		    .setUnitPrice((float) 100.56);
		preference.appendItem(item);
		preference.setPayer(payer);
		try {
			preference.save();
//			 System.out.println();
			 
		} catch (MPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preference.getId();
	
	}

}
