package br.com.caelum.ingresso.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.client.ImdbClient;
import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDAO;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorSessao;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SessaoDAO sessaoDao;
	@Autowired
	private ImdbClient imdb;
	@Autowired
	private Carrinho cart;
	
	@GetMapping("admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form){
		ModelAndView mav = new ModelAndView("sessao/sessao");
		System.err.println(salaId);
		mav.addObject("sala",salaDao.findOne(salaId));
		mav.addObject("filmes",filmeDao.findAll());
		form.setSalaId(salaId);
		mav.addObject("form",form);
		return mav;
	}
	
	@PostMapping("admin/sessao") 
	@Transactional
	public ModelAndView grava(@Valid SessaoForm form, BindingResult result){
		if(result.hasErrors())return new ModelAndView("/error");
		Sessao s = form.toSessao(salaDao, filmeDao);

		List<Sessao> lsSessao = sessaoDao.buscaSessoesSala(s.getSala());
		GerenciadorSessao gerSessao = new GerenciadorSessao(lsSessao);

		if(gerSessao.cabe(s)){
			sessaoDao.save(s);
			return new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
		}
		return form(form.getSalaId(), form);
	}
	
	@GetMapping("sessao/{id}/lugares")
	public ModelAndView lugaresNaSessao(@PathVariable("id") Integer sessaoId){
		ModelAndView mav = new ModelAndView("sessao/lugares");
		Sessao s = sessaoDao.findOne(sessaoId);
		Optional<ImagemCapa> imagem = imdb.request(s.getFilme(), ImagemCapa.class);
		mav.addObject("sessao",s);
		mav.addObject("carrinho", cart);
		mav.addObject("imagemCapa", imagem.orElse(new ImagemCapa()));
		mav.addObject("tiposDeIngressos", TipoIngresso.values());
		return mav;
	}
	
	
}
