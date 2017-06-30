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

<%@ include file="/corp_admin/init.jsp" %>

<%
long corpEntryId = ParamUtil.getLong(request, "corpEntryId");

CorpEntry corpEntry = CorpEntryLocalServiceUtil.fetchCorpEntry(corpEntryId);

DeveloperEntry developerEntry = null;

if (corpEntry != null) {
	developerEntry = DeveloperEntryLocalServiceUtil.fetchDeveloperEntry(corpEntry.getDossieraAccountKey());
}
%>

<c:if test="<%= developerEntry != null %>">
	<aui:input checked="<%= true %>" disabled="<%= true %>" name="marketplace-developer" type="checkbox" />

	<div class="marketplace-developer">
		<strong><liferay-ui:message key="developer-status" />:</strong>

		<liferay-ui:message key="<%= WorkflowConstants.toLabel(developerEntry.getSubscriptionStatus()) %>" />

		<br />

		<c:if test="<%= developerEntry.getSubscriptionExpirationDate() != null %>">
			<strong><liferay-ui:message key="subscription-expiration-date" />:</strong>

			<%= longDateFormatDateTime.format(developerEntry.getSubscriptionExpirationDate()) %>

			<br />
		</c:if>

		<%
		List<AssetAttachment> assetAttachments = MarketplaceRegistrationUtil.getDocumentAssetAttachments(corpEntry.getCorpEntryId());
		%>

		<c:if test="<%= !assetAttachments.isEmpty() %>">
			<strong><liferay-ui:message key="legal-tax-document" /></strong>

			<br />

			<%
			for (AssetAttachment assetAttachment : assetAttachments) {
			%>

				<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveAssetAttachmentDocument" var="assetAttachmentURL">
					<portlet:param name="assetAttachmentId" value="<%= String.valueOf(assetAttachment.getAssetAttachmentId()) %>" />
				</liferay-portlet:resourceURL>

				<liferay-ui:icon
					image="clip"
					label="<%= true %>"
					message="<%= assetAttachment.getFileName() %>"
					method="get"
					url="<%= assetAttachmentURL %>"
				/>

			<%
			}
			%>

		</c:if>
	</div>
</c:if>