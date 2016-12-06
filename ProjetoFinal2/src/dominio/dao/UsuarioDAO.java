package dominio.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.inject.Inject;
import dominio.Usuario;

public class UsuarioDAO implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction ut;

	public void save(String usu) throws Exception {
		ut.begin();
		em.merge(usu);
		ut.commit();
	}

}
