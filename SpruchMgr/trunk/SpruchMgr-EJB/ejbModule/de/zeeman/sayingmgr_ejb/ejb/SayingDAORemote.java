package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;

import javax.ejb.Remote;

import de.zeeman.sayingmgr_ejb.entity.Saying;

@Remote
public interface SayingDAORemote {
	public List<Saying> getAllSayings();
	public Saying getRandomSaying();
}
