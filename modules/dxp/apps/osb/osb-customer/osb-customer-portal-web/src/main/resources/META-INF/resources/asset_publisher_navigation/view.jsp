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

<%@ include file="/asset_publisher_navigation/init.jsp" %>

<%
Portlet portlet = (Portlet)request.getAttribute(WebKeys.RENDER_PORTLET);

PublicRenderParameter publicRenderParameter = portlet.getPublicRenderParameter("categoryId");

String categoryIdParameterName = PortletQNameUtil.getPublicRenderParameterName(publicRenderParameter.getQName());

Map<Long, Long> vocabularyCategoryIds = new HashMap<Long, Long>();

long[] categoryIds = StringUtil.split(ParamUtil.getString(request, "categoryId"), 0L);

for (long categoryId : categoryIds)) {
	try {
		AssetCategory assetCategory = AssetCategoryLocalServiceUtil.getCategory(categoryId);

		vocabularyCategoryIds.put(assetCategory.getVocabularyId(), categoryId);
	}
	catch (Exception e) {
	}
}
%>

<liferay-portlet:renderURL varImpl="portletURL">
	<portlet:param name="resetCur" value="<%= Boolean.TRUE.toString() %>" />
</liferay-portlet:renderURL>

<aui:form action="<%= portletURL %>" method="get">
	<liferay-portlet:renderURLParams varImpl="portletURL" />
	<input id="<portlet:namespace />categoryIds" name="<%= categoryIdParameterName %>" type="hidden" value="<%= StringUtil.merge(categoryIds) %>" />

	<div class="filters">
		<label>
			<liferay-ui:message key="filter-by" />:
		</label>

		<%
		for (String vocabularyName : vocabularyNames) {
			try {
				AssetVocabulary assetVocabulary = AssetVocabularyLocalServiceUtil.getGroupVocabulary(scopeGroupId, vocabularyName);

				long selCategoryId = 0;

				if (vocabularyCategoryIds.containsKey(assetVocabulary.getVocabularyId())) {
					selCategoryId = vocabularyCategoryIds.get(assetVocabulary.getVocabularyId());
				}
		%>

				<select onChange="<portlet:namespace />filter('<%= selCategoryId %>', this.value);">
					<option value=""><%= HtmlUtil.escape(assetVocabulary.getTitle(locale)) %></option>

					<%
					List<AssetCategory> assetCategories = AssetCategoryLocalServiceUtil.getVocabularyCategories(assetVocabulary.getVocabularyId(), QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

					for (AssetCategory assetCategory : assetCategories) {
					%>

						<option <%= (selCategoryId == assetCategory.getCategoryId()) ? "selected" : "" %> value="<%= assetCategory.getCategoryId() %>"><%= HtmlUtil.escape(assetCategory.getTitle(locale)) %></option>

					<%
					}
					%>

				</select>

		<%
			}
			catch (Exception e) {
			}
		}
		%>

	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />filter(oldCategoryId, newCategoryId) {
		var categoryIds = document.getElementById('<portlet:namespace />categoryIds');

		if (oldCategoryId == '0') {
			if (categoryIds.value == '') {
				categoryIds.value = newCategoryId;
			}
			else {
				categoryIds.value += ',' + newCategoryId;
			}
		}
		else {
			categoryIds.value = categoryIds.value.replace(oldCategoryId, newCategoryId);

			if (newCategoryId == '') {
				categoryIds.value = categoryIds.value.replace(',,', ',');

				if (categoryIds.value.indexOf(',') == 0) {
					categoryIds.value = categoryIds.value.substring(1);
				}
				else if (categoryIds.value.indexOf(',') == categoryIds.value.length) {
					categoryIds.value = categoryIds.value.substring(0, categoryIds.value.length - 1);
				}
			}
		}

		document.<portlet:namespace />fm.submit();
	}
</aui:script>