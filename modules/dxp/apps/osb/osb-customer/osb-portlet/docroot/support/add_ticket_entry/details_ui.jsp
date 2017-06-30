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
long accountEnvironmentId = ParamUtil.getLong(request, "accountEnvironmentId");

long portalExtTicketAttachmentId = ParamUtil.getLong(request, "portalExtTicketAttachmentId");
long patchLevelTicketAttachmentId = ParamUtil.getLong(request, "patchLevelTicketAttachmentId");
long screenshotTicketAttachmentId = ParamUtil.getLong(request, "screenshotTicketAttachmentId");
long serverLogTicketAttachmentId = ParamUtil.getLong(request, "serverLogTicketAttachmentId");
%>

<liferay-ui:error exception="<%= DuplicateTicketAttachmentException.class %>" message="please-enter-a-unique-document-name" />

<liferay-ui:error exception="<%= RequiredFieldException.class %>">

	<%
	RequiredFieldException rfe = (RequiredFieldException)errorException;

	String requiredField = rfe.getRequiredField();
	%>

	<c:if test='<%= requiredField.equals("patch-level") || requiredField.equals("portal-ext") %>'>
		<liferay-ui:message key="please-upload-a-portal-ext-and-patch-level-file" />
	</c:if>
</liferay-ui:error>

<liferay-ui:error exception="<%= TicketEntryAttachmentSizeException.class %>">

	<%
	TicketEntryAttachmentSizeException tease = (TicketEntryAttachmentSizeException)errorException;
	%>

	<c:choose>
		<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EMPTY_FILE %>">
			<liferay-ui:message key="the-file-contains-no-data-and-cannot-be-uploaded" />
		</c:when>
		<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EXCEEDS_LIMIT %>">
			<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
		</c:when>
	</c:choose>
</liferay-ui:error>

<h2 class="section-heading steps">
	3. <liferay-ui:message key="verify-your-environment" />
</h2>

<div>
	<liferay-ui:message key="please-provide-accurate-environment-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
</div>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
</liferay-util:include>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT) %>" />
			<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="portal-ext" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(portalExtTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
			<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
			<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="patch-level" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(patchLevelTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix">
			<span class="fl txt-b"><liferay-ui:message key="if-possible-please-provide-any-screenshot-files" /></span>
		</div>

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="label" value="screenshot-file-or-zip-file" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(screenshotTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SCREEN_SHOT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix">
			<span class="fl txt-b"><liferay-ui:message key="if-possible-please-provide-any-log-files" /></span>
		</div>

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="label" value="log-file-or-zip-file" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(serverLogTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SERVER_LOG) %>" />
		</liferay-util:include>
	</div>
</div>

<aui:script>
	function <portlet:namespace />validateGeneric() {
		return (<portlet:namespace />validateFile('portalExt') && <portlet:namespace />validateFileConfirmed('portalExt') && <portlet:namespace />validateFile('patchLevel') && <portlet:namespace />validateFileConfirmed('patchLevel'));
	}
</aui:script>