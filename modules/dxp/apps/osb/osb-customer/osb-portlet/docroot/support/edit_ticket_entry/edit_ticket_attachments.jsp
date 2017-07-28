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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");
int visibility = ParamUtil.getInteger(request, "visibility");

TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

int[] userVisibilities = TicketEntryLocalServiceUtil.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());

AccountEntry accountEntry = ticketEntry.getAccountEntry();

boolean ticketWorker = false;

if (liferayIncOrg || (accountEntry.isPartnerManagedSupport() && PartnerWorkerLocalServiceUtil.hasPartnerWorker(user.getUserId(), accountEntry.getPartnerEntryId()))) {
	ticketWorker = true;
}

FileRepository fileRepository = SupportUtil.getFileRepository(ticketEntry);

String fileRepositoryId = StringPool.BLANK;

if (fileRepository != null) {
	fileRepositoryId = fileRepository.getFileRepositoryId();
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/edit_ticket_entry/edit_ticket_attachments.jsp");
portletURL.setParameter("redirect", redirect);
portletURL.setParameter("ticketEntryId", String.valueOf(ticketEntryId));
%>

<portlet:actionURL name="addTicketAttachment" var="addTicketAttachmentURL">
	<portlet:param name="mvcPath" value="/support/edit_ticket_entry/edit_ticket_attachments.jsp" />
	<portlet:param name="redirect" value="<%= redirect %>" />
	<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
</portlet:actionURL>

<aui:form action="<%= addTicketAttachmentURL %>" class="uni-form" enctype="multipart/form-data" method="post" name="fm">
	<input name="<portlet:namespace />redirect" type="hidden" value="<%= HtmlUtil.escapeAttribute(currentURL) %>" />
	<input name="<portlet:namespace />ticketEntryId" type="hidden" value="<%= ticketEntryId %>" />

	<div class="cleared section">
		<div class="fl">
			Edit Attachments for Ticket: <%= ticketEntry.getDisplayId() %>
		</div>

		<div class="fr">
			<a class="btn" href="<%= HtmlUtil.escapeAttribute(backURL) %>">&lt; <liferay-ui:message key="back-to-previous-page" /></a>
		</div>
	</div>

	<liferay-ui:error exception="<%= DuplicateTicketAttachmentException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
	<liferay-ui:error exception="<%= FileRepositoryNotAvailableException.class %>" message="file-servers-are-not-available-please-contact-your-support-manager" />
	<liferay-ui:error exception="<%= TicketAttachmentVisibilityException.class %>" message="please-enter-a-valid-visibility" />

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

	<h1 class="section-heading">
		<liferay-ui:message key="attachments" />
	</h1>

	<div class="callout-a">
		<div class="callout-content">

			<%
			List<String> headerNames = new ArrayList<String>();

			headerNames.add("file-name");
			headerNames.add("size");
			headerNames.add("type");
			headerNames.add("attached-by");
			headerNames.add("attached-date");

			if (userVisibilities.length > 1) {
				headerNames.add("visibility");
			}

			if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
				headerNames.add("delete-date");
			}

			if (ticketWorker) {
				headerNames.add("replicate");
			}

			headerNames.add(StringPool.BLANK);

			SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "this-support-ticket-does-not-have-any-file-attachments");

			int[] types = new int[] {TicketAttachmentConstants.TYPE_HOTFIX, TicketAttachmentConstants.TYPE_LARGE_FILE, TicketAttachmentConstants.TYPE_LARGE_HOTFIX, TicketAttachmentConstants.TYPE_NONE};

			if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_BASIC)) {
				types = ArrayUtil.append(TicketAttachmentConstants.TYPES, TicketAttachmentConstants.TYPES_LARGE);
			}

			List results = ticketEntry.getTicketAttachments(types, userVisibilities);

			searchContainer.setResults(results);

			int total = results.size();

			searchContainer.setTotal(total);

			List resultRows = searchContainer.getResultRows();

			for (int i = 0; i < results.size(); i++) {
				TicketAttachment ticketAttachment = (TicketAttachment)results.get(i);

				String fileName = ticketAttachment.getFileName();
				long fileSize = ticketAttachment.getFileSize();

				ResultRow row = new ResultRow(ticketAttachment, ticketAttachment.getTicketAttachmentId(), i);

				LiferayPortletURL rowURL = null;

				if (OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW)) {
					rowURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					rowURL.setCopyCurrentRenderParameters(false);
					rowURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
					rowURL.setResourceID("ticketAttachment");
				}

				// File name

				row.addText(fileName, rowURL);

				// Size

				row.addText(TextFormatter.formatKB((double)fileSize, locale) + "k", rowURL);

				// Type

				row.addText(LanguageUtil.get(pageContext, ticketAttachment.getTypeLabel()), rowURL);

				// Attached by

				row.addText(HtmlUtil.escape(ticketAttachment.getUserName()), rowURL);

				// Attached date

				row.addText(longDateFormatDate.format(ticketAttachment.getCreateDate()), rowURL);

				// Visibility

				if (userVisibilities.length > 1) {
					row.addText(LanguageUtil.get(pageContext, ticketAttachment.getVisibilityLabel()), rowURL);
				}

				// Delete Date

				if (ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED) {
					if (ticketAttachment.getDeleteDate() != null) {
						row.addText(longDateFormatDate.format(ticketAttachment.getDeleteDate()));
					}
					else {
						row.addText(LanguageUtil.get(pageContext, "n-a"));
					}
				}

				if (ticketWorker) {
					if (ArrayUtil.contains(TicketAttachmentConstants.TYPES_LARGE, ticketAttachment.getType())) {
						row.addText(String.valueOf(ticketAttachment.getReplicate()));
					}
					else {
						row.addText(LanguageUtil.get(pageContext, "n-a"));
					}
				}

				// Action

				PortletBag portletBag = PortletBagPool.get(OSBPortletKeys.OSB_SUPPORT);

				ServletContext servletContext = portletBag.getServletContext();

				row.addJSP("/support/edit_ticket_entry/ticket_attachment_action.jsp", servletContext, request, response);

				// Add result row

				resultRows.add(row);
			}
			%>

			<liferay-ui:search-iterator paginate="<%= false %>" searchContainer="<%= searchContainer %>" />
		</div>
	</div>

	<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED %>">
		<div class="callout-a">
			<div class="callout-content">
				<div class="aui-helper-clearfix lfr-dynamic-uploader" id="<portlet:namespace />uploaderWrapper">
					<input name="<portlet:namespace />fileRepositoryId" type="hidden" value="<%= HtmlUtil.escapeAttribute(fileRepositoryId) %>" />
					<input id="<portlet:namespace />token" type="hidden" value="" />
					<input id="<portlet:namespace />uploaderType" name="<portlet:namespace />uploaderType" type="hidden" />

					<div class="lfr-message-container" id="<portlet:namespace />messageContainer"></div>

					<div class="lfr-upload-container" id="<portlet:namespace />uploadContainer">
						<div class="upload-area" id="<portlet:namespace />uploadArea">
							<h4 class="drop-file-text">
								<liferay-ui:message key="drop-file-here-to-upload" />

								<br />

								<liferay-ui:message key="or" />
							</h4>

							<button class="btn btn-default" id="<portlet:namespace />selectButton" type="button"><liferay-ui:message key="select-file" /></button>

							<input class="aui-helper-hidden" id="<portlet:namespace />fileInputField" type="file" />
						</div>

						<div id="<portlet:namespace />fileInfo">

							<%
							for (int i = 1; i <= 3; i++) {
							%>

								<div class="aui-helper-hidden file-item">
									<input class="file" name="<portlet:namespace />file<%= i %>" type="hidden" value="" />

									<div class="aui-w40 content-column">
										<span class="txt-b">
											<liferay-ui:message key="file" />:
										</span>
										<span class="file-name"></span>
									</div>

									<div class="aui-w30 content-column hotfix-display">
										<c:choose>
											<c:when test="<%= ticketWorker %>">
												<span class="txt-b">
													<liferay-ui:message key="hotfix" />:
												</span>

												<select name="<portlet:namespace />type<%= i %>">
													<option value="<%= TicketAttachmentConstants.TYPE_NONE %>"><liferay-ui:message key="no" /></option>
													<option value="<%= TicketAttachmentConstants.TYPE_HOTFIX %>"><liferay-ui:message key="yes" /></option>
												</select>
											</c:when>
											<c:otherwise>
												<input name="<portlet:namespace />type<%= i %>" type="hidden" value="<%= TicketAttachmentConstants.TYPE_NONE %>" />
											</c:otherwise>
										</c:choose>
									</div>

									<div class="aui-w30 content-column">
										<c:choose>
											<c:when test="<%= userVisibilities.length > 1 %>">
												<span class="txt-b">
													<liferay-ui:message key="visibility" />:
												</span>

												<select name="<portlet:namespace />visibility<%= i %>">

													<%
													for (int userVisibility : userVisibilities) {
													%>

														<option value="<%= userVisibility %>"><%= LanguageUtil.get(pageContext, VisibilityConstants.toLabel(userVisibility)) %></option>

													<%
													}
													%>

												</select>
											</c:when>
											<c:otherwise>
												<input name="<portlet:namespace />visibility<%= i %>" type="hidden" value="<%= VisibilityConstants.PUBLIC %>" />
											</c:otherwise>
										</c:choose>
									</div>
								</div>

							<%
							}
							%>

						</div>

						<div class="aui-helper-hidden toolbar" id="<portlet:namespace />toolbar">
							<a class="aui-helper-hidden fr resume-button" href="javascript:;" id="<portlet:namespace />resumeButton"></a>

							<a class="aui-helper-hidden cancel-button fr" href="javascript:;" id="<portlet:namespace />cancelButton"></a>

							<a class="fr pause-button" href="javascript:;" id="<portlet:namespace />pauseButton"></a>

							<span class="cleared progress-bar" id="<portlet:namespace />progressBar">
								<span class="progress" id="<portlet:namespace />progress"></span>
							</span>
						</div>
					</div>

					<div class="aui-helper-hidden lfr-fallback-container" id="<portlet:namespace />fallbackContainer">
						<c:choose>
							<c:when test="<%= userVisibilities.length > 1 %>">
								<div class="aui-helper-clearfix">
									<div class="aui-w10 content-column">
										<span class="txt-b">
											<liferay-ui:message key="visibility" />:
										</span>
									</div>

									<div class="aui-w90 content-column">
										<select name="<portlet:namespace />visibility">

											<%
											for (int userVisibility : userVisibilities) {
											%>

												<option value="<%= userVisibility %>"><%= LanguageUtil.get(pageContext, VisibilityConstants.toLabel(userVisibility)) %></option>

											<%
											}
											%>

										</select>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<input name="<portlet:namespace />visibility" type="hidden" value="<%= VisibilityConstants.PUBLIC %>" />
							</c:otherwise>
						</c:choose>

						<table class="lfr-table">
							<tr>
								<th>
									<strong><liferay-ui:message key="upload-files" /></strong>
								</th>

								<c:if test="<%= ticketWorker %>">
									<th>
										<strong><liferay-ui:message key="hotfix" /></strong>
									</th>
								</c:if>
							</tr>

							<%
							for (int i = 1; i <= 3; i++) {
							%>

								<tr>
									<td>
										<label for="<portlet:namespace />file<%= i %>"><liferay-ui:message key="file" /> <%= i %></label>

										<input id="<portlet:namespace />file<%= i %>" name="<portlet:namespace />file<%= i %>" onChange="AUI().one('#<portlet:namespace />saveButton').show();" type="file" />
									</td>

									<c:if test="<%= ticketWorker %>">
										<td>
											<aui:input label="" name='<%= "hotfix" + i %>' type="checkbox" />
										</td>
									</c:if>
								</tr>

							<%
							}
							%>

						</table>
					</div>
				</div>

				<c:choose>
					<c:when test="<%= ticketWorker %>">
						<div id="<portlet:namespace />needResponseForm" style="display: none;">
							<span class="txt-b">
								<liferay-ui:message key="need-response-from" />:
							</span>

							<c:if test="<%= ArrayUtil.contains(userVisibilities, VisibilityConstants.PUBLIC) %>">
								<input name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_CUSTOMER %>" />

								<liferay-ui:message key="customer" />
							</c:if>

							<c:if test="<%= ArrayUtil.contains(userVisibilities, VisibilityConstants.WORKERS) %>">
								<input name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_PARTNER %>" />

								<liferay-ui:message key="partner" />
							</c:if>

							<c:if test="<%= (ticketEntry.getEscalationLevel() != TicketEntryConstants.ESCALATION_LEVEL_P1) && ArrayUtil.contains(userVisibilities, VisibilityConstants.LIFERAY_INC) %>">
								<input name="<portlet:namespace />pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_LIFERAY %>" />

								<liferay-ui:message key="liferay" />
							</c:if>
						</div>
					</c:when>
					<c:otherwise>
						<input name="<portlet:namespace />pendingTypes" type="hidden" value="<%= (ticketEntry.getEscalationLevel() == TicketEntryConstants.ESCALATION_LEVEL_P1) ? TicketFlagConstants.TYPE_PENDING_PARTNER : TicketFlagConstants.TYPE_PENDING_LIFERAY %>" />
					</c:otherwise>
				</c:choose>

				<div class="button-holder" id="<portlet:namespace />switchButtonHolder"></div>

				<br />

				<div class="button-holder">
					<input class="aui-button-input aui-helper-hidden" id="<portlet:namespace />saveButton" type="submit" value="<liferay-ui:message key="save" />" />

					<c:if test="<%= ticketWorker && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) %>">
						<input class="aui-button-input" id="<portlet:namespace />needResponseButton" onClick="<portlet:namespace />toggleForm('<portlet:namespace />needResponseButton', '<portlet:namespace />needResponseForm');" type="button" value="<liferay-ui:message key="add-need-response-from" />" />
					</c:if>

					<input class="aui-button-input" onClick="parent.location = '<%= HtmlUtil.escape(PortalUtil.escapeRedirect(redirect)) %>';" type="button" value="<liferay-ui:message key="cancel" />" />
				</div>
			</div>
		</div>
	</c:if>
</aui:form>

<aui:script>
	AUI().ready(
		'aui-base',
		function(A) {
			for (var i = 1; i < 4; i++) {
				var fileField = A.one('#<portlet:namespace />file' + i);

				if (fileField) {
					fileField.on('change', <portlet:namespace />onFileChange);

					<portlet:namespace />validateFile(fileField);
				}
			}
		}
	);

	function <portlet:namespace />onFileChange(event) {
		<portlet:namespace />validateFile(event.currentTarget);
	}

	function <portlet:namespace />validateFile(fileField) {
		var value = fileField.val();

		if (value) {
			var extension = value.substring(value.lastIndexOf('.')).toLowerCase();
			var validExtensions = ['<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), "', '") %>'];

			if ((A.Array.indexOf(validExtensions, '*') == -1) &&
				(A.Array.indexOf(validExtensions, extension) == -1)) {

				alert('<%= UnicodeLanguageUtil.get(pageContext, "document-names-must-end-with-one-of-the-following-extensions") %> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>');

				fileField.val('');
			}
		}
	}

	function <portlet:namespace />toggleForm(hideId, showId) {
		document.getElementById(showId).style.display = "";
		document.getElementById(hideId).style.display = "none";
	}
</aui:script>

<c:if test="<%= ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED %>">
	<portlet:resourceURL id="tempTicketAttachment" var="tempTicketAttachmentURL">
		<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
	</portlet:resourceURL>

	<%
	String fileRepositoryURL = StringPool.BLANK;

	try {
		fileRepositoryURL = FileRepositoryUtil.getFileRepositoryURL(fileRepositoryId, FileRepositoryUtil.PATH_UPLOAD);
	}
	catch (Exception e) {
	}
	%>

	<aui:script use="aui-io,aui-template,liferay-dynamic-uploader">
		var resumableUploader = new Resumable(
			{
				chunkRetryInterval: 1000,
				chunkSize: 25 * 1024 * 1024,
				headers: {
					'Content-Type': 'multipart/form-data;charset=utf-8'
				},
				maxFiles: 1,
				method: 'octet',
				query: function(resumableFile, resumableChunk) {
					var token = A.one("#<portlet:namespace />token");

					queryParam = {
						cmd: "upload",
						token: token.get("value")
					};

					return queryParam;
				},
				simultaneousUploads: 1,
				target: '<%= fileRepositoryURL %>',
				testChunks: true,
				throttleProgressCallbacks: 1
			}
		);

		resumableUploader.on("fileAdded", <portlet:namespace />displayProgressMessage);
		resumableUploader.on("fileAdded", <portlet:namespace />validateFile);
		resumableUploader.on("fileError", <portlet:namespace />handleFileError);
		resumableUploader.on("fileProgress", <portlet:namespace />handleFileProgress);
		resumableUploader.on("fileSuccess", <portlet:namespace />handleFileSuccess);
		resumableUploader.on("fileValidated", <portlet:namespace />handleLargeFileValidated);

		var regularUploader = new Resumable(
			{
				chunkSize: 100 * 1024 * 1024,
				maxFiles: 3,
				simultaneousUploads: 1,
				target: '<%= tempTicketAttachmentURL %>',
				testChunks: false,
				throttleProgressCallbacks: 1
			}
		);

		regularUploader.on("fileAdded", <portlet:namespace />displayProgressMessage);
		regularUploader.on("fileAdded", <portlet:namespace />validateFile);
		regularUploader.on("fileSuccess", <portlet:namespace />handleFileSuccess);
		regularUploader.on("fileValidated", <portlet:namespace />handleFileValidated);

		var dynamicUploader = new Liferay.DynamicUploader(
			{
				container: '#<portlet:namespace />uploaderWrapper',
				maxFiles: 3,
				maxFileSize: 35 * 1024 * 1024 * 1024,
				namespace: '<portlet:namespace />',
				regularUploader: regularUploader,
				resumableUploader: resumableUploader,
				uploaderSwitch: '#<portlet:namespace />switchButtonHolder'
			}
		);

		function <portlet:namespace />displayProgressMessage(file) {
			dynamicUploader.updateMessage('<liferay-ui:message key="uploading" unicode="<%= true %>" />...', "progress");
		}

		function <portlet:namespace />handleFileValidated(file) {
			var fileItem = A.one('#<portlet:namespace />fileInfo .aui-helper-hidden.file-item');

			fileItem.set("id", A.Lang.String.escapeHTML(file.uniqueIdentifier));

			fileItem.one('.file').set("value", "");

			fileItem.one('.file-name').setContent(file.fileName);

			fileItem.show();

			regularUploader.upload();
		}

		function <portlet:namespace />handleFileError(file, message) {
			var response = A.JSON.parse(message);

			A.one('#<portlet:namespace />toolbar').hide();

			var errorMessage = '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

			if (response.message == "fail") {
				if (response.exception) {
					if (response.exception === "<%= DuplicateTicketAttachmentException.class.getName() %>") {
						errorMessage = '<liferay-ui:message key="please-enter-a-unique-document-name" unicode="<%= true %>" />';
					}
					else if (response.exception === "<%= FileNameException.class.getName() %>") {
						errorMessage = '<liferay-ui:message key="please-enter-a-file-with-a-valid-file-name" unicode="<%= true %>" />';
					}
				}
			}
			else if (response.message == "invalid-file") {
				errorMessage = '<liferay-ui:message key="please-upload-a-valid-file" unicode="<%= true %>" />';
			}
			else if (response.message == "invalid-session") {
				errorMessage = '<liferay-ui:message key="there-was-an-unexpected-error.-please-refresh-the-current-page" unicode="<%= true %>" />';
			}

			dynamicUploader.updateMessage(errorMessage, "error");
		}

		function <portlet:namespace />handleFileProgress(file) {
			var percentLoaded = Math.floor(file.progress() * 100);

			A.one('#<portlet:namespace />progress').setStyle('width', percentLoaded + '%');
		}

		function <portlet:namespace />handleFileSuccess(file, message) {
			A.one('#<portlet:namespace />toolbar').hide();

			var response = A.JSON.parse(message);

			if (response.message == "fail") {
				<portlet:namespace />handleFileError(file, message);

				return;
			}

			if ((response.message == "complete") || (response.message == "file-exists")) {
				var fileItem = A.one('#' + file.uniqueIdentifier);

				fileItem.one('.file').set("value", JSON.stringify(response.file));

				dynamicUploader.updateMessage("<liferay-ui:message key="file-ready-to-be-saved" unicode="<%= true %>" />", "success");
			}
			else if (response.message == "success") {
				var fileItem = A.one('#' + file.uniqueIdentifier);

				fileItem.one('.file').set("value", JSON.stringify(response.fileObject));

				dynamicUploader.updateMessage("<liferay-ui:message key="file-ready-to-be-saved" unicode="<%= true %>" />", "success");
			}

			A.one('#<portlet:namespace />saveButton').show();
		}

		function <portlet:namespace />handleLargeFileValidated(file) {
			<portlet:namespace />generateToken(dynamicUploader, resumableUploader, false);

			var fileItem = A.one('#<portlet:namespace />fileInfo .aui-helper-hidden.file-item');

			fileItem.set("id", A.Lang.String.escapeHTML(file.uniqueIdentifier));

			fileItem.one('.file').set("value", "");

			fileItem.one('.file-name').setContent(file.fileName);

			fileItem.show();

			A.one('#<portlet:namespace />pauseButton').on(
				'click',
				function() {
					A.one('#<portlet:namespace />pauseButton').hide();
					A.one('#<portlet:namespace />resumeButton').show();

					dynamicUploader.updateMessage("<liferay-ui:message key="paused" unicode="<%= true %>" />...", "error");

					resumableUploader.pause();
				}
			);

			A.one('#<portlet:namespace />resumeButton').on(
				'click',
				function() {
					A.one('#<portlet:namespace />pauseButton').show();
					A.one('#<portlet:namespace />resumeButton').hide();

					<portlet:namespace />displayProgressMessage(file);

					resumableUploader.upload();
				}
			);

			A.one('#<portlet:namespace />toolbar').show();
		}

		function <portlet:namespace />validateFile(file, event) {
			var uploader;

			if (dynamicUploader.uploaderType.val() == 'resumableUploader') {
				uploader = dynamicUploader.resumableUploader;
			}
			else {
				uploader = dynamicUploader.regularUploader;
			}

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="validateTicketAttachment" />',
				{
					data: {
						<portlet:namespace />fileName: file.fileName,
						<portlet:namespace />ticketEntryId: <%= ticketEntryId %>,
						<portlet:namespace />validateDuplicate: <%= ticketWorker ? String.valueOf(Boolean.FALSE) : String.valueOf(Boolean.TRUE) %>
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response.message != 'success') {
								<portlet:namespace />handleFileError(file, JSON.stringify(response));

								uploader.removeFile(file);
							}
							else {
								uploader.fire("fileValidated", file, event);
							}
						}
					}
				}
			);
		}
	</aui:script>

	<aui:script>
		function <portlet:namespace />generateToken(dynamicUploader, resumableUploader, overwrite) {
			var A = AUI();

			var token = A.one("#<portlet:namespace />token");

			if (overwrite || !token.get("value")) {
				A.io.request(
					'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="uploadToken" />',
					{
						data: {
							<portlet:namespace />fileRepositoryId: '<%= HtmlUtil.escape(fileRepositoryId) %>',
							<portlet:namespace />ticketEntryId: <%= ticketEntryId %>
						},
						dataType: 'json',
						method: 'post',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								if (response.message == 'success') {
									token.set("value", response.token);

									resumableUploader.upload();
								}
								else {
									dynamicUploader.updateMessage("<liferay-ui:message key="there-was-an-unexpected-error.-please-refresh-the-current-page" unicode="<%= true %>" />", "error");
								}
							}
						}
					}
				);
			}
			else {
				resumableUploader.upload();
			}
		}
	</aui:script>
</c:if>