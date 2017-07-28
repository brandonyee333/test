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

<%@ include file="/support/init.jsp" %>

<%
long ticketAttachmentId = ParamUtil.getLong(request, "ticketAttachmentId");

TicketAttachment ticketAttachment = TicketAttachmentServiceUtil.getTicketAttachment(ticketAttachmentId);

Set<String> availableFileRepositoryIds = ticketAttachment.getAvailableFileRepositoryIdsSet();

List<FileRepository> fileRepositories = SupportUtil.getFileRepositories();
%>

<div>
	<h1 class="section-heading">
		<liferay-ui:message key="download-details" />
	</h1>

	<div class="callout-a">
		<div class="aui-helper-clearfix">
			<c:if test="<%= fileRepositories.isEmpty() %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="file-servers-not-available-please-contact-your-support-manager" />
				</div>
			</c:if>

			<%
			for (FileRepository fileRepository : fileRepositories) {
				boolean available = availableFileRepositoryIds.contains(fileRepository.getFileRepositoryId());
			%>

				<div class="attachment-download-server aui-w20">
					<div>
						<%= HtmlUtil.escape(fileRepository.getFileRepositoryId()) %>
					</div>

					<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketAttachment" var="ticketAttachmentURL">
						<portlet:param name="ticketAttachmentId" value="<%= String.valueOf(ticketAttachmentId) %>" />
						<portlet:param name="fileRepositoryId" value="<%= fileRepository.getFileRepositoryId() %>" />
					</liferay-portlet:resourceURL>

					<input class="<%= available ? "" : "aui-helper-hidden" %> aui-button-input" id="<portlet:namespace/><%= fileRepository.getFileRepositoryId() %>download" onclick="location.href = '<%= ticketAttachmentURL.toString() %>'" type="button" value="<liferay-ui:message key="download" />" />

					<c:if test="<%= !available %>">
						<div class="attachment-syncing aui-button-input" id="<portlet:namespace/><%= fileRepository.getFileRepositoryId() %>waiting">
							<liferay-ui:message key="syncing" />
						</div>
					</c:if>
				</div>

			<%
			}
			%>

		</div>
	</div>
</div>

<aui:script>
	<c:if test="<%= availableFileRepositoryIds.size() < fileRepositories.size() %>">
		AUI().ready(
			function() {

				<%
				for (FileRepository fileRepository : fileRepositories) {
					if (availableFileRepositoryIds.contains(fileRepository.getFileRepositoryId())) {
						continue;
					}
				%>

					<portlet:namespace />checkTicketAttachmentServerAvailability('<%= ticketAttachmentId %>', '<%= fileRepository.getFileRepositoryId() %>');

				<%
				}
				%>

			}
		);
	</c:if>

	Liferay.provide(
		window,
		'<portlet:namespace />checkTicketAttachmentServerAvailability',
		function(ticketAttachmentId, fileRepositoryId) {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="ticketAttachmentServer" />',
				{
					data: {
						<portlet:namespace />ticketAttachmentId: ticketAttachmentId,
						<portlet:namespace />fileRepositoryId: fileRepositoryId
					},
					dataType: 'json',
					method: 'post',
					on: {
						success: function(event, id, obj) {
							var response = this.get('responseData');

							if (response.available) {
								A.one('#<portlet:namespace />' + fileRepositoryId + 'download').show();
								A.one('#<portlet:namespace />' + fileRepositoryId + 'waiting').hide();
							}
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>