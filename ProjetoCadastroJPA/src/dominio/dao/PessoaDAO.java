package dominio.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import controle.util.JpaDAO;
import dominio.Grupo;
import dominio.Pessoa;

public class PessoaDAO extends JpaDAO<Pessoa>
{

	public PessoaDAO()
	{
		super();
	}

	public PessoaDAO(EntityManager manager)
	{
		super(manager);
	}

	public Pessoa lerPorLogin(String nome)
	{
		Pessoa resultado;

		Query consulta = this.getEntityManager().createQuery("from Pessoa p where p.nome = :nome");
		consulta.setParameter("nome", nome);

		try
		{
			resultado = (Pessoa) consulta.getSingleResult();
		}
		catch (NoResultException e)
		{
			resultado = null;
		}

		return resultado;
	}
	
	public List<Pessoa> filtrarPessoas(Grupo grupo, String nome)
	{
		List<Pessoa> retorno = null;

		String conector = " where ";
		StringBuilder comando = new StringBuilder();
		comando.append("from Pessoa p ");
		
		if (grupo != null)
		{
			comando.append(conector).append("p.grupo.id = :idGrupo");
			conector = " and ";
		}
		
		if ((nome != null) && (nome.trim().length() > 0))
		{
			comando.append(conector).append("p.nome like :nome");
			conector = " and ";
		}
		
		TypedQuery<Pessoa> consulta = this.getEntityManager().createQuery(comando.toString(), Pessoa.class);
		if (grupo != null)
			consulta.setParameter("idGrupo", grupo.getId());
		
		if ((nome != null) && (nome.trim().length() > 0))
			consulta.setParameter("nome", "%" + nome + "%");

		try
		{
			retorno = consulta.getResultList();
		}
		catch (NoResultException e)
		{
			retorno = new ArrayList<Pessoa>();
		}
		return retorno;
	}

}
