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

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

Map<String, List<AccountEnvironment>> accountEnvironmentsMap = AccountEnvironmentServiceUtil.getAccountEnvironmentsMap(accountEntryId);

for (String productEntryName : accountEnvironmentsMap.keySet()) {
%>

	<h1 class="section-heading">
		<%= HtmlUtil.escape(productEntryName) %>
	</h1>

	<%
	List<AccountEnvironment> accountEnvironments = accountEnvironmentsMap.get(productEntryName);

	for (AccountEnvironment accountEnvironment : accountEnvironments) {
	%>

		<div class="account-environment">
			<div class="clearfix">
				<div class="content-column-content">
					<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

					<%= HtmlUtil.escape(accountEnvironment.getName()) %>
				</div>
			</div>

			<br />

			<div class="clearfix">
				<div class="content-column w33">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up"><liferay-ui:message key="liferay-version" />:</span>

						<%= LanguageUtil.get(request, accountEnvironment.getEnvLFRLabel()) %>

						<c:if test="<%= Validator.isNotNull(accountEnvironment.getSupportPhaseLabel()) %>">
							<span class="support-phase-label">(<%= LanguageUtil.get(request, accountEnvironment.getSupportPhaseLabel()) %>)</span>
						</c:if>

						<br />

						<span class="txt-b txt-up"><liferay-ui:message key="operating-system" />:</span>

						<%= LanguageUtil.get(request, accountEnvironment.getEnvOSLabel()) %>

						<c:if test="<%= Validator.isNotNull(accountEnvironment.getEnvOSCustom()) %>">
							- <%= HtmlUtil.escape(accountEnvironment.getEnvOSCustom()) %>
						</c:if>
					</div>
				</div>

				<div class="content-column w33">
					<div class="content-column-content middle-column">
						<span class="txt-b txt-up"><liferay-ui:message key="application-server" />:</span>

						<%= LanguageUtil.get(request, accountEnvironment.getEnvASLabel()) %>

						<br />

						<span class="txt-b txt-up"><liferay-ui:message key="java-virtual-machine" />:</span>

						<%= LanguageUtil.get(request, accountEnvironment.getEnvJVMLabel()) %>
					</div>
				</div>

				<div class="content-column w33">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up"><liferay-ui:message key="database" />:</span>

						<%= LanguageUtil.get(request, accountEnvironment.getEnvDBLabel()) %>
					</div>
				</div>
			</div>

			<div class="clearfix">
				<div class="content-column-content">
					<div class="pull-left">
						<span class="txt-b txt-up"><liferay-ui:message key="portal-ext" />:</span>

						<%
						AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
						%>

						<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(portalExtAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>

						<br />

						<span class="txt-b txt-up"><liferay-ui:message key="patch-level" />:</span>

						<%
						AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
						%>

						<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">

							<%
							LiferayPortletURL accountEnvironmentAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

							accountEnvironmentAttachmentURL.setCopyCurrentRenderParameters(false);
							accountEnvironmentAttachmentURL.setParameter("accountEnvironmentAttachmentId", String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()));
							accountEnvironmentAttachmentURL.setResourceID("accountEnvironmentAttachment");
							%>

							<a href="<%= accountEnvironmentAttachmentURL.toString() %>" target="_blank"><%= HtmlUtil.escape(patchLevelAccountEnvironmentAttachment.getFileName()) %></a>
						</c:if>
					</div>

					<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE) %>">

						<%
						PortletURL editAccountEnvironmentURL = renderResponse.createRenderURL();

						editAccountEnvironmentURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
						editAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
						editAccountEnvironmentURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));
						editAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);

						String editEnvironmentConfigurationOnClick = renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "edit-environment-configuration") + "', '" + editAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "updateAccountEnvironment');";
						%>

						<aui:button cssClass="aui-button-input edit-button" onClick="<%= editEnvironmentConfigurationOnClick %>" value="edit" />
					</c:if>

					<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.DELETE) %>">
						<portlet:actionURL name="deleteAccountEnvironment" var="deleteAccountEnvironmentURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
						</portlet:actionURL>

						<%
						String deleteURL = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-delete-this") +"')) { submitForm(document." + renderResponse.getNamespace() + "fm, '" + deleteAccountEnvironmentURL.toString() + "'); } else { return false; }";
						%>

						<aui:button cssClass="aui-button-input edit-button" onClick="<%= deleteURL %>" value="delete" />
					</c:if>
				</div>
			</div>
		</div>

<%
	}
}
%>

<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) %>">

	<%
	PortletURL addAccountEnvironmentURL = renderResponse.createRenderURL();

	addAccountEnvironmentURL.setParameter("mvcPath", "/support/2/edit_account_environment.jsp");
	addAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
	addAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);

	String addEnvironmentDetailsOnClick = renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "add-environment-details") + "', '" + addAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "updateAccountEnvironment');";
	%>

	<aui:button cssClass="aui-button-input pull-right" onClick="<%= addEnvironmentDetailsOnClick %>" value="add" />
</c:if>

<aui:script>
	function <portlet:namespace />openDialog(title, url, popupId) {
		Liferay.Util.openWindow(
			{
				cache: false,
				dialog: {
					align: Liferay.Util.Window.ALIGN_CENTER,
					centered: true,
					resizable: true
				},
				id: popupId,
				title: title,
				uri: url
			}
		);
	}
</aui:script>