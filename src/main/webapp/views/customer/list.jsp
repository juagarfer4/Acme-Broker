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

<h3><spring:message code="consumers.list" /></h3>	

<display:table name="consumers" id="consumer" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="consumer.companyName" var="companyName" />
	<display:column title="${companyName}" property="companyName" />

	<spring:message code="consumer.ticker" var="ticker" />
	<display:column title="${ticker}" property="ticker" />
	
	<spring:message code="consumer.email" var="email" />
	<display:column title="${email}" property="email" />

</display:table>

<h3><spring:message code="suppliers.list" /></h3>	

<display:table name="suppliers" id="supplier" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="supplier.companyName" var="companyName" />
	<display:column title="${companyName}" property="companyName" />

	<spring:message code="supplier.ticker" var="ticker" />
	<display:column title="${ticker}" property="ticker" />
	
	<spring:message code="supplier.email" var="email" />
	<display:column title="${email}" property="email" />
	
	<display:column><a href="item/list.do?supplierId=${supplier.id}"><input type="button" name="create"
		value="<spring:message code="supplier.items"/>" /></a></display:column>

</display:table>

<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="customer.back"/>" /></a>
