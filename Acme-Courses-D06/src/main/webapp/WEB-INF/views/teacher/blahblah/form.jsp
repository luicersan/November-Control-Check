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
	<acme:input-textbox readonly="true" code="teacher.blahblah.form.label.ticker" path="ticker"/>
	<acme:input-moment readonly="true" code="teacher.follow-up.form.label.creation-moment" path="creationMoment"/>
	<acme:input-textarea code="teacher.blahblah.form.label.caption" path="caption"/>
	<acme:input-textarea code="teacher.blahblah.form.label.summary" path="summary"/>
	<acme:input-moment code="teacher.blahblah.form.label.initDate" path="initDate"/>
	<acme:input-moment code="teacher.blahblah.form.label.finishDate" path="finishDate"/>
	<acme:input-money code="teacher.blahblah.form.label.cost" path="cost"/>
	<acme:input-url code="teacher.blahblah.form.label.hlink" path="hlink"/>
</acme:form>
