package com.bolsaideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsaideas.springboot.app.models.entity.Cliente;

public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long>{


}
