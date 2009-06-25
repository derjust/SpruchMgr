package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;

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
	public static final String RemoteJNDIName =  SayingDAOBean.class.getSimpleName() + "/remote";
	public static final String LocalJNDIName =  SayingDAOBean.class.getSimpleName() + "/local";
	/**
	 * Default constructor.
	 */
	public SayingDAOBean() {
	}

	public List<Saying> getAllSayings() {
		Query q = em.createQuery("SELECT s FROM Saying s");
		List<Saying> users = q.getResultList();
		return users;
	}
}
