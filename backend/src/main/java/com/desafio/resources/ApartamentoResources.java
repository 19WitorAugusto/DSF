package com.desafio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.desafio.domain.Apartamento;
import com.desafio.services.ApartamentoService;

//Classe de controle/acesso aos serviços

@RestController
@RequestMapping(value = "/api/apartamento") // endpoint rest que a classe vai responder
public class ApartamentoResources {

	@Autowired
	private ApartamentoService apartamentoService;

	// buscar todos
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.GET) // metodo HTTP para busca
	public ResponseEntity<List<Apartamento>> findAll() {
		List<Apartamento> list = apartamentoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	// busca por id
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // metodo HTTP para busca, recebendo um parametro id
	public ResponseEntity<Apartamento> findById(@PathVariable Integer id) {
		Apartamento obj = apartamentoService.findById(id);// declarando um apartamento, e chamando operação vinda do service														
		return ResponseEntity.ok().body(obj);
	}

	//metodo para receber um apartamento no formato JSON e inserir no BD
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(method = RequestMethod.POST)//Medodo HTTP para inserção
	public ResponseEntity<Void> insert(@RequestBody Apartamento obj) {//requestBody - faz o JSON ser convertido p/ objeto java automaticamente
		obj = apartamentoService.insert(obj);//chamando objeto e inserindo no serviço
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();//pegando id, para que na hora da inserção temos o id correto(id criado automatico pelo BD)
		return ResponseEntity.created(uri).build();//codigo 201 - created
	}

	// metodo para atualização de apartamento
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT) // metodo HTTP para atualização, recebendo um parametro id
	public ResponseEntity<Void> update(@RequestBody Apartamento obj, @PathVariable Integer id) {
		obj.setId(id);// garantimos que iremos atualizar o apartamento correto que estamos passando o id
		obj = apartamentoService.update(obj);// chamando objeto / metodo de atualização / inserindo no serviço
		return ResponseEntity.noContent().build();
	}

	// metodo para delete de apartamento
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		apartamentoService.delete(id);// metodo delete / vindo do serviço
		return ResponseEntity.noContent().build();
	}
}