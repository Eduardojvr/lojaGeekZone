package com.geekzone.entity;

import java.util.ArrayList;

public class Venda {
	private String idVenda; 
	private String cpfComprador;
	private String cepComprador;
	private String emailComprador;
	private String cidade;
	private String bairro;
	private String estado;
	private String numero;
	private String complemento;
	private String telefone;
	private String valorFrete;
	private ArrayList <Item> itens;
	
	public String getIdVenda() {
		return idVenda;
	}
	public void setIdVenda(String idVenda) {
		this.idVenda = idVenda;
	}
	public String getCpfComprador() {
		return cpfComprador;
	}
	public void setCpfComprador(String cpfComprador) {
		this.cpfComprador = cpfComprador;
	}
	public String getCepComprador() {
		return cepComprador;
	}
	public void setCepComprador(String cepComprador) {
		this.cepComprador = cepComprador;
	}
	public String getEmailComprador() {
		return emailComprador;
	}
	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(String valorFrete) {
		this.valorFrete = valorFrete;
	}
	public ArrayList<Item> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Item> itens) {
		this.itens = itens;
	}
	
	
	

}
