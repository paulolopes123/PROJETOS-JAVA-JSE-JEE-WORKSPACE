package dominio;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class AplicacaoDeProva {

	@Id
	@GeneratedValue
	private Long id;
	private Status status;

	private enum Status {
		Disponivel, EmRealizacao, Finalizada
	};

	private Date dataInicio;

	private Date dataFim;
	@ManyToOne
	private Prova prova;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private Professor professor;

	public AplicacaoDeProva(Long id, Status status, Date dataInicio, Date dataFim, Time horaInicio, Time horaFim,
			Prova prova, Aluno aluno, Professor professor) throws DominioException {
		super();
		this.id = id;
		this.setStatus(status);
		this.setDataInicio(dataInicio);
		this.setDataFim(dataFim);
		this.setProva(prova);
		this.setAluno(aluno);
		this.setProfessor(professor);
	}

	public AplicacaoDeProva() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) throws DominioException {
		validarDataInicio(dataInicio);
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) throws DominioException {
		validarDataFim(dataFim);
		this.dataFim = dataFim;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		if (this.prova == prova)
			return;
		if (prova == null) {
			Prova antigo = this.prova;
			this.prova = null;
			antigo.removeAplicacaoDeProva(this);
		} else {

			if (this.prova != null)
				this.prova.removeAplicacaoDeProva(this);
			this.prova = prova;
			prova.adicionaAplicacaoDeProva(this);
		}
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		if (this.aluno == aluno)
			return;
		if (aluno == null) {
			Aluno antigo = this.aluno;
			this.prova = null;
			antigo.removeAplicacaoDeProva(this);
		} else {

			if (this.aluno != null)
				this.aluno.removeAplicacaoDeProva(this);
			this.aluno = aluno;
			aluno.adicionaAplicacaoDeProva(this);
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
			antigo.removeAplicacaoDeProva(this);
		} else {

			if (this.professor != null)
				this.professor.removeAplicacaoDeProva(this);
			this.professor = professor;
			professor.adicionaAplicacaoDeProva(this);
		}
	}

	@Override
	public String toString() {
		return "Aplicação [aluno=" + aluno + "]";
	}

	// validação dos atributos
	public void validarDataInicio(Date d) throws DominioException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DominioException(ErroDominio.DATA_INVALIDA);

	}

	public void validarDataFim(Date d) throws DominioException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DominioException(ErroDominio.DATA_INVALIDA);

	}

}
