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
String tabs1 = ParamUtil.getString(request, "tabs1", "reviews");

String backURL = ParamUtil.getString(request, "backURL");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(appEntryId);

boolean preview = ParamUtil.getBoolean(request, "preview");

long appVersionId = ParamUtil.getLong(request, "appVersionId");

AppVersion appVersion = null;

if (preview && (appVersionId > 0)) {
	appVersion = AppVersionServiceUtil.getAppVersion(appVersionId);
}
else {
	appVersion = appEntry.getApprovedAppVersion();

	appVersionId = appVersion.getAppVersionId();
}

DeveloperEntry developerEntry = appEntry.getDeveloperEntry();

boolean displayReviews = true;

if (appEntry.isLiferayEEPlugin()) {
	tabs1 = "history";

	displayReviews = false;
}

if (!preview) {
	int domain = AssetAuditImpl.getDomain(themeDisplay.getPortalURL());

	AssetAuditLocalServiceUtil.addAssetAudit(user.getUserId(), StringPool.BLANK, AppEntry.class.getName(), appEntryId, AssetAuditConstants.TYPE_VIEW, domain, StringPool.BLANK, 0);
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
portletURL.setParameter("backURL", backURL);
portletURL.setParameter("appEntryId", String.valueOf(appEntryId));

PortalUtil.addPageTitle(appVersion.getTitle(), request);

MarketplaceUtil.addPortletBreadcrumbEntries(AppEntry.class.getName(), appEntryId, request, renderResponse);
%>

<div class="marketplace view-app-entry">
	<c:if test="<%= themeDisplay.isSignedIn() && !preview && !RoleLocalServiceUtil.hasUserRole(themeDisplay.getUserId(), OSBConstants.ROLE_VERIFIED_USER_ID) %>">
		<liferay-util:include page="/marketplace/user_verification.jsp" servletContext="<%= application %>" />
	</c:if>

	<c:if test="<%= !preview %>">
		<liferay-util:include page="/marketplace/breadcrumb.jsp" servletContext="<%= application %>">
			<liferay-util:param name="backURL" value="<%= backURL %>" />
		</liferay-util:include>
	</c:if>

	<div class="app-heading">
		<div class="icon">
			<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveIcon" var="iconURL">
				<portlet:param name="assetAttachmentId" value="<%= String.valueOf(appVersion.getIconImageId()) %>" />
			</liferay-portlet:resourceURL>

			<img src="<%= iconURL %>" />
		</div>

		<div class="title">
			<h1>
				<%= HtmlUtil.escape(appVersion.getTitle()) %>
			</h1>

			<div class="owner">
				<c:choose>
					<c:when test="<%= Validator.isNull(developerEntry.getProfileURL(themeDisplay)) %>">
						<%= HtmlUtil.escape(developerEntry.getName()) %>
					</c:when>
					<c:when test="<%= MarketplaceUtil.isMarketplaceServer(request) %>">
						<a href="<%= developerEntry.getProfileURL(themeDisplay) %>" target="_blank"><%= HtmlUtil.escape(developerEntry.getName()) %></a>
					</c:when>
					<c:otherwise>
						<a href="<%= developerEntry.getProfileURL(themeDisplay) %>"><%= HtmlUtil.escape(developerEntry.getName()) %></a>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>

	<div class="app-body">
		<div class="side-bar">
			<c:if test="<%= !preview %>">
				<div class="purchase">
					<c:choose>
						<c:when test="<%= appEntry.isApproved() && !appEntry.isPortalRequired() %>">

							<%
							PortletURL purchaseURLImpl = renderResponse.createRenderURL();

							purchaseURLImpl.setParameter(mvcPathParam, "/marketplace/purchase_app_entry.jsp");
							purchaseURLImpl.setParameter("appEntryId", String.valueOf(appEntryId));

							String purchaseURL = purchaseURLImpl.toString();

							purchaseURLImpl.setParameter("trial", String.valueOf(true));

							String trialURL = purchaseURLImpl.toString();

							if (!themeDisplay.isSignedIn()) {
								purchaseURL = HttpUtil.addParameter(themeDisplay.getURLSignIn(), "redirect", purchaseURL);
								trialURL = HttpUtil.addParameter(themeDisplay.getURLSignIn(), "redirect", trialURL);
							}
							%>

							<c:choose>
								<c:when test="<%= Validator.isNotNull(appEntry.getOrderURL()) %>">
									<a class="btn" href="<%= appEntry.getOrderURL() %>" target='<%= MarketplaceUtil.isMarketplaceServer(request) ? "_blank" : StringPool.BLANK %>'><liferay-ui:message key="order-2" /></a>

									<c:if test="<%= appVersion.hasTrialLicense() %>">
										<a class="btn" href="<%= trialURL %>"><liferay-ui:message key="30-day-trial" /></a>
									</c:if>
								</c:when>
								<c:when test="<%= appEntry.isSOEEPlugin() %>">
									<a class="btn" href="<%= purchaseURL %>"><liferay-ui:message key="order-2" /></a>
								</c:when>
								<c:when test="<%= appEntry.isLiferayEEPlugin() && !OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.PURCHASE_APP) %>">
									<c:choose>
										<c:when test="<%= themeDisplay.isSignedIn() %>">
											<span class="btn disabled"><liferay-ui:message key="ee-only" /></span>
										</c:when>
										<c:otherwise>
											<span class="btn disabled" href="<%= themeDisplay.getURLSignIn() %>"><liferay-ui:message key="ee-only" /></span>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="<%= appEntry.isFree() %>">
									<a class="btn" href="<%= purchaseURL %>"><liferay-ui:message key="free" /></a>
								</c:when>
								<c:when test="<%= appEntry.hasAvailableCountry(storeCountryId) %>">

									<%
									if (MarketplaceUtil.isMarketplaceServer(request)) {
										purchaseURL = MarketplaceUtil.getExternalPurchaseURL(company.getVirtualHostname(), appEntryId);
									}
									%>

									<a class="btn" href="<%= purchaseURL %>" id="<portlet:namespace />buy-button" target="<%= MarketplaceUtil.isMarketplaceServer(request) ? "_blank" : StringPool.BLANK %>"><liferay-ui:message key="buy" /></a>

									<c:if test="<%= appVersion.hasTrialLicense() %>">
										<a class="btn" href="<%= trialURL %>" id="<portlet:namespace />trial-button"><liferay-ui:message key="30-day-trial" /></a>
									</c:if>
								</c:when>
								<c:otherwise>
									<span class="btn disabled"><liferay-ui:message key="not-available" /></span>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<span class="btn disabled"><liferay-ui:message key="not-available" /></span>
						</c:otherwise>
					</c:choose>
				</div>
			</c:if>

			<ul class="app-meta-data">

				<%
				JournalArticle journalArticle = null;

				try {
					journalArticle = JournalArticleLocalServiceUtil.getArticle(OSBConstants.GROUP_GUEST_ID, _GET_LIFERAY_JOURNAL_ARTICLE_ID);
				}
				catch (Exception e) {
				}
				%>

				<c:if test="<%= journalArticle != null %>">
					<li>
						<div class="new-customer-instructions">
							<liferay-portlet:resourceURL id="viewJournalArticleContent" var="viewJournalArticleContentURL">
								<portlet:param name="groupId" value="<%= String.valueOf(OSBConstants.GROUP_GUEST_ID) %>" />
								<portlet:param name="articleId" value="<%= _GET_LIFERAY_JOURNAL_ARTICLE_ID %>" />
							</liferay-portlet:resourceURL>

							<a href="<%= viewJournalArticleContentURL %>"><liferay-ui:message key="new-customer-get-liferay" /></a>
						</div>
					</li>

					<aui:script use="aui-dialog,aui-io">
						A.one('.new-customer-instructions a').on(
							'click',
							function(event) {
								event.preventDefault();

								var width = 600;

								var dialog = new A.Dialog(
									{
										destroyOnClose: true,
										draggable: false,
										modal: true,
										resizable: false,
										title: '<liferay-ui:message key="new-customer-get-liferay" unicode="<%= true %>" />',
										width: width,
										x: document.documentElement.clientWidth/2 - width/2,
										y: 300
									}
								).plug(
									A.Plugin.IO,
									{
										uri: event.currentTarget.attr('href')
									}
								).render();
							}
						);
					</aui:script>
				</c:if>

				<li>
					<liferay-ui:message key="latest-version" />: <%= HtmlUtil.escape(appVersion.getVersion()) %>
				</li>
				<li>
					<liferay-ui:message key="total-downloads" />: <%= appEntry.getDownloadCount() %>
				</li>
			</ul>

			<ul class="app-links">
				<c:if test="<%= Validator.isNotNull(appVersion.getWebsite()) %>">
					<li>
						<div class="developer-website">
							<a href="<%= HtmlUtil.escapeAttribute(appVersion.getWebsite()) %>" target="_blank">
								<liferay-ui:message key="developer-website" />
							</a>
						</div>
					</li>
				</c:if>

				<c:if test="<%= Validator.isNotNull(appVersion.getDemoWebsite()) %>">
					<li>
						<div class="demo-website">
							<a href="<%= HtmlUtil.escapeAttribute(appVersion.getDemoWebsite()) %>" target="_blank">
								<liferay-ui:message key="demo-website" />
							</a>
						</div>
					</li>
				</c:if>

				<c:if test="<%= Validator.isNotNull(appVersion.getDocumentationWebsite()) %>">
					<li>
						<div class="documentation-website">
							<a href="<%= HtmlUtil.escapeAttribute(appVersion.getDocumentationWebsite()) %>" target="_blank">
								<liferay-ui:message key="documentation" />
							</a>
						</div>
					</li>
				</c:if>

				<c:if test="<%= Validator.isNotNull(appVersion.getReferenceWebsite()) %>">
					<li>
						<div class="reference-website">
							<a href="<%= HtmlUtil.escapeAttribute(appVersion.getReferenceWebsite()) %>" target="_blank">
								<liferay-ui:message key="api-reference" />
							</a>
						</div>
					</li>
				</c:if>

				<c:if test="<%= Validator.isNotNull(appVersion.getSupportWebsite()) %>">
					<li>
						<div class="support">
							<a href="<%= HtmlUtil.escape(appVersion.getSupportWebsite()) %>" target="_blank">
								<liferay-ui:message key="support" />
							</a>
						</div>
					</li>
				</c:if>

				<c:if test="<%= Validator.isNotNull(appVersion.getSourceCodeWebsite()) %>">
					<li>
						<div class="source-code">
							<a href="<%= HtmlUtil.escapeAttribute(appVersion.getSourceCodeWebsite()) %>" target="_blank">
								<liferay-ui:message key="source-code" />
							</a>
						</div>
					</li>
				</c:if>

				<li>
					<div class="license-agreement">
						<a href="javascript:;" id="<portlet:namespace />license-agreement"><liferay-ui:message key="license-agreement" /></a>
					</div>
				</li>

				<c:if test="<%= !preview %>">
					<c:if test="<%= themeDisplay.isSignedIn() %>">
						<li>
							<div class="report-abuse">
								<a href="javascript:;" id="<portlet:namespace />report-abuse"><liferay-ui:message key="report-abuse" /></a>
							</div>
						</li>
					</c:if>
				</c:if>
			</ul>

			<div class="side-bar-heading">
				<strong><liferay-ui:message key="current-requirements" /></strong>
			</div>

			<ul class="requirements">

				<%
				List<AppPackage> appPackages = AppPackageLocalServiceUtil.getAppPackages(appVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				List<String> versionNames = new ArrayList<String>();

				for (AppPackage appPackage : appPackages) {
					versionNames.add(PortalReleaseUtil.getVersionName(appPackage));
				%>

					<li>
						<%= PortalReleaseUtil.getVersionName(appPackage) %>
					</li>

				<%
				}
				%>

			</ul>

			<%
			List<String> pastVersionNames = new ArrayList<String>();

			List<AppVersion> appVersions = AppVersionLocalServiceUtil.getAppVersions(appEntryId, WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AppVersionVersionOrderComparator(true));

			for (AppVersion previousAppVersion : appVersions) {
				List<AppPackage> previousAppPackages = AppPackageLocalServiceUtil.getAppPackages(previousAppVersion.getAppVersionId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (AppPackage previousAppPackage : previousAppPackages) {
					String versionName = PortalReleaseUtil.getVersionName(previousAppPackage);

					if (!versionNames.contains(versionName) && !pastVersionNames.contains(versionName)) {
						pastVersionNames.add(versionName);
					}
				}
			}

			Collections.sort(pastVersionNames);
			%>

			<c:if test="<%= pastVersionNames.size() > 0 %>">
				<div class="side-bar-heading">
					<strong><liferay-ui:message key="past-versions-work-with" /></strong>
				</div>

				<ul class="requirements">

					<%
					for (String versionName : pastVersionNames) {
					%>

						<li>
							<%= versionName %>
						</li>

					<%
					}
					%>

				</ul>
			</c:if>

			<c:if test="<%= !appEntry.isFree() && appEntry.hasAvailableCountry(storeCountryId) %>">

				<%
				CountryAppPricing countryAppPricing = CountryAppPricingLocalServiceUtil.fetchCountryAppPricing(appVersion.getAppVersionId(), storeCountryId);

				AppPricing appPricing = null;

				if (countryAppPricing != null) {
					appPricing = countryAppPricing.getAppPricing();
				}

				List<AssetLicense> assetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				%>

				<c:if test="<%= !assetLicenses.isEmpty() %>">
					<div class="side-bar-heading">
						<strong><liferay-ui:message key="standard-licenses" /></strong>
					</div>

					<ul class="pricing">

						<%
						for (AssetLicense assetLicense : assetLicenses) {
							AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.getAppPricingItem(appVersion.getAppVersionId(), storeCountryId, assetLicense.getAssetLicenseId());
						%>

							<li>
								<span class="license"><liferay-ui:message arguments="<%= assetLicense.getLicenseTypeAllotment() %>" key='<%= (assetLicense.getLicenseTypeAllotment() > 1) ? "x-instance-units" : "x-instance-unit" %>' /></span>

								<span class="price"><%= appPricingItem.getFormattedPrice(locale) %></span>
							</li>

						<%
						}
						%>

						<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appEntry.isSupported() %>">
							<li>
								<span class="support"><liferay-ui:message key="subscription-services" /></span>

								<span class="price"><%= appPricing.getFormattedSupportPrice(AssetLicenseConstants.USAGE_TYPE_STANDARD, locale) %></span>
							</li>
						</c:if>
					</ul>
				</c:if>

				<%
				assetLicenses = AssetLicenseLocalServiceUtil.getAssetLicenses(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, appEntry.getLicenseType(), WorkflowConstants.STATUS_APPROVED, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
				%>

				<c:if test="<%= !assetLicenses.isEmpty() %>">
					<div class="side-bar-heading">
						<strong><liferay-ui:message key="developer-licenses" /></strong>
					</div>

					<ul class="pricing">

						<%
						for (AssetLicense assetLicense : assetLicenses) {
							AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.getAppPricingItem(appVersion.getAppVersionId(), storeCountryId, assetLicense.getAssetLicenseId());
						%>

							<li>
								<span class="license"><liferay-ui:message arguments="<%= assetLicense.getLicenseTypeAllotment() %>" key='<%= (assetLicense.getLicenseTypeAllotment() > 1) ? "x-instance-units" : "x-instance-unit" %>' /></span>

								<span class="price"><%= appPricingItem.getFormattedPrice(locale) %></span>
							</li>

						<%
						}
						%>

						<c:if test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL) && appEntry.isSupported() %>">
							<li>
								<span class="support"><liferay-ui:message key="subscription-services" /></span>

								<span class="price"><%= appPricing.getFormattedSupportPrice(AssetLicenseConstants.USAGE_TYPE_DEVELOPER, locale) %></span>
							</li>
						</c:if>
					</ul>
				</c:if>
			</c:if>

			<c:if test="<%= !appEntry.isFree() %>">
				<div class="side-bar-heading">
					<strong><liferay-ui:message key="license-term" /></strong>
				</div>

				<c:choose>
					<c:when test="<%= appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_PERPETUAL %>">
						<liferay-ui:message key="perpetual" />
					</c:when>
					<c:when test="<%= (appEntry.getLicenseLifetime() == AssetLicenseConstants.LIFETIME_SUBSCRIPTION) || appEntry.isSOEEPlugin() %>">
						<liferay-ui:message key="non-perpetual-annual" />
					</c:when>
				</c:choose>
			</c:if>
		</div>

		<div class="details">

			<%
			List<AssetAttachment> assetAttachments = AssetAttachmentLocalServiceUtil.getAssetAttachments(AppVersion.class.getName(), appVersionId, AssetAttachmentConstants.TYPE_MEDIA, QueryUtil.ALL_POS, QueryUtil.ALL_POS, new AssetAttachmentRankComparator());
			%>

			<c:if test="<%= !assetAttachments.isEmpty() %>">
				<div class="carousel-container">
					<div class="asset-carousel" id="<portlet:namespace />appCarousel">

						<%
						for (AssetAttachment assetAttachment : assetAttachments) {
						%>

							<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="serveMedia" var="mediaURL">
								<portlet:param name="assetAttachmentId" value="<%= String.valueOf(assetAttachment.getAssetAttachmentId()) %>" />
							</liferay-portlet:resourceURL>

							<div class="aui-carousel-item">
								<a href="<%= mediaURL %>">
									<img alt="screenshot" src="<%= mediaURL %>" />
								</a>
							</div>

						<%
						}
						%>

					</div>

					<aui:script use="aui-base,aui-carousel,aui-debounce,aui-image-viewer-base,event-touch">
						var imageViewer = new A.ImageViewer(
							{
								caption: '<%= HtmlUtil.escapeJS(appEntry.getTitle()) %>',
								captionFromTitle: true,
								cssClass: 'marketplace view-app-entry-image-viewer',
								links: '#<portlet:namespace />appCarousel a',
								maxWidth: 960,
								preloadAllImages: true,
								showInfo: true
							}
						);

						imageViewer.after(
							'render',
							function() {
								A.all('.aui-image-viewer-arrow').addClass('marketplace');
							}
						);

						imageViewer.render();

						new A.Carousel(
							{
								activeIndex: 0,
								contentBox: '#<portlet:namespace />appCarousel',
								height: <%= preview ? 322 : 475 %>,
								intervalTime: 6
							}
						).render();

						imageViewer.on(
							'touchstart',
							function(event) {
								var touchNode = event.currentTarget;

								var getTouchPositionX = function(event) {
									return event.domEvent.targetTouches[0].pageX;
								};

								var scrollImage = function(relativePositionX) {
									if (relativePositionX > 0) {
										imageViewer.next();
									}
									else {
										imageViewer.prev();
									}

									touchNode.detach('touchend');
									touchNode.detach('touchmove');
								};

								var initialPositionX = getTouchPositionX(event);
								var currentPositionX = initialPositionX;

								touchNode.on(
									'touchmove',
									function(event) {
										currentPositionX = getTouchPositionX(event);
									}
								);

								touchNode.on(
									'touchend',
									function(event) {
										scrollImage(initialPositionX - currentPositionX);
									}
								);
							}
						);
					</aui:script>
				</div>
			</c:if>

			<liferay-util:buffer var="html">
				<c:if test="<%= AppFlagLocalServiceUtil.hasAppFlag(appVersion.getAppVersionId(), AppFlagConstants.TYPE_DEPRECATED) %>">
					<li class="info-item deprecated">
						<strong><liferay-ui:message key="final-version" />:</strong>

						<liferay-ui:message key="no-new-versions-will-be-available-for-this-app" />
					</li>
				</c:if>

				<c:if test="<%= appVersion.isPaclEnabled() %>">
					<li class="info-item security">
						<strong><liferay-ui:message key="security-enabled" />:</strong>

						<liferay-ui:message arguments='<%= "/documentation/liferay-portal/6.1/development/-/ai/lp-6-1-dgen11-plugin-security-management-0" %>' key="this-app-uses-liferay's-pacl-security-manager" />
					</li>
				</c:if>

				<c:if test="<%= !appEntry.isFree() %>">
					<c:choose>
						<c:when test="<%= appVersion.isSupported() %>">
							<li class="info-item support">
								<strong><liferay-ui:message key="subscription-services" />:</strong> <liferay-ui:message key="this-app-offers-support-maintenance-and-updates" />
							</li>
						</c:when>
						<c:otherwise>
							<li class="info-item support">
								<strong><liferay-ui:message key="subscription-services" />:</strong> <liferay-ui:message key="updates-only" />.
							</li>
						</c:otherwise>
					</c:choose>
				</c:if>

				<c:if test="<%= appEntry.isLiferayEEPlugin() %>">
					<li class="ee-only info-item">
						<strong><liferay-ui:message key="enterprise-subscribers-only" />:</strong>

						<liferay-ui:message arguments='<%= "https://www.liferay.com/products/liferay-portal/ee/overview" %>' key="find-out-how-to-get-a-subscription" />
					</li>
				</c:if>

				<c:if test="<%= appVersion.isLabs() %>">
					<li class="info-item labs">
						<strong><liferay-ui:message key="labs" />:</strong>

						<liferay-ui:message key="this-app-is-experimental-and-not-supported-by-the-developer" />
					</li>
				</c:if>

				<c:if test="<%= developerEntry.isUser() && OrganizationLocalServiceUtil.hasUserOrganization(developerEntry.getUserId(), OSBConstants.ORGANIZATION_LIFERAY_INC_ID) %>">
					<li class="employee info-item">
						<strong><liferay-ui:message key="disclaimer" />:</strong>

						<liferay-ui:message key="this-app-was-submitted-by-a-liferay-employee-but-is-in-no-way-affiliated-with-liferay,-inc.-or-its-affiliates" />
					</li>
				</c:if>
			</liferay-util:buffer>

			<c:if test="<%= Validator.isNotNull(html.trim()) %>">
				<ul class="app-disclaimers">
					<%= html %>
				</ul>
			</c:if>

			<div class="description">
				<%= MarketplaceMarkupUtil.getHTML(appVersion.getDescription(themeDisplay.getLanguageId())) %>
			</div>

			<%
			String changeLog = appVersion.getChangeLog(themeDisplay.getLanguageId());
			%>

			<c:if test="<%= Validator.isNotNull(changeLog) %>">
				<div class="changelog">
					<liferay-ui:panel-container persistState="<%= true %>">
						<liferay-ui:panel collapsible="<%= true %>" persistState="<%= true %>" title="latest-changes">
							<c:choose>
								<c:when test="<%= !PortletPropsValues.DEVELOPER_MODE_ENABLED && developerEntry.isLiferayInc() %>">
									<ul>

										<%
										String[] jiraIssueKeys = StringUtil.split(changeLog.replace(StringPool.NEW_LINE, StringPool.SPACE), StringPool.SPACE);

										List<JIRAIssue> jiraIssues = JIRAIssueLocalServiceUtil.getJIRAIssues(jiraIssueKeys);

										for (int i = 0; i < jiraIssues.size(); i++) {
											JIRAIssue jiraIssue = jiraIssues.get(i);
										%>

											<li class="jira-issue <%= (i >= 25) ? "aui-helper-hidden" : StringPool.BLANK %>">
												<a href="<%= PortletPropsValues.JIRA_BROWSE_URL + jiraIssue.getKey() %>" target="_blank"><%= jiraIssue.getKey() %></a> <%= HtmlUtil.escape(MarketplaceUtil.shortenString(jiraIssue.getSummary(), 110)) %>
											</li>

										<%
										}
										%>

									</ul>

									<c:if test="<%= jiraIssues.size() > 25 %>">
										<a href="javascript:;" id="<portlet:namespace />show-more"><liferay-ui:message key="show-more" /></a>
									</c:if>
								</c:when>
								<c:otherwise>
									<%= MarketplaceMarkupUtil.getHTML(changeLog) %>
								</c:otherwise>
							</c:choose>
						</liferay-ui:panel>
					</liferay-ui:panel-container>
				</div>
			</c:if>

			<%
			String[] assetTagNames = AssetTagLocalServiceUtil.getTagNames(AppVersion.class.getName(), appVersion.getAppVersionId());
			%>

			<c:if test="<%= assetTagNames.length > 0 %>">
				<div class="taglib-asset-tags-summary">

					<%
					PortletURL marketplaceSearchURL = null;

					if (MarketplaceUtil.isMarketplaceServer(request)) {
						marketplaceSearchURL = renderResponse.createRenderURL();
					}
					else {
						long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

						marketplaceSearchURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);
					}

					marketplaceSearchURL.setParameter(mvcPathParam, "/marketplace/search.jsp");

					for (int i = 0; i < assetTagNames.length; i++) {
						String assetTagName = assetTagNames[i];

						String keywords = assetTagName.trim();

						if (keywords.matches(".+\\s.+")) {
							keywords = StringPool.QUOTE + keywords + StringPool.QUOTE;
						}

						marketplaceSearchURL.setParameter("keywords", MarketplaceUtil.ASSET_TAGS_KEYWORD + keywords);
					%>

						<a class="tag" href="<%= marketplaceSearchURL.toString() %>"><%= HtmlUtil.escape(assetTagName) %></a>

					<%
					}
					%>

				</div>
			</c:if>
		</div>
	</div>

	<c:if test="<%= !preview %>">
		<div class="app-footer">
			<nav class="tab-nav">
				<ul>
					<li data-target-node="reviews">
						<a>
							<liferay-ui:message key="reviews" />
						</a>
					</li>
					<li data-target-node="version-history">
						<a>
							<liferay-ui:message key="version-history" />
						</a>
					</li>
					<li data-target-node="access-control">
						<a>
							<liferay-ui:message key="access-control" />
						</a>
					</li>
					<c:if test="<%= !appEntry.isFree() %>">
						<li data-target-node="tco">
							<a>
								<liferay-ui:message key="tco" />
							</a>
						</li>
					</c:if>
					<li data-target-node="installation-instructions">
						<a>
							<liferay-ui:message key="installation-instructions" />
						</a>
					</li>
				</ul>
			</nav>

			<div class="app-footer-content"></div>
		</div>

		<%
		AssetRecommendationSet assetRecommendationSet = AssetRecommendationSetLocalServiceUtil.fetchAssetRecommendationSet(AppEntry.class.getName(), appEntryId);
		%>

		<c:if test="<%= assetRecommendationSet != null %>">

			<%
			List<AssetRecommendationEntry> assetRecommendationEntries = AssetRecommendationEntryServiceUtil.getAssetRecommendationEntries(assetRecommendationSet.getAssetRecommendationSetId(), AssetRecommendationEntryConstants.TYPE_VIEWED_ALSO_PURCHASED, 0, 4);
			%>

			<c:if test="<%= !assetRecommendationEntries.isEmpty() %>">
				<div class="callout-b-head">
					<div class="callout-content">
						<h2 class="title">
							<liferay-ui:message key="customers-who-viewed-this-also-bought" />
						</h2>
					</div>
				</div>

				<div class="callout-e pseudo-portlet-content">
					<div class="asset">

						<%
						for (AssetRecommendationEntry assetRecommendationEntry : assetRecommendationEntries) {
							AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(assetRecommendationEntry.getClassName(), assetRecommendationEntry.getClassPK());
						%>

							<liferay-util:include page="/marketplace/asset_entry_display.jsp" servletContext="<%= application %>">
								<liferay-util:param name="className" value="<%= PortalUtil.getClassName(assetEntry.getClassNameId()) %>" />
								<liferay-util:param name="classPK" value="<%= String.valueOf(assetEntry.getClassPK()) %>" />
								<liferay-util:param name="displayStyle" value="1" />
								<liferay-util:param name="title" value="<%= assetEntry.getTitle() %>" />
							</liferay-util:include>

						<%
						}
						%>

					</div>
				</div>
			</c:if>
		</c:if>
	</c:if>
</div>

<aui:script use="aui-base,aui-dialog">
	<c:if test="<%= !preview %>">
		A.one('.purchase').delegate(
			'click',
			function(event) {
				var trackingId = '<%= developerEntry.getGoogleAnalyticsKey() %>';

				if (trackingId == '') {
					return;
				}

				(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function() {
				(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
				m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
				})(window,document,'script','https://www.google-analytics.com/analytics.js','mpGA');

				mpGA('create', trackingId, 'auto', 'appPurchaseTracker');

				var button = event.currentTarget;

				if (button.get('id') == '<portlet:namespace />buy-button') {
					mpGA('appPurchaseTracker.send', 'event', 'buttons', 'click', '<liferay-ui:message key="buy-button" />');
				}
				else if (button.get('id') == '<portlet:namespace />trial-button') {
					mpGA('appPurchaseTracker.send', 'event', 'buttons', 'click', '<liferay-ui:message key="trial-button" />');
				}
			},
			'a[class=btn]'
		);
	</c:if>

	A.one('#<portlet:namespace />license-agreement').on(
		'click',
		function(event) {
			var width = 600;

			<%
			PortletURL viewContractEntryURL = renderResponse.createRenderURL();

			String portletId = portletDisplay.getId();

			if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_ADMIN)) {
				viewContractEntryURL.setParameter(mvcPathParam, "/marketplace_admin/view_contract_entry.jsp");
			}
			else if (portletId.equals(OSBPortletKeys.OSB_MARKETPLACE_DEVELOPER_APPS)) {
				viewContractEntryURL.setParameter(mvcPathParam, "/marketplace_developer_apps/view_contract_entry.jsp");
			}
			else {
				viewContractEntryURL.setParameter(mvcPathParam, "/marketplace/view_contract_entry.jsp");
			}

			ContractEntry contractEntry = appVersion.getContractEntry();

			viewContractEntryURL.setParameter("contractEntryId", String.valueOf(contractEntry.getContractEntryId()));

			viewContractEntryURL.setWindowState(LiferayWindowState.EXCLUSIVE);
			%>

			var dialog = new A.Dialog(
				{
					destroyOnClose: true,
					draggable: true,
					height: 500,
					modal: true,
					resizable: false,
					stack: true,
					title: '<liferay-ui:message key="end-user-license-agreement" unicode="<%= true %>" />',
					width: width,
					x: document.documentElement.clientWidth/2 - width/2,
					y: 100
				}
			).plug(
				A.Plugin.IO,
				{
					uri: '<%= viewContractEntryURL.toString() %>'
				}
			).render();
		}
	);

	<c:if test="<%= !preview && themeDisplay.isSignedIn() %>">
		A.one('#<portlet:namespace />report-abuse').on(
			'click',
			function() {
				var width = 435;

				var popup = new A.Dialog(
					{
						destroyOnClose: true,
						draggable: true,
						modal: true,
						resizable: false,
						stack: true,
						title: '<liferay-ui:message key="report-abuse" />',
						width: width,
						x: document.documentElement.clientWidth/2 - width/2,
						y: 300
					}
				).plug(
					A.Plugin.IO,
					{
						data: {
							<portlet:namespace />className: '<%= AppEntry.class.getName() %>',
							<portlet:namespace />classPK: '<%= appEntry.getAppEntryId() %>',
							<portlet:namespace />contentTitle: '<%= HtmlUtil.escapeJS(appEntry.getTitle()) %>',
							<portlet:namespace />contentURL: '<%= HtmlUtil.escapeJS(PortalUtil.getPortalURL(request) + PortalUtil.getCurrentURL(request)) %>',
							<portlet:namespace />reportedUserId: '<%= appEntry.getUserId() %>'
						},
						uri: '<liferay-portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="<%= mvcPathParam %>" value="/marketplace/report_abuse.jsp" /></liferay-portlet:renderURL>'
					}
				).render();
			}
		);
	</c:if>

	<c:if test="<%= developerEntry.isLiferayInc() && Validator.isNotNull(appEntry.getChangeLog()) %>">
		var showMore = A.one('#<portlet:namespace />show-more');

		if (showMore) {
			showMore.on(
				'click',
				function(event) {
					var changelogContainer = A.one('.changelog');

					changelogContainer.all('.jira-issue').removeClass('aui-helper-hidden');

					showMore.hide();
				}
			);
		}
	</c:if>

	<c:if test="<%= !preview %>">
		var appFooter = A.one('.app-footer');

		var appFooterContent = appFooter.one('.app-footer-content');

		<%
		request.setAttribute("view_app_entry.jsp-appEntry", appEntry);
		%>

		var appFooterURL = {
			'reviews': '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/app_entry_reviews.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>',
			'version-history': '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/app_entry_version_history.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /><portlet:param name="appVersionId" value="<%= String.valueOf(appVersionId) %>" /></portlet:renderURL>',
			'access-control': '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/app_entry_security_manager.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>',

			<c:if test="<%= !appEntry.isFree() %>">
				'tco': '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/app_entry_tco.jsp" /><portlet:param name="appEntryId" value="<%= String.valueOf(appEntryId) %>" /></portlet:renderURL>',
			</c:if>

			'installation-instructions': '<portlet:renderURL windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>"><portlet:param name="mvcPath" value="/marketplace/installation_instructions.jsp" /></portlet:renderURL>'
		};

		var setAppFooterContent = function(target) {
			appFooter.all('.tab-nav [data-target-node]').each(
				function(node) {
					if (node.getAttribute('data-target-node') == target) {
						node.addClass('selected');
					}
					else {
						node.removeClass('selected');
					}
				}
			);

			A.io.request(
				appFooterURL[target],
				{
					method: 'get',
					dataType: 'text/html',
					on: {
						start: function(event, id) {
							appFooterContent.empty();

							appFooterContent.addClass('loading-animation');
						},

						success: function(event, id, obj) {
							var content = this.get('responseData');

							appFooterContent.removeClass('loading-animation');

							A.DOM.addHTML(appFooterContent, content, null);

							appFooterContent.all('script').each(
								function(node) {
									eval(node.getContent());
								}
							);
						}
					}
				}
			);
		};

		appFooter.delegate(
			'click',
			function(event) {
				if (!appFooterContent.hasClass('loading-animation')) {
					var target = event.currentTarget.getAttribute('data-target-node');

					setAppFooterContent(target);
				}
			},
			'.tab-nav li'
		);

		var firstTabNavigationItem = appFooter.one('.tab-nav li');

		setAppFooterContent(firstTabNavigationItem.getAttribute('data-target-node'));
	</c:if>
</aui:script>

<%!
private static final String _GET_LIFERAY_JOURNAL_ARTICLE_ID = "45767664";
%>