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
int serverCommunicationType = ParamUtil.getInteger(request, "serverCommunicationType");
int numberOfNodes = ParamUtil.getInteger(request, "numberOfNodes");
String serverConfigurations = ParamUtil.getString(request, "serverConfigurations");
long toPortalExtTicketAttachmentId = ParamUtil.getLong(request, "toPortalExtTicketAttachmentId");
long toPatchLevelTicketAttachmentId = ParamUtil.getLong(request, "toPatchLevelTicketAttachmentId");
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

<div>
	<h2 class="section-heading component-title">
		<liferay-ui:message key="clustering-details" />
	</h2>

	<div>
		<liferay-ui:message key="please-provide-accurate-environment-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
	</div>
</div>

<br />

<div class="aui-helper-clearfix">
	<div class="aui-w45 fl">
		<span class="section-heading"><liferay-ui:message key="server-communication-type" />:</span>

		<select data-field-required-status="<%= false %>" field-required-message='<%= HtmlUtil.escapeAttribute(LanguageUtil.format(pageContext, "invalid-value-provided-for-x", "server-communication-type")) %>' id="<portlet:namespace />serverCommunicationType" name="<portlet:namespace />serverCommunicationType">
			<option value=""></option>

			<%
			for (int curServerCommunicationType : TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES) {
			%>

				<option <%= (curServerCommunicationType == serverCommunicationType) ? "selected" : StringPool.BLANK %> value="<%= curServerCommunicationType %>"><liferay-ui:message key="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(curServerCommunicationType) %>" /></option>

			<%
			}
			%>

		</select>
	</div>

	<div class="aui-w45 fr">
		<span class="section-heading"><liferay-ui:message key="number-of-nodes" />:</span>

		<select id="<portlet:namespace />numberOfNodes" name="<portlet:namespace />numberOfNodes">
			<option value="0"></option>
			<option <%= (numberOfNodes == 1) ? "selected" : StringPool.BLANK %> value="1">1</option>
			<option <%= (numberOfNodes == 2) ? "selected" : StringPool.BLANK %> value="2">2</option>
			<option <%= (numberOfNodes == 3) ? "selected" : StringPool.BLANK %> value="3">3</option>
			<option <%= (numberOfNodes == 4) ? "selected" : StringPool.BLANK %> value="4">4</option>
			<option <%= (numberOfNodes == 5) ? "selected" : StringPool.BLANK %> value="5">5</option>
			<option <%= (numberOfNodes == 6) ? "selected" : StringPool.BLANK %> value="6">6</option>
			<option <%= (numberOfNodes == 7) ? "selected" : StringPool.BLANK %> value="7">7</option>
			<option <%= (numberOfNodes == 8) ? "selected" : StringPool.BLANK %> value="8">8+</option>
		</select>
	</div>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="jvm-arguments-settings-optional" />:
	</h2>

	<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
		<liferay-util:param name="content" value="<%= serverConfigurations %>" />
		<liferay-util:param name="editorId" value="serverConfigurations" />
		<liferay-util:param name="name" value="serverConfigurations" />
		<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.FALSE) %>" />
	</liferay-util:include>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="please-provide-all-portal-ext-files-from-all-nodes-within-the-cluster" />:
	</h2>

	<div class="aui-helper-clearfix">
		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="zip-file-containing-portal-ext-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPortalExtTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>
</div>

<div>
	<h2 class="section-heading">
		<liferay-ui:message key="please-provide-all-patching-tool-info-files-from-all-nodes-within-the-cluster" />:
	</h2>

	<div class="aui-helper-clearfix">
		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="zip-file-containing-patching-tool-info-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPatchLevelTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>