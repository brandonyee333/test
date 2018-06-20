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

params.put("accountEntryMembership", new Long(user.getUserId()));
params.put("activePortalLicense", new Long[] {new Long(OfferingEntryConstants.STATUS_ACTIVE), new Long(ProductEntryConstants.TYPE_PRIMARY)});

List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AccountEntryNameComparator(true));

AccountEntry accountEntry = null;

if (accountEntries.size() == 1) {
	accountEntry = accountEntries.get(0);
}
%>

<span class="txt-b"><liferay-ui:message key="download-your-portal-activation-key" /></span>

<div class="osb-portlet-self-provisioning">
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
				<select id="<portlet:namespace />productDisplayName" name="<portlet:namespace />productDisplayName"></select>
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
</div>

<aui:script>
	function <portlet:namespace />generateLicenseKey() {
		var A = AUI();

		var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
		var productDisplayName = A.one('#<portlet:namespace />productDisplayName');
		var licenseEntryType = A.one('#<portlet:namespace />licenseEntryType');

		submitForm(document.hrefFm, '<liferay-portlet:resourceURL id="generateLicenseKey" />');
	}
</aui:script>

<aui:script use="aui-io">
	Liferay.provide(
		window,
		'<portlet:namespace />selectAccountEntry',
		function(accountEntryId) {
			var licenseEntryTypeSelect = A.one('#<portlet:namespace />licenseEntryType');
			var productDisplayNameSelect = A.one('#<portlet:namespace />productDisplayName');

			licenseEntryTypeSelect.empty();
			productDisplayNameSelect.empty();

			if (accountEntryId > 0) {
				A.io.request(
					'<liferay-portlet:resourceURL id="productDisplayNames" />',
					{
						data: {
							<portlet:namespace />accountEntryId: accountEntryId
						},
						dataType: 'json',
						method: 'post',
						on: {
							success: function(event, id, obj) {
								var response = this.get('responseData');

								var productDisplayNameOptions = [];

								var productDisplayNames = A.JSON.parse(response["productDisplayNames"]);

								for (var i in productDisplayNames) {
									productDisplayNameOptions.push('<option value="' + productDisplayNames[i] + '">' + Liferay.Language.get(productDisplayNames[i]) + '</option>');
								}

								productDisplayNameSelect.append(productDisplayNameOptions.join(''));

								productDisplayNameSelect.set('selectedIndex', 0);

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