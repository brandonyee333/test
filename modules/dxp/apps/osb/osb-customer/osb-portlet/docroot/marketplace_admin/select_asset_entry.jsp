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

<%@ include file="/marketplace_admin/init.jsp" %>

<%
String keywords = ParamUtil.getString(request, "keywords");

boolean approved = ParamUtil.getBoolean(request, "approved", true);
long assetCategoryId = ParamUtil.getLong(request, "assetCategoryId");
int assetListType = ParamUtil.getInteger(request, "assetListType");
String className = ParamUtil.getString(request, "className");

String orderByCol = ParamUtil.getString(request, "orderByCol", "title");
String orderByType = ParamUtil.getString(request, "orderByType", "asc");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/marketplace_admin/select_asset_entry.jsp");
portletURL.setParameter("keywords", keywords);
portletURL.setParameter("approved", String.valueOf(approved));
portletURL.setParameter("assetCategoryId", String.valueOf(assetCategoryId));
portletURL.setParameter("assetListType", String.valueOf(assetListType));
portletURL.setParameter("className", className);
%>

<liferay-ui:search-container
	emptyResultsMessage="no-assets-were-found"
	headerNames="title,author,type,category,modified-date,visible"
	iteratorURL="<%= portletURL %>"
	orderByCol="<%= orderByCol %>"
	orderByType="<%= orderByType %>"
>
	<div class="search-form-container">
		<portlet:actionURL var="searchURL">
			<portlet:param name="mvcPath" value="/marketplace_admin/select_asset_entry.jsp" />
			<portlet:param name="assetListType" value="<%= String.valueOf(assetListType) %>" />
			<portlet:param name="className" value="<%= className %>" />
		</portlet:actionURL>

		<aui:form action="<%= searchURL %>" method="post" name="fm">
			<liferay-ui:tabs names="assets" />

			<div class="asset-entry-control">
				<aui:select label="category" name="assetCategoryId">
					<aui:option label="home" value="0" />

					<%
					for (AssetCategory assetCategory : MarketplaceUtil.getAssetCategories()) {
					%>

						<aui:option label="<%= assetCategory.getName() %>" selected="<%= assetCategoryId == assetCategory.getCategoryId() %>" value="<%= String.valueOf(assetCategory.getCategoryId()) %>" />

					<%
					}
					%>

				</aui:select>

				<aui:input name="keywords" type="text" value="<%= keywords %>" />

				<c:if test="<%= className.equals(AppEntry.class.getName()) %>">
					<aui:input name="approved" type="checkbox" value="<%= approved %>" />
				</c:if>

				<aui:button type="submit" value="search" />
			</div>
		</aui:form>
	</div>

	<div class="separator"><!-- --></div>

	<liferay-ui:search-container-results>

		<%
		Indexer indexer = IndexerRegistryUtil.nullSafeGetIndexer(className);

		SearchContext searchContext = SearchContextFactory.getInstance(request);

		LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

		searchContext.setEnd(searchContainer.getEnd());
		searchContext.setKeywords(keywords);

		if (className.equals(AppEntry.class.getName())) {
			if (approved) {
				params.put("appEntryStatus", WorkflowConstants.STATUS_APPROVED);
			}

			Sort sort = SortFactoryUtil.getSort(AppEntry.class, orderByCol, orderByType);

			searchContext.setSorts(new Sort[] {sort});
		}
		else {
			String orderByField = orderByCol;

			if (orderByCol.equals("author")) {
				orderByField = Field.USER_NAME;
			}
			else if (orderByCol.equals("modified-date")) {
				orderByField = Field.MODIFIED_DATE;
			}

			Sort sort = SortFactoryUtil.create(orderByField, !orderByType.equalsIgnoreCase("asc"));

			searchContext.setSorts(new Sort[] {sort});
		}

		searchContext.setStart(searchContainer.getStart());

		if (assetCategoryId > 0) {
			params.put(Field.ASSET_CATEGORY_IDS, assetCategoryId);
		}

		if (!params.isEmpty()) {
			searchContext.setAttribute("params", params);
		}

		Hits hits = indexer.search(searchContext);

		total = hits.getLength();

		for (int i = 0; i < hits.getDocs().length; i++) {
			Document doc = hits.doc(i);

			long classPK = GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK));

			AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(className, classPK);

			results.add(assetEntry.toEscapedModel());
		}

		pageContext.setAttribute("results", results);
		pageContext.setAttribute("total", total);
		%>

	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row
		className="com.liferay.portlet.asset.model.AssetEntry"
		escapedModel="<%= true %>"
		keyProperty="entryId"
		modelVar="assetEntry"
	>

		<%
		StringBuilder sb = new StringBuilder();

		sb.append("javascript:opener.");
		sb.append(renderResponse.getNamespace());
		sb.append("selectAssetEntry('");
		sb.append(assetEntry.getEntryId());
		sb.append("', '");
		sb.append(assetEntry.getClassName());
		sb.append("', '");
		sb.append(assetEntry.getClassPK());
		sb.append("', '");
		sb.append(MarketplaceAdminUtil.getMarketplaceAssetEntryTypeLabel(assetEntry));
		sb.append("', '");
		sb.append(HtmlUtil.escapeJS(assetEntry.getTitle(locale)));
		sb.append("', '");
		sb.append(HtmlUtil.escapeJS(MarketplaceUtil.getAssetEntryAuthorName(assetEntry)));
		sb.append("', '");
		sb.append(LanguageUtil.get(pageContext, MarketplaceAdminUtil.getMarketplaceAssetEntryStatusLabel(assetEntry)));
		sb.append("', '");
		sb.append(assetEntry.getVisible());
		sb.append("', '");
		sb.append(MarketplaceAdminUtil.getMarketplaceAssetEntryIconURL(assetEntry, renderResponse));

		if (assetListType > 0) {
			sb.append("', '");
			sb.append(assetListType);
		}

		sb.append("'); window.close();");

		String rowHREF = sb.toString();
		%>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="title"
			orderable="<%= true %>"
			value="<%= HtmlUtil.escape(assetEntry.getTitle(locale)) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="author"
			orderable="<%= true %>"
			value="<%= HtmlUtil.escape(MarketplaceUtil.getAssetEntryAuthorName(assetEntry)) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="type"
			value="<%= LanguageUtil.get(pageContext, MarketplaceAdminUtil.getMarketplaceAssetEntryTypeLabel(assetEntry)) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="category"
			orderable="<%= true %>"
			value="<%= MarketplaceUtil.getSubcategoryName(assetEntry) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="modified-date"
			orderable="<%= true %>"
			value="<%= mediumDateFormatDate.format(assetEntry.getModifiedDate()) %>"
		/>

		<liferay-ui:search-container-column-text
			href="<%= rowHREF %>"
			name="visible"
			translate="<%= true %>"
			value='<%= assetEntry.getVisible() ? "visible" : "not-visible" %>'
		/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>