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
String tabs1 = ParamUtil.getString(request, "tabs1");

String redirect = ParamUtil.getString(request, "redirect", currentURL);
String backURL = ParamUtil.getString(request, "backURL", redirect);

long corpProjectId = ParamUtil.getLong(request, "corpProjectId");

CorpProject corpProject = CorpProjectLocalServiceUtil.getCorpProject(corpProjectId);

AccountEntry accountEntry = AccountEntryLocalServiceUtil.fetchCorpProjectAccountEntry(corpProjectId);

PortletURL portletURL = renderResponse.createRenderURL();

portletURL.setParameter("mvcPath", "/corp_project_admin/view_corp_project.jsp");
portletURL.setParameter("tabs1", tabs1);
portletURL.setParameter("corpProjectId", String.valueOf(corpProjectId));
portletURL.setWindowState(WindowState.MAXIMIZED);
%>

<liferay-ui:header
	backURL="<%= backURL %>"
	title="<%= corpProject.getName() %>"
/>

<c:if test='<%= SessionMessages.contains(renderRequest, "lcsSyncFailed") %>'>
	<div class="portlet-msg-alert">
		<liferay-ui:message key="there-was-an-error-syncing-with-lcs" />
	</div>
</c:if>

<liferay-ui:error exception="<%= CorpProjectSalesforceProjectKeyException.class %>" message="the-corp-project-must-have-a-valid-salesforce-project-key" />

<table class="lfr-table">
<tr>
	<td>
		<liferay-ui:message key="dossiera-project-key" />:
	</td>
	<td>
		<%= HtmlUtil.escape(corpProject.getDossieraProjectKey()) %>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="salesforce-project-key" />:
	</td>
	<td>
		<%= HtmlUtil.escape(corpProject.getSalesforceProjectKey()) %>
	</td>
</tr>
<tr>
	<td>
		<liferay-ui:message key="support-project" />:
	</td>
	<td>
		<c:if test="<%= accountEntry != null %>">
			<liferay-portlet:renderURL portletName="<%= OSBPortletKeys.OSB_ADMIN %>" var="accountEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
				<portlet:param name="mvcPath" value="/admin/edit_account_entry.jsp" />
				<portlet:param name="backURL" value="<%= currentURL %>" />
				<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
			</liferay-portlet:renderURL>

			<a href="<%= accountEntryURL.toString() %>" target="_blank"><%= HtmlUtil.escape(accountEntry.getName()) %></a>
		</c:if>
	</td>
</tr>
</table>

<br />

<div>
	<c:if test="<%= PortletPropsValues.REMOTE_JSON_SERVICE_API_LCS_ENABLED %>">
		<portlet:actionURL name="syncToLCS" var="syncToLCSURL">
			<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
			<portlet:param name="redirect" value="<%= currentURL %>" />
			<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProjectId) %>" />
		</portlet:actionURL>

		<input onClick="location.href = '<%= HtmlUtil.escape(syncToLCSURL.toString()) %>';" type="button" value="<liferay-ui:message key="sync-to-lcs" />" />
	</c:if>

	<liferay-portlet:renderURL var="editCorpProjectURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
		<portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project.jsp" />
		<portlet:param name="backURL" value="<%= currentURL %>" />
		<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProjectId) %>" />
	</liferay-portlet:renderURL>

	<input onClick="location.href = '<%= HtmlUtil.escape(editCorpProjectURL.toString()) %>';" type="button" value="<liferay-ui:message key="edit" />" />
</div>

<br />

<liferay-ui:tabs
	names="users,messages"
	param="tabs1"
	url="<%= portletURL.toString() %>"
/>

<c:choose>
	<c:when test='<%= tabs1.equals("messages") %>'>
		<c:if test='<%= SessionMessages.contains(renderRequest, "lcsSyncFailed") %>'>
			<div class="portlet-msg-alert">
				<liferay-ui:message key="there-was-an-error-syncing-with-lcs" />
			</div>
		</c:if>

		<div>
			<input onClick="location.href = '<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="mvcPath" value="/corp_project_admin/edit_corp_project_message.jsp" /><portlet:param name="redirect" value="<%= portletURL.toString() %>" /><portlet:param name="corpProjectId" value="<%= String.valueOf(corpProjectId) %>" /></portlet:renderURL>';" type="button" value="<liferay-ui:message key="add-message" />" />
		</div>

		<br />

		<%
		List<CorpProjectMessage> corpProjectMessages = CorpProjectMessageLocalServiceUtil.getCorpProjectMessages(corpProjectId);
		%>

		<liferay-ui:search-container
			emptyResultsMessage="there-are-no-messages"
			headerNames="message,type,severity,display-lcs,display-lesa"
		>
			<liferay-ui:search-container-results
				results="<%= corpProjectMessages %>"
				total="<%= corpProjectMessages.size() %>"
			/>

			<liferay-ui:search-container-row
				className="com.liferay.osb.model.CorpProjectMessage"
				escapedModel="<%= true %>"
				keyProperty="corpProjectMessageId"
				modelVar="corpProjectMessage"
			>
				<liferay-ui:search-container-column-text
					name="title"
					value="<%= corpProjectMessage.getTitle() %>"
				/>

				<liferay-ui:search-container-column-text
					name="message"
					value="<%= StringUtil.shorten(corpProjectMessage.getContent(), 150) %>"
				/>

				<liferay-ui:search-container-column-text
					name="type"
					translate="<%= true %>"
					value="<%= corpProjectMessage.getTypeLabel() %>"
				/>

				<liferay-ui:search-container-column-text
					name="severity"
					translate="<%= true %>"
					value="<%= corpProjectMessage.getSeverityLevelLabel() %>"
				/>

				<liferay-ui:search-container-column-text
					name="display-cp"
				>
					<c:if test="<%= corpProjectMessage.isDisplayCP() %>">
						<liferay-ui:icon image="checked" />
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="display-lcs"
				>
					<c:if test="<%= corpProjectMessage.isDisplayLCS() %>">
						<liferay-ui:icon image="checked" />
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-text
					name="display-lesa"
				>
					<c:if test="<%= corpProjectMessage.isDisplayLESA() %>">
						<liferay-ui:icon image="checked" />
					</c:if>
				</liferay-ui:search-container-column-text>

				<liferay-ui:search-container-column-jsp
					align="right"
					path="/corp_project_admin/corp_project_message_action.jsp"
				/>
			</liferay-ui:search-container-row>

			<liferay-ui:search-iterator paginate="<%= false %>" />
		</liferay-ui:search-container>
	</c:when>
	<c:otherwise>
		<c:if test="<%= OSBCorpProjectPermission.contains(permissionChecker, corpProject, OSBActionKeys.ASSIGN_MEMBERS) %>">
			<div class="add-user">
				<portlet:actionURL name="addCorpProjectUsers" var="addCorpProjectUsersURL">
					<portlet:param name="mvcPath" value="/corp_project_admin/view_corp_project.jsp" />
					<portlet:param name="backURL" value="<%= currentURL %>" />
					<portlet:param name="corpProjectId" value="<%= String.valueOf(corpProject.getCorpProjectId()) %>" />
				</portlet:actionURL>

				<aui:form action="<%= addCorpProjectUsersURL %>" method="post" name="fm">
					<liferay-ui:error exception="<%= NoSuchUserException.class %>" message="user-not-found.-please-verify-that-the-email-is-registered-with-liferay.com" />

					<div class="aui-helper-hidden portlet-msg-error" id="<portlet:namespace />errorContainer"></div>

					<div class="aui-field-label">
						<liferay-ui:message key="add-user" />
					</div>

					<div class="aui-field-help">
						<liferay-ui:message key="to-add-a-user,-specify-their-liferay-registered-email-address" />
					</div>

					<div class="aui-field-input-container" style="margin: 5px 0 10px;">
						<input class="email-address" name="<portlet:namespace />emailAddress" placeholder="<liferay-ui:message key="email-address" />" type="text" />

						<aui:button type="submit" value="add" />
					</div>
				</aui:form>
			</div>
		</c:if>

		<div class="corp-project-admin">
			<liferay-ui:search-container
				emptyResultsMessage="there-are-no-users"
				iteratorURL="<%= portletURL %>"
			>
				<liferay-ui:search-container-results
					results="<%= CorpMembersUtil.getCorpProjectUsers(corpProject.getCorpProjectId(), StringPool.BLANK, 0, searchContainer.getStart(), searchContainer.getEnd(), null) %>"
					total="<%= CorpMembersUtil.getCorpProjectUsersCount(corpProject.getCorpProjectId(), StringPool.BLANK, 0) %>"
				/>

				<liferay-ui:search-container-row
					className="com.liferay.portal.model.User"
					escapedModel="<%= true %>"
					keyProperty="userId"
					modelVar="user"
				>
					<liferay-ui:search-container-row-parameter
						name="corpProject"
						value="<%= corpProject %>"
					/>

					<liferay-ui:search-container-column-text
						name="name"
						value="<%= user.getFullName() %>"
					/>

					<liferay-ui:search-container-column-text
						name="email-address"
						value="<%= user.getEmailAddress() %>"
					/>

					<liferay-ui:search-container-column-text
						name="screen-name"
						value="<%= user.getScreenName() %>"
					/>

					<liferay-ui:search-container-column-text
						name="roles"
					>

						<%
						User user2 = (User)row.getObject();
						%>

						<div class="actions roles" data-userId="<%= user2.getUserId() %>">
							<aui:input data-roleId="<%= OSBConstants.ROLE_OSB_CORP_ADMIN_ID %>" data-userId="<%= user2.getUserId() %>" disabled="<%= themeDisplay.getUserId() == user2.getUserId() %>" label="user-admin" name='<%= "userAdmin" + user2.getUserId() %>' type="checkbox" value="<%= CorpProjectLocalServiceUtil.hasUserCorpProjectRole(user2.getUserId(), corpProject.getCorpProjectId(), OSBConstants.ROLE_OSB_CORP_ADMIN_ID) %>" />

							<aui:input data-roleId="<%= OSBConstants.ROLE_OSB_CORP_BUYER_ID %>" data-userId="<%= user2.getUserId() %>" label="buyer" name='<%= "buyer" + user2.getUserId() %>' type="checkbox" value="<%= CorpProjectLocalServiceUtil.hasUserCorpProjectRole(user2.getUserId(), corpProject.getCorpProjectId(), OSBConstants.ROLE_OSB_CORP_BUYER_ID) %>" />

							<aui:input data-roleId="<%= OSBConstants.ROLE_OSB_CORP_LCS_USER_ID %>" data-userId="<%= user2.getUserId() %>" label="lcs-user" name='<%= "buyer" + user2.getUserId() %>' type="checkbox" value="<%= CorpProjectLocalServiceUtil.hasUserCorpProjectRole(user2.getUserId(), corpProject.getCorpProjectId(), OSBConstants.ROLE_OSB_CORP_LCS_USER_ID) %>" />
						</div>
					</liferay-ui:search-container-column-text>

					<liferay-ui:search-container-column-jsp
						align="right"
						path="/corp_project_admin/corp_project_user_action.jsp"
					/>
				</liferay-ui:search-container-row>

				<liferay-ui:search-iterator />
			</liferay-ui:search-container>
		</div>

		<aui:script use="aui-base, aui-io">
			A.one('.corp-project-admin').delegate(
				'change',
				function(event) {
					var input = event.currentTarget;

					var actions = input.ancestor('.actions');

					A.io.request(
						'<liferay-portlet:actionURL name="setCorpProjectUserRole" />',
						{
							data: {
								<portlet:namespace />assignRole: input.attr('checked'),
								<portlet:namespace />corpProjectId: <%= corpProject.getCorpProjectId() %>,
								<portlet:namespace />roleId: input.attr('data-roleId'),
								<portlet:namespace />userId: actions.attr('data-userId')
							},
							dataType: 'json',
							method: 'post',
							on: {
								start: function(event, id, obj) {
									var errorContainer = A.one('#<portlet:namespace />errorContainer');

									errorContainer.hide();
								},
								success: function(event, id, obj) {
									var response = this.get('responseData');

									if (response.exception) {
										var message = '<liferay-ui:message key="an-unexpected-error-has-occurred" />';

										if (response.exception === "<%= CorpProjectSalesforceProjectKeyException.class.getName() %>") {
											message = '<liferay-ui:message key="the-corp-project-must-have-a-valid-salesforce-project-key" unicode="<%= true %>" />';
										}

										var errorContainer = A.one('#<portlet:namespace />errorContainer');

										errorContainer.html(message);
										errorContainer.show();

										input.attr('checked', !input.attr('checked'));
									}
								}
							}
						}
					);
				},
				'.actions.roles input[type=checkbox]'
			);
		</aui:script>
	</c:otherwise>
</c:choose>