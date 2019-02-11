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
AssetCategory fileTypeAssetCategory = (AssetCategory)renderRequest.getAttribute(DownloadsDisplayWebKeys.ASSET_CATEGORY_FILE_TYPE);
AssetCategory productAssetCategory = (AssetCategory)renderRequest.getAttribute(DownloadsDisplayWebKeys.ASSET_CATEGORY_PRODUCT);

String ddmStructureKey = downloadsDisplayContext.getDDMStructureKey();
%>

<h1>
	<liferay-ui:message key="downloads" />
</h1>

<c:if test="<%= ddmStructureKey.equals(DDMStructureConstants.KEY_DOWNLOAD) %>">
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

				Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());

				Date releaseDate = dateFormat.parse(DDMFieldsUtil.getString(ddmFields, "releaseDate"));
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

<c:if test="<%= ddmStructureKey.equals(DDMStructureConstants.KEY_DOWNLOAD) %>">

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