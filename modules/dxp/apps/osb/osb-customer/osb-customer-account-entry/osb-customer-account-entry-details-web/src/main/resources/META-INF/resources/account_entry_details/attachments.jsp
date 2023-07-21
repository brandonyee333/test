<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<liferay-util:include page="/account_entry_details/customer_portal_banner.jsp" servletContext="<%= application %>" />

<liferay-ui:search-container
	headerNames="ticket,file-name,file-size,attached,does-not-contain-personal-data"
	searchContainer="<%= accountEntryViewDisplayContext.getTicketAttachmentsSearchContainer() %>"
>
	<liferay-ui:search-container-row
		className="com.liferay.osb.customer.ticket.model.TicketAttachment"
		modelVar="ticketAttachment"
	>

		<%
		String ticketAttachmentURL = StringPool.BLANK;

		if (TicketAttachmentPermissionChecker.contains(permissionChecker, ticketAttachment, ActionKeys.VIEW)) {
			ResourceURL resourceURL = liferayPortletResponse.createResourceURL();

			resourceURL.setParameter("ticketAttachmentId", String.valueOf(ticketAttachment.getTicketAttachmentId()));
			resourceURL.setResourceID("/ticket_attachment");

			ticketAttachmentURL = resourceURL.toString();
		}
		%>

		<liferay-ui:search-container-column-text
			href="<%= accountEntryDetailsConfiguration.zendeskTicketURL() + ticketAttachment.getZendeskTicketId() %>"
			name="ticket"
		>
			#<%= ticketAttachment.getZendeskTicketId() %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			href="<%= ticketAttachmentURL %>"
			name="file-name"
			value="<%= ticketAttachment.getFileName() %>"
		/>

		<liferay-ui:search-container-column-text
			name="file-size"
		>
			<%= TextFormatter.formatStorageSize((double)ticketAttachment.getFileSize(), locale) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="attached"
		>
			<%= fullDateFormatDateTime.format(ticketAttachment.getCreateDate()) %><br />

			<liferay-ui:message key="by" /> <%= PortalUtil.getUserName(ticketAttachment.getUserId(), ticketAttachment.getUserName()) %>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-text
			name="does-not-contain-personal-data"
		>
			<c:if test="<%= !ticketAttachment.isRegionRestricted() %>">
				<liferay-ui:icon
					image="checked"
				/>
			</c:if>
		</liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/account_entry_details/ticket_attachment_action.jsp"
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator
		markupView="lexicon"
		paginate="<%= false %>"
		resultRowSplitter="<%= new TicketAttachmentResultRowSplitter() %>"
	/>
</liferay-ui:search-container>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />closeDialog',
		function(dialog) {
			dialog.hide();

			AUI().use(
				'aui-loading-mask-deprecated',
				function(A) {
					new A.LoadingMask(
						{
							target: A.getBody()
						}
					).show();
				}
			);

			window.location.replace('<%= HtmlUtil.escapeJS(currentURL) %>');
		},
		['aui-base','aui-dialog','aui-dialog-iframe']
	);
</aui:script>