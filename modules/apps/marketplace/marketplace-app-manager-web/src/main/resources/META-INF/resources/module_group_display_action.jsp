<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

ModuleGroupDisplay moduleGroupDisplay = (ModuleGroupDisplay)row.getObject();

String bundleIds = _getBundleIds(moduleGroupDisplay);
%>

<liferay-ui:icon-menu
	direction="left-side"
	icon="<%= StringPool.BLANK %>"
	markupView="lexicon"
	message="<%= StringPool.BLANK %>"
	showWhenSingleIcon="<%= true %>"
>
	<c:choose>
		<c:when test="<%= moduleGroupDisplay.getState() == BundleStateConstants.ACTIVE %>">
			<portlet:actionURL name="deactivateBundles" var="deactivateBundlesURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="bundleIds" value="<%= bundleIds %>" />
			</portlet:actionURL>

			<%
			String taglibDeactivateBundlesURL = "javascript:if (confirm('" + UnicodeLanguageUtil.get(request, "are-you-sure-you-want-to-deactivate-this") + "')) {submitForm(document.hrefFm, '" + HtmlUtil.unescape(deactivateBundlesURL.toString()) + "');};";
			%>

			<liferay-ui:icon
				message="deactivate"
				url="<%= taglibDeactivateBundlesURL %>"
			/>
		</c:when>
		<c:otherwise>
			<portlet:actionURL name="activateBundles" var="activateBundlesURL">
				<portlet:param name="redirect" value="<%= currentURL %>" />
				<portlet:param name="bundleIds" value="<%= bundleIds %>" />
			</portlet:actionURL>

			<liferay-ui:icon
				message="activate"
				url="<%= activateBundlesURL %>"
			/>
		</c:otherwise>
	</c:choose>

	<portlet:actionURL name="uninstallBundles" var="uninstallBundlesURL">
		<portlet:param name="redirect" value="<%= currentURL %>" />
		<portlet:param name="bundleIds" value="<%= bundleIds %>" />
	</portlet:actionURL>

	<liferay-ui:icon-delete
		message="uninstall"
		url="<%= uninstallBundlesURL %>"
	/>
</liferay-ui:icon-menu>

<%!
private String _getBundleIds(ModuleGroupDisplay moduleGroupDisplay) {
	List<Bundle> bundles = moduleGroupDisplay.getBundles();

	long[] bundleIds = new long[bundles.size()];

	for (int i = 0; i < bundles.size(); i++) {
		Bundle bundle = bundles.get(i);

		bundleIds[i] = bundle.getBundleId();
	}

	return StringUtil.merge(bundleIds);
}
%>