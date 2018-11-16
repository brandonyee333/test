<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<div class="portlet-configuration-body-content">
	<div class="container-fluid-1280">
		<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationActionURL" />

		<aui:form action="<%= configurationActionURL %>" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

			<liferay-ui:error key="jiraProjectKey" message="please-enter-a-valid-jira-project-key" />

			<aui:fieldset>
				<aui:select label="jira-project-key" name="preferences--jiraProjectKey--">

					<%
					for (String curJiraProjectKey : ReleaseNotesConfigurationValues.JIRA_PROJECT_KEYS_ALLOWED) {
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
	</div>
</div>