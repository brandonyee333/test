<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<%
Account koroneikiAccount = (Account)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT);

long zendeskTicketId = ParamUtil.getLong(request, "zendeskTicketId");

FileRepository fileRepository = fileRepositoryManager.getDataRegionFileRepository(koroneikiAccount.getDataRegionAsString());
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/file_repository_token" var="generateTokenURL">
	<portlet:param name="fileRepositoryId" value="<%= fileRepository.getFileRepositoryId() %>" />
	<portlet:param name="zendeskTicketId" value="<%= String.valueOf(zendeskTicketId) %>" />
</liferay-portlet:resourceURL>

<portlet:actionURL name="addTicketAttachment" var="addTicketAttachmentURL">
	<portlet:param name="redirect" value="<%= accountEntryDetailsConfiguration.zendeskTicketURL() + zendeskTicketId %>" />
	<portlet:param name="fileRepositoryId" value="<%= fileRepository.getFileRepositoryId() %>" />
	<portlet:param name="zendeskTicketId" value="<%= String.valueOf(zendeskTicketId) %>" />
</portlet:actionURL>

<div class="add-ticket-attachment" id="<portlet:namespace />addTicketAttachment"></div>

<liferay-ui:error exception="<%= FileRepositoryConnectionException.class %>">

	<%
	FileRepositoryConnectionException fileRepositoryConnectionException = (FileRepositoryConnectionException)errorException;

	FileRepository curFileRepository = fileRepositoryConnectionException.getFileRepository();
	%>

	<liferay-ui:message key='<%= "unable-to-connect-to-" + curFileRepository.getName() + "-file-server" %>' />
</liferay-ui:error>

<liferay-ui:error exception="<%= NoSuchAccountEntryException.class %>" message="the-project-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchZendeskTicketException.class %>" message="the-ticket-could-not-be-found" />

<aui:script>
	AccountDetails.render(
		AccountDetails.AddTicketAttachment,
		{
			addTicketAttachmentURL: '<%= addTicketAttachmentURL %>',
			dataAccessArticleURL: '<%= accountEntryDetailsConfiguration.dataAccessArticleURL() %>',
			fileRepositoryMessage: '<%= LanguageUtil.get(request, "your-file-will-be-uploaded-to-a-file-server-in-" + fileRepository.getName()) %>',
			generateTokenURL: '<%= generateTokenURL %>',
			uploadURL: '<%= fileRepositoryWebService.getUploadURL(fileRepository.getFileRepositoryId()) %>',
			zendeskTicketId: '<%= String.valueOf(zendeskTicketId) %>',
			zendeskTicketURL: '<%= accountEntryDetailsConfiguration.zendeskTicketURL() + zendeskTicketId %>'
		},
		document.getElementById('${renderResponse.namespace}addTicketAttachment')
	);
</aui:script>