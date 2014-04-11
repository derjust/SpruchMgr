/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.spruchmgr_ws;

public abstract class AbstractWebService {
	public abstract String getVersion();
	public abstract String getName();
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName());
		sb.append("-v");
		sb.append(getVersion());
		return sb.toString();
	}
}
