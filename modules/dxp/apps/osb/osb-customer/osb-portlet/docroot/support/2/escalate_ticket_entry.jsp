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

<c:choose>
	<c:when test="<%= SessionMessages.contains(renderRequest, PortalUtil.getPortletId(renderRequest) + SessionMessages.KEY_SUFFIX_REFRESH_PORTLET) %>">
		<aui:script>
			window.close();
		</aui:script>
	</c:when>
	<c:otherwise>

		<%
		long ticketEntryId = ParamUtil.getLong(request, "ticketEntryId");

		int visibility = VisibilityConstants.WORKERS;

		if (liferayIncOrg) {
			visibility = VisibilityConstants.LIFERAY_INC;
		}
		%>

		<portlet:actionURL name="escalateTicketEntry" var="escalateTicketEntryURL">
			<portlet:param name="mvcPath" value="/support/2/escalate_ticket_entry.jsp" />
			<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntryId) %>" />
			<portlet:param name="visibility" value="<%= String.valueOf(visibility) %>" />
		</portlet:actionURL>

		<aui:form action="<%= escalateTicketEntryURL %>" method="post" name="fm6">
			<div class="unit">
				<h1 class="section-heading">
					<liferay-ui:message key="escalation-details" />
				</h1>

				<div>
					<textarea id="<portlet:namespace />body" name="<portlet:namespace />body" style="height: 250px; width: 750px;" wrap="soft"><%= SupportUtil.getEscalationDetails() %></textarea>
				</div>

				<div>
					<input class="aui-button-input" type="submit" value="<liferay-ui:message key="escalate" />" />

					<input class="aui-button-input" onClick="window.close();" type="button" value="<liferay-ui:message key="cancel" />" />
				</div>
			</div>
		</aui:form>
	</c:otherwise>
</c:choose>