package com.desafio.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.desafio.domain.enums.TipoEstado;

@Entity
public class Apartamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "Preenchimento obrigatório")
	@Column(unique = true)
	private Integer numero;
	@NotNull(message = "Preenchimento obrigatório")
	private Integer estado;

	public Apartamento() {

	}

	public Apartamento(Integer id, Integer numero, TipoEstado estado) {

		this.id = id;
		this.numero = numero;
		this.estado = estado.getCod();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public TipoEstado getEstado() {
		return TipoEstado.toEnum(estado);
	}

	public void setEstado(TipoEstado estado) {
		this.estado = estado.getCod();
	}
}