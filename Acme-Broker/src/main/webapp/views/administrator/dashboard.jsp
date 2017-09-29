<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- The list of credit cards that are used by more than one customer -->

<h3><spring:message code="administrator.dashboard.req1" /></h3>

<display:table name="creditCards" id="creditCard" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="administrator.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="holderName" sortable="true" />

	<spring:message code="administrator.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="brandName" sortable="true" />
	
	<spring:message code="administrator.creditCard.expirationMonth" var="expirationMonth" />
	<display:column title="${expirationMonth}" property="expirationMonth" sortable="true" />
	
	<spring:message code="administrator.creditCard.expirationYear" var="expirationYear" />
	<display:column title="${expirationYear}" property="expirationYear" sortable="true" />
		
	<spring:message code="administrator.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="cardNumber" sortable="true" />
	
</display:table>

<br />

<!-- The name of consumer who has made more requests -->

<h3><spring:message code="administrator.dashboard.req2" /></h3>

<display:table name="consumers" id="consumer" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="administrator.consumer.name" var="name" />
	<display:column title="${name}" property="name" sortable="true" />

	<spring:message code="administrator.consumer.surname" var="surname" />
	<display:column title="${surname}" property="surname" sortable="true" />
	
</display:table>

<br />

<!-- The minimum number of items offered by the suppliers -->

<div>
	<h3><spring:message code="administrator.dashboard.req3" /></h3>
	<div>
		<p>
			<jstl:out value="${minItems}"/>
		</p>
	</div>
</div>

<br />

<!-- The maximum number of items offered by the suppliers -->

<div>
	<h3><spring:message code="administrator.dashboard.req4" /></h3>
	<div>
		<p>
			<jstl:out value="${maxItems}"/>
		</p>
	</div>
</div>

<br />

<!-- The average number of items offered by the suppliers -->

<div>
	<h3><spring:message code="administrator.dashboard.req5" /></h3>
	<div>
		<p>
			<jstl:out value="${avgItems}"/>
		</p>
	</div>
</div>

<br />

<!-- The name of the suppliers who offers more items -->

<h3><spring:message code="administrator.dashboard.req6" /></h3>

<display:table name="suppliersMore" id="supplierMore" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="administrator.supplier.name" var="name" />
	<display:column title="${name}" property="name" sortable="true" />

	<spring:message code="administrator.supplier.surname" var="surname" />
	<display:column title="${surname}" property="surname" sortable="true" />
	
</display:table>

<br />

<!-- The name of the suppliers who offers less items -->

<h3><spring:message code="administrator.dashboard.req7" /></h3>

<display:table name="suppliersLess" id="supplierLess" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="administrator.customer.name" var="name" />
	<display:column title="${name}" property="name" sortable="true" />

	<spring:message code="administrator.customer.surname" var="surname" />
	<display:column title="${surname}" property="surname" sortable="true" />
	
</display:table>

<br />

<!-- A list of consumer, together with the number of requests that he or she's created --> 

<h3><spring:message code="administrator.dashboard.req8" /></h3>

<table>
	<tr>
		<spring:message code="administrator.consumer.name" var="name" />
		<th>${name}</th>
    	<spring:message code="administrator.consumer.surname" var="surname" />
    	<th>${surname}</th>
    	<spring:message code="administrator.consumer.number.requests" var="request" />
    	<th>${request}</th>
  	</tr>
	<jstl:forEach var="p" items="${consumerRequests}" varStatus="status">
  		<tr>
	        <td>${p.name}</td>
	        <td>${p.surname}</td>
	        <td>${numberRequestsInteger[status.index]}</td>
  		</tr>
	</jstl:forEach>	
</table>

<br />

<!-- The list of contracts that have not been signed yet-->

<h3><spring:message code="administrator.dashboard.req9" /></h3>

<display:table name="contracts" id="contract" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="administrator.contract.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" sortable="true" />

	<spring:message code="administrator.contract.contractHolderDateSign" var="contractHolderDateSign" />
	<display:column title="${contractHolderDateSign}" property="contractHolderDateSign" sortable="true" />
	
	<spring:message code="administrator.contract.contractorDateSign" var="contractorDateSign" />
	<display:column title="${contractorDateSign}" property="contractorDateSign" sortable="true" />
	
	<spring:message code="administrator.contract.startingDate" var="startingDate" />
	<display:column title="${startingDate}" property="startingDate" sortable="true" />
	
	<spring:message code="administrator.contract.endingDate" var="endingDate" />
	<display:column title="${endingDate}" property="endingDate" sortable="true" />
	
</display:table>

<br />

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>
