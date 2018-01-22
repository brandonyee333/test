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