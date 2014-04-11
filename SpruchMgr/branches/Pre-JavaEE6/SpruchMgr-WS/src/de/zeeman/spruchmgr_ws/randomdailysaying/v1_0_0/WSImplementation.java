/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.spruchmgr_ws.randomdailysaying.v1_0_0;

import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

import de.zeeman.sayingmgr_ejb.ejb.SayingDAOLocal;
import de.zeeman.sayingmgr_ejb.entity.Saying;
import de.zeeman.spruchmgr_ws.AbstractWebService;

@WebService(
	serviceName = "RandomDailySaying", 
	name = "RandomDailySaying",
	targetNamespace = "http://SpruchMgr.zeeman.de/RandomDailySaying/v1_0_0")
@SOAPBinding(
	style = Style.DOCUMENT, 
	parameterStyle = ParameterStyle.BARE)
public class WSImplementation extends AbstractWebService {
	@EJB
	private SayingDAOLocal sayingDAO;
	private Saying randomDailySaying;
	private long nextTimestamp;
	
	@Override
	public String getName() {
		return "RandomDailySaying";
	}
	
	@Override
	public String getVersion() {
		return "1_0_0";
	}

	@WebMethod(operationName = "getRandomDailySaying")
	public Saying getRandomDailySaying(/*@WebParam(name="a")String b*/) {
		if (randomDailySaying == null || System.currentTimeMillis() >= nextTimestamp) {
			GregorianCalendar cal = new GregorianCalendar();
			cal.set(GregorianCalendar.HOUR_OF_DAY, 23);
			cal.set(GregorianCalendar.MINUTE, 59);
			cal.set(GregorianCalendar.SECOND, 59);
			cal.set(GregorianCalendar.MILLISECOND, 999);
			nextTimestamp = cal.getTimeInMillis();
			randomDailySaying = sayingDAO.getRandomSaying();
		}
		return randomDailySaying;
	}

}
