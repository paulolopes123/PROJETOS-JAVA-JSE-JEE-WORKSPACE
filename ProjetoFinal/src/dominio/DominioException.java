package dominio;

public class DominioException extends Exception {

	private ErroDominio erro;

	public DominioException(ErroDominio erro) {
		super(erro.getMensagem());
		this.erro = erro;
	}

	public ErroDominio getErro() {
		return erro;
	}

}
