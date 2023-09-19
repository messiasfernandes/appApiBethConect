package br.com.bethpapp.dominio.enumerado;

public enum StatusPagamento {
	PENDENTE("Pendente"), PAGO("Pago"), CANCELADO("Cancelado"), PAGOPARCIAL("Pagamento Parcial"),
	VENCIDO("Vencido "), RENEGOCIADO("Renegociado");

	private String descricao;

	private StatusPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	   public static StatusPagamento fromDescricao(String descricao) {
	        for (StatusPagamento status : StatusPagamento.values()) {
	            if (status.getDescricao().equals(descricao)) {
	                return status;
	            }
	        }
	        throw new IllegalArgumentException("Descrição inválida para o enum StatusPagamento: " + descricao);
	    }

	    public static String toDescricao(StatusPagamento status) {
	        // Lógica para converter o enum em um formato desejado
	        // Neste caso, cada palavra terá letras maiúsculas iniciais
	        String[] words = status.getDescricao().toLowerCase().split(" ");
	        StringBuilder formatted = new StringBuilder();
	        for (String word : words) {
	            formatted.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
	        }
	        return formatted.toString().trim();
	    }
	  
	
}
