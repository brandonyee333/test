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

Field productDDMField = ddmFields.get("product");

String product = JournalArticleUtil.getSelectOptionValue(productDDMField);
%>

<c:choose>
	<c:when test="<%= product.equals(DDMStructureConstants.PRODUCT_COMMERCE) %>">
	</c:when>
	<c:when test="<%= product.equals(DDMStructureConstants.PRODUCT_DXP_70) || product.equals(DDMStructureConstants.PRODUCT_DXP_71) %>">
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
		>
			<liferay-ui:search-container-column-text
				name="released"
			>

				<%
				Field releaseDateDDMField = ddmFields.get("releaseDate");
				%>

				<%= String.valueOf(releaseDateDDMField.getValue()) %>
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text
				name="name"
			>

				<%
				JournalArticleDisplay journalArticleDisplay = JournalArticleLocalServiceUtil.getArticleDisplay(journalArticle, null, null, themeDisplay.getLanguageId(), 0, new PortletRequestModel(renderRequest, renderResponse), themeDisplay);
				%>

				<%= journalArticleDisplay.getContent() %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</div>

<portlet:renderURL var="backURL">
	<portlet:param name="mvcRenderCommandName" value="/view" />
</portlet:renderURL>

<liferay-ui:message arguments="<%= backURL %>" key="view-previous-releases-in-liferay's-archive" />