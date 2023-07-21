<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String commandOutput = (String)SessionMessages.get(renderRequest, "commandOutput");
%>

<portlet:actionURL name="executeCommand" var="executeCommandURL" />

<div class="container-fluid-1280">
	<aui:form action="<%= executeCommandURL %>" method="post" name="fm" onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "executeCommand();" %>'>
		<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />

		<liferay-ui:error key="gogo">

			<%
			Exception e = (Exception)errorException;
			%>

			<%= HtmlUtil.escape(e.getMessage()) %>
		</liferay-ui:error>

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input autoFocus="<%= windowState.equals(WindowState.MAXIMIZED) || windowState.equals(LiferayWindowState.POP_UP) %>" name="command" prefix='<%= (String)SessionMessages.get(renderRequest, "prompt") %>' value='<%= (String)SessionMessages.get(renderRequest, "command") %>' />
			</aui:fieldset>
		</aui:fieldset-group>

		<c:if test="<%= Validator.isNotNull(commandOutput) %>">
			<b><liferay-ui:message key="output" /></b>

			<pre><%= commandOutput %></pre>
		</c:if>

		<aui:button-row>
			<aui:button cssClass="btn-lg" primary="<%= true %>" type="submit" value="execute" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />executeCommand() {
		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>