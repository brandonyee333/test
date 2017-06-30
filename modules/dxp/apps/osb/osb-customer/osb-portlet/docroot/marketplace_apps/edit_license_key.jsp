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

<%@ include file="/marketplace_apps/init.jsp" %>

<%
long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

long assetReceiptLicenseId = ParamUtil.getLong(request, "assetReceiptLicenseId");

long appPackageId = ParamUtil.getLong(request, "appPackageId");
%>

<div class="marketplace-apps edit-license-key">
	<h2>
		<liferay-ui:message key="register-server" />
	</h2>

	<c:if test="<%= appPackageId > 0 %>">

		<%
		String[] taglibArguments = new String[] {"<a href='https://www.liferay.com'>", "</a>", "<a href='https://web.liferay.com/web/developer/marketplace/contact'>", "</a>"};
		%>

		<p>
			<liferay-ui:message arguments="<%= taglibArguments %>" key="note-if-you-register-your-server-from-liferay" />
		</p>
	</c:if>

	<portlet:actionURL name="addLicenseKey" var="addLicenseKeyURL" />

	<aui:form action="<%= addLicenseKeyURL %>" method="post" name="fm">
		<aui:input name="mvcPath" type="hidden" value="/marketplace_apps/view_license_keys.jsp" />
		<aui:input name="assetReceiptId" type="hidden" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
		<aui:input name="appPackageId" type="hidden" value="<%= String.valueOf(appPackageId) %>" />

		<liferay-ui:error exception="<%= LicenseKeyIPAddressException.class %>" message="please-enter-valid-ip-addresses" />
		<liferay-ui:error exception="<%= LicenseKeyMACAddressException.class %>" message="please-enter-valid-mac-addresses" />
		<liferay-ui:error exception="<%= LicenseKeyServerInfoException.class %>" message="please-enter-the-servers-information" />
		<liferay-ui:error exception="<%= MaximumLicenseKeyException.class %>" message="please-select-a-valid-license" />
		<liferay-ui:error exception="<%= NoSuchAssetReceiptLicenseException.class %>" message="please-select-a-valid-license" />

		<c:if test="<%= appPackageId > 0 %>">
			<div class="aui-helper-hidden portlet-msg-error"></div>
		</c:if>

		<aui:fieldset>

			<%
			List<AssetReceiptLicense> assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceipt.getAssetReceiptId());
			%>

			<liferay-ui:search-container
				delta="<%= assetReceiptLicenses.size() %>"
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
						<aui:input disabled="<%= !licenseKeysAvailable %>" label="<%= StringPool.BLANK %>" name="assetReceiptLicenseId" type="radio" value="<%= assetReceiptLicense.getAssetReceiptLicenseId() %>" />
					</liferay-ui:search-container-column-text>

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
						name="available-licenses"
					>

						<%
						int count = LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeysCount(assetReceiptLicense.getAssetReceiptLicenseId(), false, true);
						%>

						<%= count %>/<%= assetLicense.getLicenseTypeAllotment() %>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator paginate="<%= false %>" />
			</liferay-ui:search-container>
		</aui:fieldset>

		<aui:fieldset>
			<aui:input cssClass="aui-w90 fl" name="hostName" type="text" />

			<aui:input cssClass="aui-w50 fl" helpMessage="one-address-per-line" name="ipAddresses" type="textarea" />

			<aui:input cssClass="aui-w50 fl" helpMessage="one-address-per-line" name="macAddresses" type="textarea" />
		</aui:fieldset>

		<aui:button-row>
			<aui:button type="submit" value='<%= (appPackageId > 0) ? "register-and-download" : "register" %>' />
		</aui:button-row>
	</aui:form>
</div>

<c:if test="<%= appPackageId > 0 %>">
	<aui:script use="aui-dialog,aui-dialog-iframe,aui-io-request">
		var form = A.one('#<portlet:namespace />fm');

		form.on(
			'submit',
			function(event) {
				event.halt();

				A.io.request(
					form.get('action'),
					{
						dataType: 'json',
						form: {
							id: document.<portlet:namespace />fm
						},
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								if (response.success) {
									parentWindowAUI = Liferay.Util.getTop().AUI();

									parentWindowAUI.DialogManager.closeByChild('#<portlet:namespace />viewLicenseKeys');

									window.parent.location = response.downloadURL;
								}
								else if (response.exception) {
									if (response.exception === "<%= LicenseKeyIPAddressException.class.getName() %>") {
										message = '<liferay-ui:message key="please-enter-valid-ip-addresses" unicode="<%= true %>" />';
									}
									else if (response.exception === "<%= LicenseKeyMACAddressException.class.getName() %>") {
										message = '<liferay-ui:message key="please-enter-valid-mac-addresses" unicode="<%= true %>" />';
									}
									else if (response.exception === "<%= LicenseKeyServerInfoException.class.getName() %>") {
										message = '<liferay-ui:message key="please-enter-the-servers-information" unicode="<%= true %>" />';
									}
									else if (response.exception === "<%= MaximumLicenseKeyException.class.getName() %>") {
										message = '<liferay-ui:message key="please-select-a-valid-license" unicode="<%= true %>" />';
									}
									else if (response.exception === "<%= NoSuchAssetReceiptLicenseException.class.getName() %>") {
										message = '<liferay-ui:message key="please-select-a-valid-license" unicode="<%= true %>" />';
									}

									var messageNode = A.one('.marketplace-apps.edit-license-key .portlet-msg-error');

									messageNode.setContent(message);

									messageNode.show();
								}
							}
						}
					}
				);
			}
		);
	</aui:script>
</c:if>