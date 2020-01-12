package com.geekzone.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import javax.ws.rs.core.Response.*;

import org.json.*;

import com.geekzone.entity.Frete;

@Path("/frete")
public class RESTFrete {

	
	@POST
	@Path("/calculaFrete")
	@Produces({ MediaType.APPLICATION_JSON})
	@Consumes({ MediaType.APPLICATION_JSON})
	public String calculaFrete(Frete frete) {
		OkHttpClient client = new OkHttpClient();
		String dados = "";
		JSONObject json = null;
		Request request = new Request.Builder()
				.url("http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?nCdEmpresa&sDsSenha"+
						"&sCepOrigem="+frete.getsCepOrigem()+
						"&sCepDestino="+frete.getsCepDestino()+
						"&nVlPeso="+frete.getnVlPeso()+
						"&nCdFormato="+frete.getnCdFormato()+
						"&nVlComprimento="+frete.getnVlComprimento()+
						"&nVlAltura="+frete.getnVlAltura()+
						"&nVlLargura="+frete.getnVlLargura()+
						"&sCdMaoPropria="+frete.getsCdMaoPropria()+
						"&nVlValorDeclarado&sCdAvisoRecebimento="+frete.getsCdAvisoRecebimento()+
						"&nCdServico="+frete.getnCdServico()+
						"&nVlDiametro="+frete.getnVlDiametro()+
						"&StrRetorno=xml").build();

		try (Response r = client.newCall(request).execute()) {
			dados = r.body().string();
			System.out.println(dados);
	        json = XML.toJSONObject(dados);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json.get("Servicos").toString();

	}
}


