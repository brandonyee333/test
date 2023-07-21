<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/html/taglib/ui/form_navigator/init.jsp" %>

<%
String tabs1Param = "tabs1";
String tabs1Value = GetterUtil.getString(SessionClicks.get(request, namespace + id, null));

List<String> filterCategoryKeys = new ArrayList<String>();

for (String categoryKey : categoryKeys) {
	List<FormNavigatorEntry<Object>> formNavigatorEntries = FormNavigatorEntryUtil.getFormNavigatorEntries(id, categoryKey, user, formModelBean);

	if (ListUtil.isNotEmpty(formNavigatorEntries)) {
		filterCategoryKeys.add(categoryKey);
	}
}
%>

<c:choose>
	<c:when test="<%= deprecatedCategorySections.length > 0 %>">
		<%@ include file="/html/taglib/ui/form_navigator/lexicon/deprecated_sections.jspf" %>
	</c:when>
	<c:when test="<%= filterCategoryKeys.size() > 1 %>">
		<liferay-ui:tabs
			names="<%= StringUtil.merge(filterCategoryKeys) %>"
			param="<%= tabs1Param %>"
			refresh="<%= false %>"
			type="tabs nav-tabs-default"
			value="<%= tabs1Value %>"
		>

			<%
			for (String categoryKey : filterCategoryKeys) {
				List<FormNavigatorEntry<Object>> formNavigatorEntries = FormNavigatorEntryUtil.getFormNavigatorEntries(id, categoryKey, user, formModelBean);

				request.setAttribute("currentTab", categoryKey);
			%>

				<liferay-ui:section>
					<%@ include file="/html/taglib/ui/form_navigator/lexicon/sections.jspf" %>
				</liferay-ui:section>

			<%
			}

			String errorTab = (String)request.getAttribute("errorTab");

			if (Validator.isNotNull(errorTab)) {
				request.setAttribute(WebKeys.ERROR_SECTION, errorTab);
			}
			%>

		</liferay-ui:tabs>
	</c:when>
	<c:otherwise>

		<%
		List<FormNavigatorEntry<Object>> formNavigatorEntries = FormNavigatorEntryUtil.getFormNavigatorEntries(id, user, formModelBean);
		%>

		<%@ include file="/html/taglib/ui/form_navigator/lexicon/sections.jspf" %>
	</c:otherwise>
</c:choose>

<c:if test="<%= showButtons %>">
	<aui:button-row>
		<aui:button cssClass="btn-lg" primary="<%= true %>" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= backURL %>" type="cancel" />
	</aui:button-row>
</c:if>

<aui:script require="metal-dom/src/dom,metal-uri/src/Uri">
	AUI().use(
		'liferay-store',
		function(A) {
			var dom = metalDomSrcDom.default;
			var uri = metalUriSrcUri.default;

			var redirectField = dom.toElement('input[name="<portlet:namespace />redirect"]');
			var tabs1Param = '<portlet:namespace /><%= tabs1Param %>';

			var updateRedirectField = function(event) {
				var redirectURL = new uri(redirectField.value);

				redirectURL.setParameterValue(tabs1Param, event.id);

				redirectField.value = redirectURL.toString();

				Liferay.Store('<portlet:namespace /><%= id %>', event.id);
			};

			var clearFormNavigatorHandles = function(event) {
				if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
					Liferay.detach('showTab', updateRedirectField);
					Liferay.detach('destroyPortlet', clearFormNavigatorHandles);
				}
			};

			if (redirectField) {
				var currentURL = new uri(document.location.href);

				var tabs1Value = currentURL.getParameterValue(tabs1Param);

				if (tabs1Value) {
					updateRedirectField(
						{
							id: tabs1Value
						}
					);
				}

				Liferay.on('showTab', updateRedirectField);
				Liferay.on('destroyPortlet', clearFormNavigatorHandles);
			}
		}
	);
</aui:script>