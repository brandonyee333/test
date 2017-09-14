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

<liferay-portlet:actionURL portletConfiguration="true" var="actionURL" />

<aui:form action="<%= actionURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

	<liferay-ui:error key="jiraProjectKey" message="please-enter-a-valid-jira-project-key" />

	<aui:fieldset>
		<aui:select label="jira-project-key" name="preferences--jiraProjectKey--">

			<%
			for (String curJiraProjectKey : PortletPropsValues.JIRA_PROJECT_KEYS_ALLOWED) {
			%>

				<aui:option label="<%= curJiraProjectKey %>" selected="<%= curJiraProjectKey.equals(jiraProjectKey) %>" value="<%= curJiraProjectKey %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input cssClass="lfr-input-text-container" name="preferences--jiraProjectVersionNamePrefix--" type="text" value="<%= jiraProjectVersionNamePrefix %>" />
	</aui:fieldset>

	<aui:button-row>
		<aui:button type="submit" />
	</aui:button-row>
</aui:form>