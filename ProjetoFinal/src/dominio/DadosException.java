package dominio;

public class DadosException extends Exception {

	private ErroDeDominio erro;

	public DadosException(ErroDeDominio erro) {
		super(erro.getMensagem());
		this.erro = erro;
	}

	public ErroDeDominio getErro() {
		return erro;
	}

}
