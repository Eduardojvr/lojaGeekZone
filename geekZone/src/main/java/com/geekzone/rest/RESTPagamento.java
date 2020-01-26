package com.geekzone.rest;

import java.util.ArrayList;

import com.geekzone.dao.VendaDAO;
import com.geekzone.dto.VendaDTO;
import com.geekzone.entity.ItemVenda;
import com.geekzone.entity.Venda;
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

	@POST
	@Path("/paga")
	@Produces({ MediaType.APPLICATION_JSON })
	public String pagaItem(Venda venda) {
		
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
		payer.setName(venda.getNomeComprador())
		     .setEmail(venda.getEmailComprador())
		     .setIdentification(new Identification()
		        .setType("CPF")
		        .setNumber(venda.getCpfComprador()))
		     .setAddress(new Address()
		        .setStreetName("Num: "+venda.getNumero()+"Compl: "+venda.getComplemento())
		        .setZipCode(venda.getCepComprador()));
		
		
		//Define item da venda
		for(ItemVenda i : venda.getItens()) {
			Item item = new Item();
			item.setTitle(i.getNomeProduto()+" Cod: "+i.getIdProduto())
			.setQuantity(Integer.parseInt(i.getQtd()))
			.setUnitPrice(Float.parseFloat(i.getPrecoUnidade()));
			preference.appendItem(item);
		}		
		
		Item frete = new Item();
		frete.setTitle("Frete");
		frete.setQuantity(1);
		frete.setUnitPrice(Float.parseFloat(venda.getValorFrete()));
		preference.appendItem(frete);
		
		preference.setPayer(payer);
		preference.setExternalReference(venda.getIdVenda());

		try {
			preference.save();
		} catch (MPException e) {
			e.printStackTrace();
		}
		
		VendaDAO registraVenda = new VendaDAO();
		try {
			registraVenda.registraVenda(venda, venda.getItens());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return preference.getId();
	
	}

}