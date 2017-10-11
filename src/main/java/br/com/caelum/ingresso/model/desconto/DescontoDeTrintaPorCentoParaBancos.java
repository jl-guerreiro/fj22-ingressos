package br.com.caelum.ingresso.model.desconto;

import java.math.BigDecimal;

public class DescontoDeTrintaPorCentoParaBancos implements Desconto {

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract( precoOriginal.multiply(new BigDecimal("0.3")));
	}
	
	@Override
	public String getDescricao(){
		return "Desconto Banco";
	}

}
