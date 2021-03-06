package softblue.bluetasks.controller;


import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import softblue.bluetasks.model.Task;

@Named
@ViewScoped
public class ListTasksBean implements Serializable {
	private static final long serialVersionIUD = 1L;
	@PersistenceContext
	private EntityManager em;

	@Inject
	private UserTransaction ut;

	private Task currentTask;

	public String save() throws Exception {
		ut.begin();
		currentTask.setCompleted(false);
		em.merge(currentTask);
		ut.commit();
		return "List";
	}

	@SuppressWarnings("unchecked")
	public List<Task> getTasks() {
		Query q = em.createQuery("SELECT t FROM Task t ORDER BY ID");
		return q.getResultList();
	}

	public String markAsCompleted(Task task) throws Exception {
		ut.begin();
		Task managedTask = em.merge(task);
		managedTask.setCompleted(true);
		ut.commit();
		return null;
	}

	public String delete(Task task) throws Exception {
		ut.begin();
		Task managedTask = em.merge(task);
		em.remove(managedTask);
		ut.commit();
		return null;
	}

	@SuppressWarnings("unchecked")
	public String deleteCompletedTasks() throws Exception {
		ut.begin();
		Query q = em.createQuery("SELECT t FROM Task t WHERE t.completed = true");
		List<Task> results = q.getResultList();

		for (Task task : results) {
			em.remove(task);
		}
		ut.commit();
		return null;
	}

	public Task getCurrentTask() {
		if (currentTask == null) {
			currentTask = new Task();
		}
		return currentTask;
	}

	public String edit(Task task) {
		currentTask = task;
		return "Edit";
	}
}
