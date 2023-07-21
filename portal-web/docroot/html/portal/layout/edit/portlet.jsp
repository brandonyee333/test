<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/portal/layout/edit/init.jsp" %>

<%
Boolean showCopyPortlets = ParamUtil.getBoolean(request, "showCopyPortlets");
Boolean showLayoutTemplates = ParamUtil.getBoolean(request, "showLayoutTemplates", true);
%>

<div class="<%= showCopyPortlets ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />copyPortletsFromPage">
	<p>
		<c:if test="<%= selLayout != null %>">
			<liferay-ui:message arguments="<%= HtmlUtil.escape(selLayout.getName(locale)) %>" key="the-applications-in-page-x-will-be-replaced-with-the-ones-in-the-page-you-select-below" translateArguments="<%= false %>" />
		</c:if>
	</p>

	<liferay-util:include page="/html/portal/layout/edit/portlet_applications.jsp" />

	<aui:button-row>
		<aui:button name="copySubmitButton" value="copy" />
	</aui:button-row>
</div>

<div class="<%= showLayoutTemplates ? StringPool.BLANK : "hide" %>" id="<portlet:namespace />layoutTemplates">

	<%
	LayoutTypePortlet selLayoutTypePortlet = null;

	Theme selTheme = layout.getTheme();

	if (selLayout != null) {
		selLayoutTypePortlet = (LayoutTypePortlet)selLayout.getLayoutType();

		selTheme = selLayout.getTheme();
	}
	%>

	<liferay-ui:layout-templates-list
		layoutTemplateId="<%= (selLayoutTypePortlet != null) ? selLayoutTypePortlet.getLayoutTemplateId() : StringPool.BLANK %>"
		layoutTemplates="<%= LayoutTemplateLocalServiceUtil.getLayoutTemplates(selTheme.getThemeId()) %>"
	/>
</div>