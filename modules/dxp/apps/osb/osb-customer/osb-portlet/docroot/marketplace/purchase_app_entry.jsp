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

<%@ include file="/marketplace/init.jsp" %>

<%
String purchaseStep = ParamUtil.getString(request, "purchaseStep");

long ecDocumentEntryId = ParamUtil.getLong(request, "ecDocumentEntryId");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryServiceUtil.getAppEntry(appEntryId);

boolean trial = ParamUtil.getBoolean(request, "trial");

MarketplaceUtil.addPortletBreadcrumbEntries(AppEntry.class.getName(), appEntryId, request, renderResponse);
%>

<div class="marketplace purchase-app-entry">

	<%
	ECDocumentEntry ecDocumentEntry = null;

	if (ecDocumentEntryId > 0) {
		ecDocumentEntry = ECDocumentEntryLocalServiceUtil.getECDocumentEntry(ecDocumentEntryId);

		if (ecDocumentEntry.getShippingAddressCountryId() != storeCountryId) {
			ecDocumentEntry = null;
		}
	}

	if (ecDocumentEntry == null) {
		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		params.put("storeName", ECommerceConstants.STORE_NAME_MARKETPLACE);
		params.put("type", ECDocumentEntryConstants.TYPE_SALES_ORDER);
		params.put("userId", themeDisplay.getUserId());

		List<ECDocumentEntry> ecDocumentEntries = ECDocumentEntryLocalServiceUtil.getECDocumentEntries(OSBConstants.GROUP_GUEST_ID, params, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (ECDocumentEntry curECDocumentEntry : ecDocumentEntries) {
			if (curECDocumentEntry.getShippingAddressCountryId() != storeCountryId) {
				continue;
			}

			ECDocumentEntryExtraSettings curECDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings(curECDocumentEntry);

			if (appEntryId == curECDocumentEntryExtraSettings.getAppEntryId()) {
				ecDocumentEntry = curECDocumentEntry;

				break;
			}
		}
	}

	ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings = new ECDocumentEntryExtraSettings();

	if (ecDocumentEntry != null) {
		ecDocumentEntryId = ecDocumentEntry.getEcDocumentEntryId();

		ecDocumentEntryExtraSettings.setExtraSettingsProperties(ecDocumentEntry.getExtraSettingsProperties());
	}
	else {
		ecDocumentEntryId = 0;
	}

	if (trial || appEntry.isFree() || (ecDocumentEntryId <= 0)) {
		purchaseStep = "project";
	}
	else if ((ecDocumentEntryId > 0) && Validator.isNull(purchaseStep)) {
		if (ecDocumentEntryExtraSettings.getAddressId() > 0) {
			purchaseStep = "summary";
		}
		else if (ECDocumentItemLocalServiceUtil.getECDocumentItemsCount(ecDocumentEntryId) > 0) {
			purchaseStep = "destination";
		}
		else if (Validator.isNotNull(ecDocumentEntryExtraSettings.getOwnerClassName())) {
			purchaseStep = "license";
		}
	}
	%>

	<liferay-util:include page="/marketplace/breadcrumb.jsp" servletContext="<%= application %>" />

	<div class="purchase-heading">
		<h1 class="purchase-title">
			<liferay-ui:message key="purchase" />
		</h1>

		<c:if test="<%= !appEntry.isFree() && !trial %>">
			<liferay-util:include page="/marketplace/purchase_app_entry/breadcrumb.jsp" servletContext="<%= application %>">
				<liferay-util:param name="purchaseStep" value="<%= purchaseStep %>" />
				<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
			</liferay-util:include>
		</c:if>

		<aui:layout>
			<aui:column columnWidth="<%= 30 %>" first="<%= true %>">
				<div class="asset">
					<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
						<liferay-util:param name="className" value="<%= AppEntry.class.getName() %>" />
						<liferay-util:param name="classPK" value="<%= String.valueOf(appEntryId) %>" />
						<liferay-util:param name="displayStyle" value="5" />
						<liferay-util:param name="title" value="<%= appEntry.getTitle() %>" />
					</liferay-util:include>
				</div>
			</aui:column>

			<aui:column columnWidth="<%= 70 %>" last="<%= true %>">
				<c:if test="<%= !appEntry.isFree() %>">
					<ul class="license-terms">

						<%
						AppVersion appVersion = appEntry.getApprovedAppVersion();
						%>

						<c:if test="<%= AppFlagLocalServiceUtil.hasAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED) %>">
							<li>
								<liferay-ui:message key="final-version" />:

								<liferay-ui:message key="no-new-versions-will-be-available-for-this-app" />
							</li>
						</c:if>

						<li>
							<liferay-ui:message key="license-term" />:

							<c:choose>
								<c:when test="<%= trial %>">
									<liferay-ui:message key="trial" />
								</c:when>
								<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>">
									<liferay-ui:message key="perpetual" />
								</c:when>
								<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>">
									<liferay-ui:message key="non-perpetual-annual" />
								</c:when>
							</c:choose>
						</li>

						<c:if test="<%= !trial %>">
							<li>
								<liferay-ui:message key="support-and-updates" />:

								<c:choose>
									<c:when test="<%= appEntry.isSupported() %>">
										<c:choose>
											<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>">
												<liferay-ui:message key="annual-renewable-first-year-included-with-license" />
											</c:when>
											<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION %>">
												<liferay-ui:message key="included-with-license" />
											</c:when>
										</c:choose>
									</c:when>
									<c:otherwise>
										<liferay-ui:message key="updates-only" />
									</c:otherwise>
								</c:choose>
							</li>
						</c:if>

						<c:if test="<%= !appEntry.isDeveloperEntryLiferayInc() %>">
							<li>
								<liferay-ui:message key="this-is-a-third-party-app-please-contact-the-developer-for-any-support-related-requests" />
							</li>
						</c:if>
					</ul>
				</c:if>
			</aui:column>
		</aui:layout>
	</div>

	<div class="purchase-body">
		<c:choose>
			<c:when test="<%= !themeDisplay.isSignedIn() %>">
				<div class="portlet-msg-error">
					<a href="<%= themeDisplay.getURLSignIn() %>">
						<liferay-ui:message key="please-sign-in-to-continue" />
					</a>
				</div>
			</c:when>
			<c:when test="<%= !RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
				<div class="portlet-msg-error">
					<liferay-ui:message key="please-verify-the-email-address-associated-with-your-liferay-account" />

					<liferay-portlet:actionURL name="sendEmailAddressVerification" var="sendEmailAddressVerificationURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
					</liferay-portlet:actionURL>

					<a href="<%= sendEmailAddressVerificationURL %>">
						<liferay-ui:message key="send-verification-email" />
					</a>
				</div>
			</c:when>
			<c:when test="<%= !appEntry.hasAvailableCountry(storeCountryId) %>">
				<div class="portlet-msg-error">
					<a href="<%= themeDisplay.getURLSignIn() %>">
						<liferay-ui:message arguments="<%= storeCountry.getName() %>" key="this-app-is-not-available-for-sale-in-x" />
					</a>
				</div>
			</c:when>
			<c:when test="<%= appEntry.isSOEEPlugin() %>">
				<liferay-util:include page="/marketplace/purchase_so_ee.jsp" servletContext="<%= application %>" />
			</c:when>
			<c:when test='<%= purchaseStep.equals("coterm") %>'>
				<liferay-util:include page="/marketplace/purchase_app_entry/coterm.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="coterm" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:when test='<%= purchaseStep.equals("license") %>'>
				<liferay-util:include page="/marketplace/purchase_app_entry/license.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="license" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:when test='<%= purchaseStep.equals("destination") %>'>
				<liferay-util:include page="/marketplace/purchase_app_entry/destination.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="destination" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:when test='<%= purchaseStep.equals("summary") %>'>
				<liferay-util:include page="/marketplace/purchase_app_entry/summary.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="summary" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:when test='<%= purchaseStep.equals("end-user") %>'>
				<liferay-util:include page="/marketplace/purchase_app_entry/end_user.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="end-user" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:when>
			<c:otherwise>
				<liferay-util:include page="/marketplace/purchase_app_entry/project.jsp" servletContext="<%= application %>">
					<liferay-util:param name="purchaseStep" value="project" />
					<liferay-util:param name="ecDocumentEntryId" value="<%= String.valueOf(ecDocumentEntryId) %>" />
				</liferay-util:include>
			</c:otherwise>
		</c:choose>
	</div>
</div>