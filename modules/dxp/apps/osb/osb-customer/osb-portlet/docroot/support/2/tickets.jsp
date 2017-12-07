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
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");

PortalPreferences preferences = PortletPreferencesFactoryUtil.getPortalPreferences(request);

boolean notification = false;

if (!SubscriptionLocalServiceUtil.isSubscribed(OSBConstants.COMPANY_ID, user.getUserId(), BlogsEntry.class.getName(), OSBConstants.GROUP_CUSTOMER_ID)) {
	notification = true;
}

if (Validator.isNotNull(orderByCol) && Validator.isNotNull(orderByType)) {
	preferences.setValue(OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-col", orderByCol);
	preferences.setValue(OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-type", orderByType);
}
else {
	orderByCol = preferences.getValue(OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-col", "ticket");
	orderByType = preferences.getValue(OSBPortletKeys.OSB_SUPPORT, "ticket-entry-order-by-type", "desc");
}

String orderableColumns = "assignee,create-date,display-id,due-date,modified-date,status,subject";

int needsResponseCount = 0;

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/2/view.jsp");
%>

<div class="home-page <%= notification ? "has-notification" : "" %>">
	<c:if test="<%= notification %>">
		<liferay-util:include page="/support/2/lesa_announcements_notification.jsp" servletContext="<%= application %>"></liferay-util:include>
	</c:if>

	<c:choose>
		<c:when test="<%= liferayIncOrg || supportPartnerWorker %>">

			<%
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			if (supportPartnerWorker) {
				params.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_PARTNER});
				params.put("ticketWorkerUserIds", new long[] {user.getUserId()});

				needsResponseCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_WORKER_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
			}

			params.clear();

			params.put("primaryTicketWorker", new Object[] {user.getUserId(), true});

			int primaryAssignmentsCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_WORKER_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);

			params.clear();

			params.put("primaryTicketWorker", new Object[] {user.getUserId(), false});

			int auxiliaryAssignmentsCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_WORKER_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);

			params.clear();

			params.put("subscription", user.getUserId());

			int watchingCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_WORKER_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
			%>

			<div class="fixed page-title">
				<c:choose>
					<c:when test="<%= liferayIncOrg %>">
						<liferay-ui:message key="my-open-tickets" />
					</c:when>
					<c:otherwise>
						<liferay-ui:message key="my-liferay-support-tickets" />
					</c:otherwise>
				</c:choose>
			</div>

			<c:if test="<%= needsResponseCount > 0 %>">
				<div class="search-results">
					<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
						<portlet:param name="count" value="<%= String.valueOf(needsResponseCount) %>" />
						<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="first" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="id" value="needsResponseResultsList" />
						<portlet:param name="label" value="needs-response" />
						<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
						<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
						<portlet:param name="orderByCol" value="<%= orderByCol %>" />
						<portlet:param name="orderByType" value="<%= orderByType %>" />
					</liferay-util:include>

					<div id="<portlet:namespace />needsResponse">
					</div>
				</div>
			</c:if>

			<div class="search-results">
				<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
					<portlet:param name="count" value="<%= String.valueOf(primaryAssignmentsCount) %>" />
					<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
					<portlet:param name="first" value="<%= (needsResponseCount <= 0) ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
					<portlet:param name="id" value="primaryResultsList" />
					<portlet:param name="label" value="primary-assignments" />
					<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
					<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
					<portlet:param name="orderByCol" value="<%= orderByCol %>" />
					<portlet:param name="orderByType" value="<%= orderByType %>" />
				</liferay-util:include>

				<div id="<portlet:namespace />primary">
				</div>
			</div>

			<c:if test="<%= auxiliaryAssignmentsCount > 0 %>">
				<div class="search-results">
					<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
						<portlet:param name="count" value="<%= String.valueOf(auxiliaryAssignmentsCount) %>" />
						<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="id" value="auxiliaryResultsList" />
						<portlet:param name="label" value="auxiliary-assignments" />
						<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
						<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
						<portlet:param name="orderByCol" value="<%= orderByCol %>" />
						<portlet:param name="orderByType" value="<%= orderByType %>" />
					</liferay-util:include>

					<div id="<portlet:namespace />auxiliary">
					</div>
				</div>
			</c:if>

			<c:if test="<%= watchingCount > 0 %>">
				<div class="search-results">
					<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
						<portlet:param name="count" value="<%= String.valueOf(watchingCount) %>" />
						<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="id" value="watchingResultsList" />
						<portlet:param name="label" value="watching" />
						<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
						<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
						<portlet:param name="orderByCol" value="<%= orderByCol %>" />
						<portlet:param name="orderByType" value="<%= orderByType %>" />
					</liferay-util:include>

					<div id="<portlet:namespace />watching">
					</div>
				</div>
			</c:if>
		</c:when>
		<c:otherwise>

			<%
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("pendingCustomer", StringPool.BLANK);
			params.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER});

			needsResponseCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);

			params.clear();

			int openTicketsCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
			%>

			<div class="fixed page-title">
				<liferay-ui:message key="my-liferay-support-tickets" />
			</div>

			<c:if test="<%= needsResponseCount > 0 %>">
				<div class="search-results">
					<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
						<portlet:param name="count" value="<%= String.valueOf(needsResponseCount) %>" />
						<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="first" value="<%= Boolean.TRUE.toString() %>" />
						<portlet:param name="id" value="needsResponseResultsList" />
						<portlet:param name="label" value="needs-response" />
						<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
						<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
						<portlet:param name="orderByCol" value="<%= orderByCol %>" />
						<portlet:param name="orderByType" value="<%= orderByType %>" />
					</liferay-util:include>

					<div id="<portlet:namespace />needsResponse">
					</div>
				</div>
			</c:if>

			<div class="search-results">
				<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
					<portlet:param name="count" value="<%= String.valueOf(openTicketsCount) %>" />
					<portlet:param name="fade" value="<%= Boolean.TRUE.toString() %>" />
					<portlet:param name="first" value="<%= (needsResponseCount <= 0) ? Boolean.TRUE.toString() : Boolean.FALSE.toString() %>" />
					<portlet:param name="id" value="openTicketsResultsList" />
					<portlet:param name="label" value="all-open-tickets" />
					<portlet:param name="notification" value="<%= String.valueOf(notification) %>" />
					<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
					<portlet:param name="orderByCol" value="<%= orderByCol %>" />
					<portlet:param name="orderByType" value="<%= orderByType %>" />
				</liferay-util:include>

				<div id="<portlet:namespace />openTickets">
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>

<aui:script>
	function <portlet:namespace />checkOnClick(element, event) {
		var node = <portlet:namespace />getSelectionNode();

		if (node && (element != node) && element.contains(node)) {
			event.preventDefault();
			event.stopPropagation();
		}
	}

	function <portlet:namespace />columnSort(orderByCol, curOrderByCol, orderByType) {
		if (orderByCol != '<%= HtmlUtil.escape(orderByCol) %>') {
			window.location.href = '<%= portletURL %>' + '&<portlet:namespace />orderByCol=' + orderByCol + '&<portlet:namespace />orderByType=asc';
		}
		else {
			<c:choose>
				<c:when test='<%= orderByCol.equals("create-date") && orderByType.equals("desc") %>'>
					window.location.href = '<%= portletURL %>' + '&<portlet:namespace />orderByCol=' + orderByCol + '&<portlet:namespace />orderByType=asc';
				</c:when>
				<c:when test='<%= orderByType.equals("asc") %>'>
					window.location.href = '<%= portletURL %>' + '&<portlet:namespace />orderByCol=' + orderByCol + '&<portlet:namespace />orderByType=desc';
				</c:when>
				<c:otherwise>
					window.location.href = '<%= portletURL %>';
				</c:otherwise>
			</c:choose>
		}
	}

	function <portlet:namespace />getSelectionNode() {
		var selection = window.getSelection();

		var returnVal;

		if (selection.toString() != '') {
			returnVal = selection.anchorNode;
		}

		return returnVal;
	}

	function <portlet:namespace />pinElement(id, prevNode) {
		var tableColumnHeaderHeight = 0;

		if (prevNode) {
			if (prevNode.querySelector('.table')) {
				tableColumnHeaderHeight = prevNode.querySelector('.table').offsetHeight;
			}

			var offsetFromPrev = prevNode.getBoundingClientRect().bottom - tableColumnHeaderHeight;

			var element = document.getElementById('<portlet:namespace />' + id);

			var headerElement = document.getElementById('<portlet:namespace />' + id + 'PinnedHeader');

			if (element.getBoundingClientRect().top <= offsetFromPrev) {
				element.classList.add('visibility-hidden');

				headerElement.style.top = offsetFromPrev + 'px';

				headerElement.classList.remove('hide');
			}
			else {
				element.classList.remove('visibility-hidden');

				headerElement.classList.add('hide');
			}
		}
	}

	function <portlet:namespace />selectText(element, event) {
		event.stopImmediatePropagation();
		event.preventDefault();

		element.draggable = false;

		window.getSelection().selectAllChildren(element);
	}

	function <portlet:namespace />readyPinning(divId) {
		var nodes = document.querySelectorAll('.section-title.pinned');

		var prevNode;

		for (var i = 0; i < nodes.length; i++) {
			if (nodes[i].id == '<portlet:namespace />' + divId + 'PinnedHeader') {
				if ((i > 0) && (nodes.length > 1)) {
					prevNode = nodes[i - 1];
				}

				break;
			}
		}

		window.addEventListener(
			'scroll',
			function() {
				<portlet:namespace />pinElement(divId, prevNode);
			},
			false
		);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />loadTickets',
		function(divId) {
			var A = AUI();

			var ticketsURL = '';

			<portlet:renderURL copyCurrentRenderParameters="<%= false %>" var="ticketsURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString() %>">
				<portlet:param name="mvcPath" value="/support/2/advanced_search/ticket_search.jsp" />
				<portlet:param name="needsResponseCount" value="<%= String.valueOf(needsResponseCount) %>" />
				<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
				<portlet:param name="orderByCol" value="<%= orderByCol %>" />
				<portlet:param name="orderByType" value="<%= orderByType %>" />
			</portlet:renderURL>

			ticketsURL += '<%= ticketsURL %>&<portlet:namespace />divId=' + divId;

			var tabContentDiv = A.one('#<portlet:namespace />' + divId);

			A.io.request(
				ticketsURL,
				{
					on: {
						start: function() {
							tabContentDiv.html('<img src="<%= themeDisplay.getPathThemeImages() + "/aui/loading_indicator.gif" %>" style="display: block; margin: auto;" />');
						},
						success: function() {
							var response = this.get('responseData');

							tabContentDiv.html(response);

							<portlet:namespace />readyPinning(divId + 'ResultsList');
						}
					}
				}
			);
		},
		['aui-io']
	);

	<c:if test="<%= needsResponseCount > 0 %>">
		<portlet:namespace />loadTickets('needsResponse');
	</c:if>

	<c:choose>
		<c:when test="<%= liferayIncOrg || supportPartnerWorker %>">
			<portlet:namespace />loadTickets('primary');

			<portlet:namespace />loadTickets('auxiliary');

			<portlet:namespace />loadTickets('watching');
		</c:when>
		<c:otherwise>
			<portlet:namespace />loadTickets('openTickets');
		</c:otherwise>
	</c:choose>
</aui:script>