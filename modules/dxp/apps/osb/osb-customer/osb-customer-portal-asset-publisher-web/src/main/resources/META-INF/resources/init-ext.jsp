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

<%
long[] assetCategoryIds = StringUtil.split(ParamUtil.getString(request, "categoryId"), 0L);

if (assetCategoryIds.length > 0) {
	for (int i = 0; true; i++) {
		String[] queryValues = portletPreferences.getValues("queryValues" + i, null);

		if (ArrayUtil.isEmpty(queryValues)) {
			portletPreferences.setValue("queryAndOperator" + i, Boolean.TRUE.toString());
			portletPreferences.setValue("queryContains" + i, Boolean.TRUE.toString());
			portletPreferences.setValue("queryName" + i, "assetCategories");
			portletPreferences.setValues("queryValues" + i, ArrayUtil.toStringArray(assetCategoryIds));

			break;
		}
	}

	assetPublisherDisplayContext = new AssetPublisherDisplayContext(assetPublisherCustomizer, liferayPortletRequest, liferayPortletResponse, portletPreferences);
}
%>