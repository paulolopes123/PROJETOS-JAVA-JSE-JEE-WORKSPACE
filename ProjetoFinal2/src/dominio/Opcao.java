package dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import controle.ITabelavel;

/**
 * Implementa a classe Op��o que tem o "implements Serializable" para realizar o
 * processo de serializa��o e o "implements Tabelavel" para informar que os
 * objetos poder�o ser exibidos em uma tabela de interface
 * 
 *
 */
@Entity
public class Opcao implements IDados, ITabelavel, Serializable {
	// Atributos
	@Id
	@GeneratedValue
	private Long id;

	private String textoHtml;

	private boolean ehcorreta;
	@ManyToOne
	private Questao questao;
	@OneToMany(mappedBy = "opcao")
	private Set<Comentario> comentario = new HashSet<Comentario>();
	@OneToMany(mappedBy = "opcao")
	private Set<Responde> responde = new HashSet<Responde>();

	// M�todos
	public Opcao(Long id, String textoHtml, boolean ehcorreta, Questao questao) throws DadosException {
		super();
		this.id = id;
		this.setTextoHtml(textoHtml);
		this.setEhcorreta(ehcorreta);
		this.setQuestao(questao);
	}

	public Opcao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTextoHtml() {
		return textoHtml;
	}

	public void setTextoHtml(String textoHtml) throws DadosException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public boolean isEhcorreta() {
		return ehcorreta;
	}

	public void setEhcorreta(boolean ehcorreta) throws DadosException {
		validarEhCorreta(ehcorreta);
		this.ehcorreta = ehcorreta;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) throws DadosException {
		if (this.questao == questao)
			return;
		if (questao == null) {
			Questao antigo = this.questao;
			this.questao = null;
			antigo.removeOpcao(this);

		} else {
			if (this.questao != null)
				this.questao.removeOpcao(this);
			this.questao = questao;
			questao.adicionaOpcao(this);

		}

	}

	public Set<Comentario> getComentario() {
		return new HashSet<Comentario>(this.comentario);
	}

	// metodo adiciona Coment�rio do atributo de relacionamento n-�rio
	public void adicionaComentario(Comentario comentario) throws DadosException {
		if (this.comentario.contains(comentario))
			return;
		this.comentario.add(comentario);
		comentario.setOpcao(this);

	}

	// metodo remove Coment�rio do atributo de relacionamento n-�rio
	public void removeComentario(Comentario comentario) throws DadosException {
		if (!this.comentario.contains(comentario))
			return;
		this.responde.remove(comentario);
		comentario.setOpcao(null);

	}

	public Set<Responde> getResponde() {
		return new HashSet<Responde>(this.responde);
	}

	// metodo adiciona Resposta do atributo de relacionamento n-�rio
	public void adicionaResposta(Responde responde) throws DadosException {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setOpcao(this);

	}

	// metodo remove Resposta do atributo de relacionamento n-�rio
	public void removeResposta(Responde responde) throws DadosException {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setOpcao(null);

	}

	/**
	 * Implementa��o do m�todo toString que retorna uma String que descreve o
	 * objeto Op��o
	 */
	public String toString() {
		return "Op��o [textoHtml=" + textoHtml + "]";
	}

	// valida��o dos atributos
	@RegraDeDominio
	public void validarTexto(String t) throws DadosException {
		if (t == null || t.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Texto n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarEhCorreta(boolean e) throws DadosException {
		if (e != true || e != false)
			throw new DadosException(new ErroDeDominio(2, "Op��o Inv�lida"));
	}

	@RegraDeDominio
	public void validarQuestao(Questao questao) throws DadosException {
		if (questao == null)
			throw new DadosException(new ErroDeDominio(3, "A Quest�o n�o pode ser nulo"));
	}

	@RegraDeDominio
	public void validarComentario(Comentario comentario) throws DadosException {
		// N�o h� regras de valida��o
	}

	@RegraDeDominio
	public void validarResponde(Responde responde) throws DadosException {
		if (responde == null)
			throw new DadosException(new ErroDeDominio(4, "A Resposta n�o pode ser nulo"));
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		// TODO Auto-generated method stub
		return new Object[] { this.textoHtml };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
