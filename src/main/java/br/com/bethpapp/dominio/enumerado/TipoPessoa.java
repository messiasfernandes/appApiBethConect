package br.com.bethpapp.dominio.enumerado;

public enum TipoPessoa {
	 FÍSICA("Pessoa Física" ),JURÍDICA("Pessoa Juídica");

	private final String descricao;
;
	private TipoPessoa(String descricao) {
      
		this.descricao = descricao;
		
	}

	public String getDescricao() {
		return descricao;
	}
}
