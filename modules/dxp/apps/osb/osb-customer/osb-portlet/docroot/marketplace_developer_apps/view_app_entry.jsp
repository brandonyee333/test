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

<%@ include file="/marketplace_developer_apps/init.jsp" %>

<%
String tabs1 = ParamUtil.getString(request, "tabs1", "app-preview");

String redirect = ParamUtil.getString(request, "redirect");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

AppVersion actionableAppVersion = appEntry.getActionableAppVersion();
%>

<div class="marketplace-developer-apps view-app-entry">
	<div class="heading">
		<liferay-ui:header
			title="apps"
		/>

		<liferay-portlet:renderURL var="addAppEntryURL">
			<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
		</liferay-portlet:renderURL>

		<c:if test="<%= developerEntry.getStatus() == WorkflowConstants.STATUS_APPROVED %>">
			<a class="btn" href="<%= addAppEntryURL %>"><liferay-ui:message key="add-new-app" /></a>
		</c:if>

		<liferay-portlet:renderURL var="viewURL">
			<portlet:param name="mvcPath" value="/marketplace_developer_apps/view.jsp" />
		</liferay-portlet:renderURL>

		<a class="btn" href="<%= viewURL %>"><liferay-ui:message key="back-to-all-apps" /></a>
	</div>

	<liferay-ui:error exception="<%= AppEntryStatusException.class %>" message="the-action-you-wish-to-perform-is-no-longer-available" />

	<c:choose>
		<c:when test="<%= OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.MANAGE_APP) %>">
			<div class="my-application container">
				<%@ include file="/marketplace_developer_apps/app_entry_summary.jspf" %>

				<liferay-util:include page="/marketplace_developer_apps/tabs1.jsp" servletContext="<%= application %>" />

				<liferay-ui:error exception="<%= AppEntryVersionException.class %>" message="please-enter-a-valid-version.-it-may-only-contain-numbers-and-periods" />
				<liferay-ui:error exception="<%= AppPackagePluginBundleSymbolicNameException.class %>" message="some-packages-have-bundles-with-problems" />
				<liferay-ui:error exception="<%= AppPackagePluginPACLException.class %>" message="plugins-must-have-liferays-pacl-security-manager-enabled" />
				<liferay-ui:error exception="<%= AppPricingCountryException.class %>" message="please-update-your-pricing,-your-app-must-be-available-for-sale-in-a-country" />
				<liferay-ui:error exception="<%= AppPricingItemPriceException.class %>" message="please-enter-valid-prices-for-each-license-in-every-pricing-table" />
				<liferay-ui:error exception="<%= AppPricingPriceException.class %>" message="please-update-your-pricing,-license-and-support-prices-cannot-be-free" />
				<liferay-ui:error exception="<%= NoSuchCurrencyEntryException.class %>" message="please-update-your-pricing,-all-pricing-tables-must-specify-a-currency" />
				<liferay-ui:error exception="<%= RequiredLiferayDeploymentContextException.class %>" message="invalid-liferay-app,-missing-plugins-that-are-required-for-deployment" />
				<liferay-ui:error exception="<%= RequiredResourcesImporterException.class %>" message='<%= LanguageUtil.format(pageContext, "requirements-for-stand-alone-themes-were-not-met", "/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen10-developing-and-publishing-apps-0") %>' />
				<liferay-ui:error exception="<%= RestrictedLiferayDeploymentContextException.class %>" message="invalid-liferay-app,-please-check-liferay-releng-properties" />

				<c:choose>
					<c:when test='<%= tabs1.equals("app-pricing") %>'>

						<%
						List<AppPricing> appPricings = AppPricingLocalServiceUtil.getAppPricings(actionableAppVersion.getAppVersionId());

						for (AppPricing appPricing : appPricings) {
						%>

							<div class="pricing-display">
								<div class="pricing-display-heading">
									<%= appPricing.getName() %>
								</div>

								<div class="pricing-display-body">
									<div class="prices">
										<div class="column details">
											<span class="aui-field-label">
												<liferay-ui:message key="licenses" />
											</span>
										</div>

										<div class="column standard">
											<span class="aui-field-label">
												<liferay-ui:message key="standard" />
											</span>

											<%
											List<AssetLicense> standardAssetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), actionableAppVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

											for (AssetLicense assetLicense : standardAssetLicenses) {
												AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.fetchAppPricingItem(appPricing.getAppPricingId(), assetLicense.getAssetLicenseId());
											%>

												<div class="license-type-allotment">
													<%= (assetLicense.getLicenseTypeAllotment() == AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED) ? "&infin;" : assetLicense.getLicenseTypeAllotment() %>
												</div>

												<div class="price">
													<%= appPricingItem.getFormattedPrice(locale) %>
												</div>

											<%
											}
											%>

										</div>

										<div class="column developer">
											<span class="aui-field-label">
												<liferay-ui:message key="developer" />
											</span>

											<%
											List<AssetLicense> developerAssetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), actionableAppVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

											for (AssetLicense assetLicense : developerAssetLicenses) {
												AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.fetchAppPricingItem(appPricing.getAppPricingId(), assetLicense.getAssetLicenseId());
											%>

												<div class="license-type-allotment">
													<%= (assetLicense.getLicenseTypeAllotment() == AssetLicenseConstants.LICENSE_TYPE_ALLOTMENT_UNLIMITED) ? "&infin;" : assetLicense.getLicenseTypeAllotment() %>
												</div>

												<div class="price">
													<%= appPricingItem.getFormattedPrice(locale) %>
												</div>

											<%
											}
											%>

										</div>
									</div>

									<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appEntry.isSupported() %>">
										<div class="support">
											<div class="column details">
												<span class="aui-field-label">
													<liferay-ui:message key="support" />
												</span>
											</div>

											<div class="column standard">
												<span class="aui-field-label">
													<liferay-ui:message key="standard" />
												</span>

												<div class="price">
													<%= appPricing.getFormattedSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD, locale) %>
												</div>
											</div>

											<div class="column developer">
												<span class="aui-field-label">
													<liferay-ui:message key="developer" />
												</span>

												<div class="price">
													<%= appPricing.getFormattedSupportPrice(AssetLicenseConstants.USAGE_TYPE_DEVELOPER, locale) %>
												</div>
											</div>
										</div>
									</c:if>

									<div>
										<div class="countries">
											<span class="aui-field-label">
												<liferay-ui:message key="countries" />
											</span>

											<ul class="countries">

											<%
											List<CountryAppPricing> countryAppPricings = CountryAppPricingLocalServiceUtil.getCountryAppPricings(appPricing.getAppPricingId());

											for (CountryAppPricing countryAppPricing : countryAppPricings) {
												Country country = CountryServiceUtil.getCountry(countryAppPricing.getCountryId());
											%>

												<li class="country" data-countryId="<%= country.getCountryId() %>"><%= country.getName() %></li>

											<%
											}
											%>

											</ul>
										</div>
									</div>
								</div>
							</div>

						<%
						}
						%>

					</c:when>
					<c:when test='<%= tabs1.equals("developer") %>'>
						<div class="developers container">
							<p>
								<liferay-ui:message key="the-unlicensed-lpkg-is-for-testing-purposes-only" />
							</p>

							<%
							List<AppVersion> allAppVersions = AppVersionLocalServiceUtil.getAppVersions(appEntry.getAppEntryId(), new int[] {WorkflowConstants.STATUS_APPROVED, WorkflowConstants.STATUS_DRAFT, WorkflowConstants.STATUS_PENDING, WorkflowConstants.STATUS_PENDING_QA}, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppVersionVersionOrderComparator(false));
							%>

							<liferay-ui:search-container
								delta="<%= allAppVersions.size() %>"
								headerNames="version-number,unlicensed-lpkg"
							>
								<liferay-ui:search-container-results
									results="<%= allAppVersions %>"
									total="<%= allAppVersions.size() %>"
								/>

								<liferay-ui:search-container-row
									className="com.liferay.osb.model.AppVersion"
									escapedModel="<%= true %>"
									keyProperty="appVersionId"
									modelVar="appVersion"
								>
									<liferay-ui:search-container-column-text
										cssClass="valign-top"
										name="version-number"
										value="<%= appVersion.getVersion() %>"
									/>

									<liferay-ui:search-container-column-text
										cssClass="valign-top"
										name="unlicensed-lpkg"
									>

										<%
										List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

										for (AppPackage appPackage : appPackages) {
										%>

											<div>
												<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveUnlicensedApp" var="serveUnlicensedAppURL">
													<portlet:param name="appPackageId" value="<%= String.valueOf(appPackage.getAppPackageId()) %>" />
												</liferay-portlet:resourceURL>

												<a href="<%= serveUnlicensedAppURL %>"><%= PortalReleaseUtil.getVersionName(appPackage) %></a>
											</div>

										<%
										}
										%>

									</liferay-ui:search-container-column-text>
								</liferay-ui:search-container-row>

								<liferay-ui:search-iterator paginate="<%= false %>" />
							</liferay-ui:search-container>
						</div>
					</c:when>
					<c:otherwise>
						<div class="aui-helper-clearfix container preview">
							<liferay-util:include page="/marketplace/view_app_entry.jsp" servletContext="<%= application %>">
								<liferay-util:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" />
								<liferay-util:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" />
								<liferay-util:param name="preview" value="<%= Boolean.TRUE.toString() %>" />
							</liferay-util:include>
						</div>

						<c:choose>
							<c:when test="<%= actionableAppVersion.getStatus() != WorkflowConstants.STATUS_APPROVED %>">
								<div class="plugins container">
									<h3>
										<liferay-ui:message key="uploaded-plugin-files" />
									</h3>

									<liferay-ui:error exception="<%= AppPackagePluginNameException.class %>" message="please-remove-invalid-files-uploaded-in-any-of-the-package" />
									<liferay-ui:error exception="<%= RequiredAppPackageException.class %>" message="please-create-a-package-with-plugins" />
									<liferay-ui:error exception="<%= RequiredAppPackagePluginException.class %>" message="please-remove-empty-packages" />

									<%
									AppVersion approvedAppVersion = appEntry.getApprovedAppVersion();

									List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(actionableAppVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
									%>

									<c:choose>
										<c:when test="<%= appPackages.isEmpty() && actionableAppVersion.isNewRelease() %>">
											<div class="portlet-msg-alert">
												<liferay-ui:message key="no-compatible-liferay-versions-found" />
											</div>
										</c:when>
										<c:otherwise>
											<%@ include file="/marketplace_developer_apps/app_packages.jspf" %>
										</c:otherwise>
									</c:choose>
								</div>
							</c:when>
							<c:otherwise>
								<div class="versions container">
									<h3>
										<liferay-ui:message key="versions" />
									</h3>

									<%
									List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntry.getAppEntryId(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppVersionVersionOrderComparator(false));
									%>

									<liferay-ui:search-container
										delta="<%= appVersions.size() %>"
										headerNames="version-number,liferay-compatibility,date-created,downloads"
									>
										<liferay-ui:search-container-results
											results="<%= appVersions %>"
											total="<%= appVersions.size() %>"
										/>

										<liferay-ui:search-container-row
											className="com.liferay.osb.model.AppVersion"
											escapedModel="<%= true %>"
											keyProperty="appVersionId"
											modelVar="appVersion"
										>
											<liferay-ui:search-container-column-text
												name="version-number"
												value="<%= appVersion.getVersion() %>"
											/>

											<liferay-ui:search-container-column-text
												name="liferay-compatibility"
											>

												<%
												List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

												StringBundler sb = new StringBundler(appPackages.size() * 2);

												for (AppPackage appPackage : appPackages) {
													sb.append(PortalReleaseUtil.getVersionName(appPackage));

													sb.append(StringPool.COMMA_AND_SPACE);
												}

												if (!appPackages.isEmpty()) {
													sb.setIndex(sb.index() - 1);
												}
												%>

												<%= sb.toString() %>
											</liferay-ui:search-container-column-text>

											<liferay-ui:search-container-column-text
												name="date-created"
												value="<%= mediumDateFormatDate.format(appVersion.getCreateDate()) %>"
											/>

											<liferay-ui:search-container-column-text
												name="downloads"
												value="<%= String.valueOf(appVersion.getDownloadCount()) %>"
											/>
										</liferay-ui:search-container-row>

										<liferay-ui:search-iterator paginate="<%= false %>" />
									</liferay-ui:search-container>
								</div>
							</c:otherwise>
						</c:choose>

						<%
						String statusMessage = null;

						if ((appEntry.getStatus() == WorkflowConstants.STATUS_DENIED) || (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED)) {
							statusMessage = actionableAppVersion.getStatusMessage();
						}
						else if ((appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) && !appEntry.hasUnreleasedAppVersion() && !appEntry.isStatusByDeveloper()) {
							Object[] arguments = new Object[] {"<a href=\"" + themeDisplay.getPortalURL() + "/web/developer/marketplace/contact\" target=\"_blank\">", "</a>"};

							statusMessage = LanguageUtil.format(pageContext, "your-app-was-removed-from-the-marketplace-contact-us-for-more-information-or-submit-a-new-version-of-your-app", arguments);
						}
						%>

						<c:if test="<%= Validator.isNotNull(statusMessage) %>">
							<div class="status-message container">
								<h3>
									<liferay-ui:message key="reviewers-message" />
								</h3>

								<div class="portlet-msg-error">
									<liferay-ui:message key="there-was-an-issue-with-your-app" />
								</div>

								<c:choose>
									<c:when test="<%= appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED %>">
										<%= statusMessage %>
									</c:when>
									<c:otherwise>
										<%= MarketplaceMarkupUtil.getHTML(statusMessage) %>
									</c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="<%= ArrayUtil.contains(AppVersionConstants.STATUSES_USER_EDITABLE, actionableAppVersion.getStatus()) %>">
				<div class="status-message container">
					<aui:form method="post" name="fm">
						<aui:fieldset>
							<aui:input label="note-to-reviewer" name="statusMessage" type="textarea" />
						</aui:fieldset>
					</aui:form>
				</div>
			</c:if>

			<aui:button-row cssClass="container">
				<c:choose>
					<c:when test="<%= !appEntry.isFree() && developerEntry.isSubscriptionExpired() %>">

						<%
						long marketplaceRegistrationPlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION);
						%>

						<liferay-portlet:renderURL plid="<%= marketplaceRegistrationPlid %>" portletName="<%= OSBPortletKeys.OSB_MARKETPLACE_REGISTRATION %>" var="upgradeDeveloperEntryURL">
							<portlet:param name="mvcPath" value="/marketplace_registration/upgrade_developer_entry.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="developerEntryId" value="<%= String.valueOf(developerEntry.getDeveloperEntryId()) %>" />
						</liferay-portlet:renderURL>

						<aui:button cssClass="btn" onClick="<%= upgradeDeveloperEntryURL %>" value="renew-paid-app-developer-account" />

						<div class="fr">
							<a href="javascript:;" onClick="<portlet:namespace />removeAppEntry();"><liferay-ui:message key="remove-from-store" /></a>

							<aui:script>
								function <portlet:namespace />removeAppEntry() {
									if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-remove-this-app-from-the-marketplace") %>')) {
										submitForm(document.hrefFm, '<liferay-portlet:actionURL name="updateAppEntryStatus"><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" /><portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" /><portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_EXPIRED) %>" /></liferay-portlet:actionURL>');
									}
								}
							</aui:script>
						</div>
					</c:when>
					<c:when test="<%= appEntry.isApproved() && actionableAppVersion.isApproved() %>">
						<liferay-portlet:renderURL var="editAppEntryURL">
							<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
							<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="releaseType" value="<%= String.valueOf(AppVersionConstants.RELEASE_TYPE_METADATA) %>" />
						</liferay-portlet:renderURL>

						<aui:button cssClass="btn" onClick="<%= editAppEntryURL %>" value="edit-details" />

						<c:if test="<%= !appEntry.isFree() %>">
							<liferay-portlet:renderURL var="editAppLicenseURL">
								<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_license.jsp" />
								<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
								<portlet:param name="redirect" value="<%= currentURL %>" />
								<portlet:param name="releaseType" value="<%= String.valueOf(AppVersionConstants.RELEASE_TYPE_PRICING) %>" />
							</liferay-portlet:renderURL>

							<aui:button cssClass="btn" onClick="<%= editAppLicenseURL %>" value="update-pricing" />
						</c:if>

						<liferay-portlet:renderURL var="addAppVersionURL">
							<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
							<portlet:param name="releaseType" value="<%= String.valueOf(AppVersionConstants.RELEASE_TYPE_NEW_VERSION) %>" />
						</liferay-portlet:renderURL>

						<aui:button cssClass="btn" onClick="<%= addAppVersionURL %>" value="add-new-version" />

						<c:if test="<%= actionableAppVersion.getAppEntryId() != PortletPropsValues.MARKETPLACE_APP_ENTRY_ID %>">
							<div class="fr">
								<a href="javascript:;" onClick="<portlet:namespace />removeAppEntry();"><liferay-ui:message key="remove-from-store" /></a>

								<aui:script>
									function <portlet:namespace />removeAppEntry() {
										if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-remove-this-app-from-the-marketplace") %>')) {
											submitForm(document.hrefFm, '<liferay-portlet:actionURL name="updateAppEntryStatus"><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" /><portlet:param name="redirect" value="<%= currentURL %>" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" /><portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" /><portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_EXPIRED) %>" /></liferay-portlet:actionURL>');
										}
									}
								</aui:script>
							</div>
						</c:if>
					</c:when>
					<c:when test="<%= actionableAppVersion.isPending() %>">
						<liferay-portlet:actionURL name="updateAppEntryStatus" var="updateAppEntryStatusURL">
							<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
							<portlet:param name="redirect" value="<%= currentURL %>" />
							<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
							<portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" />
							<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_DRAFT) %>" />
						</liferay-portlet:actionURL>

						<aui:button cssClass="btn" onClick="<%= updateAppEntryStatusURL %>" value="cancel-submission" />
					</c:when>
					<c:when test="<%= (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DRAFT) || (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED) || (appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">
						<c:choose>
							<c:when test="<%= !appEntry.hasUnreleasedAppVersion() && (appEntry.getStatus() == WorkflowConstants.STATUS_EXPIRED) %>">
								<liferay-portlet:renderURL var="addAppVersionURL">
									<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
									<portlet:param name="releaseType" value="<%= String.valueOf(AppVersionConstants.RELEASE_TYPE_NEW_VERSION) %>" />
								</liferay-portlet:renderURL>

								<aui:button cssClass="btn" onClick="<%= addAppVersionURL %>" value="add-new-version" />
							</c:when>
							<c:when test="<%= actionableAppVersion.getReleaseType() == AppVersionConstants.RELEASE_TYPE_PRICING %>">
								<liferay-portlet:renderURL var="editAppLicenseURL">
									<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_license.jsp" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
								</liferay-portlet:renderURL>

								<aui:button cssClass="btn" onClick="<%= editAppLicenseURL %>" value="update-pricing" />

								<liferay-portlet:actionURL name="updateAppEntryStatus" var="updateAppEntryStatusURL">
									<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
									<portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" />
									<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_APPROVED) %>" />
								</liferay-portlet:actionURL>

								<aui:button cssClass="btn" onClick="<%= updateAppEntryStatusURL %>" value="publish-to-marketplace" />
							</c:when>
							<c:otherwise>
								<c:if test="<%= themeDisplay.isSignedIn() && !RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
									<div class="user-verification">
										<div class="aui-helper-hidden portlet-msg-error" id="<portlet:namespace />userVerificationError">
											<liferay-ui:message key="an-error-occurred-while-sending-the-verification-email-please-try-again-in-a-few-minutes" />
										</div>

										<div class="aui-helper-hidden portlet-msg-success" id="<portlet:namespace />userVerificationSuccess">
											<liferay-ui:message key="a-verification-email-was-sent" />
										</div>
									</div>

									<div class="user-verification-section portlet-msg-info">
										<p>
											<liferay-ui:message key="please-verify-the-email-address-associated-with-your-liferay-account-in-order-to-submit-the-app" />
										</p>

										<aui:button cssClass="btn" onClick='<%= renderResponse.getNamespace() + "sendEmailAddressVerification();" %>' value="send-verification-email" />
									</div>
								</c:if>

								<liferay-portlet:renderURL var="editVersionURL">
									<portlet:param name="mvcPath" value="/marketplace_developer_apps/edit_app_entry.jsp" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
								</liferay-portlet:renderURL>

								<aui:button cssClass="btn" onClick="<%= editVersionURL %>" value="edit" />

								<liferay-portlet:actionURL name="updateAppEntryStatus" var="updateAppEntryStatusURL">
									<portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" />
									<portlet:param name="redirect" value="<%= currentURL %>" />
									<portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" />
									<portlet:param name="appVersionId" value="<%= String.valueOf(actionableAppVersion.getAppVersionId()) %>" />
									<portlet:param name="status" value="<%= String.valueOf(WorkflowConstants.STATUS_PENDING) %>" />
								</liferay-portlet:actionURL>

								<aui:button cssClass="btn" disabled="<%= !RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>" onClick='<%= renderResponse.getNamespace() + "submitAppEntry();" %>' value="submit-for-review" />

								<aui:script>
									function <portlet:namespace />submitAppEntry() {
										submitForm(document.<portlet:namespace />fm, '<%= updateAppEntryStatusURL %>');
									}
								</aui:script>
							</c:otherwise>
						</c:choose>
					</c:when>
				</c:choose>

				<c:if test="<%= (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DRAFT) || (actionableAppVersion.getStatus() == WorkflowConstants.STATUS_DENIED) %>">
					<div class="fr">
						<a href="javascript:;" onClick="<portlet:namespace />discardAppVersion();"><liferay-ui:message key="discard-changes" /></a>

						<aui:script>
							function <portlet:namespace />discardAppVersion() {
								if (confirm('<%= UnicodeLanguageUtil.get(pageContext, "are-you-sure-you-want-to-discard-all-changes") %>')) {
									submitForm(document.hrefFm, '<liferay-portlet:actionURL name="discardAppVersion"><portlet:param name="mvcPath" value="/marketplace_developer_apps/view_app_entry.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntry.getAppEntryId()) %>" /></liferay-portlet:actionURL>');
								}
							}
						</aui:script>
					</div>
				</c:if>
			</aui:button-row>
		</c:when>
		<c:otherwise>
			<div class="portlet-msg-error">
				<liferay-ui:message key="you-do-not-have-the-required-permissions" />
			</div>
		</c:otherwise>
	</c:choose>
</div>

<aui:script>
	Liferay.provide(
		window,
		'<portlet:namespace />sendEmailAddressVerification',
		function() {
			var A = AUI();

			A.io.request(
				'<liferay-portlet:actionURL name="sendEmailAddressVerification" />',
				{
					on: {
						failure: function() {
							var errorMessageNode = A.one('#<portlet:namespace />userVerificationError');

							errorMessageNode.show();
						},
						success: function() {
							var successMessageNode = A.one('#<portlet:namespace />userVerificationSuccess');

							successMessageNode.show();

							var errorMessageNode = A.one('#<portlet:namespace />userVerificationError');

							errorMessageNode.hide();

							var errorMessageNode = A.one('.user-verification-section');

							errorMessageNode.hide();
						}
					}
				}
			);
		},
		['aui-io']
	);
</aui:script>