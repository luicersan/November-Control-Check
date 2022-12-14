<%--
- input-select.tag
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@tag language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
 
<%@attribute name="path" required="true" type="java.lang.String"%>
<%@attribute name="code" required="true" type="java.lang.String"%>
<%@attribute name="readonly" required="false" type="java.lang.Boolean"%>

<jstl:if test="${readonly == null}">
	<jstl:set var="readonly" value="false"/>
</jstl:if>

<div class="form-group">
	<input id="${path}" name="${path}" type="hidden" value="<jstl:out value="${requestScope[path]}"/>"/>
	<label for="${path}_proxy">
		<acme:message code="${code}"/>
	</label>	
	<select id="${path}_proxy" name="${path}_proxy" class="form-control selectpicker show-tick"
		<jstl:if test="${readonly}">disabled</jstl:if>
		<jstl:if test="${!readonly}">	
			onfocus="javascript: this.size = (this.options.length < 5 ? this.options.length : 5)" 
			onblur="javascript: this.size = 0" 
			onchange="javascript: this.size=1; this.blur(); $('#${path}').val($(this).val())">
		</jstl:if>	
	>
  		<jsp:doBody/>
	</select>
	<acme:show-errors path="${path}"/>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#${path}").val($(this).find(":selected").val());
	})
</script>
