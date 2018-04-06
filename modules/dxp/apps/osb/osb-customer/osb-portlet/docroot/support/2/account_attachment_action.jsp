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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

AccountAttachment accountAttachment = (AccountAttachment)row.getObject();
%>

<liferay-ui:icon-menu>
	<c:if test="<%= OSBAccountEntryPermission.contains(permissionChecker, accountAttachment.getAccountEntryId(), OSBActionKeys.UPDATE_ACCOUNT_INFO) %>">
		<portlet:actionURL name="deleteAccountAttachment" var="deleteURL">
			<portlet:param name="mvcPath" value="/support/2/edit_account_attachments.jsp" />
			<portlet:param name="accountEntryId" value="<%= String.valueOf(accountAttachment.getAccountEntryId()) %>" />
			<portlet:param name="accountAttachmentId" value="<%= String.valueOf(accountAttachment.getAccountAttachmentId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			label="<%= true %>"
			url="<%= deleteURL %>"
		/>
	</c:if>

	<%
	LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

	accountAttachmentURL.setCopyCurrentRenderParameters(false);
	accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
	accountAttachmentURL.setResourceID("accountAttachment");
	%>

	<liferay-ui:icon
		message="download"
		url="<%= accountAttachmentURL.toString() %>"
	/>

	<c:if test="<%= SupportUtil.hasAttachmentPreview(accountAttachment.getFileName()) %>">

		<%
		accountAttachmentURL.setParameter("preview", Boolean.TRUE.toString());
		%>

		<liferay-ui:icon
			message="preview"
			target="_blank"
			url="<%= accountAttachmentURL.toString() %>"
		/>
	</c:if>
</liferay-ui:icon-menu>