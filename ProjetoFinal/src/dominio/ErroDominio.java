package dominio;

public enum ErroDominio {

	NOME_INVALIDO(1, "Nome n�o pode ser nula"), MATRICULA_NULA(2, "Matr�cula n�o pode ser nula"), SENHA_INVALIDA(3,
			"A senha passada � inv�lida"), LOGIN_INVALIDO(4, "O login passada � inv�lido"), DATA_INVALIDA(5,
					"A data passada e inv�lida"), HORA_INICIO_INVALIDA(5, "Hora inicio inv�lida"), HORA_FIM_INVALIDA(5,
							"Hora fim inv�lida"), TEXTO_INVALIDO(6, "Texto inv�lido"), ITEM_INVALIDO(7,
									"Op��o inv�lida"), VALOR_INVALIDO(8,
											"O valor passado � inv�lido"), ID_INVALIDO(9, "O ID informado � inv�lido");

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
