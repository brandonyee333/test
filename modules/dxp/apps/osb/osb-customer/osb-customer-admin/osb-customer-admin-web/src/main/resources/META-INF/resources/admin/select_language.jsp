<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
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
			%>

			<liferay-ui:search-container-column-text
				href='<%= "javascript:" + sb.toString() + "window.close();" %>'
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