package com.bolsaideas.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsaideas.springboot.error.app.errors.UsuarioNoEncontradoException;
import com.bolsaideas.springboot.error.app.models.domains.Usuario;
import com.bolsaideas.springboot.error.app.services.IUsuarioService;

@Controller
public class AppController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@SuppressWarnings("unused")
	@GetMapping("/index")
	public String index(Model model) {
		Integer valor = 100/0;
		//Integer valor = Integer.parseInt("10x");
		return "index";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable Integer id, Model model) {
		/*Usuario usuario = usuarioService.obtenerPorId(id);
		
		if(usuario == null) {
			throw new UsuarioNoEncontradoException(id.toString());
		}*/
		
		Usuario usuario = usuarioService.obetenerPorIdOptional(id).orElseThrow(() -> new UsuarioNoEncontradoException(id.toString()));
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Detalle usuario: ".concat(usuario.getNombre()));
		return "ver";
	}
}
