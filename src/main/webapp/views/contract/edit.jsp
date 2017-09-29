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

<form:form action="${actionURI}" modelAttribute="contract">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="creationMoment" />
	<form:hidden path="contractHolder" />
	<form:hidden path="request" />
	
	<form:hidden path="startingDate" />
	<form:hidden path="endingDate" />
	<form:hidden path="contractHolderDateSign" />
	<form:hidden path="contractorDateSign" />
	
	
	<security:authorize access="hasRole('CONSUMER')" >
	<form:hidden path="contractor" /> 
	<form:hidden path="code" /> 
	<form:hidden path="description" /> 
	
		<input type="submit" name="save" value="<spring:message code="contract.sign"/>" />
		
		&nbsp;
		
	</security:authorize>
	
	<security:authorize access="hasRole('SUPPLIER')" >
	<form:hidden path="contractor" />
	<form:hidden path="code" /> 
	<form:hidden path="description" /> 
	
		<input type="submit" name="save" value="<spring:message code="contract.sign"/>" />
	
		&nbsp;
	</security:authorize>
	
	<security:authorize access="hasRole('ADMIN')" >
			
			<form:label path="code">
				<spring:message code="contract.code" />
			</form:label>
			<form:input path="code" />
			<form:errors cssClass="error" path="code" />
			
			<br />
			
			<form:label path="description">
				<spring:message code="contract.description" />
			</form:label>
			<form:textarea path="description"/>
			<form:errors cssClass="error" path="description" />
	
			<br />
			
			<form:label path="contractor">
				<spring:message code="contract.contractor" />
			</form:label>
			<form:select path="contractor" >
				<form:option label="-----------" value="0"/>
				<form:options items="${contractors}" itemLabel="companyName" itemValue="id" />
			</form:select>
			<form:errors cssClass="error" path="contractor" />
			
			<br />
		
	<input type="submit" name="save" value="<spring:message code="contract.save"/>" />
	
	&nbsp;
	
		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="contract.cancel"/>" /></a>
	</security:authorize>
	
	<security:authorize access="hasRole('CONSUMER')" >
	 <a href="contract/consumer/list.do"><input type="button" name="cancel"
		value="<spring:message code="contract.cancel"/>" /></a>
	</security:authorize>
	
	<security:authorize access="hasRole('SUPPLIER')" >
	 <a href="contract/supplier/list.do"><input type="button" name="cancel"
		value="<spring:message code="contract.cancel"/>" /></a>
	</security:authorize>
</form:form>
