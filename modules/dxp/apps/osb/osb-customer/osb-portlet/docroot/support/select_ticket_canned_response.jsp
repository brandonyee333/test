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

<%@ include file="/support/init.jsp" %>

<%
String languageId = LanguageUtil.getLanguageId(request);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/select_ticket_canned_response.jsp");
%>

<aui:form action="<%= portletURL.toString() %>" class="canned-response-results" method="post" name="fm" onSubmit="submitForm(this); return false;">
	<div class="unit">
		<div class="unit-content">
			<liferay-ui:tabs names="canned-responses" />

			<liferay-ui:search-container
				searchContainer="<%= new TicketCannedResponseSearch(renderRequest, portletURL) %>"
			>

				<%
				TicketCannedResponseDisplayTerms displayTerms = (TicketCannedResponseDisplayTerms)searchContainer.getDisplayTerms();
				%>

				<aui:select inlineField="true" label="language" name="languageId" onChange='<%= renderResponse.getNamespace() + "updateLanguage();" %>'>

					<%
					Locale[] locales = LanguageUtil.getAvailableLocales();

					for (int i = 0; i < locales.length; i++) {
					%>

						<aui:option label="<%= locales[i].getDisplayName(locale) %>" selected="<%= languageId.equals(LocaleUtil.toLanguageId(locales[i])) %>" value="<%= LocaleUtil.toLanguageId(locales[i]) %>" />

					<%
					}
					%>

				</aui:select>

				<br /><br />

				<%@ include file="/support/ticket_canned_response_search.jspf" %>

				<liferay-ui:search-container-results>
					<%@ include file="/support/ticket_canned_response_search_results.jspf" %>
				</liferay-ui:search-container-results>

				<liferay-ui:search-container-row
					className="com.liferay.osb.model.TicketCannedResponse"
					keyProperty="ticketCannedResponseId"
					modelVar="ticketCannedResponse"
				>

					<%
					String name = ticketCannedResponse.getName(languageId);
					String content = ticketCannedResponse.getContent(languageId);

					StringBuilder sb = new StringBuilder();

					sb.append("javascript:opener.");
					sb.append(renderResponse.getNamespace());
					sb.append("selectCannedResponse('");
					sb.append(ticketCannedResponse.getTicketCannedResponseId());
					sb.append("', '");
					sb.append(UnicodeFormatter.toString(content));
					sb.append("'); window.close();");

					String rowHREF = sb.toString();
					%>

					<liferay-ui:search-container-column-text
						name="canned-response"
					>
						<a href="<%= rowHREF.toString() %>"><strong><%= HtmlUtil.escape(name) %></strong></a>

						<br /><br />

						<%= SupportUtil.getHTML(content) %>
					</liferay-ui:search-container-column-text>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>
	</div>
</aui:form>

<aui:script>
	function <portlet:namespace />updateLanguage() {
		document.<portlet:namespace />fm.submit();
	}
</aui:script>