<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalContentDisplayContext.getArticle();

AssetRenderer<JournalArticle> assetRenderer = journalContentDisplayContext.getAssetRenderer();

String title = assetRenderer.getTitle(locale);

if (article.getGroupId() != themeDisplay.getScopeGroupId()) {
	Group articleGroup = GroupLocalServiceUtil.getGroup(article.getGroupId());

	title = title + StringPool.SPACE + StringPool.OPEN_PARENTHESIS + articleGroup.getDescriptiveName(locale) + StringPool.CLOSE_PARENTHESIS;
}

String articleImageURL = HtmlUtil.escapeAttribute(assetRenderer.getThumbnailPath(liferayPortletRequest));
%>

<c:choose>
	<c:when test="<%= Validator.isNotNull(articleImageURL) %>">
		<liferay-frontend:vertical-card
			cssClass="article-preview-content"
			imageUrl="<%= articleImageURL %>"
			subtitle="<%= assetRenderer.getSummary(liferayPortletRequest, liferayPortletResponse) %>"
			title="<%= title %>"
		>
			<liferay-frontend:vertical-card-sticker-bottom>
				<liferay-ui:user-portrait
					cssClass="sticker sticker-bottom"
					imageCssClass="user-icon-lg"
					userId="<%= assetRenderer.getUserId() %>"
				/>
			</liferay-frontend:vertical-card-sticker-bottom>

			<liferay-frontend:vertical-card-footer>
				<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= article.getStatus() %>" />
			</liferay-frontend:vertical-card-footer>
		</liferay-frontend:vertical-card>
	</c:when>
	<c:otherwise>
		<liferay-frontend:icon-vertical-card
			cssClass="article-preview-content"
			icon="web-content"
			subtitle="<%= assetRenderer.getSummary(liferayPortletRequest, liferayPortletResponse) %>"
			title="<%= title %>"
		>
			<liferay-frontend:vertical-card-sticker-bottom>
				<liferay-ui:user-portrait
					cssClass="sticker sticker-bottom"
					imageCssClass="user-icon-lg"
					userId="<%= assetRenderer.getUserId() %>"
				/>
			</liferay-frontend:vertical-card-sticker-bottom>

			<liferay-frontend:vertical-card-footer>
				<aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= article.getStatus() %>" />
			</liferay-frontend:vertical-card-footer>
		</liferay-frontend:icon-vertical-card>
	</c:otherwise>
</c:choose>