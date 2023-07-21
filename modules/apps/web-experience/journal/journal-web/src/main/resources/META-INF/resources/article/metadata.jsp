<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
JournalArticle article = journalDisplayContext.getArticle();

DDMStructure ddmStructure = (DDMStructure)request.getAttribute("edit_article.jsp-structure");

boolean changeStructure = GetterUtil.getBoolean(request.getAttribute("edit_article.jsp-changeStructure"));
%>

<liferay-ui:error-marker
	key="<%= WebKeys.ERROR_SECTION %>"
	value="categorization"
/>

<aui:model-context bean="<%= article %>" model="<%= JournalArticle.class %>" />

<liferay-ui:asset-categories-error />

<liferay-ui:asset-tags-error />

<%
long classPK = 0;
double priority = 0;

if (article != null) {
	classPK = article.getResourcePrimKey();

	if (!article.isApproved() && (article.getVersion() != JournalArticleConstants.VERSION_DEFAULT)) {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), article.getPrimaryKey());

		if (assetEntry != null) {
			classPK = article.getPrimaryKey();
			priority = assetEntry.getPriority();
		}
	}
	else {
		AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(JournalArticle.class.getName(), article.getResourcePrimKey());

		if (assetEntry != null) {
			priority = assetEntry.getPriority();
		}
	}
}
%>

<aui:input classPK="<%= classPK %>" classTypePK="<%= ddmStructure.getStructureId() %>" ignoreRequestValue="<%= changeStructure %>" name="categories" type="assetCategories" />

<aui:input classPK="<%= classPK %>" ignoreRequestValue="<%= changeStructure %>" name="tags" type="assetTags" />

<aui:input label="priority" name="assetPriority" type="text" value="<%= priority %>">
	<aui:validator name="number" />

	<aui:validator name="min">[0]</aui:validator>
</aui:input>

<c:if test="<%= CustomAttributesUtil.hasCustomAttributes(company.getCompanyId(), JournalArticle.class.getName(), classPK, null) %>">
	<liferay-ui:custom-attribute-list
		className="<%= JournalArticle.class.getName() %>"
		classPK="<%= (article != null) ? article.getPrimaryKey() : 0 %>"
		editable="<%= true %>"
		label="<%= true %>"
	/>
</c:if>