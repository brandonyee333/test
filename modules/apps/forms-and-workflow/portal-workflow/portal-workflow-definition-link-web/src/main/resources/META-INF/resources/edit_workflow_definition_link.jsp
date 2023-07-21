<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String className = ParamUtil.getString(request, "className");
String resource = ParamUtil.getString(request, "resource");

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(resource);
%>

<portlet:actionURL name="updateWorkflowDefinitionLink" var="updateWorkflowDefinitionLinkURL" />

<div class="container-fluid-1280 workflow-definition-link-container" id="<portlet:namespace />formContainer">
	<aui:form action="<%= updateWorkflowDefinitionLinkURL %>" method="post">
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
		<aui:input name="groupId" type="hidden" value="<%= workflowDefinitionLinkDisplayContext.getGroupId() %>" />
		<aui:input name="resource" type="hidden" value="<%= resource %>" />

		<div class="col-xs-4">
			<aui:select label="<%= resource %>" name='<%= "workflowDefinitionName@" + HtmlUtil.escapeAttribute(className) %>' title="workflow-definition">

				<%
				String defaultWorkflowDefinitionLabel = workflowDefinitionLinkDisplayContext.getDefaultWorkflowDefinitionLabel(className);
				%>

				<aui:option><%= HtmlUtil.escape(defaultWorkflowDefinitionLabel) %></aui:option>

				<%
				for (WorkflowDefinition workflowDefinition : workflowDefinitionLinkDisplayContext.getWorkflowDefinitions()) {
				%>

					<aui:option label="<%= HtmlUtil.escape(workflowDefinitionLinkDisplayContext.getWorkflowDefinitionLabel(workflowDefinition)) %>" selected="<%= workflowDefinitionLinkDisplayContext.isWorkflowDefinitionSelected(workflowDefinition, className) %>" value="<%= workflowDefinitionLinkDisplayContext.getWorkflowDefinitionValue(workflowDefinition) %>" />

				<%
				}
				%>

			</aui:select>
		</div>

		<aui:button-row>
			<aui:button cssClass="btn-lg" type="submit" />

			<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>