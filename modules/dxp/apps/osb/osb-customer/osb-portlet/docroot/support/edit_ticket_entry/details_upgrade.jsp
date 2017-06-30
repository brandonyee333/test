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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

boolean edit = (Boolean)request.getAttribute("edit_ticket_entry.jsp-edit");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
OfferingEntry offeringEntry = (OfferingEntry)request.getAttribute("edit_ticket_entry.jsp-offeringEntry");

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

String databaseFTPFileName = ParamUtil.getString(request, "databaseFTPFileName", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DATABASE_FTP_FILENAME)));
int databaseUploadMethod = ParamUtil.getInteger(request, "databaseUploadMethod", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DATABASE_UPLOAD_METHOD), 1));
String dataFolderFTPFileName = ParamUtil.getString(request, "dataFolderFTPFileName", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DATA_FOLDER_FTP_FILENAME)));
int dataFolderUploadMethod = ParamUtil.getInteger(request, "dataFolderUploadMethod", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DATA_FOLDER_UPLOAD_METHOD), 1));
int docLibPersistence = ParamUtil.getInteger(request, "docLibPersistence", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_DOC_LIB_PERSISTENCE)));
String stepsToUpgrade = ParamUtil.getString(request, "stepsToUpgrade", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_STEPS_TO_UPGRADE)));
%>

<h2 class="section-heading">
	<liferay-ui:message key="upgrade-details" />
</h2>

<liferay-util:include page="/support/common/details_environment.jsp" servletContext="<%= application %>">
	<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
	<liferay-util:param name="offeringEntryId" value="<%= String.valueOf(offeringEntry.getOfferingEntryId()) %>" />
</liferay-util:include>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><%= edit ? "*" : StringPool.BLANK %><liferay-ui:message key="how-is-the-document-library-server-persisting-documents" /></span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<select id="<portlet:namespace />docLibPersistence" name="<portlet:namespace />docLibPersistence">
						<option value="0"></option>

						<%
						for (int curDocLibPersistence : TicketEntryConstants.DOC_LIB_PERSISTENCES) {
						%>

							<option <%= (curDocLibPersistence == docLibPersistence) ? "selected" : StringPool.BLANK %> value="<%= curDocLibPersistence %>"><%= LanguageUtil.get(locale, TicketEntryConstants.getDocLibPersistenceLabel(curDocLibPersistence)) %></option>

						<%
						}
						%>

					</select>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.get(pageContext, TicketEntryConstants.getDocLibPersistenceLabel(docLibPersistence)) %>

					<input name="<portlet:namespace />docLibPersistence" type="hidden" value="<%= docLibPersistence %>" />
				</c:otherwise>
			</c:choose>
		</div>

		<div class="aui-helper-clearfix single-line">
			<div class="aui-helper-clearfix txt-b"><%= edit ? "*" : StringPool.BLANK %><liferay-ui:message key="steps-used-to-perform-the-upgrade" /></div>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<liferay-util:include page="/common/textarea.jsp" servletContext="<%= application %>">
						<liferay-util:param name="content" value="<%= stepsToUpgrade %>" />
						<liferay-util:param name="editorId" value="stepsToUpgrade" />
						<liferay-util:param name="height" value="300" />
						<liferay-util:param name="name" value="stepsToUpgrade" />
						<liferay-util:param name="showCounter" value="<%= String.valueOf(Boolean.TRUE) %>" />
					</liferay-util:include>
				</c:when>
				<c:otherwise>
					<pre><%= SupportUtil.getHTML(stepsToUpgrade) %></pre>

					<input name="<portlet:namespace />stepsToUpgrade" type="hidden" value="<%= HtmlUtil.escape(stepsToUpgrade) %>" />
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="portal-ext-files" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142855" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="original-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PORTAL_EXT) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="new-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PORTAL_EXT) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="patching-tool-info-files" /><a class="help-link" href="/group/customer/kbase/-/knowledge_base/article/33142925" target="_blank"><img src="<%= themeDisplay.getPathThemeImages() + "/common/help.png" %>" /></a>:</span>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="original-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_PATCH_LEVEL) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="new-liferay-server" />
				<liferay-util:param name="required" value="<%= String.valueOf(Boolean.TRUE) %>" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_NEW_PATCH_LEVEL) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="database-upload-method-selected" />:</span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<select name="<portlet:namespace />databaseUploadMethod" onChange="<portlet:namespace />selectUploadMethod('database', this.value);">

						<%
						for (int curUploadMethod : TicketEntryConstants.UPLOAD_METHODS) {
						%>

							<option <%= databaseUploadMethod == curUploadMethod ? "selected" : StringPool.BLANK %> value="<%= curUploadMethod %>"><%= LanguageUtil.get(locale, TicketEntryConstants.getUploadMethodLabel(curUploadMethod)) %></option>

						<%
						}
						%>

					</select>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.get(locale, TicketEntryConstants.getUploadMethodLabel(databaseUploadMethod)) %>

					<input name="<portlet:namespace />databaseUploadMethod" type="hidden" value="<%= databaseUploadMethod %>" />
				</c:otherwise>
			</c:choose>
		</div>

		<div class="aui-helper-clearfix <%= databaseUploadMethod == TicketEntryConstants.UPLOAD_METHOD_FTP ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />databaseFileTextBox">
			<span class="txt-b"><liferay-ui:message key="file-name" />:</span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<input name="<portlet:namespace />databaseFTPFileName" type="text" value="<%= HtmlUtil.escape(databaseFTPFileName) %>" />
				</c:when>
				<c:otherwise>
					<%= HtmlUtil.escape(databaseFTPFileName) %>

					<input name="<portlet:namespace />databaseFTPFileName" type="hidden" value="<%= HtmlUtil.escape(databaseFTPFileName) %>" />
				</c:otherwise>
			</c:choose>
		</div>

		<div class="aui-helper-clearfix <%= databaseUploadMethod == TicketEntryConstants.UPLOAD_METHOD_HERE ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />databaseFileUpload">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="exported-database-file" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_DATABASE) %>" />
			</liferay-util:include>
		</div>

		<div class="aui-helper-clearfix single-line">
			<span class="txt-b"><liferay-ui:message key="data-upload-method-selected" />:</span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<select name="<portlet:namespace />dataFolderUploadMethod" onChange="<portlet:namespace />selectUploadMethod('dataFolder', this.value);">

						<%
						for (int curUploadMethod : TicketEntryConstants.UPLOAD_METHODS) {
						%>

							<option <%= dataFolderUploadMethod == curUploadMethod ? "selected" : StringPool.BLANK %> value="<%= curUploadMethod%>"><%= LanguageUtil.get(locale, TicketEntryConstants.getUploadMethodLabel(curUploadMethod)) %></option>

						<%
						}
						%>

					</select>
				</c:when>
				<c:otherwise>
					<%= LanguageUtil.get(locale, TicketEntryConstants.getUploadMethodLabel(dataFolderUploadMethod)) %>

					<input name="<portlet:namespace />dataFolderUploadMethod" type="hidden" value="<%= dataFolderUploadMethod %>" />
				</c:otherwise>
			</c:choose>
		</div>

		<div class="aui-helper-clearfix <%= dataFolderUploadMethod == TicketEntryConstants.UPLOAD_METHOD_FTP ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />dataFolderFileTextBox">
			<span class="txt-b"><liferay-ui:message key="file-name" />:</span>

			<c:choose>
				<c:when test="<%= edit && hasUpdateAdvanced %>">
					<input name="<portlet:namespace />dataFolderFTPFileName" type="text" value="<%= HtmlUtil.escape(dataFolderFTPFileName) %>" />
				</c:when>
				<c:otherwise>
					<%= HtmlUtil.escape(dataFolderFTPFileName) %>

					<input name="<portlet:namespace />dataFolderFTPFileName" type="hidden" value="<%= HtmlUtil.escape(dataFolderFTPFileName) %>" />
				</c:otherwise>
			</c:choose>
		</div>

		<div class="aui-helper-clearfix <%= dataFolderUploadMethod == TicketEntryConstants.UPLOAD_METHOD_HERE ? StringPool.BLANK : "aui-helper-hidden" %> indent" id="<portlet:namespace />dataFolderFileUpload">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="data-folder-image-gallery" />
				<liferay-util:param name="ticketAttachmentType" value="<%= String.valueOf(TicketAttachmentConstants.TYPE_DATA_FOLDER) %>" />
			</liferay-util:include>
		</div>
	</div>
</div>

<div class="callout-a">
	<div class="aui-helper-clearfix callout-content">
		<div class="aui-helper-clearfix single-line">
			<liferay-util:include page="/support/common/ticket_attachment_upload.jsp" servletContext="<%= application %>">
				<liferay-util:param name="edit" value="<%= String.valueOf(edit && hasUpdateAdvanced) %>" />
				<liferay-util:param name="label" value="log-file-or-zip-file" />
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
</aui:script>