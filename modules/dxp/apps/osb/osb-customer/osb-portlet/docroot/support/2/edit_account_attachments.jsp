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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			window.close();
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

		AccountEntry accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);

		long accountProjectId = ParamUtil.getLong(request, "accountProjectId");

		PortletURL portletURL = renderResponse.createRenderURL();
		%>

		<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountEntryId, OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">
			<portlet:actionURL name="addAccountAttachment" var="addAccountAttachmentURL">
				<portlet:param name="mvcPath" value="/support/2/edit_account_attachments.jsp" />
			</portlet:actionURL>

			<aui:form action="<%= addAccountAttachmentURL %>" cssClass="uni-form" enctype="multipart/form-data" method="post" name="fm">
				<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
				<aui:input name="accountProjectId" type="hidden" value="<%= accountProjectId %>" />

				<div class="clearfix section">
					<div class="pull-left">
						Edit Attachments for Project: <%= HtmlUtil.escape(accountEntry.getName()) %>
					</div>
				</div>

				<liferay-ui:error exception="<%= AccountAttachmentSizeException.class %>" message="please-upload-a-file-less-than-100-mb" />
				<liferay-ui:error exception="<%= DuplicateAccountAttachmentException.class %>" message="please-enter-a-unique-document-name" />
				<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />

				<h1 class="section-heading">
					<liferay-ui:message key="attachments" />
				</h1>

				<div>

					<%
					List<String> headerNames = new ArrayList<String>();

					headerNames.add("file-name");
					headerNames.add("size");
					headerNames.add("type");
					headerNames.add("attached-by");
					headerNames.add("attached-date");

					headerNames.add(StringPool.BLANK);

					SearchContainer searchContainer = new SearchContainer(renderRequest, null, null, SearchContainer.DEFAULT_CUR_PARAM, SearchContainer.DEFAULT_DELTA, portletURL, headerNames, "this-project-does-not-have-any-file-attachments");

					List results = accountEntry.getAccountAttachments(accountProjectId);

					searchContainer.setResults(results);

					int total = results.size();

					searchContainer.setTotal(total);

					List resultRows = searchContainer.getResultRows();

					for (int i = 0; i < results.size(); i++) {
						AccountAttachment accountAttachment = (AccountAttachment)results.get(i);

						String fileName = accountAttachment.getFileName();
						long fileSize = accountAttachment.getFileSize();

						ResultRow row = new ResultRow(accountAttachment, accountAttachment.getAccountAttachmentId(), i);

						LiferayPortletURL rowURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

						rowURL.setCopyCurrentRenderParameters(false);
						rowURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
						rowURL.setResourceID("accountAttachment");

						// File name

						row.addText(fileName, rowURL.toString());

						// Size

						row.addText(TextFormatter.formatStorageSize((double)fileSize, locale) + "k", rowURL.toString());

						// Type

						row.addText(LanguageUtil.get(request, accountAttachment.getTypeLabel()), rowURL.toString());

						// Attached by

						row.addText(HtmlUtil.escape(accountAttachment.getUserName()), rowURL.toString());

						// Attached date

						row.addText(longDateFormatDate.format(accountAttachment.getCreateDate()), rowURL.toString());

						// Action

						PortletBag portletBag = PortletBagPool.get(OSBPortletKeys.OSB_SUPPORT);

						ServletContext servletContext = portletBag.getServletContext();

						row.addJSP("/support/2/account_attachment_action.jsp", servletContext, request, response);

						// Add result row

						resultRows.add(row);
					}
					%>

					<liferay-ui:search-iterator
						paginate="<%= false %>"
						searchContainer="<%= searchContainer %>"
					/>

					<div>
						<table class="lfr-table">
							<tr>
								<th>
									<strong><liferay-ui:message key="upload-files" /></strong>
								</th>
							</tr>

							<%
							for (int i = 1; i <= 3; i++) {
							%>

								<tr>
									<td>
										<aui:input label='<%= "file" + i %>' name='<%= "file" + i %>' type="file" />
									</td>
								</tr>

							<%
							}
							%>

						</table>

						<div class="button-holder">
							<aui:button type="submit" value="save" />

							<aui:button onClick="javascript:window.close();" value="cancel" />
						</div>
					</div>
				</div>
			</aui:form>

			<aui:script use="aui-base">
				function <portlet:namespace />onFileChange(event) {
					<portlet:namespace />validateFile(event.currentTarget);
				}

				function <portlet:namespace />validateFile(fileField) {
					var value = fileField.val();

					if (value) {
						var extension = value.substring(value.lastIndexOf('.')).toLowerCase();

						var validExtensions = ['<%= StringUtil.merge(PrefsPropsUtil.getStringArray(PropsKeys.DL_FILE_EXTENSIONS, StringPool.COMMA), "', '") %>'];

						if ((A.Array.indexOf(validExtensions, '*') == -1) && (A.Array.indexOf(validExtensions, extension) == -1)) {
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
		</c:if>
	</c:otherwise>
</c:choose>