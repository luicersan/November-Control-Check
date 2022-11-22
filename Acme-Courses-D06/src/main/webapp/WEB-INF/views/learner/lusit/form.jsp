<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for learner particular
- purposes.  The copyright owner does not offer learner warranties or representations, nor do
- they accept learner liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox readonly="true" code="learner.lusit.form.label.iname" path="iname"/>
	<acme:input-moment readonly="true" code="learner.lusit.form.label.initialDate" path="initialDate"/>
	<acme:input-textarea code="learner.lusit.form.label.logo" path="logo"/>
	<acme:input-textarea code="learner.lusit.form.label.description" path="description"/>
	<acme:input-moment code="learner.lusit.form.label.beginDate" path="beginDate"/>
	<acme:input-moment code="learner.lusit.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="learner.lusit.form.label.budget" path="budget"/>
	<acme:input-url code="learner.lusit.form.label.hlink" path="hlink"/>
</acme:form>
