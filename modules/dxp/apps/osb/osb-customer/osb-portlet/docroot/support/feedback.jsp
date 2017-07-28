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
String filter = ParamUtil.getString(request, "filter");

LinkedHashMap<String, Object> userParams = new LinkedHashMap<String, Object>();

userParams.put("partnerWorker", Long.valueOf(user.getUserId()));
userParams.put("status", AccountEntryConstants.STATUSES_ACTIVE);

boolean ticketWorker = liferayIncOrg || (AccountEntryLocalServiceUtil.searchCount(null, userParams) > 0);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/view.jsp");
portletURL.setParameter("tabs1", "feedback");
portletURL.setParameter("filter", filter);

PortletURL waitingURL = renderResponse.createRenderURL();

waitingURL.setParameter("mvcPath", "/support/view.jsp");
waitingURL.setParameter("tabs1", "feedback");
waitingURL.setParameter("filter", "waiting");

PortletURL allFeedbackURL = renderResponse.createRenderURL();

allFeedbackURL.setParameter("mvcPath", "/support/view.jsp");
allFeedbackURL.setParameter("tabs1", "feedback");

PortletURL searchURL = renderResponse.createRenderURL();

searchURL.setParameter("mvcPath", "/support/view.jsp");
searchURL.setParameter("tabs1", "feedback");

pageContext.setAttribute("searchURL", searchURL);
%>

<div class="aui-helper-clearfix aui-w100">
	<div class="aui-w50 content-column">
		<div class="content-column-content left-column">
			<a class="callout-a display-b paragraph" href="<%= waitingURL.toString() %>">
				<div class="callout-content ha-c">
					<div class="display-ib">
						<div class="txt-b">
							<liferay-ui:message key="waiting" />
						</div>

						<div class="txt-b txt-h1">

							<%
							Calendar cal = Calendar.getInstance();

							cal.add(Calendar.DATE, -30);
							%>

							<%= TicketFeedbackServiceUtil.searchCount(null, cal.get(Calendar.DATE), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null, null, new LinkedHashMap<String, Object>(), true) %>
						</div>
					</div>
				</div>
			</a>
		</div>
	</div>

	<div class="aui-w50 content-column">
		<div class="content-column-content right-column">
			<a class="callout-a display-b paragraph" href="<%= allFeedbackURL.toString() %>">
				<div class="callout-content ha-c">
					<div class="display-ib">
						<div class="txt-b">
							<liferay-ui:message key="all-feedback" />
						</div>

						<div class="txt-b txt-h1">
							<%= TicketFeedbackServiceUtil.searchCount(null, new LinkedHashMap<String, Object>()) %>
						</div>
					</div>
				</div>
			</a>
		</div>
	</div>
</div>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<input name="<portlet:namespace />classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(TicketFeedback.class.getName()) %>" />

	<div class="feedback-results table-report">
		<liferay-ui:search-container
			searchContainer="<%= new TicketFeedbackSearch(renderRequest, portletURL) %>"
		>

			<%
			TicketFeedbackDisplayTerms displayTerms = (TicketFeedbackDisplayTerms)searchContainer.getDisplayTerms();
			TicketFeedbackSearchTerms searchTerms = (TicketFeedbackSearchTerms)searchContainer.getSearchTerms();
			%>

			<%@ include file="/support/ticket_feedback_search.jspf" %>

			<liferay-ui:search-container-results>
				<%@ include file="/support/ticket_feedback_search_results.jspf" %>
			</liferay-ui:search-container-results>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.TicketFeedback"
				escapedModel="<%= true %>"
				keyProperty="ticketFeedbackId"
				modelVar="ticketFeedback"
			>

				<%
				TicketEntry ticketEntry = ticketFeedback.getTicketEntry();
				%>

				<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketFeedback.getTicketEntryId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text name="ticket">
					<a class="display-b btn-a v<%= ticketEntry.getSeverity() %>" href="<%= rowURL %>"><%= ticketEntry.getDisplayId() %></a>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="create-date"
				>
					<div>
						<%= longDateFormatDate.format(ticketFeedback.getCreateDate()) %>
					</div>

					<div class="fs-9">
						<%= longDateFormatTime.format(ticketFeedback.getCreateDate()) %>
					</div>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="satisfied"
				>
					<liferay-ui:message key="<%= ticketFeedback.getSatisfiedLabel() %>" />
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="average-rating"
					value="<%= ticketFeedback.getAverageRatingDisplay() %>"
				/>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="comments"
				>
					<c:choose>
						<c:when test="<%= (ticketFeedback.getStatus() == TicketFeedbackConstants.STATUS_ANSWERED) || (ticketFeedback.getStatus() == TicketFeedbackConstants.STATUS_CLOSED) %>">
							<%= StringUtil.shorten(ticketFeedback.getComments(), 200) %>
						</c:when>
						<c:otherwise>
							N/A
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					href="<%= rowURL %>"
					name="status"
				>
					<c:choose>
						<c:when test="<%= ticketFeedback.isClosed() %>">
							<liferay-ui:message key="closed" />
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="<%= ticketFeedback.getStatus() == TicketFeedbackConstants.STATUS_UNANSWERED %>">
									<liferay-ui:message key="waiting" />
								</c:when>
								<c:otherwise>
									<liferay-ui:message key="responded" />
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator />
		</liferay-ui:search-container>
	</div>
</aui:form>