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
JournalArticle journalArticle = (JournalArticle)renderRequest.getAttribute(DownloadsDisplayWebKeys.JOURNAL_ARTICLE);

Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());

String product = DDMFieldsUtil.getSelectOption(ddmFields, "product");
%>

<c:choose>
	<c:when test="<%= product.equals(DDMStructureConstants.PRODUCT_COMMERCE) %>">
		<svg class="commerce-logo-full">
			<use xlink:href="#commerce-logo-full" />
		</svg>
	</c:when>
	<c:when test="<%= product.equals(DDMStructureConstants.PRODUCT_DXP_70) || product.equals(DDMStructureConstants.PRODUCT_DXP_71) %>">
		<svg class="dxp-logo-full">
			<use xlink:href="#dxp-logo-full" />
		</svg>
	</c:when>
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

<portlet:renderURL var="backURL">
	<portlet:param name="mvcRenderCommandName" value="/view" />
</portlet:renderURL>

<div class="view-previous-releases">
	<liferay-ui:message arguments="<%= backURL %>" key="view-previous-releases-in-liferay's-archive" />
</div>