package com.bolsaideas.springboot.app.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsaideas.springboot.app.models.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long>{

	@Query("select u from Usuario u where u.username=?1")
	public Usuario finByUsername(String username);
}
