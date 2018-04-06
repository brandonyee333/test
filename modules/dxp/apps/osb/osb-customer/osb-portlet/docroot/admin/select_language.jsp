<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/select_language.jsp");

String[] languageIds = AccountEntryConstants.LANGUAGES;

String selectAll = "javascript:";
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs
		names="languages"
	/>

	<liferay-ui:search-container
		headerNames="language"
		iteratorURL="<%= portletURL %>"
		total="<%= languageIds.length %>"
	>
		<liferay-ui:search-container-results
			results="<%= ListUtil.fromArray(languageIds) %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.String"
			modelVar="languageId"
		>

			<%
			String languageLabel = LanguageUtil.get(request, AccountEntryConstants.getLanguageLabel(languageId));

			StringBuilder sb = new StringBuilder();

			sb.append("opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectRow('languageIds', '");
			sb.append(languageId);
			sb.append("', '");
			sb.append(renderResponse.getNamespace());
			sb.append("languageSearchContainer', ['");
			sb.append(languageLabel);
			sb.append("']);");

			selectAll += sb.toString();

			String rowHREF = "javascript:" + sb.toString() + "window.close();";
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="language"
			>
				<%= languageLabel %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button onClick='<%= selectAll + "window.close();" %>' value="select-all" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>