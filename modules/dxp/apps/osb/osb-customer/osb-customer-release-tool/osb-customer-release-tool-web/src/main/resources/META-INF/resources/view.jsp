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
String tabs1 = ParamUtil.getString(request, "tabs1");

String product = ParamUtil.getString(request, "product");
double productVersion = ParamUtil.getDouble(request, "productVersion");
double fromFixPackVersion = ParamUtil.getDouble(request, "fromFixPackVersion");
double toFixPackVersion = ParamUtil.getDouble(request, "toFixPackVersion");

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("fromFixPackVersion", String.valueOf(fromFixPackVersion));
portletURL.setParameter("product", product);
portletURL.setParameter("productVersion", String.valueOf(productVersion));
portletURL.setParameter("toFixPackVersion", String.valueOf(toFixPackVersion));
%>

<liferay-portlet:renderURL var="fixPacksURL">
	<portlet:param name="tabs1" value="<%= tabs1 %>" />
	<portlet:param name="fromFixPackVersion" value="<%= String.valueOf(fromFixPackVersion) %>" />
	<portlet:param name="orderByType" value="desc" />
	<portlet:param name="product" value="<%= product %>" />
	<portlet:param name="productVersion" value="<%= String.valueOf(productVersion) %>" />
	<portlet:param name="toFixPackVersion" value="<%= String.valueOf(toFixPackVersion) %>" />
</liferay-portlet:renderURL>

<div class="main-heading">
	<h1>
		<liferay-ui:message key="liferay-dxp-release-notes-tool" />
	</h1>
</div>

<div class="fixpack-filters" id="<portlet:namespace />fixpackFilters"></div>

<liferay-ui:tabs
	names='<%= "highlights,changelog,module-changes" %>'
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("changelog") %>'>
		<liferay-util:include page="/changelog.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:when test='<%= tabs1.equals("module-changes") %>'>
		<liferay-util:include page="/module_changes.jsp" servletContext="<%= application %>" />
	</c:when>
	<c:otherwise>
		<liferay-util:include page="/highlights.jsp" servletContext="<%= application %>" />
	</c:otherwise>
</c:choose>

<aui:script>
	ReleaseTool.render(
		ReleaseTool.FixpackFilters,
		{
			actionURL: '<%= fixPacksURL %>',
			filtersJSON: <%= releaseToolDisplayContext.getFixPackFiltersJSONArray() %>,
			fixpackURL: '<%= releaseToolDisplayContext.getFixPackDownloadURL(product, productVersion, toFixPackVersion) %>',
			fromFixPackVersion: '<%= String.valueOf(fromFixPackVersion) %>',
			productVersion: '<%= String.valueOf(productVersion) %>',
			tabName: '<%= HtmlUtil.escape(tabs1) %>',
			toFixPackVersion: '<%= String.valueOf(toFixPackVersion) %>'
		},
		document.getElementById('<portlet:namespace />fixpackFilters')
	);
</aui:script>