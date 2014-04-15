package de.zeeman.spruchmgr_war;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.zeeman.spruchmgr.Saying;
import de.zeeman.spruchmgr_ejb.SayingService;


@Path("/spruch")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Named
public class SpruchEndpoint {

	@Inject
	SayingService sayingService;

	@GET
	@Path("/count")
	public long getCount() {

		return sayingService.getSpruecheCount();
	}
	
	@GET
	@Path("/")
	public Collection<Saying> getSprueche(@QueryParam("start") int start,
			@QueryParam("length") int length) {

		if (length==0){
			length=15;
		}
		System.out.println(start);
		System.out.println(length);

		Collection<Saying> retValue = sayingService.getAllSprueche(start, length);

		return retValue;
	}

	@GET
	@Path("/{id}")
	public Saying getSpruch(@PathParam("id") int id) {
		return sayingService.getSaying(id);

	}

	@POST
	@Path("/{id}")
	public Saying updateSpruch(@PathParam("id") int id, Saying spruch) {
		
		spruch = sayingService.update(id, spruch);

		return spruch;
	}

	@GET
	@Path("/search")
	public Collection<Saying> searchSprueche(
			@FormParam("spruch") String spruchText,
			@FormParam("autor") String autorText) {

		return sayingService.search(spruchText, autorText);
		
	}

	@PUT
	@Path("/")
	public Saying createSpruch(Saying spruch) {

		return sayingService.create(spruch);
	}

}
