<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<%
TicketAttachment ticketAttachment = (TicketAttachment)renderRequest.getAttribute(AccountEntryDetailsWebKeys.TICKET_ATTACHMENT);
%>

<c:choose>
	<c:when test='<%= SessionMessages.contains(renderRequest, "requestProcessed") %>'>
		<aui:script>
			var dialog = Liferay.Util.getWindow();

			Liferay.Util.getOpener().<portlet:namespace />closeDialog(dialog);
		</aui:script>
	</c:when>
	<c:otherwise>
		<portlet:actionURL name="removeRegionRestriction" var="removeRegionRestrictionURL">
			<portlet:param name="mvcRenderCommandName" value="/remove_region_restriction" />
		</portlet:actionURL>

		<aui:form action="<%= removeRegionRestrictionURL %>" cssClass="container-fluid-1280" method="post">
			<aui:input name="ticketAttachmentId" type="hidden" value="<%= String.valueOf(ticketAttachment.getTicketAttachmentId()) %>" />

			<h1>
				<liferay-ui:message key="edit-access-for" /> <%= HtmlUtil.escape(ticketAttachment.getFileName()) %>
			</h1>

			<aui:input label="i-certify-that-the-uploaded-attachment-does-not-contain-any-personal-data" name="noPersonalData" type="checkbox" value="" />

			<div class="portlet-msg-info">
				<liferay-ui:message key="note-this-action-cannot-be-undone" />
			</div>

			<aui:button-row>
				<aui:button cssClass="btn-lg" type="submit" />

				<aui:button cssClass="btn-lg" type="cancel" />
			</aui:button-row>
		</aui:form>
	</c:otherwise>
</c:choose>