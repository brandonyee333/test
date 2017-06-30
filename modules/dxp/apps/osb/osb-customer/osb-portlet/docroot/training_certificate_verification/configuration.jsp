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
String tabs1 = ParamUtil.getString(request, "tabs1", "certificate-verification-type");

String redirect = ParamUtil.getString(request, "redirect");
%>

<liferay-portlet:renderURL portletConfiguration="true" var="portletURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="redirect" value="<%= redirect %>" />
</liferay-portlet:renderURL>

<liferay-ui:tabs
	names="verification"
	param="tabs1"
	url="<%= portletURL %>"
/>

<liferay-portlet:actionURL portletConfiguration="true" var="configurationURL" />

<aui:form action="<%= configurationURL %>" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="tabs1" type="hidden" value="<%= tabs1 %>" />

	<table class="lfr-table">
	<tr>
		<td>
			<liferay-ui:message key="training-certificate-type" />
		</td>
		<td>

			<%
			int trainingCertificateType = PrefsParamUtil.getInteger(portletPreferences, request, "trainingCertificateType");
			%>

			<aui:select label="" name="trainingCertificateType">
				<aui:option label="<%= TrainingCertificateTemplateConstants.getTypeLabel(TrainingCertificateTemplateConstants.TYPE_TRAINING_EVENT) %>" selected="<%=TrainingCertificateTemplateConstants.TYPE_TRAINING_EVENT == trainingCertificateType %>" value="<%= TrainingCertificateTemplateConstants.TYPE_TRAINING_EVENT %>" />
				<aui:option label="<%= TrainingCertificateTemplateConstants.getTypeLabel(TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM) %>" selected="<%= TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM == trainingCertificateType %>" value="<%= TrainingCertificateTemplateConstants.TYPE_TRAINING_EXAM %>" />
			</aui:select>
		</td>
	</tr>
	</table>

	<input type="submit" value="<liferay-ui:message key="save" />" />
</aui:form>