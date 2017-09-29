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

<form:form action="${actionURI}" modelAttribute="consumer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	
	<form:hidden path="requests" />
	<form:hidden path="contracts" />
	


	<form:label path="userAccount.username">
		<spring:message code="consumer.userAccount.username" />
	</form:label>
	<form:input path="userAccount.username" />	
	<form:errors class="error" path="userAccount.username" />
	<br />

	<form:label path="userAccount.password">
		<spring:message code="consumer.userAccount.password" />
	</form:label>
	<form:password path="userAccount.password" />	
	<form:errors class="error" path="userAccount.password" />
	<br />
	
	<form:label path="name">
		<spring:message code="consumer.name" />
	</form:label>
	<form:input path="name" />
	<form:errors cssClass="error" path="name"/>
	<br />
	
	<form:label path="surname">
		<spring:message code="consumer.surname" />
	</form:label>
	<form:input path="surname" />
	<form:errors cssClass="error" path="surname"/>	
	<br />
	
	<form:label path="email">
		<spring:message code="consumer.email" />
	</form:label>
	<form:input path="email" />
	<form:errors cssClass="error" path="email"/>
	<br />
	
	<form:label path="companyName">
		<spring:message code="consumer.companyName" />
	</form:label>
	<form:input path="companyName" />
	<form:errors cssClass="error" path="companyName"/>
	<br />
	
	<form:label path="ticker">
		<spring:message code="consumer.ticker" />
	</form:label>
	<form:input path="ticker" />
	<form:errors cssClass="error" path="ticker"/>
	<br />
	
	<h3><spring:message code="consumer.creditCard.title" /></h3>
	
	<form:label path="creditCard.holderName">
		<spring:message code="consumer.creditCard.holderName" />
	</form:label>
	<form:input path="creditCard.holderName" />
	<form:errors cssClass="error" path="creditCard.holderName"/>
	<br />
	
	<form:label path="creditCard.brandName">
		<spring:message code="consumer.creditCard.brandName" />
	</form:label>
	<form:input path="creditCard.brandName" />
	<form:errors cssClass="error" path="creditCard.brandName"/>
	<br />
	
	<form:label path="creditCard.expirationMonth">
		<spring:message code="consumer.creditCard.expirationMonth" />
	</form:label>
	<form:select path="creditCard.expirationMonth" >
		<form:option label="----" value="0" />
		<form:option label="1" value="1"></form:option>
		<form:option label="2" value="2"></form:option>
		<form:option label="3" value="3"></form:option>
		<form:option label="4" value="4"></form:option>
		<form:option label="5" value="5"></form:option>
		<form:option label="6" value="6"></form:option>
		<form:option label="7" value="7"></form:option>
		<form:option label="8" value="8"></form:option>
		<form:option label="9" value="9"></form:option>
		<form:option label="10" value="10"></form:option>
		<form:option label="11" value="11"></form:option>
		<form:option label="12" value="12"></form:option>
	</form:select>
	<form:errors cssClass="error" path="creditCard.expirationMonth"/>
	<br />
	
	<form:label path="creditCard.expirationYear">
		<spring:message code="consumer.creditCard.expirationYear" />
	</form:label>
	<form:input path="creditCard.expirationYear" />
	<form:errors cssClass="error" path="creditCard.expirationYear"/>
	<br />
	
	<form:label path="creditCard.cVVCode">
		<spring:message code="consumer.creditCard.cVVCode" />
	</form:label>
	<form:input path="creditCard.cVVCode" />
	<form:errors cssClass="error" path="creditCard.cVVCode"/>
	<br />
	
	<form:label path="creditCard.cardNumber">
		<spring:message code="consumer.creditCard.cardNumber" />
	</form:label>
	<form:input path="creditCard.cardNumber" />
	<form:errors cssClass="error" path="creditCard.cardNumber"/>
	<br />
	
	<input type="submit" name="save" value="<spring:message code="consumer.register" />" />
	&nbsp;
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="consumer.cancel"/>" /></a>
	
</form:form>
