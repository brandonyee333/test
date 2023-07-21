<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<aui:input name="TypeSettingsProperties--groupId--" type="hidden" value="<%= (selLayout == null) ? StringPool.BLANK : selLayout.getGroupId() %>" />
<aui:input name="TypeSettingsProperties--privateLayout--" type="hidden" value="<%= (selLayout == null) ? StringPool.BLANK : selLayout.isPrivateLayout() %>" />

<%
long linkToLayoutId = 0;

if (selLayout != null) {
	linkToLayoutId = GetterUtil.getLong(selLayout.getTypeSettingsProperty("linkToLayoutId"));
}
%>

<aui:select label="link-to-layout" name="TypeSettingsProperties--linkToLayoutId--">

	<%
	List<LayoutDescription> layoutDescriptions = (List<LayoutDescription>)request.getAttribute(WebKeys.LAYOUT_DESCRIPTIONS);

	for (LayoutDescription layoutDescription : layoutDescriptions) {
		Layout layoutDescriptionLayout = LayoutLocalServiceUtil.fetchLayout(layoutDescription.getPlid());
	%>

		<c:if test="<%= layoutDescriptionLayout != null %>">
			<aui:option disabled="<%= (selLayout != null) && (selLayout.getPlid() == layoutDescriptionLayout.getPlid()) %>" label="<%= layoutDescription.getDisplayName() %>" selected="<%= linkToLayoutId == layoutDescriptionLayout.getLayoutId() %>" value="<%= layoutDescriptionLayout.getLayoutId() %>" />
		</c:if>

	<%
	}
	%>

</aui:select>