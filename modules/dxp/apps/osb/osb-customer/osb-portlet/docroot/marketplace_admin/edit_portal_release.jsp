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
String redirect = ParamUtil.getString(request, "redirect");
String backUrl = ParamUtil.getString(request, "backUrl", redirect);

long portalReleaseId = ParamUtil.getLong(request, "portalReleaseId");

PortalRelease portalRelease = null;

portalRelease = PortalReleaseLocalServiceUtil.fetchPortalRelease(portalReleaseId);

if (portalRelease == null) {
	portalRelease = PortalReleaseLocalServiceUtil.createPortalRelease(0);
}
%>

<div class="edit-portal-release">
	<liferay-ui:header
		backURL="<%= backUrl %>"
		title='<%= LanguageUtil.get(pageContext, "portal-release") %>'
	/>

	<portlet:actionURL name="updatePortalRelease" var="updatePortalReleaseURL">
		<portlet:param name="mvcPath" value="/marketplace_admin/edit_portal_release.jsp" />
	</portlet:actionURL>

	<aui:form action="<%= updatePortalReleaseURL %>" method="post" name="fm">
		<aui:input name="backUrl" type="hidden" value="<%= backUrl %>" />
		<aui:input name="portalReleaseId" type="hidden" value="<%= portalRelease.getPortalReleaseId() %>" />

		<liferay-ui:error exception="<%= PortalReleaseBuildNumberException.class %>" message="please-enter-a-valid-build-number" />
		<liferay-ui:error exception="<%= PortalReleaseNameException.class %>" message="please-enter-a-release-name" />

		<aui:model-context bean="<%= portalRelease %>" model="<%= PortalRelease.class %>" />

		<div class="portal-release-edit-form">
			<aui:fieldset>
				<aui:input name="versionName" />

				<aui:input name="buildNumber" />

				<aui:input name="fixPackName" />

				<aui:input name="ee" />

				<aui:input name="marketplaceSupport" />

				<aui:input label="marektplace-support-prevent-new-releases" name="hidden" />

				<aui:input name="osgiSupport" />

				<aui:input name="paclSupport" />

				<aui:button-row>
					<aui:button type="submit" value='<%= (portalReleaseId > 0) ? "save" : "add" %>' />

					<aui:button href="<%= backUrl %>" type="cancel" />
				</aui:button-row>
			</aui:fieldset>
		</div>
	</aui:form>
</div>