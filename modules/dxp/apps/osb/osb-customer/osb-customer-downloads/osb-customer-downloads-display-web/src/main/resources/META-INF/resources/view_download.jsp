<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle journalArticle = (JournalArticle)renderRequest.getAttribute(DownloadsDisplayWebKeys.JOURNAL_ARTICLE);

AssetCategory productAssetCategory = (AssetCategory)renderRequest.getAttribute(DownloadsDisplayWebKeys.ASSET_CATEGORY_PRODUCT);

String logo = downloadsAssetCategoryUtil.getPropertyValue(productAssetCategory.getCategoryId(), DownloadsAssetCategoryConstants.PROPERTY_LOGO);
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(logo) %>">
		<svg class="<%= logo %>">
			<use xlink:href="<%= StringPool.POUND + logo %>" />
		</svg>
	</c:when>
	<c:otherwise>
		<h1>
			<liferay-ui:message key="download" />
		</h1>
	</c:otherwise>
</c:choose>

<div class="results">
	<liferay-ui:search-container>
		<liferay-ui:search-container-results>

			<%
			results.add(journalArticle);

			searchContainer.setResults(results);
			searchContainer.setTotal(results.size());
			%>

		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			cssClass="journal-article-row"
			keyProperty="resourcePrimKey"
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

<portlet:renderURL var="backURL">
	<portlet:param name="mvcRenderCommandName" value="/view" />
</portlet:renderURL>

<%
String taglibArchiveURL = "<a href=\"" + backURL + "\">" + LanguageUtil.get(request, "archive") + "</a>";
%>

<div class="view-previous">
	<liferay-ui:message arguments="<%= taglibArchiveURL %>" key="view-previous-releases-in-the-x" />
</div>