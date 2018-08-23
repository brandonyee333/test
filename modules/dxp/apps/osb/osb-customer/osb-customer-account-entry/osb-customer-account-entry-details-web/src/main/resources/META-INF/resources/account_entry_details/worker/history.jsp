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
	AccountEntry accountEntry = (AccountEntry)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

	List<List<AuditEntry>> auditEntrySets = AuditEntryLocalServiceUtil.getAuditEntrySets(PortalUtil.getClassNameId(AccountEntry.class.getName()), accountEntry.getAccountEntryId(), new int[] {VisibilityConstants.ADMIN, VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC, VisibilityConstants.WORKERS});

	for (int i = 0; i < auditEntrySets.size(); i++) {
		List<AuditEntry> auditEntries = auditEntrySets.get(i);

		AuditEntry auditEntry = auditEntries.get(0);
	%>

		<table class="audit-entry-set">
			<thead class="entry-header">
				<tr>
					<th class="user-display">

						<%
						String auditEntryUserName = StringPool.BLANK;
						String portraitURL = StringPool.BLANK;

						if (auditEntry.getUserId() != OSBConstants.USER_DEFAULT_USER_ID) {
							User auditEntryUser = UserLocalServiceUtil.getUser(auditEntry.getUserId());

							auditEntryUserName = auditEntryUser.getFullName();

							portraitURL = auditEntryUser.getPortraitURL(themeDisplay);
						}
						else {
							auditEntryUserName = "Auto";
						}
						%>

						<span><%= HtmlUtil.escape(auditEntryUserName) %></span>
					</th>

					<th>
						&gt;
					</th>

					<th class="summary">
						<liferay-ui:message key="<%= auditEntry.getActionLabel() %>" />

						<liferay-ui:message key="<%= auditEntry.getFieldClassNameIdLabel() %>" />
					</th>

					<th class="create-date">
						<span title="<%= fullDateFormatDateTime.format(auditEntry.getCreateDate()) %>"><%= shortDateFormatDate.format(auditEntry.getCreateDate()) %> <%= shortDateFormatTime.format(auditEntry.getCreateDate()) %></span>
					</th>
				</tr>
			</thead>

			<tbody class="content">
				<c:if test="<%= auditEntry.getAction() != AuditEntryConstants.ACTION_AUDIT %>">
					<tr class="content-header">
						<td class="txt-sb w15">
							<liferay-ui:message key="field" />
						</td>

						<td class="txt-sb w40">
							<liferay-ui:message key="original-value" />
						</td>

						<td class="txt-sb w40">
							<liferay-ui:message key="new-value" />
						</td>
					</tr>
				</c:if>

				<%
				for (int j = 0; j < auditEntries.size(); j++) {
					AuditEntry curAuditEntry = auditEntries.get(j);

					String oldLabel = curAuditEntry.getOldLabel();

					if (Validator.isNull(oldLabel)) {
						oldLabel = curAuditEntry.getOldValue();
					}

					String newLabel = curAuditEntry.getNewLabel();

					if (Validator.isNull(newLabel)) {
						newLabel = curAuditEntry.getNewValue();
					}
				%>

				<tr class="content-row">
					<c:choose>
						<c:when test="<%= curAuditEntry.getAction() == AuditEntryConstants.ACTION_AUDIT %>">
							<td>

								<%
								int[] outOfSyncFields = StringUtil.split(curAuditEntry.getOldValue(), 0);
								%>

								<c:choose>
									<c:when test="<%= Validator.isNotNull(curAuditEntry.getOldLabel()) %>">
										<liferay-ui:message key="<%= curAuditEntry.getOldLabel() %>" />
									</c:when>
									<c:when test="<%= ArrayUtil.isNotEmpty(outOfSyncFields) %>">
										<liferay-ui:message key="the-following-fields-are-out-of-sync-with-dossiera" />

										<%
										for (int k = 0; k < outOfSyncFields.length; k++) {
											int field = outOfSyncFields[k];
										%>

											<liferay-ui:message key="<%= AuditEntryConstants.getFieldLabel(field) %>" /><%= ((k + 1) < outOfSyncFields.length) ? StringPool.COMMA : "" %>

										<%
										}
										%>

									</c:when>
									<c:otherwise>
										<liferay-ui:message key="project-information-has-been-verified" />
									</c:otherwise>
								</c:choose>

							</td>
						</c:when>
						<c:otherwise>
							<td class="txt-sb w15">
								<liferay-ui:message key="<%= curAuditEntry.getFieldLabel() %>" />
							</td>

							<td class="txt-code w40">
								<c:choose>
									<c:when test="<%= Validator.isNull(oldLabel) %>">
										<%= AuditEntryConstants.NOT_AVAILABLE %>
									</c:when>
									<c:when test="<%= curAuditEntry.getI18n() %>">
										<liferay-ui:message key="<%= HtmlUtil.escape(oldLabel) %>" />
									</c:when>
									<c:otherwise>
										<%= HtmlUtil.escape(oldLabel) %>
									</c:otherwise>
								</c:choose>
							</td>

							<td class="txt-code w40">
								<c:choose>
									<c:when test="<%= Validator.isNull(newLabel) %>">
										<%= AuditEntryConstants.NOT_AVAILABLE %>
									</c:when>
									<c:when test="<%= curAuditEntry.getI18n() %>">
										<liferay-ui:message key="<%= HtmlUtil.escape(newLabel) %>" />
									</c:when>
									<c:otherwise>
										<%= HtmlUtil.escape(newLabel) %>
									</c:otherwise>
								</c:choose>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>

				<%
				}
				%>

			</tbody>
		</table>

	<%
	}
	%>

</div>