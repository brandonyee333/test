<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/init.jsp" %>

<%
String image = (String)request.getAttribute("liferay-ui:png_image:image");
%>

<div
	style="
	<c:choose>
		<c:when test="<%= BrowserSnifferUtil.isIe(request) && (BrowserSnifferUtil.getMajorVersion(request) >= 5.5) %>">
			filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='<%= image %>', sizingMethod='scale');
		</c:when>
		<c:otherwise>
			background-image: url(<%= image %>);
		</c:otherwise>
	</c:choose>

	height: <%= (String)request.getAttribute("liferay-ui:png_image:height") %>px; width: <%= (String)request.getAttribute("liferay-ui:png_image:width") %>px;"
>
</div>