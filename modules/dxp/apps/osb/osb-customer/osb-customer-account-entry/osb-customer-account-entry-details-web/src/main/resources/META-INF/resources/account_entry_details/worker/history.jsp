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

<%@ include file="/init.jsp" %>

<%
AccountEntry accountEntry = (AccountEntry)renderRequest.getAttribute(AccountEntryDetailsWebKeys.ACCOUNT_ENTRY);

List<List<AuditEntry>> auditEntrySets = AuditEntryLocalServiceUtil.getAuditEntrySets(PortalUtil.getClassNameId(AccountEntry.class.getName()), accountEntry.getAccountEntryId(), new int[] {VisibilityConstants.ADMIN, VisibilityConstants.LIFERAY_INC, VisibilityConstants.PUBLIC, VisibilityConstants.WORKERS});

for (int i = 0; i < auditEntrySets.size(); i++) {
	List<AuditEntry> auditEntries = auditEntrySets.get(i);

	AuditEntry auditEntry = auditEntries.get(0);
%>

	<div class="audit-entry-set" id="<portlet:namespace />auditSet<%= auditEntry.getAuditSetId() %>">
		<div class="header" id="<portlet:namespace />audit<%= auditEntry.getAuditEntryId() %>">
			<div class="user-display">

				<%
				String portraitURL = StringPool.BLANK;

				String auditEntryUserName = StringPool.BLANK;

				if (auditEntry.getUserId() != OSBConstants.USER_DEFAULT_USER_ID) {
					User auditEntryUser = UserLocalServiceUtil.getUser(auditEntry.getUserId());

					portraitURL = auditEntryUser.getPortraitURL(themeDisplay);

					auditEntryUserName = auditEntryUser.getFullName();
				}
				else {
					auditEntryUserName = "Auto";
				}
				%>

				<div class="audit user-avatar" style="background-image: url('<%= portraitURL %>&height=30&width=30')"></div>

				<span>
					<%= HtmlUtil.escape(auditEntryUserName) %>
				</span>
			</div>

			&gt;

			<span class="summary">
				<liferay-ui:message key="<%= auditEntry.getActionLabel() %>" />

				<liferay-ui:message key="<%= auditEntry.getFieldClassNameIdLabel() %>" />
			</span>

			<div class="create-date">
				<span title="<%= fullDateFormatDateTime.format(auditEntry.getCreateDate()) %>">
					<%= shortDateFormatDate.format(auditEntry.getCreateDate()) %> <%= shortDateFormatTime.format(auditEntry.getCreateDate()) %>
				</span>
			</div>
		</div>

		<div class="content">
			<c:if test="<%= auditEntry.getAction() != AuditEntryConstants.ACTION_AUDIT %>">
				<div class="column txt-sb w20">
					<div class="left-column">
						<liferay-ui:message key="field" />
					</div>
				</div>

				<div class="column txt-sb w40">
					<div class="middle-column">
						<liferay-ui:message key="original-value" />
					</div>
				</div>

				<div class="column txt-sb w40">
					<div class="content-column-content right-column">
						<liferay-ui:message key="new-value" />
					</div>
				</div>
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

				<div class="clearfix"></div>

				<c:choose>
					<c:when test="<%= curAuditEntry.getAction() == AuditEntryConstants.ACTION_AUDIT %>">

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
					</c:when>
					<c:otherwise>
						<div class="column txt-sb w20">
							<div class="left-column">
								<liferay-ui:message key="<%= curAuditEntry.getFieldLabel() %>" />
							</div>
						</div>

						<div class="column w40">
							<div class="middle-column">
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
							</div>
						</div>

						<div class="column w40">
							<div class="right-column">
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
							</div>
						</div>
					</c:otherwise>
				</c:choose>

			<%
			}
			%>

		</div>
	</div>

<%
}
%>