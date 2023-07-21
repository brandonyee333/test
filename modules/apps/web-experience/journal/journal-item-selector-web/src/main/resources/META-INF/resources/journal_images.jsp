<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
String[] imageExtensions = PrefsPropsUtil.getStringArray(PropsKeys.JOURNAL_IMAGE_EXTENSIONS, StringPool.COMMA);
JournalItemSelectorViewDisplayContext journalItemSelectorViewDisplayContext = (JournalItemSelectorViewDisplayContext)request.getAttribute(JournalItemSelectorView.JOURNAL_ITEM_SELECTOR_VIEW_DISPLAY_CONTEXT);
%>

<div class="container-fluid-1280 lfr-item-viewer" id="itemSelectorUploadContainer">
	<liferay-util:buffer
		var="selectFileHTML"
	>
		<label class="btn btn-default" for="<portlet:namespace />inputFile"><liferay-ui:message key="select-file" /></label>

		<input accept="<%= ArrayUtil.isEmpty(imageExtensions) ? "*" : StringUtil.merge(imageExtensions) %>" class="hide" id="<portlet:namespace />inputFile" type="file" />
	</liferay-util:buffer>

	<div class="drop-enabled drop-zone upload-view">
		<div id="uploadDescription">
			<strong><liferay-ui:message arguments="<%= selectFileHTML %>" key="drag-and-drop-to-upload-or-x" /></strong>
		</div>
	</div>
</div>

<aui:script use="liferay-item-selector-repository-entry-browser">
	new Liferay.ItemSelectorRepositoryEntryBrowser(
		{
			closeCaption: '<%= journalItemSelectorViewDisplayContext.getTitle(locale) %>',
			maxFileSize: '<%= PrefsPropsUtil.getLong(PropsKeys.DL_FILE_MAX_SIZE) %> ',
			on: {
				selectedItem: function(event) {
					Liferay.Util.getOpener().Liferay.fire('<%= journalItemSelectorViewDisplayContext.getItemSelectedEventName() %>', event);
				}
			},
			rootNode: '#itemSelectorUploadContainer',
			uploadItemReturnType: '<%= HtmlUtil.escapeAttribute(FileEntryItemSelectorReturnType.class.getName()) %>',
			uploadItemURL: '<%= journalItemSelectorViewDisplayContext.getUploadURL(liferayPortletResponse) %>',
			validExtensions: '<%= ArrayUtil.isEmpty(imageExtensions) ? "*" : StringUtil.merge(imageExtensions) %>'
		}
	);
</aui:script>