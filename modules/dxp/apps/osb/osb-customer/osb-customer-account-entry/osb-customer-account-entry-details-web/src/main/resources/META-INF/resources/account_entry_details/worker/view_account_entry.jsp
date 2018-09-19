<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();

String tabs1 = ParamUtil.getString(request, "tabs1");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcRenderCommandName", "/view_account_entry");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntry.getAccountEntryId()));
%>

<h1>
	<%= HtmlUtil.escape(accountEntry.getName()) %>
</h1>

<liferay-ui:tabs
	names="overview,team-members,liferay-contacts,offerings,history"
	url="<%= portletURL.toString() %>"
/>

<div class="container-fluid-max-xl">
	<c:choose>
		<c:when test='<%= tabs1.equals("history") %>'>
			<liferay-util:include page="/account_entry_details/worker/history.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("liferay-contacts") %>'>
			<liferay-util:include page="/account_entry_details/worker/liferay_contacts.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("offerings") %>'>
			<liferay-util:include page="/account_entry_details/worker/offerings.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:when test='<%= tabs1.equals("team-members") %>'>
			<liferay-util:include page="/account_entry_details/worker/team_members.jsp" servletContext="<%= application %>" />
		</c:when>
		<c:otherwise>
			<liferay-util:include page="/account_entry_details/worker/overview.jsp" servletContext="<%= application %>" />
		</c:otherwise>
	</c:choose>
</div>