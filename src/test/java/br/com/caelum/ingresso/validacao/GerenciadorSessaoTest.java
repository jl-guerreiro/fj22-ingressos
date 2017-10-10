package br.com.caelum.ingresso.validacao;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;


public class GerenciadorSessaoTest {
	
	private final BigDecimal dec = BigDecimal.ONE;
	
	@Test
	public void garanteQueNaoDevePermitirSessaoNoMesmoHorario(){
		Filme f = new Filme("Rogue One", Duration.ofMinutes(120), "Açao",dec);
		LocalTime horario = LocalTime.parse("10:00:00");
		Sala sa = new Sala("",dec);
		Sessao se = new Sessao(sa,f,horario);
		List<Sessao> ls = Arrays.asList(se);
		GerenciadorSessao gs = new GerenciadorSessao(ls);
		Assert.assertFalse(gs.cabe(se));
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente(){
		Filme f = new Filme("Rogue One", Duration.ofMinutes(120), "Açao",dec);
		LocalTime horario = LocalTime.parse("10:00:00");
		Sala sa = new Sala("",dec);
		List<Sessao> ls = Arrays.asList(new Sessao(sa,f,horario));
		Sessao se = new Sessao(sa,f,horario.minusHours(1));
		GerenciadorSessao gs = new GerenciadorSessao(ls);
		Assert.assertFalse(gs.cabe(se));	
	}
	
	@Test
	public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente(){
		Filme f = new Filme("Rogue One", Duration.ofMinutes(120), "Açao",dec);
		LocalTime horario = LocalTime.parse("10:00:00");
		Sala sa = new Sala("",dec);
		List<Sessao> ls = Arrays.asList(new Sessao(sa,f,horario));
		Sessao se = new Sessao(sa,f,horario.plusHours(1));
		GerenciadorSessao gs = new GerenciadorSessao(ls);
		Assert.assertFalse(gs.cabe(se));
	}
	
	@Test
	public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		Sala sa = new Sala("",dec);
		Filme f1 = new Filme("Rogue One", Duration.ofMinutes(90), "Ação",dec);
		LocalTime dez = LocalTime.parse("10:00:00");
		Sessao se10 = new Sessao(sa,f1,dez);
		Filme f2 = new Filme("Rogue One", Duration.ofMinutes(120), "Ação",dec);
		LocalTime seis = LocalTime.parse("18:00:00");
		Sessao se18 = new Sessao(sa,f2,seis);
		List<Sessao> ls = Arrays.asList(se10,se18);
		GerenciadorSessao gs = new GerenciadorSessao(ls);
		Sessao se = new Sessao(sa,new Filme("A",Duration.ofMinutes(90),"A",dec),LocalTime.parse("13:00:00"));
		Assert.assertTrue(gs.cabe(se));
	} 
	
}
