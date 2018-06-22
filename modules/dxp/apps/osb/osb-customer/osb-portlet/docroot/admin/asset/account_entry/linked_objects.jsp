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
List<User> missingUsers = (List<User>)request.getAttribute("linked_objects.jsp-missingUsers");
String salesforceOpportunityAction = (String)request.getAttribute("linked_objects.jsp-salesforceOpportunityAction");
%>

<c:choose>
	<c:when test="<%= salesforceOpportunityAction.equals(Constants.UPDATE) %>">
		<c:if test="<%= (missingUsers != null) && !missingUsers.isEmpty() %>">
			<h3>
				<liferay-ui:message key="missing-users" />
			</h3>

			<%= ListUtil.toString(missingUsers, "emailAddress", "<br />") %>
		</c:if>

		<%
		List<User> newUsers = (List<User>)request.getAttribute("linked_objects.jsp-newUsers");
		%>

		<c:if test="<%= (newUsers != null) && !newUsers.isEmpty() %>">
			<h3>
				<liferay-ui:message key="new-users" />
			</h3>

			<%= ListUtil.toString(newUsers, "emailAddress", "<br />") %>
		</c:if>

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