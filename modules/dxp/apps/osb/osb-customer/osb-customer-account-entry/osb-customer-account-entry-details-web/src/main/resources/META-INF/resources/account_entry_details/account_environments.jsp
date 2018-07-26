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
%>

<h2>
	<liferay-ui:message key="environment-configurations" />
</h2>

<liferay-portlet:renderURL var="addAccountEnvironmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
	<portlet:param name="mvcRenderCommandName" value="/edit_account_environment" />
	<portlet:param name="accountEntryId" value="<%= String.valueOf(accountEntry.getAccountEntryId()) %>" />
</liferay-portlet:renderURL>

<aui:button onClick='<%= renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "new-environment-configuration") + "', '" + addAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "editAccountEnvironment')" %>' value="add" />

<%
List<AccountEnvironment> accountEnvironments = AccountEnvironmentLocalServiceUtil.getAccountEnvironments(accountEntry.getAccountEntryId());

for (AccountEnvironment accountEnvironment : accountEnvironments) {
	ProductEntry productEntry = ProductEntryLocalServiceUtil.getProductEntry(accountEnvironment.getProductEntryId());
%>

	<div>
		<h4><%= HtmlUtil.escape(accountEnvironment.getName()) %></h4>

		<strong><liferay-ui:message key="product" />:</strong> <%= HtmlUtil.escape(productEntry.getDisplayName()) %>

		<span class="spacer"></span>

		<strong>LR:</strong> <%= LanguageUtil.get(request, accountEnvironment.getEnvLFRLabel()) %>

		<hr />

		<div>
			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="operating-system" />:

					<br />

					<%= LanguageUtil.get(request, accountEnvironment.getEnvOSLabel()) %>

					<c:if test="<%= Validator.isNotNull(accountEnvironment.getEnvOSCustom()) %>">
						- <%= HtmlUtil.escape(accountEnvironment.getEnvOSCustom()) %>
					</c:if>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="java-version" />:

					<br />

					<%= LanguageUtil.get(request, accountEnvironment.getEnvJVMLabel()) %>
				</aui:col>
			</aui:row>

			<br />

			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="application-server" />:

					<br />

					<%= LanguageUtil.get(request, accountEnvironment.getEnvASLabel()) %>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="database" />:

					<br />

					<%= LanguageUtil.get(request, accountEnvironment.getEnvDBLabel()) %>
				</aui:col>
			</aui:row>

			<br />

			<aui:row>
				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="portal-ext" />:

					<br />

					<%
					AccountEnvironmentAttachment portalExtAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PORTAL_EXT);
					%>

					<c:if test="<%= portalExtAccountEnvironmentAttachment != null %>">
						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/account_environment_attachment" var="accountEnvironmentAttachmentURL">
							<portlet:param name="accountEnvironmentAttachmentId" value="<%= String.valueOf(portalExtAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()) %>" />
						</liferay-portlet:resourceURL>

						<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= portalExtAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
					</c:if>
				</aui:col>

				<aui:col width="<%= 50 %>">
					<liferay-ui:message key="patch-info" />:

					<br />

					<%
					AccountEnvironmentAttachment patchLevelAccountEnvironmentAttachment = AccountEnvironmentAttachmentLocalServiceUtil.fetchAccountEnvironmentAttachment(accountEnvironment.getAccountEnvironmentId(), AccountEnvironmentAttachmentConstants.TYPE_PATCH_LEVEL);
					%>

					<c:if test="<%= patchLevelAccountEnvironmentAttachment != null %>">
						<liferay-portlet:resourceURL copyCurrentRenderParameters="<%= false %>" id="/account_environment_attachment" var="accountEnvironmentAttachmentURL">
							<portlet:param name="accountEnvironmentAttachmentId" value="<%= String.valueOf(patchLevelAccountEnvironmentAttachment.getAccountEnvironmentAttachmentId()) %>" />
						</liferay-portlet:resourceURL>

						<aui:a href="<%= accountEnvironmentAttachmentURL.toString() %>" label="<%= patchLevelAccountEnvironmentAttachment.getFileName() %>" target="_blank" />
					</c:if>
				</aui:col>
			</aui:row>

			<aui:row>
				<aui:col width="<%= 100 %>">
					<liferay-portlet:renderURL var="editAccountEnvironmentURL" windowState="<%= LiferayWindowState.POP_UP.toString() %>">
						<portlet:param name="mvcRenderCommandName" value="/edit_account_environment" />
						<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
					</liferay-portlet:renderURL>

					<aui:button onClick='<%= renderResponse.getNamespace() + "openDialog('" + LanguageUtil.get(request, "update-environment-configuration") + "', '" + editAccountEnvironmentURL.toString() + "', '" + renderResponse.getNamespace() + "editAccountEnvironment')" %>' value="edit" />

					<portlet:actionURL name="deleteAccountEnvironment" var="deleteAccountEnvironmentURL">
						<portlet:param name="redirect" value="<%= currentURL %>" />
						<portlet:param name="accountEnvironmentId" value="<%= String.valueOf(accountEnvironment.getAccountEnvironmentId()) %>" />
					</portlet:actionURL>

					<aui:button href="<%= deleteAccountEnvironmentURL.toString() %>" value="delete" />
				</aui:col>
			</aui:row>
		</div>
	</div>

<%
}
%>