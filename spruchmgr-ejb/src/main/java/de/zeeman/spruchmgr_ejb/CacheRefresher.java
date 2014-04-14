package de.zeeman.spruchmgr_ejb;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Init;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ISet;
import com.hazelcast.instance.HazelcastInstanceFactory;

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
	
	private static final HazelcastInstance hazelcast = HazelcastInstanceFactory.newHazelcastInstance(null);
	
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
    	ISet<Saying> rs = hazelcast.getSet("randomsaying");
    	Saying s = sayingService.getRandomSaying();
    	logger.log(Level.INFO, "Updated Cache with {0}", s);
    	rs.clear();
    	rs.add(s);
    }
    
    public Saying getRandomSaying() {
    	ISet<Saying> rs = hazelcast.getSet("randomsaying");
    	Saying s = rs.iterator().next();
    	logger.log(Level.FINE, "Retrieve random saying {0}", s);
    	return s;
    }

}
