package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.desconto.Desconto;
import br.com.caelum.ingresso.model.desconto.DescontoDeTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.SemDesconto;

public enum TipoIngresso {

	INTEIRO (new SemDesconto()),
	MEIA (new DescontoEstudante()),
	BANCO (new DescontoDeTrintaPorCentoParaBancos());
	
	private final Desconto desconto;
	
	TipoIngresso(Desconto desconto){
		this.desconto = desconto;
	}
	
	public BigDecimal aplicaDesconto(BigDecimal valor){
		return this.desconto.aplicarDescontoSobre(valor);
	}
	
	public String getDescricao(){
		return desconto.getDescricao();
	}
	
}
