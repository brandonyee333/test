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

<%= releaseToolDisplayContext.getHightlightsFiltersJSONArray() %>

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/fix_packs" var="fixPacksURL">
	<liferay-portlet:param name="fromFixPackAssetCategoryId" value="1" />
	<liferay-portlet:param name="orderByCol" value="releaseDate" />
	<liferay-portlet:param name="orderByType" value="desc" />
	<liferay-portlet:param name="productAssetCategoryId" value="1" />
	<liferay-portlet:param name="toFixPackAssetCategoryId" value="2" />
</liferay-portlet:resourceURL>

<%= fixPacksURL %>