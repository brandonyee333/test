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
long appPackageId = ParamUtil.getLong(request, "appPackageId");

long assetReceiptId = ParamUtil.getLong(request, "assetReceiptId");

AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(assetReceiptId);

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());
%>

<div class="marketplace-apps view-license-keys">
	<c:if test="<%= appPackageId <= 0 %>">
		<liferay-portlet:renderURL var="viewAppEntryURL">
			<portlet:param name="mvcPath" value="/marketplace_apps/view_app_entry.jsp" />
			<portlet:param name="assetReceiptId" value="<%= String.valueOf(assetReceipt.getAssetReceiptId()) %>" />
		</liferay-portlet:renderURL>

		<liferay-util:include page="/marketplace_apps/app_entry_header.jsp" servletContext="<%= application %>">
			<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
			<liferay-util:param name="backURL" value="<%= viewAppEntryURL %>" />
		</liferay-util:include>
	</c:if>

	<c:if test="<%= assetReceipt.hasActiveAssetReceiptLicenses() %>">
		<div class="license-information">
			<table>
				<thead>
					<tr>
						<th class="col-1 first">
							<liferay-ui:message key="license-type" />
						</th>
						<th class="col-2">
							<liferay-ui:message key="usage-type" />
						</th>
						<th class="col-3">
							<liferay-ui:message key="license-expiration" />
						</th>
						<th class="col-4 last">
							<liferay-ui:message key="support-expiration" />
						</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="<%= assetReceipt.hasActiveAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_STANDARD) %>">
						<tr>
							<td class="col-1 first">
								<liferay-ui:message key="<%= appEntry.getLicenseLifetimeLabel() %>" />
							</td>
							<td class="col-2">
								<liferay-ui:message key="standard" />
							</td>
							<td class="col-3">

								<%
								Date assetReceiptStandardLicensesEndDate = assetReceipt.getAssetReceiptLicensesEndDate(AssetLicenseConstants.USAGE_TYPE_STANDARD);
								%>

								<c:if test="<%= assetReceiptStandardLicensesEndDate != null %>">
									<%= longDateFormatDate.format(assetReceiptStandardLicensesEndDate) %>
								</c:if>
							</td>
							<td class="col-4 last">

								<%
								Date assetReceiptStandardSupportsEndDate = assetReceipt.getAssetReceiptSupportsEndDate(AssetLicenseConstants.USAGE_TYPE_STANDARD);
								%>

								<c:choose>
									<c:when test="<%= assetReceiptStandardSupportsEndDate != null %>">
										<%= longDateFormatDate.format(assetReceiptStandardSupportsEndDate) %>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="not-available" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>

					<c:if test="<%= assetReceipt.hasActiveAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_DEVELOPER) %>">
						<tr>
							<td class="col-1 first">
								<liferay-ui:message key="<%= appEntry.getLicenseLifetimeLabel() %>" />
							</td>
							<td class="col-2">
								<liferay-ui:message key="developer" />
							</td>
							<td class="col-3">

								<%
								Date assetReceiptDeveloperLicensesEndDate = assetReceipt.getAssetReceiptLicensesEndDate(AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
								%>

								<c:if test="<%= assetReceiptDeveloperLicensesEndDate != null %>">
									<%= longDateFormatDate.format(assetReceiptDeveloperLicensesEndDate) %>
								</c:if>
							</td>
							<td class="col-4 last">

								<%
								Date assetReceiptDeveloperSupportsEndDate = assetReceipt.getAssetReceiptSupportsEndDate(AssetLicenseConstants.USAGE_TYPE_DEVELOPER);
								%>

								<c:choose>
									<c:when test="<%= assetReceiptDeveloperSupportsEndDate != null %>">
										<%= longDateFormatDate.format(assetReceiptDeveloperSupportsEndDate) %>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="not-available" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:if>

					<c:if test="<%= assetReceipt.hasActiveAssetReceiptLicenses(AssetLicenseConstants.USAGE_TYPE_TRIAL) %>">
						<tr>
							<td class="col-1 first">
								<liferay-ui:message key="<%= appEntry.getLicenseLifetimeLabel() %>" />
							</td>
							<td class="col-2">
								<liferay-ui:message key="trial" />
							</td>
							<td class="col-3">

								<%
								Date assetReceiptTrialLicensesEndDate = assetReceipt.getAssetReceiptLicensesEndDate(AssetLicenseConstants.USAGE_TYPE_TRIAL);
								%>

								<c:if test="<%= assetReceiptTrialLicensesEndDate != null %>">
									<%= longDateFormatDate.format(assetReceiptTrialLicensesEndDate) %>
								</c:if>
							</td>
						</tr>
					</c:if>
				</tbody>
			</table>
		</div>
	</c:if>

	<c:if test="<%= appPackageId <= 0 %>">
		<h2>
			<liferay-ui:message key="registered-servers" />
		</h2>

		<%
		String[] taglibArguments = new String[] {"<a href='https://www.liferay.com'>", "</a>", "<a href='https://web.liferay.com/web/developer/marketplace/contact'>", "</a>"};
		%>

		<p>
			<liferay-ui:message arguments="<%= taglibArguments %>" key="you-can-only-deauthorize-servers-that-you-registered" />
		</p>

		<p>
			<liferay-ui:message arguments="<%= taglibArguments %>" key="note-if-you-register-your-server-from-liferay" />
		</p>
	</c:if>

	<div class="purchased-licenses">

		<%
		List<LicenseKey> licenseKeys = new ArrayList<LicenseKey>();

		List<AssetReceiptLicense> assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceiptId);

		for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
			List<LicenseKey> receiptLicenseKeys = LicenseKeyLocalServiceUtil.getAssetReceiptLicenseLicenseKeys(assetReceiptLicense.getAssetReceiptLicenseId(), false, true);

			licenseKeys.addAll(receiptLicenseKeys);
		}
		%>

		<c:if test="<%= !licenseKeys.isEmpty() %>">
			<div class="used-licenses">
				<liferay-ui:search-container
					delta="<%= licenseKeys.size() %>"
				>
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
							name="registered-host-name"
							valign="top"
							value="<%= licenseKey.getHostName() %>"
						/>

						<liferay-ui:search-container-column-text
							name="ip-address"
							valign="top"
						>

							<%
							String ipAddresses = HtmlUtil.escape(licenseKey.getIpAddresses());

							ipAddresses = StringUtil.replace(ipAddresses, StringPool.COMMA, "<br />");
							%>

							<%= ipAddresses %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="mac-address"
							valign="top"
						>

							<%
							String macAddresses = HtmlUtil.escape(licenseKey.getMacAddresses());

							macAddresses = StringUtil.replace(macAddresses, StringPool.COMMA, "<br />");
							%>

							<%= macAddresses %>
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							name="usage-type"
							valign="top"
						>

							<%
							AssetReceiptLicense assetReceiptLicense = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicense(licenseKey.getAssetReceiptLicenseId());
							%>

							<liferay-ui:message key="<%= assetReceiptLicense.getUsageTypeLabel() %>" />
						</liferay-ui:search-container-column-text>

						<liferay-ui:search-container-column-text
							valign="top"
						>
							<span class="actions">
								<c:choose>
									<c:when test="<%= appPackageId <= 0 %>">
										<c:if test="<%= Validator.isNotNull(licenseKey.getServerId()) %>">
											<liferay-portlet:actionURL name="deactivateLicenseKey" var="deactivateLicenseKeyURL">
												<portlet:param name="mvcPath" value="/marketplace/view_app_entry.jsp" />
												<portlet:param name="redirect" value="<%= currentURL %>" />
												<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
												<portlet:param name="assetReceiptLicenseId" value="<%= String.valueOf(licenseKey.getAssetReceiptLicenseId()) %>" />
											</liferay-portlet:actionURL>

											<a class="btn" href="javascript:;" onClick="deactivateLicenseKey('<%= HtmlUtil.escapeJS(deactivateLicenseKeyURL) %>');"><liferay-ui:message key="deauthorize" /></a>
										</c:if>

										<liferay-portlet:resourceURL id="serveLicenseKey" var="serveLicenseKeyURL">
											<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
										</liferay-portlet:resourceURL>

										<a class="btn" href="<%= serveLicenseKeyURL %>"><liferay-ui:message key="download" /></a>
									</c:when>
									<c:when test="<%= Validator.isNull(licenseKey.getServerId()) %>">
										<liferay-portlet:resourceURL id="serveApp" var="serveAppURL">
											<portlet:param name="licenseKeyId" value="<%= String.valueOf(licenseKey.getLicenseKeyId()) %>" />
											<portlet:param name="appPackageId" value="<%= String.valueOf(appPackageId) %>" />
										</liferay-portlet:resourceURL>

										<a class="btn" href="javascript:;" onClick="downloadAppPackage('<%= HtmlUtil.escapeJS(serveAppURL) %>')"><liferay-ui:message key="download" /></a>
									</c:when>
								</c:choose>
							</span>
						</liferay-ui:search-container-column-text>
					</liferay-ui:search-container-row>

					<liferay-ui:search-iterator paginate="<%= false %>" />
				</liferay-ui:search-container>
			</div>
		</c:if>
	</div>

	<%
	PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
	%>

	<c:choose>
		<c:when test="<%= SessionErrors.isEmpty(portletRequest) %>">
			<a href="javascript:;" onClick="showLicenseRegistration(this);"><liferay-ui:message key='<%= (appPackageId > 0) ? "i-dont-see-my-server" : "register-new-server" %>' /></a>

			<div class="aui-helper-hidden" id="registerNewServerSection">
				<liferay-util:include page="/marketplace_apps/edit_license_key.jsp" servletContext="<%= application %>">
					<liferay-util:param name="assetReceiptId" value="<%= String.valueOf(assetReceiptId) %>" />
					<liferay-util:param name="appPackageId" value="<%= String.valueOf(appPackageId) %>" />
				</liferay-util:include>
			</div>
		</c:when>
		<c:otherwise>
			<div id="registerNewServerSection">
				<liferay-util:include page="/marketplace_apps/edit_license_key.jsp" servletContext="<%= application %>">
					<liferay-util:param name="assetReceiptId" value="<%= String.valueOf(assetReceiptId) %>" />
					<liferay-util:param name="appPackageId" value="<%= String.valueOf(appPackageId) %>" />
				</liferay-util:include>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<aui:script>
	function deactivateLicenseKey(url) {
		if (confirm('<liferay-ui:message key="are-you-sure-you-want-to-deauthorize-this-license" />')) {
			submitForm(document.hrefFm, url);
		}
	}

	Liferay.provide(
		window,
		'downloadAppPackage',
		function(url) {
			var A = AUI();

			window.parent.location = url;

			parentWindowAUI = Liferay.Util.getTop().AUI();

			var iframeId = '#<portlet:namespace />viewLicenseKeys';

			if (parentWindowAUI.one(iframeId)) {
				parentWindowAUI.DialogManager.closeByChild(iframeId);
			}
		},
		['aui-dialog', 'aui-dialog-iframe']
	);

	Liferay.provide(
		window,
		'showLicenseRegistration',
		function(node) {
			node.className = 'aui-helper-hidden';

			AUI().one('#registerNewServerSection').show();
		},
		[]
	);
</aui:script>