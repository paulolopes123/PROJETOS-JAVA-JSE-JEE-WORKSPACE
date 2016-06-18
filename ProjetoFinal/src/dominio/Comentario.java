package dominio;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import controle.ITabelavel;

/**
 * Implementa a classe Comentário que tem o "implements Serializable" para
 * realizar o processo de serialização e o "implements Tabelavel" para informar
 * que os objetos poderão ser exibidos em uma tabela de interface
 * 
 *
 */

@Entity
public class Comentario implements IDados, ITabelavel, Serializable {
	// Atributos
	@Id
	@GeneratedValue
	private Long id;

	private String textoHtml;
	@ManyToOne
	private Opcao opcao;
	@ManyToOne
	private Professor professor;

	// Métodos
	public Comentario(Long id, String textoHtml, Opcao opcao, Professor professor) throws DadosException {
		super();

		this.id = id;
		this.setTextoHtml(textoHtml);
		this.setOpcao(opcao);
		this.setProfessor(professor);
	}

	public Comentario() {
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

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) throws DadosException {
		if (this.opcao == opcao)
			return;
		if (opcao == null) {
			Opcao antigo = this.opcao;
			this.opcao = null;
			antigo.removeComentario(this);

		} else {

			if (this.opcao != null)
				this.opcao.removeComentario(this);
			this.opcao = opcao;
			opcao.adicionaComentario(this);

		}
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) throws DadosException {
		if (this.professor == professor)
			return;
		if (professor == null) {
			Professor antigo = this.professor;
			this.professor = null;
			antigo.removeComentario(this);

		} else {

			if (this.professor != null) {
				this.professor.removeComentario(this);
				this.professor = professor;
				professor.adicionaComentario(this);
			}
		}
	}

	/**
	 * Implementação do método toString que retorna uma String que descreve o
	 * objeto Comentário
	 */
	public String toString() {
		return "Comentário [textoHtml=" + textoHtml + "]";
	}

	// validação dos atributos
	@RegraDeDominio
	public void validarTexto(String t) throws DadosException {
		if (t == null || t.length() == 0)
			throw new DadosException(new ErroDeDominio(1, "O Texto não pode ser nulo"));
	}

	@RegraDeDominio
	public void validarOpcao(Opcao opcao) throws DadosException {
		if (opcao == null)
			throw new DadosException(new ErroDeDominio(2, "A opção não pode ser nulo"));
	}

	@RegraDeDominio
	public void validarProfessor(Professor professor) throws DadosException {
		if (professor == null)
			throw new DadosException(new ErroDeDominio(3, "O Professor não pode ser nulo"));
	}

	/**
	 * Retorna um array de Objects com os estados dos atributos dos objetos
	 * 
	 * @return
	 */
	public Object[] getData() {
		// TODO Auto-generated method stub
		return new Object[] { this.textoHtml, this.professor.getNome() };
	}

	@Override
	public Object getChave() {
		// TODO Auto-generated method stub
		return id;
	}

}
