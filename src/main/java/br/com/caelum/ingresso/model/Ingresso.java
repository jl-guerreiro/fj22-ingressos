package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.desconto.Desconto;

public class Ingresso {

	private Sessao sessao;
	private BigDecimal preco;
	
	public Ingresso(Sessao sessao, Desconto tipoDesconto){
		this.sessao = sessao;
		this.preco = tipoDesconto.aplicarDescontoSobre(sessao.getPreco());
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
