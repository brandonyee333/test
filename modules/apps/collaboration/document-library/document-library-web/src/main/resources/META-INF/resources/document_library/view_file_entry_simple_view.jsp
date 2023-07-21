<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRY);

FileVersion fileVersion = (FileVersion)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_VERSION);

if (fileVersion == null) {
	fileVersion = fileEntry.getFileVersion();
}

DLViewFileVersionDisplayContext dlViewFileVersionDisplayContext = dlDisplayContextProvider.getDLViewFileVersionDisplayContext(request, response, fileVersion);
%>

<div class="view">
	<div class="body-row">
		<aui:model-context bean="<%= fileVersion %>" model="<%= DLFileVersion.class %>" />

		<%
		dlViewFileVersionDisplayContext.renderPreview(request, response);
		%>

	</div>
</div>