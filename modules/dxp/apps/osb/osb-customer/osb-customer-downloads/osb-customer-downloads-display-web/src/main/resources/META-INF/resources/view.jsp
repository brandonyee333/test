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

<h1>
	<liferay-ui:message key="downloads" />
</h1>

<div>
	<liferay-ui:message key="find-the-downloads-you-need-by-filtering-the-results-with-the-drop-down-menus-below" />
</div>

<div class="filters">

</div>

<div class="results">
	<liferay-ui:search-container
		emptyResultsMessage="no-downloads-were-found"
		searchContainer="<%= downloadsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.journal.model.JournalArticle"
			modelVar="journalArticle"
		>

			<%
			Fields ddmFields = journalConverter.getDDMFields(journalArticle.getDDMStructure(), journalArticle.getContent());
			%>

			<liferay-ui:search-container-column-text
				name="released"
			>

				<%
				Field ddmField = ddmFields.get("releaseDate");
				%>

				<%= String.valueOf(ddmField.getValue()) %>
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