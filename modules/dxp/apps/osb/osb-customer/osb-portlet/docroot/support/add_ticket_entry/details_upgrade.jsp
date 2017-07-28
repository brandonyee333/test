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

AccountEnvironment accountEnvironment = null;

if (accountEnvironmentId > 0) {
	accountEnvironment = AccountEnvironmentServiceUtil.getAccountEnvironment(accountEnvironmentId);
}

long databaseTicketAttachmentId = ParamUtil.getLong(request, "databaseTicketAttachmentId");
int databaseUploadMethod = ParamUtil.getInteger(request, "databaseUploadMethod", 1);
long dataFolderTicketAttachmentId = ParamUtil.getLong(request, "dataFolderTicketAttachmentId");
int dataFolderUploadMethod = ParamUtil.getInteger(request, "dataFolderUploadMethod", 1);
int docLibPersistence = ParamUtil.getInteger(request, "docLibPersistence");
int envAS = ParamUtil.getInteger(request, "envAS");
int envBrowser = ParamUtil.getInteger(request, "envBrowser");
String envBrowserCustom = ParamUtil.getString(request, "envBrowserCustom");
int envDB = ParamUtil.getInteger(request, "envDB");
int envJVM = ParamUtil.getInteger(request, "envJVM");
int envLFR = ParamUtil.getInteger(request, "envLFR");
int envOS = ParamUtil.getInteger(request, "envOS");
String envOSCustom = ParamUtil.getString(request, "envOSCustom");
long patchLevelTicketAttachmentId = ParamUtil.getLong(request, "patchLevelTicketAttachmentId");
long portalExtTicketAttachmentId = ParamUtil.getLong(request, "portalExtTicketAttachmentId");
long serverLogTicketAttachmentId = ParamUtil.getLong(request, "serverLogTicketAttachmentId");
String stepsToUpgrade = ParamUtil.getString(request, "stepsToUpgrade");
int toEnvLFR = ParamUtil.getInteger(request, "toEnvLFR");
long toPatchLevelTicketAttachmentId = ParamUtil.getLong(request, "toPatchLevelTicketAttachmentId");
long toPortalExtTicketAttachmentId = ParamUtil.getLong(request, "toPortalExtTicketAttachmentId");
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
	3. <liferay-ui:message key="verify-your-upgrade-details" />
</h2>

<div>
	<liferay-ui:message key="please-provide-accurate-upgrade-details-these-details-will-help-us-reproduce-your-issue-and-come-to-a-faster-resolution" />
</div>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
</liferay-util:include>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b">*<liferay-ui:message key="how-is-the-document-library-server-persisting-documents" /></span>

			<select id="<portlet:namespace />docLibPersistence" name="<portlet:namespace />docLibPersistence">
				<option value="0"></option>

				<%
				for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
				%>

					<option <%= (curDocLibPersistence == docLibPersistence) ? "selected" : StringPool.BLANK %> value="<%= curDocLibPersistence %>"><%= LanguageUtil.get(request, TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence)) %></option>

				<%
				}
				%>

			</select>
		</div>

		<div class="aui-helper-clearfix single-line">
			<div class="aui-helper-clearfix txt-b">
				*<liferay-ui:message key="please-provide-the-steps-used-to-perform-the-upgrade" />
			</div>

			<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
				<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
				<liferay-util:param name="editorId" value="stepsToUpgrade" />
				<liferay-util:param name="height" value="300" />
				<liferay-util:param name="name" value="stepsToUpgrade" />
				<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix fl txt-b">*
			<liferay-ui:message key="please-provide-both-portal-ext.properties-files" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142855" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT) %>" />
				<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
				<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="original-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(portalExtTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="new-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPortalExtTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix txt-b">*<liferay-ui:message key="please-provide-both-patching-tool-info-files" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142925" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a></div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="accountEnvironmentAttachmentType" value="<%= String.valueOf(AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
				<liferay-util:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironmentId) %>" />
				<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="original-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(patchLevelTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="confirm" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="new-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(toPatchLevelTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="if-possible-please-provide-a-copy-of-the-original-database-pre-upgrade" /></span>

			<select name="<portlet:namespace />databaseUploadMethod" onChange="<portlet:namespace />selectUploadMethod('database', this.value);">

				<%
				for (int curUploadMethod : TicketEntryConstants.UPLOAD_METHODS) {
				%>

					<option <%= (databaseUploadMethod == curUploadMethod) ? "selected" : StringPool.BLANK %> value="<%= curUploadMethod %>"><%= LanguageUtil.get(request, TicketEntryConstants.getUploadMethodLabel(curUploadMethod)) %></option>

				<%
				}
				%>

			</select>
		</div>

		<div class="aui-helper-clearfix <%= (databaseUploadMethod == TicketEntryConstants.UPLOAD_METHOD_FTP) ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />databaseFileTextBox">
			<span class="txt-b"><liferay-ui:message key="file-name" /><a class="help-link" href="/group/customer/knowledge/kb/-/knowledge_base/article/14743040" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>

			<input name="<portlet:namespace />databaseFTPFileName" type="text" />
		</div>

		<div class="aui-helper-clearfix <%= (databaseUploadMethod == TicketEntryConstants.UPLOAD_METHOD_HERE) ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />databaseFileUpload">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="exported-database-file" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(databaseTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_DATABASE) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="if-possible-please-provide-a-copy-of-the-data-folder-image-gallery" /></span>

			<select name="<portlet:namespace />dataFolderUploadMethod" onChange="<portlet:namespace />selectUploadMethod('dataFolder', this.value);">

				<%
				for (int curUploadMethod : TicketEntryConstants.UPLOAD_METHODS) {
				%>

					<option <%= (dataFolderUploadMethod == curUploadMethod) ? "selected" : StringPool.BLANK %> value="<%= curUploadMethod %>"><%= LanguageUtil.get(request, TicketEntryConstants.getUploadMethodLabel(curUploadMethod)) %></option>

				<%
				}
				%>

			</select>
		</div>

		<div class="aui-helper-clearfix <%= (dataFolderUploadMethod == TicketEntryConstants.UPLOAD_METHOD_FTP) ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />dataFolderFileTextBox">
			<span class="txt-b"><liferay-ui:message key="file-name" /><a class="help-link" href="/group/customer/knowledge/kb/-/knowledge_base/article/14743040" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>

			<input name="<portlet:namespace />dataFolderFTPFileName" type="text" />
		</div>

		<div class="aui-helper-clearfix <%= (dataFolderUploadMethod == TicketEntryConstants.UPLOAD_METHOD_HERE) ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />dataFolderFileUpload">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="data-folder-image-gallery" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(dataFolderTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_DATA_FOLDER) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix txt-b">
			<liferay-ui:message key="if-possible-please-provide-any-log-files" />
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="label" value="log-file-or-zip-file" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentId" value="<%= String.valueOf(serverLogTicketAttachmentId) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_SERVER_LOG) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<aui:script>
	function <portlet:namespace />selectUploadMethod(field, method) {
		var fileDiv = AUI().one('#<portlet:namespace />' + field + 'FileUpload');
		var ftpDiv = AUI().one('#<portlet:namespace />' + field + 'FileTextBox');

		if (method == <%= TicketEntryConstants.UPLOAD_METHOD_FTP %>) {
			fileDiv.hide();
			ftpDiv.show();
		}
		else if (method == <%= TicketEntryConstants.UPLOAD_METHOD_HERE %>) {
			fileDiv.show();
			ftpDiv.hide();
		}
	}

	function <portlet:namespace />validateGeneric() {
		return (<portlet:namespace />validateFile('portalExt') && <portlet:namespace />validateFileConfirmed('portalExt') && <portlet:namespace />validateFile('patchLevel') && <portlet:namespace />validateFileConfirmed('patchLevel') && <portlet:namespace />validateFile('toPortalExt') && <portlet:namespace />validateFileConfirmed('toPortalExt') && <portlet:namespace />validateFile('toPatchLevel') && <portlet:namespace />validateFileConfirmed('toPatchLevel'));
	}
</aui:script>