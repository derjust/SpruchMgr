package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.zeeman.sayingmgr_ejb.entity.Author;
import de.zeeman.sayingmgr_ejb.entity.Saying;

/**
 * Session Bean implementation class SayingDAOBean
 */
@Stateless
public class SayingDAOBean implements SayingDAOLocal, SayingDAORemote {
	@PersistenceContext
	private EntityManager em;
	private Random random = new Random(System.currentTimeMillis());

	/**
	 * Default constructor.
	 */
	public SayingDAOBean() {
	}

	public Author getAuthor(int id) {
		Query q = em.createQuery("SELECT a FROM Author a WHERE a.id=:id");
		q.setParameter("id", id);
		q.setMaxResults(1);
		List<?> result = q.getResultList();
		if (result.size() == 1 && result.get(0) instanceof Author) {
			return (Author) result.get(0);
		} else {
			return null;
		}

	}

	public List<Saying> getAllSayings() {
		Query q = em.createQuery("SELECT s FROM Saying s");
		List<Saying> sayings = q.getResultList();
		return sayings;
	}

	public Saying getRandomSaying() {
		Query q = em.createQuery("SELECT s FROM Saying s");
		List<Saying> sayings = q.getResultList();
		int id = random.nextInt(sayings.size());
		return sayings.get(id);
	}

	public Saying getSaying(int id) {
		Query q = em.createQuery("SELECT s FROM Saying s WHERE s.id=:id");
		q.setParameter("id", id);
		q.setMaxResults(1);
		List<?> result = q.getResultList();
		if (result.size() == 1 && result.get(0) instanceof Saying) {
			return (Saying) result.get(0);
		} else {
			return null;
		}

	}
}
