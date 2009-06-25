package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;

import javax.ejb.Local;

import de.zeeman.sayingmgr_ejb.entity.Saying;

@Local
public interface SayingDAOLocal {
	public List<Saying> getAllSayings();

	public List<Saying> getSayingsSubset(int scrollerPage, int rows);
}
