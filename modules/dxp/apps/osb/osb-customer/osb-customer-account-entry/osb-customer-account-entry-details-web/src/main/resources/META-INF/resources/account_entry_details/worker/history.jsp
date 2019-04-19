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

		<div class="audit-entry-set">
			<div class="entry-header">
				<div class="entry-header-row">
					<div class="semibold user-display">

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
					</div>

					<div class="icon">
						<svg class="lexicon-icon lexicon-icon-angle-right">
							<use xlink:href="#angle-right" />
						</svg>
					</div>

					<div class="summary">
						<liferay-ui:message key="<%= auditEntry.getActionLabel() %>" />

						<liferay-ui:message key="<%= auditEntry.getFieldClassNameIdLabel() %>" />
					</div>

					<div class="create-date entry-header-item">
						<span title="<%= fullDateFormatDateTime.format(auditEntry.getCreateDate()) %>"><%= shortDateFormatDate.format(auditEntry.getCreateDate()) %> <%= shortDateFormatTime.format(auditEntry.getCreateDate()) %></span>
					</div>
				</div>
			</div>

			<table class="table">
				<c:if test="<%= auditEntry.getAction() != AuditEntryConstants.ACTION_AUDIT %>">
					<thead class="content-header">
						<tr class="row">
							<th class="col-md-2 semibold">
								<h6>
									<liferay-ui:message key="field" />
								</h6>
							</th>
							<th class="col-md-5 semibold">
								<h6>
									<liferay-ui:message key="original-value" />
								</h6>
							</th>
							<th class="col-md-5 semibold">
								<h6>
									<liferay-ui:message key="new-value" />
								</h6>
							</th>
						</tr>
					</thead>
				</c:if>

				<tbody>

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

						<tr class="row">
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

													<liferay-ui:message key="<%= AuditEntryConstants.getFieldLabel(field) %>" /><%= ((k + 1) < outOfSyncFields.length) ? StringPool.COMMA : StringPool.BLANK %>

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
									<td class="col-md-2 semibold">
										<liferay-ui:message key="<%= curAuditEntry.getFieldLabel() %>" />
									</td>
									<td class="code col-md-5">
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
									<td class="code col-md-5">
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
		</div>

	<%
	}
	%>

</div>