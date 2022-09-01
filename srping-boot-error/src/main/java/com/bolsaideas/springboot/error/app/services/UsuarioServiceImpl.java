package com.bolsaideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsaideas.springboot.error.app.models.domains.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	private List<Usuario> lista;
	
	public UsuarioServiceImpl() {
		this.lista = new ArrayList<>();
		this.lista.add(new Usuario(1, "Rodrigo", "Perez"));
		this.lista.add(new Usuario(2, "Ándres", "Gonzales"));
		this.lista.add(new Usuario(3, "Pedro", "Jimenez"));
		this.lista.add(new Usuario(4, "María", "Alonso"));
		this.lista.add(new Usuario(5, "Lina", "Ospina"));
	}

	@Override
	public List<Usuario> listar() {
		return this.lista;
	}

	@Override
	public Usuario obtenerPorId(Integer id) {
		Usuario resultado = null;
		
		for (Usuario usuario : lista) {
			if(usuario.getId().equals(id)) {
				resultado = usuario;
				break;
			}
		}
		return resultado;
	}

	@Override
	public Optional<Usuario> obetenerPorIdOptional(Integer id) {
		Usuario usuario = this.obtenerPorId(id);
		return Optional.ofNullable(usuario);
	}

}
