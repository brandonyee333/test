<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<%
String copyLayoutIdPrefix = ParamUtil.getString(request, "copyLayoutIdPrefix");
%>

<aui:select id='<%= HtmlUtil.escapeAttribute(copyLayoutIdPrefix) + "copyPlid" %>' label="copy-from-page" name="copyPlid">

	<%
	List<LayoutDescription> layoutDescriptions = (List<LayoutDescription>)request.getAttribute(WebKeys.LAYOUT_DESCRIPTIONS);

	for (LayoutDescription layoutDescription : layoutDescriptions) {
	%>

		<c:if test="<%= layoutDescription.getPlid() >= 0 %>">
			<aui:option disabled="<%= (layoutDescription.getPlid() == 0) || ((selLayout != null) && (selLayout.getPlid() == layoutDescription.getPlid())) %>" label="<%= layoutDescription.getDisplayName() %>" value="<%= layoutDescription.getPlid() %>" />
		</c:if>

	<%
	}
	%>

</aui:select>