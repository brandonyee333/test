<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
AssetRenderer<?> assetRenderer = (AssetRenderer<?>)request.getAttribute(WebKeys.ASSET_RENDERER);

BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());

String coverImageURL = entry.getCoverImageURL(themeDisplay);
%>

<liferay-util:html-top
	outputKey="blogs_common_main_css"
>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/blogs/css/common_main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<c:if test="<%= entry.isSmallImage() && Validator.isNull(coverImageURL) %>">
	<div class="asset-small-image">
		<img alt="" class="asset-small-image img-thumbnail" src="<%= HtmlUtil.escape(entry.getSmallImageURL(themeDisplay)) %>" width="150" />
	</div>
</c:if>

<div class="portlet-blogs">
	<div class="entry-body" data-analytics-asset-id="<%= String.valueOf(entry.getEntryId()) %>" data-analytics-asset-title="<%= HtmlUtil.escapeAttribute(entry.getTitle()) %>" data-analytics-asset-type="blog">
		<c:if test="<%= Validator.isNotNull(coverImageURL) %>">
			<div class="cover-image-container" style="background-image: url(<%= coverImageURL %>);"></div>
		</c:if>

		<%= HtmlUtil.stripHtml(assetRenderer.getSummary(renderRequest, renderResponse)) %>
	</div>
</div>