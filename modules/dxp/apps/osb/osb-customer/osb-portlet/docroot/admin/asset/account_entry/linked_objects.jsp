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
String salesforceOpportunityAction = (String)request.getAttribute("linked_objects.jsp-salesforceOpportunityAction");
%>

<c:choose>
	<c:when test="<%= salesforceOpportunityAction.equals(Constants.UPDATE) %>">
		<h3>
			<liferay-ui:message key="existing-orders" />
		</h3>

		<%
		long[] existingOrderEntryIds = StringUtil.split((String)request.getAttribute("linked_objects.jsp-existingOrderEntryIds"), 0l);

		for (long orderEntryId : existingOrderEntryIds) {
			PortletURL portletURL = PortletURLFactoryUtil.create(request, OSBPortletKeys.OSB_ADMIN, layout.getPlid(), PortletRequest.RENDER_PHASE);

			portletURL.setParameter("mvcPath", "/admin/edit_order_entry.jsp");
			portletURL.setParameter("orderEntryId", String.valueOf(orderEntryId));
		%>

			<aui:a href="<%= portletURL.toString() %>" label="<%= String.valueOf(orderEntryId) %>" target="_blank" /><br />

		<%
		}
		%>

		<h3>
			<liferay-ui:message key="order-summary" />
		</h3>

		<%
		String orderEntries = (String)request.getAttribute("linked_objects.jsp-orderEntries");

		JSONArray jsonArray = null;

		if (Validator.isNotNull(orderEntries)) {
			jsonArray = JSONFactoryUtil.createJSONArray(orderEntries);
		}
		%>

		<c:if test="<%= (jsonArray != null) && (jsonArray.length() > 0) %>">

			<%
			for (int i = 0; i < jsonArray.length(); i++) {
				request.setAttribute("summary.jsp-jsonObject", jsonArray.getJSONObject(i));
			%>

				<liferay-util:include page="/admin/asset/order_entry/summary.jsp" servletContext="<%= application %>" />

				<c:if test="<%= (i + 1) < jsonArray.length() %>">
					<br /><br />
				</c:if>

			<%
			}
			%>

		</c:if>
	</c:when>
	<c:otherwise>

		<%
		List<User> missingUsers = (List<User>)request.getAttribute("linked_objects.jsp-missingUsers");
		%>

		<c:if test="<%= (missingUsers != null) && !missingUsers.isEmpty() %>">
			<h3>
				<liferay-ui:message key="missing-users" />
			</h3>

			<%= ListUtil.toString(missingUsers, "emailAddress", "<br />") %>
		</c:if>

		<%
		String orderEntries = (String)request.getAttribute("linked_objects.jsp-orderEntries");

		JSONArray jsonArray = null;

		if (Validator.isNotNull(orderEntries)) {
			jsonArray = JSONFactoryUtil.createJSONArray(orderEntries);
		}
		%>

		<c:if test="<%= (jsonArray != null) && (jsonArray.length() > 0) %>">
			<h3>
				<liferay-ui:message key="linked-pending-objects" />
			</h3>

			<%
			for (int i = 0; i < jsonArray.length(); i++) {
				request.setAttribute("summary.jsp-jsonObject", jsonArray.getJSONObject(i));
			%>

				<liferay-util:include page="/admin/asset/order_entry/summary.jsp" servletContext="<%= application %>" />

				<c:if test="<%= (i + 1) < jsonArray.length() %>">
					<br /><br />
				</c:if>

			<%
			}
			%>

		</c:if>
	</c:otherwise>
</c:choose>