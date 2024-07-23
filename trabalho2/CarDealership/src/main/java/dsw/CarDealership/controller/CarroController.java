package dsw.CarDealership.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dsw.CarDealership.domain.Usuario;
import dsw.CarDealership.security.UsuarioDetails;
import dsw.CarDealership.domain.Carro;
import dsw.CarDealership.domain.Loja;
import dsw.CarDealership.service.spec.ICarroService;
import dsw.CarDealership.service.spec.ILojaService;

@Controller
@RequestMapping("/carros")
public class CarroController {
	@Autowired
	private ICarroService carroService;

	@Autowired
	private ILojaService lojaService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Carro carro, ModelMap model) {
		carro.setLoja(lojaService.buscarPorEmail(getUsuario().getEmail()));
		model.addAttribute("carro", carro);
		return "carro/cadastro";
	}
	
	private Usuario getUsuario() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUsuario();
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("carros", carroService.buscarTodos());
		return "carro/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Carro carro, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "carro/cadastro";
		}
		
		carro.setLoja(lojaService.buscarPorEmail(this.getUsuario().getEmail())); 

		carroService.salvar(carro);
		attr.addFlashAttribute("sucess", "carro.create.sucess");
		if(this.getUsuario().getPapel().equals("LOJA")) {
			return "redirect:/lojas/listarCarro";
		}
		return "redirect:/carros/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		Carro carro = carroService.buscarPorId(id);
		model.addAttribute("carro", carro);
		model.addAttribute("loja", carro.getLoja());
		return "carro/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Carro carro, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "carro/cadastro";
		}

		carroService.salvar(carro);
		attr.addFlashAttribute("sucess", "carro.edit.sucess");
		if(this.getUsuario().getPapel().equals("LOJA")) {
			return "redirect:/lojas/listarCarro";
		}
		return "redirect:/carros/listar";
		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		carroService.excluir(id);
		attr.addFlashAttribute("sucess", "carro.delete.sucess");
		if(this.getUsuario().getPapel().equals("LOJA")) {
			return "redirect:/lojas/listarCarro";
		}
		return "redirect:/carros/listar";
	}

	@ModelAttribute("lojas")
	public List<Loja> listaLojas() {
		return lojaService.buscarTodos();
	}
}
