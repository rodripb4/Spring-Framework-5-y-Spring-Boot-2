package com.bolsaideas.springboot.error.app.services;

import java.util.List;
import java.util.Optional;

import com.bolsaideas.springboot.error.app.models.domains.Usuario;

public interface IUsuarioService {

	public List<Usuario> listar();
	public Usuario obtenerPorId(Integer id);
	public Optional<Usuario> obetenerPorIdOptional(Integer id);
}
