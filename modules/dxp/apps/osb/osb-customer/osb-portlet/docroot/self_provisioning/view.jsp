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
LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

params.put("accountCustomer", new Long(user.getUserId()));
params.put("activePortalLicense", new int[] {OfferingEntryConstants.STATUS_ACTIVE, ProductEntryConstants.TYPE_PRIMARY});

List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AccountEntryNameComparator(true));

AccountEntry accountEntry = null;

if (accountEntries.size() == 1) {
	accountEntry = accountEntries.get(0);
}
%>

<span class="txt-b"><liferay-ui:message key="download-your-portal-activation-key" /></span>

<div class="aui-helper-clearfix activation-key-container">
	<div class="aui-w25 content-column">
		<div class="activation-column content-column-content">
			<select id="<portlet:namespace />accountEntryId" name="<portlet:namespace />accountEntryId" onChange="<portlet:namespace />selectAccountEntry(this.value);">
				<c:choose>
					<c:when test="<%= accountEntry != null %>">
						<option selected value="<%= accountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(accountEntry.getName()) %></option>
					</c:when>
					<c:otherwise>
						<option value=""></option>

						<%
						for (AccountEntry curAccountEntry : accountEntries) {
						%>

							<option value="<%= curAccountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(curAccountEntry.getName()) %></option>

						<%
						}
						%>

					</c:otherwise>
				</c:choose>
			</select>
		</div>
	</div>

	<div class="aui-w25 content-column">
		<div class="activation-column content-column-content">
			<select id="<portlet:namespace />productEntryDisplayName" name="<portlet:namespace />productEntryDisplayName"></select>
		</div>
	</div>

	<div class="aui-w25 content-column">
		<div class="activation-column content-column-content">
			<select id="<portlet:namespace />licenseEntryType" name="<portlet:namespace />licenseEntryType"></select>
		</div>
	</div>

	<div class="aui-w25 content-column">
		<div class="activation-column content-column-content">
			<button id="<portlet:namespace />activationKeyDownloadButton" onClick="<portlet:namespace />generateLicenseKey();" type="button"><liferay-ui:message key="download-activation-key" /></button>
		</div>
	</div>
</div>

<aui:script use="aui-io">
	Liferay.provide(
		window,
		'<portlet:namespace />generateLicenseKey',
		function() {
			var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
			var productEntryDisplayName = A.one('#<portlet:namespace />productEntryDisplayName');
			var licenseEntryType = A.one('#<portlet:namespace />licenseEntryType');

			if ((accountEntryId.val() <= 0) || (productEntryDisplayName.val() == '') || (licenseEntryType.val() == '')) {
				alert('<liferay-ui:message key="please-fill-out-all-required-fields" />');

				return;
			}

			window.location.href = '<liferay-portlet:resourceURL id="generateLicenseKey" />&<portlet:namespace />accountEntryId=' + accountEntryId.val() + '&<portlet:namespace />productEntryDisplayName=' + productEntryDisplayName.val() + '&<portlet:namespace />licenseEntryType=' + licenseEntryType.val();
		}
	);

	Liferay.provide(
		window,
		'<portlet:namespace />selectAccountEntry',
		function(accountEntryId) {
			var licenseEntryTypeSelect = A.one('#<portlet:namespace />licenseEntryType');
			var productEntryDisplayNameSelect = A.one('#<portlet:namespace />productEntryDisplayName');

			licenseEntryTypeSelect.empty();
			productEntryDisplayNameSelect.empty();

			if (accountEntryId > 0) {
				A.io.request(
					'<liferay-portlet:resourceURL id="productEntryDisplayNames" />',
					{
						data: {
							<portlet:namespace />accountEntryId: accountEntryId
						},
						dataType: 'json',
						method: 'post',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var productEntryDisplayNameOptions = [];

								var productEntryDisplayNames = A.JSON.parse(response["productEntryDisplayNames"]);

								for (var i in productEntryDisplayNames) {
									productEntryDisplayNameOptions.push('<option value="' + productEntryDisplayNames[i] + '">' + Liferay.Language.get(productEntryDisplayNames[i]) + '</option>');
								}

								productEntryDisplayNameSelect.append(productEntryDisplayNameOptions.join(''));

								productEntryDisplayNameSelect.set('selectedIndex', 0);

								var licenseEntryTypeOptions = [];

								licenseEntryTypeOptions.push('<option value="developer"><liferay-ui:message key="developer" /></option>');
								licenseEntryTypeOptions.push('<option value="developer-cluster"><liferay-ui:message key="developer-cluster" /></option>');

								licenseEntryTypeSelect.append(licenseEntryTypeOptions.join(''));

								licenseEntryTypeSelect.set('selectedIndex', 0);
							}
						}
					}
				);
			}
		}
	);

	<c:if test="<%= accountEntry != null %>">
		<portlet:namespace />selectAccountEntry(<%= accountEntry.getAccountEntryId() %>);
	</c:if>
</aui:script>