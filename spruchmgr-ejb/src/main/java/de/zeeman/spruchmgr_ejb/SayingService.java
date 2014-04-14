package de.zeeman.spruchmgr_ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import de.zeeman.spruchmgr.Saying;

/**
 * Session Bean implementation class SayingService
 */
@Named
@Stateless
public class SayingService {

	@PersistenceContext(unitName = "Saying_pu")
	EntityManager em;

	public SayingService() {
	}

	public Collection<Saying> getAllSprueche() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saying> cq = cb.createQuery(Saying.class);
		Root<Saying> saying = cq.from(Saying.class);
		cq.select(saying);
		TypedQuery<Saying> q = em.createQuery(cq);
		List<Saying> allSayings = q.getResultList();

		return allSayings;
	}

	public Saying getRandomSaying() {
		/*
		 * SELECT Saying, Lastname, Firstname, Birthday, Obit, Work FROM Sayings
		 * LEFT JOIN Authors ON Sayings.AuthorId = Authors.Id ORDER BY rand()
		 * LIMIT 0,1
		 */
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saying> cq = cb.createQuery(Saying.class);
		Root<Saying> saying = cq.from(Saying.class);
		cq.select(saying);
		cq.orderBy(cb.asc(cb.function("RAND", Void.class)));
		
		TypedQuery<Saying> q = em.createQuery(cq);
		q.setMaxResults(1);
		List<Saying> allSaying = q.getResultList();
		
		return allSaying.get(0);
	}
	
	public Collection<Saying> search(String SayingText, String autorText) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Saying> cq = cb.createQuery(Saying.class);

		Root<Saying> saying = cq.from(Saying.class);
		cq.select(saying);

		List<Predicate> criteria = new ArrayList<Predicate>();
		if (SayingText != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"sayingtext");
			criteria.add(cb.like(saying.<String> get("Saying"), p));
		}
		if (autorText != null) {
			ParameterExpression<String> p = cb.parameter(String.class,
					"autortext");
			criteria.add(cb.or(cb.equal(saying.<String> get("autor.nachname"), p),
					cb.like(saying.<String> get("autor.vorname"), p)));
		}

		TypedQuery<Saying> q = em.createQuery(cq);
		if (SayingText != null) {
			q.setParameter("sayingtext", SayingText);
		}
		if (autorText != null) {
			q.setParameter("autortext", autorText);
		}
		List<Saying> allPets = q.getResultList();

		return allPets;
	}

	public Saying create(Saying Saying) {
		em.persist(Saying);
		em.flush();
		return Saying;
	}

	public Saying update(int id, Saying Saying) {
		Saying.setId(id);
		em.persist(Saying);

		return Saying;
	}

	public Saying getSaying(int id) {
		Saying s = em.find(Saying.class, id);

		return s;
	}


}
