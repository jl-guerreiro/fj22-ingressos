package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessao {
	
	public List<Sessao> sessoes;
	
	public GerenciadorSessao(List<Sessao> sessoes){
		this.sessoes = sessoes;
	}
	
	private boolean horarioIsValido(Sessao existente, Sessao atual){
		
		LocalDate hoje = LocalDate.now();
		LocalDateTime hExiste = existente.getHorario().atDate(hoje);
		LocalDateTime hAtual = atual.getHorario().atDate(hoje);
		
		boolean antes = hAtual.isBefore(hExiste);
		
		if(antes){
			return hAtual.plus(atual.getFilme().getDuracao()).isBefore(hExiste);
		}else{
			return hExiste.plus(existente.getFilme().getDuracao()).isBefore(hAtual);
		}
		
	}
	
	public boolean cabe(Sessao sAtual){
		Optional<Boolean> opt = sessoes.stream().map(sExistente -> horarioIsValido(sExistente, sAtual)).reduce(Boolean::logicalAnd);
		return opt.orElse(true);
	}

}
