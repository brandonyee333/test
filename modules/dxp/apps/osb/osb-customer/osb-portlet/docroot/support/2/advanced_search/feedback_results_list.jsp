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
List<TicketFeedback> results = (List<TicketFeedback>)request.getAttribute("feedback.jsp-results");

String activeFilters = ParamUtil.getString(request, "activeFilters");
int count = ParamUtil.getInteger(request, "count");
int cur = ParamUtil.getInteger(request, "cur");
String id = ParamUtil.getString(request, "id");
String pageURL = ParamUtil.getString(request, "pageURL");
boolean paginate = ParamUtil.getBoolean(request, "paginate");
%>

<input class="hide" id="<portlet:namespace />activeFilters" value="<%= HtmlUtil.escapeAttribute(activeFilters) %>" />
<input class="hide" id="<portlet:namespace />feedbackResultsListCount" value="<%= count %>" />

<c:choose>
	<c:when test="<%= !results.isEmpty() %>">
		<div class="section-title <%= HtmlUtil.escapeAttribute(id) %>" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">
				<div class="row">
					<div class="column search-results-header severity ticket-feedback-column"></div>

					<div class="column search-results-header ticket-feedback-column ticket-number">
						<liferay-ui:message key="ticket-number" />
					</div>

					<div class="column created search-results-header ticket-feedback-column">
						<liferay-ui:message key="created" />
					</div>

					<div class="assignee column search-results-header ticket-feedback-column">
						<liferay-ui:message key="assignee" />
					</div>

					<div class="column satisfied search-results-header ticket-feedback-column">
						<liferay-ui:message key="satisfied" />
					</div>

					<div class="column final-resolution search-results-header ticket-feedback-column">
						<liferay-ui:message key="final-resolution" />
					</div>

					<div class="column search-results-header technical-knowledge ticket-feedback-column">
						<liferay-ui:message key="technical-knowledge" />
					</div>

					<div class="column response-time search-results-header ticket-feedback-column">
						<liferay-ui:message key="response-time" />
					</div>

					<div class="column professionalism search-results-header ticket-feedback-column">
						<liferay-ui:message key="professionalism" />
					</div>

					<div class="average column search-results-header ticket-feedback-column">
						<liferay-ui:message key="average" />
					</div>
				</div>
			</div>

			<div class="ticket-feedback-fade"></div>
		</div>

		<div class="results-table <%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">

				<%
				for (TicketFeedback ticketFeedback : results) {
					TicketEntry ticketEntry = ticketFeedback.getTicketEntry();

					User ticketWorkerUser = null;

					TicketWorker ticketWorker = TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(ticketEntry.getTicketEntryId());

					if (ticketWorker != null) {
						ticketWorkerUser = UserLocalServiceUtil.getUser(ticketWorker.getUserId());
					}

					String rowJS = StringPool.BLANK;

					if (!BrowserSnifferUtil.isChrome(request)) {
						StringBundler sb = new StringBundler(3);

						sb.append("onClick=\"");
						sb.append(renderResponse.getNamespace());
						sb.append("checkOnClick(this, event);\"");

						rowJS = sb.toString();
					}

					String columnJS = StringPool.BLANK;

					if (!BrowserSnifferUtil.isFirefox(request)) {
						StringBundler sb = new StringBundler(3);

						sb.append("onMouseDown=\"this.draggable = true;\" onDragStart=\"");
						sb.append(renderResponse.getNamespace());
						sb.append("selectText(this, event);\"");

						columnJS = sb.toString();
					}
				%>

					<liferay-portlet:renderURL varImpl="rowURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
						<portlet:param name="mvcPath" value="/support/2/edit_ticket_entry.jsp" />
						<portlet:param name="redirect" value="<%= pageURL %>" />
						<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketFeedback.getTicketEntryId()) %>" />
					</liferay-portlet:renderURL>

					<a class="row-url search-row" draggable="false" href="<%= rowURL %>" <%= rowJS %>>
						<div class="column ticket-feedback-column severity v<%= ticketEntry.getSeverity() %>"></div>

						<div class="column ticket-feedback-column ticket-number" <%= columnJS %>>
							<div class="search-title">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= HtmlUtil.escape(ticketEntry.getDisplayId()) %>
								</span>
							</div>
						</div>

						<div class="column created ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= longDateFormatDate.format(ticketFeedback.getCreateDate()) %>
								</span>
							</div>
						</div>

						<div class="assignee column ticket-feedback-column" <%= columnJS %>>
							<div class="ticket-assignee-wrapper">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<c:if test="<%= ticketWorkerUser != null %>">
										<div class="assignee user-avatar" style="background-image: url('<%= ticketWorkerUser.getPortraitURL(themeDisplay) %>&height=40&width=40');"></div>
									</c:if>

									<c:if test="<%= !screenShareMode %>">
										<div class="ticket-assignee">
											<c:if test="<%= ticketWorkerUser != null %>">
												<%= HtmlUtil.escape(ticketWorkerUser.getFullName()) %>
											</c:if>
										</div>
									</c:if>
								</span>
							</div>
						</div>

						<div class="column satisfied ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= LanguageUtil.get(request, ticketFeedback.getSatisfiedLabel()) %>
								</span>
							</div>
						</div>

						<div class="column final-resolution ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= ticketFeedback.getRating1() + " - " + LanguageUtil.get(request, ticketFeedback.getRating1Label()) %>
								</span>
							</div>
						</div>

						<div class="column technical-knowledge ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= ticketFeedback.getRating3() + " - " + LanguageUtil.get(request, ticketFeedback.getRating3Label()) %>
								</span>
							</div>
						</div>

						<div class="column response-time ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= ticketFeedback.getRating2() + " - " + LanguageUtil.get(request, ticketFeedback.getRating2Label()) %>
								</span>
							</div>
						</div>

						<div class="column professionalism ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= ticketFeedback.getRating4() + " - " + LanguageUtil.get(request, ticketFeedback.getRating4Label()) %>
								</span>
							</div>
						</div>

						<div class="average column ticket-feedback-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getDisplayId()) %>">
									<%= ticketFeedback.getAverageRating() %>
								</span>
							</div>
						</div>
					</a>

				<%
				}
				%>

			</div>

			<c:if test="<%= paginate %>">

				<%
				int start = (cur - 1) * 100;
				int end = cur * 100;

				if (end > count) {
					end = count;
				}

				int pages = (int)Math.ceil(count / 100.0);
				%>

				<div class="taglib-page-iterator">
					<div class="search-results">
						<%= LanguageUtil.format(request, "showing-x-x", new Object[] {numberFormat.format(start + 1), numberFormat.format(end)}) %>
					</div>

					<div class="search-pages">
						<div class="page-links">
							<c:choose>
								<c:when test="<%= cur != 1 %>">
									<a class="first" href="javascript:<portlet:namespace/>paginateSearchResults(1);" target="_self"><liferay-ui:message key="first" /></a>
								</c:when>
								<c:otherwise>
									<span class="first"><liferay-ui:message key="first" /></span>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="<%= cur != 1 %>">
									<a class="previous" href="javascript:<portlet:namespace/>paginateSearchResults(<%= cur - 1 %>);" target="_self"><liferay-ui:message key="previous" /></a>
								</c:when>
								<c:otherwise>
									<span class="previous"><liferay-ui:message key="previous" /></span>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="<%= cur != pages %>">
									<a class="next" href="javascript:<portlet:namespace/>paginateSearchResults(<%= cur + 1 %>);" target="_self"><liferay-ui:message key="more" /></a>
								</c:when>
								<c:otherwise>
									<span class="next"><liferay-ui:message key="more" /></span>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</c:when>
	<c:otherwise>
		<span class="portlet-msg-info"><%= LanguageUtil.get(request, "no-feedback-was-found") %></span>
	</c:otherwise>
</c:choose>