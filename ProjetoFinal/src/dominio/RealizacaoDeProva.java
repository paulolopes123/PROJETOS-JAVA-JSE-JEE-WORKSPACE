package dominio;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class RealizacaoDeProva {
	@Id
	@GeneratedValue
	private Long id;
	private Date data;
	private Time horaInicio;
	private Time horaFim;
	@ManyToOne
	private Aluno aluno;
	@ManyToOne
	private AplicacaoDeProva aplicacaodeprova;

	@OneToMany(mappedBy = "realizacao")
	private Set<Responde> responde = new HashSet<Responde>();

	public RealizacaoDeProva(Long id, Date data, Time horaInicio, Time horaFim) {
		super();
		this.id = id;
		this.data = data;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
	}

	public RealizacaoDeProva() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Time getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Time horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Time getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Time horaFim) {
		this.horaFim = horaFim;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		if (this.aluno == aluno)
			return;
		if (aluno == null) {
			Aluno antigo = this.aluno;
			this.aluno = null;
			antigo.removeRealizacaoDeProva(this);

		} else {
			if (this.aluno != null)
				this.aluno.removeRealizacaoDeProva(this);
			this.aluno = aluno;
			aluno.adicionaRealizacaoDeProva(this);

		}

	}

	public AplicacaoDeProva getAplicacaodeprova() {
		return aplicacaodeprova;
	}

	public void setAplicacaodeprova(AplicacaoDeProva aplicacaodeprova) {
		this.aplicacaodeprova = aplicacaodeprova;
	}

	public Set<Responde> getResponde() {
		return new HashSet<Responde>(this.responde);
	}

	// metodo adiciona Resposta do atributo de relacionamento n-ário
	public void adicionaResposta(Responde responde) {
		if (this.responde.contains(responde))
			return;
		this.responde.add(responde);
		responde.setRealizacaoDeProva(this);

	}

	// metodo remove Resposta do atributo de relacionamento n-ário
	public void removeResposta(Responde responde) {
		if (!this.responde.contains(responde))
			return;
		this.responde.remove(responde);
		responde.setRealizacaoDeProva(null);

	}

	@Override
	public String toString() {
		return "RealizacaoDeProva [horaInicio=" + horaInicio + ", horaFim=" + horaFim + "]";
	}

	// validação dos atributos
	public void validarData(Date d) throws DominioException {
		if (d == null || ((CharSequence) d).length() == 0)
			throw new DominioException(ErroDominio.DATA_INVALIDA);

	}

	public void validarHoraInicio(Time i) throws DominioException {
		if (i == null || ((CharSequence) i).length() == -1)
			throw new DominioException(ErroDominio.HORA_INICIO_INVALIDA);

	}

	public void validarHoraFim(Time f) throws DominioException {
		if (f == null || ((CharSequence) f).length() == -1)
			throw new DominioException(ErroDominio.HORA_FIM_INVALIDA);

	}

}
