<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/wiki/init.jsp" %>

<%
WikiPage wikiPage = (WikiPage)request.getAttribute(WikiWebKeys.WIKI_PAGE);
WikiNode node = (WikiNode)request.getAttribute(WikiWebKeys.WIKI_NODE);

PortletURL portletURL = renderResponse.createActionURL();

portletURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
portletURL.setParameter("title", wikiPage.getTitle());

portletURL.setParameter(ActionRequest.ACTION_NAME, "/wiki/view");

PortalUtil.addPortletBreadcrumbEntry(request, wikiPage.getTitle(), portletURL.toString());

portletURL.setParameter(ActionRequest.ACTION_NAME, "/wiki/view_page_attachments");

PortalUtil.addPortletBreadcrumbEntry(request, LanguageUtil.get(request, "attachments"), portletURL.toString());
%>

<portlet:actionURL name="/wiki/edit_page_attachment" var="undoTrashURL">
	<portlet:param name="<%= Constants.CMD %>" value="<%= Constants.RESTORE %>" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<liferay-trash:undo
		portletURL="<%= undoTrashURL %>"
	/>

	<c:if test="<%= WikiNodePermissionChecker.contains(permissionChecker, node.getNodeId(), ActionKeys.ADD_ATTACHMENT) %>">
		<portlet:actionURL name="/wiki/edit_page_attachment" var="emptyTrashURL">
			<portlet:param name="nodeId" value="<%= String.valueOf(wikiPage.getNodeId()) %>" />
			<portlet:param name="title" value="<%= wikiPage.getTitle() %>" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
		</portlet:actionURL>

		<liferay-trash:empty
			confirmMessage="are-you-sure-you-want-to-remove-the-attachments-for-this-page"
			emptyMessage="remove-the-attachments-for-this-page"
			infoMessage="attachments-that-have-been-removed-for-more-than-x-will-be-automatically-deleted"
			portletURL="<%= emptyTrashURL.toString() %>"
			totalEntries="<%= wikiPage.getDeletedAttachmentsFileEntriesCount() %>"
		/>
	</c:if>

	<%
	List<FileEntry> attachmentsFileEntries = wikiPage.getDeletedAttachmentsFileEntries();
	int attachmentsFileEntriesCount = wikiPage.getDeletedAttachmentsFileEntriesCount();
	String emptyResultsMessage = "this-page-does-not-have-file-attachments-in-the-recycle-bin";

	PortletURL iteratorURL = renderResponse.createRenderURL();

	iteratorURL.setParameter("mvcRenderCommandName", "/wiki/view_trash_page_attachments");
	iteratorURL.setParameter("redirect", currentURL);
	iteratorURL.setParameter("nodeId", String.valueOf(wikiPage.getNodeId()));
	iteratorURL.setParameter("title", wikiPage.getTitle());
	iteratorURL.setWindowState(LiferayWindowState.POP_UP);

	boolean paginate = false;
	boolean showPageAttachmentAction = true;
	int status = WorkflowConstants.STATUS_IN_TRASH;
	%>

	<%@ include file="/wiki/attachments_list.jspf" %>
</div>