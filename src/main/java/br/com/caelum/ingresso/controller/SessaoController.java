package br.com.caelum.ingresso.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDAO;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SessaoDAO sessaoDao;
	
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
		sessaoDao.save(s);
		return new ModelAndView("redirect:/sala/"+form.getSalaId()+"/sessoes");
	}
	
}
