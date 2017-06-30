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

<%@ include file="/support/init.jsp" %>

<%
boolean edit = (Boolean)request.getAttribute("edit_ticket_entry.jsp-edit");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("edit_ticket_entry.jsp-offeringEntry");
ProductEntry productEntry = (ProductEntry)request.getAttribute("edit_ticket_entry.jsp-productEntry");
%>

<h2 class="section-heading">
	<liferay-ui:message key="environment" />
</h2>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
	<liferay-util:param name="offeringEntryId" value="<%= String.valueOf(offeringEntry.getOfferingEntryId()) %>" />
</liferay-util:include>

<c:if test="<%= productEntry.isSocialOffice() %>">
	<div class="callout-a">
		<div class="aui-helper-clearfix callout-content">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="social-office-plugin" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SOCIAL_OFFICE_PLUGIN) %>" />
			</liferay-util:include>
		</div>
	</div>
</c:if>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="portal-ext" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="patch-level" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>