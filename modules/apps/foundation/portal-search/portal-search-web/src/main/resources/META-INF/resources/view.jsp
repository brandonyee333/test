<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<%
long groupId = ParamUtil.getLong(request, SearchPortletParameterNames.GROUP_ID);

boolean scopeEverything = groupId == 0;

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/search.jsp");
portletURL.setParameter("redirect", currentURL);
portletURL.setPortletMode(PortletMode.VIEW);
portletURL.setWindowState(WindowState.MAXIMIZED);

pageContext.setAttribute("portletURL", portletURL);
%>

<aui:form action="<%= portletURL %>" method="get" name="fm" onSubmit='<%= renderResponse.getNamespace() + "search(); event.preventDefault();" %>'>
	<liferay-portlet:renderURLParams varImpl="portletURL" />

	<aui:fieldset>
		<aui:input cssClass="search-input" inlineField="<%= true %>" label="" name="keywords" placeholder="search" size="30" title="search" type="text" value="<%= HtmlUtil.escapeAttribute(searchDisplayContext.getKeywords()) %>" />

		<button style="display: none;" type="submit"></button>

		<%
		String taglibOnClick = "Liferay.Util.focusFormField('#" + renderResponse.getNamespace() + "keywords');";
		%>

		<liferay-ui:quick-access-entry
			label="skip-to-search"
			onClick="<%= taglibOnClick %>"
		/>

		<c:choose>
			<c:when test="<%= searchDisplayContext.isSearchScopePreferenceLetTheUserChoose() %>">
				<aui:select cssClass="search-select" inlineField="<%= true %>" label="" name="scope" title="scope">
					<c:if test="<%= searchDisplayContext.isSearchScopePreferenceEverythingAvailable() %>">
						<aui:option label="everything" selected="<%= scopeEverything %>" value="everything" />
					</c:if>

					<aui:option label="this-site" selected="<%= !scopeEverything %>" value="this-site" />
				</aui:select>
			</c:when>
			<c:otherwise>
				<aui:input name="scope" type="hidden" value="<%= searchDisplayContext.getSearchScopeParameterString() %>" />
			</c:otherwise>
		</c:choose>

		<aui:field-wrapper inlineField="<%= true %>">
			<liferay-ui:icon
				cssClass="icon-monospaced"
				icon="search"
				linkTitle='<%= LanguageUtil.get(request, "search") %>'
				markupView="lexicon"
				onClick='<%= renderResponse.getNamespace() + "search();" %>'
				url="javascript:;"
			/>
		</aui:field-wrapper>
	</aui:fieldset>

	<aui:script>
		function <portlet:namespace />search() {
			var keywords = document.<portlet:namespace />fm.<portlet:namespace />keywords.value;

			keywords = keywords.replace(/^\s+|\s+$/, '');

			if (keywords != '') {
				submitForm(document.<portlet:namespace />fm);
			}
		}
	</aui:script>
</aui:form>