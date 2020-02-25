<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */
--%>

<%@ include file="/init.jsp" %>

<%
long[] digitalEnterpriseProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "digitalEnterprise_productMinorVersions"), 0L);
long[] portalProductMinorVersions = StringUtil.split(PrefsParamUtil.getString(portletPreferences, request, "portal_productMinorVersions"), 0L);
%>

<c:choose>
	<c:when test="<%= ArrayUtil.isEmpty(digitalEnterpriseProductMinorVersions) || ArrayUtil.isEmpty(portalProductMinorVersions) %>">

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

		params.put("accountCustomer", Long.valueOf(user.getUserId()));
		params.put("primaryProductEntry", new Object[] {OfferingEntryConstants.STATUS_ACTIVE, ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE, ProductEntryConstants.ROOT_NAME_PORTAL, ProductEntryConstants.ROOT_DXP_CLOUD, ProductEntryConstants.TYPE_PRIMARY, ProductEntryConstants.ENVIRONMENT_DEVELOPMENT});

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AccountEntryNameComparator(true));
		%>

		<div class="container-fluid-1280">
			<aui:row>
				<aui:col width="<%= 30 %>">
					<aui:select label="" name="accountEntryId" onChange='<%= renderResponse.getNamespace() + "selectAccountEntry(this.value);" %>'>
						<aui:option disabled="<%= true %>" label="project" selected="<%= true %>" />

						<%
						for (AccountEntry accountEntry : accountEntries) {
						%>

							<aui:option label="<%= accountEntry.getName() %>" value="<%= accountEntry.getAccountEntryId() %>" />

						<%
						}
						%>

					</aui:select>
				</aui:col>

				<aui:col width="<%= 30 %>">
					<aui:select label="" name="productEntryRootName" onChange='<%= renderResponse.getNamespace() + "selectProduct(this.value);" %>'>
						<aui:option disabled="<%= true %>" label="product" selected="<%= true %>" value="" />
					</aui:select>
				</aui:col>

				<aui:col width="<%= 20 %>">
					<aui:select label="" name="productMinorVersion">
						<aui:option disabled="<%= true %>" label="version" selected="<%= true %>" value="" />
					</aui:select>
				</aui:col>

				<aui:col width="<%= 20 %>">
					<aui:button id="activationKeyDownloadButton" onClick='<%= renderResponse.getNamespace() + "generateLicenseKey();" %>' primary="<%= true %>" value="download" />
				</aui:col>
			</aui:row>
		</div>

		<aui:script>
			Liferay.provide(
				window,
				'<portlet:namespace />generateLicenseKey',
				function() {
					var A = AUI();

					var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
					var productEntryRootName = A.one('#<portlet:namespace />productEntryRootName');
					var productMinorVersion = A.one('#<portlet:namespace />productMinorVersion');

					if (accountEntryId && productEntryRootName && productMinorVersion) {
						if (!accountEntryId.val() || !productEntryRootName || !productMinorVersion.val()) {
							alert('<liferay-ui:message key="please-fill-out-all-required-fields" />');

							return;
						}

						<portlet:resourceURL id="generateLicenseKey" var="generateLicenseKeyURL" />

						window.location.href = '<%= generateLicenseKeyURL.toString() %>&<portlet:namespace />accountEntryId=' + accountEntryId.val() + '&<portlet:namespace />productEntryRootName=' + productEntryRootName.val() + '&<portlet:namespace />productMinorVersion=' + productMinorVersion.val();
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />init',
				function(accountEntryId) {
					var A = AUI();

					var accountEntryIdSelect = A.one('#<portlet:namespace />accountEntryId');

					accountEntryIdSelect.val(accountEntryId);

					<portlet:namespace />selectAccountEntry(accountEntryId);
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />selectAccountEntry',
				function(accountEntryId) {
					var A = AUI();

					var productEntryRootNameSelect = A.one('#<portlet:namespace />productEntryRootName');

					if (productEntryRootNameSelect) {
						productEntryRootNameSelect.empty();

						var productEntryRootNameOptions = [];

						productEntryRootNameOptions.push('<option disabled><liferay-ui:message key="product" /></option>');

						var selectedOption = '';

						<%
						for (AccountEntry accountEntry : accountEntries) {
						%>

							if (accountEntryId == '<%= accountEntry.getAccountEntryId() %>') {

								<%
								Set<String> productEntryRootNames = SupportUtil.getSelfProvisioningProducts(accountEntry.getAccountEntryId());

								for (String productEntryRootName : productEntryRootNames) {
								%>

									if (selectedOption == '') {
										selectedOption = '<%= productEntryRootName %>';
									}

									productEntryRootNameOptions.push('<option value="<%= productEntryRootName %>"><%= productEntryRootName %></option>');

								<%
								}
								%>

							}

						<%
						}
						%>

						productEntryRootNameSelect.append(productEntryRootNameOptions.join(''));

						if (selectedOption != '') {
							productEntryRootNameSelect.val(selectedOption);

							<portlet:namespace />selectProduct(selectedOption);
						}
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />selectProduct',
				function(productEntryRootName) {
					var A = AUI();

					var productMinorVersionSelect = A.one('#<portlet:namespace />productMinorVersion');

					if (productMinorVersionSelect) {
						productMinorVersionSelect.empty();

						var productMinorVersionOptions = [];

						productMinorVersionOptions.push('<option disabled><liferay-ui:message key="version" /></option>');

						if (productEntryRootName == '<%= ProductEntryConstants.ROOT_NAME_DIGITAL_ENTERPRISE %>') {

							<%
							for (long productMinorVersion : digitalEnterpriseProductMinorVersions) {
								ListType productMinorVersionType = ListTypeServiceUtil.getListType(productMinorVersion);
							%>

								productMinorVersionOptions.push('<option value="<%= productMinorVersion %>"><%= LanguageUtil.get(request, productMinorVersionType.getName()) %></option>');

							<%
							}
							%>

						}
						else if (productEntryRootName == '<%= ProductEntryConstants.ROOT_NAME_PORTAL %>') {

							<%
							for (long productMinorVersion : portalProductMinorVersions) {
								ListType productMinorVersionType = ListTypeServiceUtil.getListType(productMinorVersion);
							%>

								productMinorVersionOptions.push('<option value="<%= productMinorVersion %>"><%= LanguageUtil.get(request, productMinorVersionType.getName()) %></option>');

							<%
							}
							%>

						}

						productMinorVersionSelect.append(productMinorVersionOptions.join(''));
					}
				},
				['aui-base']
			);

			<c:if test="<%= accountEntries.size() == 1 %>">

				<%
				AccountEntry accountEntry = accountEntries.get(0);
				%>

				<portlet:namespace />init(<%= accountEntry.getAccountEntryId() %>);
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>