package dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String senha;
	@Column(unique = true)
	private String login;

	// validação dos atributos
	public static void validarSenha(String s) throws DadosException {
		if (s == null || s.length() == 0)
			throw new DadosException(new ErroDeDominio(1,"A senha não pode ser nula ou vazia"));

	}

	public static void validarLogin(String l) throws DadosException {
		if (l == null || l.length() == 0)
			throw new DadosException(new ErroDeDominio(2,"A conta não pode ser nula ou vazia"));
	}

}
