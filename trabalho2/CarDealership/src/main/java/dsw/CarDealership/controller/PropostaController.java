package dsw.CarDealership.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dsw.CarDealership.domain.Proposta;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.domain.Usuario;
import dsw.CarDealership.security.UsuarioDetails;
import dsw.CarDealership.service.spec.IPropostaService;
import dsw.CarDealership.service.spec.IClienteService;
import dsw.CarDealership.service.spec.ICarroService;

@Controller
@RequestMapping("/propostas")
public class PropostaController {
	@Autowired
	private IPropostaService propostaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ICarroService carroService;

	
	@GetMapping("/cadastrar")
	public String cadastrar(Proposta proposta, ModelMap model) {
		proposta.setCliente(clienteService.buscarPorId(getUsuario().getId()));
		model.addAttribute("proposta", proposta);
		return "proposta/cadastro";
	}
	
	@GetMapping("/cadastrar/{id}")
	public String cadastrarNova(@PathVariable("id") Long id,Proposta proposta, ModelMap model) {
		proposta.setCliente(clienteService.buscarPorId(getUsuario().getId()));
		proposta.setCarro(carroService.buscarPorId(id));
		model.addAttribute("proposta", proposta);
		return "proposta/cadastro";
	}
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

	@GetMapping("/listar")
	public String listar(ModelMap model, Proposta proposta) {
		model.addAttribute("propostas", propostaService.buscarTodos());
		return "proposta/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Proposta proposta, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "proposta/cadastro";
		}

		propostaService.salvar(proposta);
		attr.addFlashAttribute("sucess", "proposta.create.sucess");
		return "redirect:/propostas/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("proposta", propostaService.buscarPorId(id));
		return "proposta/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Proposta proposta, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "proposta/cadastro";
		}
		
		propostaService.salvar(proposta);
		attr.addFlashAttribute("sucess", "proposta.edit.sucess");
		
		if(this.getUsuario().getPapel().equals("CLIENTE")) {
			return "redirect:/propostas/listar";
		}
		return "redirect:/lojas/listarProposta";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		propostaService.excluir(id);
		attr.addFlashAttribute("sucess", "proposta.delete.sucess");
		return "redirect:/propostas/listar";
	}
}