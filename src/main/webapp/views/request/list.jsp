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
	
<display:table name="requests" id="row" requestURI="request/consumer/list.do" pagesize="5" class="displaytag">

	
	<spring:message code="requestt.code" var="code" />
	<display:column title="${code}" property="code" />
	
	<spring:message code="requestt.startingDate" var="startingDate" />
	<display:column title="${startingDate}" property="startingDate"  format="{0,date,dd/MM/yyyy}" sortable="true" />

	<spring:message code="requestt.endingDate" var="endingDate" />
	<display:column title="${endingDate}" property="endingDate"  format="{0,date,dd/MM/yyyy}" sortable="true" />
	
	<spring:message code="requestt.isCancelled" var="isCancelled" />
	<display:column title="${isCancelled}" property="isCancelled" sortable="true" />
	
	<security:authorize access="hasRole('CONSUMER')">
			<jstl:if test="${requestt.isCancelled==false && ((row.contract.contractHolderDateSign == null) || (requestt.contract.contractorDateSign == null))}">
		<display:column><a href="request/consumer/edit.do?requesttId=${row.id}"><input type="button" name="create"
			value="<spring:message code="requestt.cancelrequest"/>" /></a></display:column>
			</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')">
	<display:column><a href="contract/administrator/create.do?requestId=${row.id}"><input type="button" name="create"
		value="<spring:message code="contract.create"/>" /></a></display:column>
	</security:authorize>
	
</display:table>

<div>
<jstl:if test="${cancelRequest != true}">
		<a href="request/consumer/create.do"><input type="button" name="create"
		value="<spring:message code="requestt.create"/>" /></a>
</jstl:if>

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="request.back"/>" /></a>
</div>
