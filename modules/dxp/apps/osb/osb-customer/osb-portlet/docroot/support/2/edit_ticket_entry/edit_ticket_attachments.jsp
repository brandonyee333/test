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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

int[] userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());

FileRepository fileRepository = SupportUtil.getFileRepository(ticketEntry);

String fileRepositoryId = StringPool.BLANK;

if (fileRepository != null) {
	fileRepositoryId = fileRepository.getFileRepositoryId();
}

boolean closed = (Boolean)request.getAttribute("edit_ticket_entry.jsp-closed");
boolean hasUpdateAdvanced = (Boolean)request.getAttribute("edit_ticket_entry.jsp-hasUpdateAdvanced");
boolean ticketWorker = (Boolean)request.getAttribute("edit_ticket_entry.jsp-ticketWorker");

PortletURL portletURL = (PortletURL)request.getAttribute("edit_ticket_entry.jsp-portletURL");
%>

<liferay-ui:error exception="<%= FileRepositoryNotAvailableException.class %>" message="file-servers-are-not-available-please-contact-your-support-manager" />

<div class="attachments">

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

	int[] types = {TicketAttachmentConstants.TYPE_HOTFIX, TicketAttachmentConstants.TYPE_LARGE_FILE, TicketAttachmentConstants.TYPE_LARGE_HOTFIX, TicketAttachmentConstants.TYPE_NONE};

	if (OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.UPDATE_BASIC)) {
		types = ArrayUtil.append(TicketAttachmentConstants.TYPES, TicketAttachmentConstants.TYPES_LARGE);
	}

	List<TicketAttachment> ticketAttachments = ticketEntry.getTicketAttachments(types, userVisibilities);
	%>

	<liferay-ui:search-container
		emptyResultsMessage="this-support-ticket-does-not-have-any-file-attachments"
		headerNames="<%= StringUtil.merge(headerNames) %>"
	>
		<liferay-ui:search-container-results
			results="<%= ticketAttachments %>"
		/>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.TicketAttachment"
			escapedModel="<%= true %>"
			modelVar="ticketAttachment"
		>

			<%
			LiferayPortletURL rowURL = null;

			if (OSBTicketAttachmentPermission.contains(permissionChecker, ticketAttachment, OSBActionKeys.VIEW)) {
				rowURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

				rowURL.setCopyCurrentRenderParameters(false);
				rowURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
				rowURL.setResourceID("ticketAttachment");
			}
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="file-name"
				value="<%= ticketAttachment.getFileName() %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="size"
				value='<%= TextFormatter.formatStorageSize((double)ticketAttachment.getFileSize(), locale) + "k" %>'
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="type"
				value="<%= LanguageUtil.get(request, ticketAttachment.getTypeLabel()) %>"
			/>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="attached-by"
			>
				<%= ticketAttachment.getUserName() %>

				<%
				AccountEntry accountEntry = ticketEntry.getAccountEntry();
				%>

				<liferay-util:include page="/support/2/common/user_badge.jsp" servletContext="<%= application %>">
					<portlet:param name="partnerEntryId" value="<%= String.valueOf(accountEntry.getPartnerEntryId()) %>" />
					<portlet:param name="userId" value="<%= String.valueOf(ticketAttachment.getUserId()) %>" />
				</liferay-util:include>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				href="<%= rowURL %>"
				name="attached-date"
				value="<%= longDateFormatDate.format(ticketAttachment.getCreateDate()) %>"
			/>

			<c:if test="<%= userVisibilities.length > 1 %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="visibility"
					value="<%= LanguageUtil.get(request, ticketAttachment.getVisibilityLabel()) %>"
				/>
			</c:if>

			<c:if test="<%= ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="delete-date"
					value='<%= (ticketAttachment.getDeleteDate() != null) ? longDateFormatDate.format(ticketAttachment.getDeleteDate()) : LanguageUtil.get(request, "n-a") %>'
				/>
			</c:if>

			<c:if test="<%= ticketWorker %>">
				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="replicate"
					value='<%= ArrayUtil.contains(TicketAttachmentConstants.TYPES_LARGE, ticketAttachment.getType()) ? String.valueOf(ticketAttachment.getReplicate()) : LanguageUtil.get(request, "n-a") %>'
				/>
			</c:if>

			<liferay-ui:search-container-column-jsp
				align="right"
				path="/support/2/edit_ticket_entry/ticket_attachment_action.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>

	<c:if test="<%= !ticketAttachments.isEmpty() && !screenShareMode && liferayIncOrg %>">
		<portlet:resourceURL id="ticketAttachmentsZipFile" var="ticketAttachmentsZipFileURL">
			<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
		</portlet:resourceURL>

		<div class="download-all">
			<a href="<%= ticketAttachmentsZipFileURL %>"><liferay-ui:message key="download-all" /></a>
		</div>
	</c:if>
</div>

<c:if test="<%= (!closed || (hasUpdateAdvanced && liferayIncOrg)) && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT) %>">
	<portlet:actionURL name="addTicketAttachment" var="addTicketAttachmentURL">
		<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
		<portlet:param name="generalTab" value="attachments" />
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
	</portlet:actionURL>

	<aui:form action="<%= addTicketAttachmentURL %>" class="uni-form" enctype="multipart/form-data" method="post" name="fm5">
		<aui:input name="redirect" type="hidden" value="<%= portletURL.toString() %>" />
		<aui:input name="ticketEntryId" type="hidden" value="<%= ticketEntry.getTicketEntryId() %>" />

		<div class="attachments">
			<div class="clearfix lfr-dynamic-uploader" id="<portlet:namespace />uploaderWrapper">
				<aui:input name="fileRepositoryId" type="hidden" value="<%= HtmlUtil.escapeAttribute(fileRepositoryId) %>" />
				<aui:input name="token" type="hidden" value="" />
				<aui:input name="uploaderType" type="hidden" />

				<div class="lfr-message-container" id="<portlet:namespace />messageContainer"></div>

				<div class="lfr-upload-container" id="<portlet:namespace />uploadContainer">
					<div class="upload-area" id="<portlet:namespace />uploadArea">
						<h4 class="drop-file-text">
							<liferay-ui:message key="drop-file-here-to-upload" />

							<br />

							<liferay-ui:message key="or" />
						</h4>

						<aui:button name="selectButton" value="select-file" />

						<aui:input cssClass="hide" label="" name="fileInputField" type="file" />
					</div>

					<div id="<portlet:namespace />fileInfo">

						<%
						for (int i = 1; i <= 3; i++) {
						%>

							<div class="file-item hide">
								<aui:input cssClass="file" name='<%= "file" + i %>' type="hidden" value="" />

								<div class="content-column w40">
									<span class="txt-b">
										<liferay-ui:message key="file" />:
									</span>
									<span class="file-name"></span>
								</div>

								<div class="content-column hotfix-display w30">
									<c:choose>
										<c:when test="<%= ticketWorker %>">
											<span class="txt-b">
												<liferay-ui:message key="hotfix" />:
											</span>

											<aui:select name='<%= "type" + i %>'>
												<aui:option label="no" value="<%= TicketAttachmentConstants.TYPE_NONE %>" />
												<aui:option label="yes" value="<%= TicketAttachmentConstants.TYPE_HOTFIX %>" />
											</aui:select>
										</c:when>
										<c:otherwise>
											<aui:input name='<%= "type" + i %>' type="hidden" value="<%= TicketAttachmentConstants.TYPE_NONE %>" />
										</c:otherwise>
									</c:choose>
								</div>

								<div class="content-column w30">
									<c:choose>
										<c:when test="<%= userVisibilities.length > 1 %>">
											<span class="txt-b">
												<liferay-ui:message key="visibility" />:
											</span>

											<aui:select name='<%= "visibility" + i %>'>

												<%
												for (int userVisibility : userVisibilities) {
												%>

													<aui:option label="<%= VisibilityConstants.toLabel(userVisibility) %>" value="<%= userVisibility %>" />

												<%
												}
												%>

											</aui:select>
										</c:when>
										<c:otherwise>
											<aui:input name='<%= "visibility" + i %>' type="hidden" value="<%= VisibilityConstants.PUBLIC %>" />
										</c:otherwise>
									</c:choose>
								</div>
							</div>

						<%
						}
						%>

					</div>

					<div class="hide toolbar" id="<portlet:namespace />toolbar">
						<a class="hide pull-right resume-button" href="javascript:;" id="<portlet:namespace />resumeButton"></a>

						<a class="cancel-button hide pull-right" href="javascript:;" id="<portlet:namespace />cancelButton"></a>

						<a class="pause-button pull-right" href="javascript:;" id="<portlet:namespace />pauseButton"></a>

						<span class="clearfix progress-bar" id="<portlet:namespace />progressBar">
							<span class="progress" id="<portlet:namespace />progress"></span>
						</span>
					</div>
				</div>

				<div class="hide lfr-fallback-container" id="<portlet:namespace />fallbackContainer">
					<c:choose>
						<c:when test="<%= userVisibilities.length > 1 %>">
							<div class="clearfix">
								<div class="content-column w10">
									<span class="txt-b">
										<liferay-ui:message key="visibility" />:
									</span>
								</div>

								<div class="content-column w90">
									<aui:select name="visibility">

										<%
										for (int userVisibility : userVisibilities) {
										%>

											<aui:option label="<%= LanguageUtil.get(request, VisibilityConstants.toLabel(userVisibility)) %>" value="<%= userVisibility %>" />

										<%
										}
										%>

									</aui:select>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<aui:input name="visibility" type="hidden" value="<%= VisibilityConstants.PUBLIC %>" />
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

									<aui:input label='<%= "file" + i %>' name='<%= "file" + i %>' onChange="AUI().one('#<portlet:namespace />saveButton').show();" type="file" />
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
				<c:when test="<%= ticketWorker && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) && (ticketEntry.getStatus() != TicketEntryConstants.STATUS_RESOLVED_IN_PRODUCTION) %>">
					<div id="<portlet:namespace />needResponseForm">
						<span class="txt-b">
							<liferay-ui:message key="need-response-from" />:
						</span>

						<c:if test="<%= ArrayUtil.contains(userVisibilities, VisibilityConstants.PUBLIC) %>">
							<aui:input label="" name="pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_CUSTOMER %>" />

							<liferay-ui:message key="customer" />
						</c:if>

						<c:if test="<%= ArrayUtil.contains(userVisibilities, VisibilityConstants.WORKERS) %>">
							<aui:input label="" name="pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_PARTNER %>" />

							<liferay-ui:message key="partner" />
						</c:if>

						<c:if test="<%= (ticketEntry.getEscalationLevel() != TicketEntryConstants.ESCALATION_LEVEL_P1) && ArrayUtil.contains(userVisibilities, VisibilityConstants.LIFERAY_INC) %>">
							<aui:input label="" name="pendingTypes" type="checkbox" value="<%= TicketFlagConstants.TYPE_PENDING_LIFERAY %>" />

							<liferay-ui:message key="liferay" />
						</c:if>
					</div>
				</c:when>
				<c:otherwise>
					<aui:input label="" name="pendingTypes" type="hidden" value="<%= (ticketEntry.getEscalationLevel() == TicketEntryConstants.ESCALATION_LEVEL_P1) ? TicketFlagConstants.TYPE_PENDING_PARTNER : TicketFlagConstants.TYPE_PENDING_LIFERAY %>" />
				</c:otherwise>
			</c:choose>

			<div class="button-holder hide" id="<portlet:namespace />switchButtonHolder"></div>

			<br />

			<div class="button-holder">
				<aui:button cssClass="hide" name="saveButton" type="submit" value="save" />
			</div>
		</div>
	</aui:form>
</c:if>

<aui:script use="aui-base">
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

				alert('<%= UnicodeLanguageUtil.get(request, "document-names-must-end-with-one-of-the-following-extensions") %> <%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), StringPool.COMMA_AND_SPACE) %>');

				fileField.val('');
			}
		}
	}

	for (var i = 1; i < 4; i++) {
		var fileField = A.one('#<portlet:namespace />file' + i);

		if (fileField) {
			fileField.on('change', <portlet:namespace />onFileChange);

			<portlet:namespace />validateFile(fileField);
		}
	}
</aui:script>

<c:if test="<%= (!closed || (hasUpdateAdvanced && liferayIncOrg)) && OSBTicketEntryPermission.contains(permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT) %>">
	<portlet:resourceURL id="tempTicketAttachment" var="tempTicketAttachmentURL">
		<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
	</portlet:resourceURL>

	<%
	String fileRepositoryURL = StringPool.BLANK;

	try {
		fileRepositoryURL = FileRepositoryUtil.getFileRepositoryURL(fileRepositoryId, FileRepositoryUtil.PATH_UPLOAD);
	}
	catch (Exception e) {
	}
	%>

	<aui:script use="aui-io,aui-template-deprecated,liferay-dynamic-uploader">
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
					var token = A.one('#<portlet:namespace />token');

					queryParam = {
						cmd: 'upload',
						token: token.get('value')
					};

					return queryParam;
				},
				simultaneousUploads: 1,
				target: '<%= fileRepositoryURL %>',
				testChunks: true,
				throttleProgressCallbacks: 1
			}
		);

		resumableUploader.on('fileAdded', <portlet:namespace />displayProgressMessage);
		resumableUploader.on('fileAdded', <portlet:namespace />validateFile);
		resumableUploader.on('fileError', <portlet:namespace />handleFileError);
		resumableUploader.on('fileProgress', <portlet:namespace />handleFileProgress);
		resumableUploader.on('fileSuccess', <portlet:namespace />handleFileSuccess);
		resumableUploader.on('fileValidated', <portlet:namespace />handleLargeFileValidated);

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

		regularUploader.on('fileAdded', <portlet:namespace />displayProgressMessage);
		regularUploader.on('fileAdded', <portlet:namespace />validateFile);
		regularUploader.on('fileSuccess', <portlet:namespace />handleFileSuccess);
		regularUploader.on('fileValidated', <portlet:namespace />handleFileValidated);

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
			dynamicUploader.updateMessage('<liferay-ui:message key="uploading" unicode="<%= true %>" />...', 'progress');
		}

		function <portlet:namespace />generateToken(dynamicUploader, resumableUploader, overwrite) {
			var token = A.one('#<portlet:namespace />token');

			if (token && (overwrite || !token.get('value'))) {
				A.io.request(
					'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="uploadToken" />',
					{
						data: {
							<portlet:namespace />fileRepositoryId: '<%= HtmlUtil.escape(fileRepositoryId) %>',
							<portlet:namespace />ticketEntryId: <%= ticketEntry.getTicketEntryId() %>
						},
						dataType: 'JSON',
						method: 'POST',
						on: {
							success: function() {
								var response = this.get('responseData');

								if (response.message == 'success') {
									token.set('value', response.token);

									resumableUploader.upload();
								}
								else {
									dynamicUploader.updateMessage('<liferay-ui:message key="there-was-an-unexpected-error.-please-refresh-the-current-page" unicode="<%= true %>" />', 'error');
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

		function <portlet:namespace />handleFileValidated(file) {
			var fileItem = A.one('#<portlet:namespace />fileInfo .hide.file-item');

			fileItem.set('id', A.Lang.String.escapeHTML(file.uniqueIdentifier));

			fileItem.one('.file').set('value', '');

			fileItem.one('.file-name').setContent(file.fileName);

			fileItem.show();

			regularUploader.upload();
		}

		function <portlet:namespace />handleFileError(file, message) {
			var response = A.JSON.parse(message);

			A.one('#<portlet:namespace />toolbar').hide();

			var errorMessage = '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

			if (response.message == 'fail') {
				if (response.exception) {
					if (response.exception === '<%= DuplicateTicketAttachmentException.class.getName() %>') {
						errorMessage = '<liferay-ui:message key="please-enter-a-unique-document-name" unicode="<%= true %>" />';
					}
					else if (response.exception === '<%= FileNameException.class.getName() %>') {
						errorMessage = '<liferay-ui:message key="please-enter-a-file-with-a-valid-file-name" unicode="<%= true %>" />';
					}
				}
			}
			else if (response.message == 'invalid-file') {
				errorMessage = '<liferay-ui:message key="please-upload-a-valid-file" unicode="<%= true %>" />';
			}
			else if (response.message == 'invalid-session') {
				errorMessage = '<liferay-ui:message key="there-was-an-unexpected-error.-please-refresh-the-current-page" unicode="<%= true %>" />';
			}

			dynamicUploader.updateMessage(errorMessage, 'error');
		}

		function <portlet:namespace />handleFileProgress(file) {
			var percentLoaded = Math.floor(file.progress() * 100);

			A.one('#<portlet:namespace />progress').setStyle('width', percentLoaded + '%');
		}

		function <portlet:namespace />handleFileSuccess(file, message) {
			A.one('#<portlet:namespace />toolbar').hide();

			var response = A.JSON.parse(message);

			if (response.message == 'fail') {
				<portlet:namespace />handleFileError(file, message);

				return;
			}

			var fileItem = A.one('#' + file.uniqueIdentifier);

			if (fileItem) {
				var response;

				if ((response.message == 'complete') || (response.message == 'file-exists')) {
					response = JSON.stringify(response.file);
				}
				else if (response.message == 'success') {
					response = JSON.stringify(response.fileObject);
				}

				fileItem.one('.file').set('value', response);

				dynamicUploader.updateMessage('<liferay-ui:message key="file-ready-to-be-saved" unicode="<%= true %>" />', 'success');
			}

			A.one('#<portlet:namespace />saveButton').show();
		}

		function <portlet:namespace />handleLargeFileValidated(file) {
			<portlet:namespace />generateToken(dynamicUploader, resumableUploader, false);

			var fileItem = A.one('#<portlet:namespace />fileInfo .hide.file-item');

			fileItem.set('id', A.Lang.String.escapeHTML(file.uniqueIdentifier));

			fileItem.one('.file').set('value', '');

			fileItem.one('.file-name').setContent(file.fileName);

			fileItem.show();

			A.one('#<portlet:namespace />pauseButton').on(
				'click',
				function() {
					A.one('#<portlet:namespace />pauseButton').hide();
					A.one('#<portlet:namespace />resumeButton').show();

					dynamicUploader.updateMessage('<liferay-ui:message key="paused" unicode="<%= true %>" />...', 'error');

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
						<portlet:namespace />ticketEntryId: <%= ticketEntry.getTicketEntryId() %>,
						<portlet:namespace />validateDuplicate: <%= ticketWorker ? Boolean.FALSE.toString() : Boolean.TRUE.toString() %>
					},
					dataType: 'JSON',
					method: 'POST',
					on: {
						success: function() {
							var response = this.get('responseData');

							if (response.message != 'success') {
								<portlet:namespace />handleFileError(file, JSON.stringify(response));

								uploader.removeFile(file);
							}
							else {
								uploader.fire('fileValidated', file, event);
							}
						}
					}
				}
			);
		}
	</aui:script>
</c:if>