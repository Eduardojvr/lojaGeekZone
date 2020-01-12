package com.geekzone.rest;

import java.util.ArrayList;
import com.mercadopago.*;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.merchantorder.Shipment;
import com.mercadopago.resources.datastructures.preference.Address;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;
import com.mercadopago.resources.datastructures.preference.Shipments;
import com.mercadopago.MercadoPago;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


@Path("/pagamento")
public class RESTPagamento {

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	protected void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@GET
	@Path("/pagaItem")
	@Produces({ MediaType.APPLICATION_JSON })
	public String pagaItem() {

		try {
			
			//Homologação
			MercadoPago.SDK.setAccessToken("TEST-137829318645213-010816-6ea499d1be4d48f3a621fb90ac034fa2-147152781");

		} catch (MPConfException e) {
			e.printStackTrace();
		}

		// Cria um objeto de preferência
		Preference preference = new Preference();
		
		//Criar pagamento
		Payer payer = new Payer();
		payer.setName("Joao")
		     .setSurname("Silva")
		     .setEmail("user@email.com")
		     .setDateCreated("2018-06-02T12:58:41.425-04:00")
		     .setIdentification(new Identification()
		        .setType("CPF")
		        .setNumber("19119119100"))
		     .setAddress(new Address()
		        .setStreetName("Street")
		        .setZipCode("06233200"));
		
		
		//Define item da venda
		Item item = new Item();
		item.setTitle("Meu produto")
		    .setQuantity(1)
		    .setUnitPrice((float) 100);
		
		preference.appendItem(item);
		preference.setPayer(payer);

		try {
			preference.save();
		} catch (MPException e) {
			e.printStackTrace();
		}
		return preference.getId();
	
	}

}