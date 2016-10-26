package controle;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Grupo;
import dominio.Pessoa;
import dominio.dao.GrupoDAO;
import dominio.dao.PessoaDAO;

@ManagedBean(name = "pessoaMB")
@RequestScoped
public class PessoaMB {
	private static List<String> listaUF = new ArrayList<String>();

	static {
		// trocar esta lista por uma tabela no banco com os registros dos
		// estados
		listaUF.add("RJ");
		listaUF.add("SP");
		listaUF.add("MG");
		listaUF.add("BA");
		listaUF.add("ES");
	}

	private Grupo filtroGrupo = null;

	private Pessoa pessoa = new Pessoa();
	private PessoaDAO dao = new PessoaDAO();

	private List<Pessoa> pessoas = null;
	private List<Grupo> grupos = null;

	public List<Pessoa> getPessoas() {
		//if (this.pessoas == null)
			this.pessoas = this.dao.filtrarPorGrupo(this.filtroGrupo);

		return this.pessoas;
	}

	public List<Grupo> getGrupos() {
		if (this.grupos == null)
			this.grupos = new GrupoDAO().lerTodos();

		return this.grupos;
	}

	public List<String> getListaUF() {
		return PessoaMB.listaUF;
	}

	public Grupo getFiltroGrupo() {
		return filtroGrupo;
	}

	public void setFiltroGrupo(Grupo filtroGrupo) {
		this.filtroGrupo = filtroGrupo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * 
	 */
	public String acaoListar() {
		return "pessoaListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao() {
		// limpar o objeto da p�gina
		this.setPessoa(new Pessoa());

		return "pessoaEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao() {
		// pega o ID escolhido que veio no par�metro
		Long id = JSFUtil.getParametroLong("itemId");
		Pessoa objetoDoBanco = this.dao.lerPorId(id);
		this.setPessoa(objetoDoBanco);

		return "pessoaEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar() {
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getPessoa().getId() != null)
				&& (this.getPessoa().getId().longValue() == 0))
			this.getPessoa().setId(null);

		this.dao.salvar(this.getPessoa());
		// limpa a lista
		this.pessoas = null;

		// limpar o objeto da p�gina
		this.setPessoa(new Pessoa());

		return "pessoaListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar() {
		// limpar o objeto da p�gina
		this.setPessoa(new Pessoa());

		return "pessoaListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir() {
		Long id = JSFUtil.getParametroLong("itemId");
		Pessoa objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da p�gina
		this.setPessoa(new Pessoa());
		// limpa a lista
		this.pessoas = null;

		return "pessoaListar";
	}

}
