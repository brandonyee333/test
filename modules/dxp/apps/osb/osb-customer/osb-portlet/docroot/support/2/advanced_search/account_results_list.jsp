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
List<AccountEntry> results = (List<AccountEntry>)request.getAttribute("accounts.jsp-results");

String activeFilters = ParamUtil.getString(request, "activeFilters");
int count = ParamUtil.getInteger(request, "count");
int cur = ParamUtil.getInteger(request, "cur");
String id = ParamUtil.getString(request, "id");
String pageURL = ParamUtil.getString(request, "pageURL");
boolean paginate = ParamUtil.getBoolean(request, "paginate");

boolean ticketWorker = liferayIncOrg || supportPartnerWorker;
%>

<input class="aui-helper-hidden" id="<portlet:namespace />activeFilters" value="<%= HtmlUtil.escapeAttribute(activeFilters) %>" />
<input class="aui-helper-hidden" id="<portlet:namespace />accountResultsListCount" value="<%= count %>" />

<c:choose>
	<c:when test="<%= !results.isEmpty() %>">
		<div class="section-title <%= HtmlUtil.escapeAttribute(id) %>" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">
				<div class="row">
					<div class="account-column code column search-results-header">
						<liferay-ui:message key="code" />
					</div>

					<div class="account-column column project-name search-results-header">
						<liferay-ui:message key="project-name" />
					</div>

					<div class="account-column column partner search-results-header">
						<liferay-ui:message key="partner" />
					</div>

					<div class="account-column column search-results-header tier">
						<liferay-ui:message key="tier" />
					</div>

					<div class="account-column column search-results-header support-end-date">
						<liferay-ui:message key="support-end-date" />
					</div>

					<div class="account-column column search-results-header status">
						<liferay-ui:message key="status" />
					</div>
				</div>
			</div>

			<div class="account-fade"></div>
		</div>

		<div class="results-table <%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">

				<%
				for (AccountEntry accountEntry : results) {
					PartnerEntry partnerEntry = accountEntry.getPartnerEntry();

					List<OfferingEntry> offeringEntries = OfferingEntryLocalServiceUtil.search(0, accountEntry.getAccountEntryId(), new int[0], new int[0], 0, 0, 0, 0, 0, 0, null, true, 0, 1, new OfferingEntrySupportEndDateComparator());

					OfferingEntry offeringEntry = null;

					if (!offeringEntries.isEmpty()) {
						offeringEntry = offeringEntries.get(0);
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
						<portlet:param name="mvcPath" value="/support/2/edit_account_entry.jsp" />
						<portlet:param name="redirect" value="<%= pageURL %>" />
						<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
					</liferay-portlet:renderURL>

					<a class="row-url search-row" draggable="false" href="<%= rowURL %>" <%= rowJS %>>
						<div class="account-column code column" <%= columnJS %>>
							<div class="search-title">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<%= accountEntry.getCode() %>
								</span>
							</div>
						</div>

						<div class="account-column column project-name" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<%= HtmlUtil.escape(accountEntry.getName()) %>
								</span>
							</div>
						</div>

						<div class="account-column column partner" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<c:choose>
										<c:when test="<%= ticketWorker && (partnerEntry != null) %>">
											<%= HtmlUtil.escape(partnerEntry.getCode()) %>
										</c:when>
										<c:otherwise>
											<%= HtmlUtil.escape(StringPool.DASH) %>
										</c:otherwise>
									</c:choose>
								</span>
							</div>
						</div>

						<div class="account-column column tier" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<%= LanguageUtil.get(pageContext, AccountEntryConstants.getTierLabel(accountEntry.getTier())) %>
								</span>
							</div>
						</div>

						<div class="account-column column support-end-date" <%= columnJS %>>
							<div class="account-support-end-date">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<c:if test="<%= offeringEntry != null %>">
										<%= longDateFormatDateTime.format(offeringEntry.getSupportEndDate()) %>
									</c:if>
								</span>
							</div>
						</div>

						<div class="account-column column status" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(accountEntry.getName()) %>">
									<%= LanguageUtil.get(pageContext, accountEntry.getStatusLabel()) %>
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
						<%= LanguageUtil.format(pageContext, "showing-x-x", new Object[] {numberFormat.format(start + 1), numberFormat.format(end)}) %>
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
		<span class="portlet-msg-info"><%= LanguageUtil.get(pageContext, "no-projects-were-found") %></span>
	</c:otherwise>
</c:choose>