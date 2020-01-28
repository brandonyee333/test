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

<%@ include file="/account_entry_details/init.jsp" %>

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