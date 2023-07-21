<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetCategory fileTypeAssetCategory = (AssetCategory)renderRequest.getAttribute(DownloadsDisplayWebKeys.ASSET_CATEGORY_FILE_TYPE);
AssetCategory productAssetCategory = (AssetCategory)renderRequest.getAttribute(DownloadsDisplayWebKeys.ASSET_CATEGORY_PRODUCT);

String ddmStructureKey = downloadsDisplayContext.getDDMStructureKey();
%>

<h1>
	<liferay-ui:message key="downloads" />
</h1>

<c:if test="<%= ddmStructureKey.equals(DownloadsDDMStructureConstants.KEY_DOWNLOAD) %>">
	<h5 class="secondary-text-color section-subtitle">
		<liferay-ui:message key="use-the-dropdown-menus-below-to-find-the-downloads-you-need" />
	</h5>

	<div id="osbDownloadsDisplayFilters"></div>
</c:if>

<div class="results">
	<liferay-ui:search-container
		emptyResultsMessage="content-collection-is-empty-select-your-settings-above-to-show-details"
		searchContainer="<%= downloadsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			cssClass="journal-article-row"
			keyProperty="resourcePrimKey"
			modelVar="journalArticle"
		>
			<liferay-ui:search-container-column-text
				name="released"
			>

				<%
				DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat("yyyy-MM-dd");

				Date releaseDate = dateFormat.parse(DDMFieldsUtil.getString(journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent()), "releaseDate"));
				%>

				<%= mediumDateFormatDate.format(releaseDate) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				cssClass="th-separator"
				value=""
			/>

			<liferay-ui:search-container-column-jsp
				name="name"
				path="/view_journal_article.jsp"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<c:if test="<%= ddmStructureKey.equals(DownloadsDDMStructureConstants.KEY_DOWNLOAD) %>">

	<%
	PortletURL portletURL = renderResponse.createRenderURL();
	%>

	<aui:script>
		Downloads.render(
			Downloads.SearchFilter,
			{
				actionURL: '<%= portletURL.toString() %>',
				currentFileTypeAssetCategoryId: '<%= (fileTypeAssetCategory != null) ? fileTypeAssetCategory.getCategoryId() : "" %>',
				currentProductAssetCategoryId: '<%= (productAssetCategory != null) ? productAssetCategory.getCategoryId() : "" %>',
				productsJSONArray: <%= downloadsDisplayContext.getProductsJSONArray() %>
			},
			document.getElementById('osbDownloadsDisplayFilters')
		);
	</aui:script>
</c:if>