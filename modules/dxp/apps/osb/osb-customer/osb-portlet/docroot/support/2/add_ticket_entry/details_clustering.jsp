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
	<h2 class="component-title section-heading">
		<liferay-ui:message key="clustering-details" />
	</h2>

	<div>
		<liferay-ui:message key="please-provide-accurate-environment-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
	</div>
</div>

<br />

<div class="clearfix">
	<div class="aui-w45 fl">
		<div class="section-heading"><liferay-ui:message key="server-communication-type" />:</div>

		<aui:select data-field-required-status="<%= false %>" field-required-message="<%= HtmlUtil.escapeAttribute(LanguageUtil.format(pageContext, "invalid-value-provided-for-x", "server-communication-type")) %>" name="serverCommunicationType">
			<aui:option value="" />

			<%
			for (int curServerCommunicationType : TicketEntryConstants.CLUSTER_SERVER_COMMUNICATION_TYPES) {
			%>

				<aui:option label="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(curServerCommunicationType) %>" selected="<%= curServerCommunicationType == serverCommunicationType %>" value="<%= curServerCommunicationType %>" />

			<%
			}
			%>

		</aui:select>
	</div>

	<div class="aui-w45 fr">
		<div class="section-heading"><liferay-ui:message key="number-of-nodes" />:</div>

		<aui:select name="numberOfNodes">
			<aui:option value="0" />
			<aui:option label="1" selected="<%= numberOfNodes == 1 %>" value="1" />
			<aui:option label="2" selected="<%= numberOfNodes == 2 %>" value="2" />
			<aui:option label="3" selected="<%= numberOfNodes == 3 %>" value="3" />
			<aui:option label="4" selected="<%= numberOfNodes == 4 %>" value="4" />
			<aui:option label="5" selected="<%= numberOfNodes == 5 %>" value="5" />
			<aui:option label="6" selected="<%= numberOfNodes == 6 %>" value="6" />
			<aui:option label="7" selected="<%= numberOfNodes == 7 %>" value="7" />
			<aui:option label="8+" selected="<%= numberOfNodes == 8 %>" value="8" />
		</aui:select>
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

	<div class="clearfix">
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

	<div class="clearfix">
		<liferay-util:include page="/support/2/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="zip-file-containing-patching-tool-info-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPatchLevelTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>