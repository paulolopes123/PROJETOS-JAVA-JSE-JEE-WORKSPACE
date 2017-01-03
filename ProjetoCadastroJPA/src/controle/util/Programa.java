package controle.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Grupo;
import dominio.Pessoa;

public class Programa {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("banco");
		EntityManager maneger = fabrica.createEntityManager();
		
		
		
		Grupo grupo = new Grupo();
		grupo.setDescricao("familiares");
		
		maneger.getTransaction().begin();
		
		//salvar o grupo no banco
		grupo = maneger.merge(grupo);
		maneger.flush();
		maneger.getTransaction().commit();
		
		 Pessoa pessoa = new Pessoa();
		 pessoa.setNome("ana maria");
		 pessoa.setGrupo(grupo);
		 
		 maneger.getTransaction().begin();
		 
		 //salvar pessoa no grupo
		 pessoa = maneger.merge(pessoa);
		 maneger.flush();
		 maneger.getTransaction().commit();
		

	}

}
