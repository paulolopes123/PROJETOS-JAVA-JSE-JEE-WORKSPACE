package dominio.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.websocket.Session;

import dominio.Foto;
import dominio.Questao;

public class QuestaoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private QuestaoDAO instance;
	protected EntityManager entityManager;

	public QuestaoDAO getInstance() {
		if (instance == null) {
			instance = new QuestaoDAO();
		}
		return instance;

	}

	private QuestaoDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("banco");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}
		return entityManager;
	}

	public void salvarFoto(Foto foto) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(foto);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

}
