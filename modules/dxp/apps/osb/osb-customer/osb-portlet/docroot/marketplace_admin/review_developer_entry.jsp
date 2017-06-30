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

long developerEntryId = ParamUtil.getLong(request, "developerEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntry(developerEntryId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_admin/review_developer_entry.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("developerEntryId", String.valueOf(developerEntryId));

request.setAttribute("view.jsp-portletURL", portletURL);
%>

<div class="review-developer-entry">
	<liferay-ui:header
		backURL="<%= redirect %>"
		title="<%= developerEntry.getName() %>"
	/>

	<liferay-ui:error exception="<%= CorpEntryDescriptionException.class %>" message="please-enter-a-valid-description" />
	<liferay-ui:error exception="<%= DeveloperEntryDossieraAccountKeyException.class %>" message="please-enter-a-valid-18-character-dossiera-account-key" />
	<liferay-ui:error exception="<%= DuplicateDeveloperEntryDossieraAccountKeyException.class %>" message="dossiera-account-key-is-already-assigned-to-another-developer" />
	<liferay-ui:error exception="<%= DuplicateDeveloperEntryException.class %>" message="company-name-is-already-registered" />

	<div class="status-indicator status-<%= WorkflowConstants.toLabel(developerEntry.getStatus()) %>">
		<liferay-ui:message key="<%= WorkflowConstants.toLabel(developerEntry.getStatus()) %>" />
	</div>

	<liferay-ui:tabs
		names="details,comments"
		param="tabs1"
		url="<%= portletURL.toString() %>"
	/>

	<c:choose>
		<c:when test='<%= tabs1.equals("comments") %>'>
			<liferay-util:include page="/marketplace_admin/comments.jsp" servletContext="<%= application %>">
				<liferay-util:param name="className" value="<%= DeveloperEntry.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(developerEntryId) %>" />
			</liferay-util:include>
		</c:when>
		<c:otherwise>
			<%@ include file="/marketplace_admin/developer_entry_details.jspf" %>
		</c:otherwise>
	</c:choose>
</div>