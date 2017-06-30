<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long trainingCourseId = ParamUtil.getLong(request, "trainingCourseId");

TrainingCourse trainingCourse = null;

try {
	trainingCourse = TrainingCourseLocalServiceUtil.getTrainingCourse(trainingCourseId);
}
catch (NoSuchTrainingCourseException nstce) {
}
%>

<portlet:actionURL name="updateTrainingCourse" var="updateTrainingCourseURL">
	<portlet:param name="mvcPath" value="/admin/edit_training_course.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateTrainingCourseURL %>" method="post" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="trainingCourseId" type="hidden" value="<%= trainingCourseId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="course"
	/>

	<liferay-ui:error exception="<%= TrainingCourseNameException.class %>" message="please-enter-a-valid-name" />

	<aui:model-context bean="<%= trainingCourse %>" model="<%= TrainingCourse.class %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="name" />
		</td>
		<td>
			<aui:input label="" name="name" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="description" />
		</td>
		<td>
			<aui:input label="" name="description" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="credits" />
		</td>
		<td>
			<aui:input label="" name="creditAmount" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="course-url" />
		</td>
		<td>
			<aui:input label="" name="courseURL" />
		</td>
	</tr>
	<tr>
		<td>
			<liferay-ui:message key="archived" />
		</td>
		<td>
			<aui:input label="" name="archived" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<br />
		</td>
	</tr>
	</table>

	<br />

	<input type="submit" value="<liferay-ui:message key="save" />" />

	<input onClick="location.href = '<%= HtmlUtil.escape(backURL) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
</aui:form>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>