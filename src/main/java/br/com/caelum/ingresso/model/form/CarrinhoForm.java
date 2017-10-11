package br.com.caelum.ingresso.model.form;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDAO;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoIngresso;

public class CarrinhoForm {
	
	private List<Ingresso> ingressos = new ArrayList<>();
	
	public CarrinhoForm(){
		
	}
	
	public List<Ingresso> getIngressos(){
		return this.ingressos;
	}
	
	public void setIngressos(List<Ingresso> ingressos){
		this.ingressos = ingressos;
	}
	
	public List<Ingresso> toIngressos(SessaoDAO sessaoDao, LugarDao lugarDao){
		return this.ingressos.stream().map(ingresso -> {
			Sessao sessao = sessaoDao.findOne(ingresso.getSessao().getId());
			Lugar lugar = lugarDao.findOne(ingresso.getLugar().getId());
			TipoIngresso tipo = ingresso.getTipoDeIngresso();
			return new Ingresso(sessao, tipo, lugar);
		}).collect(Collectors.toList());
	}
	
	

}
