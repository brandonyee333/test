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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

AssetEntry assetEntry = assetReceipt.getAssetEntry();

assetEntry = assetEntry.toEscapedModel();
%>

<div class="view-asset-receipt">
	<liferay-ui:header
		backURL="<%= backURL %>"
		title="order"
	/>

	<div>
		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="app" />
				</label>

				<span class="field-element">
					<%= assetEntry.getTitle() %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="owner-type" />
				</label>

				<span class="field-element">
					<c:choose>
						<c:when test="<%= assetReceipt.isOwnerCorpProject() %>">
							<liferay-ui:message key="project" />
						</c:when>
						<c:when test="<%= assetReceipt.isOwnerUser() %>">
							<liferay-ui:message key="user" />
						</c:when>
					</c:choose>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="owner" />
				</label>

				<span class="field-element">
					<%= HtmlUtil.escape(assetReceipt.getOwnerName()) %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="legal-entity-name" />
				</label>

				<span class="field-element">
					<%= HtmlUtil.escape(assetReceipt.getLegalEntityName()) %>
				</span>
			</span>
		</span>

		<span class="field">
			<span class="field-content">
				<label class="field-label">
					<liferay-ui:message key="purchased-by" />
				</label>

				<span class="field-element">
					<%= HtmlUtil.escape(PortalUtil.getUserName(assetReceipt.getUserId(), assetReceipt.getUserName())) %>
				</span>
			</span>
		</span>

		<div>
			<portlet:actionURL name="deleteAssetReceipt" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
			</portlet:actionURL>

			<input onClick="javascript:if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-delete-this-order") %>')) { location.href = '<%= deleteURL %>'; }" type="button" value="<liferay-ui:message key="delete" />" />

			<c:if test="<%= permissionChecker.isOmniadmin() %>">
				<portlet:renderURL var="editAssetReceiptOwnerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt_owner.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceiptId) %>" />
				</portlet:renderURL>

				<input onClick="location.href = '<%= HtmlUtil.escape(editAssetReceiptOwnerURL) %>';" type="button" value="<liferay-ui:message key="change-ownership" />" />
			</c:if>

			<%
			boolean editable = true;

			if (assetReceipt == null) {
				editable = false;
			}
			else {
				if (assetReceipt.hasActiveAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_STANDARD) || assetReceipt.hasActiveAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_DEVELOPER)) {
					editable = true;
				}

				if (assetReceipt.hasRenewedAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_STANDARD) || assetReceipt.hasRenewedAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_DEVELOPER)) {
					editable = false;
				}
			}
			%>

			<c:if test="<%= editable %>">
				<portlet:renderURL var="editURL">
					<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt_licenses.jsp" />
					<portlet:param name="redirect" value="<%= currentURL %>" />
					<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceiptId) %>" />
				</portlet:renderURL>

				<aui:button onClick="<%= editURL %>" value="edit" />
			</c:if>

			<aui:button onClick="<%= backURL %>" value="back" />
		</div>
	</div>

	<%
	List<AssetLicense> assetLicenses = assetReceipt.getAssetLicenses();
	%>

	<c:if test="<%= !assetLicenses.isEmpty() %>">
		<h3>
			<liferay-ui:message key="licenses" />
		</h3>

		<portlet:actionURL name="addAssetReceiptLicense" var="addAssetReceiptLicenseURL" />

		<aui:form action="<%= addAssetReceiptLicenseURL %>" method="post" name="fm">
			<aui:input name="mvcPath" type="hidden" value="/marketplace_admin/view_asset_receipt.jsp" />
			<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
			<aui:input name="assetReceiptId" type="hidden" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
			<aui:input name="assetLicenseId" type="hidden" value="" />
			<aui:input name="assetEntryId" type="hidden" value="<%= String.valueOf(assetEntry.getEntryId()) %>" />
			<aui:input name="ownerClassNameId" type="hidden" value="<%= String.valueOf(assetReceipt.getOwnerClassNameId()) %>" />
			<aui:input name="ownerClassPK" type="hidden" value="<%= String.valueOf(assetReceipt.getOwnerClassPK()) %>" />
			<aui:input name="productClassNameId" type="hidden" value="<%= String.valueOf(assetReceipt.getProductClassNameId()) %>" />
			<aui:input name="productClassPK" type="hidden" value="<%= String.valueOf(assetReceipt.getProductClassPK()) %>" />
			<aui:input name="productId" type="hidden" value="<%= String.valueOf(assetEntry.getClassUuid()) %>" />

			<aui:button-row>
				<input onClick="var licenseWindow = window.open('<portlet:renderURL windowState="<%= LiferayWindowState.POP_UP.toString() %>"><portlet:param name="mvcPath" value="/marketplace_admin/select_asset_license.jsp" /><portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceiptId) %>" /></portlet:renderURL>', 'license', 'directories=no,height=768,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=1024'); void(''); licenseWindow.focus();" type="button" value="<liferay-ui:message key="add-license" />" />
			</aui:button-row>
		</aui:form>

		<%
		List<AssetReceiptLicense> assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceiptId);
		%>

		<liferay-ui:search-container>
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
				%>

				<liferay-ui:search-container-column-text
					name="name"
					value="<%= HtmlUtil.escape(assetLicense.getName()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="order-id"
					value="<%= assetReceiptLicense.getUuid() %>"
				/>

				<liferay-ui:search-container-column-text
					name="usage-type"
					translate="<%= true %>"
					value="<%= assetLicense.getUsageTypeLabel() %>"
				/>

				<liferay-ui:search-container-column-text
					name="license-type"
					translate="<%= true %>"
					value="<%= assetLicense.getLicenseTypeLabel() %>"
				/>

				<liferay-ui:search-container-column-text
					name="term"
					translate="<%= true %>"
					value="<%= assetLicense.getLifetimeLabel() %>"
				/>

				<liferay-ui:search-container-column-text
					name="start-date"
					translate="<%= false %>"
					value="<%= mediumDateFormatDate.format(assetReceiptLicense.getStartDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name="end-date"
					translate="<%= false %>"
					value="<%= mediumDateFormatDate.format(assetReceiptLicense.getEndDate()) %>"
				/>

				<liferay-ui:search-container-column-text
					name='<%= (assetLicense.getLicenseType() == AssetLicenseConstants.LICENSE_TYPE_PER_USER) ? "max-users" : "servers" %>'
					value="<%= String.valueOf(assetLicense.getLicenseTypeAllotment()) %>"
				/>

				<liferay-ui:search-container-column-text>
					<liferay-ui:icon-menu>
						<portlet:actionURL name="deleteAssetReceiptLicense" var="deleteURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="assetReceiptLicenseId" value="<%= String.valueOf(assetReceiptLicense.getAssetReceiptLicenseId()) %>" />
						</portlet:actionURL>

						<liferay-ui:icon-delete url="<%= deleteURL %>" />
					</liferay-ui:icon-menu>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>

		<h3>
			<liferay-ui:message key="license-keys" />
		</h3>

		<%
		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			List<LicenseKey> licenseKeys = LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys(assetReceiptLicense.getAssetReceiptLicenseId(), false, true);

			if (licenseKeys.isEmpty()) {
				continue;
			}
		%>

			<div class="order-id">
				<strong>License keys for order id:</strong> <%= assetReceiptLicense.getUuid() %>
			</div>

			<liferay-ui:search-container>
				<liferay-ui:search-container-results
					results="<%= licenseKeys %>"
					total="<%= licenseKeys.size() %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.LicenseKey"
					escapedModel="<%= true %>"
					keyProperty="licenseKeyId"
					modelVar="licenseKey"
				>
					<liferay-ui:search-container-column-text
						name="host-name"
						value="<%= licenseKey.getHostName() %>"
					/>

					<liferay-ui:search-container-column-text
						name="ip-addresses"
						value="<%= licenseKey.getIpAddresses() %>"
					/>

					<liferay-ui:search-container-column-text
						name="mac-addresses"
						value="<%= licenseKey.getMacAddresses() %>"
					/>

					<liferay-ui:search-container-column-text
						name="online"
						translate="<%= true %>"
						value='<%= Validator.isNotNull(licenseKey.getServerId()) ? "yes" : "no" %>'
					/>

					<liferay-ui:search-container-column-text>
						<liferay-ui:icon-menu>
							<c:if test="<%= OSBLicenseKeyPermission.contains(permissionChecker, licenseKey, OSBActionKeys.UPDATE_ADVANCED) %>">
								<liferay-portlet:resourceURL id="serveLicenseKey" var="serveLicenseKeyURL">
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
								</liferay-portlet:resourceURL>

								<liferay-ui:icon image="download" url="<%= serveLicenseKeyURL %>" />

								<liferay-portlet:actionURL name="deactivateLicenseKey" var="deactivateLicenseKeyURL">
									<portlet:param name="mvcPath" value="/marketplace_admin/edit_asset_receipt.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
									<portlet:param name="assetReceiptLicenseId" value="<%= String.valueOf(assetReceiptLicense.getAssetReceiptLicenseId()) %>" />
								</liferay-portlet:actionURL>

								<liferay-ui:icon-deactivate url="<%= deactivateLicenseKeyURL %>" />
							</c:if>
						</liferay-ui:icon-menu>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" />
			</liferay-ui:search-container>

		<%
		}
		%>

	</c:if>
</div>

<aui:script>
	function <portlet:namespace />addLicense(assetLicenseId) {
		document.<portlet:namespace />fm.<portlet:namespace />assetLicenseId.value = assetLicenseId;

		submitForm(document.<portlet:namespace />fm);
	}
</aui:script>