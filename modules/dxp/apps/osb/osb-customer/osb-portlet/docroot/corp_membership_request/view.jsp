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

<%@ include file="/init.jsp" %>

<%
String key = ParamUtil.getString(request, "key");

CorpMembershipRequest corpMembershipRequest = CorpMembershipRequestLocalServiceUtil.fetchCorpMembershipRequest(key);
%>

<div class="marketplace-registration-wrapper">
	<c:choose>
		<c:when test="<%= corpMembershipRequest == null %>">
			<h3>
				<liferay-ui:message key="an-error-occurred-while-looking-up-your-membership-request" />
			</h3>

			<p>
				<liferay-ui:message key="we-were-unable-to-find-your-membership-request" />
			</p>
		</c:when>
		<c:otherwise>

			<%
			CorpEntry corpEntry = CorpEntryLocalServiceUtil.getCorpEntry(corpMembershipRequest.getCorpEntryId());
			%>

			<c:choose>
				<c:when test="<%= UserLocalServiceUtil.hasOrganizationUser(corpEntry.getOrganizationId(), corpMembershipRequest.getUserId()) %>">
					<h3>
						<liferay-ui:message key="success" />
					</h3>

					<p>
						<liferay-ui:message arguments="<%= HtmlUtil.escape(corpEntry.getName()) %>" key="you-have-been-added-to-x" />
					</p>
				</c:when>
				<c:otherwise>
					<h3>
						<liferay-ui:message key="your-membership-request-is-no-longer-valid" />
					</h3>

					<p>
						<liferay-ui:message arguments="<%= HtmlUtil.escape(corpEntry.getName()) %>" key="we-are-resending-your-request-to-an-admin-of-x" />
					</p>

					<%
					if (corpMembershipRequest.getStatus() == WorkflowConstants.STATUS_PENDING_VALIDATION) {
						CorpMembershipRequestLocalServiceUtil.updateStatus(corpMembershipRequest.getCorpMembershipRequestId(), WorkflowConstants.STATUS_PENDING);
					}
					else {
						CorpMembershipRequestLocalServiceUtil.addCorpMembershipRequest(corpMembershipRequest.getUserId(), corpMembershipRequest.getCorpEntryId());
					}
					%>

				</c:otherwise>
			</c:choose>
		</c:otherwise>
	</c:choose>
</div>