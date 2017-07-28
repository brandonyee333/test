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
long accountEntryId = ParamUtil.getLong(request, "accountEntryId");
long partnerEntryId = ParamUtil.getLong(request, "partnerEntryId");
String searchFilterBy = ParamUtil.getString(request, "searchFilterBy");
long searchFilterId = ParamUtil.getLong(request, "searchFilterId");

AccountEntry accountEntry = null;

if (accountEntryId > 0) {
	accountEntry = AccountEntryServiceUtil.getAccountEntry(accountEntryId);
}

LinkedHashMap<String, Object> partnerEntryParams = new LinkedHashMap<String, Object>();

PartnerEntry partnerEntry = null;

if (partnerEntryId > 0) {
	partnerEntry = PartnerEntryServiceUtil.getPartnerEntry(partnerEntryId);

	long[] partnerEntryIds = SupportUtil.getPartnerEntryIds(partnerEntryId);

	partnerEntryParams.put("partnerEntryIds", partnerEntryIds);
}

boolean consultantWorker = false;

List<AccountWorker> accountWorkers = AccountWorkerLocalServiceUtil.getUserAccountWorkers(user.getUserId());

for (AccountWorker accountWorker : accountWorkers) {
	if ((accountWorker.getRole() == AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) || (accountWorker.getRole() == AccountWorkerConstants.ROLE_MANAGED_SERVICES) || (accountWorker.getRole() == AccountWorkerConstants.ROLE_SALES)) {
		consultantWorker = true;

		break;
	}
}

boolean ticketWorker = liferayIncOrg || supportPartnerWorker;

LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
LinkedHashMap<String, Object> needResponseParams = new LinkedHashMap<String, Object>();

if (ticketWorker) {
	params.put("ticketWorkerUserIds", new long[] {user.getUserId()});
	needResponseParams.put("ticketWorkerUserIds", new long[] {user.getUserId()});

	if (liferayIncOrg) {
		needResponseParams.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_LIFERAY});
	}
	else {
		needResponseParams.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_PARTNER});
	}
}
else {
	needResponseParams.put("pendingCustomer", StringPool.BLANK);
	needResponseParams.put("pendingTypes", new int[] {TicketFlagConstants.TYPE_PENDING_CUSTOMER});
}

int myReportedTicketsCount = 0;

if (consultantWorker) {
	myReportedTicketsCount = TicketEntryServiceUtil.searchCount(user.getUserId(), null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
}

int openTicketsCount = 0;
int needResponseCount = 0;
int inProgressCount = 0;

if (ticketWorker) {
	openTicketsCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_WORKER_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
	needResponseCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, needResponseParams, true);
	inProgressCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_IN_PROGRESS, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
}
else {
	openTicketsCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, params, true);
	needResponseCount = TicketEntryServiceUtil.searchCount(0, null, new int[0], null, 0, 0, 0, 0, 0, 0, null, null, null, TicketEntryConstants.STATUSES_OPEN, new int[0], new int[0], new int[0], new long[0], new long[0], new long[0], new long[0], new long[0], new int[0], new int[0], 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, needResponseParams, true);
}

Calendar cal = Calendar.getInstance();

cal.add(Calendar.DATE, -30);

int feedbacksWaitingCount = 0;

if (!ticketWorker) {
	feedbacksWaitingCount = TicketFeedbackServiceUtil.searchCount(null, cal.get(Calendar.DATE), cal.get(Calendar.MONTH), cal.get(Calendar.YEAR), 0, 0, 0, 0, 0, 0, 0, 0, 0, null, null, TicketFeedbackConstants.STATUS_UNANSWERED, null, null, null, null, new LinkedHashMap<String, Object>(), true);
}

boolean clockedIn = SupportWorkerLocalServiceUtil.isClockedIn(user.getUserId());

boolean supportEngineer = SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_WATCHER);

boolean overlay = false;

if (!clockedIn && liferayIncOrg && supportEngineer) {
	overlay = true;
}

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/support/view.jsp");
portletURL.setParameter("tabs1", "tickets");
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
portletURL.setParameter("partnerEntryId", String.valueOf(partnerEntryId));
portletURL.setParameter("searchFilterBy", searchFilterBy);

PortletURL searchURL = renderResponse.createActionURL();

searchURL.setParameter("mvcPath", "/support/view.jsp");
searchURL.setParameter(ActionRequest.ACTION_NAME, "search");

pageContext.setAttribute("searchURL", searchURL);
%>

<div class="aui-helper-clearfix tickets-top">
	<c:if test="<%= !overlay %>">
		<portlet:renderURL var="addTicketURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
			<portlet:param name="mvcPath" value="/support/add_ticket_entry.jsp" />
		</portlet:renderURL>

		<div class="add-ticket">
			<a class="btn-d support-btn" href="<%= addTicketURL %>">
				<div class="txt-b">
					<span class="plus-sign">+</span><liferay-ui:message key="new-ticket" />
				</div>
			</a>
		</div>
	</c:if>

	<div>
		<%@ include file="/support/lesa_feedback.jspf" %>
	</div>
</div>

<div class="aui-helper-clearfix aui-w100">
	<c:if test="<%= consultantWorker %>">
		<div class="aui-w25 content-column">
			<div class="content-column-content left-column">
				<a class="callout-a display-b paragraph" href="javascript:<portlet:namespace />searchTickets('myReported');">
					<div class="callout-content ha-c">
						<div class="display-ib">
							<div class="txt-b">
								<liferay-ui:message key="my-reported-tickets" />
							</div>

							<div class="txt-b txt-h1">
								<%= myReportedTicketsCount %>
							</div>
						</div>
					</div>
				</a>
			</div>
		</div>
	</c:if>

	<div class="aui-w<%= consultantWorker ? "25" : "33" %> content-column">
		<div class="content-column-content <%= consultantWorker ? "middle" : "left" %>-column">
			<a class="callout-a display-b paragraph" href="javascript:<portlet:namespace />searchTickets('<%= ticketWorker ? "myOpen" : "open" %>');">
				<div class="callout-content ha-c">
					<div class="display-ib">
						<div class="txt-b">
							<liferay-ui:message key="my-open-tickets" />
						</div>

						<div class="txt-b txt-h1">
							<%= openTicketsCount %>
						</div>
					</div>
				</div>
			</a>
		</div>
	</div>

	<div class="aui-w<%= consultantWorker ? "25" : "33" %> content-column">
		<div class="content-column-content middle-column">
			<a class="callout-a display-b paragraph" href="javascript:<portlet:namespace />searchTickets('<%= ticketWorker ? "myNeedResponse" : "answered" %>');">
				<div class="callout-content ha-c">
					<div class="display-ib">
						<div class="txt-b">
							<liferay-ui:message key="need-response" />
						</div>

						<div class="txt-b txt-h1">
							<%= needResponseCount %>
						</div>
					</div>
				</div>
			</a>
		</div>
	</div>

	<div class="aui-w<%= consultantWorker ? "25" : "33" %> content-column">
		<div class="content-column-content right-column">
			<c:choose>
				<c:when test="<%= ticketWorker %>">
					<a class="callout-a display-b paragraph" href="javascript:<portlet:namespace />searchTickets('inProgress');">
						<div class="callout-content ha-c">
							<div class="display-ib">
								<div class="txt-b">
									<liferay-ui:message key="in-progress" />
								</div>

								<div class="txt-b txt-h1">
									<%= inProgressCount %>
								</div>
							</div>
						</div>
					</a>
				</c:when>
				<c:otherwise>
					<a class="callout-a display-b paragraph" href="javascript:<portlet:namespace />searchTickets('feedbacksWaiting');">
						<div class="callout-content ha-c">
							<div class="display-ib">
								<div class="txt-b">
									<liferay-ui:message key="feedback-waiting" />
								</div>

								<div class="txt-b txt-h1">
									<%= feedbacksWaitingCount %>
								</div>
							</div>
						</div>
					</a>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>

<aui:form action="<%= searchURL.toString() %>" method="get" name="fm" onSubmit="submitForm(this); return false;">
	<liferay-portlet:renderURLParams varImpl="searchURL" />
	<input name="<portlet:namespace />classNameId" type="hidden" value="<%= PortalUtil.getClassNameId(TicketEntry.class.getName()) %>" />
	<input name="<portlet:namespace />searchFilterBy" type="hidden" />
	<input name="<portlet:namespace />ticketWorker" type="hidden" value="<%= ticketWorker %>" />

	<liferay-ui:error exception="<%= SearchFilterNameException.class %>" message="please-enter-a-valid-name" />

	<div class="ticket-results">
		<liferay-ui:search-container
			searchContainer="<%= new TicketEntrySearch(renderRequest, portletURL) %>"
		>

			<%
			TicketEntryDisplayTerms displayTerms = (TicketEntryDisplayTerms)searchContainer.getDisplayTerms();
			TicketEntrySearchTerms searchTerms = (TicketEntrySearchTerms)searchContainer.getSearchTerms();

			String orderByCol = searchContainer.getOrderByCol();
			String orderByType = searchContainer.getOrderByType();
			%>

			<liferay-ui:search-container-results>
				<c:choose>
					<c:when test='<%= (searchTerms.isAdvancedSearch() && searchTerms.isDatabaseSearch()) || searchFilterBy.equals("feedbacksWaiting") %>'>
						<%@ include file="/support/ticket_entry_search_results_database.jspf" %>
					</c:when>
					<c:otherwise>
						<%@ include file="/support/ticket_entry_search_results_index.jspf" %>
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-results>

			<%@ include file="/support/ticket_entry_search.jspf" %>

			<div class="sort-by-wrapper">
				<span class="sort-by-text"><liferay-ui:message key="sort-by" />:</span>

				<select id="<portlet:namespace />orderByCol" name="<portlet:namespace />orderByCol" onChange="<portlet:namespace />updateSortBy();">
					<option <%= orderByCol.equals("create-date") ? "selected" : "" %> value="create-date"><liferay-ui:message key="create-date" /></option>
					<option <%= orderByCol.equals("due-date") ? "selected" : "" %> value="due-date"><liferay-ui:message key="due-date" /></option>
					<option <%= orderByCol.equals("status") ? "selected" : "" %> value="status"><liferay-ui:message key="status" /></option>
					<option <%= orderByCol.equals("display-id") ? "selected" : "" %> value="display-id"><liferay-ui:message key="ticket-name" /></option>
				</select>

				<select id="<portlet:namespace />orderByType" name="<portlet:namespace />orderByType" onChange="<portlet:namespace />updateSortBy();">
					<option <%= orderByType.equals("asc") ? "selected" : "" %> value="asc"><liferay-ui:message key="ascending" /></option>
					<option <%= orderByType.equals("desc") ? "selected" : "" %> value="desc"><liferay-ui:message key="descending" /></option>
				</select>
			</div>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.TicketEntry"
				keyProperty="ticketEntryId"
				modelVar="ticketEntry"
			>

				<%
				User ticketWorkerUser = null;

				TicketWorker curTicketWorker = TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(ticketEntry.getTicketEntryId());

				if (curTicketWorker != null) {
					ticketWorkerUser = UserLocalServiceUtil.getUser(curTicketWorker.getUserId());
				}

				String rowCssClass = StringPool.BLANK;

				if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) || (ticketEntry.getResolution() == TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER)) {
					if ((liferayIncOrg && ticketEntry.isPendingLiferay()) || (supportPartnerWorker && ticketEntry.isPendingPartner()) || (!ticketWorker && ticketEntry.isPendingCustomer())) {
						rowCssClass = "need-response";
					}
				}
				%>

				<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
					<portlet:param name="mvcPath" value="/support/edit_ticket_entry.jsp" />
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
				</liferay-portlet:renderURL>

				<liferay-ui:search-container-column-text
					cssClass='<%= " ticket-severity v" + ticketEntry.getSeverity() %>'
				>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass='<%= rowCssClass + " ticket-icon" %>'
				>
					<c:choose>
						<c:when test="<%= ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED %>">

							<%
							List<TicketFeedback> liferayTicketFeedbacks = TicketFeedbackLocalServiceUtil.getTicketFeedbacks(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_LIFERAY, TicketFeedbackConstants.STATUS_ANSWERED);
							%>

							<c:if test="<%= liferayTicketFeedbacks.isEmpty() %>">
								<liferay-ui:icon
									alt="feedback"
									image="lesa_message"
								/>
							</c:if>
						</c:when>
						<c:when test="<%= liferayIncOrg %>">

							<%
							TicketFeedback partnerTicketFeedback = TicketFeedbackServiceUtil.fetchFirstTicketFeedback(ticketEntry.getTicketEntryId(), TicketFeedbackConstants.SUBJECT_PARTNER);
							%>

							<c:if test="<%= (partnerTicketFeedback != null) && !partnerTicketFeedback.isClosed() && (partnerTicketFeedback.getStatus() == TicketFeedbackConstants.STATUS_UNANSWERED) %>">
								<img src="<%= PortalUtil.getPathContext(request) %>/support/images/partner_message.png" />
							</c:if>
						</c:when>
					</c:choose>

					<%
					boolean showLiferayCommentFlag = false;

					if (TicketFlagLocalServiceUtil.hasTicketFlag(ticketEntry.getTicketEntryId(), TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD, TicketFlagConstants.FLAG_TRUE)) {
						TicketWorker latestTicketWorker = TicketWorkerLocalServiceUtil.fetchLatestTicketWorker(ticketEntry.getTicketEntryId());

						if (((latestTicketWorker != null) && (latestTicketWorker.getUserId() == user.getUserId())) || SupportWorkerLocalServiceUtil.hasSupportWorker(user.getUserId(), SupportWorkerConstants.ROLE_MANAGER, ticketEntry.getSupportRegionId(), null) || SupportWorkerLocalServiceUtil.isManagerOfWorker(user.getUserId(), latestTicketWorker.getUserId())) {
							showLiferayCommentFlag = true;
						}
					}
					%>

					<c:if test="<%= showLiferayCommentFlag %>">
						<span class="exclamation-notification" id="<portlet:namespace />liferayCommentNotification">!</span>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="<%= rowCssClass %>"
					href="<%= rowURL %>"
				>
					<span class="ticket-title">
						<%= ticketEntry.getDisplayId() %>
					</span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="<%= rowCssClass %>"
					href="<%= rowURL %>"
				>
					<p class="ticket-detail" title="<%= HtmlUtil.escapeAttribute(ticketEntry.getSubject()) %>">
						<%= HtmlUtil.escape(ticketEntry.getSubject()) %>
					</p>

					<span class="ticket-information">
						<span class="ticket-description ticket-status">
							<div class="btn-d">
								<%= LanguageUtil.get(pageContext, ticketEntry.getStatusLabel()) %>

								<c:if test="<%= ticketEntry.getResolution() > 0 %>">
									: <%= LanguageUtil.get(pageContext, ticketEntry.getResolutionLabel()) %>
								</c:if>
							</div>
						</span>

						<c:if test="<%= !screenShareMode && ticketWorker %>">
							<span class="ticket-description ticket-escalation">
								<span class="ticket-label">
									<liferay-ui:message key="escalation" />:
								</span>

								<%= ticketEntry.getEscalationLevelLabel() %>
							</span>
						</c:if>
					</span>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="<%= rowCssClass %>"
					href="<%= rowURL %>"
				>
					<c:if test="<%= ticketWorkerUser != null %>">
						<div class="assignee user-avatar" style="background-image: url('<%= ticketWorkerUser.getPortraitURL(themeDisplay) %>&height=40&width=40')"></div>
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass="<%= rowCssClass %>"
					href="<%= rowURL %>"
				>
					<c:if test="<%= !screenShareMode && ticketWorker %>">
						<span class="ticket-assignee ticket-description">
							<c:if test="<%= ticketWorkerUser != null %>">
								<%= HtmlUtil.escape(ticketWorkerUser.getFullName()) %>
							</c:if>
						</span>
					</c:if>

					<c:choose>
						<c:when test="<%= !screenShareMode && ticketWorker %>">
							<span class="ticket-date ticket-description">
								<span class="ticket-label">
									<liferay-ui:message key="due" />:
								</span>
								<span>
									<c:choose>
										<c:when test="<%= ticketEntry.getComponent() != TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO %>">
											<%= shortDateFormatDate.format(ticketEntry.getDueDate()) %>
										</c:when>
										<c:otherwise>
											<%= TicketEntryConstants.NOT_AVAILABLE %>
										</c:otherwise>
									</c:choose>
								</span>
							</span>
						</c:when>
						<c:otherwise>
							<span class="ticket-date ticket-description">
								<span class="ticket-label">
									<liferay-ui:message key="created" />:
								</span>
								<span>
									<%= shortDateFormatDate.format(ticketEntry.getCreateDate()) %>
								</span>
							</span>
						</c:otherwise>
					</c:choose>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					cssClass='<%= rowCssClass + " ticket-icon" %>'
					href="<%= rowURL %>"
				>

					<%
					int[] userVisibilities = TicketEntryLocalServiceUtil.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
					%>

					<c:if test="<%= ticketEntry.getTicketAttachmentsCount(userVisibilities) > 0 %>">
						<liferay-ui:icon
							alt="attachment"
							image="clip"
						/>
					</c:if>
				</liferay-ui:search-container-column-text>
			</liferay-ui:search-container-row>

			<c:if test="<%= ticketWorker && (accountEntry != null) %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="the-tickets-of-project" />: <%= HtmlUtil.escape(accountEntry.getName()) %>
				</div>
			</c:if>

			<c:if test="<%= ticketWorker && (partnerEntry != null) %>">
				<div class="portlet-msg-info">
					<liferay-ui:message key="the-tickets-of-partner" />: <%= HtmlUtil.escape(partnerEntry.getCode()) %>
				</div>
			</c:if>

			<div class="table-report">
				<liferay-ui:search-iterator />
			</div>
		</liferay-ui:search-container>
	</div>
</aui:form>

<c:if test="<%= overlay %>">
	<div class="overlay" id="overlay"><div><liferay-ui:message key="you-are-not-clocked-in-and-will-not-be-assigned-tickets-to-change-this-setting-please-contact-your-support-manager" /></div></div>
</c:if>

<aui:script>
	<c:if test="<%= overlay %>">
		Liferay.on(
			"allPortletsReady",
			function() {
				var A = AUI();

				var portletContainer = A.one(".osb-portlet-support");

				var ticketsTop = A.one(".tickets-top");

				var overlay = A.one("#overlay");

				overlay.setStyle("top", ticketsTop.get("offsetTop") - 10);
				overlay.setStyle("height", portletContainer.get("offsetHeight"));
				overlay.setStyle("width", portletContainer.get("offsetWidth"));
			}
		);
	</c:if>

	function <portlet:namespace />searchTickets(filterBy) {
		if (filterBy == 'answered') {
			<portlet:namespace />resetForm();

			document.<portlet:namespace />fm.<portlet:namespace />searchFilterBy.value = 'answered';
		}
		else if (filterBy == 'feedbacksWaiting') {
			<portlet:namespace />resetForm();

			document.<portlet:namespace />fm.<portlet:namespace />searchFilterBy.value = 'feedbacksWaiting';
		}
		else if (filterBy == 'myReported') {
			<portlet:namespace />resetForm();

			document.<portlet:namespace />fm.<portlet:namespace />searchFilterBy.value = 'myReportedBy';
		}
		else {
			<portlet:namespace />updateSearch(filterBy);
		}

		var A = AUI();

		var advancedSearch = A.one("#<portlet:namespace />toggle_id_ticket_entry_searchadvancedSearch");

		if (advancedSearch.val() == 'false') {
			advancedSearch.val('true');

			var advancedControls = A.one("#toggle_id_ticket_entry_searchadvanced").all('input, select');

			advancedControls.attr('disabled', '');
		}

		submitForm(document.<portlet:namespace />fm);
	}

	function <portlet:namespace />updateSortBy() {
		var A = AUI();

		var orderByCol = A.one('#<portlet:namespace />orderByCol').val();
		var orderByType = A.one('#<portlet:namespace />orderByType').val();

		<%
		String sortByURL = portletURL.toString();

		sortByURL = HttpUtil.removeParameter(sortByURL, renderResponse.getNamespace() + "orderByCol");
		sortByURL = HttpUtil.removeParameter(sortByURL, renderResponse.getNamespace() + "orderByType");
		%>

		window.location.href = "<%= sortByURL %>&<portlet:namespace />orderByCol=" + orderByCol + "&<portlet:namespace />orderByType=" + orderByType;
	}
</aui:script>