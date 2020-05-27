package br.ucsal.c1b.vo;

public class Notificacao {
	
	private Integer idNotificacao;
	
	private Integer idCliente;
	
	private Integer idBarbeiro;
	
	private String mensagem;
	
	private String dataMarcada;
	
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Integer getIdBarbeiro() {
		return idBarbeiro;
	}

	public void setIdBarbeiro(Integer idBarbeiro) {
		this.idBarbeiro = idBarbeiro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDataMarcada() {
		return dataMarcada;
	}

	public void setDataMarcada(String dataMarcada) {
		this.dataMarcada = dataMarcada;
	}
	
	public Integer getIdNotificacao() {
		return idNotificacao;
	}

	public void setIdNotificacao(Integer idNotificacao) {
		this.idNotificacao = idNotificacao;
	}


}
