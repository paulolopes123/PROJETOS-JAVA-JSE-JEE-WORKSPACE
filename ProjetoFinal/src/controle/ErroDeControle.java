package controle;

public class ErroDeControle {
	private int codigo;
	private String origem;
	private String mensagem;
	
	public ErroDeControle(int codigo, String mensagem) {
		super();
		this.codigo = codigo;
		this.mensagem = mensagem;
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		this.setOrigem(stack[2].getClassName() + "-" + stack[2].getMethodName());
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	@Override
	public String toString() {
		return "Erro #" + codigo + ": " + mensagem + "\n(Classe: " + origem + ")";
	}
}
