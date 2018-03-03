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
String mvcPath = ParamUtil.getString(request, "mvcPath");

List<TicketEntry> results = (List<TicketEntry>)request.getAttribute("tickets.jsp-results");

boolean advancedSearch = ParamUtil.getBoolean(request, "advancedSearch");
boolean bulkEdit = ParamUtil.getBoolean(request, "bulkEdit");
int count = ParamUtil.getInteger(request, "count");
String cssClass = ParamUtil.getString(request, "cssClass");
int cur = ParamUtil.getInteger(request, "cur");
boolean invalidSearch = ParamUtil.getBoolean(request, "invalidSearch");
String orderableColumns = ParamUtil.getString(request, "orderableColumns");
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
String pageURL = ParamUtil.getString(request, "pageURL");
boolean paginate = ParamUtil.getBoolean(request, "paginate");
boolean supportManager = ParamUtil.getBoolean(request, "supportManager");

boolean ticketWorker = liferayIncOrg || supportPartnerWorker;

if (!mvcPath.endsWith("search.jsp")) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setParameter("mvcPath", "/support/2/view.jsp");
	portletURL.setWindowState(WindowState.NORMAL);

	pageURL = portletURL.toString();
}
%>

<c:if test="<%= advancedSearch %>">
	<liferay-util:include page="/support/2/ticket_results_header.jsp" servletContext="<%= application %>">
		<portlet:param name="advancedSearch" value="<%= String.valueOf(advancedSearch) %>" />
		<portlet:param name="bulkEdit" value="<%= String.valueOf(bulkEdit) %>" />
		<portlet:param name="orderableColumns" value="<%= orderableColumns %>" />
		<portlet:param name="orderByCol" value="<%= orderByCol %>" />
		<portlet:param name="orderByType" value="<%= orderByType %>" />
		<portlet:param name="supportManager" value="<%= String.valueOf(supportManager) %>" />
	</liferay-util:include>
</c:if>

<c:choose>
	<c:when test="<%= invalidSearch %>">
		<span class="portlet-msg-error <%= HtmlUtil.escapeAttribute(cssClass) %>"><liferay-ui:message key="invalid-ticket-search,-please-try-again" /></span>
	</c:when>
	<c:when test="<%= (results != null) && !results.isEmpty() %>">
		<div class="results-table <%= HtmlUtil.escapeAttribute(cssClass) %>">
			<div class="table <%= liferayIncOrg ? "view-liferay" : "" %>">

				<%
				for (TicketEntry ticketEntry : results) {
					User ticketWorkerUser = null;

					TicketWorker curTicketWorker = TicketWorkerLocalServiceUtil.fetchPrimaryTicketWorker(ticketEntry.getTicketEntryId());

					if (curTicketWorker != null) {
						ticketWorkerUser = UserLocalServiceUtil.getUser(curTicketWorker.getUserId());
					}

					String rowCssClass = "no-response-needed";

					if ((ticketEntry.getStatus() != TicketEntryConstants.STATUS_CLOSED) || (ticketEntry.getResolution() == TicketEntryConstants.RESOLUTION_PENDING_CUSTOMER)) {
						if ((liferayIncOrg && ticketEntry.isPendingLiferay()) || (supportPartnerWorker && ticketEntry.isPendingPartner()) || (!ticketWorker && ticketEntry.isPendingCustomer())) {
							rowCssClass = StringPool.BLANK;
						}
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
						<portlet:param name="ticketEntryId" value="<%= String.valueOf(ticketEntry.getTicketEntryId()) %>" />
					</liferay-portlet:renderURL>

					<div class="row-url ticket-row">
						<c:if test="<%= supportManager %>">
							<div class="column select ticket-column toggle-bulk-edit <%= bulkEdit ? "" : "hide" %>">
								<input class="bulk-edit-checkbox" id="<portlet:namespace /><%= ticketEntry.getTicketEntryId() %>" onChange="<portlet:namespace />selectTicket(this);" type="checkbox" />
							</div>
						</c:if>

						<div class="column ticket-column">
							<a draggable="false" href="<%= rowURL %>" <%= rowJS %>>
								<div class="table">
									<div class="<%= rowCssClass %> row">
										<div class="column severity ticket-column <%= ticketEntry.getSeverityLabel() %>"></div>

										<div class="column img ticket-column" <%= columnJS %>>
											<div>
												<span title="<%= LanguageUtil.get(request, ticketEntry.getComponentLabel()) %>">
													<img class="ticket-img" src="<%= PortalUtil.getPathContext(request) %>/images/<%= ticketEntry.getComponentIcon() %>" />
												</span>
											</div>
										</div>

										<div class="column number ticket-column toggle-bulk <%= bulkEdit ? "bulk-edit" : "" %>" <%= columnJS %>>
											<div class="search-title">
												<span title="<%= ticketEntry.getDisplayId() %>">
													<%= ticketEntry.getDisplayId() %>
												</span>
											</div>
										</div>

										<div class="column subject ticket-column" <%= columnJS %>>
											<div class="search-detail">
												<span title="<%= HtmlUtil.escapeAttribute(ticketEntry.getSubject()) %>">
													<%= HtmlUtil.escape(ticketEntry.getSubject()) %>
												</span>
											</div>
										</div>

										<div class="assignee column ticket-column toggle-bulk <%= bulkEdit ? "bulk-edit" : "" %>" <%= columnJS %>>
											<div class="ticket-assignee-wrapper">
												<c:if test="<%= ticketWorkerUser != null %>">
													<div class="assignee user-avatar" style="background-image: url('<%= ticketWorkerUser.getPortraitURL(themeDisplay) %>&height=40&width=40')"></div>
												</c:if>

												<c:if test="<%= !screenShareMode %>">
													<div class="ticket-assignee">
														<c:if test="<%= ticketWorkerUser != null %>">
															<%= HtmlUtil.escape(ticketWorkerUser.getFullName()) %>
														</c:if>
													</div>
												</c:if>
											</div>
										</div>

										<div class="column created ticket-column" <%= columnJS %>>
											<div class="ticket-created-date">
												<%= shortDateFormatDate.format(ticketEntry.getCreateDate()) %>
											</div>
										</div>

										<div class="column last-updated ticket-column" <%= columnJS %>>
											<div class="ticket-last-updated-date">
												<%= shortDateFormatDate.format(ticketEntry.getModifiedDate()) %>
											</div>
										</div>

										<c:if test="<%= liferayIncOrg %>">
											<div class="column due-date ticket-column" <%= columnJS %>>
												<div class="ticket-due-date">
													<%= shortDateFormatDate.format(ticketEntry.getDueDate()) %>
												</div>
											</div>
										</c:if>

										<div class="column status ticket-column" <%= columnJS %>>
											<div class="ticket-status">

												<%
												StringBundler sb = new StringBundler(3);

												sb.append(LanguageUtil.get(request, ticketEntry.getStatusLabel()));

												if (ticketEntry.getResolution() > 0) {
													sb.append(" : ");
													sb.append(LanguageUtil.get(request, ticketEntry.getResolutionLabel()));
												}
												%>

												<span title="<%= sb.toString() %>">
													<%= sb.toString() %>
												</span>
											</div>
										</div>

										<div class="column files ticket-column">
											<div>

												<%
												TicketAttachment ticketAttachment = TicketAttachmentLocalServiceUtil.fetchTicketAttachment(ticketEntry.getTicketEntryId(), TicketAttachmentConstants.TYPE_HOTFIX);

												int[] userVisibilities = VisibilityConstants.getUserVisibilities(user.getUserId(), ticketEntry.getTicketEntryId());
												%>

												<span title="<%= (ticketAttachment != null) ? HtmlUtil.escapeAttribute(ticketAttachment.getFileName()) : "" %>">
													<c:choose>
														<c:when test="<%= ticketAttachment != null %>">
															<img class="ticket-img" src="<%= PortalUtil.getPathContext(request) %>/images/svg/hotfix.svg" />
														</c:when>
														<c:when test="<%= ticketEntry.getTicketAttachmentsCount(userVisibilities) > 0 %>">
															<img class="ticket-img" src="<%= PortalUtil.getPathContext(request) %>/images/svg/attachment.svg" />
														</c:when>
													</c:choose>
												</span>
											</div>
										</div>
									</div>
								</div>
							</a>
						</div>
					</div>

				<%
				}
				%>

			</div>

			<c:if test="<%= paginate %>">

				<%
				int pages = (int)Math.ceil(count / 100.0);

				if (cur > pages) {
					cur = pages;
				}

				int start = (cur - 1) * 100;
				int end = cur * 100;

				if (end > count) {
					end = count;
				}
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
		<span class="portlet-msg-info <%= HtmlUtil.escapeAttribute(cssClass) %>"><liferay-ui:message key="no-tickets-were-found" /></span>
	</c:otherwise>
</c:choose>