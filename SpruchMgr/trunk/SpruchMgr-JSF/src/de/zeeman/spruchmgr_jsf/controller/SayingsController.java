/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.spruchmgr_jsf.controller;

import javax.ejb.EJB;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import de.zeeman.sayingmgr_ejb.ejb.SayingDAOBean;
import de.zeeman.sayingmgr_ejb.ejb.SayingDAOLocal;

public class SayingsController {
	@EJB
	private SayingDAOLocal sayingDAO;
	private DataModel model;
	
	public SayingsController() {
		super();
	}
	
	public DataModel getSayings() {
		model = new ListDataModel(sayingDAO.getAllSayings());
		return model;
	}
	
}
