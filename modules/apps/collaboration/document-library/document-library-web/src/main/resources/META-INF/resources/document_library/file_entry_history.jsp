<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
FileEntry fileEntry = (FileEntry)request.getAttribute("info_panel.jsp-fileEntry");

int status = WorkflowConstants.STATUS_APPROVED;

if ((user.getUserId() == fileEntry.getUserId()) || permissionChecker.isContentReviewer(user.getCompanyId(), scopeGroupId)) {
	status = WorkflowConstants.STATUS_ANY;
}

List<FileVersion> fileVersions = fileEntry.getFileVersions(status);

for (FileVersion fileVersion : fileVersions) {
	request.setAttribute("info_panel.jsp-fileVersion", fileVersion);
%>

	<aui:row>
		<aui:col width="<%= 100 %>">
			<ul class="sidebar-header-actions">
				<li>
					<liferay-util:include page="/document_library/file_entry_history_action.jsp" servletContext="<%= application %>" />
				</li>
			</ul>

			<dl>
				<dt class="h5">
					<liferay-ui:message arguments="<%= fileVersion.getVersion() %>" key="version-x" />
				</dt>
				<dd>
					<c:choose>
						<c:when test="<%= Validator.isNull(fileVersion.getChangeLog()) %>">
							<small class="text-muted">
								<liferay-ui:message key="no-change-log" />
							</small>
						</c:when>
						<c:otherwise>
							<%= fileVersion.getChangeLog() %>
						</c:otherwise>
					</c:choose>
				</dd>
				<dd>
					<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(fileVersion.getUserName()), dateFormatDateTime.format(fileVersion.getCreateDate())} %>" key="by-x-on-x" translateArguments="<%= false %>" />
				</dd>
			</dl>
		</aui:col>
	</aui:row>

<%
}
%>