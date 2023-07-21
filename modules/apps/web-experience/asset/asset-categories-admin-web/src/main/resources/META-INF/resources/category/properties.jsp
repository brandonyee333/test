<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long categoryId = ParamUtil.getLong(request, "categoryId");

AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

int[] categoryPropertiesIndexes = null;

List<AssetCategoryProperty> categoryProperties = Collections.emptyList();

String categoryPropertiesIndexesParam = ParamUtil.getString(request, "categoryPropertiesIndexes");

if (Validator.isNotNull(categoryPropertiesIndexesParam)) {
	categoryProperties = new ArrayList<AssetCategoryProperty>();

	categoryPropertiesIndexes = StringUtil.split(categoryPropertiesIndexesParam, 0);

	for (int categoryPropertiesIndex : categoryPropertiesIndexes) {
		categoryProperties.add(new AssetCategoryPropertyImpl());
	}
}
else {
	if (category != null) {
		categoryProperties = AssetCategoryPropertyServiceUtil.getCategoryProperties(category.getCategoryId());

		categoryPropertiesIndexes = new int[categoryProperties.size()];

		for (int i = 0; i < categoryProperties.size(); i++) {
			categoryPropertiesIndexes[i] = i;
		}
	}

	if (categoryProperties.isEmpty()) {
		categoryProperties = new ArrayList<AssetCategoryProperty>();

		categoryProperties.add(new AssetCategoryPropertyImpl());

		categoryPropertiesIndexes = new int[] {0};
	}

	if (categoryPropertiesIndexes == null) {
		categoryPropertiesIndexes = new int[0];
	}
}
%>

<div id="<portlet:namespace />categoryPropertiesId">
	<p class="text-muted">
		<liferay-ui:message key="properties-are-a-way-to-add-more-detailed-information-to-a-specific-category" />
	</p>

	<%
	for (int i = 0; i < categoryPropertiesIndexes.length; i++) {
		int categoryPropertiesIndex = categoryPropertiesIndexes[i];
	%>

		<aui:model-context bean="<%= categoryProperties.get(i) %>" model="<%= AssetCategoryProperty.class %>" />

		<div class="lfr-form-row lfr-form-row-inline">
			<div class="row-fields">
				<aui:input fieldParam='<%= "key" + categoryPropertiesIndex %>' id='<%= "key" + categoryPropertiesIndex %>' name="key" />

				<aui:input fieldParam='<%= "value" + categoryPropertiesIndex %>' id='<%= "value" + categoryPropertiesIndex %>' name="value" />
			</div>
		</div>

	<%
	}
	%>

</div>

<aui:input name="categoryPropertiesIndexes" type="hidden" value="<%= StringUtil.merge(categoryPropertiesIndexes) %>" />

<aui:script use="liferay-auto-fields">
	var autoFields = new Liferay.AutoFields(
		{
			contentBox: '#<portlet:namespace />categoryPropertiesId',
			fieldIndexes: '<portlet:namespace />categoryPropertiesIndexes',
			namespace: '<portlet:namespace />'
		}
	).render();

	var categoryPropertiesTrigger = A.one('#<portlet:namespace />categoryPropertiesId');

	if (categoryPropertiesTrigger) {
		categoryPropertiesTrigger.setData('autoFieldsInstance', autoFields);
	}
</aui:script>