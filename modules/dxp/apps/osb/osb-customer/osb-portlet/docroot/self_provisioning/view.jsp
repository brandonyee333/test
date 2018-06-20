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
String productEntryRootName = portletPreferences.getValue("productEntryRootName", StringPool.BLANK);
int[] productMinorVersions = StringUtil.split(portletPreferences.getValue("productMinorVersions", StringPool.BLANK), 0);
%>

<c:choose>
	<c:when test="<%= Validator.isNull(productEntryRootName) || ArrayUtil.isEmpty(productMinorVersions) %>">

		<%
		renderRequest.setAttribute(WebKeys.PORTLET_CONFIGURATOR_VISIBILITY, Boolean.TRUE);
		%>

		<div class="portlet-msg-info">
			<liferay-ui:message key="please-configure-this-portlet-to-make-it-visible-to-all-users" />
		</div>
	</c:when>
	<c:otherwise>

		<%
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put("accountCustomer", new Long(user.getUserId()));
		params.put("primaryProductEntry", new Object[] {OfferingEntryConstants.STATUS_ACTIVE, productEntryRootName, ProductEntryConstants.TYPE_PRIMARY});

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AccountEntryNameComparator(true));

		AccountEntry accountEntry = null;

		if (accountEntries.size() == 1) {
			accountEntry = accountEntries.get(0);
		}
		%>

		<span class="txt-b"><liferay-ui:message arguments="<%= new Object[] {productEntryRootName} %>" key="download-your-x-activation-key" /></span>

		<div class="aui-helper-clearfix activation-key-container">
			<div class="aui-w25 content-column">
				<div class="activation-column content-column-content">
					<select id="<portlet:namespace />accountEntryId" name="<portlet:namespace />accountEntryId" onChange="<portlet:namespace />selectAccountEntry(this.value);">
						<c:choose>
							<c:when test="<%= accountEntry != null %>">
								<option selected value="<%= accountEntry.getAccountEntryId() %>"><%= HtmlUtil.escape(accountEntry.getName()) %></option>
							</c:when>
							<c:otherwise>
								<option disabled selected><liferay-ui:message key="project" /></option>

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
					<select id="<portlet:namespace />productMinorVersion" name="<portlet:namespace />productMinorVersion">
						<option disabled selected><liferay-ui:message key="version" /></option>
					</select>
				</div>
			</div>

			<div class="aui-w25 content-column">
				<div class="activation-column content-column-content">
					<select id="<portlet:namespace />licenseEntryType" name="<portlet:namespace />licenseEntryType">
						<option disabled selected><liferay-ui:message key="type" /></option>
					</select>
				</div>
			</div>

			<div class="aui-w25 content-column">
				<div class="activation-column content-column-content">
					<button id="<portlet:namespace />activationKeyDownloadButton" onClick="<portlet:namespace />generateLicenseKey();" type="button"><liferay-ui:message key="download-activation-key" /></button>
				</div>
			</div>
		</div>

		<aui:script>
			function <portlet:namespace />generateLicenseKey() {
				var A = AUI();

				var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
				var productMinorVersion = A.one('#<portlet:namespace />productMinorVersion');
				var licenseEntryType = A.one('#<portlet:namespace />licenseEntryType');

				if ((accountEntryId.val() <= 0) || (productMinorVersion.val() == '') || (licenseEntryType.val() == '')) {
					alert('<liferay-ui:message key="please-fill-out-all-required-fields" />');

					return;
				}

				<portlet:resourceURL id="generateLicenseKey" var="generateLicenseKeyURL">
					<portlet:param name="productEntryRootName" value="<%= productEntryRootName %>" />
				</portlet:resourceURL>

				window.location.href = '<%= generateLicenseKeyURL.toString() %>&<portlet:namespace />accountEntryId=' + accountEntryId.val() + '&<portlet:namespace />productMinorVersion=' + productMinorVersion.val() + '&<portlet:namespace />licenseEntryType=' + licenseEntryType.val();
			}

			function <portlet:namespace />selectAccountEntry(accountEntryId) {
				var A = AUI();

				var productMinorVersionSelect = A.one('#<portlet:namespace />productMinorVersion');

				productMinorVersionSelect.empty();

				var productMinorVersionOptions = [];

				productMinorVersionOptions.push('<option disabled><liferay-ui:message key="version" /></option>');

				<%
				for (int productMinorVersion : productMinorVersions) {
					ListType productMinorVersionType = ListTypeServiceUtil.getListType(productMinorVersion);
				%>

					productMinorVersionOptions.push('<option value="<%= productMinorVersion %>"><%= LanguageUtil.get(pageContext, productMinorVersionType.getName()) %></option>');

				<%
				}
				%>

				productMinorVersionSelect.append(productMinorVersionOptions.join(''));

				productMinorVersionSelect.set('selectedIndex', 0);

				var licenseEntryTypeSelect = A.one('#<portlet:namespace />licenseEntryType');

				licenseEntryTypeSelect.empty();

				var licenseEntryTypeOptions = [];

				licenseEntryTypeOptions.push('<option disabled><liferay-ui:message key="type" /></option>');
				licenseEntryTypeOptions.push('<option value="developer"><liferay-ui:message key="developer" /></option>');
				licenseEntryTypeOptions.push('<option value="developer-cluster"><liferay-ui:message key="developer-cluster" /></option>');

				licenseEntryTypeSelect.append(licenseEntryTypeOptions.join(''));

				licenseEntryTypeSelect.set('selectedIndex', 0);
			}

			<c:if test="<%= accountEntry != null %>">
				<portlet:namespace />selectAccountEntry(<%= accountEntry.getAccountEntryId() %>);
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>