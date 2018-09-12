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

<%@ include file="/init.jsp" %>

<%
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

long zendeskTicketId = ParamUtil.getLong(request, "zendeskTicketId");
%>

<portlet:actionURL name="addTicketAttachment" var="addTicketAttachmentURL" />

<div class="add-ticket-attachment" id="addTicketAttachment"></div>

<liferay-ui:error exception="<%= NoSuchAccountEntryException.class %>" message="the-project-could-not-be-found" />
<liferay-ui:error exception="<%= NoSuchZendeskTicketException.class %>" message="the-ticket-could-not-be-found" />

<aui:script>
	HelpCenter.render(
		HelpCenter.AddTicketAttachment,
		{
			accountEntryId: '<%= accountEntryId %>',
			addTicketAttachmentURL: '<%= addTicketAttachmentURL %>',
			namespace: '${renderResponse.namespace}',
			zendeskTicketId: '<%= zendeskTicketId %>',
			zendeskURL: "temporaryUrl"
		},
		document.getElementById('addTicketAttachment')
	);
</aui:script>