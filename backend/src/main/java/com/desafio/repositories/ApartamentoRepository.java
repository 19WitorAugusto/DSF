package com.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.domain.Apartamento;

//Interface responsavel por acessar o BD e realizar consultas 
@Repository
public interface ApartamentoRepository extends JpaRepository<Apartamento, Integer> {

}
