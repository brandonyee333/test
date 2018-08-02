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

<%
AccountEntry accountEntry = accountEntryViewDisplayContext.getAccountEntry();
%>

<h2>
	<liferay-ui:message key="environment-configurations" />
</h2>

<c:if test="<%= OSBAccountEnvironmentPermission.contains(permissionChecker, accountEntry.getAccountEntryId(), OSBActionKeys.ADD_ACCOUNT_ENVIRONMENT) %>">
	<liferay-portlet:renderURL var="addAccountEnvironmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
		<portlet:param name="mvcRenderCommandName" value="/edit_account_environment" />
		<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
	</liferay-portlet:renderURL>

	<aui:button onClick='<%= renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "new-environment-configuration") + "', '" + addAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "editAccountEnvironment')" %>' value="add" />
</c:if>

<%
JSONArray jsonArray = accountEntryViewDisplayContext.getAccountEnvironmentsJSONArray();

for (int i = 0; i < jsonArray.length(); i++) {
	JSONObject jsonObject = jsonArray.getJSONObject(i);
%>

	<div>
		<h4><%= jsonObject.getString("name") %></h4>

		<strong><liferay-ui:message key="product" />:</strong> <%= jsonObject.getString("productEntryDisplayName") %>

		<span class="spacer"></span>

		<strong>LR:</strong> <%= jsonObject.getString("envLFRLabel") %>

		<hr />

		<div>
			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="operating-system" />:

					<br />

					<%= jsonObject.getString("envOSLabel") %>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="java-version" />:

					<br />

					<%= jsonObject.getString("envJVMLabel") %>
				</aui:col>
			</aui:row>

			<br />

			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="application-server" />:

					<br />

					<%= jsonObject.getString("envASLabel") %>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="database" />:

					<br />

					<%= jsonObject.getString("envDBLabel") %>
				</aui:col>
			</aui:row>

			<br />

			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="portal-ext" />:

					<br />

					<c:if test='<%= jsonObject.has("patchLevelAccountEnvironmentAttachmentFileName") %>'>
						<aui:a href='<%= jsonObject.getString("patchLevelAccountEnvironmentAttachmentURL") %>' label='<%= jsonObject.getString("patchLevelAccountEnvironmentAttachmentFileName") %>' target="_blank" />
					</c:if>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="patch-info" />:

					<br />

					<c:if test='<%= jsonObject.has("portalExtAccountEnvironmentAttachmentFileName") %>'>
						<aui:a href='<%= jsonObject.getString("portalExtAccountEnvironmentAttachmentURL") %>' label='<%= jsonObject.getString("portalExtAccountEnvironmentAttachmentFileName") %>' target="_blank" />
					</c:if>
				</aui:col>
			</aui:row>

			<aui:row>
				<aui:col width="<%= 100 %>">
					<c:if test='<%= jsonObject.has("editAccountEnvironmentURL") %>'>
						<aui:button onClick='<%= renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "update-environment-configuration") + "', '" + jsonObject.getString("editAccountEnvironmentURL") + "', '" + renderResponse.getNamespace() + "editAccountEnvironment')" %>' value="edit" />
					</c:if>

					<c:if test='<%= jsonObject.has("deleteAccountEnvironmentURL") %>'>
						<aui:button href='<%= jsonObject.getString("deleteAccountEnvironmentURL") %>' value="delete" />
					</c:if>
				</aui:col>
			</aui:row>
		</div>
	</div>

<%
}
%>