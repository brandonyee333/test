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

LinkedHashMap params = new LinkedHashMap();

params.put("validTicket", StringPool.BLANK);

List<OfferingEntryGroup> offeringEntryGroups = SupportUtil.getOfferingEntryGroups(0, accountEntryId, new int[0], new int[] {OfferingEntryConstants.STATUS_ACTIVE, OfferingEntryConstants.STATUS_ON_HOLD}, 0, 0, 0, 0, 0, 0, params, true);

for (OfferingEntryGroup offeringEntryGroup : offeringEntryGroups) {
	ProductEntry curProductEntry = offeringEntryGroup.getProductEntry();
	OfferingEntry curOfferingEntry = offeringEntryGroup.getAvailableSupportOfferingEntry();

	List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntryId, curProductEntry.getProductEntryId());
%>

	<c:if test="<%= !accountEnvironments.isEmpty() %>">
		<h1 class="section-heading">
			<%= HtmlUtil.escape(curProductEntry.getName()) %>
		</h1>
	</c:if>

	<%
	for (AccountEnvironment accountEnvironment : accountEnvironments) {
	%>

		<div class="account-environment callout-a">
			<div class="aui-helper-clearfix callout-content">
				<div class="content-column-content">
					<span class="txt-b txt-up"><liferay-ui:message key="name" />:</span>

					<%= HtmlUtil.escape(accountEnvironment.getName()) %>
				</div>
			</div>

			<br />

			<div class="aui-helper-clearfix callout-content">
				<div class="aui-w33 content-column">
					<div class="content-column-content left-column">
						<span class="txt-b txt-up"><liferay-ui:message key="liferay-version" />:</span>

						<%= LanguageUtil.get(pageContext, accountEnvironment.getEnvLFRLabel()) %>

						<c:if test="<%= Validator.isNotNull(accountEnvironment.getSupportPhaseLabel()) %>">
							<span class="support-phase-label">(<%= LanguageUtil.get(pageContext, accountEnvironment.getSupportPhaseLabel()) %>)</span>
						</c:if>

						<br />

						<span class="txt-b txt-up"><liferay-ui:message key="operating-system" />:</span>

						<%= LanguageUtil.get(pageContext, accountEnvironment.getEnvOSLabel()) %>

						<c:if test="<%= Validator.isNotNull(accountEnvironment.getEnvOSCustom()) %>">
							- <%= HtmlUtil.escape(accountEnvironment.getEnvOSCustom()) %>
						</c:if>
					</div>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content middle-column">
						<span class="txt-b txt-up"><liferay-ui:message key="application-server" />:</span>

						<%= LanguageUtil.get(pageContext, accountEnvironment.getEnvASLabel()) %>

						<br />

						<span class="txt-b txt-up"><liferay-ui:message key="java-virtual-machine" />:</span>

						<%= LanguageUtil.get(pageContext, accountEnvironment.getEnvJVMLabel()) %>
					</div>
				</div>

				<div class="aui-w33 content-column">
					<div class="content-column-content right-column">
						<span class="txt-b txt-up"><liferay-ui:message key="database" />:</span>

						<%= LanguageUtil.get(pageContext, accountEnvironment.getEnvDBLabel()) %>
					</div>
				</div>
			</div>

			<div class="aui-helper-clearfix callout-content">
				<div class="content-column-content">
					<div class="fl">
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

						editAccountEnvironmentURL.setParameter("mvcPath", "/support/edit_account_environment.jsp");
						editAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
						editAccountEnvironmentURL.setParameter("accountEnvironmentId", String.valueOf(accountEnvironment.getAccountEnvironmentId()));
						editAccountEnvironmentURL.setParameter("offeringEntryId", String.valueOf(curOfferingEntry.getOfferingEntryId()));
						editAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
						%>

						<input class="aui-button-input edit-button" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="edit-environment-details" />', '<%= editAccountEnvironmentURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" type="button" value="<liferay-ui:message key="edit" />" />
					</c:if>

					<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.DELETE) %>">
						<portlet:actionURL name="deleteAccountEnvironment" var="deleteAccountEnvironmentURL">
							<portlet:param name="redirect" value="<%= redirect %>" />
							<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
						</portlet:actionURL>

						<%
						String deleteURL = "javascript:if (confirm('" + UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this") +"')) { submitForm(document." + renderResponse.getNamespace() + "fm, '" + deleteAccountEnvironmentURL.toString() + "'); } else { return false; }";
						%>

						<input class="aui-button-input edit-button" onClick="<%= deleteURL %>" type="button" value="<liferay-ui:message key="delete" />" />
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

	addAccountEnvironmentURL.setParameter("mvcPath", "/support/edit_account_environment.jsp");
	addAccountEnvironmentURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
	addAccountEnvironmentURL.setWindowState(LiferayWindowState.POP_UP);
	%>

	<input class="aui-button-input fr" onClick="<portlet:namespace />openDialog('<liferay-ui:message key="add-environment-details" />', '<%= addAccountEnvironmentURL.toString() %>', '<portlet:namespace />updateAccountEnvironment')" type="button" value="<liferay-ui:message key="add" />" />
</c:if>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />openDialog',
		function(title, url, popupId) {
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
		},
		['aui-dialog', 'aui-overlay-manager', 'liferay-util-window']
	);
</aui:script>