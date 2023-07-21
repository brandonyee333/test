<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:if test="<%= !SessionErrors.isEmpty(renderRequest) %>">
	<div class="alert alert-danger">
		<liferay-ui:icon
			icon="exclamation-full"
			markupView="lexicon"
		/>

		<c:choose>
			<c:when test="<%= SessionErrors.contains(renderRequest, FileNotFoundException.class.getName()) %>">
				<liferay-ui:message key="the-selected-attachment-could-not-be-found" />
			</c:when>
			<c:when test="<%= SessionErrors.contains(renderRequest, FileRepositoryConnectionException.class.getName()) %>">

				<%
				FileRepositoryConnectionException fileRepositoryConnectionException = (FileRepositoryConnectionException)SessionErrors.get(renderRequest, FileRepositoryConnectionException.class.getName());

				FileRepository fileRepository = fileRepositoryConnectionException.getFileRepository();
				%>

				<liferay-ui:message key='<%= "unable-to-connect-to-" + fileRepository.getName() + "-file-server" %>' />
			</c:when>
			<c:when test="<%= SessionErrors.contains(renderRequest, NoSuchTicketAttachmentException.class.getName()) %>">
				<liferay-ui:message key="the-selected-attachment-no-longer-exists" />
			</c:when>
			<c:when test="<%= SessionErrors.contains(renderRequest, PrincipalException.getNestedClasses()) %>">
				<liferay-ui:message key="you-do-not-have-permission-to-access-the-requested-resource" />
			</c:when>
			<c:when test="<%= SessionErrors.contains(renderRequest, ZendeskTicketClosedException.class.getName()) %>">
				<liferay-ui:message key="this-ticket-is-closed" />
			</c:when>
			<c:otherwise>
				<liferay-ui:message key="your-request-failed-to-complete" />
			</c:otherwise>
		</c:choose>
	</div>
</c:if>