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

			<aui:input label="check-this-box-if-the-file-you-uploaded-does-not-contain-any-personal-data-and-therefore-can-be-accessed-from-any-liferay-support-location-globally" name="noPersonalData" type="checkbox" value="" />

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