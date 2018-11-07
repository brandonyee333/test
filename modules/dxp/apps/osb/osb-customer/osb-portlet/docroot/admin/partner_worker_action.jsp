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
ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

PartnerWorker partnerWorker = (PartnerWorker)row.getObject();

PortletURL portletURL = (PortletURL)request.getAttribute("edit_partner_entry_workers.jsp-portletURL");
%>

<liferay-ui:icon-menu>
	<liferay-ui:icon
		message='<%= (partnerWorker.getPartnerWorkerId() > 0) ? "update" : "add" %>'
		url='<%= "javascript:" + renderResponse.getNamespace() + "updatePartnerWorker(" + partnerWorker.getPartnerWorkerId() + ");" %>'
	/>

	<c:if test="<%= partnerWorker.getPartnerWorkerId() > 0 %>">
		<portlet:actionURL name="deletePartnerWorker" var="deletePartnerWorkerURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
			<portlet:param name="partnerWorkerId" value="<%= String.valueOf(partnerWorker.getPartnerWorkerId()) %>" />
		</portlet:actionURL>

		<liferay-ui:icon-delete
			message="remove"
			url="<%= deletePartnerWorkerURL %>"
		/>
	</c:if>
</liferay-ui:icon-menu>