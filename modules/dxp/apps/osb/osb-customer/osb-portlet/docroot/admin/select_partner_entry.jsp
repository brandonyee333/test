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

portletURL.setParameter("mvcPath", "/admin/select_partner_entry.jsp");
%>

<aui:form action="<%= portletURL.toString() %>" method="post">
	<liferay-ui:tabs
		names="partners"
	/>

	<liferay-ui:search-container
		searchContainer="<%= new PartnerEntrySearch(renderRequest, portletURL) %>"
	>

		<%
		PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
		PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
		%>

		<%@ include file="/admin/partner_entry_search.jspf" %>

		<liferay-ui:search-container-results>
			<%@ include file="/admin/partner_entry_search_results.jspf" %>
		</liferay-ui:search-container-results>

		<liferay-ui:search-container-row
			className="com.liferay.osb.model.PartnerEntry"
			keyProperty="partnerEntryId"
			modelVar="partnerEntry"
		>

			<%
			StringBundler sb = new StringBundler(7);

			sb.append("javascript:opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectPartnerEntry('");
			sb.append(partnerEntry.getPartnerEntryId());
			sb.append("', '");
			sb.append(UnicodeFormatter.toString(partnerEntry.getCode()));
			sb.append("'); window.close();");

			String rowHREF = sb.toString();
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="code"
				value="<%= HtmlUtil.escape(partnerEntry.getCode()) %>"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>