<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sayings Listing</title>
    </head>
    <body>
        <f:view>
            <h:form>
                <h1><h:outputText value="Sayings Listing"/></h1>
                <h:dataTable value="#{sayingsController.sayings}"
                             var="dataTableItem" border="1" cellpadding="2" cellspacing="2">
                    <h:column>
                        <f:facet name="header">
                            <h:outputText  value="Saying"/>
                        </f:facet>
                        <h:outputText value="#{dataTableItem.saying}" />
                    </h:column>
                    <h:column>
                        <f:facet name="header">
                            <h:outputText  value="Author"/>
                        </f:facet>
                        <h:outputText value="#{dataTableItem.author}" />
                    </h:column>
                </h:dataTable>
            </h:form>
        </f:view>
    </body>
</html>
