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


<form:form action="${actionURI}" modelAttribute="item">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="supplier" />

	<form:label path="code">
		<spring:message code="item.code" />
	</form:label>
	<form:input path="code" />
	<form:errors cssClass="error" path="code" />
	
	<br />
	
	<form:label path="name">
		<spring:message code="item.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name" />
	
	<br />

	<form:label path="description">
		<spring:message code="item.description" />
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description" />
	
	<br />
	
	<form:label path="price">
		<spring:message code="item.price" />
	</form:label>
	<form:input path="price" />
	<form:errors cssClass="error" path="price" />
	
	<br />
	
	<input type="submit" name="save" value="<spring:message code="item.save"/>" />
	
	&nbsp;
	<jstl:if test="${item.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="item.delete"/>" />
	</jstl:if>
	
	&nbsp;
	
	 <a href="item/supplier/list.do"><input type="button" name="cancel"
		value="<spring:message code="item.cancel"/>" /></a>
	
</form:form>
