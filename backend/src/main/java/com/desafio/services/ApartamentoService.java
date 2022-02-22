package com.desafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.domain.Apartamento;
import com.desafio.domain.enums.TipoEstado;
import com.desafio.repositories.ApartamentoRepository;

//Classe responsavel por oferecer operações e consultas de Apartamento

@Service
public class ApartamentoService {

	// depencia automaticamente instanciada pelo spring - autowired - injeção de dependencia
	@Autowired
	private ApartamentoRepository apartamentoRepository;

	// busca por id
	public Apartamento findById(Integer id) {
		Optional<Apartamento> obj = apartamentoRepository.findById(id);// operação do Repository,
		return obj.orElse(null);
	}

	// buscar todos
	public List<Apartamento> findAll() {
		return apartamentoRepository.findAll();
	}

	// inserção
	public Apartamento insert(Apartamento obj) {
		obj.setEstado(TipoEstado.LIVRE);//
		obj.setId(null);// Objeto novo a ser inserido deve ter o id null
		return apartamentoRepository.save(obj);
	}

	// atualizar
	public Apartamento update(Apartamento obj) {

		Apartamento newObj = findById(obj.getId());// estanciar apartamento a partir do BD
		updateData(newObj, obj);// atualizar os dados do novo obj, com base no obj que veio como argumento
		return apartamentoRepository.save(newObj);// salvar os dados no objeto atualizado
	}

	// deletar
	public void delete(Integer id) {
		apartamentoRepository.deleteById(id);
	}

	// atualizar o objeto newObj com os novos dados de obj
	private void updateData(Apartamento newObj, Apartamento obj) {
		newObj.setNumero(obj.getNumero());
		newObj.setEstado(obj.getEstado());
	}

}