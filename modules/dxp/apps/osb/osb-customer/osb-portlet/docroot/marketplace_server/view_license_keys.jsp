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

<%@ include file="/marketplace_server/init.jsp" %>

<%
long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion appVersion = appEntry.getApprovedAppVersion();

long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptServiceUtil.getAssetReceipt(assetReceiptId);
%>

<div class="marketplace-server view-license-keys">
	<div class="app-heading">
		<div class="icon">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
				<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appVersion.getIconImageId()) %>" />
			</liferay-portlet:resourceURL>

			<img src="<%= iconURL %>" />
		</div>

		<div class="title">
			<h1>
				<%= HtmlUtil.escape(appVersion.getTitle()) %>
			</h1>

			<div class="owner">
				<%= HtmlUtil.escape(assetReceipt.getOwnerName()) %>

				<c:if test="<%= assetReceipt.isOwnerUser() && Validator.isNotNull(assetReceipt.getLegalEntityName()) %>">
					- <%= HtmlUtil.escape(assetReceipt.getLegalEntityName()) %>
				</c:if>
			</div>
		</div>
	</div>

	<h2>
		<liferay-ui:message key="licenses" />
	</h2>

	<div class="header-message">
		<liferay-ui:message key="please-select-a-license-to-continue-with-the-installation" />
	</div>

	<liferay-portlet:renderURL varImpl="viewPurchasedURL">
		<portlet:param name="remoteMVCPath" value="/marketplace_server/view_purchased.jsp" />
		<portlet:param name="corpProjectId" value="<%= assetReceipt.isOwnerCorpProject() ? String.valueOf(assetReceipt.getOwnerClassPK()) : StringPool.BLANK %>" />
		<portlet:param name="updateAppEntryId" value="<%= String.valueOf(appEntryId) %>" />
		<portlet:param name="updateProductEntryName" value="<%= appEntry.getTitle() %>" />
	</liferay-portlet:renderURL>

	<%
	String taglibOnSubmit = "event.preventDefault();" + renderResponse.getNamespace() + "registerLicense();";
	%>

	<aui:form action="<%= viewPurchasedURL.toString() %>" method="get" name="fm" onSubmit="<%= taglibOnSubmit %>">
		<liferay-portlet:renderURLParams varImpl="viewPurchasedURL" />

		<div class="aui-helper-hidden license-unavailable portlet-msg-error">
			<liferay-ui:message key="no-licenses-available" />
		</div>

		<div class="aui-helper-hidden license-unselected portlet-msg-error">
			<liferay-ui:message key="please-select-an-available-license" />
		</div>

		<aui:fieldset>

			<%
			List<AssetReceiptLicense> assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceipt.getAssetReceiptId());
			%>

			<liferay-ui:search-container
				delta="<%= assetReceiptLicenses.size() %>"
				emptyResultsMessage="no-licenses-were-found"
			>
				<liferay-ui:search-container-results
					results="<%= assetReceiptLicenses %>"
					total="<%= assetReceiptLicenses.size() %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.AssetReceiptLicense"
					escapedModel="<%= true %>"
					keyProperty="assetReceiptLicenseId"
					modelVar="assetReceiptLicense"
				>

					<%
					AssetLicense assetLicense = assetReceiptLicense.getAssetLicense();

					boolean licenseKeysAvailable = assetReceiptLicense.hasAvailableLicenseKeys();
					%>

					<liferay-ui:search-container-column-text>
						<aui:input cssClass="license" disabled="<%= !licenseKeysAvailable %>" label="<%= StringPool.BLANK %>" name="updateAppEntryOrderUuid" type="radio" value="<%= assetReceiptLicense.getUuid() %>" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="purchased"
						value="<%= shortDateFormatDate.format(assetReceiptLicense.getCreateDate()) %>"
					/>

					<liferay-ui:search-container-column-text
						name="usage-type"
					>
						<liferay-ui:message key="<%= assetReceiptLicense.getUsageTypeLabel() %>" />
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="order-id"
						value="<%= assetReceiptLicense.getUuid() %>"
					/>

					<liferay-ui:search-container-column-text
						name="licenses"
					>

						<%
						int count = LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicense.getAssetReceiptLicenseId(), false, true);
						%>

						<%= count %>/<%= assetLicense.getLicenseTypeAllotment() %>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-text
						name="type"
						translate="<%= true %>"
						value="<%= assetLicense.getLicenseTypeLabel() %>"
					/>

					<liferay-ui:search-container-column-text
						name="license-expiration"
					>
						<c:choose>
							<c:when test="<%= assetLicense.getLifetime() < AssetLicenseConstants.LIFETIME_PERPETUAL %>">
								<%= shortDateFormatDate.format(assetReceiptLicense.getEndDate()) %>
							</c:when>
							<c:otherwise>
								<liferay-ui:message key="n/a" />
							</c:otherwise>
						</c:choose>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" />
			</liferay-ui:search-container>
		</aui:fieldset>

		<liferay-portlet:renderURL var="backURL">
			<portlet:param name="remoteMVCPath" value="/marketplace_server/view_purchased.jsp" />
		</liferay-portlet:renderURL>

		<aui:button-row>
			<a class="btn" href="<%= backURL %>"><liferay-ui:message key="back" /></a>

			<aui:button type="submit" value="register" />
		</aui:button-row>
	</aui:form>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />registerLicense',
		function() {
			var A = AUI();

			var form = A.one('#<portlet:namespace />fm');

			var checked = false;
			var disabled = true;

			form.all('.license input').each(
				function(license) {
					if (license.attr('checked')) {
						checked = license.attr('checked');
					}

					if (!license.attr('disabled')) {
						disabled = license.attr('disabled');
					}
				}
			);

			if (disabled) {
				form.one('.license-unavailable').removeClass('aui-helper-hidden');
			}
			else if (!checked) {
				form.one('.license-unselected').removeClass('aui-helper-hidden');
			}
			else {
				form.submit();
			}
		}
	);
</aui:script>