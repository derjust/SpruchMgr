package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;
import java.util.Random;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
}
