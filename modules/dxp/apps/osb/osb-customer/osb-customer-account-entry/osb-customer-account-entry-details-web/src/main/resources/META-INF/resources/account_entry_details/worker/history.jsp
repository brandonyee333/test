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
	List<AuditEntry> auditEntries = accountEntryViewDisplayContext.getAuditEntries();

	for (AuditEntry auditEntry : auditEntries) {
		String oldLabel = auditEntry.getOldValue();

		if (Validator.isNull(oldLabel)) {
			oldLabel = "N/A";
		}

		String newLabel = auditEntry.getNewValue();

		if (Validator.isNull(newLabel)) {
			newLabel = "N/A";
		}
	%>

		<div class="audit-entry-set">
			<div class="entry-header">
				<div class="entry-header-row">
					<div class="semi-bold user-display">
						<span><%= HtmlUtil.escape(auditEntry.getUserName()) %></span>
					</div>

					<div class="icon">
						<svg class="lexicon-icon lexicon-icon-angle-right">
							<use xlink:href="#angle-right" />
						</svg>
					</div>

					<div class="summary">
						<%= auditEntry.getAction() %>

						<%= auditEntry.getFieldClassName() %>
					</div>

					<div class="create-date entry-header-item">
						<span title="<%= fullDateFormatDateTime.format(auditEntry.getDateCreated()) %>"><%= shortDateFormatDate.format(auditEntry.getDateCreated()) %> <%= shortDateFormatTime.format(auditEntry.getDateCreated()) %></span>
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
					<tr class="row">
						<td class="col-md-2 semi-bold">
							<liferay-ui:message key="<%= auditEntry.getField() %>" />
						</td>
						<td class="code col-md-5">
							<%= HtmlUtil.escape(oldLabel) %>
						</td>
						<td class="code col-md-5">
							<%= HtmlUtil.escape(newLabel) %>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

	<%
	}
	%>

</div>