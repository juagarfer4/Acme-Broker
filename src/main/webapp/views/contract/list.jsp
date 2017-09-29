<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
	
<display:table name="contracts" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="contract.code" var="code" />
	<display:column title="${code}" property="code" />
	
	<spring:message code="contract.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contract.description" var="description" />
	<display:column title="${description}" property="description" />
	
	<spring:message code="contract.contractHolderDateSign" var="contractHolderDateSign" />
	<display:column title="${contractHolderDateSign}" property="contractHolderDateSign" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contract.contractorDateSign" var="contractorDateSign" />
	<display:column title="${contractorDateSign}" property="contractorDateSign"  format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contract.startingDate" var="startingDate" />
	<display:column title="${startingDate}" property="startingDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="contract.endingDate" var="endingDate" />
	<display:column title="${endingDate}" property="endingDate" format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<security:authorize access="hasRole('CONSUMER')">
	<jstl:if test="${row.contractHolderDateSign==null}">
	<display:column><a href="contract/consumer/edit.do?contractId=${row.id}"><input type="button" name="create"
		value="<spring:message code="contract.sign"/>" /></a></display:column>
	</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('SUPPLIER')">
	<jstl:if test="${row.contractorDateSign==null}">
	<display:column><a href="contract/supplier/edit.do?contractId=${row.id}"><input type="button" name="create"
		value="<spring:message code="contract.sign"/>" /></a></display:column>
	</jstl:if>
	</security:authorize>
	
</display:table>

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="contract.back"/>" /></a>