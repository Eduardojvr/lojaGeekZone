package com.geekzone.entity;

import java.util.ArrayList;

public class Venda {
	private String nomeComprador;
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
	private String status;
	private String codRastreio;
	private ArrayList <ItemVenda> itens;
	
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
	public ArrayList<ItemVenda> getItens() {
		return itens;
	}
	public void setItens(ArrayList<ItemVenda> itens) {
		this.itens = itens;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCodRastreio() {
		return codRastreio;
	}
	public void setCodRastreio(String codRastreio) {
		this.codRastreio = codRastreio;
	}
	public String getNomeComprador() {
		return nomeComprador;
	}
	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}
	
}
