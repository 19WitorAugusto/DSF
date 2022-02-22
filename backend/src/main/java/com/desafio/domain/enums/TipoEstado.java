package com.desafio.domain.enums;

//tipos estados
public enum TipoEstado {

	LIVRE(0, "LIVRE"), LOCADO(1, "LOCADO");

	private int cod;
	private String descricao;

	private TipoEstado(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;

	}

	// somente metodos get / tipo enumerados não se alterada o nome
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	// metodo responsavel pela operaçao: recebe um codigo e retorna um objeto do
	// tipo tipocliente
	public static TipoEstado toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		// todos os valores possiveis do tipo enumerado estado
		for (TipoEstado x : TipoEstado.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
