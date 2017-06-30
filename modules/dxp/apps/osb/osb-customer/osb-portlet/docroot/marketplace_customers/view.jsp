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
String keywords = ParamUtil.getString(request, "keywords");

long appEntryId = ParamUtil.getLong(request, "appEntryId");

DeveloperEntry developerEntry = DeveloperEntryLocalServiceUtil.getDeveloperEntryByGroupId(themeDisplay.getScopeGroupId());

PortletURL portletURL = renderResponse.createRenderURL();
%>

<div class="marketplace-customers view">
	<liferay-ui:header
		title="customers"
	/>

	<div class="search">
		<portlet:actionURL var="searchURL">
			<portlet:param name="mvcPath" value="/marketplace_customers/view.jsp" />
		</portlet:actionURL>

		<aui:form action="<%= searchURL %>" method="post" name="searchFm">
			<aui:input label="search" name="keywords" size="40" type="text" value="<%= keywords %>" />

			<aui:select label="app" name="appEntryId">
				<aui:option value="" />

				<%
				List<AppEntry> appEntries = AppEntryLocalServiceUtil.getAppEntries(developerEntry.getDeveloperEntryId(), WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

				for (AppEntry appEntry : appEntries) {
				%>

					<aui:option label="<%= appEntry.getTitle() %>" selected="<%= appEntry.getAppEntryId() == appEntryId %>" value="<%= appEntry.getAppEntryId() %>" />

				<%
				}
				%>

			</aui:select>

			<aui:button type="submit" value="search" />
		</aui:form>
	</div>

	<liferay-ui:search-container
		emptyResultsMessage="there-are-no-results"
		headerNames="app,type,instance,license-expiration,support-expiration"
		iteratorURL="<%= portletURL %>"
	>
		<liferay-ui:search-container-results>

			<%
			Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(AssetReceipt.class.getName());

			SearchContext searchContext = SearchContextFactory.getInstance(request);

			searchContext.setEnd(searchContainer.getEnd());
			searchContext.setKeywords(keywords);

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("developerEntryId", developerEntry.getDeveloperEntryId());
			params.put("free", false);

			if (appEntryId > 0) {
				params.put("productClassNameId", PortalUtil.getClassNameId(AppEntry.class));
				params.put("productClassPK", appEntryId);
			}

			searchContext.setAttribute("params", params);

			searchContext.setStart(searchContainer.getStart());

			Hits hits = indexer.search(searchContext);

			total = hits.getLength();

			for (int i = 0; i < hits.getDocs().length; i++) {
				Document doc = hits.doc(i);

				long classPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

				AssetReceipt assetReceipt = AssetReceiptLocalServiceUtil.getAssetReceipt(classPK);

				results.add(assetReceipt.toEscapedModel());
			}

			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
			%>

		</liferay-ui:search-container-results>

		<%
		List resultRows = searchContainer.getResultRows();

		long ownerClassNameId = 0;
		long ownerClassPK = 0;

		for (int i = 0; i < results.size(); i++) {
			AssetReceipt assetReceipt = (AssetReceipt)results.get(i);

			if ((ownerClassNameId != assetReceipt.getOwnerClassNameId()) || (ownerClassPK != assetReceipt.getOwnerClassPK())) {
				ownerClassNameId = assetReceipt.getOwnerClassNameId();
				ownerClassPK = assetReceipt.getOwnerClassPK();

				ResultRow resultRow = new ResultRow(assetReceipt, assetReceipt.getAssetReceiptId(), i);

				resultRow.setClassHoverName("owner-name");
				resultRow.setClassName("owner-name");

				String ownerName = assetReceipt.getOwnerName();

				if (Validator.isNotNull(assetReceipt.getLegalEntityName())) {
					ownerName += " - " + assetReceipt.getLegalEntityName();
				}

				PortletURL rowURL = renderResponse.createRenderURL();

				rowURL.setParameter("mvcPath", "/marketplace_customers/view_asset_receipts.jsp");
				rowURL.setParameter("backURL", currentURL);
				rowURL.setParameter("ownerClassNameId", String.valueOf(ownerClassNameId));
				rowURL.setParameter("ownerClassPK", String.valueOf(ownerClassPK));

				resultRow.addText("left", "top", 5, ownerName, rowURL);

				resultRows.add(resultRow);
			}

			AppEntry appEntry = AppEntryLocalServiceUtil.getAppEntry(assetReceipt.getProductClassPK());

			List<AssetReceiptLicense> assetReceiptLicenses = AssetReceiptLicenseLocalServiceUtil.getAssetReceiptLicenses(assetReceipt.getAssetReceiptId());

			for (AssetReceiptLicense assetReceiptLicense : assetReceiptLicenses) {
				ResultRow resultRow = new ResultRow(assetReceiptLicense, assetReceiptLicense.getAssetReceiptLicenseId(), i);

				// App

				resultRow.addText(appEntry.getTitle());

				// Type

				resultRow.addText(LanguageUtil.get(pageContext, assetReceiptLicense.getUsageTypeLabel()));

				// Instance

				resultRow.addText(String.valueOf(assetReceiptLicense.getLicenseTypeAllotment()));

				// License expiration

				if (assetReceiptLicense.getLicenseLifetime() != AssetLicenseConstants.LIFETIME_PERPETUAL) {
					resultRow.addText(mediumDateFormatDate.format(assetReceiptLicense.getEndDate()));
				}
				else {
					resultRow.addText(StringPool.DASH);
				}

				// Support expiration

				List<AssetReceiptSupport> assetReceiptSupports = AssetReceiptSupportLocalServiceUtil.getAssetReceiptSupports(assetReceipt.getAssetReceiptId(), AssetReceiptLicense.class.getName(), assetReceiptLicense.getAssetReceiptLicenseId(), 0, 1, new AssetReceiptSupportEndDateComparator());

				if (!assetReceiptSupports.isEmpty()) {
					AssetReceiptSupport assetReceiptSupport = assetReceiptSupports.get(0);

					resultRow.addText(mediumDateFormatDate.format(assetReceiptSupport.getEndDate()));
				}
				else {
					resultRow.addText(StringPool.DASH);
				}

				// Add result row

				resultRows.add(resultRow);
			}
		}
		%>

		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>