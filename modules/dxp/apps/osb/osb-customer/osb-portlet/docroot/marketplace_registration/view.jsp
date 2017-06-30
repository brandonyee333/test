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

String companyName = ParamUtil.getString(request, "companyName");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_registration/view_company_developer_entries.jsp");
%>

<div class="marketplace-registration-wrapper">

	<%
	long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);
	%>

	<liferay-portlet:renderURL plid="<%= marketplacePlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE %>" var="marketplaceURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
		<portlet:param name="mvcPath" value="/marketplace/view.jsp" />
	</liferay-portlet:renderURL>

	<liferay-ui:header
		backLabel="marketplace"
		backURL="<%= marketplaceURL %>"
		title="create-a-marketplace-developer-account"
	/>

	<p>
		<liferay-ui:message key="thank-you-for-your-interest-in-developing-for-the-liferay-marketplace" />
	</p>

	<div class="marketplace-registration-account-selection">
		<aui:layout cssClass="individual-account">
			<aui:column columnWidth="<%= 70 %>" first="<%= true %>">
				<h3>
					<liferay-ui:message key="individual-account" />
				</h3>

				<p>
					<liferay-ui:message key="choose-this-option-if-you-are-an-individual-or-sole-proprietor" />
				</p>
			</aui:column>

			<aui:column columnWidth="<%= 30 %>" last="<%= true %>">
				<liferay-portlet:renderURL var="developerURL">
					<portlet:param name="mvcPath" value="/marketplace_registration/add_user_developer_entry.jsp" />
					<portlet:param name="backURL" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<c:choose>
					<c:when test="<%= themeDisplay.isSignedIn() %>">
						<aui:button cssClass="fr" onClick="<%= developerURL %>" value="create-individual-account" />
					</c:when>
					<c:otherwise>
						<aui:button cssClass="fr" onClick='<%= themeDisplay.getURLSignIn() + "&redirect=" + HtmlUtil.escapeURL(developerURL) %>' value="create-individual-account" />
					</c:otherwise>
				</c:choose>
			</aui:column>
		</aui:layout>

		<aui:layout cssClass="company-account">
			<aui:column columnWidth="<%= 70 %>" first="<%= true %>">
				<h3>
					<liferay-ui:message key="company-account" />
				</h3>

				<p>
					<liferay-ui:message key="choose-this-option-if-you-are-registering-on-behalf-of-a-company-corporation-limited-liability-company-non-profit-organization-joint-venture-partnership-or-government-organization" />
				</p>
			</aui:column>

			<aui:column columnWidth="<%= 30 %>" last="<%= true %>">
				<liferay-portlet:renderURL secure="<%= request.isSecure() || GetterUtil.getBoolean(PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS)) %>" var="corpURL">
					<portlet:param name="mvcPath" value="/marketplace_registration/view_company_developer_entries.jsp" />
					<portlet:param name="backURL" value="<%= currentURL %>" />
				</liferay-portlet:renderURL>

				<c:choose>
					<c:when test="<%= themeDisplay.isSignedIn() %>">
						<aui:button cssClass="fr" onClick="<%= corpURL %>" value="create-company-account" />
					</c:when>
					<c:otherwise>
						<aui:button cssClass="fr" onClick='<%= themeDisplay.getURLSignIn() + "&redirect=" + HtmlUtil.escapeURL(corpURL) %>' value="create-company-account" />
					</c:otherwise>
				</c:choose>
			</aui:column>
		</aui:layout>
	</div>

	<div class="clear"></div>
</div>