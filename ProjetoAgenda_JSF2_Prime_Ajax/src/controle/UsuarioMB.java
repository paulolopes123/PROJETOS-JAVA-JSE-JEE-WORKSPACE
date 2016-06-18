package controle;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import controle.util.JSFUtil;
import dominio.Usuario;
import dominio.dao.UsuarioDAO;

@ManagedBean(name = "userMB")
@RequestScoped
public class UsuarioMB
{
	private Usuario usuario = new Usuario();
	private UsuarioDAO dao = new UsuarioDAO();

	private List<Usuario> usuarios = null;

	public List<Usuario> getUsuarios()
	{
		if (this.usuarios == null)
			this.usuarios = this.dao.lerTodos();

		return this.usuarios;
	}

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	/**
	 * 
	 */
	public String acaoListar()
	{
		return "usuarioListar";
	}

	/**
	 * 
	 */
	public String acaoAbrirInclusao()
	{
		// limpar o objeto da p�gina
		this.setUsuario(new Usuario());

		return "usuarioEditar";
	}

	/**
	 * 
	 */
	public String acaoAbrirAlteracao()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Usuario objetoDoBanco = this.dao.lerPorId(id);
		this.setUsuario(objetoDoBanco);

		return "usuarioEditar";
	}

	/**
	 * 
	 */
	public String acaoSalvar()
	{
		/**
		 * Deve limpar o ID com valor zero, pois o JSF sempre converte o campo
		 * vazio para um LONG = 0.
		 */
		if ((this.getUsuario().getId() != null) && (this.getUsuario().getId().longValue() == 0))
			this.getUsuario().setId(null);

		/**
		 * Se o usu�rio n�o tiver ID, deve testar se existe o mesmo no banco
		 */
		if (this.getUsuario().getId() == null)
		{
			Usuario objetoDoBanco = this.dao.lerPorLogin(this.getUsuario().getLogin());

			if (objetoDoBanco != null)
			{
				JSFUtil.retornarMensagemErro("Outro usu�rio com o mesmo login j� existe no sistema.", null, null);
				return null; // volta p/mesma p�gina
			}
		}

		this.dao.salvar(this.getUsuario());
		// limpa a lista
		this.usuarios = null;

		// limpar o objeto da p�gina
		this.setUsuario(new Usuario());

		return "usuarioListar";
	}

	/**
	 * 
	 */
	public String acaoCancelar()
	{
		// limpar o objeto da p�gina
		this.setUsuario(new Usuario());

		return "usuarioListar";
	}

	/**
	 * 
	 */
	public String acaoExcluir()
	{
		Long id = JSFUtil.getParametroLong("itemId");
		Usuario objetoDoBanco = this.dao.lerPorId(id);
		this.dao.excluir(objetoDoBanco);

		// limpar o objeto da p�gina
		this.setUsuario(new Usuario());
		// limpa a lista
		this.usuarios = null;

		return "usuarioListar";
	}

}
