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
long socialOfficePluginTicketAttachmentId = ParamUtil.getLong(request, "socialOfficePluginTicketAttachmentId");
%>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="please-provide-a-copy-of-the-social-office-plugin" />
	</h2>

	<div class="clearfix">
		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="label" value="social-office-plugin" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(socialOfficePluginTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SOCIAL_OFFICE_PLUGIN) %>" />
		</liferay-util:include>
	</di>
</div>