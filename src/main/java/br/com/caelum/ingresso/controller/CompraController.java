package br.com.caelum.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDAO;
import br.com.caelum.ingresso.model.Carrinho;
import br.com.caelum.ingresso.model.form.CarrinhoForm;

@Controller
public class CompraController {

	@Autowired
	private SessaoDAO sessaoDao;
	
	@Autowired
	private LugarDao lugarDao;
	
	@Autowired
	private Carrinho cart;
	
	@PostMapping("compra/ingressos")
	public ModelAndView enviarParaPagamento(CarrinhoForm carrinhoForm){
		ModelAndView mav = new ModelAndView("redirect:/compra");
		carrinhoForm.toIngressos(sessaoDao, lugarDao).forEach(cart::add);
		return mav;
	}
	
	@GetMapping("compra")
	public ModelAndView checkout(){
		ModelAndView mav = new ModelAndView("compra/pagamento");
		mav.addObject("carrinho",cart);
		return mav;
	}
	
}
