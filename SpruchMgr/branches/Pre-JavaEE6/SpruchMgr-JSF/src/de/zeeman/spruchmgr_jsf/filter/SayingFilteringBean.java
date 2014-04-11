/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.spruchmgr_jsf.filter;

import de.zeeman.sayingmgr_ejb.entity.Saying;

public class SayingFilteringBean {
	private String filterSayingValue="";

	/**
	 * @return the filterSayingValue
	 */
	public String getFilterSayingValue() {
		return filterSayingValue;
	}

	/**
	 * @param filterSayingValue the filterSayingValue to set
	 */
	public void setFilterSayingValue(String filterSayingValue) {
		this.filterSayingValue = filterSayingValue;
	}
	
    public boolean filterSaying(Object current) {
        Saying saying = (Saying)current;
        if (filterSayingValue.length()==0) {
            return true;
        }
        else {
        	return (saying.getSaying().toLowerCase().contains(filterSayingValue));
        }
    }
}
