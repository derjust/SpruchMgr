package de.zeeman.sayingmgr_ejb.ejb;

import java.util.List;

import javax.ejb.Local;

import de.zeeman.sayingmgr_ejb.entity.Author;
import de.zeeman.sayingmgr_ejb.entity.Saying;

@Local
public interface SayingDAOLocal {
	public List<Saying> getAllSayings();

	public Saying getRandomSaying();
	
	public Saying getSaying(int id);
/** a*/
	public Author getAuthor(int authorid);

}
