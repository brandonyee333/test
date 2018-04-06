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

portletURL.setParameter("mvcPath", "/admin/select_severity.jsp");

Integer[] severities = ArrayUtil.toArray(SupportResponseConstants.SEVERITIES);

String selectAll = "javascript:";
%>

<aui:form method="post" name="fm">
	<liferay-ui:tabs
		names="severities"
	/>

	<liferay-ui:search-container
		headerNames="severity"
		iteratorURL="<%= portletURL %>"
		total="<%= severities.length %>"
	>
		<liferay-ui:search-container-results
			results="<%= ListUtil.fromArray(severities) %>"
		/>

		<liferay-ui:search-container-row
			className="java.lang.Integer"
			modelVar="severity"
		>

			<%
			String severityLabel = LanguageUtil.get(request, TicketEntryConstants.getSeverityLabel(severity));

			StringBuilder sb = new StringBuilder();

			sb.append("opener.");
			sb.append(renderResponse.getNamespace());
			sb.append("selectRow('severities', '");
			sb.append(severity);
			sb.append("', '");
			sb.append(renderResponse.getNamespace());
			sb.append("severitySearchContainer', ['");
			sb.append(severityLabel);
			sb.append("']);");

			selectAll += sb.toString();

			String rowHREF = "javascript:" + sb.toString() + "window.close();";
			%>

			<liferay-ui:search-container-column-text
				href="<%= rowHREF %>"
				name="severity"
			>
				<%= severityLabel %>
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>

		<aui:button onClick='<%= selectAll + "window.close();" %>' value="select-all" />

		<br /><br />

		<liferay-ui:search-iterator
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>