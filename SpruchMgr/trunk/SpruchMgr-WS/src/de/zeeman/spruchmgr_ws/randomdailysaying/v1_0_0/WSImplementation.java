/**
 * Copyright (c) 2007-2009 RÖPERWEISE Systems GmbH, Gehrden - GERMANY. All rights reserved.
 */
package de.zeeman.spruchmgr_ws.randomdailysaying.v1_0_0;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.DOCUMENT,parameterStyle=ParameterStyle.BARE)
public class WSImplementation {
	
	  @WebMethod
      public String greet(@WebParam(name = "name") String s){
              return "Hello" + s;
      }

}
