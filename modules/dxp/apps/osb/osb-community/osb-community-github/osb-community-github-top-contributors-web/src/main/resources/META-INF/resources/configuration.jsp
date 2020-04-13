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

<div class="configuration">
	<liferay-portlet:actionURL portletConfiguration="<%= true %>" var="configurationURL" />

	<aui:form action="<%= configurationURL %>" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />

		<liferay-portlet:renderURL portletConfiguration="<%= true %>" var="redirect" />

		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

		<aui:select label="open-source-project" name="preferences--docProjectId--" value="<%= docProjectId %>">

			<%
			List<DocProject> docProjects = DocProjectLocalServiceUtil.getDocProjects(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (DocProject docProject : docProjects) {
			%>

				<aui:option label="<%= docProject.getName() %>" value="<%= docProject.getDocProjectId() %>" />

			<%
			}
			%>

		</aui:select>

		<aui:input label="number-of-top-members" name="preferences--contributorsCount--" value="<%= contributorsCount %>" />

		<aui:button-row>
			<aui:button type="submit" />
		</aui:button-row>
	</aui:form>
</div>