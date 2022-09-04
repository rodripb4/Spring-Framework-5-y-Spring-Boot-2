package com.bolsaideas.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bolsaideas.springboot.app.models.entity.Cliente;

public interface IClienteDAO extends CrudRepository<Cliente, Long>{


}
