package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Controller
public class ContaController {
	
	private ContaDAO dao;
	
	@Autowired
	public ContaController(ContaDAO dao){
		this.dao = dao;
	}
	
	@RequestMapping("/form")
	public String execute2 (){
		return ("conta/formulario");
	}
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result) {
		
		if(result.hasErrors()){
			return"conta/formulario";
		}
		System.out.println("Adicionando conta");
		dao.adiciona(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping("/removeConta")
		public String remove(Conta conta){
			System.out.println("Removendo Conta");
			dao.remove(conta);
			return "redirect:listaContas";
		}
	
	@RequestMapping("/pagaConta")
		public void paga(Conta conta, HttpServletResponse response){
		System.out.println("Pagando Conta...");
		dao.paga(conta.getId());
		response.setStatus(200);
	}
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model) {
	  model.addAttribute("conta", dao.buscaPorId(id));
	  return "conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
	  dao.altera(conta);
	  return "redirect:listaContas";
	}
	
	@RequestMapping("/listaContas")
	public String lista(Model mv) {
	  List<Conta> contas = dao.lista();
	  System.out.println("Listando Contas");
	  mv.addAttribute("todasContas", contas);
	  return "conta/lista-contas";
	}

}
