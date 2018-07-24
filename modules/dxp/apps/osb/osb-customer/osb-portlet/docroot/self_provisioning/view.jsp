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

		params.put("accountCustomer", Long.valueOf(user.getUserId()));
		params.put("primaryProductEntry", new Object[] {OfferingEntryConstants.STATUS_ACTIVE, productEntryRootName, ProductEntryConstants.TYPE_PRIMARY, ProductEntryConstants.ENVIRONMENT_DEVELOPMENT});

		List<AccountEntry> accountEntries = AccountEntryLocalServiceUtil.search(null, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AccountEntryNameComparator(true));

		AccountEntry accountEntry = null;

		if (accountEntries.size() == 1) {
			accountEntry = accountEntries.get(0);
		}
		%>

		<span class="txt-b"><liferay-ui:message arguments="<%= new Object[] {ProductEntryConstants.getDisplayName(productEntryRootName)} %>" key="download-a-x-activation-key-for-your-developer-workstation" /></span>

		<div class="activation-key-container aui-helper-clearfix">
			<div class="aui-w33 content-column">
				<div class="activation-column content-column-content">
					<aui:select label="" name="accountEntryId" onChange='<%= renderResponse.getNamespace() + "selectAccountEntry(this.value);" %>'>
						<aui:option disabled="<%= true %>" label="project" selected="<%= true %>" />

						<c:choose>
							<c:when test="<%= accountEntry != null %>">
								<aui:option label="<%= accountEntry.getName() %>" value="<%= accountEntry.getAccountEntryId() %>" />
							</c:when>
							<c:otherwise>

								<%
								for (AccountEntry curAccountEntry : accountEntries) {
								%>

									<aui:option label="<%= curAccountEntry.getName() %>" value="<%= curAccountEntry.getAccountEntryId() %>" />

								<%
								}
								%>

							</c:otherwise>
						</c:choose>
					</aui:select>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="activation-column content-column-content">
					<aui:select label="" name="productMinorVersion">
						<aui:option disabled="<%= true %>" label="version" selected="<%= true %>" value="" />
					</aui:select>
				</div>
			</div>

			<div class="aui-w33 content-column">
				<div class="activation-column content-column-content">
					<aui:button id="activationKeyDownloadButton" onClick='<%= renderResponse.getNamespace() + "generateLicenseKey();" %>' value="download-activation-key" />
				</div>
			</div>
		</div>

		<aui:script>
			Liferay.provide(
				window,
				'<portlet:namespace />generateLicenseKey',
				function() {
					var A = AUI();

					var accountEntryId = A.one('#<portlet:namespace />accountEntryId');
					var productMinorVersion = A.one('#<portlet:namespace />productMinorVersion');

					if (accountEntryId && productMinorVersion) {
						if (!accountEntryId.val() || !productMinorVersion.val()) {
							alert('<liferay-ui:message key="please-fill-out-all-required-fields" />');

							return;
						}

						<portlet:resourceURL id="generateLicenseKey" var="generateLicenseKeyURL">
							<portlet:param name="productEntryRootName" value="<%= productEntryRootName %>" />
						</portlet:resourceURL>

						window.location.href = '<%= generateLicenseKeyURL.toString() %>&<portlet:namespace />accountEntryId=' + accountEntryId.val() + '&<portlet:namespace />productMinorVersion=' + productMinorVersion.val();
					}
				},
				['aui-base']
			);

			Liferay.provide(
				window,
				'<portlet:namespace />selectAccountEntry',
				function(accountEntryId) {
					var A = AUI();

					var productMinorVersionSelect = A.one('#<portlet:namespace />productMinorVersion');

					if (productMinorVersionSelect) {
						productMinorVersionSelect.empty();

						var productMinorVersionOptions = [];

						productMinorVersionOptions.push('<option disabled><liferay-ui:message key="version" /></option>');

						<%
						for (int productMinorVersion : productMinorVersions) {
							ListType productMinorVersionType = ListTypeServiceUtil.getListType(productMinorVersion);
						%>

							productMinorVersionOptions.push('<option value="<%= productMinorVersion %>"><%= LanguageUtil.get(request, productMinorVersionType.getName()) %></option>');

						<%
						}
						%>

						productMinorVersionSelect.append(productMinorVersionOptions.join(''));
					}
				},
				['aui-base']
			);

			<c:if test="<%= accountEntry != null %>">
				<portlet:namespace />selectAccountEntry(<%= accountEntry.getAccountEntryId() %>);
			</c:if>
		</aui:script>
	</c:otherwise>
</c:choose>