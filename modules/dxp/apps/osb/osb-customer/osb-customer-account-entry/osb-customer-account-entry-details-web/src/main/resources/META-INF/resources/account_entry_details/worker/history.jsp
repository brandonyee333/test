<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/account_entry_details/init.jsp" %>

<liferay-util:include page="/account_entry_details/customer_portal_banner.jsp" servletContext="<%= application %>" />

<div class="history">

	<%
	List<List<AuditEntry>> auditEntrySets = accountEntryViewDisplayContext.getAuditEntrySets();

	for (List<AuditEntry> auditEntrySet : auditEntrySets) {
		AuditEntry firstAuditEntry = auditEntrySet.get(0);
	%>

		<div class="audit-entry-set">
			<div class="entry-header">
				<div class="entry-header-row">
					<div class="semi-bold user-display">
						<span><%= HtmlUtil.escape(firstAuditEntry.getAgentName()) %></span>
					</div>

					<div class="icon">
						<svg class="lexicon-icon lexicon-icon-angle-right">
							<use xlink:href="#angle-right" />
						</svg>
					</div>

					<div class="summary">
						<%= firstAuditEntry.getSummary() %>
					</div>

					<c:if test="<%= Validator.isNotNull(firstAuditEntry.getDescription()) %>">
						<div class="icon">
							<svg class="lexicon-icon lexicon-icon-angle-right">
								<use xlink:href="#angle-right" />
							</svg>
						</div>

						<div class="description">
							<%= firstAuditEntry.getDescription() %>
						</div>
					</c:if>

					<div class="create-date entry-header-item">
						<span title="<%= fullDateFormatDateTime.format(firstAuditEntry.getDateCreated()) %>"><%= shortDateFormatDate.format(firstAuditEntry.getDateCreated()) %> <%= shortDateFormatTime.format(firstAuditEntry.getDateCreated()) %></span>
					</div>
				</div>
			</div>

			<table class="table">
				<thead class="content-header">
					<tr class="row">
						<th class="col-md-2 semi-bold">
							<h6>
								<liferay-ui:message key="field" />
							</h6>
						</th>
						<th class="col-md-5 semi-bold">
							<h6>
								<liferay-ui:message key="original-value" />
							</h6>
						</th>
						<th class="col-md-5 semi-bold">
							<h6>
								<liferay-ui:message key="new-value" />
							</h6>
						</th>
					</tr>
				</thead>

				<tbody>

					<%
					for (AuditEntry auditEntry : auditEntrySet) {
						String oldValue = auditEntry.getOldValue();

						if (Validator.isNull(oldValue)) {
							oldValue = "N/A";
						}

						String newValue = auditEntry.getNewValue();

						if (Validator.isNull(newValue)) {
							newValue = "N/A";
						}
					%>

						<tr class="row">
							<td class="col-md-2 semi-bold">
								<%= auditEntry.getField() %>
							</td>
							<td class="code col-md-5">
								<%= HtmlUtil.escape(oldValue) %>
							</td>
							<td class="code col-md-5">
								<%= HtmlUtil.escape(newValue) %>
							</td>
						</tr>

					<%
					}
					%>

				</tbody>
			</table>
		</div>

	<%
	}
	%>

</div>