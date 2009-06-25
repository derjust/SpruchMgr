<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sayings Listing</title>
<script type="text/javascript">
	function setCaretToEnd(e) {
		var control = $((e.target ? e.target : e.srcElement).id);
		if (control.createTextRange) {
			var range = control.createTextRange();
			range.collapse(false);
			range.select();
		} else if (control.setSelectionRange) {
			control.focus();
			var length = control.value.length;
			control.setSelectionRange(length, length);
		}
		control.selectionStart = control.selectionEnd = control.value.length;
	}
</script>
<style>
.scrollerCell {
	padding-right: 10px;
	padding-left: 10px;
}
</style>
</head>
<body>
<f:view>
	<a4j:form>

		<rich:dataTable id="table" rows="#{sayingsController.rows}"
			columnClasses="col" value="#{sayingsController.sayings}" var="s">
			<f:facet name="footer">
				<rich:datascroller maxPages="20" fastControls="hide" id="ds2"
					renderIfSinglePage="false" page="#{sayingsController.scrollerPage}"
					pagesVar="pages">
					<f:facet name="first">
						<h:outputText value="First" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="first_disabled">
						<h:outputText value="First" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="last">
						<h:outputText value="Last" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="last_disabled">
						<h:outputText value="Last" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="previous">
						<h:outputText value="Previous" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="previous_disabled">
						<h:outputText value="Previous" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="next">
						<h:outputText value="Next" styleClass="scrollerCell" />
					</f:facet>
					<f:facet name="next_disabled">
						<h:outputText value="Next" styleClass="scrollerCell" />
					</f:facet>
				</rich:datascroller>
			</f:facet>
			<f:facet name="header">
				<rich:columnGroup>
					<rich:column rowspan="2">
						<h:outputText value="Nr" />
					</rich:column>
					<rich:column rowspan="2">
						<h:outputText value="Spruch" />
					</rich:column>
					<rich:column colspan="3">
						<h:outputText value="Autor" />
					</rich:column>
					<rich:column breakBefore="true">
						<h:outputText value="Name" />
					</rich:column>
					<rich:column>
						<h:outputText value="Lebzeiten" />
					</rich:column>
					<rich:column>
						<h:outputText value="Arbeit" />
					</rich:column>
				</rich:columnGroup>
			</f:facet>
			<rich:column>
				<h:outputText value="#{s.id}" />
			</rich:column>
			<rich:column filterMethod="#{sayingFilteringBean.filterSaying}">
				<f:facet name="header">
                	Spruch
                    <h:inputText
						value="#{sayingFilteringBean.filterSayingValue}" id="input">
						<a4j:support event="onkeyup" reRender="table , ds2"
							ignoreDupResponses="true" requestDelay="700" />
					</h:inputText>
				</f:facet>
				<h:outputText value="#{s.saying}" />
			</rich:column>
			<rich:column filterBy="#{s.author}" filterEvent="onkeyup">
				<h:outputText value="#{s.author}" />
			</rich:column>
			<rich:column>
				<h:panelGrid columns="2">
					<h:outputText value="* " rendered="#{s.author.birthday != null}" />
					<h:outputText value="#{s.author.birthday}">
						<f:convertDateTime type="date" dateStyle="medium" />
					</h:outputText>

					<h:outputText value="† " rendered="#{s.author.obit != null}" />
					<h:outputText value="#{s.author.obit}">
						<f:convertDateTime type="date" dateStyle="medium" />
					</h:outputText>
				</h:panelGrid>
			</rich:column>
			<rich:column>
				<h:outputText value="#{s.author.work}" />
			</rich:column>
		</rich:dataTable>

	</a4j:form>
</f:view>
</body>
</html>
