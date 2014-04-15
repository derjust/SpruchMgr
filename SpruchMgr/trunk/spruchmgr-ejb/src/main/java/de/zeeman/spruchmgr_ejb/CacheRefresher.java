package de.zeeman.spruchmgr_ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.resource.ResourceException;

import com.hazelcast.core.ISet;
import com.hazelcast.core.TransactionalSet;
import com.hazelcast.jca.HazelcastConnection;
import com.hazelcast.jca.HazelcastConnectionFactory;

import de.zeeman.spruchmgr.Saying;

/**
 * Session Bean implementation class CacheRefresher
 */
@Named
@Stateless
public class CacheRefresher {
	private static Logger logger = Logger.getLogger(CacheRefresher.class.getName());
	
	@EJB
	protected SayingService sayingService;
	
	@Resource(mappedName="java:/HazelcastCF") 
	protected com.hazelcast.jca.HazelcastConnectionFactory hzFactory;
	
    /**
     * Default constructor. 
     */
    public CacheRefresher() {
    }
    
    @Init
    public void init() {
    	updateCache();
    }
    
    @Schedule(second="*/55", minute = "*", hour="*", persistent=false)
    protected void refreshCache() {
    	logger.fine("Refreshing cache");
    	updateCache();
    }
    
    private void updateCache() {
    	try (HazelcastConnection hazelcast = hzFactory.getConnection()) {
    		TransactionalSet<Saying> rs = hazelcast.getTransactionalSet("randomsaying");
        	Saying s = sayingService.getRandomSaying();
        	logger.log(Level.INFO, "Updated Cache with {0}", s);
        	rs.destroy();
        	rs.add(s);	
    	} catch (ResourceException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
    }
    
    public Saying getRandomSaying() {
    	try (HazelcastConnection hazelcast = hzFactory.getConnection()) {
	    	ISet<Saying> rs = hazelcast.getSet("randomsaying");
	    	Saying s = rs.iterator().next();
	    	logger.log(Level.FINE, "Retrieve random saying {0}", s);
	    	return s;
    	} catch (ResourceException e) {
    		throw new RuntimeException(e.getMessage(), e);
		}
    }

}
