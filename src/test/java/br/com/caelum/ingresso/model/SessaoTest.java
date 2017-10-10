package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;


public class SessaoTest {

	@Test
	public void Garante(){
		Sala sa = new Sala("Eldorado - IMAX", new BigDecimal("22.5"));
		Filme f = new Filme("Rogue One", Duration.ofMinutes(120), "Ação" , new BigDecimal("12.0"));
		BigDecimal somaDosPrecosSalaEFilme = sa.getPreco().add(f.getPreco());
		Sessao se = new Sessao(sa, f, LocalTime.parse("10:00:00"));
		Assert.assertEquals(somaDosPrecosSalaEFilme, se.getPreco());
	}
	
}
