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

<%@ include file="/support/2/init.jsp" %>

<%
PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/select_partner_entry.jsp");
%>

<c:if test="<%= liferayIncOrg %>">
	<aui:form method="post" name="fm">
		<liferay-ui:tabs names="partners" />

		<liferay-ui:search-container
			emptyResultsMessage="no-partners-were-found"
			id="partnerEntrySearchContainer"
			iteratorURL="<%= portletURL %>"
			searchContainer="<%= new PartnerEntrySearch(renderRequest, portletURL) %>"
		>

			<%
			PartnerEntryDisplayTerms displayTerms = (PartnerEntryDisplayTerms)searchContainer.getDisplayTerms();
			PartnerEntrySearchTerms searchTerms = (PartnerEntrySearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/support/2/partner_entry_search.jspf" %>

			<%@ include file="/support/2/partner_entry_search_results.jspf" %>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.PartnerEntry"
				keyProperty="partnerEntryId"
				modelVar="partnerEntry"
			>

				<%
				StringBundler sb = new StringBundler(5);

				sb.append("javascript:opener.");
				sb.append(renderResponse.getNamespace());
				sb.append("selectPartnerEntry('");
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

			<liferay-ui:search-iterator markupView="lexicon" />
		</liferay-ui:search-container>
	</aui:form>
</c:if>