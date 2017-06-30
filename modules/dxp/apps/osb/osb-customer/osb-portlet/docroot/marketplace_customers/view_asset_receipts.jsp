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
String backURL = ParamUtil.getString(request, "backURL");

long ownerClassNameId = ParamUtil.getLong(request, "ownerClassNameId");
long ownerClassPK = ParamUtil.getLong(request, "ownerClassPK");

String ownerName = StringPool.BLANK;

CorpProject corpProject = null;

if (ownerClassNameId == PortalUtil.getClassNameId(CorpProject.class)) {
	corpProject = CorpProjectLocalServiceUtil.getCorpProject(ownerClassPK);

	ownerName = corpProject.getName();
}
else if (ownerClassNameId == PortalUtil.getClassNameId(User.class)) {
	User user3 = UserLocalServiceUtil.getUser(ownerClassPK);

	ownerName = user3.getFullName();
}
%>

<div class="marketplace-customers view">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="customers"
	/>

	<liferay-ui:header
		title="<%= ownerName %>"
	/>

	<div class="user-info">
		<h2>
			<liferay-ui:message key="users" />
		</h2>

		<%
		List<User> users = new ArrayList<User>();

		if (corpProject != null) {
			users.addAll(UserLocalServiceUtil.getOrganizationUsers(corpProject.getOrganizationId()));
		}
		else {
			users.add(UserLocalServiceUtil.getUser(ownerClassPK));
		}
		%>

		<liferay-ui:search-container
			emptyResultsMessage="no-users-were-found"
			headerNames="name,email,phone"
		>
			<liferay-ui:search-container-results
				results="<%= users %>"
				total="<%= users.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.portal.model.User"
				escapedModel="<%= true %>"
				keyProperty="userId"
				modelVar="user2"
			>
				<liferay-ui:search-container-column-text>
					<img class="avatar" src="<%= user2.getPortraitURL(themeDisplay) %>" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= user2.getFullName() %>"
				/>

				<liferay-ui:search-container-column-text
					name="email"
				>
					<a href="mailto:<%= user2.getEmailAddress() %>"><%= user2.getEmailAddress() %></a>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="phone"
				>

					<%
					Phone phone = null;

					List<Phone> phones = user2.getPhones();

					for (Phone phone2 : phones) {
						if (phone2.isPrimary()) {
							phone = phone2;

							break;
						}
					}

					if (Validator.isNull(phone) && !phones.isEmpty()) {
						phone = phones.get(0);
					}
					%>

					<c:if test="<%= phone != null %>">
						<%= phone.getNumber() %> <%= phone.getExtension() %>
					</c:if>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</div>

	<div class="purchase-info">
		<h2>
			<liferay-ui:message key="apps" />
		</h2>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-results"
			headerNames="name,type,license-expiration,support-expiration"
		>
			<liferay-ui:search-container-results>

				<%
				DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

				List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(developerEntry.getDeveloperEntryId(), WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				List<AssetReceiptLicense> assetReceiptLicenses = new ArrayList<AssetReceiptLicense>();

				for (AppEntry appEntry : appEntries) {
					assetReceiptLicenses.addAll(AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(PortalUtil.getClassName(ownerClassNameId), ownerClassPK, appEntry.getUuid()));
				}

				pageContext.setAttribute("results", assetReceiptLicenses);
				pageContext.setAttribute("total", assetReceiptLicenses.size());
				%>

			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.AssetReceiptLicense"
				escapedModel="<%= true %>"
				keyProperty="assetReceiptLicenseId"
				modelVar="assetReceiptLicense"
			>

				<%
				AppVersion appVersion = AppVersionLocalServiceUtil.getAppVersion(assetReceiptLicense.getProductClassPK());

				String iconURL = StringPool.BLANK;

				if (appVersion.getIconImageId() > 0) {
					LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					liferayPortletURL.setCopyCurrentRenderParameters(false);
					liferayPortletURL.setParameter("assetAttachmentId", String.valueOf(appVersion.getIconImageId()), false);
					liferayPortletURL.setResourceID("serveIcon");

					iconURL = liferayPortletURL.toString();
				}
				else {
					iconURL = themeDisplay.getPathThemeImages() + "/custom/icon-marketplace-placeholder-70x70.png";
				}
				%>

				<liferay-ui:search-container-column-text>
					<img class="icon" src="<%= iconURL %>" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= HtmlUtil.escape(appVersion.getTitle()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="type"
					value="<%= LanguageUtil.get(pageContext, assetReceiptLicense.getUsageTypeLabel()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="license-expiration"
					value="<%= LanguageUtil.get(pageContext, assetReceiptLicense.getUsageTypeLabel()) %>"
				>
					<c:choose>
						<c:when test="<%= assetReceiptLicense.getLicenseLifetime() != AssetLicenseConstants.LIFETIME_PERPETUAL %>">
							<%= mediumDateFormatDate.format(assetReceiptLicense.getEndDate()) %>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="support-expiration"
					value="<%= LanguageUtil.get(pageContext, assetReceiptLicense.getUsageTypeLabel()) %>"
				>

					<%
					List<AssetReceiptSupport> assetReceiptSupports = AssetReceiptSupportLocalServiceUtil.getAssetReceiptSupports(assetReceiptLicense.getAssetReceiptId(), AssetReceiptLicense.class.getName(), assetReceiptLicense.getAssetReceiptLicenseId(), 0, 1, new AssetReceiptSupportEndDateComparator());
					%>

					<c:choose>
						<c:when test="<%= !assetReceiptSupports.isEmpty() %>">

							<%
							AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);
							%>

							<%= mediumDateFormatDate.format(assetReceiptLicense.getEndDate()) %>
						</c:when>
						<c:otherwise>
							-
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</div>
</div>