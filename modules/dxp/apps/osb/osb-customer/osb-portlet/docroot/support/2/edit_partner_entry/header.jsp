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

<%@ include file="/support/2/init.jsp" %>

<%
String backURL = (String)request.getAttribute("edit_partner_entry.jsp-backURL");
PartnerEntry partnerEntry = (PartnerEntry)request.getAttribute("edit_partner_entry.jsp-partnerEntry");
%>

<div class="detail-view-header">
	<div class="back-link clearfix txt-sb">
		<a href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back" /></a>
	</div>

	<div class="header">
		<div class="buttons">
			<liferay-util:include page="/support/2/edit_partner_entry/buttons.jsp" servletContext="<%= application %>" />
		</div>

		<div class="page-heading" id="<portlet:namespace/>pageHeading">
			<span class="partner-code"><%= partnerEntry.getCode() %></span>

			<span>(<%= LanguageUtil.get(request, partnerEntry.getStatusLabel()) %>)</span>
		</div>

		<div class="sub-header">

			<%
			SupportRegion supportRegion = partnerEntry.getSupportRegion();
			%>

			<c:if test="<%= supportRegion != null %>">
				<span class="first segment">
					<liferay-ui:message key="support-region" />:

					<span class="txt-sb"><%= HtmlUtil.escape(supportRegion.getName()) %></span>
				</span>
			</c:if>
		</div>
	</div>
</div>