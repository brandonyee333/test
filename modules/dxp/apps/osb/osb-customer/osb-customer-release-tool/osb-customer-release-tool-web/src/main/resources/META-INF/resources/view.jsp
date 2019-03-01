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

<br />

<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/fix_packs" var="fixPacksURL">
	<liferay-portlet:param name="fromFixPackVersion" value="2.0" />
	<liferay-portlet:param name="fromProductVersion" value="7.0" />
	<liferay-portlet:param name="orderByCol" value="releaseDate" />
	<liferay-portlet:param name="orderByType" value="desc" />
	<liferay-portlet:param name="product" value="dxp" />
	<liferay-portlet:param name="toFixPackVersion" value="1.0" />
	<liferay-portlet:param name="toProductVersion" value="7.1" />
</liferay-portlet:resourceURL>

<%= fixPacksURL %>

<br />

<%
JSONObject jsonObject = fixPackSearcher.search(renderRequest, renderResponse);
%>

<%= HtmlUtil.escape(jsonObject.toString()) %>