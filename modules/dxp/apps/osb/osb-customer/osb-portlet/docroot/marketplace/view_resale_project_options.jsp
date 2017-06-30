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

<%@ include file="/marketplace/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

long appEntryId = ParamUtil.getLong(request, "appEntryId");
long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");
%>

<div class="marketplace view-resale-project-options" id="<portlet:namespace />viewResaleProjectOptions">
	<liferay-ui:header
		title="reseller-purchasing-on-behalf-of-an-end-user"
	/>

	<div class="resale-options">
		<a class="resale-option new-project" href="javascript:<portlet:namespace />createResaleProject();">
			<p>
				<liferay-ui:message key="create-a-new-project-for-my-client" />
			</p>
		</a>

		<portlet:renderURL var="purchaseAppEntryExistingProjectURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="<%= mvcPathParam %>" value="/marketplace/purchase_app_entry.jsp" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			<portlet:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			<portlet:param name="resale" value="<%= String.valueOf(true) %>" />
			<portlet:param name="purchaseStep" value="project" />
		</portlet:renderURL>

		<a class="resale-option existing-project" href="<%= purchaseAppEntryExistingProjectURL %>">
			<p>
				<liferay-ui:message key="use-an-existing-project-for-my-client" />
			</p>
		</a>
	</div>
</div>

<aui:script>
	function <portlet:namespace />createResaleProject() {
		var A = AUI();

		A.DialogManager.closeByChild('#<portlet:namespace />viewResaleProjectOptions');

		var width = 435;

		var popup = new A.Dialog(
			{
				destroyOnClose: true,
				draggable: true,
				modal: true,
				resizable: false,
				stack: true,
				title: '<liferay-ui:message key="new-project" />',
				width: width,
				x: document.documentElement.clientWidth/2 - width/2,
				y: 300
			}
		).plug(
			A.Plugin.IO,
			{
				data: {
					<portlet:namespace />redirect: '<%= purchaseAppEntryExistingProjectURL %>'
				},
				uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="<%= mvcPathParam %>" value="/marketplace/edit_corp_project.jsp" /></liferay-portlet:renderURL>'
			}
		).render();
	}
</aui:script>