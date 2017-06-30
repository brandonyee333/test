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
int serverCommunicationType = ParamUtil.getInteger(request, "serverCommunicationType");
int numberOfNodes = ParamUtil.getInteger(request, "numberOfNodes");
String serverConfigurations = ParamUtil.getString(request, "serverConfigurations");
long portalExtTicketAttachmentId = ParamUtil.getLong(request, "portalExtTicketAttachmentId");
long patchLevelTicketAttachmentId = ParamUtil.getLong(request, "patchLevelTicketAttachmentId");
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
	3. <liferay-ui:message key="verify-your-clustered-environment" />
</h2>

<div>
	<liferay-ui:message key="please-provide-accurate-environment-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
</div>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
</liferay-util:include>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-w50 content-column">
			<div class="content-column-content">
				<span class="txt-b">*<liferay-ui:message key="server-communication-type" />:</span>

				<select id="<portlet:namespace />serverCommunicationType" name="<portlet:namespace />serverCommunicationType">
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
		</div>

		<div class="aui-w50 content-column">
			<div class="content-column-content">
				<span class="txt-b"><liferay-ui:message key="number-of-nodes" />:</span>

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
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="fl txt-b"><liferay-ui:message key="please-provide-any-jvm-arguments-or-settings-used-to-set-up-each-node" />:</span>

		<br />

		<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
			<liferay-util:param name="content" value="<%= serverConfigurations %>" />
			<liferay-util:param name="editorId" value="serverConfigurations" />
			<liferay-util:param name="height" value="300" />
			<liferay-util:param name="name" value="serverConfigurations" />
			<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="fl txt-b"><liferay-ui:message key="please-provide-all-portal-ext-files-from-all-nodes-within-the-cluster" />:</span>

		<br />

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142855" />
			<liferay-util:param name="label" value="zip-file-containing-portal-ext-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(portalExtTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
		</liferay-util:include>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<span class="fl txt-b"><liferay-ui:message key="please-provide-all-patching-tool-info-files-from-all-nodes-within-the-cluster" />:</span>

		<br />

		<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
			<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="kBaseArticleId" value="33142925" />
			<liferay-util:param name="label" value="zip-file-containing-patching-tool-info-files" />
			<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
			<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(patchLevelTicketAttachmentId) %>" />
			<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
		</liferay-util:include>
	</div>
</div>

<aui:script>
	function <portlet:namespace />validateGeneric() {
		return (<portlet:namespace />validateFile('portalExt') && <portlet:namespace />validateFile('patchLevel'));
	}
</aui:script>