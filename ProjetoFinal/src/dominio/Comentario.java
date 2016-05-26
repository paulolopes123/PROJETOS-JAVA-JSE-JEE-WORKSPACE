package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {
	@Id
	@GeneratedValue
	private Long id;

	private String textoHtml;
	@ManyToOne
	private Opcao opcao;
	@ManyToOne
	private Professor professor;

	public Comentario(Long id, String textoHtml, Opcao opcao, Professor professor) throws DominioException {
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

	public void setTextoHtml(String textoHtml) throws DominioException {
		validarTexto(textoHtml);
		this.textoHtml = textoHtml;
	}

	public Opcao getOpcao() {
		return opcao;
	}

	public void setOpcao(Opcao opcao) {
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

	public void setProfessor(Professor professor) {
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

	@Override
	public String toString() {
		return "Comentário [textoHtml=" + textoHtml + "]";
	}

	// validação dos atributos
	public void validarTexto(String t) throws DominioException {
		if (t == null || t.length() == 0)
			throw new DominioException(ErroDominio.TEXTO_INVALIDO);
	}

}
