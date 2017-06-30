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

<%@ include file="/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getUnreleasedAppVersion();

DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

boolean displayContextWarning = false;
boolean displayPACLError = false;
%>

<div class="marketplace-developer-apps edit-app-version">
	<liferay-ui:header
		title='<%= Validator.isNull(appVersion.getVersion()) ? "add-app-version" : "edit-app-version" %>'
	/>

	<liferay-util:include page="/marketplace_developer_apps/app_breadcrumb.jsp" servletContext="<%= application %>">
		<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
	</liferay-util:include>

	<portlet:actionURL name="updateAppEntryVersion" var="editAppEntryVersionURL" />

	<aui:form action="<%= editAppEntryVersionURL %>" enctype="multipart/form-data" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_developer_apps/edit_app_version.jsp" />
		<aui:input name="redirect" type="hidden" />
		<aui:input name="appEntryId" type="hidden" value="<%= String.valueOf(appEntryId) %>" />

		<liferay-ui:error exception="<%= AppEntryChangeLogException.class %>" message="please-explain-whats-new-with-this-version" />
		<liferay-ui:error exception="<%= AppEntryVersionException.class %>" message="please-enter-a-valid-version.-it-may-only-contain-numbers-and-periods" />
		<liferay-ui:error exception="<%= RequiredAppPackagePluginException.class %>" message="please-remove-empty-packages" />

		<aui:model-context bean="<%= appVersion %>" model="<%= AppVersion.class %>" />

		<aui:fieldset>
			<div class="version-number">
				<aui:input cssClass="required" disabled="<%= !appVersion.isNewRelease() %>" label="version-number" name="version" />
			</div>

			<c:if test="<%= AppVersionLocalServiceUtil.getAppVersionsCount(appEntryId, WorkflowConstants.STATUS_ANY) > 1 %>">
				<liferay-util:include page="/common/localized_input.jsp" servletContext="<%= application %>">
					<liferay-util:param name="defaultLanguageId" value="en_US" />
					<liferay-util:param name="label" value='<%= LanguageUtil.get(pageContext, "whats-new") %>' />
					<liferay-util:param name="name" value="changeLog" />
					<liferay-util:param name="required" value="<%= appEntry.hasMultipleNewVersions() ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
					<liferay-util:param name="value" value="<%= appVersion.getChangeLog() %>" />
				</liferay-util:include>
			</c:if>
		</aui:fieldset>

		<aui:fieldset>
			<aui:input name="appEntryId" type="hidden" value="<%= String.valueOf(appEntryId) %>" />

			<liferay-ui:error exception="<%= FileSizeException.class %>" message="please-enter-a-file-with-a-valid-file-size" />

			<div>
				<span class="aui-field-label required">
					<liferay-ui:message key="upload-liferay-plugin-packages" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="please-be-sure-to-specify-liferay-compatability-through-the-appropriate-properties-or-xml-files-in-your-plugins" />
				</span>

				<span class="aui-field-help">
					<liferay-ui:message key="you-can-select-multiple-files-for-upload" />
				</span>
			</div>

			<c:if test="<%= appVersion.isAddResourcesImporter() %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="your-app-will-have-resources-importer-web-automatically-added" />

					<span>
						<liferay-ui:message arguments='<%= "/documentation/liferay-portal/6.2/development/-/ai/importing-resources-with-your-themes-liferay-portal-6-2-dev-guide-09-en" %>' key="please-read-our-requirements-for-stand-alone-themes-for-more-information" />
					</span>
				</div>
			</c:if>

			<div class="app-entry-uploader">
				<div class="liferay-portal-builds">
					<div class="header">
						<liferay-ui:message key="liferay-versions" />
					</div>

					<div class="content">

						<%
						List<PortalRelease> portalReleases = PortalReleaseLocalServiceUtil.getPortalReleases(true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new PortalReleaseBuildNumberComparator(false));

						for (PortalRelease portalRelease : portalReleases) {
							int buildNumber = portalRelease.getBuildNumber();

							if (appVersion.isPaclEnabled() && !portalRelease.isPaclSupport()) {
								continue;
							}

							if (!developerEntry.isLiferayInc() && portalRelease.isHidden()) {
								continue;
							}

							AppPackage appPackage = AppPackageLocalServiceUtil.fetchAppPackage(appVersion.getAppVersionId(), portalRelease.getBuildNumber());
						%>

							<aui:input checked="<%= appPackage != null %>" cssClass="liferay-version-checkbox" id="<%= String.valueOf(buildNumber) %>" ignoreRequestValue="<%= true %>" label="<%= portalRelease.getVersionName() %>" name="compatibility" type="checkbox" value="<%= buildNumber %>" />

						<%
						}
						%>

					</div>
				</div>

				<div class="app-packages-uploader" id="<portlet:namespace />appPackagesUploader">

					<%
					AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

					List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppPackageCompatibilityComparator(false));

					for (AppPackage appPackage : appPackages) {
						PortalRelease portalRelease = PortalReleaseLocalServiceUtil.getPortalRelease(appPackage.getCompatibility());
					%>

						<div class="app-package-uploader" data-appPackageId="<%= appPackage.getAppPackageId() %>" data-buildNumber="<%= appPackage.getCompatibility() %>">
							<div class="header">
								<%= portalRelease.getVersionName() %>
							</div>

							<div class="content">

								<%
								boolean locked = false;

								if (!appVersion.isNewRelease()) {
									AppPackage approvedAppPackage = AppPackageLocalServiceUtil.fetchAppPackage(approvedAppVersion.getAppVersionId(), appPackage.getCompatibility());

									if (approvedAppPackage != null) {
										locked = true;
									}
								}
								%>

								<c:if test="<%= locked %>">
									<span class="aui-field-help">
										<liferay-ui:message key="uploading-to-this-package-is-locked-please-create-a-new-version-to-update" />
									</span>
								</c:if>

								<div id="app-package-uploader-form">
									<div class="buttons">
										<div class="upload-area">
											<liferay-ui:message key="drop-file-here-to-upload-or-click-to-browse-for-files" />
										</div>
									</div>
								</div>

								<ul class="file-list">
								</ul>

								<ul class="app-package-plugins">
									<%@ include file="/marketplace_developer_apps/app_pacakge_plugins.jspf" %>
								</ul>

								<%
								List<AssetAttachment> sourceCodeAssetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppPackage.class.getName(), appPackage.getAppPackageId(), AssetAttachmentConstants.TYPE_PACKAGE_SRC, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
								%>

								<c:choose>
									<c:when test="<%= sourceCodeAssetAttachments.isEmpty() %>">
										<span class="source-code">
											<a class="action upload" href="javascript:;"><liferay-ui:message key="upload-source-code" /></a>

											<liferay-ui:icon-help message="optionally-provide-your-source-code-to-be-hosted-on-liferay-com" />
										</span>
									</c:when>
									<c:otherwise>

										<%
										AssetAttachment sourceCodeAssetAttachment = sourceCodeAssetAttachments.get(0);
										%>

										<span class="file source-code" data-assetAttachmentId="<%= sourceCodeAssetAttachment.getAssetAttachmentId() %>">
											<span class="actions">
												<a class="action delete" href="javascript:;"><liferay-ui:message key="delete" /></a>
											</span>

											<%= sourceCodeAssetAttachment.getFileName() %>
										</span>
									</c:otherwise>
								</c:choose>

								<div class="compatibility-plus">
									<label>
										<input <%= appPackage.isCompatibilityPlus() ? "checked" : StringPool.BLANK %> class="liferay-compatibility-plus" type="checkbox" />

										<liferay-ui:message key="compatibility-plus" />
									</label>
								</div>
							</div>
						</div>

					<%
					}
					%>

				</div>

				<div class="portlet-msg-alert<%= displayContextWarning ? StringPool.BLANK : " aui-helper-hidden" %>" id="<portlet:namespace />warningMessage">
					<liferay-ui:message key="some-of-your-plugins-have-the-same-deployment-contexts" />
				</div>

				<c:if test="<%= displayPACLError %>">
					<div class="portlet-msg-error" id="<portlet:namespace />errorMessage">
						<liferay-ui:message key="some-of-your-plugins-do-not-have-liferays-pacl-security-manager-enabled" />

						<span>
							<liferay-portlet:actionURL name="deleteInvalidAppPackagePlugins" var="deleteInvalidAppPackagePluginsURL">
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
							</liferay-portlet:actionURL>

							<a href="<%= deleteInvalidAppPackagePluginsURL %>"><liferay-ui:message key="delete-plugins-that-do-not-have-pacl-enabled" /></a>
						</span>
					</div>
				</c:if>
			</div>
		</aui:fieldset>

		<aui:button-row>
			<liferay-portlet:renderURL var="editAppEntryURL">
				<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
				<portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
			</liferay-portlet:renderURL>

			<aui:button cssClass="btn" onClick="<%= editAppEntryURL %>" value="back" />

			<aui:button cssClass="btn" onClick='<%= renderResponse.getNamespace() + "saveAsDraft();" %>' value="save-as-draft" />

			<aui:button cssClass="btn" type="submit" value="next" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	function <portlet:namespace />saveAsDraft() {
		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = "<portlet:renderURL><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>";

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>

<%
Date expirationDate = new Date(System.currentTimeMillis() + GetterUtil.getLong(PropsUtil.get(PropsKeys.SESSION_TIMEOUT)) * Time.MINUTE);

Ticket ticket = TicketLocalServiceUtil.addTicket(user.getCompanyId(), User.class.getName(), user.getUserId(), TicketConstants.TYPE_IMPERSONATE, null, expirationDate, new ServiceContext());
%>

<aui:script use="attachment-upload-html5,aui-io,aui-node,collection">
	var appPackagesUploader = A.one('#<portlet:namespace />appPackagesUploader');

	var attachmentUploads = {};

	var warningMessage = A.one('#<portlet:namespace />warningMessage');

	<%
	long maxFileSize = PrefsPropsUtil.getLong(PropsKeys.UPLOAD_SERVLET_REQUEST_IMPL_MAX_SIZE);
	%>

	var createAttachmentUpload = function(buildNumber) {
		var resumableUploader = new Resumable(
			{
				chunkSize: 100 * 1024 * 1024,
				simultaneousUploads: 1,
				target: '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="addAppPackagePlugin"><portlet:param name="actionName" value="addFile" /><portlet:param name="appVersionId" value="<%= String.valueOf(appVersion.getAppVersionId()) %>" /></liferay-portlet:actionURL>&<portlet:namespace />compatibility=' + buildNumber + '&<portlet:namespace />compatibilityPlus=true&ticketKey=<%= ticket.getKey() %>',
				testChunks: false,
				throttleProgressCallbacks: 1
			}
		);

		var attachmentUpload = new Liferay.AttachmentUploadHTML5(
			{
				dragAndDropNode: '.app-package-uploader[data-buildNumber=' + buildNumber + '] .upload-area',
				fileAddError: handleAttachmentFileAddError,
				fileList: '.app-package-uploader[data-buildNumber=' + buildNumber + '] .file-list',
				fileType: ['jar', 'war'],
				fileTypeErrorMessage: '<liferay-ui:message arguments='<%= new String[] {"[$FILE_NAME$]", "[$EXTENSIONS$]"} %>' key="x-is-not-a-valid-file-type.-valid-extensions-are-x" unicode="<%= true %>" />',
				fileTypeErrorMessageExtensionPlaceholder: '<%= UnicodeFormatter.toString("[$EXTENSIONS$]") %>',
				fileTypeErrorMessageFileNamePlaceholder: '<%= UnicodeFormatter.toString("[$FILE_NAME$]") %>',
				resumableUploader: resumableUploader,
				selectButton: '.app-package-uploader[data-buildNumber=' + buildNumber + '] .upload-area',
				uploadSuccess: handleAttachmentUploadSuccess
			}
		);

		attachmentUploads[buildNumber] = attachmentUpload;
	}

	var handleAttachmentFileAddError = function(file, errorCode, message) {
		var fileList = appPackagesUploader.one('.file-list');

		if (errorCode == SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT) {
			var message = '<liferay-ui:message arguments="<%= (maxFileSize / 1024 / 1024) %>" key="please-upload-a-file-less-than-x-mb" unicode="<%= true %>" />';

			var li = A.Node.create(
				'<li class="file error">' +
					'<span class="title">' + file.name + "</span>" +
					'<div class="message">' + message + "</div>" +
				'</li>'
			);

			fileList.append(li);
		}

		return;
	}

	var handleAttachmentUploadSuccess = function(resumableFile, data, elem) {
		var actionsContainer = elem.one('.actions');

		if (!data.message || (data.message != 'success')) {
			elem.addClass('error');

			var message = '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

			if (data.exception) {
				if (data.exception === "<%= DuplicateAppPackagePluginOwnerException.class.getName() %>") {
					message = '<liferay-ui:message key="context-path-is-already-used-by-another-developer" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= DuplicateAssetAttachmentFileNameException.class.getName() %>") {
					message = '<liferay-ui:message key="file-name-is-reserved" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginFileException.class.getName() %>") {
					message = '<liferay-ui:message key="unable-to-add-duplicated-file" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackageMarketplaceSupportException.class.getName() %>") {
					message = '<liferay-ui:message key="eligible-liferay-versions-were-not-found-in-liferay-plugin-package-properties" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginDeploymentContextException.class.getName() %>") {
					message = '<liferay-ui:message key="please-unset-recommended-deployment-context-in-liferay-plugin-package-properties" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginFileNameException.class.getName() %>") {
					message = '<liferay-ui:message key="file-names-cannot-contain-commas" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginFileNameLengthException.class.getName() %>") {
					message = '<liferay-ui:message key="file-name-is-too-long" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginNameException.class.getName() %>") {
					message = '<liferay-ui:message arguments='<%= "https://dev.liferay.com/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app" %>' key="file-name-does-not-follow-the-required-format" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginPACLException.class.getName() %>") {
					message = '<liferay-ui:message key="plugins-must-have-liferays-pacl-security-manager-enabled" unicode="<%= true %>" />';
				}
				else if ((data.exception === "<%= AppPackagePluginPluginPackageException.class.getName() %>") || (data.exception === "<%= AppPackagePluginVersionException.class.getName() %>")) {
					message = '<liferay-ui:message key="eligible-liferay-versions-were-not-found-in-liferay-plugin-package-properties" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginBundleSymbolicNameException.class.getName() %>") {
					message = '<liferay-ui:message key="bundle-symbolic-name-version-is-not-unique" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginRelengException.class.getName() %>") {
					message = '<liferay-ui:message key="liferays-apps-must-include-liferay-releng-properties" unicode="<%= true %>" />';
				}
				else if (data.exception === "<%= AppPackagePluginXMLException.class.getName() %>") {
					message = '<liferay-ui:message key="please-remove-liferay-plugin-package-xml-from-your-plugin" unicode="<%= true %>" />';
				}
			}

			var messageNode = A.Node.create('<div class="msg">' + message + '</div>');

			elem.append(messageNode);

			actionsContainer.remove();

			return;
		}

		var assetAttachment = data.assetAttachment;

		if (assetAttachment) {
			var actions = A.Node.create('<a class="action delete" href="javascript:;"><liferay-ui:message key="delete" /></a>');

			actionsContainer.setContent(actions);

			var fileElem = elem.cloneNode(true);

			fileElem.attr('data-assetAttachmentId', assetAttachment.assetAttachmentId);

			fileElem.removeClass('uploading');
			fileElem.removeClass('upload-file');

			if (assetAttachment.conflictingContextName) {
				fileElem.addClass('warn');

				warningMessage.show();
			}

			var appPackagePluginsList = elem.ancestor('.app-package-uploader').one('.app-package-plugins');

			appPackagePluginsList.append(fileElem);

			elem.remove(true);
		}
	}

	var sortAppPackageUploaders = function() {
		var appPackageUploaderNodes = appPackagesUploader.all('.app-package-uploader');

		appPackageUploaderNodes.remove();

		var appPackageUploaderDOMNodes = appPackageUploaderNodes.getDOMNodes();

		appPackageUploaderDOMNodes.sort(
			function(appPackageUploader1, appPackageUploader2) {
				var buildNumber1 = A.one(appPackageUploader1).getAttribute('data-buildNumber');
				var buildNumber2 = A.one(appPackageUploader2).getAttribute('data-buildNumber');

				if (buildNumber1 < buildNumber2) {
					return 1;
				}
				else if (buildNumber1 > buildNumber2) {
					return -1;
				}

				return 0;
			}
		);

		appPackagesUploader.append(appPackageUploaderDOMNodes);
	}

	appPackagesUploader.delegate(
		'click',
		function(event) {
			var target = event.currentTarget;

			var file = target.ancestor('.file');

			var url = '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="deleteAppPackagePlugin" />';

			if (file.hasClass('source-code')) {
				url = '<liferay-portlet:actionURL doAsUserId="<%= user.getUserId() %>" name="deleteAppPackageSrc" />';
			}

			A.io.request(
				url,
				{
					data: {
						<portlet:namespace />assetAttachmentId: file.attr('data-assetAttachmentId')
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response.message == 'success') {
								if (file.hasClass('source-code')) {
									var sourceCodeMessage = A.Node.create(
										'<a class="action upload" href="javascript:;"><liferay-ui:message key="upload-source-code" /></a> ' +
										'<liferay-ui:icon-help message="optionally-provide-your-source-code-to-be-hosted-on-liferay-com" />');

									file.removeClass('file');

									file.setContent(sourceCodeMessage);
								}
								else {
									file.remove(true);
								}
							}
							else {
								var msg = response.message || '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

								appPackagesUploader.one('.portlet-msg-error').setContent(msg).show();
							}
						}
					}
				}
			);
		},
		'.actions .delete'
	);

	appPackagesUploader.delegate(
		'click',
		function(event) {
			var target = event.currentTarget;

			var sourceCodeNode = target.ancestor('.source-code');
			var packageContainer = target.ancestor('.app-package-uploader');

			var sourceCodeInput = A.Node.create(
				'<span class="aui-field aui-field-text">' +
					'<span class="aui-field-content">' +
						'<label class="aui-field-label"><liferay-ui:message key="source-code" /></label>' +
						'<span class="aui-field-element">' +
							'<input class="aui-field-input aui-field-input-text" name="<portlet:namespace />srcFile' + packageContainer.attr('data-buildNumber') + '" type="file" />' +
						'</span>' +
					'</span>' +
				'</span>'
			);

			sourceCodeNode.setContent(sourceCodeInput);
		},
		'.source-code a.upload'
	);

	A.one('.liferay-portal-builds').delegate(
		'change',
		function(event) {
			var target = event.currentTarget;

			var compatibility = target.attr('value');

			if (target.attr('checked')) {
				A.io.request(
					'<portlet:actionURL name="addAppPackage" />',
					{
						data: {
							<portlet:namespace />appEntryId: <%= appEntryId %>,
							<portlet:namespace />appVersionId: <%= appVersion.getAppVersionId() %>,
							<portlet:namespace />compatibility: compatibility
						},
						dataType: 'json',
						method: 'get',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var appPackageUploaderNode = A.Node.create(
									'<div class="app-package-uploader" data-appPackageId="' + response.appPackageId + '" data-buildNumber="' + response.compatibility + '">' +
										'<div class="header">' +
											response.compatibilityLabel +
										'</div>' +
										'<div class="content">' +
											'<div id="app-package-uploader-form">' +
												'<div class="buttons">' +
													'<div class="upload-area"><liferay-ui:message key="drop-file-here-to-upload-or-click-to-browse-for-files" /></div>' +
												'</div>' +
											'</div>' +
											'<ul class="file-list">' +
											'</ul>' +
											'<ul class="app-package-plugins">' +
											'</ul>' +
											'<span class="source-code">' +
												'<a class="action upload" href="javascript:;"><liferay-ui:message key="upload-source-code" /></a>' +
												'<liferay-ui:icon-help message="optionally-provide-your-source-code-to-be-hosted-on-liferay-com" />' +
											'</span>' +
											'<div class="compatibility-plus">' +
												'<label>' +
													'<input checked="checked" class="liferay-compatibility-plus" type="checkbox" />' +
													'<liferay-ui:message key="compatibility-plus" />' +
												'</label>' +
											'</div>' +
										'</div>' +
									'</div>'
								);

								appPackagesUploader.append(appPackageUploaderNode);

								createAttachmentUpload(response.compatibility);

								sortAppPackageUploaders();
							}
						}
					}
				);
			}
			else {
				A.io.request(
					'<portlet:actionURL name="deleteAppPackage" />',
					{
						data: {
							<portlet:namespace />appVersionId: <%= appVersion.getAppVersionId() %>,
							<portlet:namespace />compatibility: compatibility
						},
						dataType: 'json',
						method: 'get',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var appPackageUploaderBox = A.one('.app-package-uploader[data-buildNumber=' + response.compatibility + ']');

								if (appPackageUploaderBox) {
									appPackageUploaderBox.remove(true);
								}

								var swfUploader = A.one('.lfr-upload-movie[data-swfId=' + compatibility + ']');

								if (swfUploader) {
									swfUploader.remove(true);
								}

								attachmentUploads[compatibility] = null;
							}
						}
					}
				);
			}
		},
		'input[type=checkbox]'
	);

	A.one('.app-packages-uploader').delegate(
		'change',
		function(event) {
			if (!confirm('<liferay-ui:message key="this-will-remove-all-incompatible-plugins.-do-you-want-to-continue" unicode="<%= true %>" />')) {
				location.reload();

				return;
			}

			var target = event.currentTarget;

			var appPackageUploader = target.ancestor('.app-package-uploader');

			var appPackageId = appPackageUploader.attr('data-apppackageid');

			var compatibilityPlus = target.attr('checked');

			A.io.request(
				'<portlet:actionURL name="updateAppPackage" />',
				{
					data: {
						<portlet:namespace />appPackageId: appPackageId,
						<portlet:namespace />compatibilityPlus: compatibilityPlus
					},
					dataType: 'json',
					method: 'post',
					on: {
						end: function(event, id, obj) {
							location.reload();
						}
					}
				}
			);
		},
		'input[type=checkbox]'
	);

	A.all('.app-package-uploader').each(
		function(node) {
			createAttachmentUpload(node.attr('data-buildNumber'));
		}
	);
</aui:script>