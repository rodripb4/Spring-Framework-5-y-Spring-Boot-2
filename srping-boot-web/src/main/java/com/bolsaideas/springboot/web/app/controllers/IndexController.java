package com.bolsaideas.springboot.web.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsaideas.springboot.web.app.models.Usuario;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${text.indexcontroller.index.title}")
	private String textIndex;
	
	@Value("${text.indexcontroller.profile.title}")
	private String textProfile;
	
	@Value("${text.indexcontroller.list.title}")
	private String textList;

	@GetMapping({"/index", "/", "", "home"})
	public String index(Model model) {
		model.addAttribute("titulo", textIndex);
		return "index";
	}
	
	@RequestMapping("/perfil")
	public String perfil(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Rodrigo");
		usuario.setApellido("Perez");
		usuario.setEmail("correo@correo.com");
		
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", textProfile.concat(usuario.getNombre()));
		return "perfil";
	}
	
	
	@RequestMapping("/listar")
	public String listar(Model model) {
		
		model.addAttribute("titulo", textList);
		//model.addAttribute("usuarios", usuarios);
		
		return "listar";
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> poblarUsuarios(){
		List<Usuario> usuarios = Arrays.asList(new Usuario("Rodrigo", "Perez", "correo@correo.com"),
				new Usuario("Pedro", "Perez", "correo@correo.com"),
				new Usuario("Jimena", "Perez", "correo@correo.com"),
				new Usuario("Lucia", "X", "correo@correo.com"));
		
		return usuarios;
	}
	
	
	
}
