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
List<PartnerEntry> results = (List<PartnerEntry>)request.getAttribute("partners.jsp-results");

String activeFilters = ParamUtil.getString(request, "activeFilters");
int count = ParamUtil.getInteger(request, "count");
int cur = ParamUtil.getInteger(request, "cur");
String id = ParamUtil.getString(request, "id");
String pageURL = ParamUtil.getString(request, "pageURL");
boolean paginate = ParamUtil.getBoolean(request, "paginate");
%>

<input class="hide" id="<portlet:namespace />activeFilters" value="<%= HtmlUtil.escapeAttribute(activeFilters) %>" />
<input class="hide" id="<portlet:namespace />partnerResultsListCount" value="<%= count %>" />

<c:choose>
	<c:when test="<%= !results.isEmpty() %>">
		<div class="section-title <%= HtmlUtil.escapeAttribute(id) %>" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">
				<div class="row">
					<div class="column name partner-column search-results-header">
						<liferay-ui:message key="name" />
					</div>

					<div class="column first-line-support partner-column search-results-header">
						<liferay-ui:message key="first-line-support" />
					</div>

					<div class="column partner-column search-results-header support-region">
						<liferay-ui:message key="support-region" />
					</div>

					<div class="column partner-column search-results-header status">
						<liferay-ui:message key="status" />
					</div>
				</div>
			</div>

			<div class="detail-view-fade"></div>
		</div>

		<div class="results-table <%= HtmlUtil.escapeAttribute(id) %>">
			<div class="table">

				<%
				for (PartnerEntry partnerEntry : results) {
					List<AccountEntry> accountEntries = partnerEntry.getAccountEntries();

					boolean isManagingSupport = false;

					for (AccountEntry accountEntry : accountEntries) {
						if (accountEntry.isPartnerManagedSupport()) {
							isManagingSupport = true;

							break;
						}
					}

					SupportRegion supportRegion = partnerEntry.getSupportRegion();

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
						<portlet:param name="mvcPath" value="/support/2/edit_partner_entry.jsp" />
						<portlet:param name="redirect" value="<%= pageURL %>" />
						<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntry.getPartnerEntryId()) %>" />
					</liferay-portlet:renderURL>

					<a class="row-url search-row" draggable="false" href="<%= rowURL %>" <%= rowJS %>>
						<div class="column name partner-column" <%= columnJS %>>
							<div class="search-title">
								<span title="<%= HtmlUtil.escapeAttribute(partnerEntry.getCode()) %>">
									<%= HtmlUtil.escape(partnerEntry.getCode()) %>
								</span>
							</div>
						</div>

						<div class="column first-line-support partner-column" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(partnerEntry.getCode()) %>">
									<%= isManagingSupport ? LanguageUtil.get(request, "yes") : LanguageUtil.get(request, "no") %>
								</span>
							</div>
						</div>

						<div class="column partner-column support-region" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(partnerEntry.getCode()) %>">
									<%= Validator.isNotNull(supportRegion) ? HtmlUtil.escape(supportRegion.getName()) : StringPool.DASH %>
								</span>
							</div>
						</div>

						<div class="column partner-column status" <%= columnJS %>>
							<div class="search-detail">
								<span title="<%= HtmlUtil.escapeAttribute(partnerEntry.getCode()) %>">
									<%= LanguageUtil.get(request, partnerEntry.getStatusLabel()) %>
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
		<span class="portlet-msg-info"><%= LanguageUtil.get(request, "no-partners-were-found") %></span>
	</c:otherwise>
</c:choose>