package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.desconto.DescontoDeTrintaPorCentoParaBancos;
import br.com.caelum.ingresso.model.desconto.DescontoEstudante;
import br.com.caelum.ingresso.model.desconto.SemDesconto;


public class DescontoTest {
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressosDeClientesDeBancos(){
		Sala sa = new Sala("", new BigDecimal("20.5"));
		Filme f = new Filme("", Duration.ofMinutes(120),"", new BigDecimal("12"));
		Sessao se = new Sessao(sa,f,LocalTime.parse("10:00:00"));
		Ingresso i = new Ingresso(se, new DescontoDeTrintaPorCentoParaBancos());
		BigDecimal esperado = new BigDecimal("22.75");
		Assert.assertEquals(esperado, i.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaEstudante(){
		Sala sa = new Sala("", new BigDecimal("20.5"));
		Filme f = new Filme("", Duration.ofMinutes(120),"", new BigDecimal("12"));
		Sessao se = new Sessao(sa,f,LocalTime.parse("10:00:00"));
		Ingresso i = new Ingresso(se, new DescontoEstudante());
		BigDecimal esperado = new BigDecimal("16.25");
		Assert.assertEquals(esperado, i.getPreco());
	}
	
	@Test
	public void naoDeveConcederDesconto(){
		Sala sa = new Sala("", new BigDecimal("20.5"));
		Filme f = new Filme("", Duration.ofMinutes(120),"", new BigDecimal("12"));
		Sessao se = new Sessao(sa,f,LocalTime.parse("10:00:00"));
		Ingresso i = new Ingresso(se, new SemDesconto());
		BigDecimal esperado = new BigDecimal("32.5");
		Assert.assertEquals(esperado, i.getPreco());
	}

}
