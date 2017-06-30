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
List<TicketEntry> results = (List<TicketEntry>)request.getAttribute("tickets.jsp-results");

boolean advancedSearch = ParamUtil.getBoolean(request, "advancedSearch");
boolean banner = ParamUtil.getBoolean(request, "banner");
boolean bulkEdit = ParamUtil.getBoolean(request, "bulkEdit");
int count = ParamUtil.getInteger(request, "count");
boolean fade = ParamUtil.getBoolean(request, "fade");
boolean first = ParamUtil.getBoolean(request, "first");
String id = ParamUtil.getString(request, "id");
String label = ParamUtil.getString(request, "label");
String[] orderableColumns = StringUtil.split(ParamUtil.getString(request, "orderableColumns"));
String orderByCol = ParamUtil.getString(request, "orderByCol");
String orderByType = ParamUtil.getString(request, "orderByType");
boolean supportManager = ParamUtil.getBoolean(request, "supportManager");

String topCssClass = "top normal-top";

if (banner) {
	topCssClass = "top banner-top";
}
%>

<div class="section-title <%= first ? "pinned " + topCssClass : "other unpinned" %>" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(id) %><%= first ? "PinnedHeader" : StringPool.BLANK %>">
	<c:if test="<%= Validator.isNotNull(label) %>">
		<liferay-ui:message key="<%= HtmlUtil.escape(label) %>" /> (<%= (count > 0) ? count : StringPool.DASH %>)
	</c:if>

	<c:if test="<%= (!advancedSearch && (count > 0)) || ((results != null) && !results.isEmpty()) %>">
		<div class="table <%= liferayIncOrg ? "view-liferay" : "" %>">
			<div class="row">
				<c:if test="<%= supportManager %>">
					<div class="column search-results-header ticket-column select toggle-bulk-edit <%= bulkEdit ? "" : "aui-helper-hidden" %>"></div>
				</c:if>

				<div class="column search-results-header ticket-column severity"></div>

				<div class="column search-results-header ticket-column img"></div>

				<div class="column search-results-header ticket-column number toggle-bulk <%= bulkEdit ? "bulk-edit" : "" %> <%= _getCSSClass("display-id", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "display-id") %>'>
						<a href="javascript:<portlet:namespace />columnSort('display-id', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="ticket-number" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "display-id") %>'>
						</a>
					</c:if>
				</div>

				<div class="column search-results-header ticket-column subject <%= _getCSSClass("subject", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "subject") %>'>
						<a href="javascript:<portlet:namespace />columnSort('subject', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="subject" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "subject") %>'>
						</a>
					</c:if>
				</div>

				<div class="column search-results-header ticket-column assignee toggle-bulk <%= bulkEdit ? "bulk-edit" : "" %> <%= _getCSSClass("assignee", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "assignee") %>'>
						<a href="javascript:<portlet:namespace />columnSort('assignee', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="assignee" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "assignee") %>'>
						</a>
					</c:if>
				</div>

				<div class="column search-results-header ticket-column created <%= _getCSSClass("create-date", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "create-date") %>'>
						<a href="javascript:<portlet:namespace />columnSort('create-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="created" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "create-date") %>'>
						</a>
					</c:if>
				</div>

				<div class="column search-results-header ticket-column last-updated <%= _getCSSClass("modified-date", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "modified-date") %>'>
						<a href="javascript:<portlet:namespace />columnSort('modified-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="last-updated" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "modified-date") %>'>
						</a>
					</c:if>
				</div>

				<c:if test="<%= liferayIncOrg %>">
					<div class="column search-results-header ticket-column due-date <%= _getCSSClass("due-date", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "due-date") %>'>
							<a href="javascript:<portlet:namespace />columnSort('due-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="due-date" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "due-date") %>'>
							</a>
						</c:if>
					</div>
				</c:if>

				<div class="column search-results-header ticket-column status <%= _getCSSClass("status", orderByCol, orderByType) %>">
					<c:if test='<%= ArrayUtil.contains(orderableColumns, "status") %>'>
						<a href="javascript:<portlet:namespace />columnSort('status', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
					</c:if>

					<liferay-ui:message key="status" />

					<c:if test='<%= ArrayUtil.contains(orderableColumns, "status") %>'>
						</a>
					</c:if>
				</div>

				<div class="column search-results-header ticket-column files">
					<liferay-ui:message key="files" />
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="<%= fade %>">
		<div class="ticket-fade"></div>
	</c:if>
</div>

<c:if test="<%= !first %>">
	<div class="aui-helper-hidden section-title pinned <%= first ? topCssClass : "other" %>" id="<portlet:namespace /><%= HtmlUtil.escapeAttribute(id) %>PinnedHeader">
		<liferay-ui:message key="<%= HtmlUtil.escape(label) %>" /> (<%= (count > 0) ? count : StringPool.DASH %>)

		<c:if test="<%= (!advancedSearch && (count > 0)) || ((results != null) && !results.isEmpty()) %>">
			<div class="table <%= liferayIncOrg ? "view-liferay" : "" %>">
				<div class="row">
					<div class="column search-results-header ticket-column severity"></div>

					<div class="column search-results-header ticket-column img"></div>

					<div class="column search-results-header ticket-column number <%= _getCSSClass("display-id", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "display-id") %>'>
							<a href="javascript:<portlet:namespace />columnSort('display-id', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="ticket-number" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "display-id") %>'>
							</a>
						</c:if>
					</div>

					<div class="column search-results-header ticket-column subject <%= _getCSSClass("subject", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "subject") %>'>
							<a href="javascript:<portlet:namespace />columnSort('subject', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="subject" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "subject") %>'>
							</a>
						</c:if>
					</div>

					<div class="column search-results-header ticket-column assignee <%= _getCSSClass("assignee", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "assignee") %>'>
							<a href="javascript:<portlet:namespace />columnSort('assignee', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="assignee" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "assignee") %>'>
							</a>
						</c:if>
					</div>

					<div class="column search-results-header ticket-column created <%= _getCSSClass("create-date", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "create-date") %>'>
							<a href="javascript:<portlet:namespace />columnSort('create-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="created" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "create-date") %>'>
							</a>
						</c:if>
					</div>

					<div class="column search-results-header ticket-column last-updated <%= _getCSSClass("modified-date", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "modified-date") %>'>
							<a href="javascript:<portlet:namespace />columnSort('modified-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="last-updated" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "modified-date") %>'>
							</a>
						</c:if>
					</div>

					<c:if test="<%= liferayIncOrg %>">
						<div class="column search-results-header ticket-column due-date <%= _getCSSClass("due-date", orderByCol, orderByType) %>">
							<c:if test='<%= ArrayUtil.contains(orderableColumns, "due-date") %>'>
								<a href="javascript:<portlet:namespace />columnSort('due-date', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
							</c:if>

							<liferay-ui:message key="due-date" />

							<c:if test='<%= ArrayUtil.contains(orderableColumns, "due-date") %>'>
								</a>
							</c:if>
						</div>
					</c:if>

					<div class="column search-results-header ticket-column status <%= _getCSSClass("status", orderByCol, orderByType) %>">
						<c:if test='<%= ArrayUtil.contains(orderableColumns, "status") %>'>
							<a href="javascript:<portlet:namespace />columnSort('status', '<%= HtmlUtil.escapeAttribute(orderByCol) %>', '<%= HtmlUtil.escapeAttribute(orderByType) %>');">
						</c:if>

						<liferay-ui:message key="status" />

						<c:if test='<%= ArrayUtil.contains(orderableColumns, "status") %>'>
							</a>
						</c:if>
					</div>

					<div class="column search-results-header ticket-column files">
						<liferay-ui:message key="files" />
					</div>
				</div>
			</div>
		</c:if>

		<c:if test="<%= fade %>">
			<div class="ticket-fade"></div>
		</c:if>
	</div>
</c:if>

<%!
private String _getCSSClass(String column, String orderByCol, String orderByType) {
	if (column.equals(orderByCol)) {
		if (orderByType.equals("asc")) {
			return "arrow-up";
		}
		else if (orderByType.equals("desc")) {
			return "arrow-down";
		}
	}

	return StringPool.BLANK;
}
%>