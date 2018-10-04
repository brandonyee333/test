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
AccountEntry accountEntry = (AccountEntry)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

long zendeskTicketId = ParamUtil.getLong(request, "zendeskTicketId");

long[] supportRegionIds = accountEntry.getSupportRegionIds();

FileRepository fileRepository = fileRepositoryManager.getFileRepository(supportRegionIds[0]);

String fileRepositoryId = fileRepository.getFileRepositoryId();
%>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/file_repository_token" var="generateTokenURL">
	<portlet:param name="fileRepositoryId" value="<%= fileRepositoryId %>" />
	<portlet:param name="zendeskTicketId" value="<%= String.valueOf(zendeskTicketId) %>" />
</liferay-portlet:resourceURL>

<portlet:actionURL name="addTicketAttachment" var="addTicketAttachmentURL">
	<portlet:param name="fileRepositoryId" value="<%= fileRepositoryId %>" />
	<portlet:param name="zendeskTicketId" value="<%= String.valueOf(zendeskTicketId) %>" />
</portlet:actionURL>

<div class="add-ticket-attachment" id="<portlet:namespace />addTicketAttachment"></div>

<liferay-ui:error exception="<%= NoSuchAccountEntryException.class %>" message="the-project-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchZendeskTicketException.class %>" message="the-ticket-could-not-be-found" />

<aui:script>
	HelpCenter.render(
		HelpCenter.AddTicketAttachment,
		{
			addTicketAttachmentURL: '<%= addTicketAttachmentURL %>',
			generateTokenURL: '<%= generateTokenURL %>',
			uploadURL: '<%= fileRepositoryWebService.getUploadURL(fileRepositoryId) %>',
			zendeskTicketId: '<%= String.valueOf(zendeskTicketId) %>'
		},
		document.getElementById('${renderResponse.namespace}addTicketAttachment')
	);
</aui:script>