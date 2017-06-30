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
String assetEntryURL = ParamUtil.getString(request, "assetEntryURL");
String className = ParamUtil.getString(request, "className");
long classPK = ParamUtil.getLong(request, "classPK");
int displayStyle = ParamUtil.getInteger(request, "displayStyle");
boolean excludeAssetEntryLink = ParamUtil.getBoolean(request, "excludeAssetEntryLink");
String iconURL = ParamUtil.getString(request, "iconURL");
String ownerName = ParamUtil.getString(request, "ownerName");
String title = ParamUtil.getString(request, "title");
int titleLength = ParamUtil.getInteger(request, "titleLength", 15);

RatingsStats ratingsStats = RatingsStatsLocalServiceUtil.getStats(className, classPK);

String price = StringPool.BLANK;
boolean displayPrice = false;

boolean displayReviews = true;

int downloadCount = 0;

if (className.equals(AppEntry.class.getName())) {
	if (Validator.isNull(assetEntryURL)) {
		PortletURL portletURL = null;

		if (MarketplaceUtil.isMarketplaceServer(request)) {
			portletURL = renderResponse.createRenderURL();
		}
		else {
			long marketplacePlid = PortalUtil.getPlidFromPortletId(OSBConstants.GROUP_GUEST_ID, OSBPortletKeys.OSB_MARKETPLACE);

			portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_MARKETPLACE, marketplacePlid, PortletRequest.RENDER_PHASE);
		}

		portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");
		portletURL.setParameter("appEntryId", String.valueOf(classPK));

		assetEntryURL = portletURL.toString();
	}

	AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(classPK);

	ownerName = appEntry.getDeveloperEntryName();

	if (Validator.isNull(iconURL)) {
		long iconImageId = appEntry.getIconImageId();

		if (iconImageId > 0) {
			LiferayPortletURL liferayPortletURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

			liferayPortletURL.setCopyCurrentRenderParameters(false);
			liferayPortletURL.setParameter("assetAttachmentId", String.valueOf(iconImageId), false);
			liferayPortletURL.setResourceID("serveIcon");

			iconURL = liferayPortletURL.toString();
		}
		else {
			iconURL = themeDisplay.getPathThemeImages() + "/custom/icon-marketplace-placeholder-70x70.png";
		}
	}

	if (appEntry.isLiferayEEPlugin()) {
		displayReviews = false;
	}

	if (appEntry.isApproved()) {
		AppVersion appVersion = appEntry.getApprovedAppVersion();

		if (appEntry.isLiferayEEPlugin() && !OSBAppEntryPermission.contains(permissionChecker, appEntry, OSBActionKeys.PURCHASE_APP)) {
			price = "ee-only";
			displayPrice = true;
		}
		else if (!appEntry.isFree()) {
			AssetLicense assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_STANDARD, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);

			if (assetLicense == null) {
				assetLicense = AssetLicenseLocalServiceUtil.fetchAssetLicense(AppVersion.class.getName(), appVersion.getAppVersionId(), AssetLicenseConstants.USAGE_TYPE_DEVELOPER, AssetLicenseConstants.LICENSE_TYPE_PRODUCTION, 1, WorkflowConstants.STATUS_APPROVED);
			}

			AppPricingItem appPricingItem = AppPricingItemLocalServiceUtil.fetchAppPricingItem(appVersion.getAppVersionId(), storeCountryId, assetLicense.getAssetLicenseId());

			if (appPricingItem != null) {
				price = appPricingItem.getFormattedPrice(locale);
				displayPrice = true;
			}
		}
		else {
			displayPrice = true;
		}

		if ((displayStyle == 5) && !appEntry.isFirstSubmission()) {
			downloadCount = appVersion.getDownloadCount();
		}
	}
}
%>

<c:choose>
	<c:when test="<%= displayStyle == 1 %>">
		<div class="item">
			<div class="icon">
				<c:choose>
					<c:when test="<%= excludeAssetEntryLink %>">
						<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
					</c:when>
					<c:otherwise>
						<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>">
							<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>

			<ul class="info">
				<li class="title">
					<c:choose>
						<c:when test="<%= excludeAssetEntryLink %>">
							<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, 14)) %>
						</c:when>
						<c:otherwise>
							<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>" title="<%= title %>">
								<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, 14)) %>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<%= HtmlUtil.escape(MarketplaceUtil.shortenString(ownerName, 14)) %>
				</li>
				<li>
					<div class="aui-helper-clearfix" id="rating">
						<c:choose>
							<c:when test="<%= displayReviews %>">

								<%
								for (int i = 1; i <= 5; i++) {
								%>

									<span class="aui-rating-element <%= (i <= ratingsStats.getAverageScore()) ? "aui-rating-element-on" : StringPool.BLANK %>"></span>

								<%
								}
								%>

							</c:when>
							<c:otherwise>
								<span></span>
							</c:otherwise>
						</c:choose>
					</div>
				</li>

				<c:if test="<%= displayPrice %>">

					<%
					String purchaseURL = StringPool.BLANK;

					if (MarketplaceUtil.isMarketplaceServer(request) && Validator.isNotNull(price)) {
						purchaseURL = MarketplaceUtil.getExternalPurchaseURL(company.getVirtualHostname(), classPK);
					}
					else {
						PortletURL portletURL = renderResponse.createRenderURL();

						portletURL.setParameter(mvcPathParam, "/marketplace/purchase_app_entry.jsp");
						portletURL.setParameter("appEntryId", String.valueOf(classPK));

						purchaseURL = portletURL.toString();

						if (!themeDisplay.isSignedIn()) {
							purchaseURL = HttpUtil.addParameter(themeDisplay.getURLSignIn(), "redirect", purchaseURL);
						}
					}
					%>

					<li>
						<c:choose>
							<c:when test="<%= title.equals(PortletPropsValues.MARKETPLACE_SO_EE_APP_ENTRY_TITLE) %>">
								<a class="btn price" href="<%= HtmlUtil.escapeAttribute(purchaseURL) %>" target="<%= (Validator.isNotNull(price) && MarketplaceUtil.isMarketplaceServer(request)) ? "_blank" : StringPool.BLANK %>">
									<liferay-ui:message key="order-2" />
								</a>
							</c:when>
							<c:when test='<%= Validator.isNotNull(price) && price.equals("ee-only") %>'>
								<span class="btn price">
									<liferay-ui:message key="ee-only" />
								</span>
							</c:when>
							<c:when test="<%= Validator.isNotNull(price) %>">
								<a class="btn price" href="<%= HtmlUtil.escapeAttribute(purchaseURL) %>" target="<%= (Validator.isNotNull(price) && MarketplaceUtil.isMarketplaceServer(request)) ? "_blank" : StringPool.BLANK %>">
									<%= price %>
								</a>
							</c:when>
							<c:otherwise>
								<a class="btn price" href="<%= HtmlUtil.escapeAttribute(purchaseURL) %>" target="<%= (Validator.isNotNull(price) && MarketplaceUtil.isMarketplaceServer(request)) ? "_blank" : StringPool.BLANK %>">
									<liferay-ui:message key="free" />
								</a>
							</c:otherwise>
						</c:choose>
					</li>
				</c:if>
			</ul>
		</div>
	</c:when>
	<c:when test="<%= displayStyle == 2 %>">
		<div class="featured">
			<ul class="info">
				<li class="icon">
					<c:choose>
						<c:when test="<%= excludeAssetEntryLink %>">
							<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
						</c:when>
						<c:otherwise>
							<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>">
								<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
							</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li class="title">
					<c:choose>
						<c:when test="<%= excludeAssetEntryLink %>">
							<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, 17)) %>
						</c:when>
						<c:otherwise>
							<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>" title="<%= title %>">
								<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, 17)) %>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<%= HtmlUtil.escape(MarketplaceUtil.shortenString(ownerName, 17)) %>
				</li>
			</ul>
		</div>
	</c:when>
	<c:when test="<%= (displayStyle == 3) || (displayStyle == 4) %>">

		<%
		if (className.equals(JournalArticle.class.getName())) {
			JournalArticle journalArticle = JournalArticleLocalServiceUtil.getLatestArticle(classPK);

			String languageId = LanguageUtil.getLanguageId(request);

			JournalArticleDisplay journalArticleDisplay = JournalContentUtil.getDisplay(journalArticle.getGroupId(), journalArticle.getArticleId(), journalArticle.getTemplateId(), null, languageId, themeDisplay);

			request.setAttribute(WebKeys.JOURNAL_ARTICLE, journalArticle);
			request.setAttribute(WebKeys.JOURNAL_ARTICLE_DISPLAY, journalArticleDisplay);
		%>

			<liferay-util:buffer var="html">
				<liferay-util:include page="/html/portlet/journal_content/view.jsp" />
			</liferay-util:buffer>

			<%
			PortletURL portletURL = renderResponse.createRenderURL();

			portletURL.setParameter(mvcPathParam, "/marketplace/view_app_entry.jsp");

			Matcher matcher = _appEntryPattern.matcher(html);

			while (matcher.find()) {
				String appEntryId = matcher.group(1);

				portletURL.setParameter("appEntryId", appEntryId);

				html = matcher.replaceFirst(portletURL.toString());

				matcher.reset(html);
			}
			%>

			<div class="<%= (displayStyle == 3) ? "aui-carousel-item" : "ad" %>">
				<%= html %>
			</div>

		<%
		}
		%>

	</c:when>
	<c:when test="<%= displayStyle == 5 %>">
		<div class="item">
			<div class="icon">
				<c:choose>
					<c:when test="<%= excludeAssetEntryLink %>">
						<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
					</c:when>
					<c:otherwise>
						<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>">
							<img class="small" src="<%= HtmlUtil.escapeAttribute(iconURL) %>" />
						</a>
					</c:otherwise>
				</c:choose>
			</div>

			<ul class="info">
				<li class="title">
					<c:choose>
						<c:when test="<%= excludeAssetEntryLink %>">
							<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, titleLength)) %>
						</c:when>
						<c:otherwise>
							<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>" title="<%= title %>">
								<%= HtmlUtil.escape(MarketplaceUtil.shortenString(title, titleLength)) %>
							</a>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<%= HtmlUtil.escape(MarketplaceUtil.shortenString(ownerName, 16)) %>
				</li>
				<li>
					<div class="aui-helper-clearfix" id="rating">

						<%
						for (int i = 1; i <= 5; i++) {
						%>

							<span class="aui-rating-element <%= (i <= ratingsStats.getAverageScore()) ? "aui-rating-element-on" : StringPool.BLANK %>"></span>

						<%
						}
						%>

					</div>
				</li>
				<li>
					<c:choose>
						<c:when test="<%= className.equals(AppEntry.class.getName()) %>">
							<liferay-ui:message arguments="<%= downloadCount %>" key="x-downloads" />
						</c:when>
						<c:otherwise>
							<%= LanguageUtil.format(pageContext, (ratingsStats.getTotalEntries() == 1) ? "x-rating" : "x-ratings", ratingsStats.getTotalEntries()) %>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		</div>
	</c:when>
	<c:otherwise>
		<li>
			<span class="title">
				<a href="<%= HtmlUtil.escapeAttribute(assetEntryURL) %>"><%= HtmlUtil.escape(title) %></a>
			</span>

			<span class="info"><%= HtmlUtil.escape(MarketplaceUtil.shortenString(ownerName, 20)) %></span>
		</li>
	</c:otherwise>
</c:choose>

<%!
private Pattern _appEntryPattern = Pattern.compile("\\[\\$APPLICATIONENTRY_ID=([^\\$]+)\\$\\]");
%>