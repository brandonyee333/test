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

portletURL.setParameter("mvcPath", "/admin/select_account_tier.jsp");

List<Integer> accountTiers = ListUtil.toList(AccountEntryConstants.TIERS);

String selectAll = "javascript:";
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs
		names="project-tiers"
	/>

	<liferay-ui:search-container
		headerNames="project-tier"
		iteratorURL="<%= portletURL %>"
		total="<%= accountTiers.size() %>"
	>
		<liferay-ui:search-container-results
			results="<%= accountTiers %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Integer"
			modelVar="accountTier"
		>

			<%
			StringBuilder sb = new StringBuilder();

			sb.append("opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectRow('accountTiers', '");
			sb.append(accountTier);
			sb.append("', '");
			sb.append(renderResponse.getNamespace());
			sb.append("accountTierSearchContainer', ['");
			sb.append(LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountTier)));
			sb.append("']);");

			selectAll += sb.toString();

			String rowHREF = "javascript:" + sb.toString() + "window.close();";
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="project-tier"
			>
				<%= LanguageUtil.get(request, AccountEntryConstants.getTierLabel(accountTier)) %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button onClick='<%= selectAll + "window.close();" %>' value="select-all" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
			paginate="<%= false %>"
		/>
	</liferay-ui:search-container>
</aui:form>