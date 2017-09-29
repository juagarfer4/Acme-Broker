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

<form:form action="${actionURI}" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="consumer" />
	<form:hidden path="isCancelled" />

	<jstl:if test="${request.id == 0}">

	<form:label path="code">
		<spring:message code="request.code" />
	</form:label>
	<form:input path="code"/>
	<form:errors cssClass="error" path="code" />
	
	<br />
	
	<form:label path="description">
		<spring:message code="request.description" />
	</form:label>
	<form:textarea path="description"/>
	<form:errors cssClass="error" path="description" />
	
	<br />
	
	<form:label path="startingDate">
		<spring:message code="request.startingDate" />
	</form:label>
	<form:input path="startingDate" />
	<form:errors cssClass="error" path="startingDate" />
	
	<br />
	
	<form:label path="endingDate">
		<spring:message code="request.endingDate" />
	</form:label>
	<form:input path="endingDate" />
	<form:errors cssClass="error" path="endingDate" />
	
	<br />
	
	</jstl:if>
	
	<jstl:if test="${request.id != 0}">
	
	<form:hidden path="code" />
	<form:hidden path="description" />
	<form:hidden path="startingDate" />
	<form:hidden path="endingDate" />
	<form:hidden path="contract" />
	
	<br />
	</jstl:if>
	
	<input type="submit" name="save" value="<spring:message code="request.save"/>" />
	
	&nbsp;
	
	 <a href="request/consumer/list.do"><input type="button" name="cancel"
		value="<spring:message code="request.cancel"/>" /></a>
	
</form:form>
