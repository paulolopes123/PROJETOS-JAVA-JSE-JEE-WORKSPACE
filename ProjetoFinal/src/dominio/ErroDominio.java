package dominio;

public enum ErroDominio {

	NOME_INVALIDO(1, "Nome não pode ser nula"), MATRICULA_NULA(2, "Matrícula não pode ser nula"), SENHA_INVALIDA(3,
			"A senha passada é inválida"), LOGIN_INVALIDO(4, "O login passada é inválido"), DATA_INVALIDA(5,
					"A data passada e inválida"), HORA_INICIO_INVALIDA(5, "Hora inicio inválida"), HORA_FIM_INVALIDA(5,
							"Hora fim inválida"), TEXTO_INVALIDO(6, "Texto inválido"), ITEM_INVALIDO(7,
									"Opção inválida"), VALOR_INVALIDO(8,
											"O valor passado é inválido"), ID_INVALIDO(9, "O ID informado é inválido");

	private int codigo;
	private String mensagem;

	private ErroDominio(int codigo, String mensagem) {
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
