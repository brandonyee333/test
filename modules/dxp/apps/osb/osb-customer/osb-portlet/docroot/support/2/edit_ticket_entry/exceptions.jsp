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

<%@ include file="/support/2/init.jsp" %>

<div class="ticket-exceptions">
	<liferay-ui:error exception="<%= DuplicateTicketAttachmentException.class %>" message="please-enter-a-unique-document-name" />
	<liferay-ui:error exception="<%= FileNameException.class %>" message="please-enter-a-file-with-a-valid-file-name" />
	<liferay-ui:error exception="<%= MaximumDraftTicketCommentException.class %>" message="the-number-of-draft-comments-has-exceeded-the-maximum-number-of-allowed-draft-comments" />
	<liferay-ui:error exception="<%= NoSuchOfferingEntryException.class %>" message="please-select-a-valid-support" />

	<liferay-ui:error exception="<%= OSBPrincipalException.class %>">

		<%
		OSBPrincipalException osbpe = (OSBPrincipalException)errorException;
		%>

		<c:if test="<%= osbpe.getType() == OSBPrincipalException.TYPE_TICKET_CLOSED %>">
			<liferay-ui:message key="this-ticket-has-been-closed.-please-reopen-the-ticket-to-post-any-new-comments" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= RequiredFieldException.class %>">

		<%
		RequiredFieldException rfe = (RequiredFieldException)errorException;
		%>

		<liferay-ui:message arguments="<%= LanguageUtil.get(request, rfe.getLabelId()) %>" key="please-upload-a-x-file" />
	</liferay-ui:error>

	<liferay-ui:error exception="<%= TicketAttachmentVisibilityException.class %>" message="please-enter-a-valid-visibility" />
	<liferay-ui:error exception="<%= TicketCommentBodyException.class %>" message="please-enter-a-valid-comment" />
	<liferay-ui:error exception="<%= TicketCommentPendingTypeException.class %>" message="please-select-a-valid-need-response-from" />
	<liferay-ui:error exception="<%= TicketCommentVisibilityException.class %>" message="please-enter-a-valid-comment-visibility" />

	<liferay-ui:error exception="<%= TicketEntryAttachmentSizeException.class %>">

		<%
		TicketEntryAttachmentSizeException tease = (TicketEntryAttachmentSizeException)errorException;
		%>

		<c:choose>
			<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EMPTY_FILE %>">
				<liferay-ui:message key="the-file-contains-no-data-and-cannot-be-uploaded" />
			</c:when>
			<c:when test="<%= tease.getType() == TicketEntryAttachmentSizeException.EXCEEDS_LIMIT %>">
				<liferay-ui:message key="please-upload-a-file-less-than-100-mb" />
			</c:when>
		</c:choose>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= TicketEntryClosedException.class %>" message="this-ticket-has-been-closed.-please-reopen-the-ticket-to-post-any-new-comments" />
	<liferay-ui:error exception="<%= TicketEntryComponentException.class %>" message="please-select-a-valid-component" />
	<liferay-ui:error exception="<%= TicketEntryResolutionException.class %>" message="please-select-a-valid-resolution" />
	<liferay-ui:error exception="<%= TicketEntrySeverityException.class %>" message="please-select-a-valid-severity" />
	<liferay-ui:error exception="<%= TicketEntryStatusException.class %>" message="please-select-a-valid-status" />
	<liferay-ui:error exception="<%= TicketEntrySubcomponentException.class %>" message="please-provide-a-valid-subcomponent" />
	<liferay-ui:error exception="<%= TicketEntrySubjectException.class %>" message="please-enter-a-valid-subject" />
	<liferay-ui:error exception="<%= TicketEntrySystemStatusException.class %>" message="please-select-a-valid-system-status" />
	<liferay-ui:error exception="<%= TicketEntryWeightException.class %>" message="please-enter-a-valid-weight" />

	<liferay-ui:error exception="<%= TicketInformationException.class %>">

		<%
		TicketInformationException tie = (TicketInformationException)errorException;
		%>

		<liferay-ui:message arguments="<%= tie.getMessage() %>" key="invalid-value-provided-for-x" />
	</liferay-ui:error>
</div>