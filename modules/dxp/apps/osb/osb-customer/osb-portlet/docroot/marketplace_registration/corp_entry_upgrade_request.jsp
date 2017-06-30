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
long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(corpEntryId);
%>

<c:choose>
	<c:when test="<%= CorpEntryLocalServiceUtil.hasUserCorpEntryRole(themeDisplay.getUserId(), corpEntryId, OSBConstants.ROLE_OSB_CORP_ADMIN_ID) %>">
		<p>
			<liferay-ui:message key="this-company-is-already-registered-as-a-liferay-service-partner.-to-upgrade-this-company-to-be-a-service-partner-and-marketplace-developer-click-register-to-continue" />
		</p>

		<p>
			<liferay-ui:message key="if-you-would-like-to-create-a-new-marketplace-developer-company" />
		</p>

		<liferay-portlet:renderURL var="backURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace_registration/view_company_developer_entries.jsp" />
		</liferay-portlet:renderURL>

		<liferay-portlet:renderURL var="createCorpURL" windowState="<%= LiferayWindowState.NORMAL.toString() %>">
			<portlet:param name="mvcPath" value="/marketplace_registration/edit_company_developer_entry.jsp" />
			<portlet:param name="backURL" value="<%= backURL %>" />
			<portlet:param name="corpEntryId" value="<%= String.valueOf(corpEntryId) %>" />
		</liferay-portlet:renderURL>

		<div class="buttons">
			<aui:button onClick="<%= createCorpURL %>" value="register-your-company" />
		</div>
	</c:when>
	<c:otherwise>
		<p>
			<liferay-ui:message arguments="<%= HtmlUtil.escape(corpEntry.getContactEmailAddress()) %>" key="this-company-is-already-registered-as-a-liferay-service-partner.-requests-to-upgrade-this-company-to-be-a-service-partner-and-marketplace-developer-should-be-submitted-by-your-company-administrator-x" />
		</p>

		<p>
			<liferay-ui:message key="if-you-would-like-to-create-a-new-marketplace-developer-company" />
		</p>
	</c:otherwise>
</c:choose>