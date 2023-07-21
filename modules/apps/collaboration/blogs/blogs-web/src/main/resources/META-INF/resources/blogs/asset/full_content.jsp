<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
BlogsEntry entry = (BlogsEntry)request.getAttribute(WebKeys.BLOGS_ENTRY);

Portlet portlet = PortletLocalServiceUtil.getPortletById(company.getCompanyId(), portletDisplay.getId());
%>

<liferay-util:html-top
	outputKey="blogs_common_main_css"
>
	<link href="<%= PortalUtil.getStaticResourceURL(request, PortalUtil.getPathProxy() + application.getContextPath() + "/blogs/css/common_main.css", portlet.getTimestamp()) %>" rel="stylesheet" type="text/css" />
</liferay-util:html-top>

<div class="portlet-blogs">
	<div class="entry-body" data-analytics-asset-id="<%= String.valueOf(entry.getEntryId()) %>" data-analytics-asset-title="<%= HtmlUtil.escapeAttribute(entry.getTitle()) %>" data-analytics-asset-type="blog">

		<%
		String coverImageURL = entry.getCoverImageURL(themeDisplay);
		%>

		<c:if test="<%= Validator.isNotNull(coverImageURL) %>">
			<div class="cover-image-container" style="background-image: url(<%= coverImageURL %>);"></div>
		</c:if>

		<%
		String subtitle = entry.getSubtitle();
		%>

		<c:if test="<%= Validator.isNotNull(subtitle) %>">
			<div class="entry-subtitle">
				<p><%= HtmlUtil.escape(subtitle) %></p>
			</div>
		</c:if>

		<div class="entry-date icon-calendar">
			<span class="hide-accessible"><liferay-ui:message key="published-date" /></span>

			<%= dateFormatDateTime.format(entry.getDisplayDate()) %>
		</div>

		<%= entry.getContent() %>

		<liferay-expando:custom-attributes-available
			className="<%= BlogsEntry.class.getName() %>"
		>
			<liferay-expando:custom-attribute-list
				className="<%= BlogsEntry.class.getName() %>"
				classPK="<%= (entry != null) ? entry.getEntryId() : 0 %>"
				editable="<%= false %>"
				label="<%= true %>"
			/>
		</liferay-expando:custom-attributes-available>
	</div>
</div>