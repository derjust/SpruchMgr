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
	private int scrollerPage;
	private int rows;
	
	
	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(int rows) {
		this.rows = rows;
	}

	public SayingsController() {
		super();
	}
	
	public DataModel getSayings() {
		if (model==null) {
			model = new ListDataModel(sayingDAO.getSayingsSubset(getScrollerPage(), getRows()));
		}
		return model;
	}

	/**
	 * @return the scrollerPage
	 */
	public int getScrollerPage() {
		return scrollerPage;
	}

	/**
	 * @param scrollerPage the scrollerPage to set
	 */
	public void setScrollerPage(int scrollerPage) {
		this.scrollerPage = scrollerPage;
	}
	
}
