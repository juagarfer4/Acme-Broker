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

<display:table name="items" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="item.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="item.code" var="code" />
	<display:column title="${code}" property="code" />
	
	<spring:message code="item.description" var="description" />
	<display:column title="${description}" property="description"/>
	
	<spring:message code="item.price" var="price" />
	<display:column title="${price}" property="price"/>
	
	<jstl:if test="${myItems != true}">
		<spring:message code="item.supplier.name" var="name" />
		<display:column title="${name}" property="supplier.name"/>
	</jstl:if>
	
	<security:authorize access="hasRole('SUPPLIER')">
	<jstl:if test="${myItems == true}">
		<display:column><a href="item/supplier/edit.do?itemId=${row.id}"><input type="button" name="create"
		value="<spring:message code="item.edit"/>" /></a></display:column>
	</jstl:if>
	</security:authorize>

</display:table>

<br />

<security:authorize access="hasRole('SUPPLIER')">
	<jstl:if test="${myItems == true}">
		<a href="item/supplier/create.do"><input type="button" name="create"
		value="<spring:message code="item.create"/>" /></a>
	</jstl:if>
</security:authorize>

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="item.back"/>" /></a>
