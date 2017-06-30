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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "preview");

String redirect = ParamUtil.getString(request, "redirect");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();
AppVersion actionableAppVersion = appEntry.getActionableAppVersion();

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_admin/review_app_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<div class="review-app-entry">
	<c:if test='<%= SessionMessages.contains(portletSession, "updateAssetListFailed") %>'>
		<div class="portlet-msg-alert">
			<liferay-ui:message key="the-app-was-approved-but-there-was-an-error-adding-the-app-to-a-featured-list" />
		</div>
	</c:if>

	<liferay-ui:header
		backURL="<%= redirect %>"
		title='<%= LanguageUtil.format(pageContext, "review-app-x", appEntry.getTitle()) %>'
	/>

	<liferay-ui:error exception="<%= AppPackageLicenseException.class %>" message="unable-to-update-the-app-with-the-required-licensing-component" />
	<liferay-ui:error exception="<%= DeveloperEntrySubscriptionStatusException.class %>" message="the-developer-must-first-be-approved-to-submit-paid-apps" />

	<div class="status-indicator status-<%= WorkflowConstants.toLabel(actionableAppVersion.getStatus()) %>">
		<liferay-ui:message key="<%= appEntry.getStatusLabel() %>" />

		<c:if test="<%= appEntry.getStatus() != actionableAppVersion.getStatus() %>">
			(<liferay-ui:message key="<%= actionableAppVersion.getStatusLabel() %>" />)
		</c:if>
	</div>

	<%
	String tabs1Names = "preview,details,pacl-properties,pricing";

	if (RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_MARKETPLACE_ADMIN_ID)) {
		tabs1Names = "preview,details,pacl-properties,licenses,pricing";
	}
	%>

	<liferay-ui:tabs
		names="<%= tabs1Names %>"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<%
	DeveloperEntry developerEntry = appEntry.getDeveloperEntry();
	%>

	<c:if test="<%= !appEntry.isFree() && (developerEntry.isSubscriptionExpired() || (developerEntry.getSubscriptionStatus() != WorkflowConstants.STATUS_APPROVED)) %>">
		<div class="portlet-msg-error">
			<liferay-ui:message key="the-developer-must-first-be-approved-to-submit-paid-apps" /><br />

			<liferay-portlet:renderURL var="reviewDeveloperEntryURL">
				<portlet:param name="mvcPath" value="/marketplace_admin/review_developer_entry.jsp" />
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntry.getDeveloperEntryId()) %>" />
			</liferay-portlet:renderURL>

			<%
			String taglibReviewDeveloperEntry = "<a href=\"" + reviewDeveloperEntryURL.toString() + "\">";
			%>

			<liferay-ui:message arguments='<%= new String[] {taglibReviewDeveloperEntry, "</a>"} %>' key="you-may-approve-the-developer-status-in-the-review-developer-page" />
		</div>
	</c:if>

	<c:choose>
		<c:when test='<%= tabs1.equals("details") %>'>
			<%@ include file="/marketplace_admin/app_entry_details.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("licenses") %>'>
			<%@ include file="/marketplace_admin/app_entry_licenses.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("pacl-properties") %>'>
			<%@ include file="/marketplace_admin/app_entry_pacl_properties.jspf" %>
		</c:when>
		<c:when test='<%= tabs1.equals("pricing") %>'>
			<%@ include file="/marketplace_admin/app_entry_pricing.jspf" %>
		</c:when>
		<c:otherwise>
			<%@ include file="/marketplace_admin/app_entry_preview.jspf" %>
		</c:otherwise>
	</c:choose>

	<c:if test='<%= OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.ADMIN_APP) && (tabs1.equals("details") || tabs1.equals("pacl-properties") || tabs1.equals("preview")) %>'>
		<liferay-portlet:actionURL name="updateAppEntryStatus" varImpl="updateAppEntryStatusURL">
			<portlet:param name="mvcPath" value="/marketplace_admin/review_app_entry.jsp" />
			<portlet:param name="redirect" value="<%= redirect %>" />
			<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
		</liferay-portlet:actionURL>

		<aui:form action="<%= updateAppEntryStatusURL.toString() %>" cssClass="status-form" method="post" name="fm">
			<aui:input name="status" type="hidden" value="<%= String.valueOf(WorkflowConstants.STATUS_DENIED) %>" />

			<aui:fieldset cssClass="aui-helper-hidden status-message-container">
				<div class="field-header">
					<h3>
						<liferay-ui:message key="message" />: <span class="status-label"><liferay-ui:message key="deny" /></span>
					</h3>
				</div>

				<aui:input label="" name="statusMessage" type="textarea" />

				<aui:button-row>
					<aui:button type="submit" value="deny" />

					<aui:button cssClass="cancel-button" value="cancel" />
				</aui:button-row>
			</aui:fieldset>

			<aui:button-row>
				<liferay-portlet:actionURL name="sendManualQAAppVersion" var="sendManualQAAppVersionURL">
					<portlet:param name="mvcPath" value="/marketplace_admin/review_app_entry.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" />
				</liferay-portlet:actionURL>

				<aui:button href="<%= sendManualQAAppVersionURL %>" value="upload-to-files-liferay-com" />
			</aui:button-row>

			<c:if test="<%= actionableAppVersion.isPending() %>">
				<aui:button-row>

					<%
					AppFlag appFlag = AppFlagLocalServiceUtil.fetchAppFlag(actionableAppVersion.getAppVersionId(), AppFlagConstants.TYPE_AWAITING_RESPONSE);
					%>

					<c:if test="<%= appFlag != null %>">
						<div class="portlet-msg-alert">
							<liferay-ui:message key="awaiting-response-from-the-developer" />
						</div>
					</c:if>

					<liferay-portlet:actionURL name="updateAwaitingResponseAppFlag" var="updateAppFlagURL">
						<portlet:param name="mvcPath" value="/marketplace_admin/review_app_entry.jsp" />
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
					</liferay-portlet:actionURL>

					<aui:button href="<%= updateAppFlagURL %>" value='<%= (appFlag == null) ? "flag-app-as-awaiting-response" : "unflag-app-as-awaiting-response" %>' />
				</aui:button-row>
			</c:if>

			<aui:button-row cssClass="action-buttons-container">
				<c:choose>
					<c:when test="<%= (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_PENDING_QA) || ((actionableAppVersion.getStatus() == WorkflowConstants.STATUS_PENDING) && !_hasNewAppPackages(approvedAppVersion, actionableAppVersion)) %>">

						<%
						updateAppEntryStatusURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
						%>

						<aui:button href="<%= updateAppEntryStatusURL.toString() %>" value="approve" />
					</c:when>
					<c:when test="<%= actionableAppVersion.getStatus() == WorkflowConstants.STATUS_PENDING %>">

						<%
						updateAppEntryStatusURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_PENDING_QA));
						%>

						<aui:button href="<%= updateAppEntryStatusURL.toString() %>" value="send-to-manual-and-auto-qa" />
					</c:when>
					<c:when test="<%= actionableAppVersion.getStatus() == WorkflowConstants.STATUS_PENDING_AUTO_QA %>">

						<%
						updateAppEntryStatusURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_PENDING_QA));
						%>

						<aui:button href="<%= updateAppEntryStatusURL.toString() %>" value="send-to-qa" />
					</c:when>
					<c:when test="<%= (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED) || (appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">

						<%
						if (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED) {
							updateAppEntryStatusURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_PENDING));
						}
						else if (approvedAppVersion != null) {
							updateAppEntryStatusURL.setParameter("status", String.valueOf(WorkflowConstants.STATUS_APPROVED));
						}
						%>

						<aui:button href="<%= updateAppEntryStatusURL.toString() %>" value='<%= (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED) ? "restore-denied-app" : "restore-rejected-app" %>' />
					</c:when>
				</c:choose>

				<c:if test="<%= appEntry.isApproved() || actionableAppVersion.isPending() %>">
					<aui:button cssClass="deny-button" value='<%= actionableAppVersion.isPending() ? "deny-app" : "reject-app" %>' />
				</c:if>
			</aui:button-row>
		</aui:form>

		<aui:script use="aui-base">
			var form = A.one('#<portlet:namespace />fm');

			var statusMessageContainer = form.one('.status-message-container');
			var actionButtonsContainer = form.one('.action-buttons-container');

			actionButtonsContainer.one('.deny-button').on(
				'click',
				function(event) {
					var button = event.currentTarget.one('input');

					statusMessageContainer.show();
					actionButtonsContainer.hide();

					statusMessageContainer.scrollIntoView();
				},
				'.status-message-button'
			);

			statusMessageContainer.one('.cancel-button input').on(
				'click',
				function(event) {
					statusMessageContainer.hide();
					actionButtonsContainer.show();
				}
			);
		</aui:script>
	</c:if>
</div>

<%!
private boolean _hasNewAppPackages(AppVersion approvedAppVersion, AppVersion actionableAppVersion) throws Exception {
	if (!actionableAppVersion.isPending()) {
		return false;
	}

	if (actionableAppVersion.getReleaseType() != AppVersionConstants.RELEASE_TYPE_METADATA) {
		return true;
	}

	if (approvedAppVersion.getAppVersionId() == actionableAppVersion.getAppVersionId()) {
		return false;
	}

	int actionableAppPackagesCount = AppPackageLocalServiceUtil.getAppPackagesCount(actionableAppVersion.getAppVersionId());
	int approvedAppPackagesCount = AppPackageLocalServiceUtil.getAppPackagesCount(approvedAppVersion.getAppVersionId());

	if (actionableAppPackagesCount != approvedAppPackagesCount) {
		return true;
	}
	else {
		return false;
	}
}
%>