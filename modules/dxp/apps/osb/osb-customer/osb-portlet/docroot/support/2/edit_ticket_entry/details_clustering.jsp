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
TicketEntry ticketEntry = (TicketEntry)request.getAttribute(OSBWebKeys.OSB_TICKET_ENTRY);

Map<Long, String> ticketInformationFieldsMap = ticketEntry.getTicketInformationFieldsMap();

int numberOfNodes = ParamUtil.getInteger(request, "numberOfNodes", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_NUMBER_OF_NODES)));
int serverCommunicationType = ParamUtil.getInteger(request, "serverCommunicationType", GetterUtil.getInteger(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_CLUSTER_SERVER_COMMUNICATION_TYPE)));
String serverConfigurations = ParamUtil.getString(request, "serverConfigurations", GetterUtil.getString(ticketInformationFieldsMap.get(TicketInformationConstants.FIELD_SERVER_CONFIGURATIONS)));
%>

<div>
	<div class="aui-w50 content-column">
		<div class="content-column-content">
			<span class="txt-sb"><liferay-ui:message key="server-communication-type" />:</span>

			<liferay-ui:message key="<%= TicketEntryConstants.getClusterServerCommunicationTypeLabel(serverCommunicationType) %>" />
		</div>
	</div>

	<div class="aui-w50 content-column">
		<div class="content-column-content">
			<span class="txt-sb"><liferay-ui:message key="number-of-nodes" />:</span>

			<c:choose>
				<c:when test="<%= numberOfNodes < 8 %>">
					<%= (numberOfNodes > 1) ? numberOfNodes : StringPool.BLANK %>
				</c:when>
				<c:otherwise>
					<%= numberOfNodes %>+
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<div>
	<span class="txt-sb"><liferay-ui:message key="jvm-arguments-settings-optional" />:</span>

	<br />

	<pre><%= SupportUtil.getHTML(serverConfigurations) %></pre>
</div>