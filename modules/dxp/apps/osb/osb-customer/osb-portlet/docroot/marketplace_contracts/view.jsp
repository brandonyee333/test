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

<%@ include file="/marketplace_contracts/init.jsp" %>

<div class="marketplace-contracts view">
	<liferay-ui:header
		title="end-user-license-agreement"
	/>

	<c:choose>
		<c:when test="<%= !defaultEula && (appEntryId <= 0) %>">
			<div class="portlet-msg-info">
				<liferay-ui:message key="please-configure-this-portlet" />
			</div>
		</c:when>
		<c:otherwise>

			<%
			AppEntry appEntry = AppEntryLocalServiceUtil.fetchAppEntry(appEntryId);
			%>

			<c:if test="<%= appEntry != null %>">
				<div class="item">
					<div class="icon">

						<%
						LiferayPortletURL iconURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE), PortletRequest.RESOURCE_PHASE);

						iconURL.setCopyCurrentRenderParameters(false);
						iconURL.setParameter("assetAttachmentId", String.valueOf(appEntry.getIconImageId()));
						iconURL.setResourceID("serveIcon");
						%>

						<img src="<%= iconURL.toString() %>" />
					</div>

					<div class="title">
						<%= HtmlUtil.escape(appEntry.getTitle()) %>
					</div>
				</div>
			</c:if>

			<div class="license-agreement">

				<%
				ContractEntry contractEntry = null;

				if (defaultEula) {
					contractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(ContractEntryConstants.DEFAULT_CLASS_NAME_ID, ContractEntryConstants.DEFAULT_CLASS_PK, ContractEntryConstants.TYPE_APP_EULA);
				}
				else {
					AppVersion appVersion = appEntry.getApprovedAppVersion();

					contractEntry = ContractEntryLocalServiceUtil.getLatestContractEntry(AppVersion.class.getName(), appVersion.getAppVersionId(), ContractEntryConstants.TYPE_APP_EULA);
				}

				String languageId = LanguageUtil.getLanguageId(request);

				String content = contractEntry.getContent(languageId);
				%>

				<%= content.replace(StringPool.NEW_LINE, "<br />") %>
			</div>
		</c:otherwise>
	</c:choose>
</div>