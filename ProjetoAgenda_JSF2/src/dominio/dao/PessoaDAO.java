package dominio.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.util.JpaDAO;
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

}
