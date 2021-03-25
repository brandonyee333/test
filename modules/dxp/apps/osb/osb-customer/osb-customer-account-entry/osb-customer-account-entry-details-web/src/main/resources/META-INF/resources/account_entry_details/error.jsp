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