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

<%@ include file="/init.jsp" %>

<%
String detailTab = ParamUtil.getString(request, "detailTab");

String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

if (Validator.isNull(backURL)) {
	PortletURL portletURL = renderResponse.createRenderURL();

	portletURL.setWindowState(LiferayWindowState.MAXIMIZED);

	backURL = portletURL.toString();
}

long accountEntryId = ParamUtil.getLong(request, "accountEntryId");

AccountEntry accountEntry = null;

try {
	accountEntry = AccountEntryLocalServiceUtil.getAccountEntry(accountEntryId);
}
catch (NoSuchAccountEntryException nsaee) {
}

String corpProjectUuid = BeanParamUtil.getString(accountEntry, request, "corpProjectUuid");
int type = BeanParamUtil.getInteger(accountEntry, request, "type", AccountEntryConstants.TYPE_GROUP);
int industry = BeanParamUtil.getInteger(accountEntry, request, "industry");
long partnerEntryId = BeanParamUtil.getLong(accountEntry, request, "partnerEntryId");
int tier = BeanParamUtil.getInteger(accountEntry, request, "tier", AccountEntryConstants.TIER_REGULAR);

Address address = null;

if (accountEntry != null) {
	address = accountEntry.getAddress();
}

long addressId = BeanPropertiesUtil.getLong(address, "addressId");

long countryId = BeanParamUtil.getLong(address, request, "countryId");
long regionId = BeanParamUtil.getLong(address, request, "regionId");

String defaultEWSADossieraProjectKey = StringPool.BLANK;

if (accountEntry != null) {
	defaultEWSADossieraProjectKey = accountEntry.getEWSADossieraProjectKey();
}

String ewsaDossieraProjectKey = ParamUtil.getString(request, "ewsaDossieraProjectKey", defaultEWSADossieraProjectKey);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/admin/edit_account_entry.jsp");
portletURL.setParameter("detailTab", detailTab);
portletURL.setParameter("accountEntryId", String.valueOf(accountEntryId));
%>

<portlet:actionURL name="updateAccountEntry" var="updateAccountEntryURL">
	<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
</portlet:actionURL>

<aui:form action="<%= updateAccountEntryURL %>" method="post">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="backURL" type="hidden" value="<%= backURL %>" />
	<aui:input name="accountEntryId" type="hidden" value="<%= accountEntryId %>" />
	<aui:input name="accountProjectId" type="hidden" value="<%= AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID %>" />
	<aui:input name="corpProjectUuid" type="hidden" value="<%= corpProjectUuid %>" />
	<aui:input name="key" type="hidden" />
	<aui:input name="offeringEntryIds" type="hidden" />
	<aui:input name="partnerEntryId" type="hidden" value="<%= partnerEntryId %>" />
	<aui:input name="addressId" type="hidden" value="<%= addressId %>" />

	<liferay-ui:tabs
		backURL="<%= backURL %>"
		names="project"
	/>

	<liferay-ui:error exception="<%= AccountEntryCodeException.class %>" message="please-enter-a-valid-code" />

	<liferay-ui:error exception="<%= AccountEntryCorpProjectException.class %>">

		<%
		AccountEntry curAccountEntry = AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(corpProjectUuid);
		%>

		<c:if test="<%= curAccountEntry != null %>">
			<liferay-ui:message arguments="<%= new Object[] {HtmlUtil.escape(curAccountEntry.getName())} %>" key="the-corp-project-is-already-linked-to-x" />
		</c:if>
	</liferay-ui:error>

	<liferay-ui:error exception="<%= AccountEntryIndustryException.class %>" message="please-enter-a-valid-industry" />
	<liferay-ui:error exception="<%= AccountEntryLanguageIdException.class %>" message="please-select-valid-support-languages" />
	<liferay-ui:error exception="<%= AccountEntryMaximumCustomersException.class %>" message="the-number-of-contacts-has-exceeded-the-maximum-number-of-contacts" />
	<liferay-ui:error exception="<%= AccountEntryNameException.class %>" message="please-enter-a-valid-name" />
	<liferay-ui:error exception="<%= AccountEntryPartnerEntryException.class %>" message="please-enter-a-valid-partner-entry" />
	<liferay-ui:error exception="<%= AccountEntrySupportRegionException.class %>" message="please-select-valid-support-regions" />
	<liferay-ui:error exception="<%= AddressCityException.class %>" message="please-enter-a-valid-city" />
	<liferay-ui:error exception="<%= AddressStreetException.class %>" message="please-enter-a-valid-address-line" />
	<liferay-ui:error exception="<%= AddressZipException.class %>" message="please-enter-a-valid-postal-code" />
	<liferay-ui:error exception="<%= DuplicateAccountEntryException.class %>" message="please-enter-a-unique-code" />

	<aui:model-context bean="<%= accountEntry %>" model="<%= AccountEntry.class %>" />

	<c:if test="<%= accountEntry != null %>">
		<div class="sub-header">
			<span class="first segment">
				<liferay-ui:message key="created-by" />:

				<span class="txt-sb">
					<%= HtmlUtil.escape(PortalUtil.getUserName(accountEntry.getUserId(), accountEntry.getUserName())) %>
				</span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="last-modified" />:

				<span class="txt-sb">
					<%= HtmlUtil.escape(PortalUtil.getUserName(accountEntry.getModifiedUserId(), accountEntry.getModifiedUserName())) %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(accountEntry.getModifiedDate()) %>
				</span>
			</span>
			<span class="spacer"></span>

			<span class="segment">
				<liferay-ui:message key="last-audited" />:

				<%
				AuditEntry auditEntry = AuditEntryLocalServiceUtil.getLastAuditEntry(PortalUtil.getClassNameId(AccountEntry.class.getName()), accountEntry.getAccountEntryId(), AuditEntryConstants.FIELD_NOT_APPLICABLE, AuditEntryConstants.ACTION_AUDIT);
				%>

				<span class="txt-sb">
					<c:choose>
						<c:when test="<%= auditEntry != null %>">

							<%
							boolean verified = GetterUtil.getBoolean(auditEntry.getNewValue());
							%>

							<%= verified ? LanguageUtil.get(request, "verified") : LanguageUtil.get(request, "out-of-sync") %> <liferay-ui:message key="on" /> <%= longDateFormatDateTime.format(auditEntry.getCreateDate()) %>
						</c:when>
						<c:otherwise>
							N/A
						</c:otherwise>
					</c:choose>
				</span>
			</span>
			<span class="spacer"></span>

			<%
			WorkflowTask workflowTask = null;

			List<WorkflowTask> workflowTasks = WorkflowTaskManagerUtil.search(OSBConstants.COMPANY_ID, 0, null, AccountEntry.class.getName(), new Long[] {accountEntry.getAccountEntryId()}, null, null, false, null, true, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

			if (accountEntry.isPending()) {
				for (WorkflowTask curWorkflowTask : workflowTasks) {
					Map<String, Serializable> optionalAttributes = curWorkflowTask.getOptionalAttributes();

					String entryClassName = GetterUtil.getString(optionalAttributes.get(WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME));

					if (entryClassName.equals(AccountEntry.class.getName())) {
						workflowTask = curWorkflowTask;

						break;
					}
				}
			}
			%>

			<span class="<%= (workflowTask == null) ? "last" : "" %> segment">
				<liferay-ui:message key="status" />:

				<c:choose>
					<c:when test="<%= workflowTask != null %>">

						<%
						PortletURL workflowTaskURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

						workflowTaskURL.setParameter("struts_action", "/my_workflow_tasks/edit_workflow_task");
						workflowTaskURL.setParameter("redirect", currentURL);
						workflowTaskURL.setParameter("workflowTaskId", String.valueOf(workflowTask.getWorkflowTaskId()));
						%>

						<aui:a href="<%= workflowTaskURL.toString() %>" label="<%= accountEntry.getStatusLabel() %>" target="_blank" />
					</c:when>
					<c:otherwise>
						<span class="txt-sb">
							<%= LanguageUtil.get(request, accountEntry.getStatusLabel()) %>
						</span>
					</c:otherwise>
				</c:choose>

				<c:if test="<%= !workflowTasks.isEmpty() %>">

					<%
					PortletURL workflowTasksURL = PortletURLFactoryUtil.create(request, PortletKeys.MY_WORKFLOW_TASK, themeDisplay.getPlid(), PortletRequest.RENDER_PHASE);

					workflowTasksURL.setParameter("tabs1", "other-assignees");
					workflowTasksURL.setParameter("accountEntryCode", StringPool.QUOTE + accountEntry.getCode() + StringPool.QUOTE);
					%>

					(<aui:a href="<%= workflowTasksURL.toString() %>" label="open-workflow-tasks" target="_blank" />)
				</c:if>
			</span>

			<c:if test="<%= workflowTask != null %>">
				<span class="spacer"></span>

				<span class="last segment">
					<liferay-ui:message key="assigned-to" />:

					<span class="txt-sb">

						<%
						List<WorkflowTaskAssignee> workflowTaskAssignees = workflowTask.getWorkflowTaskAssignees();

						for (int i = 0; i < workflowTaskAssignees.size(); i++) {
							WorkflowTaskAssignee workflowTaskAssignee = workflowTaskAssignees.get(i);

							String assignedToName = StringPool.BLANK;

							String assigneeClassName = workflowTaskAssignee.getAssigneeClassName();

							if (assigneeClassName.equals(Role.class.getName())) {
								Role role = RoleLocalServiceUtil.getRole(workflowTaskAssignee.getAssigneeClassPK());

								assignedToName = role.getName();
							}
							else if (assigneeClassName.equals(User.class.getName())) {
								User curUser = UserLocalServiceUtil.getUser(workflowTaskAssignee.getAssigneeClassPK());

								assignedToName = curUser.getFullName();
							}
						%>

							<%= HtmlUtil.escape(assignedToName) %><%= ((i + 1) < workflowTaskAssignees.size()) ? StringPool.COMMA_AND_SPACE : "" %>

						<%
						}
						%>

					</span>
				</span>
			</c:if>
		</div>

		<br />
	</c:if>

	<table class="lfr-table">
		<c:if test="<%= accountEntry != null %>">
			<tr>
				<td>
					<liferay-ui:message key="dossiera-account-name" />
				</td>
				<td>
					<%= HtmlUtil.escape(accountEntry.getCorpEntryName()) %>
				</td>
			</tr>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="project-name" />
			</td>
			<td>
				<aui:input label="" name="name" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="code" />
			</td>
			<td>
				<aui:input label="" name="code" />
			</td>
		</tr>

		<c:if test="<%= accountEntry != null %>">

			<%
			List<AccountEntry> redirectAccountEntries = AccountEntryLocalServiceUtil.getRedirectAccountEntries(accountEntry.getAccountEntryId());
			%>

			<c:if test="<%= !redirectAccountEntries.isEmpty() %>">
				<tr>
					<td>
						<liferay-ui:message key="redirect-codes" />
					</td>
					<td>
						<table>

							<%
							for (AccountEntry redirectAccountEntry : redirectAccountEntries) {
							%>

								<tr>
									<td>
										<%= redirectAccountEntry.getCode() %>
									</td>
									<td>
										<portlet:actionURL name="deleteAccountEntry" var="deleteURL">
											<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
											<portlet:param name="accountEntryId" value="<%= String.valueOf(redirectAccountEntry.getAccountEntryId()) %>" />
										</portlet:actionURL>

										<liferay-ui:icon-delete
											label="<%= true %>"
											url="<%= deleteURL %>"
										/>
									</td>
								</tr>

							<%
							}
							%>

						</table>
					</td>
				</tr>
			</c:if>
		</c:if>

		<tr>
			<td>
				<liferay-ui:message key="corp-project" />
			</td>
			<td>

				<%
				CorpProject corpProject = CorpProjectLocalServiceUtil.fetchCorpProjectByUuid(corpProjectUuid);
				%>

				<span id="<portlet:namespace />corpProjectName">
					<c:if test="<%= corpProject != null %>">
						<%--<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_CORP_PROJECT_ADMIN %>" var="corpProjectURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
							<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
							<portlet:param name="backURL" value="<%= currentURL %>" />
							<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
						</liferay-portlet:renderURL>--%>

						<aui:a href="#" label="<%= corpProject.getName() %>" target="_blank" />
					</c:if>
				</span>

				<portlet:renderURL var="selectCorpProjectURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/admin/select_corp_project.jsp" />
					<portlet:param name="callback" value="selectCorpProject" />
				</portlet:renderURL>

				<%
				String taglibSelectCorpProject = "var corpProjectWindow = window.open('" + selectCorpProjectURL + "', 'corp-project', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); corpProjectWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectCorpProject %>" value="select" />

				<aui:button onClick='<%= renderResponse.getNamespace() + "removeCorpProject();" %>' value="remove" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="ewsa-dossiera-project-key" />
			</td>
			<td>
				<aui:input label="" name="ewsaDossieraProjectKey" type="text" value="<%= ewsaDossieraProjectKey %>" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="type" />
			</td>
			<td>
				<aui:select label="" name="type">

					<%
					for (int i = 1; i <= 4; i++) {
					%>

						<aui:option label="<%= AccountEntryConstants.getTypeLabel(i) %>" selected="<%= type == i %>" value="<%= i %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="industry" />
			</td>
			<td>
				<aui:select label="" name="industry">
					<aui:option value="" />

					<%
					List<ListType> industryTypes = ListTypeServiceUtil.getListTypes(AccountEntryConstants.LIST_TYPE_INDUSTRY);

					for (ListType industryType : industryTypes) {
					%>

						<aui:option label="<%= industryType.getName() %>" selected="<%= industryType.getListTypeId() == industry %>" value="<%= industryType.getListTypeId() %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="partner" />
			</td>
			<td>

				<%
				String partnerEntryCode = StringPool.BLANK;

				try {
					PartnerEntry partnerEntry = PartnerEntryLocalServiceUtil.getPartnerEntry(partnerEntryId);

					partnerEntryCode = partnerEntry.getCode();
				}
				catch (NoSuchPartnerEntryException nspe) {
				}
				%>

				<span id="<portlet:namespace />partnerEntryCode">
					<c:choose>
						<c:when test="<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) && (partnerEntryId > 0) %>">
							<portlet:renderURL var="partnerEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
								<portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" />
								<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
								<portlet:param name="partnerEntryId" value="<%= String.valueOf(partnerEntryId) %>" />
							</portlet:renderURL>

							<aui:a href="<%= partnerEntryURL.toString() %>" label="<%= partnerEntryCode %>" />
						</c:when>
						<c:otherwise>
							<%= partnerEntryCode %>
						</c:otherwise>
					</c:choose>
				</span>

				<portlet:renderURL var="selectPartnerEntryURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
					<portlet:param name="mvcPath" value="/admin/select_partner_entry.jsp" />
				</portlet:renderURL>

				<%
				String taglibSelectPartnerEntry = "var partnerEntryWindow = window.open('" + selectPartnerEntryURL + "', 'partner', 'directories=no,height=640,location=no,menubar=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,width=680'); void(''); partnerEntryWindow.focus();";
				%>

				<aui:button onClick="<%= taglibSelectPartnerEntry %>" value="select" />

				<aui:button id="removePartnerEntryButton" onClick='<%= renderResponse.getNamespace() + "removePartnerEntry();" %>' value="remove" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="partner-first-line-support" />
			</td>
			<td>
				<liferay-ui:input-field
					bean="<%= accountEntry %>"
					disabled="<%= partnerEntryId <= 0 %>"
					field="partnerManagedSupport"
					id="partnerManagedSupportCheckbox"
					model="<%= AccountEntry.class %>"
				/>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="tier" />
			</td>
			<td>
				<aui:select label="" name="tier">

					<%
					for (int curTier : AccountEntryConstants.TIERS) {
					%>

						<aui:option label="<%= AccountEntryConstants.getTierLabel(curTier) %>" selected="<%= tier == curTier %>" value="<%= curTier %>" />

					<%
					}
					%>

				</aui:select>
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="maximum-contacts" />
			</td>
			<td>
				<aui:input label="" name="maxCustomers" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="special-instructions" />
			</td>
			<td>
				<aui:input label="" name="instructions" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="oem-instructions" />
			</td>
			<td>

				<%
				List<AccountAttachment> accountAttachments = AccountAttachmentLocalServiceUtil.getAccountAttachments(accountEntryId, AccountProjectConstants.DEFAULT_ACCOUNT_PROJECT_ID, AccountAttachmentConstants.TYPE_OEM_INSTRUCTIONS);

				AccountAttachment accountAttachment = null;

				if (!accountAttachments.isEmpty()) {
					accountAttachment = accountAttachments.get(0);

					LiferayPortletURL accountAttachmentURL = PortletURLFactoryUtil.create(request, portletDisplay.getId(), layout.getPlid(), PortletRequest.RESOURCE_PHASE);

					accountAttachmentURL.setCopyCurrentRenderParameters(false);
					accountAttachmentURL.setParameter("accountAttachmentId", String.valueOf(accountAttachment.getAccountAttachmentId()));
					accountAttachmentURL.setResourceID("accountAttachment");
				%>

					<div id="<portlet:namespace />accountAttachment">
						<aui:input name="accountAttachmentId" type="hidden" value="<%= accountAttachment.getAccountAttachmentId() %>" />
						<aui:input name="deleteAccountAttachment" type="hidden" />

						<aui:a href="<%= accountAttachmentURL.toString() %>" label="<%= accountAttachment.getFileName() %>" target="_blank" />

						<aui:button onClick='<%= renderResponse.getNamespace() + "removeAccountAttachment();" %>' value="remove" />
					</div>

				<%
				}
				%>

				<aui:input id="accountAttachmentField" inputCssClass='<%= (accountAttachment != null) ? "hide" : "" %>' label="" name="accountAttachment" type="file" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="additional-notes" />
			</td>
			<td>
				<aui:input label="" name="notes" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street1" />
			</td>
			<td>
				<aui:input bean="<%= address %>" field="street1" label="" model="<%= Address.class %>" name="street1" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street2" />
			</td>
			<td>
				<aui:input bean="<%= address %>" field="street2" label="" model="<%= Address.class %>" name="street2" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="street3" />
			</td>
			<td>
				<aui:input bean="<%= address %>" field="street3" label="" model="<%= Address.class %>" name="street3" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="country" />
			</td>
			<td>
				<aui:select bean="<%= address %>" field="countryId" label="" name="countryId" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="state-province" />
			</td>
			<td>
				<aui:select bean="<%= address %>" field="regionId" label="" name="regionId" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="city" />
			</td>
			<td>
				<aui:input bean="<%= address %>" field="city" label="" model="<%= Address.class %>" name="city" />
			</td>
		</tr>
		<tr>
			<td>
				<liferay-ui:message key="postal-code" />
			</td>
			<td>
				<aui:input bean="<%= address %>" field="zip" label="" model="<%= Address.class %>" name="zip" />
			</td>
		</tr>
	</table>

	<br />

	<div>
		<aui:button onClick='<%= "submitForm(document." + renderResponse.getNamespace() + "fm);" %>' value="save" />

		<c:if test="<%= accountEntry != null %>">
			<portlet:renderURL var="assignWorkersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry_workers.jsp" />
				<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= assignWorkersURL %>" label="assign-workers" />

			<portlet:renderURL var="assignCustomersURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry_customers.jsp" />
				<portlet:param name="backURL" value="<%= portletURL.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= assignCustomersURL %>" label="assign-customers" />

			<portlet:renderURL var="viewOrdersEntriesURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="tabs1" value="sales" />
				<portlet:param name="tabs2" value="orders" />
				<portlet:param name="redirect" value="<%= redirect %>" />
				<portlet:param name="advancedSearch" value="<%= Boolean.TRUE.toString() %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
				<portlet:param name="accountEntryName" value="<%= accountEntry.getName() %>" />
			</portlet:renderURL>

			<aui:a cssClass="btn btn-default" href="<%= viewOrdersEntriesURL %>" label="view-orders" />

			<c:if test="<%= PortletPropsValues.REMOTE_REST_SERVICE_API_DOSSIERA_ENABLED && permissionChecker.isOmniadmin() %>">
				<portlet:actionURL name="auditAccountEntry" var="auditOrdersURL">
					<portlet:param name="redirect" value="<%= portletURL.toString() %>" />
					<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
				</portlet:actionURL>

				<aui:a cssClass="btn btn-default" href="<%= auditOrdersURL %>" label="audit-project" />
			</c:if>
		</c:if>

		<aui:a cssClass="btn btn-default" href="<%= backURL %>" label="cancel" />
	</div>

	<br />

	<%
	request.setAttribute("edit_account_entry.jsp-accountEntry", accountEntry);
	request.setAttribute("edit_account_entry.jsp-detailTab", detailTab);
	request.setAttribute("edit_account_entry.jsp-portletURL", portletURL);
	%>

	<liferay-util:include page="/admin/edit_account_entry/details_tabs.jsp" servletContext="<%= application %>" />
</aui:form>

<aui:script use="liferay-address,liferay-dynamic-select">
	new Liferay.DynamicSelect(
		[
			{
				select: '<portlet:namespace />countryId',
				selectData: Liferay.Address.getCountries,
				selectDesc: 'nameCurrentValue',
				selectId: 'countryId',
				selectSort: '<%= true %>',
				selectVal: '<%= countryId %>'
			},
			{
				select: '<portlet:namespace />regionId',
				selectData: Liferay.Address.getRegions,
				selectDesc: 'name',
				selectId: 'regionId',
				selectVal: '<%= regionId %>'
			}
		]
	);
</aui:script>

<aui:script>
	function <portlet:namespace />addColumn(row, html) {
		var cell = row.insertCell(-1);

		cell.innerHTML = html;
	}

	function <portlet:namespace />removeAccountAttachment() {
		var A = AUI();

		document.<portlet:namespace />fm.<portlet:namespace />deleteAccountAttachment.value = true;

		A.one('#<portlet:namespace />accountAttachment').hide();
		A.one('#<portlet:namespace />accountAttachmentField').show();
	}

	function <portlet:namespace />removeCorpProject() {
		document.<portlet:namespace />fm.<portlet:namespace />corpProjectId.value = 0;

		document.getElementById('<portlet:namespace />corpProjectName').innerHTML = '';
	}

	function <portlet:namespace />removePartnerEntry() {
		document.<portlet:namespace />fm.<portlet:namespace />partnerEntryId.value = 0;

		var nameEl = document.getElementById('<portlet:namespace />partnerEntryCode');

		nameEl.href = '';
		nameEl.innerHTML = '';

		document.<portlet:namespace />fm.<portlet:namespace />partnerManagedSupport.value = '';
		document.<portlet:namespace />fm.<portlet:namespace />partnerManagedSupportCheckbox.disabled = true;
	}

	function <portlet:namespace />removeRow(inputName, value, tableId, row) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		values = values.replace(value + ',', '');

		document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

		var table = document.getElementById(tableId).getElementsByTagName('tbody')[0];

		table.removeChild(row.parentNode.parentNode);
	}

	function <portlet:namespace/>searchOfferingEntry() {
		submitForm(document.<portlet:namespace />fm, '<portlet:actionURL><portlet:param name="jspPage" value="/admin/edit_account_entry.jsp" /></portlet:actionURL>');
	}

	function <portlet:namespace />selectCorpProject(corpProjectUuid, corpProjectName) {
		document.<portlet:namespace />fm.<portlet:namespace />corpProjectUuid.value = corpProjectUuid;

		document.getElementById('<portlet:namespace />corpProjectName').innerHTML = corpProjectName;
	}

	function <portlet:namespace />selectPartnerEntry(partnerEntryId, partnerEntryCode) {
		document.<portlet:namespace />fm.<portlet:namespace />partnerEntryId.value = partnerEntryId;

		var nameEl = document.getElementById('<portlet:namespace />partnerEntryCode');

		if (<%= RoleLocalServiceUtil.hasUserRole(permissionChecker.getUserId(), OSBConstants.ROLE_OSB_ADMINISTRATOR_ID) %>) {
			nameEl.innerHTML = "<a href='<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/admin/edit_partner_entry.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /></portlet:renderURL>&<portlet:namespace />partnerEntryId=" + partnerEntryId + "'>" + partnerEntryCode + "</a>";
		}
		else {
			nameEl.innerHTML = partnerEntryCode + '&nbsp;';
		}

		document.<portlet:namespace />fm.<portlet:namespace />partnerManagedSupportCheckbox.disabled = false;
	}

	function <portlet:namespace />selectRow(inputName, value, tableId, columnValues) {
		var values = document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value;

		if (values.indexOf(value + ',') == -1) {
			values += value + ',';

			document.<portlet:namespace />fm['<portlet:namespace />' + inputName].value = values;

			var table = document.getElementById(tableId);

			table.parentNode.parentNode.className = 'lfr-search-container';

			var tBody = table.getElementsByTagName('tbody')[0];

			var row = tBody.insertRow(-1);

			row.className = 'results-row';

			for (i = 0; i < columnValues.length; i++) {
				<portlet:namespace />addColumn(row, columnValues[i]);
			}

			<portlet:namespace />addColumn(row, '<input class="btn btn-default" onClick="<portlet:namespace />removeRow(\'' + inputName + '\', \'' + value + '\', \'' + tableId + '\', this);" type="button" value="<liferay-ui:message key="remove" />" />');
		}
	}

	function <portlet:namespace />selectSupportRegion(supportRegionId, supportRegionName) {
		<portlet:namespace />selectRow('supportRegionIds', supportRegionId, '<portlet:namespace />supportRegionSearchContainer', [supportRegionName]);
	}

	Liferay.provide(
		window,
		'<portlet:namespace />openDialog',
		function(title, url, popupId) {
			Liferay.Util.openWindow(
				{
					cache: false,
					dialog: {
						align: Liferay.Util.Window.ALIGN_CENTER,
						centered: true,
						height: 350,
						resizable: false,
						width: 870
					},
					id: popupId,
					title: title,
					uri: url
				}
			);
		},
		['aui-dialog', 'aui-overlay-manager', 'liferay-util-window']
	);
</aui:script>

<c:if test="<%= windowState.equals(WindowState.MAXIMIZED) %>">
	<script type="text/javascript">
		Liferay.Util.focusFormField(document.<portlet:namespace />fm.<portlet:namespace />name);
	</script>
</c:if>