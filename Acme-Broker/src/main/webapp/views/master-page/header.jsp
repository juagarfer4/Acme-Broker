<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Broker" />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li>
				<a class="fNiv"><spring:message	code="master.page.profile" />
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="contract/administrator/listcancelled.do"><spring:message code="master.page.list.cancelled.contract" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.dashboard" /></a></li>
					<li><a href="request/administrator/list.do"><spring:message code="master.page.admin.list.requestnotcontract" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('CONSUMER')">
			<li>
				<a class="fNiv"><spring:message	code="master.page.profile" />
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="request/consumer/list.do"><spring:message code="master.page.consumer.list.request" /></a></li>
					<li><a href="contract/consumer/list.do"><spring:message code="master.page.list.contract" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>					
				</ul>
			</li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('SUPPLIER')">
			<li>
				<a class="fNiv"><spring:message	code="master.page.profile" />
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="item/supplier/list.do"><spring:message code="master.page.supplier.list.item" /></a></li>
					<li><a href="contract/supplier/list.do"><spring:message code="master.page.list.contract" /></a></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>					
				</ul>
			</li>
			
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv"><spring:message code="master.page.register" /></a>
			<ul>
					<li class="arrow"></li>
					<li><a href="consumer/create.do"><spring:message code="master.page.consumer.register" /></a></li>
					<li><a href="supplier/create.do"><spring:message code="master.page.supplier.register" /></a></li>
			</ul>
			</li>
		</security:authorize>
		 
		<security:authorize access="isAuthenticated()">
		</security:authorize>
		
		<li><a href="customer/list.do"><spring:message code="master.page.company.list" /></a></li>
		
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>